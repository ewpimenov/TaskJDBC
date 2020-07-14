package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

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

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/my_crud?serverTimezone=UTC");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "1111");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        return configuration;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
