package com.silikonm.pos.supplier.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import net.miginfocom.swing.MigLayout;

import javax.swing.JTextField;

public class PosSupplierDetailsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSupplierID;
	private JTextField txtSupplierName;
	private JTextField txtSupplierAddress;
	private JTextField txtSupplierPhone;
	private JButton btnSave, btnCancel;
	private JTextField txtSupplierCode;

	/**
	 * Create the dialog.
	 */
	public PosSupplierDetailsDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblSupplierDetails = new JLabel(" Supplier Details");
			lblSupplierDetails.setOpaque(true);
			lblSupplierDetails.setForeground(Color.WHITE);
			lblSupplierDetails.setFont(new Font("Dialog", Font.BOLD, 24));
			lblSupplierDetails.setBackground(new Color(9, 149, 214));
			contentPanel.add(lblSupplierDetails, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new MigLayout("", "[][grow]", "[][][][][]"));
			{
				JLabel lblSupplierId = new JLabel("Supplier ID");
				panel.add(lblSupplierId, "cell 0 0");
			}
			{
				txtSupplierID = new JTextField();
				txtSupplierID.setEditable(false);
				panel.add(txtSupplierID, "cell 1 0,growx");
				txtSupplierID.setColumns(10);
			}
			{
				JLabel lblSupplierCode = new JLabel("Supplier Code");
				panel.add(lblSupplierCode, "cell 0 1");
			}
			{
				txtSupplierCode = new JTextField();
				txtSupplierCode.setText("TESTCODE001");
				txtSupplierCode.setEditable(false);
				panel.add(txtSupplierCode, "cell 1 1,growx");
				txtSupplierCode.setColumns(10);
			}
			{
				JLabel lblSupplierName = new JLabel("Supplier Name");
				panel.add(lblSupplierName, "cell 0 2");
			}
			{
				txtSupplierName = new JTextField();
				panel.add(txtSupplierName, "cell 1 2,growx");
				txtSupplierName.setColumns(10);
			}
			{
				JLabel lblSupplierAddress = new JLabel("Supplier Address");
				panel.add(lblSupplierAddress, "cell 0 3");
			}
			{
				txtSupplierAddress = new JTextField();
				panel.add(txtSupplierAddress, "cell 1 3,growx");
				txtSupplierAddress.setColumns(10);
			}
			{
				JLabel lblSupplierPhone = new JLabel("Supplier Phone");
				panel.add(lblSupplierPhone, "cell 0 4");
			}
			{
				txtSupplierPhone = new JTextField();
				panel.add(txtSupplierPhone, "cell 1 4,growx");
				txtSupplierPhone.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSave = new JButton("Save");
				btnSave.setActionCommand("SAVE_SUPPLIER");				
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				btnCancel = new JButton("Cancel");
				btnCancel.setActionCommand("CLOSE_SUPPLIER_SAVE_DIALOG");				
				buttonPane.add(btnCancel);
			}
		}
	}

	public void setSaveButtonActionListener(ActionListener al) {
		btnSave.addActionListener(al);
	}

	public void setCancelButtonActionListener(ActionListener al) {
		btnCancel.addActionListener(al);
	}

	public int getSupplierID() {
		if (txtSupplierID.getText().isEmpty()) {
			return -1;
		}
		return Integer.valueOf(txtSupplierID.getText());
	}
	
	public String getSupplierCode(){
		if(txtSupplierCode.getText().isEmpty()){
			return null;
		}
		
		return txtSupplierCode.getText();
	}

	public String getSupplierName() {
		if (txtSupplierName.getText().isEmpty()) {
			return null;
		}
		return txtSupplierName.getText();
	}

	public String getSupplierAddress() {
		if (txtSupplierAddress.getText().isEmpty()) {
			return null;
		}
		return txtSupplierAddress.getText();
	}

	public String getSupplierPhone() {
		if (txtSupplierPhone.getText().isEmpty()) {
			return null;
		}

		return txtSupplierPhone.getText();
	}

	public void setSupplierID(int supplierID) {
		txtSupplierID.setText(String.valueOf(supplierID));
	}
	
	public void setSupplierCode(String supplierCode){
		txtSupplierCode.setText(supplierCode);
	}

	public void setSupplierName(String supplierName) {
		txtSupplierName.setText(supplierName);
	}
	
	public void setSupplierAddress(String supplierAddress){
		txtSupplierAddress.setText(supplierAddress);
	}
	
	public void setSupplierPhone(String supplierPhone){
		txtSupplierPhone.setText(supplierPhone);
	}
	
	//override dispose method
	@Override
	public void dispose() {
		txtSupplierID.setText("");
		txtSupplierName.setText("");
		txtSupplierAddress.setText("");
		txtSupplierPhone.setText("");
		super.dispose();
	}
	
}
