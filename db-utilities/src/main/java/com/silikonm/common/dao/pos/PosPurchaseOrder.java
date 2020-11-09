package com.silikonm.common.dao.pos;

import java.sql.SQLException;

import com.silikonm.common.daoimpl.pos.PosPurchaseOrderImpl;
import com.silikonm.common.dto.pos.PosPurchaseOrderBean;
import com.silikonm.common.interfaces.DAO;

public interface PosPurchaseOrder extends
		DAO<PosPurchaseOrderBean, PosPurchaseOrderImpl> {
	public int getNextId(int year) throws SQLException;

	public PosPurchaseOrderBean selectPurchaseOrder(int poid)
			throws SQLException;
}
