package com.sourceit.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionService {

    private Properties properties = new Properties();

    private static class InnerClass {
        public static ConnectionService REF = new ConnectionService();
    }

    public static ConnectionService getInstance() {
        return InnerClass.REF;
    }

    public Properties getProperties() {
        return properties;
    }

    private ConnectionService() {
        try (FileInputStream in = new FileInputStream(".\\src\\main\\resources\\db.properties")) {
            properties.load(in);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public Connection createConnection() {
        Connection connection = null;
        try {
            //Load driver
            Class.forName(properties.getProperty("driver"));
            System.out.println("Driver is loaded");
            //Create connection
            connection = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("user"), properties.getProperty("password"));
            System.out.println("Connection is created");
        } catch (Exception ex) {
            System.out.println("Something wrong");
            ex.printStackTrace();
        }
        return connection;
    }


}
