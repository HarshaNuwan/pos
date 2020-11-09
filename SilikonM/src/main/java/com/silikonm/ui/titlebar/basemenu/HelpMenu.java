package com.silikonm.ui.titlebar.basemenu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.silikonm.commons.PluginMenu;

public class HelpMenu extends JMenu implements PluginMenu {

	private String menuKey = "HELP";
	private String menuName = "Help";
	
	public HelpMenu() {
		setText(menuName);
		setMnemonic('H');
	}

	public int getMenuIndex() {
		return 20;
	}

	public String getParentMenuName() {
		return null;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public String getMenuKey() {
		return this.menuKey;
	}
	
	public void addMenuItem(JMenuItem item) {		
		add(item);
	}


}
