package repository;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//Class that solves the tsp problem
public class Solver {
    // algorithm
    public List<Integer> solve(double[][] distances, int startCity, List<Integer> selected) 
        throws IllegalArgumentException, IllegalStateException {

        if (selected == null) {
            throw new IllegalArgumentException("The list of selected cities cannot be null.");
        }

        int N = selected.size(); //N stores the number of cities that was selected

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

        // Cities Map in the subset
        Map<Integer, Integer> cityToIndex = new HashMap<>(); //the HashMaps store key-value pairs
        Map<Integer, Integer> indexToCity = new HashMap<>();
        
        for (int i = 0; i < N; i++) {
            cityToIndex.put(selected.get(i), i);
            indexToCity.put(i, selected.get(i));
        }

        int startIndex = cityToIndex.get(startCity);

        // the state where all the cities have been visited
        final int END_STATE = (1 << N) - 1;

        // Αποθηκεύει τις βέλτιστες τιμές για την συγκεκριμένη κατάσταση
        Double[][] memo = new Double[N][1 << N];

        // Αρχικοποίηση του πίνακα memo για το πρώτο βήμα
        for (int end = 0; end < N; end++) {
            if (end == startIndex)
                continue; //because the distance is 0
            memo[end][(1 << startIndex) | (1 << end)] = distances[startIndex][end];
        }

        // Υπολογισμός για κάθε υποσύνολο πόλεων
        for (int r = 3; r <= N; r++) {
            for (int subset : combinations(r, N)) { //all the possible combinations of r cities from N
                if (notIn(startIndex, subset)) //if the starting city is not in the subset
                    continue;
                for (int next = 0; next < N; next++) {
                    if (next == startIndex || notIn(next, subset)) //if the next city is the same as the starting city or not in the subset
                        continue;
                    
                    int subsetWithoutNext = subset ^ (1 << next); //next is removed from the subset
                    double minDistance = Double.POSITIVE_INFINITY;
                    for (int end = 0; end < N; end++) {
                        if (end == startIndex || end == next || notIn(end, subset)) //if the end is the same as the starting city or the next or if it is not in the subset
                            continue;
                        
                        double newDistance = memo[end][subsetWithoutNext] + distances[end][next]; //the distance from end to next, having visited all of the other cities
                        if (newDistance < minDistance) {
                            minDistance = newDistance;
                        }
                    }
                    memo[next][subset] = minDistance;
                }
            }
        }

        int lastIndex = startIndex; //sets the starting city as the last one
        int state = END_STATE; //END_STATE is the state where all the cities have been visited
        List<Integer> tour = new ArrayList<>(); //this list will store the optimal route
        tour.add(startCity);

        for (int i = 1; i < N; i++) { //a loop for every city in the route, except from the start and the end
            int bestNextIndex = -1; //the next best city has not been found yet
            double minCost = Double.POSITIVE_INFINITY;
            for (int next = 0; next < N; next++) { //a loop that goes through every possible city that can be the best previous city
                if (next == lastIndex || notIn(next, state))
                    continue;
                if (memo[next][state] == null) {
                    memo[next][state] = Double.POSITIVE_INFINITY;
                }
                double currentCost = memo[next][state] + distances[next][lastIndex]; //the total distance to get to next from the lastindex, having visited all of the other cities
                if (currentCost < minCost) {
                    minCost = currentCost;
                    bestNextIndex = next; //it sets the current city as the best next one
                }
            }
            tour.add(indexToCity.get(bestNextIndex)); //the city is added to the list
            state ^= (1 << bestNextIndex); //the city is removed from the cities that we have to visit
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

    public List<Integer> nearestNeighbour(int start, double dist[][], List<Integer> selected) {
        int n = selected.size();
        List<Integer> Best = new ArrayList<>();
        Best.add(start); //Η πρώτη πόλη θα είναι η αφετηρία
        int poli = start;
        int thesiMIN = 0;
        for (int i = 1; i <= n; i++) {
            thesiMIN = 0;
            double MIN = 10000;
            for (int j = 0; j <= n-1; j++) {
                if (selected.get(j) != 0) {
                    if (dist[poli][selected.get(j)] < MIN) {
                        MIN = dist[poli][selected.get(j)];
                        thesiMIN = j;
                    }
                }
            }
            Best.add(selected.get(thesiMIN));
            poli = selected.get(thesiMIN);
            selected.set(thesiMIN, 0);
        }
        Best.add(start); // Η τελευταία πόλη θα είναι η αφετηρία
        return Best;
    }

    //ο πίνακας best  θα είναι το output της μεθόδου nearestNeighbour.
    public double totalDist(List<Integer>best, double dist[][] ) {
        int m = best.size();
        double sum = 0;
        for (int i = 0; i <= m-2; i++) {
            sum = sum + dist[best.get(i)][best.get(i+1)];
        }
        return sum;
    }

    //μέθοδος για σύγκριση αποστάσεων ώστε να βρεθεί ο καλύτερος αλγόριθμος
    public List<Integer> bestRoute(double sum1, double sum2, List<Integer>selected, double [][] distances, int startCity) { 
        //sum1= συνολική απόσταση αλγορίθμου 1
        //sum2= συνολική απόσταση αλγορίθμου 2
        //selected= είναι από τη main, με τις ενδιάμεσες πόλεις που θα επιλέξει ο χρήστης
        //distances= πίνακας με τις αποστάσεις μεταξύ των πόλεων
        //startCity= η πόλη αφετηρία

        List<Integer> bestRoute; //Λίστα για την βέλτιστη διαδρομή
        if (sum1 < sum2) {
            bestRoute = solve(distances, startCity, selected); //Χρησιμοποίησε τον 1ο αλγόριθμο
        } else {
            bestRoute = nearestNeighbour(startCity, distances, new ArrayList<>(selected)); //Χρησιμοποίησε τον 2ο αλγόριθμο
        }
        return bestRoute;
    }
}


