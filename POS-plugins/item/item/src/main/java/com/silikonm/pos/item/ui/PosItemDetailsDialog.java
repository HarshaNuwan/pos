package com.silikonm.pos.item.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import com.silikonm.common.dto.pos.ItemCategoryBean;
import com.silikonm.common.dto.pos.PosSupplierBean;
//import com.silikonm.pos.item.extended.FontService;
import com.silikonm.pos.item.extended.JTextNumericFilter;

public class PosItemDetailsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton, cancelButton;
	private JLabel lblPosItem;
	private JPanel panel;
	private JLabel lblItemCode;
	private JLabel lblSupplier;
	private JLabel lblCatagory;
	private JLabel lblItemName;
	private JLabel lblItemDescriptiom;
	private JLabel lblReorderLevel;
	private JTextField txtItemCode;
	private JComboBox<Object> cmbSupplier;
	private JComboBox cmbCategory;
	private JTextField txtItemName;
	private JTextField txtReOrderLevel;
	private JTextArea txaItemDescription;
	private JScrollPane scrollPane;
	private JLabel lblItemUnit;
	private JTextField txtItemUnit;
	private JLabel lblBarcode;
	private JTextField txtBarCode;

	/**
	 * Create the dialog.
	 */
	public PosItemDetailsDialog() {
		setBounds(100, 100, 493, 408);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			lblPosItem = new JLabel(" POS - Item Details");
			lblPosItem.setOpaque(true);
			lblPosItem.setForeground(Color.WHITE);
			lblPosItem.setFont(new Font("Dialog", Font.BOLD, 24));
			lblPosItem.setBackground(new Color(9, 149, 214));
			contentPanel.add(lblPosItem, BorderLayout.NORTH);
		}
		{
			panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][grow]"));
			{
				lblItemCode = new JLabel("Item Code");
				panel.add(lblItemCode, "cell 0 0");
			}
			{
				txtItemCode = new JTextField();
				txtItemCode.setFont(new Font("Tahoma", Font.BOLD, 11));
				txtItemCode.setBackground(new Color(153, 255, 0));
				txtItemCode.setEditable(false);
				panel.add(txtItemCode, "cell 1 0,growx");
				txtItemCode.setColumns(10);
			}
			{
				lblBarcode = new JLabel("Barcode");
				panel.add(lblBarcode, "cell 0 1,aligny center");
			}
			{
				txtBarCode = new JTextField();
				txtBarCode.setFont(new Font("Dialog", Font.BOLD, 13));
				txtBarCode.setBackground(new Color(0, 191, 255));
				panel.add(txtBarCode, "cell 1 1,growx");
				txtBarCode.setColumns(10);
			}
			{
				lblSupplier = new JLabel("Supplier");
				panel.add(lblSupplier, "cell 0 2");
			}
			{
				cmbSupplier = new JComboBox();
				panel.add(cmbSupplier, "cell 1 2,growx");
			}
			{
				lblCatagory = new JLabel("Category");
				panel.add(lblCatagory, "cell 0 3");
			}
			{
				cmbCategory = new JComboBox();
				panel.add(cmbCategory, "cell 1 3,growx");
			}
			{
				lblItemName = new JLabel("Item Name");
				panel.add(lblItemName, "cell 0 4");
			}
			{
				txtItemName = new JTextField();
				panel.add(txtItemName, "cell 1 4,growx");
				txtItemName.setColumns(10);
			}
			{
				lblItemUnit = new JLabel("Item Unit");
				panel.add(lblItemUnit, "cell 0 5");
			}
			{
				txtItemUnit = new JTextField();
				panel.add(txtItemUnit, "cell 1 5,growx");
				txtItemUnit.setColumns(10);
			}
			{
				lblReorderLevel = new JLabel("Re-order level");
				panel.add(lblReorderLevel, "cell 0 6");
			}
			{
				txtReOrderLevel = new JTextField();
				txtReOrderLevel.setDocument(new JTextNumericFilter(
						"1234657890."));
				panel.add(txtReOrderLevel, "cell 1 6,growx");
				txtReOrderLevel.setColumns(10);
			}
			{
				lblItemDescriptiom = new JLabel("Item Description");
				panel.add(lblItemDescriptiom, "cell 0 7");
			}
			{
				scrollPane = new JScrollPane();
				panel.add(scrollPane, "cell 0 8 2 1,grow");
				{
					txaItemDescription = new JTextArea();
					scrollPane.setViewportView(txaItemDescription);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Save");
				okButton.setActionCommand("SAVE_ITEM");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("CLOSE_POS_ITEM_DIALOG");
				buttonPane.add(cancelButton);
			}
		}
	}

	// getters
	public String getBarCode() {
		if (txtBarCode.getText().isEmpty()) {
			return null;
		}
		return txtBarCode.getText();
	}

	public String getItemCode() {
		if (txtItemCode.getText().isEmpty()) {
			return null;
		}
		return txtItemCode.getText().trim();
	}

	public int getSupplierCode() {
		if (cmbSupplier.getSelectedIndex() == 0) {
			return -1;
		}
		return ((PosSupplierBean) cmbSupplier.getSelectedItem())
				.getSupplierID();
	}

	public int getCategoryCode() {

		if (cmbCategory.getSelectedIndex() == 0) {
			return -1;
		}

		return ((ItemCategoryBean) cmbCategory.getSelectedItem())
				.getCategoryID();
	}

	public String getItemName() {
		if (txtItemName.getText().isEmpty()) {
			return null;
		}
		return txtItemName.getText().trim();
	}

/*	public String getSinhalaName() {
		if (txtSinhalaName.getText().isEmpty()) {
			return null;
		}
		return txtSinhalaName.getText().trim();
	}*/

	public String getItemDescription() {
		if (txaItemDescription.getText().isEmpty()) {
			return null;
		}
		return txaItemDescription.getText().trim();
	}

	public double getReorderLevel() {
		if (txtReOrderLevel.getText().isEmpty()) {
			return 0f;
		}
		return Double.valueOf(txtReOrderLevel.getText());
	}

	public String getItemUnit() {
		if (txtItemUnit.getText().isEmpty()) {
			return null;
		}
		return txtItemUnit.getText();
	}

	// setters
	public void setBarCode(String barCode) {
		txtBarCode.setText(barCode);
	}

	public void setItemUnit(String unit) {
		txtItemUnit.setText(unit);
	}

	public void setItemCode(String itemCode) {
		txtItemCode.setText(itemCode);
	}

	public void setItemName(String itemName) {
		txtItemName.setText(itemName);
	}

	public void setReorderLevel(double rol) {
		txtReOrderLevel.setText(String.valueOf(rol));
	}

/*	public void setItemNameInSinhala(String sinhalaName) {
		txtSinhalaName.setText(sinhalaName);

	}*/

	public void setItemDescription(String desc) {
		txaItemDescription.setText(desc);

	}

	public void setSelectedItemCategory(int categoryID) {
		ItemCategoryBean bean = new ItemCategoryBean();
		bean.setCategoryID(categoryID);
		cmbCategory.setSelectedItem(bean);
	}

	public void setSelectedSupplier(int supplierID) {
		PosSupplierBean bean = new PosSupplierBean();
		bean.setSupplierID(supplierID);
		cmbSupplier.setSelectedItem(bean);
	}

	//
	public void addCancelButtonActionListener(ActionListener al) {
		cancelButton.addActionListener(al);
	}

	public void addSaveButtonActionListener(ActionListener al) {
		okButton.addActionListener(al);
	}

	//
	public void setSuppliersList(Object[] suppliers) {
		cmbSupplier.setModel(new DefaultComboBoxModel(suppliers));
	}

	public void setItemCategoriesList(Object[] itemCategories) {
		cmbCategory.setModel(new DefaultComboBoxModel(itemCategories));
	}

	//
	@Override
	public void dispose() {
		txtItemCode.setText("");
		txtItemName.setText("");
		txtReOrderLevel.setText("0.0");
		//txtSinhalaName.setText("");
		cmbCategory.setSelectedIndex(0);
		cmbSupplier.setSelectedIndex(0);
		txaItemDescription.setText("");
		txtItemUnit.setText("");
		txtBarCode.setText("");
		super.dispose();
	}

}
