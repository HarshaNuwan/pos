package com.silikonm.pos.purchasing.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
import com.silikonm.pos.purchasing.extended.PosGeneralPurchasingsTableModel;
import javax.swing.JToolBar;
import javax.swing.JButton;

public class PosGeneralPurchasingPanelView extends JPanel implements UIType {
	public static final String DELETE_GENERAL_PURCHASING = "DELETE_PURCHASE_ORDER";
	public static final String PURCHASE_ORDERS_TABLE = "PURCHASE_ORDERS_TABLE";

	private JTable tblPurchaseOrders;
	private ActionListener newAction;
	private JMenuItem mntmDelete;
	private JButton btnNew;

	/**
	 * Create the panel.
	 */
	public PosGeneralPurchasingPanelView() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel lblPosPurchase = new JLabel(" POS - General Purchasings");
		panel_2.add(lblPosPurchase, BorderLayout.NORTH);
		lblPosPurchase.setOpaque(true);
		lblPosPurchase.setForeground(Color.WHITE);
		lblPosPurchase.setFont(new Font("Dialog", Font.BOLD, 24));
		lblPosPurchase.setBackground(new Color(0, 153, 0));

		JPanel panel = new JPanel();
		panel_2.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		JPopupMenu popupMenu = new JPopupMenu();

		mntmDelete = new JMenuItem("Delete");
		mntmDelete.setActionCommand(DELETE_GENERAL_PURCHASING);
		//mntmDelete.setIcon(new ImageIcon(PosGeneralPurchasingPanelView.class.getResource("\\com\\silikonm\\pos\\purchasing\\resources\\table_2_delete.png")));
		popupMenu.add(mntmDelete);

		tblPurchaseOrders = new JTable();
		tblPurchaseOrders.setName(PURCHASE_ORDERS_TABLE);

		addPopup(tblPurchaseOrders, popupMenu);
		scrollPane.setViewportView(tblPurchaseOrders);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel_1.add(toolBar, BorderLayout.NORTH);
		
		btnNew = new JButton("New");
		btnNew.setToolTipText("New");
		btnNew.setActionCommand("TOOLBAR_NEW_ACTION");
		toolBar.add(btnNew);

	}

	public void setPurchaseOrdersTableModel(PosGeneralPurchasingsTableModel model) {
		tblPurchaseOrders.setModel(model);
	}

	public void addDeleteMenuItemActionListener(ActionListener al) {
		mntmDelete.addActionListener(al);
	}

	public void addPurchaseOrdersTableMouseListener(MouseListener ml) {
		tblPurchaseOrders.addMouseListener(ml);
	}

	//
	public void setNewAction(ActionListener al) {
		System.out.println("PosGeneralPurchasingPanelView.setNewAction() " + al);
		this.btnNew.addActionListener(al);

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
		if (tblPurchaseOrders.getSelectedRowCount() == 0) {
			return -1;
		}
		return tblPurchaseOrders.getSelectedRow();
	}

	@Override
	public JPanel getPanel() {
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
