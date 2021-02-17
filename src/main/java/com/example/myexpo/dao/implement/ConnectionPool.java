package com.example.myexpo.dao.implement;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.util.ResourceBundle;

public class ConnectionPool {
    static final Logger log = LogManager.getRootLogger();
    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {

        if (dataSource == null) {
            synchronized (ConnectionPool.class) {
                if (dataSource == null) {
                    ResourceBundle prop = ResourceBundle.getBundle("statements");
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(prop.getString("CONNECTION"));
                    ds.setUsername(prop.getString("USER"));
                    ds.setPassword(prop.getString("PASS"));
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;

    }
}
