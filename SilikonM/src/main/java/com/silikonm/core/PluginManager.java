package com.silikonm.core;

import java.util.ArrayList;

import com.silikonm.commons.PluginBase;

public class PluginManager {

	private static PluginManager pluginManager = null;
	private ArrayList<PluginBase> plugins = null;

	private PluginManager() {
		plugins = new ArrayList<PluginBase>();
	}

	public static PluginManager getInstance() {
		if (pluginManager == null) {
			pluginManager = new PluginManager();
		}
		return pluginManager;
	}

	public void addPlugin(PluginBase plugin) {
		this.plugins.add(plugin);
	}

	public PluginBase getPlugin(String pluginKey) {		
		for (PluginBase pluginBase : this.plugins) {
			if (pluginBase.getPluginKey().equals(pluginKey)) {
				return pluginBase;
			}
		}

		return null;
	}
	
	public ArrayList<PluginBase> getPlugins() {
		return this.plugins;
	}

}
