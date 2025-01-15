import gr.aueb.dmst.NinJavas.Algorithms.DynamicProgramming;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicProgrammingTest {
    
    DynamicProgramming dp = new DynamicProgramming();

    @Test
    void validateInputs_nullSelectedList_throwsIllegalArgumentException() {
        double[][] distances = {{0, 1}, {1, 0}};
        int startCity = 0;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            dp.validateInputs(distances, startCity, null)
        );

        assertEquals("The list of selected cities cannot be null.", exception.getMessage());
    }

    @Test
    void validateInputs_startCityNotInSelected_throwsIllegalArgumentException() {
        double[][] distances = {{0, 1}, {1, 0}};
        int startCity = 0;
        var selected = Arrays.asList(1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            dp.validateInputs(distances, startCity, selected)
        );

        assertEquals("The starting city must be included in the list of selected cities.", exception.getMessage());
    }

    @Test
    void validateInputs_nullDistances_throwsIllegalArgumentException() {
        int startCity = 0;
        var selected = Arrays.asList(0, 1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            dp.validateInputs(null, startCity, selected)
        );

        assertEquals("The distance matrix cannot be null or empty.", exception.getMessage());
    }

    @Test
    void validateInputs_emptyDistances_throwsIllegalArgumentException() {
        double[][] distances = {};
        int startCity = 0;
        var selected = Arrays.asList(0, 1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            dp.validateInputs(distances, startCity, selected)
        );

        assertEquals("The distance matrix cannot be null or empty.", exception.getMessage());
    }

    @Test
    void validateInputs_nonSquareDistances_throwsIllegalArgumentException() {
        double[][] distances = {{0, 1, 2}, {1, 0}};
        int startCity = 0;
        var selected = Arrays.asList(0, 1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            dp.validateInputs(distances, startCity, selected)
        );

        assertEquals("The distance matrix must be square (N x N).", exception.getMessage());
    }

    @Test
    void validateInputs_negativeStartCity_throwsIllegalArgumentException() {
        double[][] distances = {{0, 1}, {1, 0}};
        int startCity = -1;
        var selected = Arrays.asList(0, 1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            dp.validateInputs(distances, startCity, selected)
        );
        assertEquals("The starting city must be included in the list of selected cities.", exception.getMessage());
    }

    @Test
    void validateInputs_startCityOutOfBounds_throwsIllegalArgumentException() {
        double[][] distances = {{0, 1}, {1, 0}};
        int startCity = 2;
        var selected = Arrays.asList(0, 1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            dp.validateInputs(distances, startCity, selected)
        );
        assertEquals("The starting city must be included in the list of selected cities.", exception.getMessage());
    }
    
    @Test
    void validateInputs_validInputs_noExceptionThrown() {
        double[][] distances = {{0, 1}, {1, 0}};
        int startCity = 0;
        var selected = Arrays.asList(0, 1);

        assertDoesNotThrow(() -> dp.validateInputs(distances, startCity, selected));
    }
     @Test
     void initializeCityMappings_twoCities_correctMappings() {
        // Selected cities' list
        var selected = Arrays.asList(1, 2);
        Map<Integer, Integer> cityToIndex = new java.util.HashMap<>();
        Map<Integer, Integer> indexToCity = new java.util.HashMap<>();
        dp.initializeCityMappings(selected, cityToIndex, indexToCity);

        //Check for city-index matching
        assertEquals(0, (int) cityToIndex.get(1));
        assertEquals(1, (int) cityToIndex.get(2));

        //Check for index-city matching
        assertEquals(1, (int) indexToCity.get(0));
        assertEquals(2, (int) indexToCity.get(1));
    }

    @Test
    void initializeCityMappings_singleCity_correctMappings() {
        //Selected cities' list with only one city
        var selected = Arrays.asList(5);
        Map<Integer, Integer> cityToIndex = new java.util.HashMap<>();
        Map<Integer, Integer> indexToCity = new java.util.HashMap<>();
        dp.initializeCityMappings(selected, cityToIndex, indexToCity);
        //Check for city-index matching
        assertEquals(0, (int) cityToIndex.get(5));
        //Check for index-city matching
        assertEquals(5, (int) indexToCity.get(0));
    }

    @Test
    void initializeMemo_correctInitialization() {
        double[][] distances = {
            {0, 10, 15},
            {10, 0, 35},
            {15, 35, 0}
        };
        int N = distances.length;
        Double[][] memo = new Double[N][1 << N];
         //startIndex
        int startIndex = 0; // The first city(0)
        dp.initializeMemo(distances, memo, startIndex, N);
        assertEquals(10.0, memo[1][(1 << 0) | (1 << 1)], "The distance between the first and the second city is wrong.");
        assertEquals(15.0, memo[2][(1 << 0) | (1 << 2)], "The distance between the first and the third city is wrong");
        assertNull(memo[0][(1 << 0) | (1 << 0)], "The distance of the first city and back to itself must not be saved.");
    }

    @Test
    void calculateSubsets_correctDistanceCalculation() {
        double[][] distances = {
            {0, 10, 15},
            {10, 0, 35},
            {15, 35, 0}
        };
        int N = distances.length;
        Double[][] memo = new Double[N][1 << N];
        int startIndex = 0;
        dp.initializeMemo(distances, memo, startIndex, N);
        dp.calculateSubsets(distances, memo, startIndex, N);
        int subset01 = (1 << 0) | (1 << 1);
        assertNotNull(memo[1][subset01], "First's city memo and subset 01 are null");
        assertEquals(10.0, memo[1][subset01], "The distance between the first and the second city on subset 01 is incorrect.");
        int subset02 = (1 << 0) | (1 << 2);
        assertNotNull(memo[2][subset02], "Second's city memo and subset 02 are null");
        assertEquals(15.0, memo[2][subset02], "The distance between the first and the third city on subset 02 is incorrect.");
        int fullSubset = (1 << 0) | (1 << 1) | (1 << 2);
        assertNotNull(memo[1][fullSubset], "Second's city memo and the fullsubset are null.");
        assertNotNull(memo[2][fullSubset], "Third's city memo and and the fullsubset are null");
    }

    @Test
    void buildTour_correctTourGeneration() {
        double[][] distances = {
            {0, 10, 15},
            {10, 0, 35},
            {15, 35, 0}
        };
        int N = distances.length;
        Double[][] memo = new Double[N][1 << N];
        int startIndex = 0;
        dp.initializeMemo(distances, memo, startIndex, N);
        dp.calculateSubsets(distances, memo, startIndex, N);
        Map<Integer, Integer> cityToIndex = new HashMap<>();
        Map<Integer, Integer> indexToCity = new HashMap<>();
        dp.initializeCityMappings(List.of(0, 1, 2), cityToIndex, indexToCity);
        int END_STATE = (1 << N) - 1;
        List<Integer> tour = dp.buildTour(distances, memo, 0, cityToIndex, indexToCity, END_STATE, startIndex);
        assertEquals(0, (int) tour.get(0), "The route does not begin from the first city");
        assertEquals(0, (int) tour.get(tour.size() - 1), "The route does not end at the first city");
        assertTrue(tour.contains(0), "The first city does not exist on the route");
        assertTrue(tour.contains(1), "The second city does not exist on the route");
        assertTrue(tour.contains(2), "The third city does not exist on the route");
        assertEquals(4, tour.size(), "The route does not contain the valid number of the cities");
        assertEquals(0, (int) tour.get(tour.size() - 1), "Route does not end at the first city");
        assertTrue(tour.indexOf(1) > tour.indexOf(0), "The second city does not follow the first one on the route.");
        assertFalse(tour.indexOf(2) > tour.indexOf(1), "The third city does not follow the second one on the route");
    }

    @Test
    void testNotIn_elementIsNotInSubset() {
        int subset = 0b0101; 
                int elem = 3;
        assertTrue(DynamicProgramming.notIn(elem, subset), "Element 3 must not be in the subset.");
    }

    @Test
    void testNotIn_elementIsInSubset() {
        int subset = 0b0110;
        int elem = 1;
        assertFalse(DynamicProgramming.notIn(elem, subset), "Element 1 must not be in the subset.");
    }

    @Test
    void testNotIn_emptySubset() {
        int subset = 0b0000;
        int elem = 0;
        assertTrue(DynamicProgramming.notIn(elem, subset), "Element 0 must not be in the subset.");
    }

    @Test
    void testNotIn_singleElementSubset() {
        int subset = 0b0100;
        int elem = 2;
        assertFalse(DynamicProgramming.notIn(elem, subset), "Element 2 must not be in the subset.");
    }

    @Test
    void testNotIn_largeSubset() {
        int subset = 0b111111;
        int elem = 5;
        assertFalse(DynamicProgramming.notIn(elem, subset), "Το στοιχείο 5 πρέπει να είναι στο υποσύνολο.");
    }

    @Test
    void testCombinations_basic() {
        int r = 2;
        int n = 4;

        List<Integer> result = DynamicProgramming.combinations(r, n);

        // The combinations must be:
        // 01 (0,1), 02 (0,2), 03 (0,3), 12 (1,2), 13 (1,3), 23 (2,3)
        // The bitmasks are 3, 5, 9, 6, 10, 12

        List<Integer> expected = List.of(0b0011, 0b0101, 0b1001, 0b0110, 0b1010, 0b1100);

        assertEquals(expected, result, "The combinations for r=2 and n=4 are incorrect.");
    }

    @Test
    void testCombinations_edgeCase_r0() {
        int r = 0;
        int n = 4;

        List<Integer> result = DynamicProgramming.combinations(r, n);

        // only null subset must exist
        List<Integer> expected = List.of(0b0000);

        assertEquals(expected, result, "The combinations for r=0 and n=4 are incorrect.");
    }

    @Test
    void testCombinations_rEqualToN() {
        int r = 4;
        int n = 4;

        List<Integer> result = DynamicProgramming.combinations(r, n);

        List<Integer> expected = List.of(0b1111);

        assertEquals(expected, result, "The combinations for r=4 and n=4 are incorrect.");
    }

    @Test
    void testCombinations_rGreaterThanN() {
        int r = 5;
        int n = 4;

        List<Integer> result = DynamicProgramming.combinations(r, n);

        List<Integer> expected = List.of();

        assertEquals(expected, result, "The combinations for r=5 and n=4 are incorrect.");
    }

    @Test
    void testCombinations_singleCombination() {

        int r = 1;
        int n = 1;

        List<Integer> result = DynamicProgramming.combinations(r, n);

        List<Integer> expected = List.of(0b0001);

        assertEquals(expected, result, "he combinations for r=1 and n=1 are incorrect.");
    }
}
