import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import repository.Solver;
import org.junit.jupiter.api.BeforeEach;

public class SolverTest {
    
    private Solver solver;
    
    @BeforeEach
    public void setup(){
    solver = new Solver();
   }
   
   @Test
    public void testSolverInitialization() {
        assertNotNull(solver);
    }

   @Test
    public void nearestNeighbourTest () {
        double[][] distances = {
            {0, 494, 678, 798},
            {494, 0, 397, 292},
            {678, 397, 0, 473},
            {798, 292, 473, 0}
        };
        List<Integer> selected = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> expectedTour = Arrays.asList(0, 1, 3, 2);
        List<Integer> result = solver.nearestNeighbour(0, distances, selected);
        assertEquals(expectedTour, result, "nearestNeighbour method does not return the right tour");
    }
    @Test
    public void combinationsTest() {
        //example of 4 elements and 2 combinations
        int n = 4;
        int r = 2;
        List<Integer> result = Solver.combinations(r, n);
        List<Integer> expected = List.of(3, 5, 6, 9, 10, 12);//expected combinations
        assertEquals(expected.size(), result.size(), "the number of combinations is not right");
        assertTrue(result.containsAll(expected) && expected.containsAll(result), "combinations are not right");
    }
    @Test
    public void CombinationsEdgeCaseTest() {
        //case when r=0
        int n = 5;
        int r = 0;

        List<Integer> result = Solver.combinations(r, n);
        //zero space expected
        List<Integer> expected = List.of(0);
        assertEquals(expected, result, "The method does not return the right result when r=0, it has nothing to return.");
    }
    @Test
    public void CombinationsFullSetTest() {
        //Case when r=n
        //(it has to return all sets of combinations)
        int n = 3;
        int r = 3;
        List<Integer> result = Solver.combinations(r, n);

        //Expected all sets
        //111 (binary), which means 7
        List<Integer> expected = List.of(7);
        assertEquals(expected, result, "The method does not return the right result when r=0, all sets of combinations must be included");
    }

    @Test
    public void CombinationsInvalidCaseTest() {
        //case when r>n
        //(null space expected)
        int n = 3;
        int r = 4;

        List<Integer> result = Solver.combinations(r, n);

        //expected result: no combinations.
        List<Integer> expected = List.of();
        assertEquals(expected, result, "The method does not return the null space when r>n.");
    }

    @Test
    public void testCombinations() {
        List<Integer> subsets = new ArrayList<>();
        int r = 2; //number of combinations
        int n = 3; //number of elements
        Solver.combinations(0, 0, r, n, subsets);
        //expected combinations
        List<Integer> expected = List.of(
            0b011,
            0b101,
            0b110
        );
        assertEquals(expected, subsets);
    }
    
    @Test
    public void NotInTest() {
        // Bitmask subset: 0b101 (it contains the elements 2 and 0, not the element 1)
        int subset = 0b101;
        // Check for an element that is not included
        assertTrue(Solver.notIn(1, subset), "Element 1 must not be included in the subset");
        // Δοκιμή για στοιχεία που περιλαμβάνονται
        assertFalse(Solver.notIn(0, subset), "Element 0 must be included in the subset");
        assertFalse(Solver.notIn(2, subset), "Element 2 must be included in the subset");
    }

    @Test
    void testSolve_withValidInputs() {
        Solver solver = new Solver();
        double[][] distances = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };
        int startCity = 0;
        List<Integer> selected = Arrays.asList(0, 1, 2, 3);

        List<Integer> result = solver.solve(distances, startCity, selected);

        // Assert the result is not null and contains all selected cities
        assertNotNull(result, "Result should not be null");
        assertEquals(selected.size(), result.size(), "Result should contain the same number of cities as selected");
        assertTrue(result.containsAll(selected), "Result should contain all selected cities in the tour");
    }

    @Test
    void testSolve_withInvalidDistances() {
        Solver solver = new Solver();
        double[][] distances = {
            {0, 10, 15}, // Invalid: Missing values to make it square
            {10, 0, 35},
            {15, 35, 0}
        };
        int startCity = 0;
        List<Integer> selected = Arrays.asList(0, 1, 2);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> solver.solve(distances, startCity, selected),
            "Expected solve to throw IllegalArgumentException for invalid distances"
        );

        assertTrue(exception.getMessage().contains("distances"), "Exception message should mention distances");
    }

    @Test
    void testSolve_withInvalidStartCity() {
        Solver solver = new Solver();
        double[][] distances = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };
        int startCity = 5; // Invalid start city
        List<Integer> selected = Arrays.asList(0, 1, 2, 3);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> solver.solve(distances, startCity, selected),
            "Expected solve to throw IllegalArgumentException for invalid start city"
        );

        assertTrue(exception.getMessage().contains("startCity"), "Exception message should mention startCity");
    }

    @Test
    void testSolve_withEmptySelectedList() {
        Solver solver = new Solver();
        double[][] distances = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };
        int startCity = 0;
        List<Integer> selected = Arrays.asList(); // Empty list of cities

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> solver.solve(distances, startCity, selected),
            "Expected solve to throw IllegalArgumentException for empty selected list"
        );

        assertTrue(exception.getMessage().contains("selected"), "Exception message should mention selected");
    }

    @Test 
    public void totalDistTest() {
        double[][] distances = {
            {0, 494, 678, 798},
            {494, 0, 397, 292},
            {678, 397, 0, 473},
            {798, 292, 473, 0}
        };
        List<Integer> best = Arrays.asList(0, 1, 3, 2);
        double expectedTotalDistance = 494 + 292 + 473;
        double actualTotalDistance = solver.totalDist(best, distances);
        assertEquals(expectedTotalDistance, actualTotalDistance, "totalDist method did not calculate correctly the sum of the distances");
    }

    @Test
    public void bestRouteTest() {
        double[][] distances = {
            {0, 494, 678, 798},
            {494, 0, 397, 292},
            {678, 397, 0, 473},
            {798, 292, 473, 0}
        };
        List<Integer> selected = Arrays.asList(1, 2, 3);
        int startCity = 0;
        double sum1 = 50;
        double sum2 = 100;
        List<Integer> expectedSolveRoute = Arrays.asList(0, 1, 2, 3);
        List<Integer> actualSolveRoute = solver.bestRoute(sum1, sum2, selected, distances, startCity);
        assertEquals(expectedSolveRoute, actualSolveRoute,"solve method must have been called");
        sum1 = 120;
        sum2 = 100;
        List<Integer> expectedNearestRoute = Arrays.asList(0, 3, 2, 1);
        List<Integer> actualNearestRoute = solver.bestRoute(sum1, sum2, selected, distances, startCity);
        assertEquals(expectedNearestRoute, actualNearestRoute, "nearestNeighbour method must have been called");
    }
}
