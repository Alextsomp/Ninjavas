package repository;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//Class that finds the best route and the total distance
public class Solver {

    public DynamicProgramming dpSolver = new DynamicProgramming();
    public NearestNeighbour nnSolver = new NearestNeighbour();

    // The List "best" will be the output of method "nearestNeighbour".
    public double totalDist(List<Integer> best, double dist[][]) {
        int m = best.size();
        double sum = 0;
        for (int i = 0; i <= m - 2; i++) {
            sum = sum + dist[best.get(i)][best.get(i + 1)];
        }
        return sum;
    }

    // compare distances to find the best algorithm
    public List<Integer> bestRoute(double sum1, double sum2, List<Integer> selected, double[][] distances,
            int startCity) throws SQLException {
        // sum1= total distance 1st algorithm
        // sum2= total distance 2nd algorithm
        // selected= from main method, contains the cities the user wnats to visit
        // (minus the 1st)
        // distances= table with the distances between cities
        // startCity= the starting city

        DB dbManager = new DB("ninjavas.db");

        List<Integer> bestRoute; // List for the best route
        if (sum1 <= sum2) {
            bestRoute = dpSolver.dp(dbManager, startCity, selected); // Use 1st algorithm
        } else {
            bestRoute = nnSolver.nearestNeighbour(startCity, distances, new ArrayList<>(selected)); // Use 2nd algorithm
        }
        return bestRoute;
    }
}