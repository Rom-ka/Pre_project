package jm.task.core.jdbc.dao;

import com.sun.xml.bind.v2.model.core.ID;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.naming.Name;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() throws ClassNotFoundException {

    }

    public void dropUsersTable() throws SQLException {
        String sqlCommand = "DROP TABLE IF EXISTS users;";
        try (
                Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);

        }
    }

    public void createUsersTable() throws SQLException {

        String sqlCommand = "CREATE TABLE IF NOT EXISTS users(ID int auto_increment, NAME VARCHAR(20) NOT NULL," +
                "LASTNAME VARCHAR(20) NOT NULL,AGE TINYINT NOT NULL, CONSTRAINT users_pk  PRIMARY KEY (ID));";
        try (
                Statement statement = connection.createStatement()) {

            statement.executeUpdate(sqlCommand);

        }
    }


    public void saveUser(String name, String lastName, byte age) throws SQLException {


        String sqlSave = "INSERT INTO users (NAME, LASTNAME, AGE) VALUES(?,?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSave)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();

        }

    }

    public void removeUserById(long id) throws SQLException {

        String sql = "DELETE FROM USERS WHERE ID=?;";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }


    }

    public List<User> getAllUsers() throws SQLException {
        List<User> listUser = new ArrayList<>();
        String sqlCom = "SELECT ID, NAME, LASTNAME, AGE FROM USERS;";

        try (
                Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlCom);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));
                listUser.add(user);
            }

        }
        return listUser;

    }

    public void cleanUsersTable() throws SQLException {

        String sqlCommand = "TRUNCATE TABLE users;";
        try (
                Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);
        }
    }
}
