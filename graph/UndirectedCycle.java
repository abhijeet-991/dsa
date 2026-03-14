package graph;

import java.util.*;

public class UndirectedCycle {
    public static void main(String[] args) {
        int V = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        adj.get(0).add(1); adj.get(1).add(0);
        adj.get(1).add(2); adj.get(2).add(1);
        adj.get(2).add(0); adj.get(0).add(2);
        adj.get(2).add(3); adj.get(3).add(2);

        UndirectedCycle solver = new UndirectedCycle();
        if (solver.isCycle(V, adj)) {
            System.out.println("Cycle Detected ❌");
        } else {
            System.out.println("No Cycle ✅");
        }
    }

    public boolean isCycle(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V];
        
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (detect(i, adj, visited)) return true;
            }
        }
        return false;
    }

    private boolean detect(int start, List<List<Integer>> adj, boolean[] visited) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{start, -1});
        visited[start] = true;
        while (!queue.isEmpty()) {
            int[] metadata = queue.poll();
            int node = metadata[0];
            int itsParent = metadata[1];
            List<Integer> neighbors = adj.get(node);
            for (Integer neighbor : neighbors) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(new int[]{neighbor, node});
                } else if (neighbor != itsParent) {
                    return true;
                }
            }

        }

        return false;
    }
}