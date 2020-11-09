package com.silikonm.common.interfaces;

/**
 * @author harsha
 */
public abstract class AbstractDAO {

    private java.sql.Connection connection = null;

    public void setConnection(Object c) {
        this.connection = (java.sql.Connection) c;
    }

    public java.sql.Connection getConnection() {
        if (this.connection == null) {
            throw new NullPointerException("Connection is null!");
        }
        return this.connection;
    }
}
