package com.silikonm.pos.purchasing;

import javax.swing.JMenuItem;

import com.silikonm.commons.PluginMenuItem;

public class ItemPurchasingMenuItem extends JMenuItem implements PluginMenuItem{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1342880318500931166L;
	private String menuItemName = null;
	
	public ItemPurchasingMenuItem(String menuItemText, String actionCommand) {
		this.menuItemName = menuItemText;
		setActionCommand(actionCommand);
		setText(menuItemName);
	}

	public int getMenuItemIndex() {
		// TODO Auto-generated method stub
		return 10;
	}

	public String getParentMenuKey() {
		return "FILE";
	}

	public String getMenuItemName() {
		return this.menuItemName;
	}

	public String getMenuItemKey() {
		return "POS_ITEM_PURCHASING";
	}

	public JMenuItem getMenuItem() {
		return this;
	}

}
