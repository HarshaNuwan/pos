package com.silikonm.pos.itemtype.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.silikonm.pos.itemtype.model.PositemCategoriesModel;
import com.silikonm.pos.itemtype.ui.PosItemCategoriesView;

public class PosItemCategoriesController implements ActionListener{

	private PosItemCategoriesView view;
	private PositemCategoriesModel model;
		
	public PosItemCategoriesController(PosItemCategoriesView view,
			PositemCategoriesModel model) {
		super();
		this.view = view;
		this.model = model;
		
		//set item categories table model
		view.setItemCategoriesTableModel(model.getCategoriesTableModel());
		view.setNewAction(model.getCategoriesTableModel());
		view.setItemCategoryTableMouseListener(model.getCategoriesTableModel());
		view.addDeleteMenuItemActionListener(model.getCategoriesTableModel());
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	
}
