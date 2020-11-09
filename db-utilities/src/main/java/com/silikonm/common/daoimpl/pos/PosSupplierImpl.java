package com.silikonm.common.daoimpl.pos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.silikonm.common.dao.pos.PosSupplier;
import com.silikonm.common.dto.pos.PosSupplierBean;
import com.silikonm.common.interfaces.AbstractDAO;

public class PosSupplierImpl extends AbstractDAO implements PosSupplier {

	@Override
	public PosSupplierImpl getInstance() {
		return this;
	}

	@Override
	public boolean insert(PosSupplierBean t) throws SQLException {
		String sql = "INSERT INTO `supplier`(`supplier_id`,`supplier_code`, `supplier_name`, `supplier_address`, `supplier_phone`) "
				+ "VALUES(?,?,?,?,?)";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, t.getSupplierID());
		ps.setString(2, t.getSupplierCode());
		ps.setString(3, t.getSupplierName());
		ps.setString(4, t.getSupplierAddress());
		ps.setString(5, t.getSupplierPhone());

		boolean st = ps.execute();
		ps.close();
		return st;
	}

	@Override
	public boolean delete(String id) throws SQLException {
		String sql = "DELETE FROM `supplier` WHERE `supplier_id` = ?";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, Integer.valueOf(id));
		ps.executeUpdate();
		ps.close();
		return true;
	}

	@Override
	public boolean update(PosSupplierBean t) throws SQLException {
		String sql = "UPDATE `supplier` SET `supplier_name` = ?, `supplier_address` = ?, `supplier_phone`=? WHERE"
				+ "`supplier_id` = ? ";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setString(1, t.getSupplierName());
		ps.setString(2, t.getSupplierAddress());
		ps.setString(3, t.getSupplierPhone());
		ps.setInt(4, t.getSupplierID());
		ps.executeUpdate();
		ps.close();
		return true;
	}

	@Override
	public List<PosSupplierBean> select() throws SQLException {
		String sql = "SELECT `supplier_id`, `supplier_name`, `supplier_address`, `supplier_phone` FROM `supplier`";

		ResultSet rs = getConnection().createStatement().executeQuery(sql);
		List<PosSupplierBean> suppliers = new ArrayList<PosSupplierBean>();
		while (rs.next()) {
			PosSupplierBean supplier = new PosSupplierBean();
			supplier.setSupplierID(rs.getInt("supplier_id"));
			supplier.setSupplierName(rs.getString("supplier_name"));
			supplier.setSupplierAddress(rs.getString("supplier_address"));
			supplier.setSupplierPhone(rs.getString("supplier_phone"));
			suppliers.add(supplier);
		}
		rs.close();
		return suppliers;
	}

	@Override
	public int getNextId() throws SQLException {
		String sql = "SELECT MAX(`supplier_id`) AS id FROM supplier";
		ResultSet rs = getConnection().createStatement().executeQuery(sql);
		int id = 1000;
		if (rs.next()) {
			if (rs.getInt("id") == 0) {
				id = id + rs.getInt("id") + 1;
			} else {
				id = rs.getInt("id") + 1;
			}

		}
		rs.close();
		return id;
	}

	@Override
	public PosSupplierBean selectById(int supplierID) throws SQLException {
		String sql = "SELECT `supplier_id`, `supplier_name`, `supplier_address`, `supplier_phone` FROM `supplier` "
				+ "WHERE `supplier_id` = " + supplierID;

		ResultSet rs = getConnection().createStatement().executeQuery(sql);
		PosSupplierBean supplier = null;
		if (rs.next()) {
			supplier = new PosSupplierBean();
			supplier.setSupplierID(rs.getInt("supplier_id"));
			supplier.setSupplierName(rs.getString("supplier_name"));
			supplier.setSupplierAddress(rs.getString("supplier_address"));
			supplier.setSupplierPhone(rs.getString("supplier_phone"));

		}
		rs.close();
		return supplier;
	}

}
