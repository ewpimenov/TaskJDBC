package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory = getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {

        String sql = "create table IF NOT EXISTS users_table ("
                + "id int(11) auto_increment not null,"
                + "name varchar(45) not null,"
                + "lastName varchar(45) not null,"
                + "age int(25) not null, "
                + "primary key (id))";
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            if (transaction != null) transaction.commit();
        } catch (HibernateException e) {
            if (transaction!= null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session!= null) session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "drop table if exists users_table";
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            if (transaction != null) transaction.commit();
        } catch (HibernateException e) {
            if (transaction!= null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session!= null) session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            if (transaction != null) transaction.commit();
        } catch (HibernateException e) {
            if (transaction!= null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session!= null) session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            User user;
            user = (User) session.load(User.class, id);
            session.delete(user);
            if (transaction != null) transaction.commit();
        } catch (HibernateException e) {
            if (transaction!= null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session!= null) session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            List<User> users = session.createQuery("FROM User").list();
            if (transaction != null) transaction.commit();
            return users;
        } catch (HibernateException e) {
            if (transaction!= null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session!= null) session.close();
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createQuery("delete FROM User").executeUpdate();
            if (transaction != null) transaction.commit();
        } catch (HibernateException e) {
            if (transaction!= null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session!= null) session.close();
        }
    }
}

