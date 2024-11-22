package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection recuperaConection(){

        try {
            return DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/atletismo_db?user=root&password=joselito157");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
