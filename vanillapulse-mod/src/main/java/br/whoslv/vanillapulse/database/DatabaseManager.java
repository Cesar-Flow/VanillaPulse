package br.whoslv.vanillapulse.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private Connection connection;

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Usuario/Documents/VanillaPulse/vanillapulse-mod/src/main/java/br/whoslv/vanillapulse/database/banco.db");
            System.out.println("Banco conectado");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void disconnect() {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
