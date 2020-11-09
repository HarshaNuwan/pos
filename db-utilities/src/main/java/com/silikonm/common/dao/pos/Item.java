package com.silikonm.common.dao.pos;

import java.sql.SQLException;

import com.silikonm.common.daoimpl.pos.ItemImpl;
import com.silikonm.common.dto.pos.ItemBean;
import com.silikonm.common.interfaces.DAO;

/**
 * Created with IntelliJ IDEA.
 * User: Harsha
 * Date: 10/18/13
 * Time: 4:46 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Item extends DAO<ItemBean, ItemImpl> {
    public ItemBean selectByItemCode(String itemCode) throws SQLException;
    public int getMaximumItemCount(String itemPrifix) throws SQLException;
}
