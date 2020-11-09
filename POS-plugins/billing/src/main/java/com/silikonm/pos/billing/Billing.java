package com.silikonm.pos.billing;



import com.silikonm.commons.PluginBase;
import com.silikonm.commons.PluginConnector;
import com.silikonm.commons.PluginMenu;
import com.silikonm.commons.PluginMenuItem;
import com.silikonm.commons.UIType;
import com.silikonm.pos.billing.controller.POSController;
import com.silikonm.pos.billing.model.POSModel;
import com.silikonm.pos.billing.ui.BillingPanel;

public class Billing implements PluginConnector, PluginBase{

	private String pluginKey = "POS_BILLING";
	private BillingMenuItem billingMenuItem = null;
	private String pluginName = null;

//	public static void main(String [] args){
//		
//		SilikonMSkin mSkin = new SilikonMSkin();
//		try {
//			UIManager.setLookAndFeel(mSkin.getLookAndFeel());
////			UIManager.put("TableHeader.background", Color.RED);
////			UIManager.put("Table.selectionBackground", Color.blue);
////			UIManager.put("Table.gridColor", Color.ORANGE);
////			
//			//UIManager.put("Table.rowHeight", new Integer(40));
//		} catch (Exception e) {
//			try {
//				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			} catch (ClassNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (InstantiationException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (IllegalAccessException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();	
//			} catch (UnsupportedLookAndFeelException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		}		
//		JFrame frame = new JFrame();	
//		frame.setUndecorated(true);
//		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(800, 600);
//
//		BillingPanel panel = new BillingPanel();
//		frame.setContentPane(panel);
//		
//		frame.setVisible(true);
//		
//		
//		
//		
//	}
	
	public Billing() {
		this.pluginName = "POS Billing";
		billingMenuItem = new BillingMenuItem("POS Billing", pluginKey);
	}

	public String getPluginKey() {
		return this.pluginKey;
	}

	public PluginMenu getPluginMenu() {
		return null;
	}

	public PluginMenuItem getPluginMenuItem() {		
		return this.billingMenuItem;
	}

	public PluginMenuItem[] getPluginMenuItems() {
		// TODO Auto-generated method stub
		return null;
	}

	public UIType getUIType() {		
		BillingPanel panel = new BillingPanel();
		POSModel posModel = new POSModel();
		POSController controller = new POSController(panel, posModel);
		return panel;
	}

	public Boolean isActionPlugin() {
		// TODO Auto-generated method stub
		return false;
	}

	public void executeAction() {
		// TODO Auto-generated method stub
		
	}

	public String getPluginName() {
		return this.pluginName;
	}

	public PluginBase getPlugin() {
		// TODO Auto-generated method stub
		return this;
	}
}
