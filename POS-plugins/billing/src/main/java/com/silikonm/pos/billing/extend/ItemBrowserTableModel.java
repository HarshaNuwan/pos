package com.silikonm.pos.billing.extend;

import com.silikonm.common.dto.pos.ItemBean;
import com.silikonm.commons.CustomAbstractTableModel;

public class ItemBrowserTableModel extends CustomAbstractTableModel{

	private Object[] columns = new Object[]{"Item Code", "Item Name", "Barcode"};
	
	public ItemBrowserTableModel() {
		setColumns(columns);
	}

	public ItemBean getItem(int selectedItemTableRowIndex) {
		
		return (ItemBean)getValueAt(selectedItemTableRowIndex, 1);
	}
	
	
}
