package com.silikonm.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.silikonm.commons.PluginBase;
import com.silikonm.ui.MainFrame;

public class MenuAction implements ActionListener {

	public void actionPerformed(ActionEvent e) {

		PluginBase pluginBase = PluginManager.getInstance().getPlugin(
				e.getActionCommand());

		if (pluginBase != null) {
			System.out.println("Plugin found : " + pluginBase.getPluginKey());

			if (pluginBase.getUIType() != null) {
				if (pluginBase.getUIType().getPanel() != null) {
					MainFrame.getInstance().registerPanel(
							pluginBase.getUIType().getPanel(),
							pluginBase.getPluginName());
				}

				if (pluginBase.getUIType().getDialog() != null) {
					pluginBase.getUIType().getDialog().setModal(true);
					pluginBase.getUIType().getDialog().setVisible(true);
				}
			}

			if (pluginBase.isActionPlugin()) {
				pluginBase.executeAction();
			}
			
			if(pluginBase.getUIType().getFrame() != null){				
				MainFrame.getInstance().registerFrame(pluginBase.getUIType().getFrame());
				//pluginBase.getUIType().getFrame().setVisible(true);
			}
		}
	}
}
