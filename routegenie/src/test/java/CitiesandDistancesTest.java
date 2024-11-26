import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CitiesandDistancesTest {
    @Test
    void PrintCitiesTest() {
        String[] cities = {"Athens", "Thessaloniki", "sofia", "Tirana"}; // Παράδειγμα πόλεων
        CitiesAndDistances.cities = cities; // Ανάθεση του πίνακα πόλεων στην κλάση CitiesAndDistances

        ArrayList<Integer> citiesChosen = new ArrayList<>();
        citiesChosen.add(0); // Athens
        citiesChosen.add(2); // Sofia
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
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
}