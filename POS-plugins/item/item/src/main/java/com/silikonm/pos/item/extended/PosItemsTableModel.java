package com.silikonm.pos.item.extended;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.silikonm.common.dto.pos.ItemBean;
import com.silikonm.pos.item.model.PosItemModel;
import com.silikonm.pos.item.ui.PosItemDetailsDialog;
import com.silikonm.pos.item.ui.PosItemsPanelView;
import com.silikonm.utilities.Alert;

public class PosItemsTableModel extends AbstractTableModel implements
		ActionListener, MouseListener {
	private Object[] columns = new Object[] { "Item Code", "Item Name",
			"Description", "Reorder Level", "Supplier",
			"Category", "Unit", "Barcode"};
	private List<Object[]> data;
	private PosItemDetailsDialog posItemDetailsDialog;

	private PosItemModel itemModel;
	private PosItemsPanelView panelView;
	
	

	public PosItemsTableModel(List<Object[]> data, PosItemModel itemModel,
			PosItemsPanelView view) {
		if (data == null) {
			this.data = new ArrayList<Object[]>();
		} else {
			this.data = data;
		}

		this.itemModel = itemModel;
		this.panelView = view;
		//
		posItemDetailsDialog = new PosItemDetailsDialog();
		posItemDetailsDialog.setModal(true);
		posItemDetailsDialog.setLocationRelativeTo(null);

		posItemDetailsDialog.addCancelButtonActionListener(this);
		posItemDetailsDialog.addSaveButtonActionListener(this);
		posItemDetailsDialog.setSuppliersList(itemModel.getSuppliers());
		posItemDetailsDialog.setItemCategoriesList(itemModel.getCategories());
		
		

	}
	
	

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object[] row = data.get(rowIndex);
		return row[columnIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		data.get(rowIndex)[columnIndex] = aValue;

		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int column) {
		return columns[column].toString();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		/*
		 * if (columnIndex == 1 || columnIndex == 3) { return true; }
		 */
		return false;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 0)
			return getValueAt(0, columnIndex).getClass();

		else
			return super.getColumnClass(columnIndex);
	}

	public void resetTable() {
		data.clear();
		fireTableDataChanged();
	}

	public void insertRow(Object[] row) {
		data.add(row);
		fireTableDataChanged();
	}

	public void removeRow(int rowIndex) {
		data.remove(rowIndex);
		fireTableDataChanged();
	}

	private String createNewItemCode(String itemName, int itemCount){
		String newItemCode = "";
		
		return newItemCode;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if (e.getActionCommand().equals("TOOLBAR_NEW_ACTION")) {
			posItemDetailsDialog.setVisible(true);
		}
		if (e.getActionCommand().equals("CLOSE_POS_ITEM_DIALOG")) {
			posItemDetailsDialog.dispose();
		}
		if (e.getActionCommand().equals("SAVE_ITEM")) {
			if (posItemDetailsDialog.getItemName() == null) {
				Alert.message("Please enter the item name!");
				return;
			}

			if (posItemDetailsDialog.getItemUnit() == null) {
				Alert.message("Please enter the item unit!");
				return;
			}

			if (posItemDetailsDialog.getSupplierCode() == -1) {
				Alert.message("Please select the supplier!");
				return;
			}

			if (posItemDetailsDialog.getCategoryCode() == -1) {
				Alert.message("Please select the category!");
				return;
			}

		/*	if (posItemDetailsDialog.getSinhalaName() == null) {
				Alert.message("Please enter the item name in sinhala!");
				return;
			}*/

			if (posItemDetailsDialog.getReorderLevel() == 0f) {
				Alert.message("Please enter reorder level!");
				return;
			}

			ItemBean bean = new ItemBean();
			//

			if (posItemDetailsDialog.getItemCode() == null) {
				bean.setItemCode(posItemDetailsDialog.getItemName()
						.substring(0, 3).toUpperCase()
						+ (itemModel.getItemCount(posItemDetailsDialog
								.getItemName().substring(0, 3)) + 100));
			} else {
				bean.setItemCode(posItemDetailsDialog.getItemCode());
			}

			//
			bean.setItemName(posItemDetailsDialog.getItemName());
			bean.setSupplierID(posItemDetailsDialog.getSupplierCode());
			bean.setCategoryID(posItemDetailsDialog.getCategoryCode());
			//bean.setSinhalaName(posItemDetailsDialog.getSinhalaName());
			bean.setItemDescription(posItemDetailsDialog.getItemDescription());
			bean.setReorderLevel(posItemDetailsDialog.getReorderLevel());
			bean.setUnit(posItemDetailsDialog.getItemUnit());
			bean.setItemBarcode(posItemDetailsDialog.getBarCode() == null ? ""
					: posItemDetailsDialog.getBarCode());//set barcode
			itemModel.savePosItem(bean);// save the item
			itemModel.getAllItems();
			posItemDetailsDialog.dispose();// close the dialog
		}
		if (e.getActionCommand().equals("DELETE_TABLE_ROW")) {
			if (Alert
					.confirmYesNoDialog("Are you sure you need to delete this item?") == Alert.NO_OPTION) {
				return;
			}
			ItemBean bean = (ItemBean) getValueAt(
					panelView.getSelectedTableRowIndex(), 1);
			if (panelView.getSelectedTableRowIndex() == -1) {
				Alert.message("Please select an item to be deleted!");
				return;
			}

			itemModel.deletePosItem(bean.getItemCode());
			itemModel.getAllItems();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof JTable) {
			JTable table = (JTable) e.getSource();
			if (table.getName().equals(PosItemsPanelView.POS_ITEMS_TABLE)) {
				if (e.getClickCount() == 2) {
					ItemBean bean = (ItemBean) getValueAt(
							panelView.getSelectedTableRowIndex(), 1);

					posItemDetailsDialog.setItemCode(bean.getItemCode());
					posItemDetailsDialog.setItemName(bean.getItemName());
//					posItemDetailsDialog.setItemNameInSinhala(bean
//							.getSinhalaName());
					posItemDetailsDialog
							.setReorderLevel(bean.getReorderLevel());
					posItemDetailsDialog.setItemDescription(bean
							.getItemDescription());
					posItemDetailsDialog.setSelectedItemCategory(bean
							.getCategoryID());
					posItemDetailsDialog.setSelectedSupplier(bean
							.getSupplierID());
					posItemDetailsDialog.setItemUnit(bean.getUnit());
					posItemDetailsDialog.setBarCode(bean.getItemBarcode());
					posItemDetailsDialog.setVisible(true);

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

}
