package com.silikonm.pos.supplier.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.silikonm.commons.UIType;
import com.silikonm.pos.supplier.tablemodel.PosSuppliersTableModel;
import javax.swing.JToolBar;
import javax.swing.JButton;

public class POSSuppliersView extends JPanel implements UIType{
	private JTable tblPOSSuppliers;
	private ActionListener newAction;
	private JMenuItem mntmDelete;
	private JButton btnNew;

	/**
	 * Create the panel.
	 */
	public POSSuppliersView() {
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
										mntmDelete.setActionCommand("DELETE_SUPPLIER");
										mntmDelete.setIcon(new ImageIcon(POSSuppliersView.class.getResource("/com/silikonm/pos/supplier/resource/table_2_delete.png")));
										popupMenu.add(mntmDelete);
										
												tblPOSSuppliers = new JTable();
												tblPOSSuppliers.setComponentPopupMenu(popupMenu);
												tblPOSSuppliers.setName("SUPPLIERS_TABLE");
												scrollPane.setViewportView(tblPOSSuppliers);
												
														JLabel lblPosSuppliers = new JLabel(" POS Suppliers");
														panel.add(lblPosSuppliers, BorderLayout.NORTH);
														lblPosSuppliers.setOpaque(true);
														lblPosSuppliers.setForeground(Color.WHITE);
														lblPosSuppliers.setFont(new Font("Dialog", Font.BOLD, 24));
														lblPosSuppliers.setBackground(new Color(11, 114, 162));
														
														JToolBar toolBar = new JToolBar();
														toolBar.setFloatable(false);
														panel_1.add(toolBar, BorderLayout.NORTH);
														
														btnNew = new JButton("");
														btnNew.setToolTipText("New");
														btnNew.setActionCommand("TOOLBAR_NEW_ACTION");
														btnNew.setIcon(new ImageIcon(
																POSSuppliersView.class
																		.getResource("/com/silikonm/pos/supplier/resource/Text-Document.png")));
														toolBar.add(btnNew);

	}

	public void setSuppliersTableModel(PosSuppliersTableModel model) {
		tblPOSSuppliers.setModel(model);

	}

	public void setNewAction(ActionListener newAction) {
		this.btnNew.addActionListener(newAction);
	}

	public void setSupplierTableMouseAction(PosSuppliersTableModel posSuppliersTableModel) {
		tblPOSSuppliers.addMouseListener(posSuppliersTableModel);

	}

	public void setDeletePopUpMenuItemActionListener(ActionListener al) {
		mntmDelete.addActionListener(al);
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

	public int getSelectedTableRowIndex() {
		if (tblPOSSuppliers.getSelectedRowCount() == 1) {
			return tblPOSSuppliers.getSelectedRow();
		} else {
			return -1;
		}
	}

	@Override
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public JDialog getDialog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JFrame getFrame() {
		// TODO Auto-generated method stub
		return null;
	}
}
