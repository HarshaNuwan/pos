package com.silikonm.pos.item;

import javax.swing.JMenuItem;

import com.silikonm.commons.PluginMenuItem;

public class ItemMenuItem extends JMenuItem implements PluginMenuItem{
	
	private String menuItemName = null;
	
	public ItemMenuItem(String menuItemText, String actionCommand) {
		this.menuItemName = menuItemText;
		setActionCommand(actionCommand);
		setText(menuItemName);		
	}

	public int getMenuItemIndex() {
		// TODO Auto-generated method stub
		return 7;
	}

	public String getParentMenuKey() {
		return "FILE";
	}

	public String getMenuItemName() {
		return this.menuItemName;
	}

	public String getMenuItemKey() {
		return "POS_ITEM_REGISTER";
	}

	public JMenuItem getMenuItem() {
		return this;
	}

}
