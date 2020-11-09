package com.silikonm.common.dao.pos;

import java.sql.SQLException;
import java.util.List;

import com.silikonm.common.daoimpl.pos.PosSupplierImpl;
import com.silikonm.common.dto.pos.PosSupplierBean;
import com.silikonm.common.interfaces.DAO;

public interface PosSupplier extends DAO<PosSupplierBean, PosSupplierImpl> {
	public PosSupplierBean selectById(int supplierID) throws SQLException;
}
