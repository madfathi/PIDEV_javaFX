package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mydatabase {
    private final String URL="jdbc:mysql://localhost:3306/3a28";
    private final String USER="root";
    private final String PASSWORD="";
    private Connection connection;
    private static mydatabase instance;

    private mydatabase(){
        try {
            connection=DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("connection!!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());        }
    }
    public static mydatabase getInstance(){
        if(instance==null)

            instance= new mydatabase();
        return instance;

    }

    public Connection getConnection() {
        return connection;
    }
}
