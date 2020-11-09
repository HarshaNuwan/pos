package com.silikonm.common.dao.pos;

import com.silikonm.common.daoimpl.pos.ItemCategoyImpl;
import com.silikonm.common.dto.pos.ItemCategoryBean;
import com.silikonm.common.interfaces.DAO;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Harsha
 * Date: 10/18/13
 * Time: 3:24 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ItemCategory extends DAO<ItemCategoryBean, ItemCategoyImpl> {
    public ItemCategoryBean selectByCategory(int categoryID) throws SQLException;
}
