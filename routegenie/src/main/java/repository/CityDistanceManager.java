package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import repository.DB;

public class CityDistanceManager {

    private DB db;

    public CityDistanceManager(DB db) {
        this.db = db;
    }

    public List<Integer> getAllCities() throws SQLException {
        try {
            String query = "SELECT name FROM cities";
            Connection connection = db.getConnection();
            Statement stmt = connection.createStatement(); 
            ResultSet rs = stmt.executeQuery(query);
            List<String> cityNames = new ArrayList<>();
            while (rs.next()) {
                cityNames.add(rs.getString("name"));
            }
            return cityNames.toArray(new String[0]);
         }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Get the distance between two cities from the database
    public double getDistance(String city1, String city2) throws SQLException {
        String query = "SELECT distance FROM CityDistances WHERE from_city = ? AND to_city = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, city1);
            stmt.setString(2, city2);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("distance");
                }
            }
        }
        return -1; // Return -1 if no distance is found
    }

    }