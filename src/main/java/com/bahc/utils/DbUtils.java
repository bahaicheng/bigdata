package com.bahc.utils;


import org.apache.curator.framework.recipes.cache.TreeCache;

import java.sql.*;

public class DbUtils {
    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://bourne:3306/holiday";
        String user = "root";
        String password = "root123";
        Connection conn = null;
        Statement statement = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT HOL_DATE FROM HOLIDAY_TBL");
            while(rs.next()){
                Date date = rs.getDate(1);
                System.out.println(date);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }

                if (statement != null) {
                    statement.close();
                }

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
