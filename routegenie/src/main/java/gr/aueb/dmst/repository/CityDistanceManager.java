package gr.aueb.dmst.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDistanceManager {

    private DB db;

    public CityDistanceManager(DB db) {
        this.db = db;
    }

    public List<Integer> getAllCities() throws SQLException {
        try {
            String query = "SELECT id FROM cities";
            Connection connection = db.getConnection();
            Statement stmt = connection.createStatement(); 
            ResultSet rs = stmt.executeQuery(query);
            List<Integer> cityNames = new ArrayList<>();
            while (rs.next()) {
                cityNames.add(rs.getInt("id"));
            }
            return cityNames;
         }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public List<String> getCityNames() {
        try {

            String query = "SELECT name FROM cities";
            Connection connection = db.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            List<String> cityNames = new ArrayList<>();
            while (rs.next()) {
                cityNames.add(rs.getString("name"));
            }
            return cityNames;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getCityNameByCityId(int cityId) {
        String query = "SELECT name FROM cities where id=?;";
        String cityName = null;
        try {
            Connection connection = db.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, cityId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cityName = rs.getString("name");
            }
            return cityName;
        } catch (Exception e) {
            System.out.println("Exception while fetching city's name by city's id:  " + e);
        }
        return cityName;
    }

    // Get the distance between two cities from the database
    public double getDistance(int city1Id, int city2Id) throws SQLException {
        String query = "SELECT distance FROM distances WHERE from_city = ? AND to_city = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setInt(1, city1Id);
            stmt.setInt(2, city2Id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("distance");
                }
            }
        }
        return -1; // Return -1 if no distance is found
    }

    public void printTableContext() {
        String query = "SELECT * FROM distances;";

        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("distance"));
            }
        } catch (SQLException e) {
            System.out.println("SQL: " + e);
        }

    }

    }