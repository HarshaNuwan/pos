package com.silikonm.pos.billing.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import com.silikonm.skin.component.table.CustomTableRenderer;
import com.silikonm.pos.billing.extend.StoreBrowserTableModel;;

public class StoreBrowser extends JDialog {
    private JTable tblItems;

    /**
     * Create the dialog.
     */
    public StoreBrowser() {

        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        {
            JScrollPane scrollPane = new JScrollPane();
            getContentPane().add(scrollPane, BorderLayout.CENTER);
            {
                tblItems = new JTable();
                tblItems.setDefaultRenderer(Object.class, new CustomTableRenderer(new Color(202, 227, 235), Color.white));
                InputMap im = tblItems.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
                im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "none");
              
                scrollPane.setViewportView(tblItems);
            }
        }
    }
    
    public void setStoreBrowserItemsTableModel(StoreBrowserTableModel model){
    	tblItems.setModel(model);
    	
    }
   
   

    public void addEnterKeyAction(Action action, String key) {
        KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, key);
        getRootPane().getActionMap().put("ENTER", action);
    }

    public void addEscapeKeyAction(AbstractAction abstractAction, String escape) {
        KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, escape);
        getRootPane().getActionMap().put("ESCAPE", abstractAction);
    }

	public int getSelectedTableRowIndex() {
		if(tblItems.getSelectedColumnCount()==0){
			return -1;
		}
		return tblItems.getSelectedRow();
	}
}
