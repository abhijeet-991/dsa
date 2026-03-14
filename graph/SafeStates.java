package graph;

import java.util.*;

public class SafeStates {
    public static void main(String[] args) {
        // Test Case: 
        // 0 -> 1
        // 1 -> 2
        // 2 -> 0, 3 (Cycle 0-1-2, Node 3 is a terminal node)
        // 3 -> 4
        // Safe nodes should be 3 and 4.
        int[][] graph = {
            {1},
            {2},
            {0, 3},
            {4},
            {}
        };

        SafeStates solver = new SafeStates();
        List<Integer> result = solver.eventualSafeNodes(graph);
        System.out.println("Eventual Safe Nodes: " + result);
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int V = graph.length;
        int[] visited = new int[V];
        int[] pathVisited = new int[V];
        int[] check = new int[V];

        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                dfsCheck(i, graph, visited, pathVisited, check);
            }
        }

        List<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (check[i] == 1) {
                safeNodes.add(i);
            }
        }
        return safeNodes;
    }

    private boolean dfsCheck(int node, int[][] graph, int[] visited, int[] pathVisited, int[] check) {
        visited[node] = 1;
        pathVisited[node] = 1;
        int[] neighbors = graph[node];
        for (int neighbor : neighbors) {
            if (visited[neighbor] == 0) {
                if (dfsCheck(neighbor, graph, visited, pathVisited, check)) {
                    return true;
                }
            } else if (pathVisited[neighbor] == 1) {
                return true;
            }
        }
        check[node] = 1;
        pathVisited[node] = 0;
        return false;
    }
}