package com.silikonm.ui.support;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import com.silikonm.ui.MainFrame;

/**
 * @author harsha
 */
public class CloseTab extends JPanel implements ActionListener {

	private JLabel title = new JLabel();
	JButton closeButton = new JButton();
	int tabIndex;
	private JTabbedPane tabbedPane = null;
	private String panelName = null;

	public void setTitle(String title, JTabbedPane tabbedPane) {
		// this.title.setOpaque(true);
		//this.title.setFont(new Font(Font.DIALOG, Font.PLAIN, 11));
		this.title.setText(title);
		this.tabbedPane = tabbedPane;
		this.title.setToolTipText(title);
	}

	public void updateTitle(String title) {
		this.title.setText(title);
		this.title.setToolTipText(title);
		this.title.setOpaque(true);
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public void setCloseAction(ActionListener al) {
	}

	public void setTabIndex(int index) {
		this.tabIndex = index;
	}

	public void init() {
		closeButton.setSize(10, 10);
		closeButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		closeButton.setIcon(new ImageIcon(new ImageIcon(CloseTab.class
				.getResource("/com/silikonm/ui/support/delete.png")).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
		add(title);
		closeButton.addActionListener(this);
		add(closeButton);
		setOpaque(false);
	}

	public void setPanelName(String pn) {
		this.panelName = pn;
	}

	public void actionPerformed(ActionEvent e) {
		int i = tabbedPane.indexOfTabComponent(this);
		if (i != -1) {
			MainFrame.getInstance().unregisterPanel(panelName);
			tabbedPane.remove(i);
		}
	}
	
	
}
