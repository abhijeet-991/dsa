package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class UberDeadlock {

    public boolean hasCycle(int n, int[][] edges) {
        int[] inDegree = new int[n];

        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int num = edge[0];
            int prerequisite = edge[1];
            adj.get(prerequisite).add(num);
        }

        for (List<Integer> list : adj) {
            for (Integer ele : list) {
                inDegree[ele]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        int processedNodes = 0;
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
                processedNodes++;
            }
        }

        while(!queue.isEmpty()) {
            int node = queue.poll();
            processedNodes++;
            List<Integer> neighbors = adj.get(node);
            for (Integer neighbor : neighbors) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return processedNodes != adj.size();
    }

    public static void main(String[] args) {
        UberDeadlock solver = new UberDeadlock();

        int n = 3;
        int[][] edges = {{1, 0}, {2, 1}, {0, 2}};

        boolean result = solver.hasCycle(n, edges);
        System.out.println("Does it have a cycle? " + result);
    }
}