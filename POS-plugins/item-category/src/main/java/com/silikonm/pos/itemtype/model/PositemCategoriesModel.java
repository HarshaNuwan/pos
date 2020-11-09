package com.silikonm.pos.itemtype.model;

import java.sql.SQLException;
import java.util.List;

import com.silikonm.common.dao.pos.ItemCategory;
import com.silikonm.common.dto.pos.ItemCategoryBean;
import com.silikonm.common.factory.DAOFactory;
import com.silikonm.common.interfaces.Command;
import com.silikonm.pos.itemtype.extended.PosItemCategoriesTableModel;
import com.silikonm.pos.itemtype.ui.PosItemCategoriesView;
import com.silikonm.utilities.Alert;

public class PositemCategoriesModel {

	private PosItemCategoriesTableModel categoriesTableModel;

	public PositemCategoriesModel(PosItemCategoriesView view) {
		this.categoriesTableModel = new PosItemCategoriesTableModel(null, this, view);
		getAllItemCategories();
	}

	public PosItemCategoriesTableModel getCategoriesTableModel() {
		return categoriesTableModel;
	}

	public void saveItemCategory(final ItemCategoryBean categoryBean) {
		DAOFactory.getInstance().transactionAndClose(new Command() {

			@Override
			public Object execute(DAOFactory factory) {
				ItemCategory category = (ItemCategory) factory.createDAO(DAOFactory.ITEM_CATEGORY);
				try {
					category.insert(categoryBean);
				} catch (SQLException e) {
					if (e.getErrorCode() == 1062) {
						updateItemCategory(categoryBean);
					}
				}
				return null;
			}
		});

	}

	public int getNextItemCategoryId() {
		return (Integer) DAOFactory.getInstance().transactionAndClose(new Command() {

			@Override
			public Object execute(DAOFactory factory) {
				ItemCategory category = (ItemCategory) factory.createDAO(DAOFactory.ITEM_CATEGORY);
				try {
					return category.getNextId();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return -1;
			}
		});
	}

	public void getAllItemCategories() {
		DAOFactory.getInstance().transactionAndClose(new Command() {

			@Override
			public Object execute(DAOFactory factory) {
				System.out.println(DAOFactory.class);
				System.out.println(DAOFactory.ITEM_CATEGORY);
				ItemCategory category = (ItemCategory) factory.createDAO(DAOFactory.ITEM_CATEGORY);
				try {
					List<ItemCategoryBean> itemCategories = category.select();
					for (ItemCategoryBean bean : itemCategories) {
						Object[] row = new Object[] { bean.getCategoryID(), bean.getCategoryCode(), bean };
						categoriesTableModel.insertRow(row);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	public void deleteItemCategory(final int categoryId) {
		DAOFactory.getInstance().transactionAndClose(new Command() {

			@Override
			public Object execute(DAOFactory factory) {
				ItemCategory category = (ItemCategory) factory.createDAO(DAOFactory.ITEM_CATEGORY);
				try {
					category.delete(String.valueOf(categoryId));
				} catch (SQLException e) {
					if (e.getErrorCode() == 1451) {
						Alert.message("You cannot delete this item category\n" + "because it is used for an already registered item!");
					} else {
						e.printStackTrace();
					}

				}
				return null;
			}
		});
	}

	public void updateItemCategory(final ItemCategoryBean bean) {
		DAOFactory.getInstance().transactionAndClose(new Command() {

			@Override
			public Object execute(DAOFactory factory) {
				ItemCategory category = (ItemCategory) factory.createDAO(DAOFactory.ITEM_CATEGORY);
				try {
					category.update(bean);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}
}
