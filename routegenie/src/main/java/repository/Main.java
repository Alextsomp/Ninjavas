package repository;
import java.util.ArrayList;
import java.util.List;

// import main.java.repository.Comparison;
// import main.java.repository.Comparsion;
// import main.java.repository.Menu;
public class Main {
    public static void main(String[] args) {
        //Initiallizing all the necessery objects from the other classes.
        Solver solver = new Solver();
        NearestNeighbour nn = new NearestNeighbour();
        DynamicProgramming dp = new DynamicProgramming();
        CitiesAndDistances cad1 = new CitiesAndDistances();
        String[] cityNames = CitiesAndDistances.getCities();
        Menu mn = new Menu();
        Comparison comp = new Comparison();
        int citiesIndex = 0;
        int firstCityIndex = -1;
        double[][] distances = cad1.distances;
        
        mn.PrintMenu();
        ArrayList<Integer> citiesChosen = mn.ChooseCities(firstCityIndex, cityNames, citiesIndex);
        List<Integer> bestRouteNN = nn.nearestNeighbour(firstCityIndex, distances, citiesChosen);
        List<Integer> bestRouteSolver = dp.buildTour(distances, null, firstCityIndex, null, null, citiesIndex, firstCityIndex);

        comp.compareAlgorithms(
        null,
        null,
        firstCityIndex, 
        citiesIndex, 
        firstCityIndex, 
        cityNames);
        
                
                
    }
} 