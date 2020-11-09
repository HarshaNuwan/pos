package com.silikonm.common.interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 * @author harsha
 */
public interface DAO<T, I> {

    public I getInstance();

    public abstract boolean insert(T t) throws SQLException;

    public abstract boolean delete(String id) throws SQLException;

    public abstract boolean update(T t) throws SQLException;

    public abstract List<T> select() throws SQLException;

    public int getNextId() throws SQLException;

}
