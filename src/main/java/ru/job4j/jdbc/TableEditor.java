package ru.job4j.jdbc;

import java.io.*;
import java.sql.*;
import java.util.*;


public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(this.properties.getProperty("driver_class"));
        String url = this.properties.getProperty("url");
        String login = this.properties.getProperty("username");
        String password = this.properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws Exception {
        try (Statement statement = this.connection.createStatement()) {
            String sql = String.format("CREATE TABLE IF NOT EXISTS %s();", tableName);
            statement.execute(sql);
        }
    }

    public void dropTable(String tableName) throws Exception {
        try (Statement statement = this.connection.createStatement()) {
            String sql = String.format("DROP TABLE IF EXISTS %s;", tableName);
            statement.execute(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        try (Statement statement = this.connection.createStatement()) {
            String sql = String.format("ALTER TABLE IF EXISTS %s ADD COLUMN IF NOT EXISTS  %s %s;",
                    tableName, columnName, type);
            statement.execute(sql);
        }
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        try (Statement statement = this.connection.createStatement()) {
            String sql = String.format("ALTER TABLE IF EXISTS %s DROP COLUMN IF EXISTS %s;", tableName, columnName);
            statement.execute(sql);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        try (Statement statement = this.connection.createStatement()) {
            String sql = String.format("ALTER TABLE IF EXISTS %s RENAME COLUMN IF EXISTS %s TO %s;",
                    tableName, columnName, newColumnName);
            statement.execute(sql);
        }
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        try (TableEditor tl = new TableEditor(config)) {
            String tableName = "sql1";
            tl.createTable(tableName);
            System.out.println(tl.getTableScheme(tableName));
            tl.addColumn(tableName, "name", "text");
            System.out.println(tl.getTableScheme(tableName));
            tl.addColumn(tableName, "last_name", "text");
            System.out.println(tl.getTableScheme(tableName));
            tl.dropColumn(tableName, "name");
            System.out.println(tl.getTableScheme(tableName));
            tl.renameColumn(tableName, "last_name", "nickname");
            System.out.println(tl.getTableScheme(tableName));
            tl.dropTable(tableName);
            System.out.println(tl.getTableScheme(tableName));
        }
    }
}