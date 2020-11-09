package com.silikonm.pos.itemtype.ui;

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

public class PosItemCategoriyDetailsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCategoryId;
	private JTextField txtCategoryCode;
	private JTextField txtCategoryName;
	private JButton btnSave, btnCancel;

	/**
	 * Create the dialog.
	 */
	public PosItemCategoriyDetailsDialog() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblItemCategoryDetails = new JLabel("Item Category Details");
			lblItemCategoryDetails.setOpaque(true);
			lblItemCategoryDetails.setForeground(Color.WHITE);
			lblItemCategoryDetails.setFont(new Font("Dialog", Font.BOLD, 24));
			lblItemCategoryDetails.setBackground(new Color(9, 149, 214));
			contentPanel.add(lblItemCategoryDetails, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new MigLayout("", "[][grow]", "[][][]"));
			{
				JLabel lblCategoryId = new JLabel("Category ID");
				panel.add(lblCategoryId, "cell 0 0");
			}
			{
				txtCategoryId = new JTextField();
				txtCategoryId.setEditable(false);
				panel.add(txtCategoryId, "cell 1 0,growx");
				txtCategoryId.setColumns(10);
			}
			{
				JLabel lblCategoryCode = new JLabel("Category Code");
				panel.add(lblCategoryCode, "cell 0 1");
			}
			{
				txtCategoryCode = new JTextField();
				txtCategoryCode.setEditable(false);
				panel.add(txtCategoryCode, "cell 1 1,growx");
				txtCategoryCode.setColumns(10);
			}
			{
				JLabel lblCategoryName = new JLabel("Category Name");
				panel.add(lblCategoryName, "cell 0 2");
			}
			{
				txtCategoryName = new JTextField();
				panel.add(txtCategoryName, "cell 1 2,growx");
				txtCategoryName.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSave = new JButton("Save");
				btnSave.setActionCommand("SAVE_CATEGORY");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				btnCancel = new JButton("Cancel");
				btnCancel.setActionCommand("CLOSE_CATEGORY_DIALOG");
				buttonPane.add(btnCancel);
			}
		}
	}

	public void setCategoryID(int categoryId) {
		txtCategoryId.setText(String.valueOf(categoryId));

	}

	public void setCategoryCode(String categoryCode) {
		txtCategoryCode.setText(categoryCode);
	}

	public void setCategoryName(String categoryName) {
		txtCategoryName.setText(categoryName);
	}

	public int getCategoryID() {
		if (txtCategoryId.getText().isEmpty()) {
			return -1;
		}
		return Integer.valueOf(txtCategoryId.getText());
	}

	public String getCategoryCode() {
		return txtCategoryCode.getText();
	}

	public String getCategoryName() {
		if(txtCategoryName.getText().isEmpty()){
			return null;
		}
		return txtCategoryName.getText();
	}
	
	public void addSaveButtonActionListener(ActionListener al){
		btnSave.addActionListener(al);
	}
	
	public void addCancelButtonActionListener(ActionListener al){
		btnCancel.addActionListener(al);
	}

	@Override
	public void dispose() {
		txtCategoryId.setText("");
		txtCategoryCode.setText("");
		txtCategoryName.setText("");
		super.dispose();
	}

}
