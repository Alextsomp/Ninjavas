import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import gr.aueb.dmst.NinJavas.Data.DB;
import gr.aueb.dmst.NinJavas.Algorithms.DynamicProgramming;
import gr.aueb.dmst.NinJavas.Algorithms.NearestNeighbour;
import gr.aueb.dmst.NinJavas.Algorithms.Solver;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;

public class SolverTest {
    
        @Test
        public void testTotalDistance() {
            Solver solver = new Solver();
            
            double[][] distances = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
            };
            
            List<Integer> route = Arrays.asList(0, 1, 2, 3);
            
            // Calculate expected total distance: 0 -> 1 -> 2 -> 3
            // 0 -> 1 = 10, 1 -> 2 = 35, 2 -> 3 = 30
            double expectedTotalDistance = 10 + 35 + 30;
            
            double actualTotalDistance = solver.totalDistance(route, distances);
            
            assertEquals(expectedTotalDistance, actualTotalDistance, "The total distance calculation is incorrect.");
        }
    
        @Test
        public void testTotalDistanceWithReturn() {
            Solver solver = new Solver();
    
            double[][] distances = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
            };
    
            List<Integer> route = Arrays.asList(0, 1, 2, 3, 0);
    
            // Calculate expected total distance: 0 -> 1 -> 2 -> 3 -> 0
            // 0 -> 1 = 10, 1 -> 2 = 35, 2 -> 3 = 30, 3 -> 0 = 20
            double expectedTotalDistance = 10 + 35 + 30 + 20;
    
            double actualTotalDistance = solver.totalDistance(route, distances);
    
            assertEquals(expectedTotalDistance, actualTotalDistance, "The total distance with return calculation is incorrect.");
        }
    
        @Test
        public void testTotalDistanceSingleCity() {
            Solver solver = new Solver();
    
            double[][] distances = {
                {0}
            };
    
            List<Integer> route = Arrays.asList(0, 0);
    
            // Calculate expected total distance: 0 -> 0
            double expectedTotalDistance = 0;
    
            double actualTotalDistance = solver.totalDistance(route, distances);
    
            assertEquals(expectedTotalDistance, actualTotalDistance, "The total distance for a single city should be zero.");
        }

        @Test
        public void testTotalDistanceForCitiesChosen() {
            Solver solver = new Solver();
            List<Integer> route = new ArrayList<>(Arrays.asList(0, 1, 2));
            double[][] distances = {
                {0, 10, 20},
                {10, 0, 30},
                {20, 30, 0}
            };
    
            // The expected total distance is: 0 -> 1 (10) + 1 -> 2 (30) + 2 -> 0 (20) = 60
            double expectedDistance = 60.0;
            double calculatedDistance = solver.totalDistanceForCitiesChosen(route, distances);
    
            assertEquals(expectedDistance, calculatedDistance, 0.001);
        }

        @Test
        public void testTotalDistanceForCitiesChosen_NoDistances() {
            Solver solver = new Solver();
            List<Integer> route = new ArrayList<>(Arrays.asList(0, 1, 2));
            double[][] distances = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
            };
            double expectedDistance = 0; // All distances are zero
            double calculatedDistance = solver.totalDistanceForCitiesChosen(route, distances);
            assertEquals(expectedDistance, calculatedDistance, 0.001);
        }
        
        @Test
    public void testTotalDistanceForCitiesChosen_NonSquareMatrix() {
        Solver solver = new Solver();
        List<Integer> route = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        double[][] distances = {
            {0, 10, 20, 30},
            {10, 0, 15, 25},
            {20, 15, 0, 35},
            {30, 25, 35, 0}
        };
        double expectedDistance = 10 + 15 + 35 + 30; // 10 + 15 + 35 + 30 (round trip)
        double calculatedDistance = solver.totalDistanceForCitiesChosen(route, distances);
        assertEquals(expectedDistance, calculatedDistance, 0.001);
    }

        @Test
    public void testBestRoute() throws SQLException {
        DB mockDb = mock(DB.class);
        DynamicProgramming mockDpSolver = mock(DynamicProgramming.class);
        NearestNeighbour mockNnSolver = mock(NearestNeighbour.class);

        Solver solver = new Solver();
        solver.dpSolver = mockDpSolver;
        solver.nnSolver = mockNnSolver;

        List<Integer> selectedCities = Arrays.asList(1, 2, 3);
        double[][] distances = {
            {0, 10, 20, 30},
            {10, 0, 25, 35},
            {20, 25, 0, 15},
            {30, 35, 15, 0}
        };
        int startCity = 0;

        when(mockDpSolver.dp(distances, mockDb, selectedCities))
            .thenReturn(Arrays.asList(0, 1, 2, 3));
        when(mockNnSolver.nearestNeighbour(distances, selectedCities))
            .thenReturn(Arrays.asList(0, 3, 2, 1));

        List<Integer> bestRoute = solver.bestRoute(60.0, 70.0, selectedCities, distances, startCity);

        assertEquals(Arrays.asList(0, 1, 2, 3), bestRoute);
    }
    
    }