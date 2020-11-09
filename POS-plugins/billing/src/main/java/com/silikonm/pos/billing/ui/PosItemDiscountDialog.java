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

public class PosItemDiscountDialog extends JDialog {
	private JTextField txtDiscount;

	/**
	 * Create the dialog.
	 */
	public PosItemDiscountDialog() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new MigLayout("", "[grow]", "[][]"));
		{
			JLabel lblQuantity = new JLabel("Discount");
			lblQuantity.setFont(new Font("Dialog", Font.BOLD, 16));
			getContentPane().add(lblQuantity, "cell 0 0");
		}
		{
			txtDiscount = new JTextField();
			txtDiscount.setHorizontalAlignment(SwingConstants.TRAILING);
			txtDiscount.setBackground(Color.BLACK);
			txtDiscount.setForeground(Color.GREEN);
			txtDiscount.setDocument(new JTextNumericFilter("1234567890."));
			txtDiscount.setFont(new Font("Dialog", Font.BOLD, 25));
			getContentPane().add(txtDiscount, "cell 0 1,growx");
			txtDiscount.setColumns(10);
		}
	}

	public void addDiscountTextBoxActionListener(KeyListener keyListener) {
		txtDiscount.addKeyListener(keyListener);
	}

	public BigDecimal getItemDiscount() {
		return new BigDecimal(txtDiscount.getText());
	}

	public void addEscapeKeyAction(AbstractAction abstractAction, String escape) {

		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks,
				escape);
		getRootPane().getActionMap().put("ESCAPE", abstractAction);
	}

	

	@Override
	public void dispose() {
		txtDiscount.setText("");		
		super.dispose();
	}
}
