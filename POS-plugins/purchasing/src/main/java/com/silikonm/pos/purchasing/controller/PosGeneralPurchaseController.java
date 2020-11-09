package com.silikonm.pos.purchasing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.silikonm.common.dto.pos.GeneralPurchaseBean;
import com.silikonm.common.dto.pos.ItemBean;
import com.silikonm.pos.purchasing.extended.PosGeneralPurchasingsTableModel;
import com.silikonm.pos.purchasing.extended.PosPurchaseOrderItemDetailsTableModel;
import com.silikonm.pos.purchasing.extended.PosPurchaseOrderItemsTableModel;
import com.silikonm.pos.purchasing.model.PosGeneralPurchaseModel;
import com.silikonm.pos.purchasing.ui.PosGeneralPurchaseItemDetails;
import com.silikonm.pos.purchasing.ui.PosGeneralPurchaserDetailsDialog;
import com.silikonm.pos.purchasing.ui.PosGeneralPurchasingPanelView;
import com.silikonm.utilities.Alert;

public class PosGeneralPurchaseController extends KeyAdapter implements
		ActionListener, MouseListener {

	// main panel view where user can see all the POs places already.
	private PosGeneralPurchasingPanelView view;

	// custom table model for the PO showing table
	private PosGeneralPurchasingsTableModel purchaseOrdersTableModel;

	// main model
	private PosGeneralPurchaseModel model;

	// available item details showing/adding dialog for the POS PO.
	private PosGeneralPurchaseItemDetails itemDetailsDialogView;

	// custom table model for the item browser table for the PO
	private PosPurchaseOrderItemDetailsTableModel availableItemsTableModel;

	// PO details management dialog.
	private PosGeneralPurchaserDetailsDialog poDetailsDialogView;

	// custom table model for the PO items table.
	private PosPurchaseOrderItemsTableModel poItemsTableModel;

	// cash the po id when user selects a po for displaying
	private int poid = -1;

	private TableRowSorter<TableModel> sorter;

	public PosGeneralPurchaseController(PosGeneralPurchasingPanelView view,
			PosGeneralPurchaseModel model) {

		this.model = model;// initialize main model
		this.view = view;// initialize main view panel

		view.addDeleteMenuItemActionListener(this);

		purchaseOrdersTableModel = new PosGeneralPurchasingsTableModel(null);
		view.addPurchaseOrdersTableMouseListener(this);
		// fill PO table
		fillPOTable();

		// set POs table model
		view.setPurchaseOrdersTableModel(purchaseOrdersTableModel);
		view.setNewAction(this);// set tollbar new action button action
		//
		this.poDetailsDialogView = new PosGeneralPurchaserDetailsDialog();
		this.poDetailsDialogView.setModal(true);
		this.poDetailsDialogView.setLocationRelativeTo(null);

		// initialize PO items table model and set it to the table
		this.poItemsTableModel = new PosPurchaseOrderItemsTableModel(null);
		this.poDetailsDialogView
				.setPurchaseOrderItemsTableModel(poItemsTableModel);
		// set add items button action
		this.poDetailsDialogView.setAddPOItemButtonActionListener(this);
		// set cancel button action
		this.poDetailsDialogView.setCancelButtonActionListener(this);
		// set save PO button action listener
		this.poDetailsDialogView.setSaveButtonActionListener(this);
		this.poDetailsDialogView
				.setDeletePurchaseOrderMenuItemActionListner(this);

		//
		this.itemDetailsDialogView = new PosGeneralPurchaseItemDetails();
		this.itemDetailsDialogView.setModal(true);
		this.itemDetailsDialogView.setLocationRelativeTo(null);
		this.itemDetailsDialogView.addAddButtonActionListener(this);
		this.itemDetailsDialogView.addCancelButtonActionListener(this);
		this.itemDetailsDialogView.addSearchTextBoxKeyListener(this);
		// initialize available items table model and set it to the table
		this.availableItemsTableModel = new PosPurchaseOrderItemDetailsTableModel(
				null);

		sorter = new TableRowSorter<TableModel>(availableItemsTableModel);

		this.itemDetailsDialogView
				.setAvailableItemsTableModel(availableItemsTableModel);
		this.itemDetailsDialogView.setItemsTableRowSorter(sorter);

		// this.itemDetailsDialogView.getItemsTableRowSorter().convertRowIndexToModel(0);

	}

	/**
	 * Get all the available POs and fill them into the main panel's table.
	 */
	private void fillPOTable() {
		List<GeneralPurchaseBean> pos = model.getAllPurchaseOrders();
		purchaseOrdersTableModel.resetTable();// reset table data before fill

		for (GeneralPurchaseBean bean : pos) {
			purchaseOrdersTableModel.insertRow(new Object[] { bean.getGpId(),
					bean, bean.getGpDate() });
		}
	}

	private void getAvailableItems() {
		List<ItemBean> items = model.getAvailableItems();

		availableItemsTableModel.resetTable();// resete table data
		for (ItemBean bean : items) {
			availableItemsTableModel.insertRow(new Object[] {
					bean.getItemCode(), bean, bean.getUnit(),
					bean.getSupplier().getSupplierName() });
		}
	}

	public void getAvailablePurchaseOrderItems(int poid) {
		List<ItemBean> items = model.getPurchaseOrderItems(poid);
		poItemsTableModel.resetTable();
		for (ItemBean itemBean : items) {
			poItemsTableModel.insertRow(new Object[] { itemBean.getItemCode(),
					itemBean, itemBean.getSinhalaName(), itemBean.getQty(),
					itemBean.getUnit(), itemBean.getUnitPrice() });
		}
	}

	/**
	 * Implements Action Listener to control all the actoins in each registered
	 * view
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("TOOLBAR_NEW_ACTION")) {
			poDetailsDialogView
					.setPurchaseOrderNo(model
							.getNewPurchaseOrderNo(poDetailsDialogView
									.getOrderedDate()));
			poDetailsDialogView.setVisible(true);
			model.getNextPurchaseOrderID(Integer.valueOf(new SimpleDateFormat(
					"yyyy").format(poDetailsDialogView.getOrderedDate())));
		}
		//
		if (e.getActionCommand().equals(
				PosGeneralPurchaserDetailsDialog.CLOSE_PO_DIALOG)) {
			poItemsTableModel.resetTable();
			poDetailsDialogView.dispose();
		}

		//
		if (e.getActionCommand().equals(
				PosGeneralPurchaserDetailsDialog.ADD_PO_ITEM)) {
			getAvailableItems();// get available items
			itemDetailsDialogView.setVisible(true);
		}

		//
		if (e.getActionCommand().equals(
				PosGeneralPurchaserDetailsDialog.SAVE_PURCHASE_ORDER)) {
			GeneralPurchaseBean bean = new GeneralPurchaseBean();
			bean.setGpId(model.getPoID());
			bean.setGpNo(poDetailsDialogView.getPurchaseOrderNo());
			bean.setGpDate(poDetailsDialogView.getOrderedDate());

			model.savePurchaseOrder(bean, poItemsTableModel.getPOItems());
			poItemsTableModel.resetTable();
			fillPOTable();
			poDetailsDialogView.dispose();

		}

		//
		if (e.getActionCommand().equals(PosGeneralPurchaseItemDetails.ADD_ITEM)) {
			ItemBean bean = availableItemsTableModel
					.getSelectedItem(itemDetailsDialogView
							.getSelectedTableRowIndex());
			if (itemDetailsDialogView.getQty() == BigDecimal.ZERO) {
				Alert.message("Please enter the item quantity!");
				return;
			}
			if (itemDetailsDialogView.getUnitPrice() == BigDecimal.ZERO) {
				Alert.message("Please enter the item unit price!");
				return;
			}

			if (bean == null) {
				Alert.message("Please select the required item!");
				return;
			}

			bean.setQty(itemDetailsDialogView.getQty());
			bean.setUnitPrice(itemDetailsDialogView.getUnitPrice());

			poItemsTableModel.insertRow(new Object[] { bean.getItemCode(),
					bean, bean.getSinhalaName(), bean.getQty(), bean.getUnit(),
					bean.getUnitPrice() });

			itemDetailsDialogView.setQty(BigDecimal.ZERO);
			itemDetailsDialogView.setUniPirce(BigDecimal.ZERO);
		}
		//
		if (e.getActionCommand().equals(
				PosGeneralPurchaseItemDetails.CLOSE_GP_ITEM_DIALOG)) {
			availableItemsTableModel.resetTable();
			poid = -1;
			itemDetailsDialogView.dispose();
		}
		//
		if (e.getActionCommand().equals(
				PosGeneralPurchasingPanelView.DELETE_GENERAL_PURCHASING)) {
			if (view.getSelectedTableRowIndex() == -1) {
				Alert.message("Please select a purchase order to be deleted!");
				return;
			}
			if (Alert
					.confirmYesNoDialog("Are you sure you need to delete this purchase order?") == Alert.NO_OPTION) {
				return;
			}
			model.deletePurchaseOrder(purchaseOrdersTableModel
					.getSelectedPurchaseOrder(view.getSelectedTableRowIndex())
					.getGpId());
			fillPOTable();
		}
		//
		if (e.getActionCommand().equals(
				PosGeneralPurchaserDetailsDialog.DELETE_PURCHASE_ORDER_ITEM)) {
			if (Alert
					.confirmYesNoDialog("Are you sure you need to delete this purchase order item?") == Alert.NO_OPTION) {
				return;
			}
			ItemBean itemBean = poItemsTableModel
					.getItemBean(poDetailsDialogView.getSelectedTableRowIndex());

			if (itemBean.getTransactionID() != -1) {
				model.deletePurchaseOrderItem(itemBean.getTransactionID());
				getAvailablePurchaseOrderItems(poid);
			} else {
				poItemsTableModel.removeRow(poDetailsDialogView
						.getSelectedTableRowIndex());
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			if (e.getSource() instanceof JTable) {
				JTable tmp = (JTable) e.getSource();
				if (tmp.getName().equals(view.PURCHASE_ORDERS_TABLE)) {
					GeneralPurchaseBean bean = model
							.getPurchaseOrder(purchaseOrdersTableModel
									.getSelectedPurchaseOrder(
											view.getSelectedTableRowIndex())
									.getGpId());
					model.setPoID(bean.getGpId());
					poDetailsDialogView.setPurchaseOrderNo(bean.getGpNo());
					poDetailsDialogView.setOrderDate(bean.getGpDate());
					getAvailablePurchaseOrderItems(bean.getGpId());
					poid = bean.getGpId();
					poDetailsDialogView.setVisible(true);
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (itemDetailsDialogView.getSearchKey() == null) {
			sorter.setRowFilter(null);

		} else {

			try {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)"
						+ itemDetailsDialogView.getSearchKey()));
			} catch (PatternSyntaxException pse) {
				pse.printStackTrace();

			}
		}
	}

}
