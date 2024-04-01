package com.spring.springboot.dao;


import com.spring.springboot.models.User;

import java.util.List;

public interface UserDao {
    public List<User> showListUser();
    public void removeUser(int id);
    public User updateUser(User updateUser,int id);
    public User getUserById(int id);
    public void save(User user);
}
