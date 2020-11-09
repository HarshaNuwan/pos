package com.silikonm.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import com.silikonm.ui.support.CustomPanel;

import net.miginfocom.swing.MigLayout;



public class Splash extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JProgressBar pgbAppLoadProgress;
	private JLabel lblStatus;
	/**
	 * Create the dialog.
	 */
	public Splash() {
		
		setUndecorated(true);
		setSize(new Dimension(450, 300));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new CustomPanel();
			contentPanel.add(panel);
			panel.setLayout(new MigLayout("", "[][grow]", "[][][][][][]"));
			{
				JLabel lblApplicationName = new JLabel("SilikonM POS");
				lblApplicationName.setFont(new Font("Dialog", Font.PLAIN, 36));
				panel.add(lblApplicationName, "cell 0 1 2 1,alignx center");
			}
			{
				JLabel lblVersion = new JLabel("New label");
				panel.add(lblVersion, "cell 1 2,alignx right");
			}
			{
				pgbAppLoadProgress = new JProgressBar();
				pgbAppLoadProgress.setForeground(new Color(148, 0, 211));
				panel.add(pgbAppLoadProgress, "cell 0 4 2 1,growx");
			}
			{
				lblStatus = new JLabel("New label");
				panel.add(lblStatus, "cell 0 5 2 1");
			}
		}
		
		//JFrame.setDefaultLookAndFeelDecorated(true);
		//JDialog.setDefaultLookAndFeelDecorated(true);
	}

	class ContentPanel extends CustomPanel {

	}

	public void setMaxProgressSize(int maxSize) {
		pgbAppLoadProgress.setMaximum(maxSize);
	}

	public int getProgress() {
		return pgbAppLoadProgress.getValue();
	}

	public void setProgress(int value) {
		pgbAppLoadProgress.setValue(value);
	}

	public void setStatusMessage(String stMsg) {
		lblStatus.setText(stMsg);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
