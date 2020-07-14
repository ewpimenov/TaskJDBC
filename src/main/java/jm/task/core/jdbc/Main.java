package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь

        String name = "Hello";
        String lastName = "World";
        byte age = 15;

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser(name, lastName, age);

        System.out.println("User с именем - " + name + " добавлен в базу данных");

        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
