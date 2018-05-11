package com.bahc.hbase;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.io.compress.Compression;

import java.io.IOException;

public class HbaseTableOperation {

    private static final String TABLE_NAME = "MY_TABLE_NAME_TOO";
    private static final String CF_DEFAULT = "DEFAULT_COLUMN_FAMILY";

    public static void createOrOverwrite(Admin admin, HTableDescriptor table) throws IOException {
        if (admin.tableExists(table.getTableName())) {
            admin.disableTable(table.getTableName());
            admin.deleteTable(table.getTableName());
        }
        admin.createTable(table);
    }

    public static void createSchemaTables(Configuration config) throws IOException {
        try (Connection connection = ConnectionFactory.createConnection(config);
             Admin admin = connection.getAdmin()) {

            HTableDescriptor table = new HTableDescriptor(TableName.valueOf(TABLE_NAME));
            table.addFamily(new HColumnDescriptor(CF_DEFAULT).setCompressionType(Compression.Algorithm.NONE));

            System.out.print("Creating table. ");
            createOrOverwrite(admin, table);
            System.out.println(" Done.");
        }
    }

    /**
     *
     * @param config
     * @throws IOException
     */
    public static void modifySchema (Configuration config) throws IOException {
        try (Connection connection = ConnectionFactory.createConnection(config);
             Admin admin = connection.getAdmin()) {

            TableName tableName = TableName.valueOf(TABLE_NAME);
            if (!admin.tableExists(tableName)) {
                System.out.println("Table does not exist.");
                System.exit(-1);
            }

            HTableDescriptor table = admin.getTableDescriptor(tableName);

            // Update existing table
            HColumnDescriptor newColumn = new HColumnDescriptor("NEWCF");
            newColumn.setCompactionCompressionType(Compression.Algorithm.GZ);
            newColumn.setMaxVersions(HConstants.ALL_VERSIONS);
            admin.addColumn(tableName, newColumn);

            // Update existing column family
            HColumnDescriptor existingColumn = new HColumnDescriptor(CF_DEFAULT);
            existingColumn.setCompactionCompressionType(Compression.Algorithm.GZ);
            existingColumn.setMaxVersions(HConstants.ALL_VERSIONS);
            table.modifyFamily(existingColumn);
            admin.modifyTable(tableName, table);

            // Disable an existing table
            admin.disableTable(tableName);

            // Delete an existing column family
            admin.deleteColumn(tableName, CF_DEFAULT.getBytes("UTF-8"));

            // Delete a table (Need to be disabled first)
            admin.deleteTable(tableName);
        }
    }


    public static void javaApi() throws  Exception{
        TableName tableName = TableName.valueOf("users");
        String path = "hdfs://<namenode>:<port>/user/<hadoop-user>/coprocessor.jar";
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        admin.disableTable(tableName);
        HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
        HColumnDescriptor columnFamily1 = new HColumnDescriptor("personalDet");
        columnFamily1.setMaxVersions(3);
        hTableDescriptor.addFamily(columnFamily1);
        HColumnDescriptor columnFamily2 = new HColumnDescriptor("salaryDet");
        columnFamily2.setMaxVersions(3);
        hTableDescriptor.addFamily(columnFamily2);
        admin.modifyTable(tableName, hTableDescriptor);
        admin.enableTable(tableName);
    }


    public static void main(String... args) throws IOException {
        Configuration config = HBaseConfiguration.create();

        //Add any necessary configuration files (hbase-site.xml, core-site.xml)
//        config.addResource(new Path(System.getenv("HBASE_CONF_DIR"), "hbase-site.xml"));
//        config.addResource(new Path(System.getenv("HADOOP_CONF_DIR"), "core-site.xml"));

        config.set("hbase.zookeeper.quorum", "bourne");
        config.set("hbase.zookeeper.property.clientPort", "2181");
        config.set("hbase.master", "bourne:9001");

//        createSchemaTables(config);
        modifySchema(config);

    }

}
