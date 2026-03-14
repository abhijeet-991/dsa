package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class BipartiteGraph {
    public static void main(String[] args) {
        int V = 3;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        adj.get(0).add(1); adj.get(1).add(0);
        adj.get(1).add(2); adj.get(2).add(1);
        adj.get(2).add(0); adj.get(0).add(2);

        BipartiteGraph solver = new BipartiteGraph();
        if (solver.isBipartite(V, adj)) {
            System.out.println("Graph is Bipartite ✅");
        } else {
            System.out.println("Graph is NOT Bipartite ❌");
        }
    }

    private boolean isBipartite(int V, List<List<Integer>> adj) {

        int[] stateTracker = new int[V];
        Arrays.fill(stateTracker, -1);

        boolean isBipartite;

        for (int i = 0; i < V; i++) {
            if (stateTracker[i] == -1) {
                isBipartite = performColoring(i, stateTracker, adj);
                if (!isBipartite) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean performColoring(int startingNode, int[] stateTracker, List<List<Integer>> adj) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(startingNode);
        stateTracker[startingNode] = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            int parentColor = stateTracker[node];

            List<Integer> neighbors = adj.get(node);
            for (Integer neighbor : neighbors) {
                if (stateTracker[neighbor] == -1) {
                    queue.offer(neighbor);
                    stateTracker[neighbor] = 1 - parentColor;
                } else if (parentColor == stateTracker[neighbor]) {
                    return false;
                }
            }
        }

        return true;
    }

}
