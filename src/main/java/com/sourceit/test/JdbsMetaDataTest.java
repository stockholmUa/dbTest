package com.sourceit.test;

import com.sourceit.jdbc.ConnectionFactory;

import java.sql.*;

// JDK 7 and above
public class JdbsMetaDataTest {  // Save as "JdbcSelectTest.java"
    public static void main(String[] args) {
        try (
                // Step 1: Allocate a database "Connection" object
                Connection conn = ConnectionFactory.getMySQlConnection();

                // Step 2: Allocate a "Statement" object in the Connection
                Statement stmt = conn.createStatement();
        ) {
            // Step 3 & 4: Execute query and process query result
            ResultSet rset = stmt.executeQuery("select * from books");
            // Get the metadata of the ResultSet
            ResultSetMetaData rsetMD = rset.getMetaData();
            // Get the number of column from metadata
            int numColumns = rsetMD.getColumnCount();

            // Print column names - Column Index begins at 1 (instead of 0)
            for (int i = 1; i <= numColumns; ++i) {
                System.out.printf("%-30s", rsetMD.getColumnName(i));
            }
            System.out.println();

            // Print column class names
            for (int i = 1; i <= numColumns; ++i) {
                System.out.printf("%-30s",
                        "(" + rsetMD.getColumnClassName(i) + ")");
            }
            System.out.println();

            // Print column contents for all the rows
            while (rset.next()) {
                for (int i = 1; i <= numColumns; ++i) {
                    // getString() can be used for all column types
                    System.out.printf("%-30s", rset.getString(i));
                }
                System.out.println();
            }

        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        // Step 5: Close the resources - Done automatically by try-with-resources
    }
}