package com.silikonm.common.daoimpl.pos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.silikonm.common.dao.pos.GeneralPurchase;
import com.silikonm.common.dto.pos.GeneralPurchaseBean;
import com.silikonm.common.interfaces.AbstractDAO;

public class GeneralPurchaseImpl extends AbstractDAO implements GeneralPurchase{

	@Override
	public GeneralPurchaseImpl getInstance() {
		return this;
	}

	@Override
	public boolean insert(GeneralPurchaseBean t) throws SQLException {
		String sql = "INSERT INTO `general_purchasing` (`gp_id`, `gp_no`, `gp_date`) VALUES(?,?,?)";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, t.getGpId());
		ps.setString(2, t.getGpNo());
		ps.setDate(3, new java.sql.Date(t.getGpDate().getTime()));		
		ps.execute();
		ps.close();
		return false;
	}

	@Override
	public boolean delete(String id) throws SQLException {
		String sql = "DELETE FROM `general_purchasing` WHERE `gp_id` = " + id;
		Statement st = getConnection().createStatement();
		st.executeUpdate(sql);
		st.close();
		return false;
	}

	@Override
	public boolean update(GeneralPurchaseBean t) throws SQLException {
		String sql = "UPDATE `general_purchasing` SET `gp_date` = ? WHERE `gp_id` = ?";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setDate(1, new java.sql.Date(t.getGpDate().getTime()));
		ps.setInt(2, t.getGpId());
		ps.executeUpdate();
		return false;
	}

	@Override
	public List<GeneralPurchaseBean> select() throws SQLException {
		List<GeneralPurchaseBean> pos = new ArrayList<GeneralPurchaseBean>();
		String sql = "SELECT `gp_id`, `gp_no`, `gp_date` FROM `general_purchasing` ORDER BY `gp_date` DESC, `gp_id` DESC";
		Statement st = getConnection().createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			GeneralPurchaseBean bean = new GeneralPurchaseBean();
			bean.setGpId(rs.getInt("gp_id"));
			bean.setGpNo(rs.getString("gp_no"));
			bean.setGpDate(rs.getDate("gp_date"));			
			pos.add(bean);
		}
		st.close();
		rs.close();
		return pos;
	}

	@Override
	public int getNextId() throws SQLException {
		String sql = "SELECT MAX(`gp_id`) AS id FROM `general_purchasing`";
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
		String sql = "SELECT MAX(`gp_id`) AS id FROM `general_purchasing` WHERE YEAR(`gp_date`) = "
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
	public GeneralPurchaseBean selectGeneralPurchaseOrder(int gpId)
			throws SQLException {
		GeneralPurchaseBean gp = null;
		String sql = "SELECT `gp_id`, `gp_no`, `gp_date` FROM `general_purchasing` WHERE `gp_id` = "
				+ gpId;
		Statement st = getConnection().createStatement();
		ResultSet rs = st.executeQuery(sql);
		if (rs.next()) {
			gp = new GeneralPurchaseBean();
			gp.setGpId(rs.getInt("gp_id"));
			gp.setGpNo(rs.getString("gp_no"));
			gp.setGpDate(rs.getDate("gp_date"));
			
		}
		st.close();
		rs.close();
		return gp;
	}

}
