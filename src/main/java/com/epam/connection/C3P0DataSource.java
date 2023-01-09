package com.epam.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static com.epam.connection.ConnectionConstants.*;

public class C3P0DataSource {

    private static final Logger log = LogManager.getLogger(C3P0DataSource.class);
    private static final ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

    static {
        Properties properties = getProperties();
        comboPooledDataSource.setJdbcUrl(properties.getProperty(URL_PROPERTY));
        comboPooledDataSource.setUser(properties.getProperty(USER_NAME));
        comboPooledDataSource.setPassword(properties.getProperty(PASSWORD));
        comboPooledDataSource.setMinPoolSize(Integer.parseInt(properties.getProperty(MIN_POOL_SIZE)));
        comboPooledDataSource.setAcquireIncrement(Integer.parseInt(properties.getProperty(ACQUIRE_INCREMENT)));
        comboPooledDataSource.setMaxPoolSize(Integer.parseInt(properties.getProperty(MAX_POOL_SIZE)));
    }

    public static Connection getConnection() throws SQLException {
        return comboPooledDataSource.getConnection();
    }

    private C3P0DataSource() {
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        String connectionFile = "connection.properties";
        try (InputStream resource = C3P0DataSource.class.getClassLoader().getResourceAsStream(connectionFile)) {
            properties.load(resource);

        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return properties;
    }
}
