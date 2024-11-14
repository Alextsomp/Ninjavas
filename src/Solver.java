import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

//Class that solves the tsp problem
public class Solver {
    // Solution with Dynamic programming
    private double[][] distances; // distances between each set of cities
    private List<Integer> tour; // list with the optimal route
    private int startCity;
    private int N;
    private int[] citiesToVisit; // cities that need to be visited
    private boolean ranSolver = false; // shows if the algorithm has been executed
    private double minDistance = Double.POSITIVE_INFINITY; // stores the minimum distance of each route

    // Constructor
    public Solver(int startCity, double[][] distances, int[] citiesToVisit) {
        this.N = citiesToVisit.length;
        this.distances = distances;
        this.citiesToVisit = citiesToVisit;
        this.startCity = startCity;

        // validation of variables
        if (N <= 2)
            throw new IllegalStateException("N <= 2 not yet supported.");
        if (N != distances[0].length)
            throw new IllegalStateException("Matrix must be square (n x n)");
        if (startCity < 0 || startCity >= N)
            throw new IllegalArgumentException("Invalid start node.");

    }

    public double getTourCost() {
        if (!ranSolver) // if it is false
            solve(); // calls method solve() to execute the algorithm
        return minDistance;
    }

    // algorithm
    public void solve() {
        if (ranSolver) // if the algorithm is executed return
            return;
        // state where every city is visited
        final int END_STATE = (1 << N) - 1;
        // stores the optimal values for the minimum distance of the specific state.
        Double[][] memo = new Double[N][1 << N];

        for (int end = 0; end < N; end++) { // end is the city to which we are heading
            if (end == startCity)
                continue; // because the distance is 0
            /*
             * route only for 2 cities
             * distance of the route where
             * he starts from the city "start" and has visited only the city "end"
             */
            memo[end][(1 << startCity) | (1 << end)] = distances[startCity][end];
        }
        for (int r = 3; r <= N; r++) { //it starts with a subset of 3 cities, it is the minimum number of cities that can be visited
            for (int subset : combinations(r, N)) { //all the possible combinations of r cities 
                if (notIn(start, subset)) continue; //all subsets must contain the starting city
                    for (int next = 0; next < N; next++) { //next is the last stop after visiting all the cities in the subset
                        if (next == start || notIn(next, subset)) continue; //because the distance is 0 and next is not in the subset
                            int subsetWithoutNext = subset ^ (1 << next); //next is removed from the subset
                            double minDist = Double.POSITIVE_INFINITY; //stores the minimum distance for this subset with next being the last city
                            for (int end = 0; end < N; end++) { //end is the city that will lead to next
                                if (end == start || end == next || notIn(end, subset)) continue; //if it is the same as start or next, or if it is not in the subset
                                    double newDistance = memo[end][subsetWithoutNext] + distance[end][next]; //the distance from end to next, having visited all the cities of subsetWithoutNext
                                    if (newDistance < minDist) {
                                        minDist = newDistance; //if it is smaller than minDist, it becomes the new minimum distance
                                    }
                            }
                            memo[next][subset] = minDist; //the minimum distance of the route is saved to memo
                    }
            }
        }
    }

    public int[] nearestNeighbour(int start, double dist[][], List<Integer> selected) {
        double sum = 0;
        int n = selected.size();
        int k = 1;
        int[] Best = new int[n + 2];
        Best[0] = start;
        Best[n + 1] = start;
        int poli = start;
        int thesiMIN = 0;
        for (int i = 1; i <= n; i++) {
            thesiMIN = 0;
            double MIN = 10000;
            for (int j = 1; j <= n; j++) {
                if (selected.get(j) != 0) {
                    if (dist[poli][selected.get(j)] < MIN) {
                        MIN = dist[poli][selected.get(j)];
                        thesiMIN = j;
                    }
                }
            }
            Best[k] = selected.get(thesiMIN);
            k = k + 1;
            poli = selected.get(thesiMIN);
            selected.set(thesiMIN, 0);
            sum = sum + MIN;
        }
        sum = sum + dist[start][selected.get(thesiMIN)];
        return Best;
    }
}
