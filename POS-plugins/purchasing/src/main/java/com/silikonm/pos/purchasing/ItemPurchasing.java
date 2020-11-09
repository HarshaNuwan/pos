package com.silikonm.pos.purchasing;

import com.silikonm.commons.PluginBase;
import com.silikonm.commons.PluginConnector;
import com.silikonm.commons.PluginMenu;
import com.silikonm.commons.PluginMenuItem;
import com.silikonm.commons.UIType;
import com.silikonm.pos.purchasing.controller.PosGeneralPurchaseController;
import com.silikonm.pos.purchasing.model.PosGeneralPurchaseModel;
import com.silikonm.pos.purchasing.ui.PosGeneralPurchasingPanelView;

public class ItemPurchasing implements PluginConnector, PluginBase{
	
	private String pluginKey = "POS_ITEM_PURCHASING";
	private ItemPurchasingMenuItem purchasingMenuItem   = null;
	private String pluginName = null;
	
	public ItemPurchasing() {
		this.pluginName = "Item Purchasing";
		purchasingMenuItem = new ItemPurchasingMenuItem("Item Purchasing", pluginKey);
	}

	public String getPluginKey() {
		return "POS_ITEM_PURCHASING";
	}

	public PluginMenu getPluginMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	public PluginMenuItem getPluginMenuItem() {
		return this.purchasingMenuItem;
	}

	public PluginMenuItem[] getPluginMenuItems() {
		// TODO Auto-generated method stub
		return null;
	}

	public UIType getUIType() {
		PosGeneralPurchasingPanelView view = new PosGeneralPurchasingPanelView();
		PosGeneralPurchaseModel model = new PosGeneralPurchaseModel();
		PosGeneralPurchaseController controller = new PosGeneralPurchaseController(view, model);
		
		return view;
	}

	public Boolean isActionPlugin() {
		// TODO Auto-generated method stub
		return null;
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
