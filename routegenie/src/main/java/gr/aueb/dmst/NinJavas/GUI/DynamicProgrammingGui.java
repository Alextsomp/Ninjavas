package gr.aueb.dmst.NinJavas.GUI;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//Class that solves the TSP problem with Dynamic Programming
public class DynamicProgrammingGui {
	
    public List<Integer> dp(double[][] distances, int startCity, List<Integer> selected)
            throws IllegalArgumentException, IllegalStateException {

        validateInputs(distances, startCity, selected);

        int N = selected.size(); // N stores the number of cities that was selected
        // Cities Map in the subset
        Map<Integer, Integer> cityToIndex = new HashMap<>(); // the HashMaps store key-value pairs
        Map<Integer, Integer> indexToCity = new HashMap<>();
        initializeCityMappings(selected, cityToIndex, indexToCity);

        int startIndex = cityToIndex.get(startCity); // startIndex stores the index of the starting city
        // the state where all the cities have been visited
        final int END_STATE = (1 << N) - 1;
        // Stores the optimal values for the current state
        Double[][] memo = new Double[N][1 << N];

        initializeMemo(distances, memo, startIndex, N);
        calculateSubsets(distances, memo, startIndex, N);

        return buildTour(distances, memo, startCity, cityToIndex, indexToCity, END_STATE, startIndex);
    }

    public void validateInputs(double[][] distances, int startCity, List<Integer> selected) {

        if (selected == null) {
            throw new IllegalArgumentException("The list of selected cities cannot be null."); // no cities are selected
        }

        if (!selected.contains(startCity)) {
            throw new IllegalArgumentException("The starting city must be included in the list of selected cities.");
        }

        // Checks if the matrix is consistent
        if (distances == null || distances.length == 0) {
            throw new IllegalArgumentException("The distance matrix cannot be null or empty.");
        }

        if (distances.length != distances[0].length) {
            throw new IllegalArgumentException("The distance matrix must be square (N x N).");
        }

        if (startCity < 0 || startCity >= distances.length) {
            throw new IllegalArgumentException("The starting city index is out of bounds.");
        }
    }

    public void initializeCityMappings(List<Integer> selected, Map<Integer, Integer> cityToIndex,
            Map<Integer, Integer> indexToCity) {

        for (int i = 0; i < selected.size(); i++) { // the loop iterates through the 'selected' list, using 'i' as the
                                                    // index
            cityToIndex.put(selected.get(i), i); // stores the mapping of each city to its corresponding numeric index
            indexToCity.put(i, selected.get(i)); // stores the mapping of each index to its corresponding city
        }
    }

    public void initializeMemo(double[][] distances, Double[][] memo, int startIndex, int N) {
        // Initialization of the matrix memo for the first step
        for (int end = 0; end < N; end++) {
            if (end == startIndex)
                continue; // because the distance is 0
            memo[end][(1 << startIndex) | (1 << end)] = distances[startIndex][end];
        }
    }

    public void calculateSubsets(double[][] distances, Double[][] memo, int startIndex, int N) {
        // Calculation for all of the subsets of cities
        for (int r = 3; r <= N; r++) {
            for (int subset : combinations(r, N)) { // all the possible combinations of r cities from N
                if (notIn(startIndex, subset)) // if the starting city is not in the subset
                    continue;
                for (int next = 0; next < N; next++) {
                    if (next == startIndex || notIn(next, subset)) // if the next city is the same as the starting city
                                                                   // or not in the subset
                        continue;

                    int subsetWithoutNext = subset ^ (1 << next); // next is removed from the subset
                    double minDistance = Double.POSITIVE_INFINITY;
                    for (int end = 0; end < N; end++) {
                        if (end == startIndex || end == next || notIn(end, subset)) // if the end is the same as the
                                                                                    // starting city or the next or if
                                                                                    // it is not in the subset
                            continue;

                        double newDistance = memo[end][subsetWithoutNext] + distances[end][next]; // the distance from
                                                                                                  // end to next, having
                                                                                                  // visited all of the
                                                                                                  // other cities
                        if (newDistance < minDistance) {
                            minDistance = newDistance;
                        }
                    }
                    memo[next][subset] = minDistance;
                }
            }
        }
    }

    public List<Integer> buildTour(double[][] distances, Double[][] memo, int startCity,
            Map<Integer, Integer> cityToIndex, Map<Integer, Integer> indexToCity,
            int END_STATE, int startIndex) {

        int lastIndex = startIndex; // sets the starting city as the last one
        int state = END_STATE; // END_STATE is the state where all the cities have been visited
        List<Integer> tour = new ArrayList<>(); // this list will store the optimal route
        tour.add(startCity);

        for (int i = 1; i < cityToIndex.size(); i++) { // a loop for every city in the route, except from the start and
                                                       // the end
            int bestNextIndex = -1; // the next best city has not been found yet
            double minCost = Double.POSITIVE_INFINITY;
            for (int next = 0; next < cityToIndex.size(); next++) { // a loop that goes through every possible city that
                                                                    // can be the best
                // previous city
                if (next == lastIndex || notIn(next, state))
                    continue;
                if (memo[next][state] == null) {
                    memo[next][state] = Double.POSITIVE_INFINITY;
                }
                double currentCost = memo[next][state] + distances[next][lastIndex]; // the total distance to get to
                                                                                     // next from the lastindex, having
                                                                                     // visited all of the other cities
                if (currentCost < minCost) {
                    minCost = currentCost;
                    bestNextIndex = next; // it sets the current city as the best next one
                }
            }
            tour.add(indexToCity.get(bestNextIndex)); // the city is added to the list
            state ^= (1 << bestNextIndex); // the city is removed from the cities that we have to visit
            lastIndex = bestNextIndex;
        }

        tour.add(startCity); // startCity is added to the end of the list 'tour' to ensure it starts and ends
                             // with the same city
        Collections.reverse(tour); // Reverses the order of the tour
        if (tour.get(0) != startCity || tour.get(tour.size() - 1) != startCity) { // checks is the first and last city
                                                                                  // are the same
            throw new IllegalStateException("The tour does not start and end at the starting city.");
        }

        return tour;
    }

    public static boolean notIn(int elem, int subset) { // checks whether a given element is present in the subset
        return ((1 << elem) & subset) == 0; // checks if the elem bit is in subset is set to 1, if not, it returns true,
                                            // meaning it is not in the subset
    }

    public static List<Integer> combinations(int r, int n) { // it returns a list in which every value represents a
                                                             // different combination
        List<Integer> subsets = new ArrayList<>(); // subsets stored the combinations
        combinations(0, 0, r, n, subsets); // r is the number of elements we have to choose, n is the total number of
                                           // elements
        return subsets;
    }

    public static void combinations(int set, int at, int r, int n, List<Integer> subsets) {
        int elementsLeftToPick = n - at; // calculates how many elements are left to pick starting from the current
                                         // position
        if (elementsLeftToPick < r) // if there are less elements left than needed
            return;

        if (r == 0) { // if no more elements need to be picked
            subsets.add(set); // 'set' is the current combination as a bitmask
        } else {
            for (int i = at; i < n; i++) { // iterates through all the elements from the current position to the end
                set |= 1 << i; // the bit of the current element is set to 1, in order for it to be added to
                               // the combination
                combinations(set, i + 1, r - 1, n, subsets); // recursively picks the remaining elements, starting from
                                                             // the next position
                set &= ~(1 << i); // the bit of the current element is set to 0, in order for it to be removed
                                  // from the combination
            }
        }
    }
}