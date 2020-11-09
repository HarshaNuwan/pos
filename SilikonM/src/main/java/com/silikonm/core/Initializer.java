package com.silikonm.core;

import java.awt.EventQueue;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import com.silikonm.commons.PluginBase;
import com.silikonm.commons.PluginConnector;
import com.silikonm.commons.PluginMenu;
import com.silikonm.commons.PluginMenuItem;
import com.silikonm.ui.MainFrame;
import com.silikonm.ui.Splash;
import com.silikonm.ui.titlebar.basemenu.FileMenu;
import com.silikonm.ui.titlebar.basemenu.HelpMenu;

/**
 * 
 * @author Harsha Hulangamuwa
 * @version 1.0.0v
 * @Date 19-03-2015
 * 
 *       Initializer Initializer responsible for loading all the available plugins and initializing the platform. All the core menu system and menu
 *       items related to each plugin are loaded here.
 */

public class Initializer {

	// Map for hold each available menu
	private Map<Integer, PluginMenu> menus;
	// Map for hold each menu item
	private Map<Integer, PluginMenuItem> menuItems;
	// declaring the MenuAction
	private MenuAction menuAction = null;
	// declaring the splash Screen
	private Splash splash = null;

	public Initializer() throws URISyntaxException {

		// set all the frames default look and feel
		// JFrame.setDefaultLookAndFeelDecorated(true);
		// set all the dialogs default look and feel
		// JDialog.setDefaultLookAndFeelDecorated(true);

		this.splash = new Splash();
		this.splash.setVisible(true);

		// initializing the MenuAction instance, ArrayLists and Maps
		this.menuAction = new MenuAction();
		this.menus = new HashMap<Integer, PluginMenu>();
		this.menuItems = new HashMap<Integer, PluginMenuItem>();

		/*
		 * Reading the base system path using the CodeBase
		 */
		CodeSource codeSource = Initializer.class.getProtectionDomain().getCodeSource();
		File jarFile = new File(codeSource.getLocation().toURI().getPath());
		final String jarDir = jarFile.getParentFile().getPath();

		File pluginsDir = new File("plugins");
		final File[] plugins = pluginsDir.listFiles();// reading the list of available
		// plugins

		new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub

				// reading each plugin
				splash.setMaxProgressSize(plugins.length);
				for (File plg : plugins) {
					File jar = new File(jarDir + "/plugins/" + plg.getName());
					 //System.out.println(jar);

					try {
						splash.setProgress(splash.getProgress() + 1);
						splash.setStatusMessage("Loading Plugin - " + plg.getName());

						URLClassLoader child = new URLClassLoader(new URL[] { jar.toURI().toURL() }, this.getClass().getClassLoader());

						// reading the MANIFEST of each available plugin
						URL url = child.findResource("META-INF/MANIFEST.MF");
						Manifest manifest = new Manifest(url.openStream());

						Attributes attributes = manifest.getMainAttributes();
						// get the main class from the MANIFEST attributes and
						// loading the plugin
						Class classToLoad = Class.forName(attributes.getValue("Main-Class"), true, child);
						PluginConnector connector = (PluginConnector) classToLoad.newInstance();

						// add the plugin to the plugin manager
						PluginManager.getInstance().addPlugin(connector.getPlugin());
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				// setup main menu
				setupMainMenu();

				setupMainMenuItems();

				/*
				 * Create the menu for available plugins.
				 */
				for (PluginBase plugin : PluginManager.getInstance().getPlugins()) {
					PluginMenu menuType = plugin.getPluginMenu();

					if (menuType != null) {
						// each and every plugin may not have its' own main
						// menu. Here checking whether the plugin has a main
						// menu and if available
						// adding the main menu into the menus MAP.
						if (menuType.getParentMenuName() == null) {
							menus.put(menuType.getMenuIndex(), menuType);
						}
					}
				}

				/*
				 * Create the menu items for available plugins
				 */
				for (PluginBase plugin : PluginManager.getInstance().getPlugins()) {
					PluginMenuItem menuItem = plugin.getPluginMenuItem();
					//System.out.println(plugin.getPluginKey() + " : " + menuItem);
					if (menuItem != null) {
						// each and every plugin may not have its' own menu
						// item. Check whether the plugin has a menu item and if
						// available
						// adding the menu item into the menu items MAPs.
						// System.out.println(menuItem.getMenuItemName());
						// set the plugin key as the menu item action command
						menuItem.getMenuItem().setActionCommand(plugin.getPluginKey());
						// adding the action listener for the menu item
						menuItem.getMenuItem().addActionListener(menuAction);
						// putting the menu item into the menu items MAP with
						// the menu item index as the key.
						
						menuItems.put(menuItem.getMenuItemIndex(), menuItem);
					}
				}

				// ******************************
				// iterate the menu items map for registering menu items into
				// the applicable menus
				for (int key : menuItems.keySet()) {
					//
					PluginMenu menu = getMenuByName(menuItems.get(key).getParentMenuKey());
					menu.addMenuItem(menuItems.get(key).getMenuItem());
				}

				// adding menus into the main user interface
				for (int key : menus.keySet()) {
					MainFrame.getInstance().addMenu(menus.get(key));
				}

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							// starting the system
							MainFrame frame = MainFrame.getInstance();
							frame.setVisible(true);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				splash.dispose();

			}
		}).start();

	}

	private void setupMainMenu() {
		// File menu
		FileMenu fileMenu = new FileMenu();
		HelpMenu help = new HelpMenu();
		menus.put(fileMenu.getMenuIndex(), fileMenu);
		menus.put(help.getMenuIndex(), help);
	}

	private void setupMainMenuItems() {
		// ExitMenuItem exit = new ExitMenuItem("EXIT");
		// menuItems.put(exit.getMenuItemIndex(), exit);
	}

	/**
	 * 
	 * @param menuKey
	 * @return This method returns the applicable plugin menu form the menus list. Menu key is use to search the plugin menu.
	 */
	private PluginMenu getMenuByName(String menuKey) {
		for (int key : menus.keySet()) {
			PluginMenu tmp = (PluginMenu) menus.get(key);
			if (tmp.getMenuKey().toUpperCase().equals(menuKey.toUpperCase())) {
				return tmp;
			}
		}
		return null;
	}

}
