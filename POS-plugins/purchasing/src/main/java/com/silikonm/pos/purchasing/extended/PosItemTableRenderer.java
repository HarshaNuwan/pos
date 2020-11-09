package com.silikonm.pos.purchasing.extended;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class PosItemTableRenderer implements TableCellRenderer {

	private int sinhalaRow = 0;
	private int fontSize = 0;

	public PosItemTableRenderer(int row) {
		this.sinhalaRow = row;
	}
	
	public PosItemTableRenderer(int row, int fontSize) {
		this.sinhalaRow = row;
		this.fontSize = fontSize;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel editor = new JLabel();
		editor.setOpaque(true);
		if (value != null) {
			editor.setText(value.toString());
		}
		/*if (column == sinhalaRow) {
			if(fontSize!=0){
				editor.setFont(FontService.getSinhalaFont(fontSize));
			}else{
				editor.setFont(FontService.getSinhalaFont(16));
			}
			
		}*/
		editor.setBackground((row % 2 == 0) ? Color.white : new Color(229, 255,
				204));
		if (isSelected) {
			editor.setBackground(table.getSelectionBackground());
		}
		return editor;
	}

}
