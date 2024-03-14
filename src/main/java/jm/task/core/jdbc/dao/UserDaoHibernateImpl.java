package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    private static SessionFactory factory=getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction=null;
        try(Session session=factory.openSession()){
            transaction=session.getTransaction();
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users(ID int auto_increment, NAME VARCHAR(20) NOT NULL," +
            "LASTNAME VARCHAR(20) NOT NULL,AGE TINYINT NOT NULL, CONSTRAINT users_pk  PRIMARY KEY (ID));").executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction=null;
        try(Session session=factory.openSession()){
            transaction=session.getTransaction();
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users;").executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
            Transaction transaction=null;
        try(Session session=factory.openSession()){
            transaction=session.getTransaction();
            session.beginTransaction();
            session.save(new User(name,lastName,age));
            session.getTransaction().commit();
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction=null;
        try(Session session=factory.openSession()){
            transaction=session.getTransaction();
            session.beginTransaction();
            User user=session.get(User.class,id);
            session.delete(user);
            session.getTransaction().commit();
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUser=new ArrayList<>();
        Transaction transaction=null;
        try(Session session=factory.openSession()){
            transaction=session.getTransaction();
            session.beginTransaction();
            listUser=session.createQuery("from User").getResultList();
            session.getTransaction().commit();
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
        }
        return listUser;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction=null;
        try(Session session=factory.openSession()){
            transaction=session.getTransaction();
            session.beginTransaction();
            session.createQuery("delete User ").executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
        }
    }
}
