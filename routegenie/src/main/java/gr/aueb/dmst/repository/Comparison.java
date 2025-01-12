package gr.aueb.dmst.repository;

import java.util.List;

public class Comparison {
    String ANSI_YELLOW = "\u001B[33m";
    String ANSI_RESET = "\u001B[0m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_GREEN = "\u001B[32m";
    String ANSI_CYAN = "\u001B[36m";

    Solver solver = new Solver();
    
    // Method that takes the results of the algorithms and compares them to give you the shortest route
    public void compareAlgorithms(List<Integer> bestRoutesSolver,
                                  List<Integer> bestRoutesNN, 
                                  double totalDistanceSolver,
                                  double totalDistanceNN,
                                  int firstCityIndex,
                                  int[] cityIds,
                                  List<String> cityNames,
                                  double citiesChosenTotalDistance) {
        if (bestRoutesNN.size() < 4) {
            System.out.println(
                ANSI_YELLOW +"\n\nUnfortunately" + ANSI_RESET + 
                ", our application needs at least " + ANSI_RED +
                "3" + ANSI_RESET +  " cities to be practical." +
                "\nPlease try using" + ANSI_CYAN + " RouteGenie" + ANSI_RESET + 
                " again, choosing more than 2 cities.\n\n");      
        }
       else if (totalDistanceNN > totalDistanceSolver) {
            System.out.println("\n\nThe best algorithm for your trip is" + ANSI_GREEN + " Solver" + ANSI_RESET + ".");
            System.out.println("According to" + ANSI_GREEN + " Solver" + ANSI_RESET + ", the best route for your trip is: ");
            for (int i = 0; i < bestRoutesSolver.size()-1; i++) { // -1 because it would print the starting city again 
                System.out.printf(ANSI_CYAN + "%d)" + ANSI_RESET + " %s\n", i+1 , cityNames.get(bestRoutesSolver.get(i)));
            }
            System.out.printf("And then back to %s!\n", cityNames.get(firstCityIndex));
            System.out.println("\nIn this order, the distance covered is" + ANSI_CYAN + " only " + ANSI_RESET + 
                               "going to be " + ANSI_GREEN + totalDistanceSolver + ANSI_RESET + 
                               " kilometers,\nwhen if you had not used " + ANSI_CYAN + "RouteGenie, " + ANSI_RESET + 
                               "it would have been " + ANSI_RED + citiesChosenTotalDistance + ANSI_RESET + "!\n");

            System.out.println("Thank you for using " + ANSI_CYAN + 
                               "RouteGenie" + ANSI_RESET + ", the roadtrip optimisation app!");
            System.out.println("See you again on your next " + ANSI_CYAN + 
                               "RoadTrip" + ANSI_RESET + "!\n\n");                      
        
        } else if (totalDistanceSolver > totalDistanceNN) {
            System.out.println("\n\nThe best algorithm for this case is" + ANSI_GREEN + " Nearest Neighbour" + ANSI_RESET + ".");
            System.out.println("According to" + ANSI_GREEN + " Nearest Neighbour" + ANSI_RESET + ", the best route for your trip is: ");
            for (int i = 0; i < bestRoutesNN.size()-1; i++) { // -1 because it would print the starting city again 
                System.out.printf(ANSI_CYAN + "%d)" + ANSI_RESET + " %s\n", i+1 , cityNames.get(bestRoutesNN.get(i)));
            }
            System.out.printf("And then back to %s!\n", cityNames.get(firstCityIndex));
            System.out.println("\nIn this order, the distance covered is" + ANSI_CYAN + " only " + ANSI_RESET + 
                               "going to be " + ANSI_GREEN + totalDistanceNN + ANSI_RESET +
                               " kilometers,\nwhen if you had not used " + ANSI_CYAN + "RouteGenie, " + ANSI_RESET + 
                               "it would have been " + ANSI_RED + citiesChosenTotalDistance + ANSI_RESET + "!\n");

            System.out.println("Thank you for using " + ANSI_CYAN + 
                               "RouteGenie" + ANSI_RESET + ", the roadtrip optimisation app!");
            System.out.println("See you again on your next " + ANSI_CYAN + 
                               "RoadTrip" + ANSI_RESET + "!\n\n"); 

        } else {
            System.out.println(ANSI_GREEN + "\n\nBoth algorithms agree!" + ANSI_RESET);
            System.out.println("The best route for your trip is: ");
            for (int i = 0; i < bestRoutesNN.size()-1; i++) { // -1 because it would print the starting city again 
                System.out.printf(ANSI_CYAN + "%d)" + ANSI_RESET + " %s\n", i+1 , cityNames.get(bestRoutesSolver.get(i)));
            }
            System.out.printf("And then back to %s!\n", cityNames.get(firstCityIndex));
            System.out.println("\nIn this order, the distance covered is" + ANSI_CYAN + " only " + ANSI_RESET + 
                               "going to be " + ANSI_GREEN + totalDistanceNN + ANSI_RESET +
                               " kilometers,\nwhen if you had not used " + ANSI_CYAN + "RouteGenie," + ANSI_RESET + 
                               " it would have been " + ANSI_RED + citiesChosenTotalDistance + ANSI_RESET + "!\n");
            
            System.out.println("Thank you for using " + ANSI_CYAN + 
                               "RouteGenie" + ANSI_RESET + ", the roadtrip optimisation app!");
            System.out.println("See you again on your next " + ANSI_CYAN + 
                               "RoadTrip" + ANSI_RESET + "!\n\n");                   
        }
    }
}
