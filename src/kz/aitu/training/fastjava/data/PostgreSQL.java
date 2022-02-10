package kz.aitu.training.fastjava.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQL {

    private PostgreSQL() {
        throw new IllegalStateException("Utility class"); // means if someone creates instance of this class
                                                          // throws exception
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/shopdb", "postgres", "ahahahah umny chel");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
