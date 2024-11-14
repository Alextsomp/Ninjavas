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
