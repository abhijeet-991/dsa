package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class EventualSafeState {
    public int[] eventualSafeNodes(int V, int[][] adj) {
        boolean[] vis = new boolean[V];
        boolean[] recStack = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i] ) {
                findCycle(i, adj, vis, recStack);
            }
        }

        return IntStream.range(0, recStack.length)
                .filter(i -> !recStack[i])
                .toArray();
    }

    private boolean findCycle(int currentNode, int[][] adjacencyList, boolean[] vis, boolean[] recStack) {

        vis[currentNode] = true;
        recStack[currentNode] = true;

        boolean result = false;

        for (int neighbour : adjacencyList[currentNode]) {

            if (!vis[neighbour]) {
                if (findCycle(neighbour, adjacencyList, vis, recStack)) {
                    return true;
                }
            } else if (recStack[neighbour]) {
                return true;
            }

        }

        recStack[currentNode] = false;
        return result;

    }
}
