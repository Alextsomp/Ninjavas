package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {    
    public static void main(String[] args) throws SQLException {
        
        DB dbManager = new DB("ninjavas.db");

        try {
            // Database Connection
            Inserter ins = new Inserter();
            ins.createAndInsertCitiesAndDistances(dbManager);
            CityDistanceManager cityDistanceManager = new CityDistanceManager(dbManager);
            Integer[] cityIdsInt = cityIdsList.toArray(new Integer[0]);

            int[] cityIds = new int[cityIdsInt.length];
            for (int i = 0; i < cityIdsInt.length; i++) {
                cityIds[i] = cityIdsInt[i];
            }

            // Initiallizing all the necessery objects from the other classes.
            NearestNeighbour nn = new NearestNeighbour();
            Solver solver = new Solver();
            DynamicProgramming dynamicProg = new DynamicProgramming();
            Comparison comparison = new Comparison();
            Menu menu = new Menu("ninjavas.db");

            // Store the distances between the cities in an array in order to not query the DB continually
            double[][] distances = new double[cityIds.length][cityIds.length]; 
            
            for (int i = 0; i < cityIds.length; i++) {
                for (int j = 0; j < cityIds.length; j++) {
                    double distance = cityDistanceManager.getDistance(cityIds[i], cityIds[j]);
                    if (distance != -1) {
                        distances[i][j] = distance; // Save the distance in the array
                    } 
                }
            }

            menu.PrintMenu();
            ArrayList<Integer> citiesChosen = menu.chooseCities();
            
            // Calculate the best routes, based on the implementation of two algorithms
            List<Integer> bestRouteSolver = dynamicProg.dp(distances, dbManager, citiesChosen);
            List<Integer> bestRouteNN = nn.nearestNeighbour(distances, citiesChosen);

            // Based on the best routes, calculate the total distance
            double nnTotalDistance = solver.totalDistance(bestRouteNN, distances);
            double solverTotalDistance = solver.totalDistance(bestRouteSolver, distances);

            comparison.compareAlgorithms(bestRouteSolver, bestRouteNN, solverTotalDistance,
                nnTotalDistance, 0, cityIds);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources: Database connection
            if (dbManager != null) {
                try {
                    dbManager.closeConnection();
                } catch (SQLException e) {
                    System.err.println("Failed to close the database connection.");
                    e.printStackTrace();
                }
            }
        }
    }
}