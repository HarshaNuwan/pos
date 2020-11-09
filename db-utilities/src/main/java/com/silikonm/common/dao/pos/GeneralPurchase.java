package com.silikonm.common.dao.pos;

import java.sql.SQLException;

import com.silikonm.common.daoimpl.pos.GeneralPurchaseImpl;
import com.silikonm.common.dto.pos.GeneralPurchaseBean;
import com.silikonm.common.interfaces.DAO;

public interface GeneralPurchase extends DAO<GeneralPurchaseBean, GeneralPurchaseImpl>{
	public GeneralPurchaseBean selectGeneralPurchaseOrder(int poid)
			throws SQLException;
	public int getNextId(int year) throws SQLException;
}
