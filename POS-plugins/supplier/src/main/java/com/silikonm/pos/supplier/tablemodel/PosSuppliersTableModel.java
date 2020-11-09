package com.silikonm.pos.supplier.tablemodel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.silikonm.common.dto.pos.PosSupplierBean;
import com.silikonm.pos.supplier.model.POSSupplierModel;
import com.silikonm.pos.supplier.ui.POSSuppliersView;
import com.silikonm.pos.supplier.ui.PosSupplierDetailsDialog;
import com.silikonm.utilities.Alert;

public class PosSuppliersTableModel extends AbstractTableModel implements
		ActionListener, MouseListener {
	private Object[] columns = new Object[] { "Supplier ID", "Supplier Name",
			"Supplier Address", "Supplier Phone" };
	private List<Object[]> data;
	PosSupplierDetailsDialog supplierDetailsDialog;
	private POSSupplierModel posSupplierModel;
	private POSSuppliersView posSuppliersView;

	public PosSuppliersTableModel(List<Object[]> data, POSSupplierModel model,
			POSSuppliersView view) {
		// initialize supplier details dialog box
		this.supplierDetailsDialog = new PosSupplierDetailsDialog();
		// initialize supplier details main view
		this.posSuppliersView = view;
		//
		supplierDetailsDialog.setModal(true);
		supplierDetailsDialog.setLocationRelativeTo(null);
		// set save button actionlistener.
		supplierDetailsDialog.setSaveButtonActionListener(this);
		// set cancel button action listener.
		supplierDetailsDialog.setCancelButtonActionListener(this);

		this.posSupplierModel = model;// set pos supplier model
		if (data == null) {
			this.data = new ArrayList<Object[]>();
		} else {
			this.data = data;
		}
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object[] row = data.get(rowIndex);
		return row[columnIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		data.get(rowIndex)[columnIndex] = aValue;

		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int column) {
		return columns[column].toString();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		/*
		 * if (columnIndex == 1 || columnIndex == 3) { return true; }
		 */
		return false;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columns[columnIndex].getClass();
	}

	public void resetTable() {
		data.clear();
		fireTableDataChanged();
	}

	public void insertRow(Object[] row) {
		data.add(row);
		fireTableDataChanged();
	}

	public void removeRow(int rowIndex) {
		data.remove(rowIndex);
		fireTableDataChanged();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("TOOLBAR_NEW_ACTION")) {
			supplierDetailsDialog.setSupplierID(posSupplierModel
					.getNextSupplierId());
			supplierDetailsDialog.setVisible(true);
		} else if (e.getActionCommand().equals("SAVE_SUPPLIER")) {

			if (supplierDetailsDialog.getSupplierName() == null) {
				Alert.message("Please enter the supplier name.");
				return;
			}
			if (supplierDetailsDialog.getSupplierAddress() == null) {
				Alert.message("Please enter the supplier address.");
				return;
			}

			// create and set supplier details.
			PosSupplierBean bean = new PosSupplierBean();
			bean.setSupplierID(supplierDetailsDialog.getSupplierID());
			bean.setSupplierCode(supplierDetailsDialog.getSupplierCode());
			bean.setSupplierName(supplierDetailsDialog.getSupplierName());
			bean.setSupplierAddress(supplierDetailsDialog.getSupplierAddress());
			bean.setSupplierPhone(supplierDetailsDialog.getSupplierPhone());
			posSupplierModel.saveSupplier(bean);// send supplier to the model

			supplierDetailsDialog.dispose();
			posSupplierModel.getAllSuppliers();// reload available suppliers
		} else if (e.getActionCommand().equals("CLOSE_SUPPLIER_SAVE_DIALOG")) {
			supplierDetailsDialog.dispose();
		} else if (e.getActionCommand().equals("DELETE_SUPPLIER")) {

			if (Alert
					.confirmYesNoDialog("Are you sure you need to delete this supplier?") == Alert.YES_OPTION) {
				posSupplierModel.deleteSupplier(getSelectedSupplier(
						posSuppliersView.getSelectedTableRowIndex())
						.getSupplierID());
				posSupplierModel.getAllSuppliers();// reload available suppliers
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof JTable) {
			JTable table = (JTable) e.getSource();
			if (table.getName().equals("SUPPLIERS_TABLE")) {
				if (e.getClickCount() == 2) {

					PosSupplierBean bean = getSelectedSupplier(table
							.getSelectedRow());
					//
					supplierDetailsDialog.setSupplierID(bean.getSupplierID());
					supplierDetailsDialog.setSupplierName(bean
							.getSupplierName());
					supplierDetailsDialog.setSupplierAddress(bean
							.getSupplierAddress());
					supplierDetailsDialog.setSupplierPhone(bean
							.getSupplierPhone());
					supplierDetailsDialog.setVisible(true);
				}
				if (e.getButton() == MouseEvent.BUTTON3) {

				}

			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private PosSupplierBean getSelectedSupplier(int selectedRowIndex) {
		Object[] dataRow = data.get(selectedRowIndex);
		PosSupplierBean bean = (PosSupplierBean) dataRow[1];
		return bean;
	}

}
