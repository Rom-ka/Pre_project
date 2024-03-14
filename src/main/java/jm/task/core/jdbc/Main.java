package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        SessionFactory factory=Util.getSessionFactory();
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("John", "Petrov", (byte) 20);
        userService.saveUser("Ivan", "Ivanov", (byte) 25);
        userService.saveUser("Jack", "Richer", (byte) 31);
        userService.saveUser("Camila", "Larsan", (byte) 38);



       userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
       userService.dropUsersTable();
    }
    }

