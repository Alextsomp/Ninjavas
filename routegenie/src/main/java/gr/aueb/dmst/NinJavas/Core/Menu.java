package gr.aueb.dmst.NinJavas.Core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import gr.aueb.dmst.NinJavas.Controller.CityDistanceManager;
import gr.aueb.dmst.NinJavas.Data.DB;

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
        System.out.println(
            "\n\nHello there fellow " + ANSI_CYAN + "RoadTripper" + ANSI_RESET +
            "! Welcome to the " + ANSI_CYAN + "RouteGenie " + ANSI_RESET +
            "application!\nLet's begin with your new trip!");
        System.out.println(
            "\nNow, we will provide the available cities" + 
            " from which you can choose the ones that you want to visit:");
    }

    public ArrayList<Integer> chooseCities() throws SQLException {
        ArrayList<Integer> citiesChosen = new ArrayList<>();
        List<String> citiesList = cityDistanceManager.getCityNames();
        String[] cityList = citiesList.toArray(new String[0]);
        
        // Display the city list
        for (int i = 0; i < cityList.length; i++) {
            System.out.println(ANSI_YELLOW + (i + 1) + ") " + ANSI_RESET + cityList[i]);
        }
    
        int firstCityIndex;
        int citiesIndex;
    
        try {
            // Prompt for the starting/ending city
            while (true) {
                System.out.println(
                    "Please select the city that will serve as both the " + ANSI_RED +
                    "StartingPoint " + ANSI_RESET + "and the " + ANSI_RED + "EndPoint" + ANSI_RESET +
                    " of your trip by inserting the number corresponding to the city:");
    
                if (sc.hasNextInt()) {
                    firstCityIndex = sc.nextInt() - 1;
    
                    if (firstCityIndex >= 0 && firstCityIndex < cityList.length) {
                        citiesChosen.add(firstCityIndex);
                        break; // Valid input, exit the loop
                    } else {
                        System.out.println("\nPlease enter a " + ANSI_RED + "valid " + ANSI_RESET + "city index from the list.");
                    }
                } else {
                    System.out.println("\nPlease enter" + ANSI_RED +  " numbers " + ANSI_RESET + "only.");
                    sc.next(); // Clear invalid input
                }
            }
    
            // Prompt for additional cities
            while (true) {
                // System.out.println("\nChoose another city that you want to visit by" +
                //                    " inserting the number corresponding to the city:");
    
                System.out.println("\nAvailable cities:");
                for (int i = 0; i < cityList.length; i++) {
                    if (!citiesChosen.contains(i)) {
                        System.out.println(ANSI_YELLOW + (i + 1) + ") " + ANSI_RESET + cityList[i]);
                    }
                }
    
                System.out.println(
                    "Choose another city from the list or enter" + ANSI_RED +
                    " -1 " + ANSI_RESET + "if you have added all the cities you desire already.");
    
                if (sc.hasNextInt()) {
                    citiesIndex = sc.nextInt() - 1;
    
                    if (citiesIndex == -2) {
                        break; // Exit when user types -1
                    }
    
                    if (citiesIndex >= 0 && citiesIndex < cityList.length) {
                        if (!citiesChosen.contains(citiesIndex)) {
                            citiesChosen.add(citiesIndex);
                        } else {
                            System.out.println("\n\nThis city has already been added. Please select a" + ANSI_RED +
                                               " different " + ANSI_RESET + "city.");
                        }
                    } else {
                        System.out.println("\n\nPlease enter a" + ANSI_RED + " valid " + ANSI_RESET + 
                                           "number corresponding to an" + ANSI_GREEN +
                                           " available " + ANSI_RESET +  "city.");
                    }
                } else {
                    System.out.println("\n\nPlease enter" + ANSI_RED +  " numbers " + ANSI_RESET + "only.");
                    sc.next(); // Clear invalid input
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter" + ANSI_RED +  " numbers " + ANSI_RESET + "only.");
            sc.next(); // Clear invalid input
        }
    
        return citiesChosen;
    }
    


    // This method is used to add only the available cities you want to visit in a list
//     public ArrayList<Integer> chooseCities() throws SQLException {

//         ArrayList<Integer> citiesChosen = new ArrayList<>();
//         List<String> citiesList = cityDistanceManager.getCityNames();

//         String[] cityList = citiesList.toArray(new String[0]);

//         for (int i = 0; i < cityList.length; i++) {
//             System.out.println(ANSI_YELLOW + (i+1) + ") " + ANSI_RESET + cityList[i]);
//         }
//         int firstCityIndex;
//         int citiesIndex;
//         try { 
//             do { 
//                 System.out.println(
//                     "Please select the city that will serve as both the " + ANSI_RED + 
//                     "StartingPoint " + ANSI_RESET + "and the " + ANSI_RED + "EndPoint" + ANSI_RESET + 
//                     " of your trip by inserting the number which the city corresponds to:");

//                 firstCityIndex = sc.nextInt()-1;
                
//                 if (firstCityIndex >= 0 && firstCityIndex < cityList.length) {
//                     citiesChosen.add(firstCityIndex);
//                 } else {
//                     System.out.println("\nPlease enter a " + ANSI_RED + "valid " + ANSI_RESET + "city index from the list.");
//                 }

//             } while (firstCityIndex < 0 || firstCityIndex >= cityList.length);
                
//             System.out.println(
//                 "\nChoose another city that you want to visit by" + 
//                 " inserting the number which the city corresponds to:");

//             do {
//                 System.out.println("Available cities:");
//                 // Print the cities that have not been chosen yet
//                 for (int i = 0; i < cityList.length; i++) {
//                     if (!citiesChosen.contains(i)) {
//                         System.out.println(ANSI_YELLOW + (i+1) + ") " + ANSI_RESET + cityList[i]);
//                     }
//                 }

//                 citiesIndex = sc.nextInt()-1;
//                 if (citiesIndex == -2) {
//                     break; // exit when user types -1
//                 }

//                 // Check if city exists and if it has already been chosen
//                 if (citiesIndex >= 0 && citiesIndex < cityList.length) {
//                     if (!citiesChosen.contains(citiesIndex)) {
//                         citiesChosen.add(citiesIndex);
//                     } else {
//                         System.out.println(
//                             "\n\nThis city has already been added. Please select a" + ANSI_RED + 
//                             " different " + ANSI_RESET + "city that is still available.");
//                     }
//                 } else {
//                     System.out.println(
//                         "\nPlease enter a" + ANSI_RED + " valid " + ANSI_RESET + 
//                         "number from the list, corresponding to an available city.");
//                 }

//                 // Ask if he wants to continue
//                 System.out.println(
//                     "\nChoose another city from the list or enter" + ANSI_RED + 
//                     " -1 " + ANSI_RESET + "if you have added all the cities you desire already.");

//             } while (citiesIndex != -2);
//         } catch (InputMismatchException e) {
//           System.out.println("Please enter numbers only");
//         }   
//         return citiesChosen;
//     }
}
