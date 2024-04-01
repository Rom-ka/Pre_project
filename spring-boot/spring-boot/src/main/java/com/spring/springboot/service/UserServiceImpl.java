package com.spring.springboot.service;

import com.spring.springboot.dao.UserDao;
import com.spring.springboot.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDapImpl;

    @Override
    public List<User> showListUser() {
        return userDapImpl.showListUser();
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        userDapImpl.removeUser(id);
    }

    @Override
    @Transactional
    public User updateUser(User updateUser, int id) {

        return userDapImpl.updateUser(updateUser, id);
    }

    @Override
    public User getUserById(int id) {

        return userDapImpl.getUserById(id);
    }

    @Override
    @Transactional
    public void save(User user) {
        userDapImpl.save(user);
    }
}
