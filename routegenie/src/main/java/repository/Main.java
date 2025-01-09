package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// import main.java.repository.CityDistanceManager;
// import main.java.repository.Comparison;
// import main.java.repository.Menu;

// import main.java.repository.CityDistanceManager;
// import main.java.repository.Comparison;
// import main.java.repository.Menu;

// import main.java.repository.Comparison;
// import main.java.repository.Comparsion;
// import main.java.repository.Menu;
public class Main {
    public static void main(String[] args) throws SQLException {

        /*
         * DB dbManager = new DB("ninjavas.db");
         * CityDistanceManager cityDistanceManager = new CityDistanceManager(dbManager);
         * Inserter ins = new Inserter();
         * ins.createAndInsertCitiesAndDistances(dbManager);
         * 
         * // Initiallizing all the necessery objects from the other classes.
         * NearestNeighbour nn = new NearestNeighbour();
         * Solver svr = new Solver();
         * DynamicProgramming dynamicProg = new DynamicProgramming();
         * Comparison comp = new Comparison();
         * Menu mn = new Menu("ninjavas.db");
         */
        DB dbManager = new DB("ninjavas.db");

        try {
            // Δημιουργία σύνδεσης με την βάση
            Inserter ins = new Inserter();
            ins.createAndInsertCitiesAndDistances(dbManager);
            CityDistanceManager cityDistanceManager = new CityDistanceManager(dbManager);
            String[] cityNames = cityDistanceManager.getAllCities();
            // Initiallizing all the necessery objects from the other classes.
            NearestNeighbour nn = new NearestNeighbour();
            Solver svr = new Solver();
            DynamicProgramming dynamicProg = new DynamicProgramming();
            Comparison comp = new Comparison();
            Menu mn = new Menu("ninjavas.db");
            System.out.println("Cities in the database:");
            // Εισαγωγή αποστάσεων
            double[][] distances = new double[cityNames.length][cityNames.length]; // Δημιουργία πίνακα με βάση τον
                                                                                   // αριθμό των πόλεων
            for (int i = 0; i < cityNames.length; i++) {
                for (int j = 0; j < cityNames.length; j++) {
                    double distance = cityDistanceManager.getDistance(cityNames[i], cityNames[j]);
                    if (distance != -1) {
                        distances[i][j] = distance; // Αποθήκευση της απόστασης στον πίνακα
                    } else {
                        System.out.println("No distance found between " + cityNames[i] + " and " + cityNames[j]);
                    }
                }
            }

            int citiesIndex = 0;
            int firstCityIndex = 0;

            mn.PrintMenu();
            ArrayList<Integer> citiesChosen = mn.ChooseCities(firstCityIndex, citiesIndex);
            distances = dynamicProg.fetchDistancesFromDB(dbManager, citiesIndex);

            List<Integer> bestRouteSolver = dynamicProg.dp(dbManager, firstCityIndex, citiesChosen);
            List<Integer> bestRouteNN = nn.nearestNeighbour(firstCityIndex, distances, bestRouteSolver);
            double nnTotalDistance = svr.totalDist(bestRouteNN, distances);
            double SolverTotalDistance = svr.totalDist(bestRouteSolver, distances);
            // svr.bestRoute(nnTotalDistance, SolverTotalDistance, bestRouteNN, distances,
            // firstCityIndex);

            comp.compareAlgorithms(bestRouteSolver, bestRouteNN, SolverTotalDistance, nnTotalDistance, firstCityIndex,
                    cityNames);

            // Κλείσιμο σύνδεσης
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Κλείσιμο σύνδεσης
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