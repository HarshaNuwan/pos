package com.silikonm.pos.supplier;

import javax.swing.JMenuItem;

import com.silikonm.commons.PluginMenuItem;

public class SupplierMenuItem extends JMenuItem implements PluginMenuItem {

	private String menuItemName = null;

	public SupplierMenuItem(String menuItemText, String actionCommand) {
		this.menuItemName = menuItemText;
		setActionCommand(actionCommand);
		setText(menuItemName);
	}

	public int getMenuItemIndex() {		
		return 5;
	}

	public String getParentMenuKey() {
		return "FILE";
	}

	public String getMenuItemName() {
		return this.menuItemName;
	}

	public String getMenuItemKey() {
		return "POS_SUPPLIER";
	}

	public JMenuItem getMenuItem() {
		return this;
	}

}
