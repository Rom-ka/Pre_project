package jm.task.core.jdbc.util;

public class Util {
    private static final String DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String URL="jdbc:mysql://localhost:3306/mydb";
    private static final String USER_NAME="root";
    private static final String PASSWORD="root";

    public Connection getConnection(){
        Connection connection=null;
        Class.forName(DRIVER);
        try ( connection=DriverManager.getC)

    }

}
