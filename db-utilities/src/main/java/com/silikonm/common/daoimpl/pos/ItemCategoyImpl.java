package com.silikonm.common.daoimpl.pos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.silikonm.common.dao.pos.ItemCategory;
import com.silikonm.common.dto.pos.ItemCategoryBean;
import com.silikonm.common.interfaces.AbstractDAO;

/**
 * Created with IntelliJ IDEA. User: Harsha Date: 10/18/13 Time: 3:23 PM
 */
public class ItemCategoyImpl extends AbstractDAO implements ItemCategory {
	@Override
	public ItemCategoyImpl getInstance() {
		return this;
	}

	@Override
	public boolean insert(ItemCategoryBean itemCategoryBean)
			throws SQLException {
		String sql = "INSERT INTO `item_category` (`category_id`,`category_code`,`category_name` ) VALUES(?,?,?)";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, itemCategoryBean.getCategoryID());
		ps.setString(2, itemCategoryBean.getCategoryCode());
		ps.setString(3, itemCategoryBean.getCategoryName());
		ps.execute();
		ps.close();
		return true;
	}

	@Override
	public boolean delete(String id) throws SQLException {
		String sql = "DELETE FROM `item_category` WHERE `category_id` = ?";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, Integer.valueOf(id));
		ps.executeUpdate();
		
		ps.close();
		return true;
	}

	@Override
	public boolean update(ItemCategoryBean itemCategoryBean)
			throws SQLException {
		String sql = "UPDATE `item_category` SET `category_name` = ?, `category_code` = ? WHERE `category_id` = ?";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setString(1, itemCategoryBean.getCategoryName());
		ps.setString(2, itemCategoryBean.getCategoryCode());
		ps.setInt(3, itemCategoryBean.getCategoryID());
		ps.executeUpdate();
		ps.close();
		return true;
	}

	@Override
	public List<ItemCategoryBean> select() throws SQLException {
		String sql = "SELECT `category_id`,`category_name`, `category_code` FROM `item_category`";
		ResultSet rs = getConnection().createStatement().executeQuery(sql);
		List<ItemCategoryBean> list = new ArrayList<ItemCategoryBean>();
		while (rs.next()) {
			list.add(new ItemCategoryBean(rs.getInt("category_id"), rs
					.getString("category_code"), rs.getString("category_name")));
		}
		return list;
	}

	@Override
	public int getNextId() throws SQLException {
		String sql = "SELECT MAX(category_id) AS id FROM `item_category`";
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
	public ItemCategoryBean selectByCategory(int categoryId)
			throws SQLException {
		String sql = "SELECT `category_id`,`category_name`, `category_code` FROM `item_category` WHERE `category_id` = "
				+ categoryId;
		
		ResultSet rs = getConnection().createStatement().executeQuery(sql);
		ItemCategoryBean bean = null;
		if (rs.next()) {
			bean = new ItemCategoryBean(rs.getInt("category_id"),
					rs.getString("category_code"),
					rs.getString("category_name"));
		}
		rs.close();
		return bean;
	}
}
