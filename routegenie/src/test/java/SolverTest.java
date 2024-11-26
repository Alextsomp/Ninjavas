import org.junit.jupiter.Test;
import static org.junit.jupiter.Assert;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class SolverTest {
    @Test
    void nearestNeighbourTest () {
        Solver solver = new Solver();
        double[][] distances = {
            {0, 494, 678, 798},
            {494, 0, 397, 292},
            (678, 397, 0, 473),
            {798, 292, 473, 0}
        };
        int Startcity = 0;
        List<Integer> selected = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<integer> expectedTour = Arrays.asList(0, 1, 3, 2);
        List<Integer> result = Solver.nearestNeighbour(0, distances, selected);
        assertEquals(expectedTour, result, "the method nearestNeighbour does not return the right tour");
    }
}