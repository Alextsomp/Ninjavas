import java.util.ArrayList;
import java.util.Scanner;
public class testm1 {
    public static void main(String[] args) {
                // Ensure cities are initialized
                CitiesAndDistances.Cities();
                Scanner sc = new Scanner(System.in);
                ArrayList<String> citieschoosen = new ArrayList<>();
                String c;
                String[] ct = CitiesAndDistances.getCities();
                System.out.println("Choose the cities you want to visit:");
                do {
                    // Display available cities that have not been chosen
                    for (int i = 0; i < 15; i++) {
                        if (!citieschoosen.contains(ct[i])) {
                            System.out.println(ct[i]);
                        }
                    }
                    c = sc.nextLine();
                    while (!CitiesAndDistances.checkExistance(ct, c) && !c.equals("End")) {
                        System.out.println("The city you entered is not in the list. Please enter a valid city:");
                        c = sc.nextLine();
                    }
                    
                    // Take input for the city
                    citieschoosen.add(c);  // Add the city to the list of chosen cities
                
                    // Ask if the user wants to continue or finish
                    System.out.println("Choose another city from the list or enter 'End' if you have added all the cities you desire already.");
                    
                    // Check if the user entered "End" to stop the loop
                } while (!c.equals("End"));
                  
    }

}
