import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Solver {

    private double[][] distances; // πινακας distances απο την CitiesAndDistances
    private List<Integer> tour;
    private int startCity;
    private int N;
    private int[] citiesToVisit;
    private boolean ranSolver = false; // υποδεικνυει αν εχει εκτελεστει ο αλγοριθμος
    private double minDistance = Double.POSITIVE_INFINITY; // αποθηκευει την ελαχιστη αποσταση της μεχρι τοτε διαδρομης

    public Solver(int startCity, double[][] distances) {
        this.N = citiesToVisit.length;
        this.distances = distances;

        if (N <= 2)
            throw new IllegalStateException("N <= 2 not yet supported.");
        if (N != distances[0].length)
            throw new IllegalStateException("Matrix must be square (n x n)");
        if (startCity < 0 || startCity >= N)
            throw new IllegalArgumentException("Invalid start node.");

        this.startCity = startCity;
    }

    public double getTourCost() {
        if (!ranSolver) // αν ειναι false
            solve(); // καλείται η μέθοδος solve() για να εκτελέσει τον αλγόριθμο και να υπολογίσει
                     // το ελάχιστο κόστος της διαδρομής
        return minDistance; // επιστρεφει το ελαχιστο κοστος
    }

    // αλγοριθμος
    public void solve() {
        if (ranSolver) // αν εχει εκτελεστει ο αλγοριθμος επεστρεψε
            return;
        // κατασταση οπου ολες οι πολεις εχουν επισκεφθει
        final int END_STATE = (1 << N) - 1;
        // αποθηκεύει τις βέλτιστες τιμές που αντιστοιχούν στο ελάχιστο κόστος που
        // απαιτείται για να φτάσεις σε μια συγκεκριμένη πόλη με συγκεκριμένες πόλεις
        // επισκεμμένες.
        Double[][] memo = new Double[N][1 << N];

        for (int end = 0; end < N; end++) { // end ειναι καθε φορα η πολη στην οποια κατευθυνεται
            if (end == startCity)
                continue; // αποτρέπει τον υπολογισμό της πόλης εκκίνησης προς τον εαυτό της.
            /*
             * υπολογισμος διαδρομης μεχρι 2 πολεις:
             * η αποσταση της διαδρομης
             * στην οποια εχει ξεκινησει απο την start και εχει επισκεφθει την πολη end
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
