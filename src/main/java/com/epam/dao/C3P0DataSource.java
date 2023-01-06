package com.epam.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class C3P0DataSource {
  private static final ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
    static {
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/periodicals_system?characterEncoding=utf8");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("root");
        comboPooledDataSource.setMinPoolSize(5);
        comboPooledDataSource.setAcquireIncrement(5);
        comboPooledDataSource.setMaxPoolSize(30);
    }
    public static Connection getConnection () throws SQLException {
        return comboPooledDataSource.getConnection();
    }
    private C3P0DataSource(){
    }
}
