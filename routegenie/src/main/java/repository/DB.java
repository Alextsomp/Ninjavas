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

    public Connection getConnection() {
        return this.connection;
    }

    // Close the database connection
    public void closeConnection() throws SQLException {
        if (this.connection != null && !this.connection.isClosed()) {
            this.connection.close();
            System.out.println("Connection closed.");
        }
    }
    public int getDistances(int fromCity, int toCity) throws SQLException {
        String query = "SELECT distance FROM distances WHERE (from_city = ? AND to_city = ?)";
        try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setInt(1, fromCity);
            stmt.setInt(2, toCity);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("distance");
            } else {
                System.out.println("No distance found between city " + fromCity + " and city " + toCity);
                return -1;  // -1 to indicate no result found
            }
        }
    }

    // Example usage
   /* public static void main(String[] args) {
        try {
            DB dbManager = new DB("ninjavas.db");

            // Close the connection
            dbManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        */
}
