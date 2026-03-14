package graph;

import java.util.List;

public class CycleInUndirectedGraphDFS {
    public boolean isCycle(int V, List<Integer>[] adj) {
        boolean[] vis = new boolean[V];
        boolean cycleFound = false;
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                cycleFound = findCycle(i,-1,V, adj, vis);
                if (cycleFound) {
                    break;
                }
            }
        }
        return cycleFound;
    }

    private boolean findCycle(int currentNode, int itsParent, int V, List<Integer>[] adj, boolean[] vis) {

        vis[currentNode] = true;

        for (Integer neighbor : adj[currentNode]) {
            if (!vis[neighbor]) {
                if (findCycle(neighbor, currentNode, V, adj, vis)) {
                    return true;
                }
            } else if (neighbor != itsParent) {
                return true;
            }
        }

        return false;
    }
}
