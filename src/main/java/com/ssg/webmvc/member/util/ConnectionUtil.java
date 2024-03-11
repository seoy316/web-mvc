package com.ssg.webmvc.member.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public enum ConnectionUtil {
    INSTANCE;
    private HikariDataSource ds;
    ConnectionUtil() {
        HikariConfig config = new HikariConfig();

        try {
            Properties prop = loadDatabaseProperties();
            String url = prop.getProperty("database.url");
            String user = prop.getProperty("database.user");
            String pw = prop.getProperty("database.password");
            config.setDriverClassName("org.mariadb.jdbc.Driver");
            config.setJdbcUrl(url);
            config.setUsername(user);
            config.setPassword(pw);
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            ds = new HikariDataSource(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection()throws Exception {
        return ds.getConnection();
    }

    private static Properties loadDatabaseProperties() throws Exception {
        Properties prop = new Properties();
        InputStream inputStream = ConnectionUtil.class.getClassLoader().getResourceAsStream("config/database.properties");
        if (inputStream == null) {
            throw new Exception("Unable to find database.properties");
        }
        prop.load(inputStream);
        return prop;
    }
}