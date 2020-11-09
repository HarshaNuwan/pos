package com.silikonm.pos.item.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableRowSorter;

import net.miginfocom.swing.MigLayout;

import com.silikonm.commons.UIType;
import com.silikonm.pos.item.extended.PosItemsTableModel;
import javax.swing.JToolBar;
import javax.swing.JButton;

public class PosItemsPanelView extends JPanel implements UIType {
	private JTable tblItems;
	private ActionListener newAction;
	private JMenuItem mntmDelete;
	public static final String POS_ITEMS_TABLE = "POS_ITEMS_TABLE";
	private JTextField txtSearchItem;
	private JButton btnNew;

	/**
	 * Create the panel.
	 */
	public PosItemsPanelView() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_4.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_3.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel label = new JLabel(" POS - Items");
		panel_1.add(label, BorderLayout.NORTH);
		label.setOpaque(true);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Dialog", Font.BOLD, 24));
		label.setBackground(new Color(11, 114, 162));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 191, 255));
		panel_1.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new MigLayout("", "[][grow]", "[]"));

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		panel_2.add(lblSearch, "cell 0 0,alignx trailing");

		txtSearchItem = new JTextField();
		panel_2.add(txtSearchItem, "cell 1 0,growx,aligny top");
		txtSearchItem.setColumns(10);

		JPanel panel = new JPanel();
		panel_3.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);

		tblItems = new JTable();
		tblItems.setName(POS_ITEMS_TABLE);
		tblItems.getTableHeader().setReorderingAllowed(false);
		tblItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// tblItems.setDefaultRenderer(Object.class, new PosItemTableRenderer(2));

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(scrollPane, popupMenu);

		mntmDelete = new JMenuItem("Delete");
		mntmDelete.setActionCommand("DELETE_TABLE_ROW");
		mntmDelete.setIcon(new ImageIcon(PosItemsPanelView.class.getResource("/com/silikonm/pos/item/resources/table_2_delete.png")));
		popupMenu.add(mntmDelete);
		tblItems.setComponentPopupMenu(popupMenu);
		scrollPane.setViewportView(tblItems);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel_4.add(toolBar, BorderLayout.NORTH);

		btnNew = new JButton("");
		btnNew.setActionCommand("TOOLBAR_NEW_ACTION");
		btnNew.setToolTipText("New Item");
		btnNew.setIcon(new ImageIcon(PosItemsPanelView.class.getResource("/com/silikonm/pos/item/resources/Text-Document.png")));
		toolBar.add(btnNew);

	}

	public void setNewAction(ActionListener al) {
		this.btnNew.addActionListener(al);

	}

	public int getSelectedTableRowIndex() {
		if (tblItems.getSelectedRowCount() == 0) {
			return -1;
		} else {
			return tblItems.convertRowIndexToModel(tblItems.getSelectedRow());
		}
	}

	public void setSortKeys() {
		if (tblItems.getRowSorter() != null) {
			tblItems.getRowSorter().setSortKeys(null);
		}

	}

	public String getSearchKey() {
		if (txtSearchItem.getText().isEmpty()) {
			return null;
		}
		return txtSearchItem.getText();
	}

	public void setTableSorter(TableRowSorter sorter) {
		tblItems.setRowSorter(sorter);
	}

	//
	public void setPosItemsTableModel(PosItemsTableModel model) {
		tblItems.setModel(model);
	}

	//
	public void setToolBarNewActionListener(ActionListener al) {
		this.newAction = al;
	}

	public void addDeletePopUpMenuItemActionListener(ActionListener al) {
		mntmDelete.addActionListener(al);
	}

	public void addItemsTableMouseListener(MouseListener ml) {
		tblItems.addMouseListener(ml);
	}

	public void addSearchBoxKeyListener(KeyListener kl) {
		txtSearchItem.addKeyListener(kl);

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
