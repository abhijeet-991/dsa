package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    private void dfs(int startingVertex, List<List<Integer>> adj, boolean[] visited, List<Integer> ans) {
        visited[startingVertex] = true;
        ans.add(startingVertex);
        List<Integer> nodes = adj.get(startingVertex); // O(max of Edges)
        for (Integer node : nodes) {
            if (!visited[node]) {
                dfs(node, adj, visited, ans); // O(V)
            }
        }
    }

    public List<Integer> dfsOfGraph(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V]; //O(V)
        List<Integer> ans = new ArrayList<>();  // O(V)
        dfs(0, adj, visited, ans);
        return ans;
    }

    public List<Integer> bfsOfGraph(int V, List<List<Integer>> adj) {
        Queue<Integer> queue = new LinkedList<>(); // O(V)
        List<Integer> list = new ArrayList<>(); // O(V)
        boolean[] visited = new boolean[V]; // O(V)
        queue.add(0);
        while (!queue.isEmpty()) {
            int firstNode = queue.poll();
            list.add(firstNode);
            visited[firstNode] = true;
            List<Integer> nodes = adj.get(firstNode);
            for (Integer node : nodes) { // O(E)
                if (!visited[node]) {
                    queue.add(node);
                    visited[node] = true;
                }
            }
        } // O(V)
        return list;

        // Total SC - O(3V) ~ O(V)
        // Total TC - O(V) + O(E)
    }

    public int findNumberOfComponent(int V, List<List<Integer>> edges) {
        boolean[] visited = new boolean[V];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i< V; i++) {
            adj.add(new ArrayList<>());
        }

        for (List<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int cnt = 0;

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                cnt++;
                dfsConnectedComponent(i, adj, visited);
            }
        }

        return cnt;
    }

    private void dfsConnectedComponent(int i, List<List<Integer>> adj, boolean[] visited) {
        visited[i] = true;
        for (int k = 0 ; k < adj.get(i).size(); k++) {
            if (!visited[adj.get(i).get(k)]) {
                dfsConnectedComponent(adj.get(i).get(k), adj, visited);
            }
        }
    }
}