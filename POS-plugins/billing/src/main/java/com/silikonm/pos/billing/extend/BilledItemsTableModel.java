package com.silikonm.pos.billing.extend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.silikonm.common.dto.pos.ItemBean;

public class BilledItemsTableModel extends AbstractTableModel {

	private Object[] columns = new Object[] { "Item Code", "Item Name", "Qty.",
			"Unit", "Price(Rs.)","Discount", "Total" };
	private List<Object[]> data;

	public BilledItemsTableModel(List<Object[]> data) {

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
	
	public void setItemDiscount(BigDecimal discount, int rowIndex){
		setValueAt(discount, rowIndex, 5);
		calculateItemTotal();		
		fireTableDataChanged();
	}
	
	/**
	 * Calculate and return the sub total of the bill
	 * @return
	 */
	public BigDecimal getSubTotal(){
		BigDecimal subTotal = BigDecimal.ZERO;
		
		for(int i=0; i<data.size();i++){
			subTotal = subTotal.add(new BigDecimal(data.get(i)[6].toString()));
		}		
		return subTotal;
	}
	
	public void calculateItemTotal(){
		
		
		for(int i=0; i<data.size();i++){
			BigDecimal qty = new BigDecimal(getValueAt(i, 2).toString());
			BigDecimal unitPrice = new BigDecimal(getValueAt(i, 4).toString());
			BigDecimal itemTotal = qty.multiply(unitPrice);
			BigDecimal discount =  new BigDecimal(getValueAt(i, 5).toString());
			BigDecimal itemSubTotal = (qty.multiply(unitPrice)).subtract(discount);
			setValueAt(itemSubTotal, i, 6);
		}
	}

	public ItemBean getSelectedItem(int selectedTableRowIndex) {
		ItemBean bean = (ItemBean) getValueAt(selectedTableRowIndex, 1);
		return bean;
	}
	
	public BigDecimal getTotalDiscount(){
		BigDecimal discountTotal = BigDecimal.ZERO;
		for(int i=0; i<data.size();i++){			
			discountTotal = discountTotal.add(new BigDecimal(getValueAt(i, 5).toString()));			
		}
		return discountTotal;
	}

	public List<Object[]> getBilledItems() {
		return data;
		
	}

}
