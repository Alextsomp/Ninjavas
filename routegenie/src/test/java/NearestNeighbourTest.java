import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;
import gr.aueb.dmst.NinJavas.Algorithms.NearestNeighbour;

public class NearestNeighbourTest {

    @BeforeEach


    @Test
    public void testSimpleRoute() {
        NearestNeighbour nn = new NearestNeighbour();
        double[][] distances = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };
        List<Integer> selectedCities = Arrays.asList(0, 1, 2, 3);

        List<Integer> expectedRoute = Arrays.asList(0, 1, 3, 2, 0);
        List<Integer> actualRoute = nn.nearestNeighbour(distances, selectedCities);

        assertEquals(expectedRoute, actualRoute);
    }

    @Test
    public void testSingleCity() {
        NearestNeighbour nn = new NearestNeighbour();
        double[][] distances = {
            {0}
        };
        List<Integer> selectedCities = Arrays.asList(0);

        List<Integer> expectedRoute = Arrays.asList(0, 0);
        List<Integer> actualRoute = nn.nearestNeighbour(distances, selectedCities);

        assertEquals(expectedRoute, actualRoute);
    }

    @Test
    public void testTwoCities() {
        NearestNeighbour nn = new NearestNeighbour();
        double[][] distances = {
            {0, 10},
            {10, 0}
        };
        List<Integer> selectedCities = Arrays.asList(0, 1);

        List<Integer> expectedRoute = Arrays.asList(0, 1, 0);
        List<Integer> actualRoute = nn.nearestNeighbour(distances, selectedCities);

        assertEquals(expectedRoute, actualRoute);
    }

    @Test
    public void testThreeCities() {
        NearestNeighbour nn = new NearestNeighbour();
        double[][] distances = {
            {0, 10, 15},
            {10, 0, 35},
            {15, 35, 0}
        };
        List<Integer> selectedCities = Arrays.asList(0, 1, 2);

        List<Integer> expectedRoute = Arrays.asList(0, 1, 2, 0);
        List<Integer> actualRoute = nn.nearestNeighbour(distances, selectedCities);

        assertEquals(expectedRoute, actualRoute);
    }

    @Test
    public void testUnreachableCity() {
        NearestNeighbour nn = new NearestNeighbour();
        double[][] distances = {
            {0, 10, Double.MAX_VALUE},
            {10, 0, 20},
            {Double.MAX_VALUE, 20, 0}
        };
        List<Integer> selectedCities = Arrays.asList(0, 1, 2);

        List<Integer> expectedRoute = Arrays.asList(0, 1, 2, 0); // Despite unreachable, it tries to include all
        List<Integer> actualRoute = nn.nearestNeighbour(distances, selectedCities);

        assertEquals(expectedRoute, actualRoute);
    }
}
