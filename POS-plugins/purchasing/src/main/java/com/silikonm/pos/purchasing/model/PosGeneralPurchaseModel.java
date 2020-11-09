package com.silikonm.pos.purchasing.model;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.silikonm.common.dao.pos.GeneralPurchase;
import com.silikonm.common.dao.pos.Item;
import com.silikonm.common.dao.pos.PosSupplier;
import com.silikonm.common.dao.pos.Transaction;
import com.silikonm.common.dto.pos.GeneralPurchaseBean;
import com.silikonm.common.dto.pos.ItemBean;
import com.silikonm.common.dto.pos.TransactionBean;
import com.silikonm.common.factory.DAOFactory;
import com.silikonm.common.interfaces.Command;

public class PosGeneralPurchaseModel {

	private int poID = 0;

	public PosGeneralPurchaseModel() {
	}

	@SuppressWarnings("unchecked")
	public List<GeneralPurchaseBean> getAllPurchaseOrders() {
		return (List<GeneralPurchaseBean>) DAOFactory.getInstance()
				.executeAndClose(new Command() {

					@Override
					public Object execute(DAOFactory factory) {
						GeneralPurchase generalPurchase = (GeneralPurchase) factory
								.createDAO(DAOFactory.POS_GENERAL_PURCHASE);
						try {
							return (List<GeneralPurchaseBean>) generalPurchase.select();
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<ItemBean> getAvailableItems() {
		return (List<ItemBean>) DAOFactory.getInstance().executeAndClose(
				new Command() {

					@Override
					public Object execute(DAOFactory factory) {
						Item item = (Item) factory.createDAO(DAOFactory.ITEM);
						PosSupplier supplier = (PosSupplier)factory.createDAO(DAOFactory.POS_SUPPLIER);
						try {
							List<ItemBean> items=new ArrayList<ItemBean>();
							for(ItemBean bean : item.select()){
								bean.setSupplier(supplier.selectById(bean.getSupplierID()));	
								items.add(bean);
							}
							
							return items;
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
				});
	}

	public String getNewPurchaseOrderNo(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		poID = getNextPurchaseOrderID(Integer.valueOf(df.format(date))) + 1;
		StringBuilder stringBuilder = new StringBuilder("GP");
		stringBuilder.append("/");
		stringBuilder.append(df.format(date));
		stringBuilder.append("/");
		stringBuilder.append(String.valueOf(poID));
		return stringBuilder.toString();
	}

	public int getNextPurchaseOrderID(final int year) {
		return (Integer) DAOFactory.getInstance().transactionAndClose(
				new Command() {

					@Override
					public Object execute(DAOFactory factory) {
						GeneralPurchase order = (GeneralPurchase) factory
								.createDAO(DAOFactory.POS_GENERAL_PURCHASE);
						try {
							poID = order.getNextId(year);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return poID;
					}
				});
	}

	/**
	 * save purchase order
	 */
	public void savePurchaseOrder(final GeneralPurchaseBean bean,
			final List<ItemBean> poItems) {
		DAOFactory.getInstance().transactionAndClose(new Command() {

			@Override
			public Object execute(DAOFactory factory) {
				GeneralPurchase order = (GeneralPurchase) factory
						.createDAO(DAOFactory.POS_GENERAL_PURCHASE);
				Transaction transaction = (Transaction) factory
						.createDAO(DAOFactory.TRANSACTION);
				try {
					try {
						order.insert(bean);
					} catch (SQLException e) {
						if (e.getErrorCode() == 1062) {
							updatePurchaseOrder(bean, poItems);
						}
					}

					for (ItemBean itemBean : poItems) {
						TransactionBean trBean = new TransactionBean();
						if (itemBean.getTransactionID() == -1) {
							trBean.setTransactionID(transaction.getNextId());
						} else {
							trBean.setTransactionID(itemBean.getTransactionID());
						}
						trBean.setTransactionTypeID(5);
						trBean.setPoID(poID);
						trBean.setSaleID(-1);
						trBean.setItemCode(itemBean.getItemCode());
						trBean.setTransactionDate(bean.getGpDate());
						trBean.setTransactionDescription("Insert new GP item from GP : "
								+ bean.getGpNo());
						trBean.setTransactionUnitPrice(itemBean.getUnitPrice());
						trBean.setTransactionQTY(itemBean.getQty());

						try {								
							transaction.insertPurchaseOrder(trBean);
						} catch (SQLException e) {
							if (e.getErrorCode() == 1062) {
								updatePurchaseOrderItem(itemBean);
							}
						}

					}
					poID = 0;
				} catch (SQLException e) {
					factory.transactionRollback();
					e.printStackTrace();

				}
				return null;
			}
		});
	}

	/**
	 * Delete selected purchase order
	 * 
	 * @param poid
	 */
	public void deletePurchaseOrder(final int poid) {
		DAOFactory.getInstance().transactionAndClose(new Command() {

			@Override
			public Object execute(DAOFactory factory) {
				GeneralPurchase order = (GeneralPurchase) factory
						.createDAO(DAOFactory.POS_GENERAL_PURCHASE);
				Transaction transaction = (Transaction) factory
						.createDAO(DAOFactory.TRANSACTION);
				try {
					order.delete(String.valueOf(poid));
					transaction.deleteByPurchaseOrder(poid);
				} catch (Exception e) {
					factory.transactionRollback();
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	public GeneralPurchaseBean getPurchaseOrder(final int poid) {
		return (GeneralPurchaseBean) DAOFactory.getInstance().executeAndClose(
				new Command() {

					@Override
					public Object execute(DAOFactory factory) {
						GeneralPurchase order = (GeneralPurchase) factory
								.createDAO(DAOFactory.POS_GENERAL_PURCHASE);

						try {
							return order.selectGeneralPurchaseOrder(poid);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
				});
	}

	public void updatePurchaseOrder(final GeneralPurchaseBean bean,
			List<ItemBean> poItems) {
		DAOFactory.getInstance().transactionAndClose(new Command() {

			@Override
			public Object execute(DAOFactory factory) {
				GeneralPurchase order = (GeneralPurchase) factory
						.createDAO(DAOFactory.POS_GENERAL_PURCHASE);
				Transaction transaction = (Transaction) factory
						.createDAO(DAOFactory.TRANSACTION);

				try {
					order.update(bean);// update PO
				} catch (Exception e) {
					factory.transactionRollback();
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	public boolean deletePurchaseOrderItem(final int poid, final String itemCode) {
		return(Boolean)DAOFactory.getInstance().transactionAndClose(new Command() {

			@Override
			public Object execute(DAOFactory factory) {
				Transaction transaction = (Transaction) factory
						.createDAO(DAOFactory.TRANSACTION);
				try {
					return transaction.deleteByPurchaseOrderItem(poid, itemCode);
				} catch (Exception e) {
					factory.transactionRollback();
					e.printStackTrace();
				}
				return false;
			}
		});
	}
	
	public boolean deletePurchaseOrderItem(final int trID) {
		return(Boolean)DAOFactory.getInstance().transactionAndClose(new Command() {

			@Override
			public Object execute(DAOFactory factory) {
				Transaction transaction = (Transaction) factory
						.createDAO(DAOFactory.TRANSACTION);
				try {
					return transaction.delete(String.valueOf(trID));
				} catch (Exception e) {
					factory.transactionRollback();
					e.printStackTrace();
				}
				return false;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<ItemBean> getPurchaseOrderItems(final int poid) {
		return (List<ItemBean>) DAOFactory.getInstance().executeAndClose(
				new Command() {

					@Override
					public Object execute(DAOFactory factory) {
						Transaction transaction = (Transaction) factory
								.createDAO(DAOFactory.TRANSACTION);
						Item item = (Item) factory.createDAO(DAOFactory.ITEM);
						List<ItemBean> items = new ArrayList<ItemBean>();

						try {
							List<TransactionBean> transactions = transaction
									.selectByPurchaseOrder(poid);
							for (TransactionBean bean : transactions) {
								ItemBean itemBean = item.selectByItemCode(bean
										.getItemCode());
								itemBean.setQty(bean.getTransactionQTY());
								itemBean.setUnitPrice(bean
										.getTransactionUnitPrice());
								itemBean.setTransactionID(bean
										.getTransactionID());
								items.add(itemBean);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						return items;
					}
				});
	}

	public void updatePurchaseOrderItem(final ItemBean item) {
		DAOFactory.getInstance().transactionAndClose(new Command() {

			@Override
			public Object execute(DAOFactory factory) {
				Transaction transaction = (Transaction) factory
						.createDAO(DAOFactory.TRANSACTION);
				try {
					TransactionBean transactionBean = new TransactionBean();
					transactionBean.setTransactionUnitPrice(item.getUnitPrice());
					transactionBean.setTransactionQTY(item.getQty());
					transactionBean.setTransactionID(item.getTransactionID());
					transaction.update(transactionBean);
				} catch (Exception e) {
					factory.transactionRollback();
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	public int getPoID() {
		return poID;
	}

	public void setPoID(int poID) {
		this.poID = poID;
	}

}
