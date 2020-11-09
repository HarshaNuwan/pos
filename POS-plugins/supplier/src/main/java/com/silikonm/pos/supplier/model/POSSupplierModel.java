package com.silikonm.pos.supplier.model;

import java.sql.SQLException;
import java.util.List;

import com.silikonm.common.dao.pos.PosSupplier;
import com.silikonm.common.daoimpl.pos.PosSupplierImpl;
import com.silikonm.common.dto.pos.PosSupplierBean;
import com.silikonm.common.factory.DAOFactory;
import com.silikonm.common.interfaces.Command;
import com.silikonm.pos.supplier.tablemodel.PosSuppliersTableModel;
import com.silikonm.pos.supplier.ui.POSSuppliersView;
import com.silikonm.utilities.Alert;

public class POSSupplierModel {

	private PosSuppliersTableModel posSuppliersTableModel;

	public POSSupplierModel(POSSuppliersView view) {
		posSuppliersTableModel = new PosSuppliersTableModel(null, this, view);
	}

	public PosSuppliersTableModel getPosSuppliersTableModel() {
		return posSuppliersTableModel;
	}

	public void getAllSuppliers() {
		DAOFactory.getInstance().executeAndClose(new Command() {

			@Override
			public Object execute(DAOFactory factory) {
				posSuppliersTableModel.resetTable();//reset supplier details table.
				PosSupplier posSupplier = (PosSupplier) factory
						.createDAO(DAOFactory.POS_SUPPLIER);

				try {
					List<PosSupplierBean> suppliers = posSupplier.select();
					for (PosSupplierBean sBean : suppliers) {
						posSuppliersTableModel.insertRow(new Object[] {
								sBean.getSupplierID(), sBean,
								sBean.getSupplierAddress(),
								sBean.getSupplierPhone() });
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	public void saveSupplier(final PosSupplierBean posSupplierBean) {
		DAOFactory.getInstance().transactionAndClose(new Command() {

			@Override
			public Object execute(DAOFactory factory) {
				PosSupplierImpl supplierImpl = (PosSupplierImpl) factory
						.createDAO(DAOFactory.POS_SUPPLIER);
				try {
					supplierImpl.insert(posSupplierBean);
				} catch (SQLException e) {
					//when duplicate, update the supplier details
					if(e.getErrorCode() == 1062){
						updateSupplier(posSupplierBean);
					}
					e.printStackTrace();
				}
				return null;
			}
		});
	}
	
	public void updateSupplier(final PosSupplierBean posSupplierBean){
		DAOFactory.getInstance().transactionAndClose(new Command() {
			
			@Override
			public Object execute(DAOFactory factory) {
				PosSupplierImpl impl = (PosSupplierImpl)factory.createDAO(DAOFactory.POS_SUPPLIER);
				try {
					impl.update(posSupplierBean);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
		
	}
	
	public void deleteSupplier(final int supplierID){
		DAOFactory.getInstance().transactionAndClose(new Command() {
			
			@Override
			public Object execute(DAOFactory factory) {
				PosSupplierImpl impl = (PosSupplierImpl)factory.createDAO(DAOFactory.POS_SUPPLIER);
				try {
					impl.delete(String.valueOf(supplierID));
				} catch (SQLException e) {
					if(e.getErrorCode()==1451){
						Alert.message("You cannot delete this supplier\n"
								+ "because it is used for already registered items!");
					}else{
						e.printStackTrace();
					}
				}
				return null;
			}
		});
	}

	public int getNextSupplierId() {
		return (Integer) DAOFactory.getInstance().executeAndClose(
				new Command() {

					@Override
					public Object execute(DAOFactory factory) {
						PosSupplierImpl posSupplierImpl = (PosSupplierImpl) factory
								.createDAO(DAOFactory.POS_SUPPLIER);
						try {
							return posSupplierImpl.getNextId();
						} catch (Exception e) {
							e.printStackTrace();
						}
						return -1;
					}
				});
	}
}
