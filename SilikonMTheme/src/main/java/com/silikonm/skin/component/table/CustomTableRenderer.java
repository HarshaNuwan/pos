package com.silikonm.skin.component.table;

import java.awt.Color;
import java.awt.Component;
import java.math.BigDecimal;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableRenderer extends DefaultTableCellRenderer {
	private Color backGround_one;
	private Color backGround_two;
	private Color forGround;
	private Color selectedBackground;

	public CustomTableRenderer(Color bg1, Color fg) {
		this.backGround_one = bg1;
		this.forGround = fg;
	}
	
	public CustomTableRenderer(Color bg1, Color fg, Color sbk) {
		this.backGround_one = bg1;
		this.forGround = fg;
		this.selectedBackground = sbk;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int col) {
		Component comp = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, col);
		
		if (comp instanceof JComponent) {
			JComponent jc = (JComponent) comp;
			if (table.getValueAt(row, col) != null) {
				Object obj = table.getValueAt(row, col);
				if (obj instanceof BigDecimal || obj instanceof Double
						|| obj instanceof Integer) {
					setHorizontalAlignment(SwingConstants.RIGHT);
				} else {
					setHorizontalAlignment(SwingConstants.LEFT);
				}
				jc.setToolTipText(obj.toString());
			}
		}

		if (row % 2 == 1) {
			comp.setBackground(backGround_one);
		} else {
			comp.setBackground(Color.WHITE);
			comp.setBackground(forGround);
		}

		if (isSelected) {
			comp.setForeground(Color.black);
			if(this.selectedBackground != null){
				comp.setBackground(this.selectedBackground);
			}else{
				comp.setBackground(new Color(28, 134, 238));
			}
			
		}

		return comp;
	}
}
