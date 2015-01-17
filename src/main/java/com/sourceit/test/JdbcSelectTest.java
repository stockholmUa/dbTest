package com.sourceit.test;

import com.sourceit.jdbc.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// JDK 7 and above
public class JdbcSelectTest {  // Save as "JdbcSelectTest.java"
    public static void main(String[] args) {
        try (
                // Step 1: Allocate a database "Connection" object
                Connection conn = ConnectionFactory.getMySQlConnection();
                // Step 2: Allocate a "Statement" object in the Connection
                Statement stmt = conn.createStatement();
        ) {
            // Step 3: Execute a SQL SELECT query, the query result
            //  is returned in a "ResultSet" object.
            String strSelect = "select id_role, name from role";
            System.out.println("The SQL query is: " + strSelect); // Echo For debugging
            System.out.println();

            ResultSet rset = stmt.executeQuery(strSelect);

            // Step 4: Process the ResultSet by scrolling the cursor forward via next().
            //  For each row, retrieve the contents of the cells with getXxx(columnName).
            System.out.println("The records selected are:");
            int rowCount = 0;
            while(rset.next()) {   // Move the cursor to the next row
                String name = rset.getString("name");
                int idRole = rset.getInt("id_role");
                System.out.println(idRole + ": " + name);
                ++rowCount;
            }
            System.out.println("Total number of records = " + rowCount);

        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        // Step 5: Close the resources - Done automatically by try-with-resources
    }
}