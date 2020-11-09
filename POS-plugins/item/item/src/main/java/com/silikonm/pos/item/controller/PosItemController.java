package com.silikonm.pos.item.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.PatternSyntaxException;

import javax.swing.RowFilter;

import com.silikonm.pos.item.model.PosItemModel;
import com.silikonm.pos.item.ui.PosItemsPanelView;

public class PosItemController implements KeyListener{
	private PosItemsPanelView view;
	private PosItemModel model;

	
	
	public PosItemController(PosItemsPanelView view, PosItemModel model) {

		this.view = view;
		this.model = model;
		
		view.setPosItemsTableModel(model.getPosItemsTableModel());
		view.setNewAction(model.getPosItemsTableModel());
		view.addDeletePopUpMenuItemActionListener(model.getPosItemsTableModel());
		view.addItemsTableMouseListener(model.getPosItemsTableModel());
		model.getAllItems();
		
		
		
		view.addSearchBoxKeyListener(this);
		view.setTableSorter(model.getSorter());
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (view.getSearchKey() == null) {
			model.setRowFilter(null);

		} else {

			try {
				model.setRowFilter(RowFilter.regexFilter("(?i)"
						+ view.getSearchKey()));
			} catch (PatternSyntaxException pse) {
				pse.printStackTrace();

			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
