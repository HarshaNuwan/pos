package com.silikonm.pos.itemtype.ui;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.silikonm.commons.UIType;
import com.silikonm.pos.itemtype.extended.PosItemCategoriesTableModel;

import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.JButton;

public class PosItemCategoriesView extends JPanel implements UIType {
	private JTable tblItemCategories;
	private ActionListener newAction;
	private JMenuItem mntmDelete;
	private JButton btnNew;

	/**
	 * Create the panel.
	 */
	public PosItemCategoriesView() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
				JPanel panel = new JPanel();
				panel_1.add(panel, BorderLayout.CENTER);
				panel.setLayout(new BorderLayout(0, 0));
				
						JScrollPane scrollPane = new JScrollPane();
						panel.add(scrollPane, BorderLayout.CENTER);
						
								JPopupMenu popupMenu = new JPopupMenu();
								addPopup(scrollPane, popupMenu);
								
										mntmDelete = new JMenuItem("Delete");
										mntmDelete.setActionCommand("DELETE_ITEM_CATEGORY");
										mntmDelete
												.setIcon(new ImageIcon(
														PosItemCategoriesView.class
																.getResource("\\com\\silikonm\\pos\\itemtype\\resources\\table_2_delete.png")));
										popupMenu.add(mntmDelete);
										
												tblItemCategories = new JTable();
												tblItemCategories.setComponentPopupMenu(popupMenu);
												tblItemCategories.setName("ITEM_CATEGORIES_TABLE");
												scrollPane.setViewportView(tblItemCategories);
												
														JLabel lblPosItem = new JLabel(" Item Categories");
														panel.add(lblPosItem, BorderLayout.NORTH);
														lblPosItem.setOpaque(true);
														lblPosItem.setForeground(Color.WHITE);
														lblPosItem.setFont(new Font("Dialog", Font.BOLD, 24));
														lblPosItem.setBackground(new Color(11, 114, 162));
														
														JToolBar toolBar = new JToolBar();
														toolBar.setFloatable(false);
														panel_1.add(toolBar, BorderLayout.NORTH);
														
														btnNew = new JButton("");
														btnNew.setToolTipText("New");
														btnNew.setActionCommand("TOOLBAR_NEW_ACTION");
														btnNew.setIcon(new ImageIcon(
																PosItemCategoriesView.class
																		.getResource("/com/silikonm/pos/itemtype/resources/Text-Document.png")));
														toolBar.add(btnNew);

	}
	

	public void setItemCategoriesTableModel(
			PosItemCategoriesTableModel categoriesTableModel) {
		tblItemCategories.setModel(categoriesTableModel);

	}

	public void setNewAction(ActionListener al) {
		this.btnNew.addActionListener(al);

	}

	public void addDeleteMenuItemActionListener(ActionListener al) {
		mntmDelete.addActionListener(al);
	}

	public void setItemCategoryTableMouseListener(
			PosItemCategoriesTableModel categoriesTableModel) {
		tblItemCategories.addMouseListener(categoriesTableModel);

	}
	
	public int getSelectedTableRowIndex(){
		if(tblItemCategories.getSelectedRowCount()==1){
			return tblItemCategories.getSelectedRow();
		}
		return -1;
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	public JPanel getPanel() {
		return this;
	}

	public JDialog getDialog() {
		// TODO Auto-generated method stub
		return null;
	}

	public JFrame getFrame() {
		// TODO Auto-generated method stub
		return null;
	}
}
