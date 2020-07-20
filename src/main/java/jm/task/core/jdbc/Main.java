package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Misha","Petrov", (byte) 10);
        System.out.println("User с именем Misha добавлен в базу данных");
        userService.saveUser("Petya", "Leonov", (byte) 12);
        System.out.println("User с именем Petya добавлен в базу данных");
        userService.saveUser("Dima","Ivanov", (byte) 14);
        System.out.println("User с именем Dima добавлен в базу данных");
        userService.saveUser("Lesha","Sidorov", (byte) 16);
        System.out.println("User с именем Lesha добавлен в базу данных");

        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
