package com.silikonm.pos.billing.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

import com.silikonm.commons.JTextNumericFilter;

public class ItemQty extends JDialog {
	private JTextField txtQty;
	private JTextField txtUnitPrice;

	/**
	 * Create the dialog.
	 */
	public ItemQty() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new MigLayout("", "[grow]", "[][][][]"));

		JLabel lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setFont(new Font("Dialog", Font.BOLD, 16));
		getContentPane().add(lblUnitPrice, "cell 0 0");

		txtUnitPrice = new JTextField();
		txtUnitPrice.setFocusable(false);
		txtUnitPrice.setHorizontalAlignment(SwingConstants.TRAILING);
		txtUnitPrice.setForeground(Color.GREEN);
		txtUnitPrice.setText("dfsdfsdf");
		txtUnitPrice.setBackground(Color.BLACK);
		txtUnitPrice.setEditable(false);
		txtUnitPrice.setDocument(new JTextNumericFilter("1234567890."));
		txtUnitPrice.setFont(new Font("Dialog", Font.BOLD, 25));
		getContentPane().add(txtUnitPrice, "cell 0 1,growx");
		txtUnitPrice.setColumns(10);
		{
			JLabel lblQuantity = new JLabel("Quantity");
			lblQuantity.setFont(new Font("Dialog", Font.BOLD, 16));
			getContentPane().add(lblQuantity, "cell 0 2");
		}
		{
			txtQty = new JTextField();
			txtQty.setHorizontalAlignment(SwingConstants.TRAILING);
			txtQty.setBackground(Color.BLACK);
			txtQty.setForeground(Color.GREEN);
			txtQty.setDocument(new JTextNumericFilter("1234567890."));
			txtQty.setFont(new Font("Dialog", Font.BOLD, 25));
			getContentPane().add(txtQty, "cell 0 3,growx");
			txtQty.setColumns(10);
		}
	}

	public void addQtyTextBoxKeyListener(KeyListener keyListener) {
		txtQty.addKeyListener(keyListener);
	}

	public BigDecimal getItemQty() {
		if(txtQty.getText().isEmpty()){
			return BigDecimal.ZERO;
		}
		return new BigDecimal(txtQty.getText());
	}

	public void addEscapeKeyAction(AbstractAction abstractAction, String escape) {

		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks,
				escape);
		getRootPane().getActionMap().put("ESCAPE", abstractAction);
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		txtUnitPrice.setText(unitPrice.toString());
	}

	public BigDecimal getUnitPrice() {
		if (txtUnitPrice.getText().isEmpty()) {
			return BigDecimal.ZERO;
		}
		return new BigDecimal(txtUnitPrice.getText());
	}

	@Override
	public void dispose() {
		txtQty.setText("");
		txtUnitPrice.setText("");
		super.dispose();
	}
}
