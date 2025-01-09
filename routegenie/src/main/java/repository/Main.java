package repository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.repository.CityDistanceManager;
import main.java.repository.Comparison;
import main.java.repository.Menu;

// import main.java.repository.CityDistanceManager;
// import main.java.repository.Comparison;
// import main.java.repository.Menu;

// import main.java.repository.Comparison;
// import main.java.repository.Comparsion;
// import main.java.repository.Menu;
public class Main {
    public static void main(String[] args) throws SQLException {
       
        DB dbManager = new DB("ninjavas.db"); 
        CityDistanceManager cityDistanceManager = new CityDistanceManager(dbManager);

        //Initiallizing all the necessery objects from the other classes.
        NearestNeighbour nn = new NearestNeighbour();
        Solver svr = new Solver();
        DynamicProgramming dynamicProg = new DynamicProgramming();
        Comparison comp = new Comparison();
        Menu mn = new Menu("ninjavas.db");
        try {
            // Δημιουργία σύνδεσης με την βάση
            DB db = new DB("ninjavas.db");
            
            Scanner sc = new Scanner(System.in);
            int city1 = sc.nextInt();
            int city2 = sc.nextInt();            
            // Βρίσκουμε και εκτυπώνουμε τις αποστάσεις για όλα τα ζευγάρια πόλεων στη λίστα
            db.getDistances(city1,city2);

            // Κλείσιμο σύνδεσης
            db.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*String[] cityNames = cityDistanceManager.getAllCities();
        double[][] distances;
        
        // for(int i = 0 ; i < 14 ; i++) {
        //     for (int j = 0 ; j< 14 ; j++){
        //         if(i!=j){
        //             distances[i] = cityDistanceManager.getDistance(cityNames[i],cityNames[j]);
        //         }
        //     }
        // }
        int citiesIndex = 0;
        int firstCityIndex = 0;


        mn.PrintMenu();
        ArrayList<Integer> citiesChosen = mn.ChooseCities(firstCityIndex, citiesIndex);
        distances = dynamicProg.fetchDistancesFromDB(dbManager, citiesIndex);

        List<Integer> bestRouteSolver = dynamicProg.dp(dbManager, firstCityIndex, citiesChosen);
        List<Integer> bestRouteNN = nn.nearestNeighbour(firstCityIndex, distances, bestRouteSolver);
        double nnTotalDistance = svr.totalDist(bestRouteNN, distances);
        double SolverTotalDistance = svr.totalDist(bestRouteSolver, distances);
         //svr.bestRoute(nnTotalDistance, SolverTotalDistance, bestRouteNN, distances, firstCityIndex);
    
        comp.compareAlgorithms(bestRouteSolver, bestRouteNN, SolverTotalDistance, nnTotalDistance, firstCityIndex, cityNames);
        
                
                
    }*/
    }
} 