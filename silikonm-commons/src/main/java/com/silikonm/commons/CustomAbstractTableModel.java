package com.silikonm.commons;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CustomAbstractTableModel extends AbstractTableModel{

	private Object[] columns;
	private List<Object[]> data;
	
	public CustomAbstractTableModel() {
		this.data = new ArrayList<Object[]>();
	}
	
	public CustomAbstractTableModel(Object[] columns, List<Object[]> data) {

		if (data == null) {
			this.data = new ArrayList<Object[]>();
		} else {
			this.data = data;
		}
		this.columns = columns;
	}
	
	public void setColumns(Object[] columns) {
		this.columns = columns;
	}
	
	public void setData(List<Object[]> data) {
		this.data = data;
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
	public Object getValueAt(int rowIndex , int columnIndex) {		
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
	public Class<?> getColumnClass(int columnIndex) {
		return columns[columnIndex].getClass();
	}
	
	public void clearTable() {
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
	

}
