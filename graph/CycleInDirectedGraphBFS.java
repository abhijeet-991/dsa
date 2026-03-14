package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class CycleInDirectedGraphBFS {
    public boolean isCyclic(int N, List<List<Integer>> adj) {

        int[] indegree = new int[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                int currentNode = adj.get(i).get(j);
                indegree[currentNode]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i< N; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            list.add(currentNode);
            List<Integer> neighbours = adj.get(currentNode);

            for (Integer neighbour : neighbours) {
                indegree[neighbour]--;
                if (indegree[neighbour] == 0) {
                    queue.offer(neighbour);
                }
            }
        }

        return list.size() == N;
    }
}
