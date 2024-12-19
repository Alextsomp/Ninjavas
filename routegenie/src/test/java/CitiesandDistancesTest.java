import org.junit.jupiter.api.Test;
import repository.CitiesAndDistances;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class CitiesandDistancesTest {
    
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUp() {
        outContent = new ByteArrayOutputStream();
    }
    
    @Test
    public void PrintCitiesTest() {
        String[] cities = {"Athens", "Thessaloniki", "sofia", "Tirana"}; // cities' example
        CitiesAndDistances.cities = cities; // transfer this "cities" array to the classe's array

        ArrayList<Integer> citiesChosen = new ArrayList<>();
        citiesChosen.add(0); // Athens
        citiesChosen.add(2); // Sofia
        System.setOut(new PrintStream(outContent)); 

        //Excecution of the method
        CitiesAndDistances.printCities(citiesChosen);
        System.setOut(System.out);

        // Checking if the output includes the right data
        String expectedOutput = 
            "The cities that you have chosen are: \n" +
            "Athens\n" +
            "Sofia\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testPrintDistances() {
        double[][] distances = new double[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                distances[i][j] = i * j * 1.0;
            }
        }
        CitiesAndDistances.distances = distances;
        System.setOut(new PrintStream(outContent)); 
        CitiesAndDistances.printDistances();
        System.setOut(System.out);
        StringBuilder expectedOutput = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                expectedOutput.append(distances[i][j]).append("\n");
            }
        }
        assertEquals(expectedOutput.toString(), outContent.toString());
    }
    
        @Test
        public void testCheckExistance_ValidCityIndex() {
            String[] cities = {"Athens", "Sofia", "Paris"};
    
            // Check for a valid city index
            assertTrue(CitiesAndDistances.checkExistance(cities, 0), "City index 0 should exist.");
            assertTrue(CitiesAndDistances.checkExistance(cities, 1), "City index 1 should exist.");
            assertTrue(CitiesAndDistances.checkExistance(cities, 2), "City index 2 should exist.");
        }
    
        @Test
        public void testCheckExistance_InvalidCityIndex() {
            String[] cities = {"Athens", "Sofia", "Paris"};
    
            // Check for invalid indexes
            assertFalse(CitiesAndDistances.checkExistance(cities, -1), "Negative city index should not exist.");
            assertFalse(CitiesAndDistances.checkExistance(cities, 3), "City index equal to array length should not exist.");
            assertFalse(CitiesAndDistances.checkExistance(cities, 100), "City index larger than array length should not exist.");
        }
    
        @Test
        public void testCheckExistance_EmptyCityArray() {
            String[] cities = {};
    
            // Check for empty array
            assertFalse(CitiesAndDistances.checkExistance(cities, 0), "City index in an empty array should not exist.");
        }
    }
    