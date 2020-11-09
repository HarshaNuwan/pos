package com.silikonm.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import com.silikonm.core.Initializer;
import com.silikonm.ui.support.CustomPanel;


public class Login extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the dialog.
	 */
	public Login() {
		setModal(true);
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
			panel.setLayout(new MigLayout("", "[][grow]", "[][][]"));
			{
				JLabel lblNewLabel = new JLabel("User Login");
				lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 36));
				panel.add(lblNewLabel, "cell 0 0 2 1");
			}
			{
				JLabel label = new JLabel("Username");
				panel.add(label, "cell 0 1,alignx left");
			}
			{
				textField = new JTextField();
				textField.setColumns(30);
				panel.add(textField, "cell 1 1,growx");
			}
			{
				JLabel label = new JLabel("Password");
				panel.add(label, "cell 0 2,alignx left");
			}
			{
				passwordField = new JPasswordField();
				passwordField.setColumns(30);
				panel.add(passwordField, "cell 1 2,growx");
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton loginButton = new JButton("LOGIN");
				loginButton.setActionCommand("OK");
				loginButton.addActionListener(this);
				buttonPane.add(loginButton);
				getRootPane().setDefaultButton(loginButton);
			}
			{
				JButton exitButton = new JButton("EXIT");
				exitButton.addActionListener(this);
				exitButton.setActionCommand("Cancel");
				buttonPane.add(exitButton);
			}
		}
	}

	class ContentPanel extends CustomPanel {

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("OK")){
			

			try {
				this.dispose();
				Initializer initializer = new Initializer();
			} catch (URISyntaxException ex) {			
				ex.printStackTrace();
			}
			
		}else if(e.getActionCommand().equalsIgnoreCase("Cancel")){
			System.exit(0);
		}
		
	}

}
