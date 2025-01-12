package gr.aueb.dmst.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

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
        // Connection with Database and  CityDistanceManager
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

    // This method is used to add only the available cities you want to visit in a list
    public ArrayList<Integer> chooseCities() throws SQLException {

        ArrayList<Integer> citiesChosen = new ArrayList<>();
        List<String> citiesList = cityDistanceManager.getCityNames();

        String[] cityList = citiesList.toArray(new String[0]);

        for (int i = 0; i < cityList.length; i++) {
            System.out.println(ANSI_YELLOW + i + ") " + ANSI_RESET + cityList[i]);
        }
        int firstCityIndex;
        int citiesIndex;
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
            // Print the cities that have not been chosen yet
            for (int i = 0; i < cityList.length; i++) {
                if (!citiesChosen.contains(i)) {
                    System.out.println(ANSI_YELLOW + i + ") " + ANSI_RESET + cityList[i]);
                }
            }

            citiesIndex = sc.nextInt();
            if (citiesIndex == -1) {
                break; // exit when user types -1
            }

            // Check if city exists and if it has already been chosen
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

            // ask if he wants to continue
            System.out.println("\nChoose another city from the list or enter" + ANSI_RED + " -1 " + ANSI_RESET
                    + "if you have added all the cities you desire already.");

        } while (citiesIndex != -1);

        return citiesChosen;
    }
}
