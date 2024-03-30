package web.dao;

import org.springframework.stereotype.Component;
import web.models.User;

import java.util.List;
@Component
public class UserDaoImpl implements UserDao {

    @Override
    public List<User> showListUser() {
        return null;
    }

    @Override
    public void removeUser(int id) {

    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }
}
