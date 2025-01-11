package repository;

import java.sql.*;

public class DB {

    private Connection connection;

    // Constructor: Establish connection to the SQLite database
    public DB(String databasePath) throws SQLException {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            // Establish connection
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
            System.out.println("Connected to database successfully.");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC driver not found.", e);
        }
    }

    //Method to create new connection
    public Connection getConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            System.out.println("Database connection is null or closed.");
            connect();
        }
        return this.connection;
    }

    public void connect() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            try {
                String url = "jdbc:sqlite:ninjavas.db";
                this.connection = DriverManager.getConnection(url);
                System.out.println("Connection successfully established.");
            } catch (SQLException e) {
                System.err.println("Failed to establish a database connection: " + e.getMessage());
                throw e;
            }
        }
    }

    // Close the database connection
    public void closeConnection() throws SQLException {
        if (this.connection != null && !this.connection.isClosed()) {
            this.connection.close();
            System.out.println("Connection closed.");
        }
    }
}
