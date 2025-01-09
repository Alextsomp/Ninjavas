package repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

// import repository.DB;

public class Menu {
    Scanner sc = new Scanner(System.in);
    String ANSI_YELLOW = "\u001B[33m";
    String ANSI_RESET = "\u001B[0m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_GREEN = "\u001B[32m";
    String ANSI_CYAN = "\u001B[36m";

    private DB db;
    private CityDistanceManager cityDistanceManager;

    public Menu(String databasePath) throws SQLException {
        // Δημιουργούμε σύνδεση με τη βάση δεδομένων και το CityDistanceManager
        db = new DB(databasePath);
        cityDistanceManager = new CityDistanceManager(db);

    }

    // The first messages of the application
    public void PrintMenu() {
        System.out.println("\nHello there fellow" + ANSI_CYAN + " RoadTripper" + ANSI_RESET + "! Welcome to the"
                + ANSI_CYAN + " RouteGenie " + ANSI_RESET + "application! Let's begin with your new trip!\n");
        System.out.println(
                "\nNow we will provide the available cities, from which you will choose the cities you want to visit");
    }

    // This method is used to add only the available cities you want to visit in a
    // list
    public ArrayList<Integer> ChooseCities(int firstCityIndex, int citiesIndex) throws SQLException {

        ArrayList<Integer> citiesChosen = new ArrayList<>();
        String[] cityList = cityDistanceManager.getAllCities();

        for (int i = 0; i < cityList.length; i++) {
            System.out.println(ANSI_YELLOW + i + ") " + ANSI_RESET + cityList[i]);
        }
        do {
            System.out.println("Please choose your" + ANSI_RED + " starting " + ANSI_RESET + "and" + ANSI_RED
                    + " finishing " + ANSI_RESET + "city by inserting the number which the city corresponds to:");
            firstCityIndex = sc.nextInt();
            if (firstCityIndex >= 0 && firstCityIndex < cityList.length) {
                citiesChosen.add(firstCityIndex);
            } else {
                System.out.println("\nPlease enter a valid city index.");
            }
        } while (firstCityIndex < 0 || firstCityIndex >= cityList.length);

        System.out.println(
                "\nChoose the cities you want to visit by inserting the number which the city corresponds to:");
        do {
            System.out.println("Available cities:");
            // Εκτυπώνουμε τις πόλεις που δεν έχουν επιλεγεί ήδη
            for (int i = 0; i < cityList.length; i++) {
                if (!citiesChosen.contains(i)) {
                    System.out.println(ANSI_YELLOW + i + ") " + ANSI_RESET + cityList[i]);
                }
            }

            citiesIndex = sc.nextInt();
            if (citiesIndex == -1) {
                break; // Τερματισμός όταν ο χρήστης εισάγει -1
            }

            // Ελέγχουμε αν η πόλη υπάρχει και αν δεν έχει ήδη επιλεγεί
            if (citiesIndex >= 0 && citiesIndex < cityList.length) {
                if (!citiesChosen.contains(citiesIndex)) {
                    citiesChosen.add(citiesIndex);
                } else {
                    System.out.println("\nThis city has already been added. Please select a" + ANSI_RED + " different "
                            + ANSI_RESET + "city that is still available.\n");
                }
            } else {
                System.out.println("\nPlease enter a valid number corresponding to an available city.");
            }

            // Ερώτηση για αν θέλει να συνεχίσει ή να τελειώσει
            System.out.println("\nChoose another city from the list or enter" + ANSI_RED + " -1 " + ANSI_RESET
                    + "if you have added all the cities you desire already.");

        } while (citiesIndex != -1);

        return citiesChosen;
    }
}
