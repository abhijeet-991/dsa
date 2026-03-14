package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopoSortDFS {
    public int[] topoSort(int V, List<List<Integer>> adj) {
        boolean[] vis = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                fillStack(i, adj, stack, vis);
            }
        }

        List<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }

        return list.stream().mapToInt(i -> i).toArray();
    }

    private void fillStack(int i, List<List<Integer>> adj, Stack<Integer> stack, boolean[] vis) {
        vis[i] = true;
        List<Integer> neighbours = adj.get(i);
        for (Integer neighbor: neighbours) {
            if (!vis[neighbor]) {
                fillStack(neighbor, adj, stack, vis);
            }
        }
        stack.add(i);
    }
}
