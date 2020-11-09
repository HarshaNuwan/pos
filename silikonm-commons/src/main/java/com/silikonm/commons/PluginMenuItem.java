package com.silikonm.commons;

import javax.swing.JMenuItem;


public interface PluginMenuItem {	

	public int getMenuItemIndex();

	public String getParentMenuKey();

	public String getMenuItemName();
	
	public String getMenuItemKey();
	
	public JMenuItem getMenuItem();
	
}
