package com.silikonm.common.dao.pos;

import java.sql.SQLException;
import java.util.List;

import com.silikonm.common.daoimpl.pos.TransactionImpl;
import com.silikonm.common.dto.pos.TransactionBean;
import com.silikonm.common.interfaces.DAO;

/**
 * Created with IntelliJ IDEA. User: Harsha Date: 10/19/13 Time: 1:00 AM
 */
public interface Transaction extends DAO<TransactionBean, TransactionImpl> {
	public List<TransactionBean> selectByItemCode(final String itemCode)
			throws SQLException;

	public boolean insertPurchaseOrder(TransactionBean transactionBean)
			throws SQLException;
	
	public boolean insertGeneralPurchasing(TransactionBean transactionBean)
			throws SQLException;
	
	public boolean insertSalePurchasing(TransactionBean transactionBean)
			throws SQLException;

	public boolean deleteByPurchaseOrder(int poid) throws SQLException;

	public boolean deleteByPurchaseOrderItem(int poid, String itemCode)
			throws SQLException;
	

	public List<TransactionBean> selectByPurchaseOrder(int poid)
			throws SQLException;
}
