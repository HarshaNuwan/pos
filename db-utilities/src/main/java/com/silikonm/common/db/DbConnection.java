package com.silikonm.common.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.Hashtable;

public class DbConnection {
    private String databaseName;
    private String databaseUser;
    private String databasePassword;
    private String databaseHost;
    private static Hashtable<Connection, Long> lockedConnections;// holds locked
    // database
    // connections.
    private static Hashtable<Connection, Long> unlockedConnections;// holds
    // unlocked
    // database
    // connections.
    private long expirationTime;

    public DbConnection(String databaseName, String databaseUser,
                        String databasePassword, String databaseHost) {
        super();
        this.databaseName = databaseName;
        this.databaseUser = databaseUser;
        this.databasePassword = databasePassword;
        this.databaseHost = databaseHost;
        init();
    }

    public static Connection createConnection(String databaseName,
                                              String databaseUser, String databasePassword, String databaseHost) {

        return new DbConnection(databaseName, databaseUser, databasePassword,
                databaseHost).checkOut();
    }

    public static void chekInConnection(Connection connection) {
        DbConnection.checkIn(connection);
    }

    private void init() {
        lockedConnections = new Hashtable<Connection, Long>();
        unlockedConnections = new Hashtable<Connection, Long>();
        expirationTime = 9000;// connection expired
    }

    /**
     * Creates a new database connection.
     */
    private Connection createConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            con = DriverManager.getConnection("jdbc:mysql://" + databaseHost
                    + "/" + databaseName, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * @return Connection
     */
    public Connection checkOut() {
        long now = System.currentTimeMillis();

        Connection con;

        if (unlockedConnections.size() > 0) {
            Enumeration<Connection> e = unlockedConnections.keys();
            while (e.hasMoreElements()) {
                con = e.nextElement();

                // System.out.println(now + " : " +
                // unlockedConnections.get(con));

                if ((now - unlockedConnections.get(con)) > expirationTime) {
                    unlockedConnections.remove(con);
                    closeConnection(con);
                    con = null;
                } else {
                    if (!validateConnection(con)) {
                        unlockedConnections.remove(con);
                        lockedConnections.put(con, now);
                        // System.out.println(con
                        // + " : Checked out old connection.");
                        return con;
                    } else {
                        unlockedConnections.remove(con);
                        con = null;
                    }
                }
            }
        }

        con = createConnection();
        lockedConnections.put(con, now);// add the newly created connection into
        // the locked list.
        // System.out.println(con + " : Checked out new connection.");
        return con;
    }

    private static void checkIn(Connection con) {
        long time = lockedConnections.get(con);
        lockedConnections.remove(con);
        unlockedConnections.put(con, time);
        // System.out.println(con + " : Checked in.");
    }

    public void clearConnectionPool() {
        Enumeration<Connection> e = unlockedConnections.keys();
        while (e.hasMoreElements()) {
            Connection connection = (Connection) e.nextElement();
            unlockedConnections.remove(connection);
            closeConnection(connection);
            connection = null;
        }
        Enumeration<Connection> f = lockedConnections.keys();
        while (f.hasMoreElements()) {
            Connection connection = (Connection) f.nextElement();
            lockedConnections.remove(connection);
            closeConnection(connection);
            connection = null;
        }
    }

    private boolean validateConnection(Connection con) {
        try {
            if (con.isClosed()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void closeConnection(Connection con) {
        try {
            con.close();
            System.out.println(con + " : Is expired.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeConnection(String databaseName, String databaseUser,
                                 String databasePassword, String databaseHost) {
        this.databaseName = databaseName;
        this.databaseUser = databaseUser;
        this.databasePassword = databasePassword;
        this.databaseHost = databaseHost;
    }
}
