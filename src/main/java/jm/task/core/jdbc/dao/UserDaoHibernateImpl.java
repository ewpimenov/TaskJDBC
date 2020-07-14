package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;
import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory = getSessionFactory();

    private static Connection connection = getConnection();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() throws SQLException {
        String sql = "create table IF NOT EXISTS users_table ("
                + "id int(11) auto_increment not null,"
                + "name varchar(45) not null,"
                + "lastName varchar(45) not null,"
                + "age int(25) not null, "
                + "primary key (id))";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void dropUsersTable() throws SQLException {
        String sql = "drop table if exists users_table";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User(name,lastName,age);
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user;
        user = (User) session.load(User.class,id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete FROM User").executeUpdate();
        transaction.commit();
        session.close();
    }
}

