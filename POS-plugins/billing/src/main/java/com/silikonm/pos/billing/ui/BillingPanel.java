package com.silikonm.pos.billing.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import net.miginfocom.swing.MigLayout;

import com.silikonm.commons.UIType;
import com.silikonm.skin.component.table.CustomTableRenderer;
import com.silikonm.pos.billing.extend.BilledItemsTableModel;

public class BillingPanel extends JPanel implements UIType {

	JPanel pnlItems;
	JPanel pblItemTableHolder;
	JScrollPane scrollPane;
	JFrame frame;
	private JTable table;
	private JPanel pnlBillingInfo;
	private JLabel lblTotalItems;
	private JTextField textField;
	private JLabel lblValue;
	private JTextField txtSubTotal;

	//
	BilledItemsTableModel billedItemsTableModel;
	private JLabel lblReceived;
	private JTextField txtReceivedPayment;
	private JLabel lblBalance;
	private JTextField txtBalance;

	/**
	 * Create the panel.
	 */
	public BillingPanel() {
		pnlItems = new JPanel();
		pblItemTableHolder = new JPanel();
		pblItemTableHolder.setName("billedItemsHolderPanel");

		billedItemsTableModel = new BilledItemsTableModel(null);

		Object[] data = { "fdgdfgdfg", "rrtyrtrty", " we rwerwer ", "4",
				"q eqw eqe", "6" };

		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);
		billedItemsTableModel.insertRow(data);

		//

		setLayout(new BorderLayout(0, 0));

		add(pnlItems, BorderLayout.CENTER);
		pnlItems.setLayout(new BorderLayout(0, 0));

		pnlItems.add(pblItemTableHolder, BorderLayout.CENTER);
		pblItemTableHolder.setLayout(new BorderLayout(0, 0));

		table = new JTable();
		
		
		
		table.setRowHeight(35);
		
		
		
		table.setFont(new Font("Monospaced", Font.BOLD, 16));
		table.setDefaultRenderer(Object.class, new CustomTableRenderer(new Color(194, 226, 244), new Color(248, 255, 215), new Color(22, 191, 211)));
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.getTableHeader().setPreferredSize(new Dimension(100, 45));
		table.getTableHeader().setBackground(new Color(13, 135, 146));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		table.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 18));
		;
		table.setModel(billedItemsTableModel);
		
		/**
		 * Setting column widths
		 */
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		scrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pblItemTableHolder.add(scrollPane, BorderLayout.CENTER);

		pnlBillingInfo = new JPanel();
		pblItemTableHolder.add(pnlBillingInfo, BorderLayout.SOUTH);
		pnlBillingInfo.setLayout(new MigLayout("", "[grow][][][100px:n,right]",
				"[][][]"));

		lblTotalItems = new JLabel("Total Items");
		pnlBillingInfo.add(lblTotalItems, "cell 0 0,alignx right");

		textField = new JTextField();
		textField.setFocusable(false);
		textField.setEditable(false);
		pnlBillingInfo.add(textField, "cell 1 0,alignx left");
		textField.setColumns(10);

		lblValue = new JLabel("Sub Total");
		pnlBillingInfo.add(lblValue, "cell 2 0,alignx right");

		txtSubTotal = new JTextField();
		txtSubTotal.setFocusable(false);
		txtSubTotal.setEditable(false);
		pnlBillingInfo.add(txtSubTotal, "cell 3 0,alignx right");
		txtSubTotal.setColumns(20);

		lblReceived = new JLabel("Received");
		pnlBillingInfo.add(lblReceived, "cell 2 1,alignx right");

		txtReceivedPayment = new JTextField();
		txtReceivedPayment.setFocusable(false);
		txtReceivedPayment.setEditable(false);
		pnlBillingInfo.add(txtReceivedPayment, "cell 3 1,alignx right");
		txtReceivedPayment.setColumns(20);

		lblBalance = new JLabel("Balance");
		pnlBillingInfo.add(lblBalance, "cell 2 2,alignx right");

		txtBalance = new JTextField();
		txtBalance.setFocusable(false);
		txtBalance.setEditable(false);
		pnlBillingInfo.add(txtBalance, "cell 3 2,alignx right");
		txtBalance.setColumns(20);

		focusItemsTable();	

	}

	public JPanel getPanel() {
		return null;
	}

	public JDialog getDialog() {
		return null;
	}

	public JFrame getFrame() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setContentPane(this);
		return frame;
	}

	public void focusItemsTable() {
		if (table.getRowCount() > 0) {
			table.setRowSelectionInterval(0, 0);
		}
		table.requestFocus();
	}
	
	public void setBilledItemsTableModel(BilledItemsTableModel tableModel) {
		this.table.setModel(tableModel);
	}
	
	public void setPayment(BigDecimal paymentReceived) {
		txtReceivedPayment.setText(paymentReceived.toString());

	}
	
	public void setBalance(BigDecimal balance) {
		txtBalance.setText(balance.toString());

	}
	
	public void setSubTotal(BigDecimal subTotal) {
		txtSubTotal.setText(subTotal.setScale(3, RoundingMode.CEILING).toString());
	}
	
	
	public BigDecimal getCashReceived(){
		if(txtReceivedPayment.getText().isEmpty()){
			return BigDecimal.ZERO;
		}
		
		return new BigDecimal(txtReceivedPayment.getText()).setScale(3, RoundingMode.CEILING);
	}
	
	public int getSelectedRowIndex() {
		if(this.table.getSelectedRowCount()==0){
			return -1;
		}
		return this.table.getSelectedRow();
	}
	
	public void addDeleteKeyAction(Action action, String key) {
		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, key);
		getActionMap().put("DELETE", action);
	}

	// Key listener related methods
	
	/*
	 * Shows the help dialog
	 */
//	public void addF1KeyAction(Action action, String key) {
//		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
//		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, key);
//		getActionMap().put("F1", action);
//	}
	
	/*
	 * Shows the stock browser
	 */
//	public void addF4KeyAction(Action action, String key){
//		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0);
//		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, key);
//		getActionMap().put("F4", action);
//	}

	public void addEscapeKeyAction(AbstractAction abstractAction, String escape) {
		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, escape);
		getActionMap().put("ESCAPE", abstractAction);
	}
	
	
	public void addF1KeyAction(Action action, String key) {
		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, key);
		getActionMap().put("F1", action);
	}

	public void addF2KeyAction(Action action, String key) {
		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0);
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, key);
		getActionMap().put("F2", action);
	}

	public void addF3KeyAction(Action action, String key) {
		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0);
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, key);
		getActionMap().put("F3", action);
	}

	public void addF4KeyAction(Action action, String key) {
		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0);
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, key);
		getActionMap().put("F4", action);
	}

	public void addF5KeyAction(Action action, String key) {
		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0);
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, key);
		getActionMap().put("F5", action);
	}

	public void addF6KeyAction(Action action, String key) {
		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0);
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, key);
		getActionMap().put("F6", action);
	}
	
	public void addF7KeyAction(Action action, String key) {
		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0);
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, key);
		getActionMap().put("F7", action);
	}
	
	public void addF8KeyAction(Action action, String key) {
		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0);
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, key);
		getActionMap().put("F8", action);
	}

	public void resetForm() {
		txtBalance.setText("0.00");
		txtReceivedPayment.setText("0.00");
		txtSubTotal.setText("0.00");

	}

	public void disposePOS() {
		if (this.frame != null) {
			frame.dispose();
		}
	}

}
