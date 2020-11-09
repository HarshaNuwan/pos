package com.silikonm.pos.billing.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.miginfocom.swing.MigLayout;

import com.silikonm.common.dto.pos.ItemBean;
import com.silikonm.pos.billing.extend.ItemBrowserTableModel;
import com.silikonm.skin.component.table.CustomTableRenderer;

public class ItemBrowser extends JDialog {
	public static final String SEARCH_KEY = "SEARCH_KEY";
	//
	private JTable table;
	private JTextField txtSearchItem;

	/**
	 * Create the panel.
	 */
	public ItemBrowser() {
		getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][grow]"));

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 20));
		getContentPane().add(lblSearch, "cell 0 0,alignx trailing");

		txtSearchItem = new JTextField();
		txtSearchItem.setActionCommand(SEARCH_KEY);
		txtSearchItem.setFont(new Font("Dialog", Font.BOLD, 20));
		getContentPane().add(txtSearchItem, "cell 1 0,growx");
		txtSearchItem.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 0 2 2 1,grow");

		table = new JTable();
		table.setDefaultRenderer(Object.class, new CustomTableRenderer(
				new Color(237, 255, 202), Color.white));
		InputMap im = table
				.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "none");	
		scrollPane.setViewportView(table);

	}

	public void setItemsTableRowSorter(TableRowSorter<TableModel> rowSorter) {
		table.setRowSorter(rowSorter);

	}

	public String getSearchKey() {
		if (txtSearchItem.getText().isEmpty()) {
			return null;
		}

		return txtSearchItem.getText();
	}
	
	public void setItemsTableModel(ItemBrowserTableModel itemBrowserTableModel){
		table.setModel(itemBrowserTableModel);
	}

	public void setItemsTableFocussed() {
		table.requestFocus();
		if (table.getRowCount() > 0) {
			table.setRowSelectionInterval(0, 0);
		}

	}

	public void setSearchBoxFocussed() {
		txtSearchItem.requestFocus();
	}

	public int getSelectedItemTableRowIndex() {		
		return table.convertRowIndexToModel(table.getSelectedRow());
	}

	public ItemBean getSelectedItem() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		return (ItemBean) tableModel.getValueAt(table.getSelectedRow(), 1);
	}

	public void addEnterKeyAction(Action action, String key) {
		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks,
				key);
		getRootPane().getActionMap().put("ENTER", action);
	}

	public void addEscapeKeyAction(Action action, String key) {
		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks,
				key);
		getRootPane().getActionMap().put("ESCAPE", action);
	}

	public void addSearchBoxKeyListener(KeyListener kl) {
		txtSearchItem.addKeyListener(kl);
	}

}
