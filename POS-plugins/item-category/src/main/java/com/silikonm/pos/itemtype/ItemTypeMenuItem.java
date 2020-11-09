package com.silikonm.pos.itemtype;

import javax.swing.JMenuItem;

import com.silikonm.commons.PluginMenuItem;

public class ItemTypeMenuItem extends JMenuItem implements PluginMenuItem {

	private String menuItemName = null;
	
	public ItemTypeMenuItem(String menuItemText, String actionCommand) {
		this.menuItemName = menuItemText;
		setActionCommand(actionCommand);
		setText(menuItemName);
	}

	public int getMenuItemIndex() {
		return 2;
	}

	public String getParentMenuKey() {
		return "FILE";
	}

	public String getMenuItemName() {
		return this.menuItemName;
	}

	public String getMenuItemKey() {
		return "POS_ITEM_CATEGORY_REGISTER";
	}

	public JMenuItem getMenuItem() {
		return this;
	}

}
