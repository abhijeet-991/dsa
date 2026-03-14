package graph;

import java.util.*;

public class ShortestPathUndirected {

    public int[] shortestPath(int[][] edges, int V, int E, int src) {

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        for (int[] edge: edges) {
            int v1 = edge[0];
            int v2 = edge[1];
            adj.get(v1).add(v2);
            adj.get(v2).add(v1);
        }

        return findShortestPath(adj, src, V);
    }

    private int[] findShortestPath(List<List<Integer>> adj, int src, int v) {

        int[] ans = new int[v];
        Arrays.fill(ans, Integer.MAX_VALUE);
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(src);
        ans[src] = 0;

        boolean[] vis = new boolean[v];
        vis[src] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            List<Integer> list = adj.get(node);
            for (Integer ele : list) {
                if (!vis[ele]) {
                    queue.offer(ele);
                    ans[ele] = ans[node]+1;
                    vis[ele] = true;
                }
            }
        }

        for (int i =0 ;i < ans.length; i++) {
            if (ans[i] == Integer.MAX_VALUE) ans[i] = -1;
        }

        return ans;
    }

    public static void main(String[] args) {
        int V = 9, E = 10;
        int[][] edges = {
            {0, 1}, {0, 3}, {3, 4}, {4, 5}, 
            {5, 6}, {1, 2}, {2, 6}, {6, 7}, 
            {7, 8}, {6, 8}
        };
        int src = 0;

        ShortestPathUndirected solver = new ShortestPathUndirected();
        int[] ans = solver.shortestPath(edges, V, E, src);

        System.out.println("Shortest distances from source " + src + ":");
        for (int i = 0; i < V; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}