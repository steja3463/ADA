import java.util.*;

public class OptimalBinarySearchTree {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] keys = new int[n];
        double[] p = new double[n]; // probabilities of internal nodes
        double[] q = new double[n + 1]; // probabilities of external nodes
        
        for (int i = 0; i < n; i++) {
            keys[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            p[i] = in.nextDouble();
        }
        for (int i = 0; i <= n; i++) {
            q[i] = in.nextDouble();
        }
        
        double cost = findOptimalCost(n, p, q);
        System.out.printf("%.4f\n", cost);
    }
    
    public static double findOptimalCost(int n, double[] p, double[] q) {
        double[][] cost = new double[n + 1][n + 1];
        double[][] weight = new double[n + 1][n + 1];
        
        // Base cases
        for (int i = 0; i <= n; i++) {
            cost[i][i] = q[i];
            weight[i][i] = q[i];
        }
        
        // L is the chain length
        for (int L = 1; L <= n; L++) {
            for (int i = 0; i <= n - L; i++) {
                int j = i + L;
                cost[i][j] = Double.MAX_VALUE;
                
                weight[i][j] = weight[i][j - 1] + p[j - 1] + q[j];
                
                for (int r = i + 1; r <= j; r++) {
                    double c = cost[i][r - 1] + cost[r][j] + weight[i][j];
                    if (c < cost[i][j]) {
                        cost[i][j] = c;
                    }
                }
            }
        }
        return cost[0][n];
    }
}
