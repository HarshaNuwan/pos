package com.silikonm.pos.billing.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.silikonm.common.dao.pos.Item;
import com.silikonm.common.dao.pos.Sale;
import com.silikonm.common.dao.pos.Transaction;
import com.silikonm.common.dto.pos.ItemBean;
import com.silikonm.common.dto.pos.SaleBean;
import com.silikonm.common.dto.pos.TransactionBean;
import com.silikonm.common.factory.DAOFactory;
import com.silikonm.common.interfaces.Command;
import com.silikonm.utilities.Alert;

/**
 * Created with IntelliJ IDEA. User: Harsha Date: 10/19/13 Time: 12:32 AM
 */
public class POSModel {

	public POSModel() {

	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getItems() {
		return (List<Object[]>) DAOFactory.getInstance().executeAndClose(
				new Command() {
					@Override
					public Object execute(DAOFactory factory) {
						Item item = (Item) factory.createDAO(DAOFactory.ITEM);
						List<Object[]> rows = new ArrayList<Object[]>();
						try {
							List<ItemBean> list = item.select();
							for (ItemBean itemBean : list) {
								rows.add(new Object[] { itemBean.getItemCode(),
										itemBean, itemBean.getItemBarcode() });
							}
						} catch (Exception e) {
							e.printStackTrace();
							Alert.error(e.getLocalizedMessage());
						}
						return rows;
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getStocks(final String itemCode) {
		return (List<Object[]>) DAOFactory.getInstance().executeAndClose(
				new Command() {
					@Override
					public Object execute(DAOFactory factory) {
						Transaction transaction = (Transaction) factory
								.createDAO(DAOFactory.TRANSACTION);
						Item item = (Item) factory.createDAO(DAOFactory.ITEM);
						List<Object[]> rows = new ArrayList<Object[]>();
						try {
							List<TransactionBean> list = transaction
									.selectByItemCode(itemCode);
							for (TransactionBean transactionBean : list) {
								ItemBean itemBean = item
										.selectByItemCode(transactionBean
												.getItemCode());
								rows.add(new Object[] {
										transactionBean,
										transactionBean.getItemCode(),
										itemBean.getItemName(),
										transactionBean
												.getTransactionUnitPrice() });
							}
						} catch (Exception e) {
							e.printStackTrace();
							Alert.error(e.getLocalizedMessage());
						}
						return rows;
					}
				});
	}

	public ItemBean getItemBean(final String itemCode) {
		return (ItemBean) DAOFactory.getInstance().executeAndClose(
				new Command() {

					@Override
					public Object execute(DAOFactory factory) {
						Item item = (Item) factory.createDAO(DAOFactory.ITEM);
						try {
							return item.selectByItemCode(itemCode);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
				});
	}

	public int saveTransaction(final List<Object[]> saleItems) {
		return(Integer)DAOFactory.getInstance().transactionAndClose(new Command() {
			
			@Override
			public Object execute(DAOFactory factory) {
				int id = -1;
				
				Transaction transaction = (Transaction)factory.createDAO(DAOFactory.TRANSACTION);
				Sale sale = (Sale)factory.createDAO(DAOFactory.SALE);
				try {
					SaleBean bean = new SaleBean();
					bean.setSaleID(sale.getNextId());
					bean.setSaleTypeId(1);
					bean.setCustomerId(-1);
					bean.setSaleBillNo("0000");
					sale.insert(bean);
					
					for(Object[] row : saleItems){
						
						TransactionBean transactionBean = new TransactionBean();
						transactionBean.setTransactionID(transaction.getNextId());
						id = bean.getSaleID();
						transactionBean.setTransactionTypeID(1);//transaction type
						transactionBean.setUserId(000);
						transactionBean.setSaleID(bean.getSaleID());
						transactionBean.setItemCode(row[0].toString());
						transactionBean.setTransactionDate(new Date());
						transactionBean.setTransactionDescription("insert sale item from sale : " + bean.getSaleID());
						transactionBean.setTransactionUnitPrice(new BigDecimal(row[4].toString()));
						transactionBean.setTransactionQTY(new BigDecimal(row[2].toString()).negate());
						transactionBean.setTransactionDiscount(new BigDecimal(row[5].toString()));
						
						transaction.insertSalePurchasing(transactionBean);
					}
					
				} catch (Exception e) {
					factory.transactionRollback();
					e.printStackTrace();					
				}
				return id;
			}
		});
		
	}
}
