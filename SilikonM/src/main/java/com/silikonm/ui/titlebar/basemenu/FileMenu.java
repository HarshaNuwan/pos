package com.silikonm.ui.titlebar.basemenu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.silikonm.commons.PluginMenu;

public class FileMenu extends JMenu implements PluginMenu {

	private String menuKey = "FILE";
	private String menuName = "File";
	
	public FileMenu() {
		setText(menuName);		
	}
	
	public int getMenuIndex() {
		return 0;
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
