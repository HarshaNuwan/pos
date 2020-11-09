package com.silikonm.pos.billing.extend;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.silikonm.common.dto.pos.TransactionBean;
import com.silikonm.commons.CustomAbstractTableModel;

public class StoreBrowserTableModel extends CustomAbstractTableModel{

	private Object[] columns = new Object[]{"ID", "Item Code", "Item Name", "Unit Price(Rs.)"};
	
	public StoreBrowserTableModel() {
		setColumns(columns);
	}
	
	public BigDecimal getItemUnitPrice(int rowIndex){
		return new BigDecimal(getValueAt(rowIndex, 3).toString());
	}

	public TransactionBean getStockItem(int rowIndex) {
		return (TransactionBean)getValueAt(rowIndex, 0);
	}
	
	public BigDecimal getUnitPrice(int rowIndex){
		return new BigDecimal(getValueAt(rowIndex, 3).toString()).setScale(2, RoundingMode.CEILING);
	}

}
