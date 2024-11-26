import org.junit.jupiter.Test;
import static org.junit.jupiter.Assert;
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
    void nearestNeighbourTest () {
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
    void combinationsTest() {
        // παραδειγμα για ν=4 στοιχεια και ρ=2 μεγεθος συνδυασμων
        int n = 4;
        int r = 2;
        List<Integer> result = Solver.combinations(r, n);
        List<Integer> expected = List.of(3, 5, 6, 9, 10, 12);//αναμενομενοι συνδυασμοι
        assertEquals(expected.size(), result.size(), "ο αριθμος των συνδυασμων δεν ειναι σωστος");
        assertTrue(result.containsAll(expected) && expected.containsAll(result), "οι συνδυασμοι δεν ειναι σωστοι");
    }
    @Test
    void CombinationsEdgeCaseTest() {
        // Έλεγχος για περίπτωση r = 0 (πρέπει να υπάρχει μόνο το κενό σύνολο)
        int n = 5;
        int r = 0;

        List<Integer> result = Solver.combinations(r, n);

        // Αναμενόμενο αποτέλεσμα: μόνο το κενό σύνολο
        List<Integer> expected = List.of(0);
        assertEquals(expected, result, "Η μέθοδος δεν επιστρέφει σωστά το κενό σύνολο όταν r = 0.");
    }
    @Test
    void CombinationsFullSetTest() {
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
    void CombinationsInvalidCaseTest() {
        // Έλεγχος για περίπτωση r > n (πρέπει να επιστρέφει άδειο σύνολο)
        int n = 3;
        int r = 4;

        List<Integer> result = Solver.combinations(r, n);

        // Αναμενόμενο αποτέλεσμα: κανένας συνδυασμός
        List<Integer> expected = List.of();
        assertEquals(expected, result, "Η μέθοδος δεν επιστρέφει άδειο σύνολο όταν r > n.");
    }
}