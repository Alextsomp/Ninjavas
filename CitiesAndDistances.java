public class CitiesAndDistances {
    //Class that contains the 15 available cities and the distances between each one
    final static String[] cities = new String[15];
    final static double[][] distances = new double[15][15];
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
        distances[14][3] = 1223;
    }

}
