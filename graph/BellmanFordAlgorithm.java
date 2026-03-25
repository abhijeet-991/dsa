package graph;

import java.util.ArrayList;
import java.util.Arrays;

public class BellmanFordAlgorithm {
    static int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
        int CANT_BE_REACHED_VALUE = 1_000_000_000;
        int[] distance = new int[V];
        Arrays.fill(distance, CANT_BE_REACHED_VALUE);
        distance[S] = 0;
        boolean negativeCycle = false;
        for (int i =0; i < V; i++) {
            for (ArrayList<Integer> edgeMetadata : edges) {
                int source = edgeMetadata.get(0);
                int destination = edgeMetadata.get(1);
                int costFromSourceToDestination = edgeMetadata.get(2);
                if (distance[source] != CANT_BE_REACHED_VALUE) {
                    int totalCostToReach = distance[source] + costFromSourceToDestination;
                    if (totalCostToReach < distance[destination]) {
                        if (i == V-1) {
                            negativeCycle = true;
                            break;
                        }
                        distance[destination] = totalCostToReach;
                    }
                }
            }
        }
        return negativeCycle ? new int[]{-1} : distance;
    }
}
