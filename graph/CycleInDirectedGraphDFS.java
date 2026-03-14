package graph;

import java.util.List;

public class CycleInDirectedGraphDFS {
    public boolean isCyclic(int N, List<List<Integer>> adj) {

        boolean[] vis = new boolean[N];
        boolean[] samePathVis = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                if (findCycle(i, adj, vis, samePathVis)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean findCycle(int i, List<List<Integer>> adj, boolean[] vis, boolean[] samePathVis) {

        vis[i] = true;
        samePathVis[i]= true;

        List<Integer> neighbors = adj.get(i);

        boolean result = false;

        for (Integer neighbor : neighbors) {

            if (!vis[neighbor]) {
                result = result || findCycle(neighbor, adj, vis, samePathVis);
                if (result) {
                    return true;
                }
            } else if (vis[neighbor] && samePathVis[neighbor]) {
                result = true;
            }
        }

        samePathVis[i] = false;
        return result;
    }
}
