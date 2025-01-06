package main.java.repository;

import java.sql.*;

import repository.DB;

public class CityDistanceManager {

    private DB db;

    public CityDistanceManager(DB db) {
        this.db = db;
    }
   public String[] getAllCities() throws SQLException {
        String[] cityList = new String[15]; // Ορίζουμε το μέγεθος του πίνακα για τις πόλεις (αυτό μπορεί να αλλάξει)
        String query = "SELECT name FROM cities"; // Ερώτημα για να πάρουμε όλα τα ονόματα των πόλεων

        try (Statement stmt = db.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            int index = 0;
            while (rs.next()) {
                cityList[index++] = rs.getString("name"); // Αποθηκεύουμε το όνομα της πόλης στον πίνακα
            }
        }
        return cityList;
    }
    
    // Get the distance between two cities from the database
    public double getDistance(String city1, String city2) throws SQLException {
        String query = "SELECT distance FROM CityDistances WHERE city1 = ? AND city2 = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, city1);
            stmt.setString(2, city2);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("distance");
                }
            }
        }
        return -1;  // Return -1 if no distance is found
    }
}

