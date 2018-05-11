package com.bahc.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class IgniteDemo {
    public static void main(String[] args) throws Exception{
        concurrentHashMapDemo();
        createTable();
    }

    public static void concurrentHashMapDemo(){
        Map<String,String> map = new ConcurrentHashMap();
        map.put("a","1");
        map.put("b","2");
        map.putIfAbsent("b","3");
        map.put("c","5");

        Set<Map.Entry<String,String>>  set =  map.entrySet();

        for ( Map.Entry<String,String> s   :  set){
            System.out.println(s.toString());
        }

    }

    public static void createTable() throws Exception{
        String driver = "org.apache.ignite.IgniteJdbcThinDriver";
        String url = "jdbc:ignite:thin://192.168.121.66/";
        try {
            // Register JDBC driver.
            Class.forName(driver);

            // Open JDBC connection.
            Connection conn = DriverManager.getConnection("jdbc:ignite:thin://192.168.121.66/");

            // Create database tables.

            Statement stmt = conn.createStatement();
            // Create table based on REPLICATED template.
            stmt.executeUpdate("CREATE TABLE City (" +
                    " id LONG PRIMARY KEY, name VARCHAR) " +
                    " WITH \"template=replicated\"");

            // Create table based on PARTITIONED template with one backup.
            stmt.executeUpdate("CREATE TABLE Person (" +
                    " id LONG, name VARCHAR, city_id LONG, " +
                    " PRIMARY KEY (id, city_id)) " +
                    " WITH \"backups=1, affinityKey=city_id\"");

            // Create an index on the City table.
            stmt.executeUpdate("CREATE INDEX idx_city_name ON City (name)");

            // Create an index on the Person table.
            stmt.executeUpdate("CREATE INDEX idx_person_name ON Person (name)");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
