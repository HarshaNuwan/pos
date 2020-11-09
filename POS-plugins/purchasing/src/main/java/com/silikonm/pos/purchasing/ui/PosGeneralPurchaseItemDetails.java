package com.silikonm.pos.purchasing.ui;


import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.math.BigDecimal;

import net.miginfocom.swing.MigLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.silikonm.commons.JTextNumericFilter;
import com.silikonm.pos.purchasing.extended.PosPurchaseOrderItemDetailsTableModel;;

public class PosGeneralPurchaseItemDetails extends JDialog {

	public static final String ADD_ITEM = "ADD_ITEM";
	public static final String CLOSE_GP_ITEM_DIALOG = "CLOSE_GP_ITEM_DIALOG";

	private final JPanel contentPanel = new JPanel();
	JButton btnAdd, btnCancel;
	private JLabel lblPosPurchase;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable tblItems;
	private JPanel panel_1;
	private JLabel lblUnitPrice;
	private JLabel lblQty;
	private JTextField txtQty;
	private JTextField txtUnitPrice;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JTextField txtSearch;

	/**
	 * Create the dialog.
	 */
	public PosGeneralPurchaseItemDetails() {
		setBounds(100, 100, 689, 485);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			lblPosPurchase = new JLabel(" POS - General Purchasing Item Details");
			lblPosPurchase.setOpaque(true);
			lblPosPurchase.setForeground(Color.WHITE);
			lblPosPurchase.setFont(new Font("Dialog", Font.BOLD, 24));
			lblPosPurchase.setBackground(new Color(0, 153, 0));
			contentPanel.add(lblPosPurchase, BorderLayout.NORTH);
		}
		{
			panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
			}
			{
				tblItems = new JTable();
				tblItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(tblItems);
			}
			{
				panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.SOUTH);
				panel_1.setLayout(new MigLayout("", "[grow][grow]", "[grow][][]"));
				{
					panel_2 = new JPanel();
					panel_2.setBackground(new Color(30, 144, 255));
					panel_1.add(panel_2, "cell 0 0 2 1,grow");
					panel_2.setLayout(new MigLayout("", "[46px][grow]", "[14px]"));
					{
						lblNewLabel = new JLabel("Search");
						lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
						panel_2.add(lblNewLabel, "cell 0 0,aligny center");
					}
					{
						txtSearch = new JTextField();
						txtSearch.setFont(new Font("Dialog", Font.PLAIN, 14));
						panel_2.add(txtSearch, "cell 1 0,grow");
						txtSearch.setColumns(10);
					}
				}
				{
					lblQty = new JLabel("Qty.");
					panel_1.add(lblQty, "cell 0 1");
				}
				{
					txtQty = new JTextField();
					txtQty.setDocument(new JTextNumericFilter("1234567890."));
					panel_1.add(txtQty, "cell 1 1,growx,aligny top");
					txtQty.setColumns(10);
				}
				{
					lblUnitPrice = new JLabel("Unit Price");
					panel_1.add(lblUnitPrice, "cell 0 2");
				}
				{
					txtUnitPrice = new JTextField();
					txtUnitPrice.setDocument(new JTextNumericFilter(
							"1234567890."));
					panel_1.add(txtUnitPrice, "cell 1 2,growx");
					txtUnitPrice.setColumns(10);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAdd = new JButton("Add");
				btnAdd.setActionCommand(ADD_ITEM);
				buttonPane.add(btnAdd);
				getRootPane().setDefaultButton(btnAdd);
			}
			{
				btnCancel = new JButton("Close");
				btnCancel.setActionCommand(CLOSE_GP_ITEM_DIALOG);
				buttonPane.add(btnCancel);
			}
		}
	}

	public void setAvailableItemsTableModel(
			PosPurchaseOrderItemDetailsTableModel model) {
		tblItems.setModel(model);
		
	}

	public void addAddButtonActionListener(ActionListener al) {
		btnAdd.addActionListener(al);
	}

	public void addCancelButtonActionListener(ActionListener al) {
		btnCancel.addActionListener(al);
	}
	
	public void addSearchTextBoxKeyListener(KeyListener kl){
		txtSearch.addKeyListener(kl);
	}

	//
	public void setItemsTableRowSorter(TableRowSorter sorter){
		tblItems.setRowSorter(sorter);
	}
	
	public String getSearchKey(){
		if(txtSearch.getText().isEmpty()){
			return null;
		}
		return txtSearch.getText();
	}
	
	public BigDecimal getUnitPrice() {
		if (txtUnitPrice.getText().isEmpty()) {
			return BigDecimal.ZERO;
		}

		if (txtUnitPrice.getText().equals("0")) {
			return BigDecimal.ZERO;
		}
		return new BigDecimal(txtUnitPrice.getText());
	}

	public BigDecimal getQty() {
		if (txtQty.getText().isEmpty()) {
			return BigDecimal.ZERO;
		}
		if (txtQty.getText().equals("0")) {
			return BigDecimal.ZERO;
		}
		return new BigDecimal(txtQty.getText());
	}

	public int getSelectedTableRowIndex() {
		if (tblItems.getSelectedColumnCount() == 0) {
			return -1;
		}
		return tblItems.convertRowIndexToModel(tblItems.getSelectedRow());
	}

	public void setUniPirce(BigDecimal unitPrice) {
		txtUnitPrice.setText(unitPrice.toString());
	}

	public void setQty(BigDecimal qty) {
		txtQty.setText(qty.toString());
	}
	
	public TableRowSorter<TableModel> getItemsTableRowSorter(){
		return (TableRowSorter<TableModel>) tblItems.getRowSorter();
	}

	@Override
	public void dispose() {
		txtQty.setText("");
		txtUnitPrice.setText("");
		super.dispose();
	}

}
