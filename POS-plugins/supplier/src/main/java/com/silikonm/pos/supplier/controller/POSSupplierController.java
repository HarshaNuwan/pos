package com.silikonm.pos.supplier.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.silikonm.pos.supplier.model.POSSupplierModel;
import com.silikonm.pos.supplier.ui.POSSuppliersView;

public class POSSupplierController implements ActionListener {

	private POSSuppliersView view;
	private POSSupplierModel model;
	
	public POSSupplierController(POSSuppliersView view, POSSupplierModel model) {
		this.view = view;
		this.model = model;
		
		this.model.getAllSuppliers();
		this.view.setSuppliersTableModel(model.getPosSuppliersTableModel());
		
		this.view.setNewAction(model.getPosSuppliersTableModel());
		this.view.setSupplierTableMouseAction(model.getPosSuppliersTableModel());
		this.view.setDeletePopUpMenuItemActionListener(model.getPosSuppliersTableModel());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 
		
	}
}
