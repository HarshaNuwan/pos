package com.silikonm.pos.billing.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class StockBrowser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSearchBox;
	private JTable tblStock;

	/**
	 * Create the dialog.
	 */
	public StockBrowser() {
		setModal(true);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new MigLayout("", "[46px][86px,grow]", "[20px]"));
			{
				JLabel lblNewLabel = new JLabel("Search");
				panel.add(lblNewLabel, "cell 0 0,alignx left,aligny center");
			}
			{
				txtSearchBox = new JTextField();
				panel.add(txtSearchBox, "cell 1 0,growx,aligny top");
				txtSearchBox.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					tblStock = new JTable();
					scrollPane.setViewportView(tblStock);
				}
			}
		}
		
		/*
		 * Create escape key action
		 */
		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, "ESCAPE");
		getRootPane().getActionMap().put("ESCAPE", new AbstractAction() {			
			public void actionPerformed(ActionEvent e) {				
				
				if(txtSearchBox.isFocusOwner()){
					dispose();
				}
				
			}
		});
	}

}
