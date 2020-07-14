package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection getConnection() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "my_crud?useUnicode=true&serverTimezone=UTC";
            String userName = "root";
            String password = "1111";

            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url + dbName, userName, password);
            if (conn != null)
                System.out.println("Приложение подключилось к БД !");
            return conn;
        } catch (Exception e) {
            System.out.println("Приложение НЕ подключилось к БД ?");

        }
        return null;
    }
}
