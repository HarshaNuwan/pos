package com.silikonm.commons;

import javax.swing.JMenuItem;

public interface PluginMenu {

	public int getMenuIndex();

	public String getParentMenuName();

	public String getMenuName();
	
	public String getMenuKey();
	
	public void addMenuItem(JMenuItem item);
	
}
