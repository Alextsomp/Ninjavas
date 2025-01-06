package repository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.repository.CityDistanceManager;
import main.java.repository.Comparison;
import main.java.repository.Menu;

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
        Menu mn = new Menu();

        String[] cityNames = cityDistanceManager.getAllCities();
        double[][] distances = cityDistanceManager.getDistance();
        int citiesIndex = 0;
        int firstCityIndex = 0;


        mn.PrintMenu();
        ArrayList<Integer> citiesChosen = mn.ChooseCities(firstCityIndex, citiesIndex);
         // distances = dynamicProg.fetchDistancesFromDB(dbManager, citiesIndex);

         //List<Integer> bestRouteSolver = dynamicProg.dp(dbManager, firstCityIndex, citiesChosen);
        List<Integer> bestRouteNN = nn.nearestNeighbour(firstCityIndex, distances, bestRouteSolver);
        double nnTotalDistance = svr.totalDist(bestRouteNN, distances);
        double SolverTotalDistance = svr.totalDist(bestRouteSolver, distances);
         //svr.bestRoute(nnTotalDistance, SolverTotalDistance, bestRouteNN, distances, firstCityIndex);
    
        comp.compareAlgorithms(bestRouteSolver, bestRouteNN, SolverTotalDistance, nnTotalDistance, firstCityIndex, cityNames);
        
                
                
    }
} 