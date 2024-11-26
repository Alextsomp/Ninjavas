import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CitiesandDistancesTest {
    
    @BeforeEach
    public void setUp() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    }
    
    @Test
    public void PrintCitiesTest() {
        String[] cities = {"Athens", "Thessaloniki", "sofia", "Tirana"}; // Παράδειγμα πόλεων
        CitiesAndDistances.cities = cities; // Ανάθεση του πίνακα πόλεων στην κλάση CitiesAndDistances

        ArrayList<Integer> citiesChosen = new ArrayList<>();
        citiesChosen.add(0); // Athens
        citiesChosen.add(2); // Sofia
        System.setOut(new PrintStream(outContent)); 

        // Εκτέλεση της μεθόδου
        CitiesAndDistances.printCities(citiesChosen);
        System.setOut(System.out);

        // Έλεγχος αν η έξοδος περιέχει τα σωστά δεδομένα
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
}