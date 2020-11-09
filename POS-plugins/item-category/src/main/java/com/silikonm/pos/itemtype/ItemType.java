package com.silikonm.pos.itemtype;

import com.silikonm.commons.PluginBase;
import com.silikonm.commons.PluginConnector;
import com.silikonm.commons.PluginMenu;
import com.silikonm.commons.PluginMenuItem;
import com.silikonm.commons.UIType;
import com.silikonm.pos.itemtype.controller.PosItemCategoriesController;
import com.silikonm.pos.itemtype.ui.PosItemCategoriesView;
import com.silikonm.pos.itemtype.model.PositemCategoriesModel;

public class ItemType implements PluginConnector, PluginBase {

	private String pluginKey = "POS_ITEM_CATEGORY_REGISTER";
	private ItemTypeMenuItem typeMenuItem = null;
	private String pluginName = null;

	public ItemType() {
		this.pluginName = "Item Category";
		typeMenuItem = new ItemTypeMenuItem("Item Category", pluginKey);
	}

	public String getPluginKey() {
		return this.pluginKey;
	}

	public PluginMenu getPluginMenu() {
		return null;
	}

	public PluginMenuItem getPluginMenuItem() {
		return typeMenuItem;
	}

	public PluginMenuItem[] getPluginMenuItems() {
		// TODO Auto-generated method stub
		return null;
	}

	public UIType getUIType() {

		PosItemCategoriesView categoriesView = new PosItemCategoriesView();
		PositemCategoriesModel model = new PositemCategoriesModel(categoriesView);
		PosItemCategoriesController controller = new PosItemCategoriesController(categoriesView, model);
		return categoriesView;
	}

	public Boolean isActionPlugin() {
		return false;
	}

	public void executeAction() {
		// TODO Auto-generated method stub
	}

	public String getPluginName() {
		return this.pluginName;
	}

	public PluginBase getPlugin() {
		return this;
	}

}
