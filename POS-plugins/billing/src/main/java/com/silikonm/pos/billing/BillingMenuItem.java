package com.silikonm.pos.billing;

import javax.swing.JMenuItem;

import com.silikonm.commons.PluginMenuItem;

public class BillingMenuItem extends JMenuItem implements PluginMenuItem{

	private String menuItemName = null;
	
	public BillingMenuItem(String menuItemText, String actionCommand) {
		this.menuItemName = menuItemText;
		setActionCommand(actionCommand);
		setText(menuItemName);
	}
	
	public int getMenuItemIndex() {
		// TODO Auto-generated method stub
		return 6;
	}

	public String getParentMenuKey() {
		return "FILE";
	}

	public String getMenuItemName() {
		return this.menuItemName;
	}

	public String getMenuItemKey() {
		return "POS_BILLING";
	}

	public JMenuItem getMenuItem() {
		return this;
	}

}
