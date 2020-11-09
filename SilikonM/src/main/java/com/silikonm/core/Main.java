package com.silikonm.core;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.silikonm.ui.Login;

public class Main {
	public static void main(String[] args) {
		
		
		try {
			/*
			 * Initializing and setting the core system theme
			 */

			// SilikonMSkin mSkin = new SilikonMSkin();
			// UIManager.setLookAndFeel(mSkin.getLookAndFeel());
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			//throw new Exception("Theme not loaded!");
		} catch (Exception e) {
			try {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		try {
			
			
			Login login = new Login();
			login.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}