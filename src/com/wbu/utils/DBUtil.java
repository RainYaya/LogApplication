package com.wbu.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    
    Connection conn=null;

    public Connection getConnection(){
        try {
            PropertiesSinletionVialnnerClass instance=PropertiesSinletionVialnnerClass.getInstance();        
            Class.forName(instance.getValue("dirver"));
            conn= DriverManager.getConnection(instance.getValue("url"),instance.getValue("username"),instance.getValue("password"));
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }    
}