package gr.aueb.dmst.NinJavas.GUI;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

//Class that finds the best route and the total distance
public class SolverGui {
    public DynamicProgrammingGui dpSolver = new DynamicProgrammingGui();
    public NearestNeighbourGui nnSolver = new NearestNeighbourGui();

    /**
     * Returns the total distances covered by the fiven route.
     * 
     * @param route
     * @param distances
     * @return totalDistance
     */
    public double totalDistance(List<Integer> route, double[][] distances) {
        double totalDistance = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            totalDistance += distances[route.get(i)][route.get(i + 1)];
        }

        return totalDistance;
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

        List<Integer> bestRoute; // List for the best route
        if (sum1 <= sum2) {
            bestRoute = dpSolver.dp(distances, startCity, selected); // Use 1st algorithm
        } else {
            bestRoute = nnSolver.nearestNeighbour(startCity, distances, selected); // Use 2nd algorithm
        }
        return bestRoute;
    }
}