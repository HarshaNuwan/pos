package com.silikonm.pos.billing.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.silikonm.common.dto.pos.ItemBean;
import com.silikonm.common.dto.pos.TransactionBean;
import com.silikonm.pos.billing.extend.BilledItemsTableModel;
import com.silikonm.pos.billing.extend.ItemBrowserTableModel;
import com.silikonm.pos.billing.extend.StoreBrowserTableModel;
import com.silikonm.pos.billing.model.POSModel;
import com.silikonm.pos.billing.ui.BillingPanel;
import com.silikonm.pos.billing.ui.HelpDialog;
import com.silikonm.pos.billing.ui.ItemBrowser;
import com.silikonm.pos.billing.ui.ItemQty;
import com.silikonm.pos.billing.ui.PosCashPaymentDialog;
import com.silikonm.pos.billing.ui.PosItemDiscountDialog;
import com.silikonm.pos.billing.ui.StoreBrowser;
import com.silikonm.utilities.Alert;
import com.silikonm.utilities.BillPrinter;

/**
 * Created with IntelliJ IDEA. User: Harsha Date: 10/19/13 Time: 12:32 AM
 */
public class POSController implements ActionListener, KeyListener {

	// initialize main view and the model
	private BillingPanel view;
	private POSModel model;

	// item browser dialog
	private ItemBrowser itemBrowser;

	// stock browser dialog
	private StoreBrowser storeBrowser;

	// item quantity dialog
	private ItemQty itemQty;

	// item discount dialog
	private PosItemDiscountDialog itemDiscountDialog;

	// Billed items table model
	private BilledItemsTableModel billedItemsTableModel;

	// Store browser table model
	private StoreBrowserTableModel storeBrowserTableModel = new StoreBrowserTableModel();

	// cash payment dialog
	private PosCashPaymentDialog cashPaymentDialog;
	//
	private ItemBrowserTableModel itemBrowserTableModel = new ItemBrowserTableModel();

	private TableRowSorter<TableModel> itemsRowSorter;

	public POSController(BillingPanel view, POSModel model) {
		this.view = view;
		this.model = model;

		this.billedItemsTableModel = new BilledItemsTableModel(null);
		view.setBilledItemsTableModel(billedItemsTableModel);

		this.cashPaymentDialog = new PosCashPaymentDialog();
		this.cashPaymentDialog.setLocationRelativeTo(null);
		this.cashPaymentDialog.setModal(true);
		this.cashPaymentDialog.pack();

		itemBrowser = new ItemBrowser();
		itemBrowser.setTitle("Available Items");
		itemBrowser.setItemsTableModel(itemBrowserTableModel);
		itemBrowser.addSearchBoxKeyListener(this);
		//
		storeBrowser = new StoreBrowser();
		storeBrowser.setTitle("Available Items");
		storeBrowser.setStoreBrowserItemsTableModel(storeBrowserTableModel);
		itemsRowSorter = new TableRowSorter<TableModel>(itemBrowserTableModel);
		itemBrowser.setItemsTableRowSorter(itemsRowSorter);

		itemQty = new ItemQty();

		itemDiscountDialog = new PosItemDiscountDialog();

		createF1KeyAction();
		// createF2KeyAction(); //there is an issue with the F2 key, need to check further.
		createF3KeyAction();
		createF4KeyAction();
		createF5KeyAction();
		createF6KeyAction();
		createF7KeyAction();
		// createF8KeyAction(); //Why items table act wierd when pressing F8
		// createF1PlusCtrlKeysAction();

		//
		createMainViewDeleteKeyAction();

		//
		createItemBrowserEnterKeyAction();
		createItemBrowserEscapeKeyAction();
		createStoreBrowserEnterKeyAction();
		createStoreBrowserEscapeKeyAction();
		createCashPaymentDialogEscapeKeyAction();
		createCashPaymentDialogEnterKeyAction();
		// create item discount text box action in the item discount dialog
		createDiscountAmountTextBoxKeyListener();
		createDiscountEscapeKeyAction();
		//
		createQuantityDialogTextBoxKeyListener();
		createItemQtyEscapeKeyAction();

		createEscapeKeyAction();

	}

	/**
	 * createCashPaymentDialogEnterKeyAction When user press ENTER key in the cash payment amount textbox in the cash payment dialog box this method
	 * does followings, 1. Get the sub total from the billed items table. 2. Set the payment amount in the POS panel. 3. Calculate the balance by
	 * subtracting the sub total form the payed amount 4. Dispose the cash payment dialog.
	 */
	private void createCashPaymentDialogEnterKeyAction() {
		cashPaymentDialog.addAmountTextBoxKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.VK_ENTER == e.getKeyCode()) {
					BigDecimal subTotal = billedItemsTableModel.getSubTotal();
					view.setPayment(cashPaymentDialog.getAmount().setScale(2, RoundingMode.CEILING));
					view.setBalance(cashPaymentDialog.getAmount().subtract(subTotal).setScale(3, RoundingMode.CEILING));
					cashPaymentDialog.dispose();
				}
			}
		});

	}

	/**
	 * createCashPaymentDialogEscapeKeyAction This method dispose the cash amount entering dialog, while cancelling the cash payment.
	 */
	private void createCashPaymentDialogEscapeKeyAction() {
		cashPaymentDialog.addEscapeKeyAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cashPaymentDialog.dispose();
			}
		}, "ESCAPE");

	}

	/**
	 * createMainViewDeleteKeyAction Delete billed items from the main view.
	 */
	private void createMainViewDeleteKeyAction() {
		view.addDeleteKeyAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		}, "DELETE");

	}

	private void calculateSubTotal() {
		view.setSubTotal(billedItemsTableModel.getSubTotal());

		System.out.println(view.getCashReceived().compareTo(BigDecimal.ZERO));

		if (view.getCashReceived().compareTo(BigDecimal.ZERO) > 0) {
			view.setBalance(view.getCashReceived().subtract(billedItemsTableModel.getSubTotal()).setScale(3, RoundingMode.CEILING));
		}
	}

	private void createItemQtyEscapeKeyAction() {
		itemQty.addEscapeKeyAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemQty.dispose();
			}
		}, "ESCAPE");
	}

	private void createDiscountEscapeKeyAction() {
		itemDiscountDialog.addEscapeKeyAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemDiscountDialog.dispose();
			}
		}, "ESCAPE");
	}

	private void createStoreBrowserEscapeKeyAction() {
		storeBrowser.addEscapeKeyAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				storeBrowser.dispose();
			}
		}, "ESCAPE");
	}

	/**
	 * createItemBrowserEscapeKeyAction
	 */
	private void createItemBrowserEscapeKeyAction() {
		itemBrowser.addEscapeKeyAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JRootPane rootPane = (JRootPane) e.getSource();
				ItemBrowser itemBrowser = (ItemBrowser) rootPane.getFocusCycleRootAncestor();
				if (itemBrowser.getFocusOwner() instanceof JTextField) {
					itemBrowser.dispose();
					view.focusItemsTable();
				} else if (itemBrowser.getFocusOwner() instanceof JTable) {
					itemBrowser.setSearchBoxFocussed();
				}

			}
		}, "ESCAPE");

	}

	/**
	 * createDiscountAmountTextBoxKeyListener 1. Check whether there is a selected row in the billed items table. 2. Pass discount value and the item
	 * contaied table row index to the table model. 3. Table model calculates and set the total item totals in the table.
	 */
	public void createDiscountAmountTextBoxKeyListener() {
		itemDiscountDialog.addDiscountTextBoxActionListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (KeyEvent.VK_ENTER == e.getKeyCode()) {
					if (view.getSelectedRowIndex() != -1) {
						billedItemsTableModel.setItemDiscount(itemDiscountDialog.getItemDiscount(), view.getSelectedRowIndex());
						itemDiscountDialog.dispose();
						calculateSubTotal();
					}

				}
			}
		});
	}

	/**
	 * 
	 */
	private void createQuantityDialogTextBoxKeyListener() {
		itemQty.addQtyTextBoxKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.VK_ENTER == e.getKeyCode()) {

					if (itemQty.getItemQty() == BigDecimal.ZERO) {
						Alert.message("Please enter the qty!");
						return;
					}

					TransactionBean transactionBean = storeBrowserTableModel.getStockItem(storeBrowser.getSelectedTableRowIndex());

					ItemBean itemBean = model.getItemBean(transactionBean.getItemCode());
					Object[] row = new Object[] { transactionBean.getItemCode(), itemBean.getItemName(), itemQty.getItemQty(), itemBean.getUnit(), transactionBean.getTransactionUnitPrice(),
							BigDecimal.ZERO, BigDecimal.ZERO };
					billedItemsTableModel.insertRow(row);
					billedItemsTableModel.calculateItemTotal();
					calculateSubTotal();
					itemQty.dispose();
					storeBrowser.dispose();
				}
			}
		});

	}

	/**
	 * createStoreBrowserEnterKeyAction
	 */
	private void createStoreBrowserEnterKeyAction() {
		storeBrowser.addEnterKeyAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemQty.pack();
				itemQty.setLocationRelativeTo(null);
				itemQty.setUnitPrice(storeBrowserTableModel.getUnitPrice(storeBrowser.getSelectedTableRowIndex()));
				itemQty.setVisible(true);

			}
		}, "ENTER");

	}

	/**
	 * createItemBrowserEnterKeyAction
	 * 
	 */
	private void createItemBrowserEnterKeyAction() {
		itemBrowser.addEnterKeyAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JRootPane rootPane = (JRootPane) e.getSource();
				ItemBrowser itemBrowser = (ItemBrowser) rootPane.getFocusCycleRootAncestor();

				if (itemBrowser.getFocusOwner() instanceof JTextField) {
					JTextField temp = (JTextField) itemBrowser.getFocusOwner();
					String searchKey = temp.getText();
					itemBrowser.setItemsTableFocussed();
				} else if (itemBrowser.getFocusOwner() instanceof JTable) {
					storeBrowserTableModel.clearTable();
					;
					for (Object[] row : model.getStocks(itemBrowserTableModel.getItem(itemBrowser.getSelectedItemTableRowIndex()).getItemCode())) {
						storeBrowserTableModel.insertRow(row);
					}
					storeBrowser.setSize(new Dimension(600, 400));
					storeBrowser.setLocationRelativeTo(null);
					storeBrowser.setModal(true);
					storeBrowser.setVisible(true);
				}

			}
		}, "ENTER");

	}

	/**
	 * Accepting CASH PAYMENTS
	 */
	private void createF1KeyAction() {
		view.addF1KeyAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {

				HelpDialog dialog = new HelpDialog();
				dialog.setLocationRelativeTo(view);
				dialog.setVisible(true);

				/*
				 * if (billedItemsTableModel.getRowCount() == 0) { return; }
				 * 
				 * int ans = Alert .confirmYesNoDialog("Are you sure you need to finish the bill?"); if (ans == Alert.NO_OPTION | ans ==
				 * Alert.DEFAULT_OPTION) { return; }
				 * 
				 * cashPaymentDialog.setVisible(true);
				 */

				// int result = Alert.confirmYesNoDialog("PRINT THE BILL?");
				// if (result == Alert.NO_OPTION) {
				// return;
				// } else if (result == Alert.DEFAULT_OPTION) {
				// return;
				// } else {
				// printBill();
				// }

			}
		}, "F1");
	}

	/**
	 * Accepting CREDIT CARDS
	 */
	private void createF2KeyAction() {

		view.addF2KeyAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (billedItemsTableModel.getRowCount() == 0) {
					return;
				}

				int ans = Alert.confirmYesNoDialog("Are you sure you need to finish the bill?");
				if (ans == Alert.NO_OPTION | ans == Alert.DEFAULT_OPTION) {
					return;
				}

				cashPaymentDialog.setVisible(true);
			}
		}, "F2");
	}

	/**
	 * Sell for the CREDITORS
	 */
	private void createF3KeyAction() {
		view.addF3KeyAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (billedItemsTableModel.getRowCount() == 0) {
					return;
				}

				int ans = Alert.confirmYesNoDialog("Are you sure you need to finish the bill?");
				if (ans == Alert.NO_OPTION | ans == Alert.DEFAULT_OPTION) {
					return;
				}

				cashPaymentDialog.setVisible(true);
			}
		}, "F3");
	}

	/**
	 * Show the item browser
	 */
	private void createF4KeyAction() {
		view.addF4KeyAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemBrowserTableModel.clearTable();
				for (Object[] row : model.getItems()) {
					itemBrowserTableModel.insertRow(row);
				}
				itemBrowser.setSize(new Dimension(600, 400));
				itemBrowser.setLocationRelativeTo(null);
				itemBrowser.setModal(true);
				itemBrowser.setVisible(true);
			}
		}, "F4");
	}

	/*
	 * Print the bill and store the transaction into the database. Clears the form.
	 */
	private void createF5KeyAction() {
		view.addF5KeyAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (billedItemsTableModel.getRowCount() == 0) {
					return;
				}

				if (view.getCashReceived().compareTo(BigDecimal.ZERO) == 0) {
					Alert.warning("No payment has received!");
					return;
				}

				if (view.getCashReceived().compareTo(BigDecimal.ZERO) < 0) {
					Alert.warning("Payment not compleate!");
					return;
				}

				int ans = Alert.confirmYesNoDialog("PRINT THE BILL?");
				if (ans == Alert.NO_OPTION) {
					return;
				} else if (ans == Alert.DEFAULT_OPTION) {
					return;
				} else {
					printBill();
				}

			}
		}, "F5");
	}

	/**
	 * Shows discount adding dialog If there are no items in the billed items table discount dialog is not shown
	 */
	private void createF6KeyAction() {
		view.addF6KeyAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (billedItemsTableModel.getRowCount() > 0) {
					itemDiscountDialog.pack();
					itemDiscountDialog.setLocationRelativeTo(null);
					itemDiscountDialog.setVisible(true);
				}

			}
		}, "F6");
	}

	/**
	 * Delete a selected billed item
	 */
	private void createF7KeyAction() {
		view.addF7KeyAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (view.getSelectedRowIndex() == -1) {
					return;
				}
				int ans = Alert.confirmYesNoDialog("Are you sure you need to delete this item?");
				if (ans == Alert.NO_OPTION) {
					return;
				} else if (ans == Alert.DEFAULT_OPTION) {
					return;
				}
				billedItemsTableModel.removeRow(view.getSelectedRowIndex());
				billedItemsTableModel.calculateItemTotal();
				calculateSubTotal();

			}
		}, "F7");
	}

	/**
	 * Cancel a bill
	 */
	private void createF8KeyAction() {
		/*
		 * view.addF8KeyAction(new AbstractAction() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { if (billedItemsTableModel.getRowCount() == 0) { return; }
		 * 
		 * int ans = Alert.confirmYesNoDialog("Are you sure you need to cancel this bill"); if (ans == Alert.NO_OPTION) { return; } else if (ans ==
		 * Alert.DEFAULT_OPTION) { return; } billedItemsTableModel.resetTable(); view.resetForm(); } }, "F8");
		 */
	}

	// private void createF1PlusCtrlKeysAction() {
	// view.addReturnToHomeKeyAction(new AbstractAction() {
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// int ans = Alert
	// .confirmYesNoDialog("Are you sure you need to go back?");
	// if (ans == Alert.NO_OPTION | ans == Alert.DEFAULT_OPTION) {
	// return;
	// }
	// Main.getInstance().changeCard(Main.HOME);
	// }
	// }, "control+F12");
	// }

	private void createEscapeKeyAction() {
		view.addEscapeKeyAction(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showOptionDialog(view, "Are You Sure?", "Exit POS", JOptionPane.OK_OPTION, JOptionPane.OK_OPTION, null, null, null);
				if (confirm == JOptionPane.OK_OPTION) {
					view.disposePOS();
				}

			}
		}, "ESCAPE");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());

	}

	@Override
	public void keyPressed(KeyEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent event) {
		if (event.getSource() instanceof JTextField) {
			JTextField temp = (JTextField) event.getSource();
			if (temp.getText().isEmpty()) {
				itemsRowSorter.setRowFilter(null);
			} else {
				itemsRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + temp.getText()));
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub

	}

	private void printBill() {
		BillPrinter printer = new BillPrinter();
		try {

			int id = model.saveTransaction(billedItemsTableModel.getBilledItems());

			printer.printTheBill(billedItemsTableModel.getBilledItems(), billedItemsTableModel.getSubTotal(), billedItemsTableModel.getTotalDiscount(), view.getCashReceived(), id);

			billedItemsTableModel.resetTable();
			view.resetForm();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}
