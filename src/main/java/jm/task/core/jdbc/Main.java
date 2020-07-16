package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Misha","Petrov", (byte) 10);
        userService.saveUser("Petya", "Leonov", (byte) 12);
        userService.saveUser("Dima","Ivanov", (byte) 14);
        userService.saveUser("Lesha","Sidorov", (byte) 16);

        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

