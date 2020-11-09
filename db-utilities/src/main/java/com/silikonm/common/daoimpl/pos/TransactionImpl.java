package com.silikonm.common.daoimpl.pos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.silikonm.common.dao.pos.Transaction;
import com.silikonm.common.dto.pos.TransactionBean;
import com.silikonm.common.interfaces.AbstractDAO;

/**
 * Created with IntelliJ IDEA. User: Harsha Date: 10/19/13 Time: 1:00 AM
 */
public class TransactionImpl extends AbstractDAO implements Transaction {
	@Override
	public TransactionImpl getInstance() {
		return this;
	}

	@Override
	public boolean insert(TransactionBean transactionBean) throws SQLException {
		String sql = "INSERT INTO `inventory_transactions`(`trans_id`,  `trans_type_id`, `po_id`, `sale_id`, ,`gp_id`, `item_code`, `trans_date`, "
				+ "`trans_description`, `trans_unit_price`, `trans_qty`) VALUES(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, transactionBean.getTransactionID());
		ps.setInt(2, transactionBean.getTransactionTypeID());
		ps.setInt(3, transactionBean.getPoID());
		ps.setInt(4, transactionBean.getSaleID());
		ps.setInt(5, transactionBean.getGpID());
		ps.setString(6, transactionBean.getItemCode());
		ps.setDate(7, new java.sql.Date(transactionBean.getTransactionDate()
				.getTime()));
		ps.setString(8, transactionBean.getTransactionDescription());
		ps.setBigDecimal(9, transactionBean.getTransactionUnitPrice());
		ps.setBigDecimal(10, transactionBean.getTransactionQTY());
		ps.execute();
		ps.close();
		return false;
	}

	@Override
	public boolean delete(String id) throws SQLException {
		String sql = "DELETE FROM `inventory_transactions` WHERE `trans_id` = " + id;
		Statement st = getConnection().createStatement();
		int count = st.executeUpdate(sql);
		st.close();
		return count > 0 ? true : false;
	}

	@Override
	public boolean update(TransactionBean transactionBean) throws SQLException {
		String sql = "UPDATE `inventory_transactions` SET `trans_unit_price` = ?, `trans_qty`= ? WHERE `trans_id` = ?";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setBigDecimal(1, transactionBean.getTransactionUnitPrice());
		ps.setBigDecimal(2, transactionBean.getTransactionQTY());
		ps.setInt(3, transactionBean.getTransactionID());
		ps.executeUpdate();
		return false;
	}

	@Override
	public List<TransactionBean> select() throws SQLException {
		List<TransactionBean> list = new ArrayList<TransactionBean>();
		String sql = "SELECT  `trans_id`,  `trans_type_id`, `po_id`, `sale_id`, `gp_id`, `item_code`, `trans_date`, "
				+ "`trans_description`, `trans_unit_price`, `trans_qty` FROM `inventory_transactions`";
		ResultSet rs = getConnection().createStatement().executeQuery(sql);
		while (rs.next()) {
			TransactionBean tb = new TransactionBean(rs.getInt("trans_id"), rs
					.getInt("trans_type_id"), rs.getInt("po_id"), -1, rs
					.getInt("sale_id"), rs.getString("item_code"), rs
					.getDate("trans_date"), rs.getString("trans_description"),
					rs.getBigDecimal("trans_unit_price"), rs
							.getBigDecimal("trans_qty"));
			
			tb.setGpID(rs.getInt("gp_id"));
			list.add(tb);
		}
		rs.close();
		return list;
	}

	@Override
	public int getNextId() throws SQLException {
		String sql = "SELECT MAX(`trans_id`) AS id FROM `inventory_transactions`";
		Statement statement = getConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);
		int id = 1;
		if (rs.next()) {
			id = rs.getInt("id") + 1;
		}

		return id;
	}

	@Override
	public List<TransactionBean> selectByItemCode(String itemCode)
			throws SQLException {
		List<TransactionBean> list = new ArrayList<TransactionBean>();
		String sql = "SELECT  `trans_id`,  `trans_type_id`, `po_id`, `sale_id`, `gp_id`, `item_code`, `trans_date`, "
				+ "`trans_description`, `trans_unit_price`, `trans_qty` FROM `inventory_transactions` WHERE `item_code` = "
				+ "'" + itemCode + "' GROUP BY `trans_unit_price`";
		System.out.println(sql);
		ResultSet rs = getConnection().createStatement().executeQuery(sql);
		while (rs.next()) {
			TransactionBean tb = new TransactionBean(rs.getInt("trans_id"), rs
					.getInt("trans_type_id"), rs.getInt("po_id"), -1, rs
					.getInt("sale_id"), rs.getString("item_code"), rs
					.getDate("trans_date"), rs.getString("trans_description"),
					rs.getBigDecimal("trans_unit_price"), rs
							.getBigDecimal("trans_qty"));
			
			tb.setGpID(rs.getInt("gp_id"));
			list.add(tb);
		}
		rs.close();
		return list;
	}

	@Override
	public boolean insertPurchaseOrder(TransactionBean transactionBean)
			throws SQLException {
		String sql = "INSERT INTO `inventory_transactions`(`trans_id`,  `trans_type_id`, `po_id`, `user_id`,`item_code`, `trans_date`, "
				+ "`trans_description`, `trans_unit_price`, `trans_qty`) VALUES(?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement ps = getConnection().prepareStatement(sql);
		
		ps.setInt(1, transactionBean.getTransactionID());
		ps.setInt(2, transactionBean.getTransactionTypeID());
		ps.setInt(3, transactionBean.getPoID());
		ps.setInt(4, 1);
		ps.setString(5, transactionBean.getItemCode());
		ps.setDate(6, new java.sql.Date(transactionBean.getTransactionDate()
				.getTime()));
		ps.setString(7, transactionBean.getTransactionDescription());
		ps.setBigDecimal(8, transactionBean.getTransactionUnitPrice());
		ps.setBigDecimal(9, transactionBean.getTransactionQTY());
		System.out.println(ps.toString());
		ps.execute();
		ps.close();
		return false;
	}
	
	@Override
	public boolean insertGeneralPurchasing(TransactionBean transactionBean)
			throws SQLException {
		String sql = "INSERT INTO `inventory_transactions`(`trans_id`,  `trans_type_id`, `gp_id`, `user_id`,`item_code`, `trans_date`, "
				+ "`trans_description`, `trans_unit_price`, `trans_qty`) VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, transactionBean.getTransactionID());
		ps.setInt(2, transactionBean.getTransactionTypeID());
		ps.setInt(3, transactionBean.getGpID());
		ps.setInt(4, 1);
		ps.setString(5, transactionBean.getItemCode());
		ps.setDate(6, new java.sql.Date(transactionBean.getTransactionDate()
				.getTime()));
		ps.setString(7, transactionBean.getTransactionDescription());
		ps.setBigDecimal(8, transactionBean.getTransactionUnitPrice());
		ps.setBigDecimal(9, transactionBean.getTransactionQTY());
		ps.execute();
		ps.close();
		return false;
	}

	@Override
	public boolean deleteByPurchaseOrder(int poid) throws SQLException {
		String sql = "DELETE FROM `inventory_transactions` WHERE `po_id` = "
				+ poid;
		Statement st = getConnection().createStatement();
		st.executeUpdate(sql);
		st.close();
		return false;
	}

	@Override
	public boolean deleteByPurchaseOrderItem(int poid, String itemCode)
			throws SQLException {
		String sql = "DELETE FROM `inventory_transactions` WHERE `po_id` = "
				+ poid + " AND `item_code` = '" + itemCode + "'";
		Statement st = getConnection().createStatement();

		int count = st.executeUpdate(sql);
		st.close();
		return count > 0 ? true : false;
	}

	@Override
	public List<TransactionBean> selectByPurchaseOrder(int poid)
			throws SQLException {
		List<TransactionBean> list = new ArrayList<TransactionBean>();
		String sql = "SELECT  `trans_id`,  `trans_type_id`, `po_id`, `sale_id`, `item_code`, `trans_date`, "
				+ "`trans_description`, `trans_unit_price`, `trans_qty` FROM `inventory_transactions` WHERE `po_id` = "
				+ poid;
		ResultSet rs = getConnection().createStatement().executeQuery(sql);
		while (rs.next()) {
			list.add(new TransactionBean(rs.getInt("trans_id"), rs
					.getInt("trans_type_id"), rs.getInt("po_id"), -1, rs
					.getInt("sale_id"), rs.getString("item_code"), rs
					.getDate("trans_date"), rs.getString("trans_description"),
					rs.getBigDecimal("trans_unit_price"), rs
							.getBigDecimal("trans_qty")));
		}
		rs.close();
		return list;
	}

	@Override
	public boolean insertSalePurchasing(TransactionBean transactionBean)
			throws SQLException {
		String sql = "INSERT INTO `inventory_transactions`(`trans_id`,  `trans_type_id`, `sale_id`, `user_id`,`item_code`, `trans_date`, "
				+ "`trans_description`, `trans_unit_price`, `trans_qty`, `trans_discount`) VALUES(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, transactionBean.getTransactionID());
		ps.setInt(2, transactionBean.getTransactionTypeID());
		ps.setInt(3, transactionBean.getSaleID());
		ps.setInt(4, 1);
		ps.setString(5, transactionBean.getItemCode());
		ps.setDate(6, new java.sql.Date(transactionBean.getTransactionDate()
				.getTime()));
		ps.setString(7, transactionBean.getTransactionDescription());
		ps.setBigDecimal(8, transactionBean.getTransactionUnitPrice());
		ps.setBigDecimal(9, transactionBean.getTransactionQTY());
		ps.setBigDecimal(10, transactionBean.getTransactionDiscount());
		ps.execute();
		
		ps.close();
		return false;
	}
}
