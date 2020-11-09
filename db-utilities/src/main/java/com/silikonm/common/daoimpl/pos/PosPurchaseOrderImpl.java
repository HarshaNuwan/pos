package com.silikonm.common.daoimpl.pos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.silikonm.common.dao.pos.PosPurchaseOrder;
import com.silikonm.common.dto.pos.PosPurchaseOrderBean;
import com.silikonm.common.interfaces.AbstractDAO;

public class PosPurchaseOrderImpl extends AbstractDAO implements
		PosPurchaseOrder {

	@Override
	public PosPurchaseOrderImpl getInstance() {
		return this;
	}

	@Override
	public boolean insert(PosPurchaseOrderBean t) throws SQLException {
		String sql = "INSERT INTO `purchase_order` (`po_id`, `po_no`, `po_ordered_date`, `po_received_date`) VALUES(?,?,?,?)";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, t.getPoId());
		ps.setString(2, t.getPoNo());
		ps.setDate(3, new java.sql.Date(t.getOrderedDate().getTime()));
		ps.setDate(4, new java.sql.Date(t.getReceivedDate().getTime()));
		ps.execute();
		ps.close();
		return false;
	}

	@Override
	public boolean delete(String id) throws SQLException {
		String sql = "DELETE FROM `purchase_order` WHERE `po_id` = " + id;
		Statement st = getConnection().createStatement();
		st.executeUpdate(sql);
		st.close();
		return false;
	}

	@Override
	public boolean update(PosPurchaseOrderBean t) throws SQLException {
		String sql = "UPDATE `purchase_order` SET `po_ordered_date` = ? WHERE `po_id` = ?";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setDate(1, new java.sql.Date(t.getOrderedDate().getTime()));
		ps.setInt(2, t.getPoId());
		ps.executeUpdate();
		return false;
	}

	@Override
	public List<PosPurchaseOrderBean> select() throws SQLException {
		List<PosPurchaseOrderBean> pos = new ArrayList<PosPurchaseOrderBean>();
		String sql = "SELECT `po_id`, `po_no`, `po_ordered_date`, `po_received_date` FROM `purchase_order` ORDER BY `po_ordered_date` DESC, `po_id` DESC";
		Statement st = getConnection().createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			PosPurchaseOrderBean bean = new PosPurchaseOrderBean();
			bean.setPoId(rs.getInt("po_id"));
			bean.setPoNo(rs.getString("po_no"));
			bean.setOrderedDate(rs.getDate("po_ordered_date"));
			bean.setReceivedDate(rs.getDate("po_received_date"));
			pos.add(bean);
		}
		st.close();
		rs.close();
		return pos;
	}

	@Override
	public int getNextId() throws SQLException {
		String sql = "SELECT MAX(`po_id`) AS id FROM `purchase_order`";
		Statement statement = getConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);
		int id = 1;
		if (rs.next()) {
			id = rs.getInt("id");
		}
		return id;
	}

	@Override
	public int getNextId(int year) throws SQLException {
		String sql = "SELECT MAX(`po_id`) AS id FROM `purchase_order` WHERE YEAR(`po_ordered_date`) = "
				+ year;
		Statement statement = getConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);
		int id = 1;
		if (rs.next()) {
			id = rs.getInt("id");
		}
		return id;
	}

	@Override
	public PosPurchaseOrderBean selectPurchaseOrder(int poid)
			throws SQLException {
		PosPurchaseOrderBean po = null;
		String sql = "SELECT `po_id`, `po_no`, `po_ordered_date`, `po_received_date` FROM `purchase_order` WHERE `po_id` = "
				+ poid;
		Statement st = getConnection().createStatement();
		ResultSet rs = st.executeQuery(sql);
		if (rs.next()) {
			po = new PosPurchaseOrderBean();
			po.setPoId(rs.getInt("po_id"));
			po.setPoNo(rs.getString("po_no"));
			po.setOrderedDate(rs.getDate("po_ordered_date"));
			po.setReceivedDate(rs.getDate("po_received_date"));
		}
		st.close();
		rs.close();
		return po;
	}

}
