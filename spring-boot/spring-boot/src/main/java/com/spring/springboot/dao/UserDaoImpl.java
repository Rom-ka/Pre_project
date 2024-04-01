package com.spring.springboot.dao;

import com.spring.springboot.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> showListUser() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();

    }

    @Override
    public void removeUser(int id) {

        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User updateUser(User updateUser, int id) {

        return entityManager.merge(updateUser);
    }

    @Override
    public User getUserById(int id) {

        return entityManager.find(User.class, id);

    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }
}
