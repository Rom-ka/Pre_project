package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_URl="jdbc:mysql://localhost:3306/mydb";
    private static final String DB_USERNAME="root";
    private static final String DB_PASSWORD="root";


    public static Connection getConnection() throws ClassNotFoundException {
        Connection connection=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URl, DB_USERNAME, DB_PASSWORD);

                System.out.println("Connection ok");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection error");
        }
        return connection;
    }

}
