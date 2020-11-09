package com.silikonm.common.daoimpl.pos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.silikonm.common.dao.pos.Item;
import com.silikonm.common.dto.pos.ItemBean;
import com.silikonm.common.interfaces.AbstractDAO;

/**
 * Created with IntelliJ IDEA. User: Harsha Date: 10/18/13 Time: 4:45 PM
 */
public class ItemImpl extends AbstractDAO implements Item {

	@Override
	public ItemImpl getInstance() {
		return this;
	}

	@Override
	public boolean insert(ItemBean itemBean) throws SQLException {
		String sql = "INSERT INTO `item` (`item_code`,`category_id`,`item_name`, "
				+ "`item_desc`,`item_reorder_level`,`supplier_id`, `item_unit`, `item_barcode`) VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setString(1, itemBean.getItemCode());
		ps.setInt(2, itemBean.getCategoryID());
		ps.setString(3, itemBean.getItemName());
		ps.setString(4, itemBean.getItemDescription());
		ps.setDouble(5, itemBean.getReorderLevel());
		ps.setInt(6, itemBean.getSupplierID());
		//ps.setString(7, itemBean.getSinhalaName());
		ps.setString(7, itemBean.getUnit());
		ps.setString(8, itemBean.getItemBarcode());
		ps.execute();
		
		ps.close();
		
		return true;
	}

	@Override
	public boolean delete(String id) throws SQLException {
		String sql = "DELETE FROM `item` WHERE `item_code` = '" + id + "'";
		Statement st = getConnection().createStatement();
		st.executeUpdate(sql);
		st.close();
		return true;
	}

	@Override
	public boolean update(ItemBean itemBean) throws SQLException {
		String sql = "UPDATE `item` SET `item_name` = ?, `item_desc` = ?,"
				+ "`item_reorder_level` = ?, `item_unit` = ?, `item_barcode` = ?, `supplier_id` = ?, `category_id` = ? WHERE `item_code` = ?";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setString(1, itemBean.getItemName());
		ps.setString(2, itemBean.getItemDescription());
		ps.setDouble(3, itemBean.getReorderLevel());
		//ps.setString(4, itemBean.getSinhalaName());
		ps.setString(4, itemBean.getUnit());
		ps.setString(5, itemBean.getItemBarcode());
		ps.setInt(6, itemBean.getSupplierID());
		ps.setInt(7, itemBean.getCategoryID());
		ps.setString(8, itemBean.getItemCode());		
		ps.executeUpdate();
		return true;
	}

	@Override
	public List<ItemBean> select() throws SQLException {
		List<ItemBean> list = new ArrayList<ItemBean>();
		String sql = "SELECT `item_code`,`category_id`,`item_name`,`item_desc`,"
				+ "`item_reorder_level`,`supplier_id`, `item_barcode`, `item_unit` FROM `item`";
		ResultSet rs = getConnection().createStatement().executeQuery(sql);
		while (rs.next()) {
			ItemBean bean = new ItemBean(rs.getString("item_code"),
					rs.getInt("category_id"), rs.getString("item_name"),
					rs.getString("item_desc"),
					rs.getDouble("item_reorder_level"),
					rs.getInt("supplier_id"));
			bean.setSinhalaName("");
			bean.setUnit(rs.getString("item_unit"));
			bean.setItemBarcode(rs.getString("item_barcode"));
			list.add(bean);
		}
		rs.close();
		return list; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	@Override
	public int getNextId() throws SQLException {
		String sql = "SELECT COUNT(item_code) AS id FROM `item`";
		ResultSet rs = getConnection().createStatement().executeQuery(sql);
		int id = 1000;
		if (rs.next()) {
			id = id + rs.getInt("id") + 1;
		}
		rs.close();
		return id;
	}

	@Override
	public ItemBean selectByItemCode(String itemCode) throws SQLException {
		String sql = "SELECT `item_code`,`category_id`,`item_name`,`item_desc`,"
				+ "`item_reorder_level`,`supplier_id`, `item_unit`, `item_barcode` FROM `item` WHERE `item_code` = '"
				+ itemCode + "'";
		ResultSet rs = getConnection().createStatement().executeQuery(sql);
		while (rs.next()) {
			ItemBean bean = new ItemBean(rs.getString("item_code"),
					rs.getInt("category_id"), rs.getString("item_name"),
					rs.getString("item_desc"),
					rs.getDouble("item_reorder_level"),
					rs.getInt("supplier_id"));

			bean.setSinhalaName("");
			bean.setUnit(rs.getString("item_unit"));
			bean.setItemBarcode(rs.getString("item_barcode"));
			return bean;
		}
		rs.close();
		return null;
	}

	@Override
	public int getMaximumItemCount(String itemPrifix) throws SQLException {
		String sql = "SELECT COUNT(`item_code`) AS itemCount FROM `item` WHERE `item_code` LIKE '"
				+ itemPrifix + "%'";
		Statement st = getConnection().createStatement();
		ResultSet rs = st.executeQuery(sql);
		int itemCount = 1;
		if (rs.next()) {
			itemCount = rs.getInt("itemCount");
		}
		st.close();
		rs.close();
		return itemCount;
	}
}
