import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.beans.Transient;
import java.util.ArrayList;

public class SolverTest {
   
    @BeforeEach
   public void setup(){
    Solver solver = new Solver();
   }

   @Test
    public void nearestNeighbourTest () {
        double[][] distances = {
            {0, 494, 678, 798},
            {494, 0, 397, 292},
            {678, 397, 0, 473},
            {798, 292, 473, 0}
        };
        int Startcity = 0;
        List<Integer> selected = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<integer> expectedTour = Arrays.asList(0, 1, 3, 2);
        List<Integer> result = Solver.nearestNeighbour(0, distances, selected);
        assertEquals(expectedTour, result, "the method nearestNeighbour does not return the right tour");
    }
    @Test
    public void combinationsTest() {
        // παραδειγμα για ν=4 στοιχεια και ρ=2 μεγεθος συνδυασμων
        int n = 4;
        int r = 2;
        List<Integer> result = Solver.combinations(r, n);
        List<Integer> expected = List.of(3, 5, 6, 9, 10, 12);//αναμενομενοι συνδυασμοι
        assertEquals(expected.size(), result.size(), "ο αριθμος των συνδυασμων δεν ειναι σωστος");
        assertTrue(result.containsAll(expected) && expected.containsAll(result), "οι συνδυασμοι δεν ειναι σωστοι");
    }
    @Test
    public void CombinationsEdgeCaseTest() {
        // Έλεγχος για περίπτωση r = 0 (πρέπει να υπάρχει μόνο το κενό σύνολο)
        int n = 5;
        int r = 0;

        List<Integer> result = Solver.combinations(r, n);

        // Αναμενόμενο αποτέλεσμα: μόνο το κενό σύνολο
        List<Integer> expected = List.of(0);
        assertEquals(expected, result, "Η μέθοδος δεν επιστρέφει σωστά το κενό σύνολο όταν r = 0.");
    }
    @Test
    public void CombinationsFullSetTest() {
        // Έλεγχος για περίπτωση r = n (πρέπει να επιστρέφει το πλήρες σύνολο)
        int n = 3;
        int r = 3;
        List<Integer> result = Solver.combinations(r, n);

        // Αναμενόμενο αποτέλεσμα: μόνο το πλήρες σύνολο
        // 111 (δυαδικά), δηλαδή 7
        List<Integer> expected = List.of(7);
        assertEquals(expected, result, "Η μέθοδος δεν επιστρέφει σωστά το πλήρες σύνολο όταν r = n.");
    }
    @Test
    public void CombinationsInvalidCaseTest() {
        // Έλεγχος για περίπτωση r > n (πρέπει να επιστρέφει άδειο σύνολο)
        int n = 3;
        int r = 4;

        List<Integer> result = Solver.combinations(r, n);

        // Αναμενόμενο αποτέλεσμα: κανένας συνδυασμός
        List<Integer> expected = List.of();
        assertEquals(expected, result, "Η μέθοδος δεν επιστρέφει άδειο σύνολο όταν r > n.");
    }

    @Test
    public void testCombinations() {
        List<Integer> subsets = new ArrayList<>();
        int r = 2; // Μέγεθος συνδυασμών
        int n = 3; // Συνολικός αριθμός στοιχείων 
        combinations(0, 0, r, n, subsets);
        // Αναμενόμενοι συνδυασμοί
        List<Integer> expected = List.of(
            0b011,
            0b101,
            0b110  
        );
        assertEquals(expected, subsets);
    }
    @Test
    public void NotInTest() {
        // Bitmask subset: 0b101 (περιλαμβάνει στοιχεία 2 και 0, δεν περιλαμβάνει το 1)
        int subset = 0b101;
        // Δοκιμή για στοιχείο που δεν περιλαμβάνεται
        assertTrue(notIn(1, subset), "Το στοιχείο 1 ΔΕΝ πρέπει να υπάρχει στο subset");
        // Δοκιμή για στοιχεία που περιλαμβάνονται
        assertFalse(notIn(0, subset), "Το στοιχείο 0 πρέπει να υπάρχει στο subset");
        assertFalse(notIn(2, subset), "Το στοιχείο 2 πρέπει να υπάρχει στο subset");
    }
 @Test
    public void testSolve() {
        double[][] distances = {
            {0, 494, 678, 798},
            {494, 0, 397, 292},
            {678, 397, 0, 473},
            {798, 292, 473, 0}
        };
        List<Integer> selectedCities = Arrays.asList(0, 1, 2, 3);
        int startCity = 0;
        List<Integer> expectedTour = Arrays.asList(0, 1, 3, 2, 0);
        List<Integer> actualTour = solver.solve(distances, startCity, selectedCities);
        // Έλεγχος αν η διαδρομή είναι σωστή
        assertEquals(expectedTour, actualTour, "The computed tour does not match the expected tour.");
    }

    @Test 
    public void totalDistTest() {
        double[][] distances = {
            {0, 494, 678, 798},
            {494, 0, 397, 292},
            {678, 397, 0, 473},
            {798, 292, 473, 0}
        };
        List<Integer> best = Array.asList(0, 1, 3, 2);
        double expectedTotalDistance = 494 + 292 + 473;
        double actualTotalDistance = totalDist(best, distances);
        assertEquals(expectedTotalDistance, actualTotalDistance, "Η μέθοδος totalDist δεν υπολόγισε σωστά το άθροισμα των αποστάσεων!");
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
        List<Integer> actualSolveRoute = bestRoute(sum1, sum2, selected, distances, startCity);
        assertEquals(expectedSolveRoute, actualSolveRoute,"Έπρεπε να κληθεί η μέθοδος Solve!");
        sum1 = 120;
        sum2 = 100;
        List<Integer> expectedNearestRoute = Arrays.asList(0, 3, 2, 1);
        List<Integer> actualNearestRoute = bestRoute(sum1, sum2, selected, distances, startCity);
        assertEquals(expectedNearestRoute, actualNearestRoute, "Η μέθοδος nearestNeighbour δεν κλήθηκε σωστά όταν sum1 >= sum2!");
    }
}
