package com.sourceit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //URL к базе состоит из протокола:подпротокола://[хоста]:[порта_СУБД]/[БД] и других_сведений
    static final String DB_URL = "jdbc:mysql://localhost/testdb";

    //  Database credentials
    private static final String DB_USER_NAME = "root";
    private static final String DB_USER_PASS = "admin";

    static{
        try{
            Class.forName(JDBC_DRIVER);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getMySQlConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASS);
    }

}
