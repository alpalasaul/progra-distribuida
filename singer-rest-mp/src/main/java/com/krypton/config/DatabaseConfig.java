package com.krypton.config;

import org.postgresql.ds.PGSimpleDataSource;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@ApplicationScoped
public class DatabaseConfig {

    @Produces
    @ApplicationScoped
    public DataSource dataSource() {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUser("postgres");
        ds.setPassword("admin");
        ds.setDatabaseName("postgres");
        return ds;
    }

//    @Produces
//    @ApplicationScoped
    public Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost/postgres";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","admin");
        return DriverManager.getConnection(url, props);
    }

}
