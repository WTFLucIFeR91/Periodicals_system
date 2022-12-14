package com.epam.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
    private static final Logger log = LogManager.getLogger(DBManager.class);

    private C3P0DataSource ds;

    private static DBManager instance;

    public static synchronized DBManager getInstance(){
        if(instance == null){
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager(){
    }

    public Connection getConnection()  {
        try {
            return C3P0DataSource.getConnection();
        } catch (SQLException e) {
            log.error("Cannot init DBManager");
            throw new RuntimeException("Cannot init DBManager");
        }
    }

    public void close(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
