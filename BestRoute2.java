import java.util.List;
public class BestRoute2 {
    public int[] nearestNeighbour(int start, double dist[][], List<Integer>selected ) {
        double sum = 0;
        int n = selected.size();
        int k = 1;
        int [] Best = new int [n+2];
        Best[0] = start;
        Best[n+1] = start;
        int poli = start;
        int thesiMIN = 0;
        for (int i = 1; i <= n ; i++) {
            thesiMIN = 0;
            double MIN = 10000;
            for (int j = 1; j <= n ; j++) {
                if (selected.get(j) != 0) {
                    if (dist[poli][selected.get(j)] < MIN ) {
                        MIN = dist[poli][selected.get(j)];
                        thesiMIN = j;
                    }
                }
            }
            Best[k] = selected.get(thesiMIN);
            k = k + 1;
            poli = selected.get(thesiMIN);
            selected.set(thesiMIN,0);
            sum = sum + MIN;
        }
        sum = sum + dist[start][selected.get(thesiMIN)];
        return Best;
    }
}
