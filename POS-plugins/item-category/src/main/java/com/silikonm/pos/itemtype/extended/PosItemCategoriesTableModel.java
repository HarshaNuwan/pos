package com.silikonm.pos.itemtype.extended;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.silikonm.common.dto.pos.ItemCategoryBean;
import com.silikonm.pos.itemtype.model.PositemCategoriesModel;
import com.silikonm.pos.itemtype.ui.PosItemCategoriesView;
import com.silikonm.pos.itemtype.ui.PosItemCategoriyDetailsDialog;
import com.silikonm.utilities.Alert;

public class PosItemCategoriesTableModel extends AbstractTableModel implements ActionListener, MouseListener {
	private Object[] columns = new Object[] { "Item Category ID", "Category Code", "Category" };
	private List<Object[]> data;
	private PosItemCategoriyDetailsDialog categoriyDetailsDialog;
	private PositemCategoriesModel categoriesModel;
	private PosItemCategoriesView categoriesView;

	public PosItemCategoriesTableModel(List<Object[]> data, PositemCategoriesModel model, PosItemCategoriesView view) {

		this.categoriesModel = model;
		this.categoriesView = view;
		// initialize item categories details dialog.
		categoriyDetailsDialog = new PosItemCategoriyDetailsDialog();
		categoriyDetailsDialog.setLocationRelativeTo(null);
		categoriyDetailsDialog.addSaveButtonActionListener(this);
		categoriyDetailsDialog.addCancelButtonActionListener(this);
		if (data == null) {
			this.data = new ArrayList<Object[]>();
		} else {
			this.data = data;
		}
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
		return columns[columnIndex].getClass();
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

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("TOOLBAR_NEW_ACTION")) {
			categoriyDetailsDialog.setCategoryID(categoriesModel.getNextItemCategoryId());
			categoriyDetailsDialog.setVisible(true);
		} else if (e.getActionCommand().equals("SAVE_CATEGORY")) {
			if (categoriyDetailsDialog.getCategoryName() == null) {
				Alert.message("Please enter the category name!");
				return;
			}
			if (categoriyDetailsDialog.getCategoryName().length() <= 3) {
				Alert.message("Please enter the category name should be greater than 3 letters!");
				return;
			}
			// generate item code
			String itemCode = categoriyDetailsDialog.getCategoryName().substring(0, 3).toUpperCase() + categoriyDetailsDialog.getCategoryID();
			//
			ItemCategoryBean categoryBean = new ItemCategoryBean(categoriyDetailsDialog.getCategoryID(), itemCode, categoriyDetailsDialog.getCategoryName());
			categoriesModel.saveItemCategory(categoryBean);
			//
			resetTable();
			categoriesModel.getAllItemCategories();
			categoriyDetailsDialog.dispose();
		} else if (e.getActionCommand().equals("CLOSE_CATEGORY_DIALOG")) {
			categoriyDetailsDialog.dispose();
		} else if (e.getActionCommand().equals("DELETE_ITEM_CATEGORY")) {
			categoriesModel.deleteItemCategory(getSelectedItemCategory(this.categoriesView.getSelectedTableRowIndex()).getCategoryID());
			resetTable();
			categoriesModel.getAllItemCategories();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof JTable) {
			JTable table = (JTable) e.getSource();
			if (table.getName().equals("ITEM_CATEGORIES_TABLE")) {
				if (e.getClickCount() == 2) {
					ItemCategoryBean bean = getSelectedItemCategory(table.getSelectedRow());
					categoriyDetailsDialog.setCategoryID(bean.getCategoryID());
					categoriyDetailsDialog.setCategoryCode(bean.getCategoryCode());
					categoriyDetailsDialog.setCategoryName(bean.getCategoryName());
					categoriyDetailsDialog.setVisible(true);
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

	/*
	 * private PosSupplierBean getSelectedSupplier(int selectedRowIndex) { Object[] dataRow = data.get(selectedRowIndex); PosSupplierBean bean =
	 * (PosSupplierBean) dataRow[1]; return bean; }
	 */

	private ItemCategoryBean getSelectedItemCategory(int rowIndex) {
		Object[] row = (Object[]) data.get(rowIndex);

		return (ItemCategoryBean) row[2];
	}

}
