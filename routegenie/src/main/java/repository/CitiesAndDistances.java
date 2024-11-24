package repository;
import java.util.ArrayList;

public class CitiesAndDistances {
    //Class that contains the 15 available cities and the distances between each one
    final static String[] cities = new String[15];
    final static double[][] distances = new double[15][15];

    static {
        Cities();    // Automatically initialize cities
        Distances(); // Automatically initialize distances
    }

    public static void Cities() {
        //The first method that declares an array and then fills it with the cities

        cities[0] = "Athens";
        cities[1] = "Thessaloniki";
        cities[2] = "Patras";
        cities[3] = "Ioannina";
        cities[4] = "Tirana";
        cities[5] = "Skopje";
        cities[6] = "Sofia";
        cities[7] = "Podgorica";
        cities[8] = "Pristina";
        cities[9] = "Bucharest";
        cities[10] = "Belgrade";
        cities[11] = "Sarajevo";
        cities[12] = "Zagreb";
        cities[13] = "Chisinau";
        cities[14] = "Ljubljana";
    }

    public static String[] getCities() {
        return cities;
    }
    public static void Distances() {
        distances[0][0] = 0;
        distances[1][1] = 0;
        distances[2][2] = 0;
        distances[3][3] = 0;
        distances[4][4] = 0; //These are all the distances between the same cities so its always zero , for example Ioannina to Ioannina
        distances[5][5] = 0;
        distances[6][6] = 0;
        distances[7][7] = 0;
        distances[8][8] = 0;
        distances[9][9] = 0;
        distances[10][10] = 0;
        distances[11][11] = 0;
        distances[12][12] = 0;
        distances[13][13] = 0;
        distances[14][14] = 0;
        distances[1][0] = 494; //All the distances beginning from Athens
        distances[2][0] = 213; 
        distances[3][0] = 412; 
        distances[4][0] = 678; 
        distances[5][0] = 698; 
        distances[6][0] = 798; 
        distances[7][0] = 862; 
        distances[8][0] = 787; 
        distances[9][0] = 1184; 
        distances[10][0] = 1095; 
        distances[11][0] = 1061; 
        distances[12][0] = 1485; 
        distances[13][0] = 1651; 
        distances[14][0] = 1623; 
        distances[2][1] = 468; //All the distances beginning from Thessaloniki apart from the ones that have been already created the other way around
        distances[3][1] = 261;
        distances[4][1] = 397;
        distances[5][1] = 234;
        distances[6][1] = 292;
        distances[7][1] = 550;
        distances[8][1] = 323;
        distances[9][1] = 679;
        distances[10][1] = 631;
        distances[11][1] = 820;
        distances[12][1] = 1023;
        distances[13][1] = 1140;
        distances[14][1] = 1162;
        distances[3][2] = 222; //All the distances beginning from Patras apart from the ones that have already been created the other way around
        distances[4][2] = 538;
        distances[5][2] = 622;
        distances[6][2] = 756;
        distances[7][2] = 672;
        distances[8][2] = 744;
        distances[9][2] = 1143;
        distances[10][2] = 1057;
        distances[11][2] = 903;
        distances[12][2] = 1300;
        distances[13][2] = 1604;
        distances[14][2] = 1438;
        distances[4][3] = 316; //All the distances beginning from Ioannina apart from the ones that have already been created the other way around
        distances[5][3] = 415;
        distances[6][3] = 553;
        distances[7][3] = 449;
        distances[8][3] = 504;
        distances[9][3] = 940;
        distances[10][3] = 850;
        distances[11][3] = 680;
        distances[12][3] = 1164;
        distances[13][3] = 1407;
        distances[5][4] = 223; //All the distances beginning from Tirana apart from the ones that have already been created the other way around
        distances[6][4] = 473;
        distances[7][4] = 158;
        distances[8][4] = 257;
        distances[9][4] = 858;
        distances[10][4] = 603;
        distances[11][4] = 389;
        distances[12][4] = 786;
        distances[13][4] = 1330;
        distances[14][4] = 924;
        distances[6][5] = 236;  //All the distances beginning from Skopje apart from the ones that have already been created the other way around
        distances[7][5] = 330;
        distances[8][5] = 91.3;
        distances[9][5] = 629;
        distances[10][5] = 432;
        distances[11][5] = 507;
        distances[12][5] = 825;
        distances[13][5] = 1100;
        distances[14][5] = 963;
        distances[7][6] = 520; //All the distances beginning from Sofia apart from the ones that have already been created the other way around
        distances[8][6] = 333;
        distances[9][6] = 383;
        distances[10][6] = 392;
        distances[11][6] = 581;
        distances[12][6] = 785;
        distances[13][6] = 852;
        distances[14][6] = 924;
        distances[8][7] = 254;//All the distances beginning from Podgorica apart from the ones that have already been created the other way around
        distances[9][7] = 822;
        distances[10][7] = 447;
        distances[11][7] = 230;
        distances[12][7] = 627;
        distances[13][7] = 1314;
        distances[14][7] = 773;
        distances[9][8] = 721;//All the distances beginning from Pristina apart from the ones that have already been created the other way around
        distances[10][8] = 459;
        distances[11][8] = 429;
        distances[12][8] = 883;
        distances[13][8] = 1193;
        distances[14][8] = 1024;
        distances[10][9] = 597;//All the distances beginning from Bucharest apart from the ones that have already been created the other way around
        distances[11][9] = 877;
        distances[12][9] = 990;
        distances[13][9] = 474;
        distances[14][9] = 1128;
        distances[11][10] = 301;//All the distances beginning from Belgrade apart from the ones that have already been created the other way around
        distances[12][10] = 396;
        distances[13][10] = 960;
        distances[14][10] = 534;
        distances[12][11] = 402;//All the distances beginning from Sarajevo apart from the ones that have already been created the other way around
        distances[13][11] = 1249;
        distances[14][11] = 540;
        distances[13][12] = 1335;//All the distances beginning from Zagreb apart from the ones that have already been created the other way around
        distances[14][12] = 141;
        distances[14][13] = 1401;//The distance between Ljubljana and Chisinau because all the other distances have been covered before 
        for(int j=0; j < 15; j++) {
            for(int i =0;i<15;i++) {
                if(j>i){
                    distances[i][j] = distances[j][i];
                }
            }
        }
    }
    public static double getDistances(int x,int y) {
        return distances[x][y];
    }
    public static void printDistances(){
        for(int i=0 ; i<15;i++){
            for(int j=0;j<15;j++){
                System.out.println(distances[i][j]);
            }
        }
    }
    public static boolean checkExistance(String[] cities, int city) {
        if (city >= 0 && city < cities.length) {
            return true;    
        }
        return false;
    }

    public static void printCities(ArrayList<Integer> citiesChosen) {
        CitiesAndDistances.Cities();
        System.out.println("The cities that you have chosen are: ");
        for(int cityIndex : citiesChosen) {
            if (cityIndex >= 0 && cityIndex < cities.length) {
            System.out.println(cities[cityIndex]);
           
            }
        }
    }
}