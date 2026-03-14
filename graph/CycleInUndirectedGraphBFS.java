package graph;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class CycleInUndirectedGraphBFS {
    public boolean isCycle(int V, List<Integer>[] adj) {
        boolean[] vis = new boolean[V];
        boolean cycleFound = false;
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                cycleFound = findCycle(i, V, adj, vis);
            }
            if (cycleFound) {
                break;
            }
        }
        return cycleFound;
    }

    private boolean findCycle(int i, int v, List<Integer>[] adj, boolean[] vis) {
        Queue<int[]> queue = new ArrayDeque<>(); // node and parent
        queue.offer(new int[]{i, -1});
        vis[i] = true;
        while (!queue.isEmpty()) {
            int[] coordinates = queue.poll();
            int currentNode = coordinates[0];
            int itsParent = coordinates[1];

            List<Integer> neighbors = adj[currentNode];
            for (Integer neighbor: neighbors) {
                if (!vis[neighbor]) {
                    queue.offer(new int[]{neighbor, currentNode});
                    vis[neighbor] = true;
                } else if (neighbor != itsParent) {
                    return true;
                }
            }
        }

        return false;
    }
}
