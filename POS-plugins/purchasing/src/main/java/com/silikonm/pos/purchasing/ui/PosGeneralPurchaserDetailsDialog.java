package com.silikonm.pos.purchasing.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.silikonm.pos.purchasing.extended.PosItemTableRenderer;
import com.silikonm.pos.purchasing.extended.PosPurchaseOrderItemsTableModel;
import com.toedter.calendar.JDateChooser;

import net.miginfocom.swing.MigLayout;

public class PosGeneralPurchaserDetailsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static final String SAVE_PURCHASE_ORDER = "SAVE_PURCHASE_ORDER";
	public static final String CLOSE_PO_DIALOG = "CLOSE_PO_DIALOG";
	public static final String ADD_PO_ITEM = "ADD_PO_ITEM";
	public static final String DELETE_PURCHASE_ORDER_ITEM = "DELETE_PURCHASE_ORDER_ITEM";

	private JButton btnCancel, btnSave;
	private JLabel lblPosPurchase;
	private JPanel panel;
	private JLabel lblPoNo;
	private JLabel lblPoOrderedDate;
	private JDateChooser orDate;
	private JTextField txtPoNo;
	private JPanel panel_1;
	private JPanel panel_2;
	private JScrollPane scrollPane;
	private JTable tblGpItems;
	private JToolBar toolBar;
	private JButton btnAddItem;
	private JPopupMenu popupMenu;
	private JMenuItem mntmDelete;

	/**
	 * Create the dialog.
	 */
	public PosGeneralPurchaserDetailsDialog() {
		setBounds(100, 100, 736, 449);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			lblPosPurchase = new JLabel(" POS - General Purchase Details");
			lblPosPurchase.setOpaque(true);
			lblPosPurchase.setForeground(Color.WHITE);
			lblPosPurchase.setFont(new Font("Dialog", Font.BOLD, 24));
			lblPosPurchase.setBackground(new Color(0, 153, 0));
			contentPanel.add(lblPosPurchase, BorderLayout.NORTH);
		}
		{
			panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.NORTH);
				panel_1.setLayout(new MigLayout("", "[][grow][][grow]", "[][]"));
				{
					lblPoNo = new JLabel("GP No.");
					panel_1.add(lblPoNo, "cell 0 0");
				}
				{
					txtPoNo = new JTextField();
					panel_1.add(txtPoNo, "cell 1 0 2 1,growx");
					txtPoNo.setFont(new Font("Tahoma", Font.BOLD, 11));
					txtPoNo.setBackground(new Color(102, 255, 0));
					txtPoNo.setEditable(false);
					txtPoNo.setColumns(10);
				}
				{
					lblPoOrderedDate = new JLabel("Date");
					panel_1.add(lblPoOrderedDate, "cell 0 1");
				}
				{
					orDate = new JDateChooser();
					orDate.setDateFormatString("yyyy-MM-dd");
					orDate.setDate(new Date());
					panel_1.add(orDate, "cell 1 1,growx");

				}
			}
			{
				panel_2 = new JPanel();
				panel.add(panel_2, BorderLayout.CENTER);
				panel_2.setLayout(new BorderLayout(0, 0));
				{
					scrollPane = new JScrollPane();
					panel_2.add(scrollPane);
					{
						popupMenu = new JPopupMenu();

						{
							mntmDelete = new JMenuItem("Delete");
							mntmDelete.setActionCommand(DELETE_PURCHASE_ORDER_ITEM);
							mntmDelete.setIcon(new ImageIcon(PosGeneralPurchaserDetailsDialog.class.getResource("/com/silikonm/pos/purchasing/resources/table_2_delete.png")));
							popupMenu.add(mntmDelete);
						}
					}
					{
						tblGpItems = new JTable();
						tblGpItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						addPopup(tblGpItems, popupMenu);
						tblGpItems.setDefaultRenderer(Object.class, new PosItemTableRenderer(2));
						scrollPane.setViewportView(tblGpItems);
					}
				}
				{
					toolBar = new JToolBar();
					toolBar.setFloatable(false);
					panel_2.add(toolBar, BorderLayout.NORTH);
					{
						btnAddItem = new JButton("Add Item");
						btnAddItem.setActionCommand(ADD_PO_ITEM);
						toolBar.add(btnAddItem);
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSave = new JButton("Save");
				btnSave.setActionCommand(SAVE_PURCHASE_ORDER);
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				btnCancel = new JButton("Cancel");
				btnCancel.setActionCommand(CLOSE_PO_DIALOG);
				buttonPane.add(btnCancel);
			}
		}
	}

	public void setPurchaseOrderItemsTableModel(PosPurchaseOrderItemsTableModel model) {
		tblGpItems.setModel(model);
	}

	//
	public void setSaveButtonActionListener(ActionListener al) {
		btnSave.addActionListener(al);
	}

	public void setCancelButtonActionListener(ActionListener al) {
		btnCancel.addActionListener(al);

	}

	public void setAddPOItemButtonActionListener(ActionListener al) {
		btnAddItem.addActionListener(al);
	}

	public void setDeletePurchaseOrderMenuItemActionListner(ActionListener al) {
		mntmDelete.addActionListener(al);
	}

	//

	public void setPurchaseOrderNo(String poNo) {
		txtPoNo.setText(poNo);
	}

	public void setOrderDate(Date orDate) {
		this.orDate.setDate(orDate);
	}

	// public void setReceivedDate(Date rDate) {
	// this.receivedate.setDate(rDate);
	// }

	// getters
	public Date getOrderedDate() {
		return this.orDate.getDate();
	}

	// public Date getReceivedDate() {
	// //return this.receivedate.getDate();
	// }

	public String getPurchaseOrderNo() {
		if (txtPoNo.getText().isEmpty()) {
			return null;
		}

		return txtPoNo.getText();
	}

	public int getSelectedTableRowIndex() {
		if (tblGpItems.getSelectedRowCount() == 0) {
			return -1;
		}
		return tblGpItems.getSelectedRow();
	}

	//

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
