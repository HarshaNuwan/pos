package com.silikonm.pos.billing.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class PosCashPaymentDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtAmount;

	/**
	 * Create the dialog.
	 */
	public PosCashPaymentDialog() {
		
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][]"));
		{
			JLabel lblAmount = new JLabel("Amount");
			lblAmount.setFont(new Font("Dialog", Font.BOLD, 16));
			contentPanel.add(lblAmount, "cell 0 0");
		}
		{
			txtAmount = new JTextField();
			txtAmount.setFont(new Font("Dialog", Font.PLAIN, 25));
			contentPanel.add(txtAmount, "cell 0 1,growx");
			txtAmount.setColumns(10);
		}
	}
	
	public void addAmountTextBoxKeyListener(KeyListener keyListener) {
        txtAmount.addKeyListener(keyListener);
    }

	public void addEscapeKeyAction(AbstractAction abstractAction, String escape) {

		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks,
				escape);
		getRootPane().getActionMap().put("ESCAPE", abstractAction);
	}

	public BigDecimal getAmount() {
		if (txtAmount.getText().isEmpty()) {
			return BigDecimal.ZERO;
		}
		return new BigDecimal(txtAmount.getText());
	}

	@Override
	public void dispose() {
		txtAmount.setText("");
		super.dispose();
	}

}
