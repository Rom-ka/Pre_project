package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
