package gr.aueb.dmst.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 * Nearest Neighbour Algorithm model.
 */
public class NearestNeighbour {
    /**
     * Returns the best way to visit the selected cities, based on the
     * implementation nearest neighbour algorithm.
     * 
     * @param dist
     * @param selected
     * @return List<Integer>
     */
    public List<Integer> nearestNeighbour(double dist[][], List<Integer> selectedCities) {
        int startingPoint = selectedCities.get(0);
        int n = selectedCities.size();

        List<Integer> bestRoute = new ArrayList<>();
        // The first city will be the starting point
        bestRoute.add(startingPoint);

        Set<Integer> visited = new HashSet<>();
        visited.add(startingPoint);

        while (bestRoute.size() < n) {
            int nextCity = -1;
            double minDistance = Double.MAX_VALUE;

            for (int city : selectedCities) {
                if (!visited.contains(city) && dist[startingPoint][city] < minDistance) {
                    minDistance = dist[startingPoint][city];
                    nextCity = city;
                }
            }

            if (nextCity == -1) {
                break;
            }
            bestRoute.add(nextCity);
            visited.add(nextCity);
            startingPoint = nextCity;
        }
        // We return to the first city, after visiting all the selected cities
        bestRoute.add(bestRoute.get(0));

        return bestRoute;
    }
}