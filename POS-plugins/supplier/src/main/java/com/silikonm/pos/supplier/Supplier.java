package com.silikonm.pos.supplier;

import com.silikonm.commons.PluginBase;
import com.silikonm.commons.PluginConnector;
import com.silikonm.commons.PluginMenu;
import com.silikonm.commons.PluginMenuItem;
import com.silikonm.commons.UIType;
import com.silikonm.pos.supplier.controller.POSSupplierController;
import com.silikonm.pos.supplier.model.POSSupplierModel;
import com.silikonm.pos.supplier.ui.POSSuppliersView;

public class Supplier implements PluginConnector, PluginBase {
	
	private String pluginKey = "POS_SUPPLIER";
	private SupplierMenuItem supplierMenuItem = null;
	private String pluginName = null;
	
	public Supplier() {
		this.pluginName = "Supplier Management";
		supplierMenuItem = new SupplierMenuItem("Supplier Management", pluginKey);
	}

	public String getPluginKey() {
		return this.pluginKey;
	}

	public PluginMenu getPluginMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	public PluginMenuItem getPluginMenuItem() {		
		return supplierMenuItem;
	}

	public PluginMenuItem[] getPluginMenuItems() {
		// TODO Auto-generated method stub
		return null;
	}

	public UIType getUIType() {
		POSSuppliersView view = new POSSuppliersView();
		POSSupplierModel model = new POSSupplierModel(view);
		POSSupplierController controller = new POSSupplierController(view, model);
		return view;
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
