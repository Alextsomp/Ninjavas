import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//Class that solves the tsp problem
public class Solver {
    // algorithm
    public List<Integer> solve(double[][] distances, int startCity, List<Integer> selected) {

        if (selected == null) {
            throw new IllegalArgumentException("The list of selected cities cannot be null.");
        }

        int N = selected.size();

        if (N <= 1) {
            throw new IllegalArgumentException("The list of selected cities must contain at least two cities.");
        }

        if (!selected.contains(startCity)) {
            throw new IllegalArgumentException("The starting city must be included in the list of selected cities.");
        }

        // Έλεγχος εάν ο πίνακας αποστάσεων είναι συνεπής
        if (distances == null || distances.length == 0) {
            throw new IllegalArgumentException("The distance matrix cannot be null or empty.");
        }

        if (distances.length != distances[0].length) {
            throw new IllegalArgumentException("The distance matrix must be square (N x N).");
        }

        if (startCity < 0 || startCity >= distances.length) {
            throw new IllegalArgumentException("The starting city index is out of bounds.");
        }

        // Χάρτης πόλεων στο υποσύνολο
        Map<Integer, Integer> cityToIndex = new HashMap<>();
        Map<Integer, Integer> indexToCity = new HashMap<>();
        for (int i = 0; i < N; i++) {
            cityToIndex.put(selected.get(i), i);
            indexToCity.put(i, selected.get(i));
        }

        int startIndex = cityToIndex.get(startCity);

        // State όπου έχουν επισκεφθεί όλες οι πόλεις
        final int END_STATE = (1 << N) - 1;

        // Αποθηκεύει τις βέλτιστες τιμές για τη συγκεκριμένη κατάσταση
        Double[][] memo = new Double[N][1 << N];

        // Αρχικοποίηση του πίνακα memo για το πρώτο βήμα
        for (int end = 0; end < N; end++) {
            if (end == startIndex)
                continue;
            memo[end][(1 << startIndex) | (1 << end)] = distances[startIndex][end];
        }

        // Υπολογισμός για κάθε υποσύνολο πόλεων
        for (int r = 3; r <= N; r++) {
            for (int subset : combinations(r, N)) {
                if (notIn(startIndex, subset))
                    continue;
                for (int next = 0; next < N; next++) {
                    if (next == startIndex || notIn(next, subset))
                        continue;
                    int subsetWithoutNext = subset ^ (1 << next);
                    double minDistance = Double.POSITIVE_INFINITY;
                    for (int end = 0; end < N; end++) {
                        if (end == startIndex || end == next || notIn(end, subset))
                            continue;
                        double newDistance = memo[end][subsetWithoutNext] + distances[end][next];
                        if (newDistance < minDistance) {
                            minDistance = newDistance;
                        }
                    }
                    memo[next][subset] = minDistance;
                }
            }
        }

        int lastIndex = startIndex;
        int state = END_STATE;
        List<Integer> tour = new ArrayList<>();
        tour.add(startCity);

        for (int i = 1; i < N; i++) {
            int bestNextIndex = -1;
            double minCost = Double.POSITIVE_INFINITY;
            for (int next = 0; next < N; next++) {
                if (next == lastIndex || notIn(next, state))
                    continue;
                double currentCost = memo[next][state] + distances[next][lastIndex];
                if (currentCost < minCost) {
                    minCost = currentCost;
                    bestNextIndex = next;
                }
            }
            tour.add(indexToCity.get(bestNextIndex));
            state ^= (1 << bestNextIndex);
            lastIndex = bestNextIndex;
        }

        tour.add(startCity);
        Collections.reverse(tour);
        if (tour.get(0) != startCity || tour.get(tour.size() - 1) != startCity) {
            throw new IllegalStateException("The tour does not start and end at the starting city.");
        }

        return tour;
    }

    private static boolean notIn(int elem, int subset) {
        return ((1 << elem) & subset) == 0;
    }

    // Αυτή η μέθοδος δημιουργεί όλους τους συνδυασμούς bit μεγέθους n με r bits σε
    // 1
    public static List<Integer> combinations(int r, int n) {
        List<Integer> subsets = new ArrayList<>();
        combinations(0, 0, r, n, subsets);
        return subsets;
    }

    private static void combinations(int set, int at, int r, int n, List<Integer> subsets) {
        int elementsLeftToPick = n - at;
        if (elementsLeftToPick < r)
            return;

        if (r == 0) {
            subsets.add(set);
        } else {
            for (int i = at; i < n; i++) {
                set |= 1 << i;
                combinations(set, i + 1, r - 1, n, subsets);
                set &= ~(1 << i);
            }
        }
    }

    public int[] nearestNeighbour(int start, double dist[][], List<Integer> selected) {
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
        }
        return Best;
    }
}

//ο πίνακας best  θα είναι το output της μεθόδου BestRoute2.
public double totalDist2(int best[] ,double dist[][] ,List<Integer>selected ) {
    int m = selected.size() + 2;
    double sum = 0;
    for (int i = 1; i <= m-1 ; i++) {
        sum = sum + dist[best[i]][best[i+1]];
    }
    return sum;
}
