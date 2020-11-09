package com.silikonm.pos.item.model;

import java.sql.SQLException;
import java.util.List;

import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.silikonm.common.dao.pos.Item;
import com.silikonm.common.dao.pos.ItemCategory;
import com.silikonm.common.dao.pos.PosSupplier;
import com.silikonm.common.dto.pos.ItemBean;
import com.silikonm.common.dto.pos.ItemCategoryBean;
import com.silikonm.common.dto.pos.PosSupplierBean;
import com.silikonm.common.factory.DAOFactory;
import com.silikonm.common.interfaces.Command;
import com.silikonm.pos.item.extended.PosItemsTableModel;
import com.silikonm.pos.item.ui.PosItemsPanelView;
import com.silikonm.utilities.Alert;

public class PosItemModel {

	private PosItemsTableModel posItemsTableModel;
	private PosItemsPanelView view;
	private TableRowSorter<TableModel> sorter;

	public PosItemModel(PosItemsPanelView view) {
		this.posItemsTableModel = new PosItemsTableModel(null, this, view);
		this.view = view;

		sorter = new TableRowSorter<TableModel>(posItemsTableModel);
	}

	public TableRowSorter getSorter() {
		return sorter;
	}
	
	public void setRowFilter(RowFilter filter){
		sorter.setRowFilter(filter);
	}

	public PosItemsTableModel getPosItemsTableModel() {
		return posItemsTableModel;
	}

	/**
	 * Get all previously saved items from the database.
	 */
	public void getAllItems() {
		DAOFactory.getInstance().executeAndClose(new Command() {

			@Override
			public Object execute(DAOFactory factory) {
				view.setTableSorter(null);
				posItemsTableModel.resetTable();
				Item item = (Item) factory.createDAO(DAOFactory.ITEM);
				try {
					List<ItemBean> items = item.select();
					for (ItemBean bean : items) {
						Object[] row = new Object[] { bean.getItemCode(), bean,								
								bean.getItemDescription(),
								bean.getReorderLevel(),
								getSupplier(bean.getSupplierID()),
								getCategory(bean.getCategoryID()),
								bean.getUnit(), bean.getItemBarcode() };
						posItemsTableModel.insertRow(row);
					}
					sorter.setModel(posItemsTableModel);
					view.setTableSorter(sorter);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	/**
	 * Get all the available suppliers.
	 * 
	 * @return
	 */
	public Object[] getSuppliers() {
		return (Object[]) DAOFactory.getInstance().executeAndClose(
				new Command() {

					@Override
					public Object execute(DAOFactory factory) {
						PosSupplier supplier = (PosSupplier) factory
								.createDAO(DAOFactory.POS_SUPPLIER);
						try {
							List<PosSupplierBean> suppliers = supplier.select();
							Object[] sup = new Object[suppliers.size() + 1];
							sup[0] = "Select";
							int count = 1;
							for (PosSupplierBean bean : suppliers) {
								sup[count] = bean;
								count++;
							}
							return sup;
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
				});
	}

	/**
	 * Get all the item categories.
	 * 
	 * @return
	 */
	public Object[] getCategories() {
		return (Object[]) DAOFactory.getInstance().executeAndClose(
				new Command() {

					@Override
					public Object execute(DAOFactory factory) {
						ItemCategory category = (ItemCategory) factory
								.createDAO(DAOFactory.ITEM_CATEGORY);
						try {
							List<ItemCategoryBean> itemCategories = category
									.select();
							Object[] itCats = new Object[itemCategories.size() + 1];
							itCats[0] = "Select";
							int count = 1;
							for (ItemCategoryBean bean : itemCategories) {
								itCats[count] = bean;
								count++;
							}
							return itCats;
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
				});
	}

	/**
	 * Insert the POS item into the database.
	 * 
	 * @param bean
	 */
	public void savePosItem(final ItemBean bean) {
		DAOFactory.getInstance().executeAndClose(new Command() {

			@Override
			public Object execute(DAOFactory factory) {
				Item item = (Item) factory.createDAO(DAOFactory.ITEM);
				try {
					item.insert(bean);
				} catch (SQLException e) {
					if (e.getErrorCode() == 1062) {
						// update the item on duplicate error occurred in
						// inserting.
						updatePosItem(bean);
					}
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	/**
	 * Update the POS item
	 * 
	 * @param bean
	 */
	public void updatePosItem(final ItemBean bean) {
		DAOFactory.getInstance().executeAndClose(new Command() {

			@Override
			public Object execute(DAOFactory factory) {
				Item item = (Item) factory.createDAO(DAOFactory.ITEM);
				try {
					item.update(bean);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	/**
	 * Get supplier by supplier ID
	 * 
	 * @param supplierID
	 * @return
	 */
	public PosSupplierBean getSupplier(final int supplierID) {
		return (PosSupplierBean) DAOFactory.getInstance().executeAndClose(
				new Command() {

					@Override
					public Object execute(DAOFactory factory) {
						PosSupplier supplier = (PosSupplier) factory
								.createDAO(DAOFactory.POS_SUPPLIER);
						try {
							return supplier.selectById(supplierID);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
				});
	}

	public ItemCategoryBean getCategory(final int categoryID) {
		return (ItemCategoryBean) DAOFactory.getInstance().executeAndClose(
				new Command() {

					@Override
					public Object execute(DAOFactory factory) {
						ItemCategory category = (ItemCategory) factory
								.createDAO(DAOFactory.ITEM_CATEGORY);
						try {
							return category.selectByCategory(categoryID);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
				});
	}

	public void deletePosItem(final String itemCode) {
		DAOFactory.getInstance().executeAndClose(new Command() {

			@Override
			public Object execute(DAOFactory factory) {
				Item item = (Item) factory.createDAO(DAOFactory.ITEM);
				try {
					item.delete(itemCode);
				} catch (SQLException e) {
					if (e.getErrorCode() == 1451) {
						Alert.message("You cannot delete this item\n"
								+ "because it is used for an already registered purchase order!");
					} else {
						e.printStackTrace();
					}
				}
				return null;
			}
		});
	}

	public int getItemCount(final String prifix) {
		return (Integer) DAOFactory.getInstance().executeAndClose(
				new Command() {

					@Override
					public Object execute(DAOFactory factory) {
						Item item = (Item) factory.createDAO(DAOFactory.ITEM);
						try {
							return item.getMaximumItemCount(prifix);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return 0;
					}
				});
	}

}
