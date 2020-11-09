package com.silikonm.commons;

/**
 * UIType
 * 
 * @author Harsha Hulangamuwa
 * @Date 12/10/2014
 * 
 * UIType define the plugin UI type, such as JPanel, JDialog etc
 * 
 */

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public interface UIType {

	public JPanel getPanel();
	
	public JDialog getDialog();
	
	public JFrame getFrame();
	
}
