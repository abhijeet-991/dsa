package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TopoSortBFS {
    public int[] topoSort(int V, List<List<Integer>> adj) {
        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                int node = adj.get(i).get(j);
                indegree[node]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] vis = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            list.add(currentNode);
            vis[currentNode] = true;

            List<Integer> neighbours = adj.get(currentNode);

            for (Integer neighbor : neighbours) {
                if (!vis[neighbor]) {
                    indegree[neighbor]--;
                    if (indegree[neighbor] == 0) {
                        queue.offer(neighbor);
                    }
                }
            }

        }

        return list.stream().mapToInt(i -> i).toArray();
    }
}
