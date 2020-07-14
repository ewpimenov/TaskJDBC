package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {

    private static Connection connection = getConnection();

    public UserDaoJDBCImpl() {
    }

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

    public void dropUsersTable() throws SQLException {
        String sql = "drop table if exists users_table";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql = "insert into users_table (name, lastName, age) values (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, lastName);
        stmt.setByte(3, age);
        stmt.executeUpdate();
        stmt.close();
    }

    public void removeUserById(long id) throws SQLException {
        String sql = "delete from users_table where id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        stmt.executeUpdate();
        stmt.close();
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        String sql = "select name, lastName, age from users_table";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String lastName = resultSet.getString("lastName");
            byte age = resultSet.getByte("age");
            list.add(new User(name, lastName, age));
        }
        resultSet.close();
        stmt.close();
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        String sql = "delete from users_table";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();
    }
}