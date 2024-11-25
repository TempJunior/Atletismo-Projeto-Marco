package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection recuperaConection(){

        try {
            return createDataSource().getConnection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public HikariDataSource createDataSource(){
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://localhost:3306/atletismo_db");
        config.setUsername("root");
        config.setPassword("joselito157");
        config.setMaximumPoolSize(10);

        return new HikariDataSource(config);
    }

}
