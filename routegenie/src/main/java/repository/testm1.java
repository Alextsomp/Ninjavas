package routegenie.src.main.java.repository;
import java.util.ArrayList;
import java.util.Scanner;
public class testm1 {
    public static void main(String[] args) {
                // Ensure cities are initialized
                CitiesAndDistances.Cities();
                Scanner sc = new Scanner(System.in);
                ArrayList<Integer> citiesChosen = new ArrayList<>();
                int c;
                int c1 = -1;
                String[] ct = CitiesAndDistances.getCities();
                System.out.println("Choose your starting and finishing city by inserting the number which the city corresponds to from 0-14:");
                for (int i = 0; i < 15; i++) {
                    System.out.println(i + ": " + ct[i]);
                }
                do {
                    c1 = sc.nextInt(); 
                    if (CitiesAndDistances.checkExistance(ct, c1)) {
                        citiesChosen.add(c1);
                    } else {
                        System.out.println("Please enter a number between 0-14.");
                    }
                } while(c1 > 14 || c1 < 0 );
                
                System.out.println("Choose the cities you want to visit by inserting the number which the city corresponds to from 0-14:");
                do {
                    System.out.println("Available cities:");
                    // Print available cities that have not been chosen
                 for (int i = 0; i < 15; i++) {
                        if (!citiesChosen.contains(i)) {
                            System.out.println(i + ": " + ct[i]);
                        }
                    }
                    c = sc.nextInt();
                    if (c == 15) {
                        break; // End input when user enters 15
                    }
                    if (CitiesAndDistances.checkExistance(ct, c)) {
                        if (!citiesChosen.contains(c)) {
                            citiesChosen.add(c);
                        } else {
                            System.out.println("This city has already been added. Please select a different city.");
                        }
                    } else {
                        System.out.println("Enter a number between 0-14.");
                    }
                
                    // Ask if the user wants to continue or finish
                    System.out.println("Choose another city from the list or enter 15 if you have added all the cities you desire already.");
                    
                    // Check if the user entered 15 to stop the loop
                } while (c != 15);
                citiesChosen.add(c1);
             
                System.out.println("Cities chosen: " + citiesChosen);

                CitiesAndDistances.printCities(citiesChosen);
                  
    }
} 