package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int V = 23;

        int[][] adj = {
                {},                 // 0
                {8},                // 1
                {15, 6},            // 2
                {0, 13},            // 3
                {16, 1, 0},         // 4
                {12, 19},           // 5
                {3},                // 6
                {},                 // 7
                {},                 // 8
                {},                 // 9
                {15},               // 10
                {15},               // 11
                {22},               // 12
                {},                 // 13
                {},                 // 14
                {},                 // 15
                {4, 16},            // 16
                {1},                // 17
                {6, 20},            // 18
                {7, 4, 12, 8, 11, 2}, // 19
                {14, 20, 18},       // 20
                {},                 // 21
                {14, 5, 6, 3}       // 22
        };


        EventualSafeState safeState = new EventualSafeState();

        System.out.println(Arrays.toString(safeState.eventualSafeNodes(V, adj)));
    }

    static List<Integer>[] buildDirectedGraph(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
        }

        List<Integer>[] adj = new List[V];
        for (int i = 0; i < V; i++) {
            adj[i] = adjList.get(i);
        }

        return adj;
    }

    static List<Integer>[] buildUndirectedGraph(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        List<Integer>[] adj = new List[V];
        for (int i = 0; i < V; i++) {
            adj[i] = adjList.get(i);
        }

        return adj;
    }

}
