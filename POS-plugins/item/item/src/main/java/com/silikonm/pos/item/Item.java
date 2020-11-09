package com.silikonm.pos.item;

import com.silikonm.commons.PluginBase;
import com.silikonm.commons.PluginConnector;
import com.silikonm.commons.PluginMenu;
import com.silikonm.commons.PluginMenuItem;
import com.silikonm.commons.UIType;
import com.silikonm.pos.item.controller.PosItemController;
import com.silikonm.pos.item.model.PosItemModel;
import com.silikonm.pos.item.ui.PosItemsPanelView;

public class Item implements PluginConnector, PluginBase{

	private String pluginKey = "POS_ITEM_REGISTER";	
	private String pluginName = null;
	private ItemMenuItem itemMenuItem = null;
	
	public Item() {		
		this.pluginName = "Item Register";
		this.itemMenuItem = new ItemMenuItem("Item Register", pluginKey);

	}
	
	public String getPluginKey() {
		return this.pluginKey;
	}

	public PluginMenu getPluginMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	public PluginMenuItem getPluginMenuItem() {		
		return this.itemMenuItem;
	}

	public PluginMenuItem[] getPluginMenuItems() {
		// TODO Auto-generated method stub
		return null;
	}

	public UIType getUIType() {
		PosItemsPanelView view = new PosItemsPanelView();
		PosItemModel model = new PosItemModel(view);
		PosItemController controller = new PosItemController(view, model);
		
		return view;
	}

	public Boolean isActionPlugin() {
		// TODO Auto-generated method stub
		return false;
	}

	public void executeAction() {
		// TODO Auto-generated method stub
		
	}

	public String getPluginName() {
		// TODO Auto-generated method stub
		return this.pluginName;
	}

	public PluginBase getPlugin() {
		// TODO Auto-generated method stub
		return this;
	}

}
