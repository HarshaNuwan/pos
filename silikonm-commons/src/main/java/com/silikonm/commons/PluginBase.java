package com.silikonm.commons;

public interface PluginBase {

	public String getPluginKey();
	
	public PluginMenu getPluginMenu();
	
	public PluginMenuItem getPluginMenuItem();
	
	public PluginMenuItem[] getPluginMenuItems();
	
	public UIType getUIType();
	
	public Boolean isActionPlugin();
	
	public void executeAction();
	
	public String getPluginName();
	
}
