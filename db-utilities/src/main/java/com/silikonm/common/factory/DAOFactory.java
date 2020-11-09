package com.silikonm.common.factory;

import java.sql.Connection;

import com.silikonm.common.db.DbConnection;
import com.silikonm.common.interfaces.AbstractDAO;
import com.silikonm.common.interfaces.Command;
import com.silikonm.common.interfaces.DAO;
import com.silikonm.commons.PropertyHandler;

/**
 * @author harsha
 */
public class DAOFactory {

    private static DAOFactory instance = null;// instance of the DAOFactory
    private Connection databaseConnection = null;// database connection
    private boolean isTransaction = false;
    private DbConnection dbConnection = new DbConnection(
            PropertyHandler.readProperty("database"),
            PropertyHandler.readProperty("user"), PropertyHandler.readProperty("password"),
            PropertyHandler.readProperty("host"));
    
    // DAO Types
    // Stock DAOs    
    public static final String ITEM_CATEGORY = "com.silikonm.common.daoimpl.pos.ItemCategoyImpl";
    public static final String ITEM = "com.silikonm.common.daoimpl.pos.ItemImpl";
    public static final String TRANSACTION = "com.silikonm.common.daoimpl.pos.TransactionImpl";
    public static final String POS_SUPPLIER = "com.silikonm.common.daoimpl.pos.PosSupplierImpl";
    
    //
    
	public static final String POS_PURCHASE_ORDER = "com.silikonm.common.daoimpl.pos.PosPurchaseOrderImpl";
	public static final String POS_GENERAL_PURCHASE = "com.silikonm.common.daoimpl.pos.GeneralPurchaseImpl";
	public static final String SALE = "com.silikonm.common.daoimpl.pos.SaleImpl";
	public static final String USER = "com.silikonm.common.daoimpl.pos.UserImpl";

    // make private constructor to eliminate object
    // creating with new key word.
    private DAOFactory() {
    	//System.out.println(PropertyHandler.readProperty("password"));
    }

    /*
     * Get a instance of DAOFactory
     */
    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public DAO createDAO(String key) {
        DAO dao = null;
        try {
            Class controllerClass = Class.forName(key);
            dao = (DAO) controllerClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((AbstractDAO) dao.getInstance()).setConnection(getConnection());
        return dao;
    }


    /**
     * executeAndClose(Command command) handles single DAO action.
     *
     * @param command - execution command.
     * @return any object as the result of the DAO execution.
     */
    public Object executeAndClose(Command command) {
        Object object = null;
        try {
            getConnection();// get the database connection.
            object = command.execute(this);// execute the command.
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbConnection.chekInConnection(getConnection());// return the
            // database
            // connection.
            this.databaseConnection = null;// set the Connection in the
            // DAOFactory null.
        }
        return object;
    }

    /**
     * transactionAndClose(final Command command) handles transactions of
     * several DAOs
     *
     * @param command command - execution command.
     * @return any object as the result of the transaction() method.
     */
    public Object transactionAndClose(final Command command) {
        return executeAndClose(new Command() {

            @Override
            public Object execute(DAOFactory factory) {
                isTransaction = true;
                return transaction(command);
            }
        });
    }

    /**
     * transaction(Command command) do the DAO transactions.
     *
     * @param command
     * @return
     */
    private Object transaction(Command command) {
        Object object = null;
        try {
            object = command.execute(this);
            getConnection().commit();
            isTransaction = false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            try {
//                //getConnection().setAutoCommit(true);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }
        return object;
    }

    /**
     * transactionRollback() rollback DAO transactions.
     */
    public void transactionRollback() {
        try {
            // if (getConnection().getAutoCommit()) {
            // getConnection().setAutoCommit(false);
            // }
            getConnection().rollback();
            System.out
                    .println("*********************************************************");
            System.out
                    .println("***************** Transaction rollbacked ****************");
            System.out
                    .println("*********************************************************");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return database connection
     */
    public Connection getConnection() {
        if (this.databaseConnection == null) {

            this.databaseConnection = dbConnection.checkOut();
        }
        if (isTransaction) {
            try {
                this.databaseConnection.setAutoCommit(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.databaseConnection;
    }

    public void changeDataBaseConnection(String dbName, String userName,
                                         String password, String ip) {
        this.dbConnection.clearConnectionPool();
        this.dbConnection.changeConnection(dbName, userName, password, ip);
        this.databaseConnection = null;
    }

    public void changeConnection(String database, String user, String password,
                                 String host) {
        if (this.databaseConnection != null) {
            try {
                DbConnection.chekInConnection(this.databaseConnection);
                this.databaseConnection = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.databaseConnection = DbConnection.createConnection(database,
                    user, password, host);
        }
    }

    public void resetDataBaseConnection() {
        this.dbConnection.clearConnectionPool();
        this.dbConnection.changeConnection(
                PropertyHandler.readProperty("database"),
                PropertyHandler.readProperty("user"), "root",
                PropertyHandler.readProperty("host"));
        this.databaseConnection = null;
    }


}
