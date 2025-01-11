// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;
// import repository.Solver;
// import org.junit.jupiter.api.BeforeEach;
// import java.util.*;

// public class SolverTest {
    
//     private Solver solver;
    
//     @BeforeEach
//     public void setup(){
//     solver = new Solver();
//    }

//     @Test
//     void testValidateInputs_withValidInputs() {
//         double[][] distances = {
//             {0, 10, 15, 20},
//             {10, 0, 35, 25},
//             {15, 35, 0, 30},
//             {20, 25, 30, 0}
//         };
//         int startCity = 0;
//         List<Integer> selected = Arrays.asList(0, 1, 2, 3);
//         assertDoesNotThrow(() -> solver.validateInputs(distances, startCity, selected),
//             "validateInputs should not throw an exception for valid inputs");
//     }

//     @Test
//     void testValidateInputs_withNullSelectedList() {
//         double[][] distances = {
//             {0, 10, 15, 20},
//             {10, 0, 35, 25},
//             {15, 35, 0, 30},
//             {20, 25, 30, 0}
//         };
//         int startCity = 0;

//         IllegalArgumentException exception = assertThrows(
//             IllegalArgumentException.class,
//             () -> solver.validateInputs(distances, startCity, null),
//             "Expected validateInputs to throw IllegalArgumentException for null selected list"
//         );

//         assertTrue(exception.getMessage().contains("The list of selected cities cannot be null"),
//             "Exception message should mention that the selected list cannot be null");
//     }

//     @Test
//     void testValidateInputs_withStartCityNotInSelected() {
//         double[][] distances = {
//             {0, 10, 15, 20},
//             {10, 0, 35, 25},
//             {15, 35, 0, 30},
//             {20, 25, 30, 0}
//         };
//         int startCity = 4; // Invalid start city not in selected
//         List<Integer> selected = Arrays.asList(0, 1, 2, 3);

//         IllegalArgumentException exception = assertThrows(
//             IllegalArgumentException.class,
//             () -> solver.validateInputs(distances, startCity, selected),
//             "Expected validateInputs to throw IllegalArgumentException for start city not in selected"
//         );

//         assertTrue(exception.getMessage().contains("The starting city must be included"),
//             "Exception message should mention that the starting city must be included");
//     }

//     @Test
//     void testValidateInputs_withNullOrEmptyDistances() {
//         double[][] distances = null;
//         int startCity = 0;
//         List<Integer> selected = Arrays.asList(0, 1, 2, 3);

//         IllegalArgumentException exception = assertThrows(
//             IllegalArgumentException.class,
//             () -> solver.validateInputs(distances, startCity, selected),
//             "Expected validateInputs to throw IllegalArgumentException for null distances"
//         );

//         assertTrue(exception.getMessage().contains("The distance matrix cannot be null"),
//             "Exception message should mention that the distance matrix cannot be null");

//         double[][] emptyDistances = new double[0][0]; // Empty distances
//         exception = assertThrows(
//             IllegalArgumentException.class,
//             () -> solver.validateInputs(emptyDistances, startCity, selected),
//             "Expected validateInputs to throw IllegalArgumentException for empty distances"
//         );

//         assertTrue(exception.getMessage().contains("The distance matrix cannot be null"),
//             "Exception message should mention that the distance matrix cannot be null");
//     }

//     @Test
//     void testValidateInputs_withNonSquareDistances() {
//         double[][] distances = {
//             {0, 10, 15},
//             {10, 0, 35}
//         }; // Non-square matrix
//         int startCity = 0;
//         List<Integer> selected = Arrays.asList(0, 1, 2);

//         IllegalArgumentException exception = assertThrows(
//             IllegalArgumentException.class,
//             () -> solver.validateInputs(distances, startCity, selected),
//             "Expected validateInputs to throw IllegalArgumentException for non-square distances"
//         );

//         assertTrue(exception.getMessage().contains("The distance matrix must be square"),
//             "Exception message should mention that the distance matrix must be square");
//     }

//     @Test
//     void testValidateInputs_withStartCityOutOfBounds() {
//         double[][] distances = {
//             {0, 10, 15, 20},
//             {10, 0, 35, 25},
//             {15, 35, 0, 30},
//             {20, 25, 30, 0}
//         };
//         int startCity = -1; // Out of bounds
//         List<Integer> selected = Arrays.asList(0, 1, 2, 3);

//         IllegalArgumentException exception = assertThrows(
//             IllegalArgumentException.class,
//             () -> solver.validateInputs(distances, startCity, selected),
//             "Expected validateInputs to throw IllegalArgumentException for start city out of bounds"
//         );

//         assertTrue(exception.getMessage().contains("The starting city index is out of bounds"),
//             "Exception message should mention that the starting city index is out of bounds");
//     }

//    @Test
//     public void nearestNeighbourTest () {
//         double[][] distances = {
//             {0, 494, 678, 798},
//             {494, 0, 397, 292},
//             {678, 397, 0, 473},
//             {798, 292, 473, 0}
//         };
//         List<Integer> selected = new ArrayList<>(Arrays.asList(1, 2, 3));
//         List<Integer> expectedTour = Arrays.asList(0, 1, 3, 2);
//         List<Integer> result = solver.nearestNeighbour(0, distances, selected);
//         assertEquals(expectedTour, result, "nearestNeighbour method does not return the right tour");
//     }
//     @Test
//     public void combinationsTest() {
//         //example of 4 elements and 2 combinations
//         int n = 4;
//         int r = 2;
//         List<Integer> result = Solver.combinations(r, n);
//         List<Integer> expected = List.of(3, 5, 6, 9, 10, 12);//expected combinations
//         assertEquals(expected.size(), result.size(), "the number of combinations is not right");
//         assertTrue(result.containsAll(expected) && expected.containsAll(result), "combinations are not right");
//     }
//     @Test
//     public void CombinationsEdgeCaseTest() {
//         //case when r=0
//         int n = 5;
//         int r = 0;

//         List<Integer> result = Solver.combinations(r, n);
//         //zero space expected
//         List<Integer> expected = List.of(0);
//         assertEquals(expected, result, "The method does not return the right result when r=0, it has nothing to return.");
//     }
//     @Test
//     public void CombinationsFullSetTest() {
//         //Case when r=n
//         //(it has to return all sets of combinations)
//         int n = 3;
//         int r = 3;
//         List<Integer> result = Solver.combinations(r, n);

//         //Expected all sets
//         //111 (binary), which means 7
//         List<Integer> expected = List.of(7);
//         assertEquals(expected, result, "The method does not return the right result when r=0, all sets of combinations must be included");
//     }

//     @Test
//     public void CombinationsInvalidCaseTest() {
//         //case when r>n
//         //(null space expected)
//         int n = 3;
//         int r = 4;

//         List<Integer> result = Solver.combinations(r, n);

//         //expected result: no combinations.
//         List<Integer> expected = List.of();
//         assertEquals(expected, result, "The method does not return the null space when r>n.");
//     }

//     @Test
//     public void testCombinations() {
//         List<Integer> subsets = new ArrayList<>();
//         int r = 2; //number of combinations
//         int n = 3; //number of elements
//         Solver.combinations(0, 0, r, n, subsets);
//         //expected combinations
//         List<Integer> expected = List.of(
//             0b011,
//             0b101,
//             0b110
//         );
//         assertEquals(expected, subsets);
//     }
    
//     @Test
//     void testNotIn() {
//         // Case 1: Element is in the subset
//         int elem = 1;
//         int subset = 0b0010; // Binary: subset contains element 1 (010)
//         // Element 1 is in the subset (binary representation 0010), so it should return false.
//         assertFalse(Solver.notIn(elem, subset), "Element 1 should be in the subset");

//         // Case 2: Element is not in the subset
//         elem = 2;
//         subset = 0b0010; // Binary: subset contains element 1, but not element 2
//         // Element 2 is not in the subset (binary representation 0010), so it should return true.
//         assertTrue(Solver.notIn(elem, subset), "Element 2 should not be in the subset");

//         // Case 3: Element 0 is in the subset
//         elem = 0;
//         subset = 0b0001; // Binary: subset contains element 0 (001)
//         // Element 0 is in the subset (binary representation 0001), so it should return false.
//         assertFalse(Solver.notIn(elem, subset), "Element 0 should be in the subset");

//         // Case 4: Element is not in the subset (subset is empty)
//         elem = 1;
//         subset = 0b0000; // Binary: empty subset
//         // Element 1 is not in the subset (binary representation 0000), so it should return true.
//         assertTrue(Solver.notIn(elem, subset), "Element 1 should not be in an empty subset");

//         // Case 5: Element is not in the subset, but a larger subset
//         elem = 3;
//         subset = 0b1010; // Binary: subset contains element 1 and element 3, but not element 2
//         // Element 3 is in the subset (binary representation 1010), so it should return false.
//         assertFalse(Solver.notIn(elem, subset), "Element 3 should be in the subset");

//         // Case 6: Element is outside the valid range
//         elem = 4; // Out of valid range
//         subset = 0b0111; // Binary: subset with elements 0, 1, and 2
//         // Element 4 is not in the subset (binary representation 0111), so it should return true.
//         assertTrue(Solver.notIn(elem, subset), "Element 4 should not be in the subset");

//         // Case 7: Checking the same element multiple times
//         elem = 2;
//         subset = 0b0100; // Binary: subset contains element 2
//         // Element 2 is in the subset, so it should return false.
//         assertFalse(Solver.notIn(elem, subset), "Element 2 should be in the subset");

//         elem = 1;
//         subset = 0b1000; // Binary: subset contains element 3, not element 1
//         // Element 1 is not in the subset, so it should return true.
//         assertTrue(Solver.notIn(elem, subset), "Element 1 should not be in the subset");

//         // Case 8: Random non-trivial case
//         elem = 5;
//         subset = 0b111111; // Binary: subset contains elements 0, 1, 2, 3, 4, and 5
//         // Element 5 is in the subset (binary representation 111111), so it should return false.
//         assertFalse(Solver.notIn(elem, subset), "Element 5 should be in the subset");
//     }

//     @Test
//     void testSolve_withValidInputs() {
//         Solver solver = new Solver();
//         double[][] distances = {
//             {0, 10, 15, 20},
//             {10, 0, 35, 25},
//             {15, 35, 0, 30},
//             {20, 25, 30, 0}
//         };
//         int startCity = 0;
//         List<Integer> selected = Arrays.asList(0, 1, 2, 3);

//         List<Integer> result = solver.solve(distances, startCity, selected);

//         // Assert the result is not null and contains all selected cities
//         assertNotNull(result, "Result should not be null");
//         assertEquals(selected.size(), result.size(), "Result should contain the same number of cities as selected");
//         assertTrue(result.containsAll(selected), "Result should contain all selected cities in the tour");
//     }

//     @Test
//     void testSolve_withInvalidDistances() {
//         Solver solver = new Solver();
//         double[][] distances = {
//             {0, 10, 15}, // Invalid: Missing values to make it square
//             {10, 0, 35},
//             {15, 35, 0}
//         };
//         int startCity = 0;
//         List<Integer> selected = Arrays.asList(0, 1, 2);

//         IllegalArgumentException exception = assertThrows(
//             IllegalArgumentException.class,
//             () -> solver.solve(distances, startCity, selected),
//             "Expected solve to throw IllegalArgumentException for invalid distances"
//         );

//         assertTrue(exception.getMessage().contains("distances"), "Exception message should mention distances");
//     }

//     @Test
//     void testSolve_withInvalidStartCity() {
//         Solver solver = new Solver();
//         double[][] distances = {
//             {0, 10, 15, 20},
//             {10, 0, 35, 25},
//             {15, 35, 0, 30},
//             {20, 25, 30, 0}
//         };
//         int startCity = 5; // Invalid start city
//         List<Integer> selected = Arrays.asList(0, 1, 2, 3);

//         IllegalArgumentException exception = assertThrows(
//             IllegalArgumentException.class,
//             () -> solver.solve(distances, startCity, selected),
//             "Expected solve to throw IllegalArgumentException for invalid start city"
//         );

//         assertTrue(exception.getMessage().contains("startCity"), "Exception message should mention startCity");
//     }

//     @Test
//     void testSolve_withEmptySelectedList() {
//         Solver solver = new Solver();
//         double[][] distances = {
//             {0, 10, 15, 20},
//             {10, 0, 35, 25},
//             {15, 35, 0, 30},
//             {20, 25, 30, 0}
//         };
//         int startCity = 0;
//         List<Integer> selected = Arrays.asList(); // Empty list of cities

//         IllegalArgumentException exception = assertThrows(
//             IllegalArgumentException.class,
//             () -> solver.solve(distances, startCity, selected),
//             "Expected solve to throw IllegalArgumentException for empty selected list"
//         );

//         assertTrue(exception.getMessage().contains("selected"), "Exception message should mention selected");
//     }

//     @Test
//     void testTotalDist_NormalCase() {
//         // Normal case with 4 cities
//         double[][] dist = {
//             {0, 1, 2, 3}, // destination from the city 0
//             {1, 0, 4, 5}, // destination from the city 1
//             {2, 4, 0, 6}, // destination from the city 2
//             {3, 5, 6, 0}  // destination from the city 3
//         };
        
//         List<Integer> best = Arrays.asList(0, 1, 2, 3);
        
//         // The expected result is the total distance (0 -> 1 -> 2 -> 3)
//         // sum = dist[0][1] + dist[1][2] + dist[2][3] = 1 + 4 + 6 = 11
//         double result = solver.totalDist(best, dist);
        
//         assertEquals(11.0, result, "The total distance should be 11.0");
//     }

//     @Test
//     void testTotalDist_SingleCity() {
//         // Case with just one city 
//         double[][] dist = {
//             {0, 0}, // distance from the city 0 (there is no other city selected)
//             {0, 0}  // distance from the city 1
//         };
        
//         List<Integer> best = Arrays.asList(0);
        
//         // There is no route so the total distance must be 0
//         double result = solver.totalDist(best, dist);
        
//         assertEquals(0.0, result, "The total distance should be 0.0 when there is only one city.");
//     }

//     @Test
//     void testTotalDist_EmptyList() {
//         // Case when there are no cities selected
//         double[][] dist = {
//             {0, 1, 2, 3}, // Distance from the city 0
//             {1, 0, 4, 5}, // Distance from the city 1
//             {2, 4, 0, 6}, // Distance from the city 2
//             {3, 5, 6, 0}  // Distance from the city 3
//         };
        
//         List<Integer> best = Arrays.asList(); // Empty list
        
//         // The list is empty so the total distance must be 0
//         double result = solver.totalDist(best, dist);
        
//         assertEquals(0.0, result, "The total distance should be 0.0 when the list is empty.");
//     }

//     @Test
//     void testTotalDist_OneWayTrip() {
//         // Case when the route is from the city 0 to the city 3
//         double[][] dist = {
//             {0, 1, 2, 3}, // Distance from the city 0
//             {1, 0, 4, 5}, // Distance from the city 1
//             {2, 4, 0, 6}, // Distance from the city 2
//             {3, 5, 6, 0}  // Distance from the city 3
//         };
        
//         List<Integer> best = Arrays.asList(0, 3);
        
//         // The total distance must be the distance between the cities 0 and 3
//         // sum = dist[0][3] = 3
//         double result = solver.totalDist(best, dist);
        
//         assertEquals(3.0, result, "The total distance should be 3.0 for a one-way trip.");
//     }

//     @Test
//     void testTotalDist_WithLargerList() {
//         // Case with more selected cities and more complicated route
//         double[][] dist = {
//             {0, 10, 20, 30}, // distance from the city 0
//             {10, 0, 15, 25}, // distance from the city 1
//             {20, 15, 0, 35}, // distance from the city 2
//             {30, 25, 35, 0}  // distance from the city 3
//         };
        
//         List<Integer> best = Arrays.asList(0, 1, 2, 3);
        
//         // Expected total distance : dist[0][1] + dist[1][2] + dist[2][3] = 10 + 15 + 35 = 60
//         double result = solver.totalDist(best, dist);
        
//         assertEquals(60.0, result, "The total distance should be 60.0 for the given route.");
//     }

//     @Test
//     void testBestRoute_FirstAlgorithmBetter() {
//         // Case when sum1 (first algorithm) is better than sum2 (second algorithm)
        
//         double sum1 = 10.0; // total distance of the first algorithm
//         double sum2 = 15.0; // total distance of the second algorithm
//         List<Integer> selected = Arrays.asList(1, 2, 3);
//         double[][] distances = {
//             {0, 10, 20, 30}, // distance from the city 0
//             {10, 0, 15, 25}, // distance from the city 1
//             {20, 15, 0, 35}, // distance from the city 2
//             {30, 25, 35, 0}  // distance from the city 3
//         };
//         int startCity = 0;

//         List<Integer> bestRoute = solver.bestRoute(sum1, sum2, selected, distances, startCity);
        
//         // the first algorithm must be selected
//         // We assume that the first algorithm returns a valid route
//         assertNotNull(bestRoute, "Best route should not be null.");
//         assertTrue(bestRoute.contains(startCity), "Best route should contain the start city.");
//     }

//     @Test
//     void testBestRoute_SecondAlgorithmBetter() {
//         // Case when sum2 is better than sum1
        
//         double sum1 = 20.0; // first's algorithm total distance
//         double sum2 = 15.0; // second's algorithm total distance
//         List<Integer> selected = Arrays.asList(1, 2, 3);
//         double[][] distances = {
//             {0, 10, 20, 30}, // distance from the city 0
//             {10, 0, 15, 25}, // distance from the city 1
//             {20, 15, 0, 35}, // distance from the city 2
//             {30, 25, 35, 0}  // distance from the city 3
//         };
//         int startCity = 0;

//         List<Integer> bestRoute = solver.bestRoute(sum1, sum2, selected, distances, startCity);
        
//         // The second algorithm must be selected
//         // We assume that the second algorithm returns a valid route
//         assertNotNull(bestRoute, "Best route should not be null.");
//         assertTrue(bestRoute.contains(startCity), "Best route should contain the start city.");
//     }

//     @Test
//     void testBestRoute_EqualDistances() {
//         // Case when sum1=sum2
        
//         double sum1 = 15.0;  
//         double sum2 = 15.0;
//         List<Integer> selected = Arrays.asList(1, 2, 3);
//         double[][] distances = {
//             {0, 10, 20, 30}, // distance from the city 0
//             {10, 0, 15, 25}, // distance from the city 1
//             {20, 15, 0, 35}, // distance from the city 2
//             {30, 25, 35, 0}  // distance from the city 3
//         };
//         int startCity = 0;

//         List<Integer> bestRoute = solver.bestRoute(sum1, sum2, selected, distances, startCity);
        
//         // It doesn't matter which algorithm will be chosen however they must return a valid route
//         assertNotNull(bestRoute, "Best route should not be null.");
//         assertTrue(bestRoute.contains(startCity), "Best route should contain the start city.");
//     }

//     @Test
//     void testBestRoute_EmptyList() {
//         // Case when the list is empty
        
//         double sum1 = 10.0;
//         double sum2 = 15.0;
//         List<Integer> selected = Arrays.asList();
//         double[][] distances = {
//             {0, 10, 20, 30}, // distance from the city 0
//             {10, 0, 15, 25}, // distance from the city 1
//             {20, 15, 0, 35}, // distance from the city 2
//             {30, 25, 35, 0}  // distance from the city 3
//         };
//         int startCity = 0;

//         List<Integer> bestRoute = solver.bestRoute(sum1, sum2, selected, distances, startCity);
        
//         // The best route must be the start city
//         assertNotNull(bestRoute, "Best route should not be null.");
//         assertEquals(1, bestRoute.size(), "Best route should only contain the start city.");
//         assertTrue(bestRoute.contains(startCity), "Best route should contain the start city.");
//     }

//     @Test
//     void testInitializeCityMappings() {
//         // insert data
//         List<Integer> selected = Arrays.asList(3, 7, 1, 5); // cities' list
//         Map<Integer, Integer> cityToIndex = new HashMap<>();
//         Map<Integer, Integer> indexToCity = new HashMap<>();

//         // method's call
//         solver.initializeCityMappings(selected, cityToIndex, indexToCity);

//         // check for the results
//         // verify cityToIndex
//         assertEquals(4, cityToIndex.size(), "cityToIndex should contain 4 mappings");
//         assertEquals(0, cityToIndex.get(3), "City 3 should map to index 0");
//         assertEquals(1, cityToIndex.get(7), "City 7 should map to index 1");
//         assertEquals(2, cityToIndex.get(1), "City 1 should map to index 2");
//         assertEquals(3, cityToIndex.get(5), "City 5 should map to index 3");

//         // verify indexToCity
//         assertEquals(4, indexToCity.size(), "indexToCity should contain 4 mappings");
//         assertEquals(3, indexToCity.get(0), "Index 0 should map to city 3");
//         assertEquals(7, indexToCity.get(1), "Index 1 should map to city 7");
//         assertEquals(1, indexToCity.get(2), "Index 2 should map to city 1");
//         assertEquals(5, indexToCity.get(3), "Index 3 should map to city 5");
//     }

//     @Test
//     void testInitializeCityMappings_withEmptyList() {
//         // input data
//         List<Integer> selected = new ArrayList<>(); // empty list
//         Map<Integer, Integer> cityToIndex = new HashMap<>();
//         Map<Integer, Integer> indexToCity = new HashMap<>();

//         // method's call
//         solver.initializeCityMappings(selected, cityToIndex, indexToCity);

//         // The maps should stay empty
//         assertTrue(cityToIndex.isEmpty(), "cityToIndex should be empty for an empty list");
//         assertTrue(indexToCity.isEmpty(), "indexToCity should be empty for an empty list");
//     }

//     @Test
//     void testInitializeCityMappings_withNullSelected() {
//         // input data
//         List<Integer> selected = null; // null list
//         Map<Integer, Integer> cityToIndex = new HashMap<>();
//         Map<Integer, Integer> indexToCity = new HashMap<>();

//         Solver solver = new Solver();

//         // Check for the exception's existence
//         assertThrows(NullPointerException.class, 
//             () -> solver.initializeCityMappings(selected, cityToIndex, indexToCity),
//             "Expected initializeCityMappings to throw NullPointerException for null list"
//         );
//     }

//     @Test
//     void testInitializeCityMappings_withDuplicateElements() {
//         // input data
//         List<Integer> selected = Arrays.asList(3, 7, 3, 5); // A list with a set of the same elements
//         Map<Integer, Integer> cityToIndex = new HashMap<>();
//         Map<Integer, Integer> indexToCity = new HashMap<>();

//         Solver solver = new Solver();

//         // method's call
//         solver.initializeCityMappings(selected, cityToIndex, indexToCity);

//         // Checks
//         assertEquals(4, cityToIndex.size(), "cityToIndex should map all cities, including duplicates");
//         assertEquals(2, cityToIndex.get(3), "City 3 should map to the last index where it appears");
//         assertEquals(1, cityToIndex.get(7), "City 7 should map to index 1");
//         assertEquals(3, cityToIndex.get(5), "City 5 should map to index 3");

//         assertEquals(4, indexToCity.size(), "indexToCity should map all indices");
//         assertEquals(3, indexToCity.get(0), "Index 0 should map to city 3");
//         assertEquals(7, indexToCity.get(1), "Index 1 should map to city 7");
//         assertEquals(3, indexToCity.get(2), "Index 2 should map to city 3");
//         assertEquals(5, indexToCity.get(3), "Index 3 should map to city 5");
//     }

//     @Test
//     void testInitializeMemo_normalCase() {
//         // Input data
//         double[][] distances = {
//             {0, 1, 2},
//             {1, 0, 3},
//             {2, 3, 0}
//         }; // Distance matrix for 3 cities
//         Double[][] memo = new Double[3][8]; // Memo matrix for N = 3, 2^3 subsets
//         int startIndex = 0; // Starting city
//         int N = 3; // Number of cities

//         Solver solver = new Solver();
//         solver.initializeMemo(distances, memo, startIndex, N);

//         // Assertions to verify initialization
//         assertEquals(1.0, memo[1][3], "Distance from start city 0 to city 1 should be set in memo");
//         assertEquals(2.0, memo[2][5], "Distance from start city 0 to city 2 should be set in memo");
//         assertNull(memo[0][3], "Memo should not have entries for the start city itself");
//     }

//     @Test
//     void testInitializeMemo_withEmptyDistances() {
//         // Input data
//         double[][] distances = new double[0][0]; // Empty distance matrix
//         Double[][] memo = new Double[0][1]; // Empty memo
//         int startIndex = 0; // Starting city
//         int N = 0; // No cities
//         solver.initializeMemo(distances, memo, startIndex, N);
//         // Assertions to verify no changes were made
//         assertEquals(0, memo.length, "Memo should remain empty when there are no cities");
//     }

//     @Test
//     void testInitializeMemo_withInvalidDistances() {
//         // Input data
//         double[][] distances = {
//             {0, 1, Double.POSITIVE_INFINITY}, // Invalid distance
//             {1, 0, 3},
//             {Double.POSITIVE_INFINITY, 3, 0}
//         };
//         Double[][] memo = new Double[3][8];
//         int startIndex = 0; // Starting city
//         int N = 3; // Number of cities
//         solver.initializeMemo(distances, memo, startIndex, N);
//         // Assertions to handle invalid distances
//         assertEquals(1.0, memo[1][3], "Valid distance from city 0 to city 1 should be set in memo");
//         assertEquals(Double.POSITIVE_INFINITY, memo[2][5], 
//             "Invalid distance from city 0 to city 2 should be propagated to memo");
//     }

//     @Test
//     void testInitializeMemo_withStartIndexOutOfRange() {
//         // Input data
//         double[][] distances = {
//             {0, 1, 2},
//             {1, 0, 3},
//             {2, 3, 0}
//         };
//         Double[][] memo = new Double[3][8];
//         int startIndex = 3; // Invalid start index
//         int N = 3; // Number of cities
//         // Ensure an exception is thrown for invalid start index
//         assertThrows(ArrayIndexOutOfBoundsException.class, 
//             () -> solver.initializeMemo(distances, memo, startIndex, N),
//             "An out-of-range startIndex should throw an exception");
//     }

//     @Test
//     void testInitializeMemo_withSingleCity() {
//         // Input data
//         double[][] distances = { {0} }; // Distance matrix with one city
//         Double[][] memo = new Double[1][2]; // Memo for N = 1, 2^1 subsets
//         int startIndex = 0; // Starting city
//         int N = 1; // Only one city
//         solver.initializeMemo(distances, memo, startIndex, N);
//         // Assertions to verify no unnecessary changes
//         assertNull(memo[0][1], "Memo should not be set for single city because there are no valid end cities");
//     }

//     @Test
//     void testCalculateSubsets_normalCase() {
//         // Input data
//         double[][] distances = {
//             {0, 10, 15, 20},
//             {10, 0, 35, 25},
//             {15, 35, 0, 30},
//             {20, 25, 30, 0}
//         };
//         Double[][] memo = new Double[4][16]; // Memo for 4 cities, 2^4 subsets
//         int startIndex = 0;
//         int N = 4;
//         // Pre-initialize memo for subsets with just start city and one other city
//         solver.initializeMemo(distances, memo, startIndex, N);
//         // Calculate subsets
//         solver.calculateSubsets(distances, memo, startIndex, N);
//         // Assertions to verify calculations for some key subsets
//         assertEquals(45.0, memo[3][15], 
//             "Subset {0, 1, 2, 3}: Distance should reflect optimal path ending at city 3");
//         assertEquals(55.0, memo[2][15], 
//             "Subset {0, 1, 2, 3}: Distance should reflect optimal path ending at city 2");
//     }

//     @Test
//     void testCalculateSubsets_withSingleCity() {
//         // Input data
//         double[][] distances = {{0}};
//         Double[][] memo = new Double[1][2]; // Memo for 1 city, 2^1 subsets
//         int startIndex = 0;
//         int N = 1;
//         // Pre-initialize memo
//         solver.initializeMemo(distances, memo, startIndex, N);
//         // Calculate subsets
//         solver.calculateSubsets(distances, memo, startIndex, N);
//         // Assertions: Memo should remain unchanged as there are no valid subsets
//         assertNull(memo[0][1], "Single city should result in no subsets being calculated");
//     }

//     @Test
//     void testCalculateSubsets_withEmptyDistances() {
//         // Input data
//         double[][] distances = new double[0][0]; // Empty distances
//         Double[][] memo = new Double[0][1]; // Empty memo
//         int startIndex = 0;
//         int N = 0;
//         // Calculate subsets
//         solver.calculateSubsets(distances, memo, startIndex, N);
//         // Assertions: Memo should remain empty
//         assertEquals(0, memo.length, "Memo should remain empty for no cities");
//     }

//     @Test
//     void testCalculateSubsets_withInvalidDistances() {
//         // Input data
//         double[][] distances = {
//             {0, 10, Double.POSITIVE_INFINITY},
//             {10, 0, 35},
//             {Double.POSITIVE_INFINITY, 35, 0}
//         };
//         Double[][] memo = new Double[3][8]; // Memo for 3 cities, 2^3 subsets
//         int startIndex = 0;
//         int N = 3;
//         // Pre-initialize memo
//         solver.initializeMemo(distances, memo, startIndex, N);
//         // Calculate subsets
//         solver.calculateSubsets(distances, memo, startIndex, N);
//         // Assertions
//         assertEquals(45.0, memo[2][7], 
//             "Subset {0, 1, 2}: Distance should reflect path considering invalid distances");
//         assertEquals(Double.POSITIVE_INFINITY, memo[1][7], 
//             "Subset {0, 1, 2}: Distance should propagate invalid paths correctly");
//     }

//     @Test
//     void testCalculateSubsets_withStartIndexOutOfRange() {
//         // Input data
//         double[][] distances = {
//             {0, 10, 15},
//             {10, 0, 35},
//             {15, 35, 0}
//         };
//         Double[][] memo = new Double[3][8];
//         int startIndex = 3; // Invalid start index
//         int N = 3;
//         // Ensure exception is thrown for invalid start index
//         assertThrows(ArrayIndexOutOfBoundsException.class, 
//             () -> solver.calculateSubsets(distances, memo, startIndex, N),
//             "An out-of-range startIndex should throw an exception");
//     }

//     @Test
//     void testBuildTour_normalCase() {
//         // Input data
//         double[][] distances = {
//             {0, 10, 15, 20},
//             {10, 0, 35, 25},
//             {15, 35, 0, 30},
//             {20, 25, 30, 0}
//         };
//         Double[][] memo = new Double[4][16];
//         int startCity = 0; // Start from city 0
//         Map<Integer, Integer> cityToIndex = Map.of(0, 0, 1, 1, 2, 2, 3, 3);
//         Map<Integer, Integer> indexToCity = Map.of(0, 0, 1, 1, 2, 2, 3, 3);
//         int END_STATE = 15; // All cities visited (binary: 1111)
//         int startIndex = 0;

//         // Pre-initialize memo for the tour
//         solver.initializeMemo(distances, memo, startIndex, 4);
//         solver.calculateSubsets(distances, memo, startIndex, 4);

//         // Build the tour
//         List<Integer> tour = solver.buildTour(distances, memo, startCity, cityToIndex, indexToCity, END_STATE, startIndex);

//         // Assertions: Verify the tour and properties
//         assertEquals(Arrays.asList(0, 1, 3, 2, 0), tour, "The tour should visit all cities optimally");
//         assertEquals(0, tour.get(0), "The tour should start at the starting city");
//         assertEquals(0, tour.get(tour.size() - 1), "The tour should end at the starting city");
//     }

//     @Test
//     void testBuildTour_withSingleCity() {
//         // Input data
//         double[][] distances = {{0}};
//         Double[][] memo = new Double[1][2]; // 2^1 subsets
//         int startCity = 0; // Start from city 0
//         Map<Integer, Integer> cityToIndex = Map.of(0, 0);
//         Map<Integer, Integer> indexToCity = Map.of(0, 0);
//         int END_STATE = 1; // Binary: 1
//         int startIndex = 0;

//         // Build the tour
//         List<Integer> tour = solver.buildTour(distances, memo, startCity, cityToIndex, indexToCity, END_STATE, startIndex);

//         // Assertions: Single city should result in a trivial tour
//         assertEquals(Arrays.asList(0, 0), tour, "The tour should start and end at the single city");
//     }

//     @Test
//     void testBuildTour_withDisconnectedGraph() {
//         // Input data
//         double[][] distances = {
//             {0, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
//             {Double.POSITIVE_INFINITY, 0, Double.POSITIVE_INFINITY},
//             {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0}
//         };
//         Double[][] memo = new Double[3][8]; // 2^3 subsets
//         int startCity = 0;
//         Map<Integer, Integer> cityToIndex = Map.of(0, 0, 1, 1, 2, 2);
//         Map<Integer, Integer> indexToCity = Map.of(0, 0, 1, 1, 2, 2);
//         int END_STATE = 7; // Binary: 111
//         int startIndex = 0;

//         // Ensure an exception is thrown for disconnected graph
//         Exception exception = assertThrows(IllegalStateException.class, 
//             () -> solver.buildTour(distances, memo, startCity, cityToIndex, indexToCity, END_STATE, startIndex),
//             "A disconnected graph should throw an exception due to no valid path");

//         assertTrue(exception.getMessage().contains("The tour does not start and end at the starting city"),
//             "The exception should indicate an invalid tour");
//     }

//     @Test
//     void testBuildTour_withIncorrectMemo() {
//         // Input data
//         double[][] distances = {
//             {0, 10, 15},
//             {10, 0, 35},
//             {15, 35, 0}
//         };
//         Double[][] memo = new Double[3][8]; // Incorrectly uninitialized memo
//         int startCity = 0;
//         Map<Integer, Integer> cityToIndex = Map.of(0, 0, 1, 1, 2, 2);
//         Map<Integer, Integer> indexToCity = Map.of(0, 0, 1, 1, 2, 2);
//         int END_STATE = 7; // Binary: 111
//         int startIndex = 0;

//         // Ensure an exception is thrown for incorrect memo initialization
//         Exception exception = assertThrows(IllegalStateException.class, 
//             () -> solver.buildTour(distances, memo, startCity, cityToIndex, indexToCity, END_STATE, startIndex),
//             "An uninitialized memo should result in an exception");

//         assertTrue(exception.getMessage().contains("The tour does not start and end at the starting city"),
//             "The exception should indicate an invalid tour");
//     }

//     @Test
//     void testBuildTour_withEmptyInput() {
//         // Input data
//         double[][] distances = new double[0][0];
//         Double[][] memo = new Double[0][1]; // Empty memo
//         int startCity = 0;
//         Map<Integer, Integer> cityToIndex = Collections.emptyMap();
//         Map<Integer, Integer> indexToCity = Collections.emptyMap();
//         int END_STATE = 0;
//         int startIndex = 0;

//         // Ensure an exception is thrown for empty input
//         Exception exception = assertThrows(IllegalStateException.class, 
//             () -> solver.buildTour(distances, memo, startCity, cityToIndex, indexToCity, END_STATE, startIndex),
//             "Empty input should result in an exception");

//         assertTrue(exception.getMessage().contains("The tour does not start and end at the starting city"),
//             "The exception should indicate an invalid tour");
//     }
// }
