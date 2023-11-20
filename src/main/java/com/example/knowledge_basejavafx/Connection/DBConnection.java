package com.example.knowledge_basejavafx.Connection;


import java.sql.*;

public class DBConnection {
    static String user =  "root";
    static String password = "1234";
    static String url ="jdbc:mysql://localhost/KnowledgeBaseEditor";
    static String driver = "com.mysql.cj.jdbc.Driver";

    //getConnection to database (Mysql)
    public static Connection getDBCon(){
        Connection con = null;
        try {

            try {
                Class.forName(driver);
                con = DriverManager.getConnection(url,user,password);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return con;
    }




}
