package repository;

import java.util.List;
import java.util.ArrayList;


//Class that solves the tsp problem with algorithm Nearest Neighbour
public class NearestNeighbour {
    // algorithm
    public List<Integer> nearestNeighbour(int start, double dist[][], List<Integer> selected) {
        int n = selected.size();
        List<Integer> Best = new ArrayList<>();
        Best.add(start); // The first city will be the starting point
        int StartingPoint = start;
        selected.set(0, 0);
        int NextCity = 0;
        for (int i = 1; i <= n; i++) {
            NextCity = 0;
            double MIN = 10000;
            for (int j = 0; j <= n - 1; j++) {
                if (selected.get(j) != 0) {
                    if (dist[StartingPoint][selected.get(j)] < MIN) {
                        MIN = dist[StartingPoint][selected.get(j)];
                        NextCity = j;
                    }
                }
            }
            Best.add(selected.get(NextCity));
            StartingPoint = selected.get(NextCity);
            selected.set(NextCity, 0);
        }
        // Best.add(start); // User will eventually return to the starting point
        return Best;
    }
}