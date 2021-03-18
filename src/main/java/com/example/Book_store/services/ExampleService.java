package com.example.Book_store.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ExampleService {
    public static void main(String[] args){
        System.out.println("-------- MySQL JDBC Connection Demo ------------");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found !!");
            return;
        }
        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/web_bookshop?characterEncoding=utf8", "root", "adminstrator0");
            System.out.println("SQL Connection to database established!");
            String sql = "insert into user_favorite_book (user_id, book_id) values (6, 6)";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Sql done");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            return;
        }
    }
}
