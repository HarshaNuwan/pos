package com.silikonm.pos.purchasing.extended;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.silikonm.common.dto.pos.ItemBean;

public class PosPurchaseOrderItemDetailsTableModel extends AbstractTableModel  {
	private Object[] columns = new Object[] { "Item Code", "Item Name", "Unit", "Supplier"};
	private List<Object[]> data;
	
	
	public PosPurchaseOrderItemDetailsTableModel(List<Object[]> data) {
		
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

	

	public ItemBean getSelectedItem(int selectedTableRowIndex) {
		try {
			ItemBean bean = (ItemBean)getValueAt(selectedTableRowIndex, 1);
			return bean;	
		} catch (Exception e) {
			return null;
		}
		
	}



}
