package com.silikonm.pos.billing.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

public class HelpDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 */
	public HelpDialog() {
		setBounds(100, 100, 450, 300);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][][]"));
		{
			JPanel panel = new JPanel();
			panel.setName("billingInfo");
			panel.setBorder(new LineBorder(new Color(128, 128, 128)));
			contentPanel.add(panel, "cell 0 0,grow");
			panel.setLayout(new MigLayout("", "[]", "[][][]"));
			{
				JLabel label = new JLabel("(F1) Cash Payment");
				panel.add(label, "cell 0 0");
			}
			{
				JLabel label = new JLabel("(F2) Credit Card Payment");
				panel.add(label, "cell 0 1");
			}
			{
				JLabel label = new JLabel("(F3) Credit");
				panel.add(label, "cell 0 2");
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			contentPanel.add(panel, "cell 0 1,grow");
			panel.setLayout(new MigLayout("", "[]", "[]"));
			{
				JLabel lblfSearchStock = new JLabel("(F4) Search Stock");
				panel.add(lblfSearchStock, "cell 0 0");
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			contentPanel.add(panel, "cell 0 2,grow");
			panel.setLayout(new MigLayout("", "[]", "[][]"));
			{
				JLabel label = new JLabel("(F5) Print the Bill");
				panel.add(label, "cell 0 0");
			}
			{
				JLabel label = new JLabel("(F6) Cancel the Bill");
				panel.add(label, "cell 0 1");
			}
		}
		
		/*
		 * Create escape key action
		 */
		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, "ESCAPE");
		getRootPane().getActionMap().put("ESCAPE", new AbstractAction() {			
			public void actionPerformed(ActionEvent e) {				
				dispose();
			}
		});
	}

}
