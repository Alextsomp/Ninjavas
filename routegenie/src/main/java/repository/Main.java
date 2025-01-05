package repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.repository.Comparison;
import main.java.repository.Comparsion;
import main.java.repository.Menu;
public class Main {
    public static void main(String[] args) {
                
                 /*String ANSI_YELLOW = "\u001B[33m";
                 String ANSI_RESET = "\u001B[0m";
                 String ANSI_RED = "\u001B[31m";
                 String ANSI_GREEN = "\u001B[32m";
                 String ANSI_CYAN = "\u001B[36m";
                */
                // Ensure cities are initialized
                
                Solver solver = new Solver();
                CitiesAndDistances cad1 = new CitiesAndDistances();
                double[][] distances = cad1.distances;
                int citiesIndex;
                int firstCityIndex = -1;
                String[] cityNames = CitiesAndDistances.getCities();
                /* 
                System.out.println("\nHello there fellow" + ANSI_CYAN + " RoadTripper" + ANSI_RESET + "! Welcome to the" + ANSI_CYAN + " RouteGenie " + ANSI_RESET + "application! Let's begin with your new trip!\n");
                System.out.println("Please choose your" + ANSI_RED + " starting " + ANSI_RESET + "and" + ANSI_RED + " finishing " + ANSI_RESET +  "city by inserting the number which the city corresponds to from 0 to 14:");
                */
                Menu mn = new Menu();
                mn.PrintMenu();
                ArrayList<Integer> citiesChosen = mn.ChooseCities(firstCityIndex, cityNames, citiesIndex);

                /*for (int i = 0; i < 15; i++) {
                    System.out.println(ANSI_YELLOW + i + ") " + ANSI_RESET + cityNames[i]);
                }
                do {
                    firstCityIndex = sc.nextInt(); 
                    if (CitiesAndDistances.checkExistance(cityNames, firstCityIndex)) {
                        citiesChosen.add(firstCityIndex);
                    } else {
                        System.out.println("\nPlease enter a number that is between" + ANSI_RED + " 0 " + ANSI_RESET + "and" + ANSI_RED + " 14" + ANSI_RESET + ".");
                    }
                } while(firstCityIndex > 14 || firstCityIndex < 0 );
                
                System.out.println("\nChoose the cities you want to visit by inserting the number which the city corresponds to from 0 to 14:");
                do {
                    System.out.println("Available cities:");
                    // Print available cities that have not been chosen
                 for (int i = 0; i < 15; i++) {
                        if (!citiesChosen.contains(i)) {
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
                            System.out.println("\nThis city has already been added. Please select a" + ANSI_RED + " different " + ANSI_RESET + "city that is still available.\n");
                        }
                    } else {
                        System.out.println("\n\nPlease enter a number that is between" + ANSI_RED + " 0 " + ANSI_RESET + "and" + ANSI_RED + " 14" + ANSI_RESET + ".");
                    }
                
                    // Ask if the user wants to continue or finish
                    System.out.println("\nChoose another city from the list or enter" + ANSI_RED + " -1 " + ANSI_RESET + "if you have added all the cities you desire already.");
                    
                    // Check if the user entered -1 to stop the loop
                } while (citiesIndex != -1);
                // citiesChosen.add(firstCityIndex);
                // CitiesAndDistances.printCities(citiesChosen);
                */
                /*
                List<Integer> bestRoutesSolver = solver.solve(distances, firstCityIndex, citiesChosen);
                double totalDistanceSolver = solver.totalDist(bestRoutesSolver, distances);

                List<Integer> bestRoutesNN = solver.nearestNeighbour(firstCityIndex, distances, citiesChosen);
                double totalDistanceNN = solver.totalDist(bestRoutesNN, distances);

                // System.out.println("The best route is: ");
                // for (int i = 0; i < bestRoutesSolver.size(); i++) {
                // System.out.println(cityNames[bestRoutesSolver.get(i)]);
                // }
                */
                Comparison comp = new Comparison();
                
                /*
                if (totalDistanceNN > totalDistanceSolver) {
                    System.out.println("\nThe best algorithm for your trip is" + ANSI_GREEN + " Solver." + ANSI_RESET);
                    System.out.println("\nAccording to" + ANSI_GREEN + " Solver" + ANSI_RESET + ", the best route for your trip is: ");
                for (int i = 0; i < bestRoutesSolver.size()-1; i++) {
                    System.out.printf(ANSI_CYAN + "%d)" + ANSI_RESET + " %s\n", i+1 , cityNames[bestRoutesSolver.get(i)]);
                }
                    System.out.printf("And then back to %s!\n", cityNames[firstCityIndex]);
                    System.out.println("\nIn this order, the distance covered is" + ANSI_CYAN + " only " + ANSI_RESET + "going to be " + ANSI_GREEN + totalDistanceSolver + ANSI_RESET + " kilometers!\n");
                
                } else if (totalDistanceSolver > totalDistanceNN) {
                    System.out.println("\nThe best algorithm for this case is" + ANSI_GREEN + " NN." + ANSI_RESET);
                    System.out.println("\nAccording to" + ANSI_GREEN + " NN" + ANSI_RESET + ", the best route for your trip is: ");
                for (int i = 0; i < bestRoutesNN.size()-1; i++) {
                    System.out.printf(ANSI_CYAN + "%d)" + ANSI_RESET + " %s\n", i+1 , cityNames[bestRoutesNN.get(i)]);
                }
                    System.out.printf("And then back to %s!\n", cityNames[firstCityIndex]);
                    System.out.println("\nIn this order, the distance covered is" + ANSI_CYAN + " only " + ANSI_RESET + "going to be " + ANSI_GREEN + totalDistanceNN + ANSI_RESET + " kilometers!\n");
                
                } else {
                    System.out.println(ANSI_GREEN + "\nBoth algorithms agree!" + ANSI_RESET);
                    System.out.println("\nThe best route for your trip is: ");
                for (int i = 0; i < bestRoutesNN.size()-1; i++) {
                    System.out.printf(ANSI_CYAN + "%d)" + ANSI_RESET + " %s\n", i+1 , cityNames[bestRoutesNN.get(i)]);
                }
                    System.out.printf("And then back to %s!\n", cityNames[firstCityIndex]);
                    System.out.println("\nIn this order, the distance covered is" + ANSI_CYAN + " only " + ANSI_RESET + "going to be " + ANSI_GREEN + totalDistanceNN + ANSI_RESET + " kilometers!\n");
                }
                */   
    }
} 