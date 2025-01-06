package repository;

import java.util.ArrayList;

import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    String ANSI_YELLOW = "\u001B[33m";
    String ANSI_RESET = "\u001B[0m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_GREEN = "\u001B[32m";
    String ANSI_CYAN = "\u001B[36m";

    // The first messages of the app
    public void PrintMenu() {
        System.out.println("\nHello there fellow" + ANSI_CYAN + " RoadTripper" + ANSI_RESET + "! Welcome to the"
                + ANSI_CYAN + " RouteGenie " + ANSI_RESET + "application! Let's begin with your new trip!\n");
        System.out.println(
                "\nNow we will provide the available cities ,from which you will choose the cities you want to visit");
    }

    // This method is used to add only the available cities you want to visit in a
    // list
    public ArrayList<Integer> ChooseCities(int firstCityIndex, String[] cityNames, int citiesIndex) {
        CitiesAndDistances cad2 = new CitiesAndDistances();
        ArrayList<Integer> citiesChosen = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            System.out.println(ANSI_YELLOW + i + ") " + ANSI_RESET + cityNames[i]);
        }
        do {
            System.out.println(
                    "Please choose your" + ANSI_RED + " starting " + ANSI_RESET + "and" + ANSI_RED + " finishing "
                            + ANSI_RESET + "city by inserting the number which the city corresponds to from 0 to 14:");
            firstCityIndex = sc.nextInt();
            if (cad2.checkExistance(cityNames, firstCityIndex)) {
                citiesChosen.add(firstCityIndex);
            } else {
                System.out.println("\nPlease enter a number that is between" + ANSI_RED + " 0 " + ANSI_RESET + "and"
                        + ANSI_RED + " 14" + ANSI_RESET + ".");
            }
        } while (firstCityIndex > 14 || firstCityIndex < 0);
        System.out.println(
                "\nChoose the cities you want to visit by inserting the number which the city corresponds to from 0 to 14:");
        do {
            System.out.println("Available cities:");
            // Print available cities that have not been chosen
            for (int i = 0; i < 15; i++) {
                if (!citiesChosen.contains(i)) {
                    // Checks if you have already choosen the city you enter
                    System.out.println(ANSI_YELLOW + i + ") " + ANSI_RESET + cityNames[i]);
                }
            }
            citiesIndex = sc.nextInt();
            if (citiesIndex == -1) {
                break; // End input when user enters -1
            }
            if (CitiesAndDistances.checkExistance(cityNames, citiesIndex)) {
                if (!citiesChosen.contains(citiesIndex)) {
                    citiesChosen.add(citiesIndex);
                } else {
                    // If you have entered the city prior to now, it throws this message and makes
                    // you choose another
                    System.out.println("\nThis city has already been added. Please select a" + ANSI_RED + " different "
                            + ANSI_RESET + "city that is still available.\n");
                }
            } else {
                System.out.println("\n\nPlease enter a number that is between" + ANSI_RED + " 0 " + ANSI_RESET + "and"
                        + ANSI_RED + " 14" + ANSI_RESET + ".");
            }

            // Ask if the user wants to continue or finish
            System.out.println("\nChoose another city from the list or enter" + ANSI_RED + " -1 " + ANSI_RESET
                    + "if you have added all the cities you desire already.");

            // Check if the user entered -1 to stop the loop
        } while (citiesIndex != -1);
        return citiesChosen;
    }

}
