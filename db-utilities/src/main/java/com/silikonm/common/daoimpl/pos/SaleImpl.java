package com.silikonm.common.daoimpl.pos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.silikonm.common.dao.pos.Sale;
import com.silikonm.common.dto.pos.SaleBean;
import com.silikonm.common.interfaces.AbstractDAO;

public class SaleImpl extends AbstractDAO implements Sale{

	@Override
	public SaleImpl getInstance() {
		return this;
	}

	@Override
	public boolean insert(SaleBean t) throws SQLException {
		String sql = "INSERT INTO `sale`(`sale_id`, `customer_id`, `sale_type_id`, `sale_bill_no`) VALUES(?,?,?,?)";
		
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, t.getSaleID());
		ps.setInt(2, t.getCustomerId());
		ps.setInt(3, t.getSaleTypeId());
		ps.setString(4, t.getSaleBillNo());
		ps.execute();
		
		ps.close();
		return false;
	}

	@Override
	public boolean delete(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(SaleBean t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<SaleBean> select() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNextId() throws SQLException {
		String sql = "SELECT MAX(`sale_id`) AS id FROM `sale`";
		Statement statement = getConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);
		int id = 1;
		if (rs.next()) {
			id = rs.getInt("id") + 1;
		}

		return id;
	}

}
