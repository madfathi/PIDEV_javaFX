package tn.esprit.guiapplicatio.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDatabase {

    final String URL = "jdbc:mysql://localhost:3306/3a28";
    final String USERNAME = "root";
    final String PASSWORD = "";
    private Connection connection;

    public MyDatabase() {

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("etablissement connexion");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }


    }


    public Connection getConnection() {
        return connection;

    }

    public static MyDatabase instance;

    public static MyDatabase getInstance() {
        if (instance == null)
            instance = new MyDatabase();
        return instance;


    }
}
