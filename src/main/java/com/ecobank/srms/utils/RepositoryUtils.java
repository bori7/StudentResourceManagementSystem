package com.ecobank.srms.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/* @author sa_oladipo created on 5/6/22 */
public class RepositoryUtils {


    /**
     * Gets database connection
     * @param url: the url
     * @param username: the username
     * @param password: the password
     * @return the connection
     * @throws Exception: the exception thrown
     */
    public static Connection getConnection(String url, String username, String password)throws Exception {

        Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        return DriverManager.getConnection(url, username, password);
    }

    public static void closeFinally(Connection conn, CallableStatement callStmt, ResultSet resultSet) {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception ex) {
            }
        }
        if (callStmt != null) {
            try {
                callStmt.close();
            } catch (Exception ex) {
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception ex) {
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
        }
    }
}
