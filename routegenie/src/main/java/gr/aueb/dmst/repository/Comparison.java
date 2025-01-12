package gr.aueb.dmst.repository;

import java.util.List;

public class Comparison {
    String ANSI_YELLOW = "\u001B[33m";
    String ANSI_RESET = "\u001B[0m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_GREEN = "\u001B[32m";
    String ANSI_CYAN = "\u001B[36m";

    Solver solver = new Solver();
    
    //Method that takes the results of the algorithms and compares them to give you the shortest route
    public void compareAlgorithms(
        List<Integer> bestRoutesSolver,
        List<Integer> bestRoutesNN, 
        double totalDistanceSolver,
        double totalDistanceNN,
        int firstCityIndex,
        int[] cityIds) {
        
        if (totalDistanceNN > totalDistanceSolver) {
            System.out.println("\nThe best algorithm for your trip is" + ANSI_GREEN + " Solver." + ANSI_RESET);
            System.out.println("\nAccording to" + ANSI_GREEN + " Solver" + ANSI_RESET + ", the best route for your trip is: ");
            for (int i = 0; i < bestRoutesSolver.size()-1; i++) {
                System.out.printf(ANSI_CYAN + "%d)" + ANSI_RESET + " %s\n", i+1 , cityIds[bestRoutesSolver.get(i)]);
            } 
            System.out.printf("And then back to %s!\n", cityIds[firstCityIndex]);
            System.out.println("\nIn this order, the distance covered is" + ANSI_CYAN + " only " + ANSI_RESET + "going to be " + ANSI_GREEN + totalDistanceSolver + ANSI_RESET + " kilometers!\n");
        
        } else if (totalDistanceSolver > totalDistanceNN) {
            System.out.println("\nThe best algorithm for this case is" + ANSI_GREEN + " NN." + ANSI_RESET);
            System.out.println("\nAccording to" + ANSI_GREEN + " NN" + ANSI_RESET + ", the best route for your trip is: ");
            for (int i = 0; i < bestRoutesNN.size()-1; i++) {
                System.out.printf(ANSI_CYAN + "%d)" + ANSI_RESET + " %s\n", i+1 , cityIds[bestRoutesNN.get(i)]);
            }
            System.out.printf("And then back to %s!\n", cityIds[firstCityIndex]);
            System.out.println("\nIn this order, the distance covered is" + ANSI_CYAN + " only " + ANSI_RESET + "going to be " + ANSI_GREEN + totalDistanceNN + ANSI_RESET + " kilometers!\n");
        
        } else {
            System.out.println(ANSI_GREEN + "\nBoth algorithms agree!" + ANSI_RESET);
            System.out.println("\nThe best route for your trip is: ");
            for (int i = 0; i < bestRoutesNN.size()-1; i++) {
                System.out.printf(ANSI_CYAN + "%d)" + ANSI_RESET + " %s\n", i+1 , cityIds[bestRoutesNN.get(i)]);
            }
            System.out.printf("And then back to %s!\n", cityIds[firstCityIndex]);
            System.out.println("\nIn this order, the distance covered is" + ANSI_CYAN + " only " + ANSI_RESET + "going to be " + ANSI_GREEN + totalDistanceNN + ANSI_RESET + " kilometers!\n");
        }
    }
}
