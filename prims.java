import java.util.Scanner;

public class prims {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] cost = new int[n][n];
        
        // Reading the adjacency matrix and setting up for Prim's Algorithm
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = in.nextInt();
                if (i != j && cost[i][j] == 0) {
                    cost[i][j] = Integer.MAX_VALUE; // Represent no connection with infinity
                }
            }
        }
        prim(n, cost);
        in.close();
    }

    public static void prim(int n, int[][] cost) {
        int[][] t = new int[n - 1][2];
        int[] near = new int[n];
        int minCost = 0;
        int k = 0, l = 0;
        int min = Integer.MAX_VALUE;

        // Find the initial minimum edge to start the MST
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (cost[i][j] < min) {
                    min = cost[i][j];
                    k = i;
                    l = j;
                }
            }
        }

        // Start MST with the smallest edge found
        t[0][0] = k;
        t[0][1] = l;
        minCost += cost[k][l];

        // Initialize the near array
        for (int i = 0; i < n; i++) {
            if (cost[i][k] < cost[i][l]) {
                near[i] = k;
            } else {
                near[i] = l;
            }
        }
        near[k] = near[l] = -1;  // Mark starting nodes as part of the MST

        // Building the rest of the MST
        for (int i = 1; i < n - 1; i++) {
            int j = -1;

            // Find the node with the minimum edge from the MST
            for (int x = 0; x < n; x++) {
                if (near[x] != -1 && (j == -1 || cost[x][near[x]] < cost[j][near[j]])) {
                    j = x;
                }
            }

            // Add the edge to the MST and update minCost
            t[i][0] = j;
            t[i][1] = near[j];
            minCost += cost[j][near[j]];

            // Mark this node as part of the MST
            near[j] = -1;

            // Update the near array
            for (int x = 0; x < n; x++) {
                if (near[x] != -1 && cost[x][j] < cost[x][near[x]]) {
                    near[x] = j;
                }
            }
        }
        System.out.println("*******Prims Algorithm implemented*******");
        System.out.println("Minimum Cost of MST: " + minCost);
    }
}
