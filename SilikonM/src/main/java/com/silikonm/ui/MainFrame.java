package com.silikonm.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;

import com.silikonm.commons.PluginMenu;
import com.silikonm.ui.support.CloseTab;
import com.silikonm.ui.support.ComponentMover;

public class MainFrame extends JFrame {

	private JPanel stage;//this is the parent panel
	private JPanel titlePanel;//this is the title panel which holds the title and the control buttons
	private JLabel title;
	private JPanel contentPane;//main content pane
	private JMenuBar mainMenu;

	private static MainFrame mainFrame = null;
	private JTabbedPane tabbedPane;
	private Map<String, JPanel> panels;
	private Map<String, JFrame> frames;

	private MainFrame() {
		//setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		stage = new JPanel();
		stage.setBorder(new LineBorder(Color.GRAY));
		stage.setLayout(new BorderLayout());

		titlePanel = new JPanel();
		titlePanel.setName("titlePanel");
		
		titlePanel.setBorder(null);
		titlePanel.setLayout(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		FlowLayout fl_buttonPanel = new FlowLayout();
		fl_buttonPanel.setVgap(0);
		fl_buttonPanel.setHgap(0);
		buttonPanel.setLayout(fl_buttonPanel);
		

		//ImageIcon close = new ImageIcon(CloseTab.class
				//.getResource("/com/silikonm/ui/support/closeButton.png"));

		//ImageIcon closeRollOver = new ImageIcon(MainFrame.class
				//.getResource("/com/silikonm/ui/support/closeButtonMouseOver.png"));
		
		//JButton closeButton = createButton(close, closeRollOver);
		//closeButton.setName("closeButton");
		//closeButton.addActionListener(new ActionListener() {

//			public void actionPerformed(ActionEvent e) {
//				int confirm = JOptionPane.showOptionDialog(getInstance(),
//						"Are You Sure to Close Application?",
//						"Exit Confirmation", JOptionPane.YES_NO_OPTION,
//						JOptionPane.QUESTION_MESSAGE, null, null, null);
//				if (confirm == JOptionPane.YES_OPTION) {
//					System.exit(1);
//				}
//
//			}
//		});
		
//		ImageIcon maximize = new ImageIcon(CloseTab.class
//				.getResource("/com/silikonm/ui/support/maximizeButton.png"));
//
//		ImageIcon maximize_mo = new ImageIcon(MainFrame.class
//				.getResource("/com/silikonm/ui/support/maximizeButtonMouseOver.png"));
//		
//		JButton maximizeButton = createButton(maximize, maximize_mo);
//		maximizeButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				if (getInstance().getExtendedState() == MAXIMIZED_BOTH) {
//					setExtendedState(NORMAL);
//				} else {
//					setExtendedState(MAXIMIZED_BOTH);
//				}
//			}
//		});

//		ImageIcon minimize = new ImageIcon(CloseTab.class
//				.getResource("/com/silikonm/ui/support/minimizeButton.png"));
//
//		ImageIcon minimize_mo = new ImageIcon(MainFrame.class
//				.getResource("/com/silikonm/ui/support/minimizeButtonMouseOver.png"));
//		
//		JButton minimizeButton = createButton(minimize, minimize_mo);
//		minimizeButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				setExtendedState(ICONIFIED);
//			}
//		});

//		buttonPanel.add(minimizeButton);
//		buttonPanel.add(maximizeButton);
//		buttonPanel.add(closeButton);

		setTitle("SilikonM");
		title = new JLabel(" SilikonM");
		title.setName("titleLable");
		//titlePanel.add(title, BorderLayout.WEST);

		//titlePanel.add(buttonPanel, BorderLayout.EAST);

		panels = new HashMap<String, JPanel>();// initialize the panel map
		frames = new HashMap<String, JFrame>();// initialize the panel map

		mainMenu = new JMenuBar();
		mainMenu.setBorderPainted(false);
		// setJMenuBar(mainMenu);

		// mnFile = new JMenu("File");
		// mnFile.setMnemonic('F');
		// addMenu(mnFile);
		//
		// mntmExit = new JMenuItem("Exit");
		// mntmExit.addActionListener(new MenuAction());
		// mnFile.add(mntmExit);

		contentPane = new JPanel();
		contentPane.setName("contentPanel");
		contentPane.setBorder(null);
		stage.add(contentPane, BorderLayout.CENTER);
		stage.add(titlePanel, BorderLayout.NORTH);
		setContentPane(stage);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel pnlStatusBar = new JPanel();
		pnlStatusBar.setBorder(null);
		pnlStatusBar.setBackground(Color.GRAY);
		contentPane.add(pnlStatusBar, BorderLayout.SOUTH);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setName("tabbedPane");
		tabbedPane.setBorder(null);
		contentPane.add(mainMenu, BorderLayout.NORTH);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		//add moving ability
		ComponentMover cm = new ComponentMover(this, titlePanel);
	}

	public static MainFrame getInstance() {
		if (mainFrame == null) {
			mainFrame = new MainFrame();
		}

		return mainFrame;
	}

	public void registerPanel(JPanel panel, String heading) {
		CloseTab closeTab = new CloseTab();
		closeTab.setTitle(heading, tabbedPane);
		closeTab.init();

		if (panels.get(panel.getClass().toString()) == null) {
			panels.put(panel.getClass().toString(), panel);
			closeTab.setPanelName(panel.getClass().toString());
			tabbedPane.add(panel);
			tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
			tabbedPane
					.setTabComponentAt(tabbedPane.getTabCount() - 1, closeTab);

		} else {
			int index = tabbedPane.indexOfComponent((JPanel) panels.get(panel
					.getClass().toString()));
			tabbedPane.setSelectedIndex(index);
		}
	}
	
	public void registerFrame(JFrame frame){
		if(frames.get(frame.getClass().toString()) == null){
			frames.put(frame.getClass().toString(), frame);
			frame.setVisible(true);
		}else{
			frames.get(frame.getClass().toString()).setVisible(true);
		}
	}

	public void unregisterPanel(String key) {
		System.out.println(key + " Unregister panel");
		panels.remove(key);
	}

	public void addMenu(PluginMenu menu) {
		mainMenu.add((JMenu) menu);
	}
	
	private JButton createButton(ImageIcon icon, ImageIcon rolloverIcon) {
		JButton button = new JButton(new ImageIcon(icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
		button.setMaximumSize(new Dimension(25,25));
		button.setMinimumSize(new Dimension(25,25));
		button.setPreferredSize(new Dimension(25,25));
		
		button.setBorderPainted(false);
		button.setBorder(null);
		button.setFocusable(false);
		button.setMargin(new Insets(0,5,0,5));
		button.setContentAreaFilled(false);
		//button.setIcon(icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
		button.setRolloverIcon(new ImageIcon(rolloverIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
		button.setPressedIcon(new ImageIcon(rolloverIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
		button.setDisabledIcon(icon);		
		return button;
	}

}
