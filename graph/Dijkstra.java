package graph;

import java.util.*;

public class Dijkstra {
    public  int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S){
        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        minHeap.offer(new int[]{S, 0});
        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[S] = 0;

        while(!minHeap.isEmpty()) {
            int[] nodeMetadata = minHeap.poll();
            int currentNode = nodeMetadata[0];
            int currentNodeDistance = nodeMetadata[1];

            if (currentNodeDistance > distance[currentNode]) continue;

            ArrayList<ArrayList<Integer>> neighbors = adj.get(currentNode);
            for (ArrayList<Integer> neighbor : neighbors) {
                int neighborNode = neighbor.get(0);
                int distanceToNeighborNode = neighbor.get(1);

                int newDistanceFromCurrentNodeToNeighborNode
                        = distanceToNeighborNode + currentNodeDistance;

                if (newDistanceFromCurrentNodeToNeighborNode < distance[neighborNode]) {
                    distance[neighborNode] = newDistanceFromCurrentNodeToNeighborNode;
                    minHeap.offer(new int[]{neighborNode, distance[neighborNode]});
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        int V = 3;
        int S = 2;
        int[][] edges = {{0, 1, 1}, {0, 2, 6}, {1, 2, 3}};

        ArrayList<ArrayList<ArrayList<Integer>>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            ArrayList<Integer> nodeU_to_V = new ArrayList<>(Arrays.asList(v, w));
            adjList.get(u).add(nodeU_to_V);

            ArrayList<Integer> nodeV_to_U = new ArrayList<>(Arrays.asList(u, w));
            adjList.get(v).add(nodeV_to_U);
        }

        int[] ans = dijkstra.dijkstra(V, adjList, S);

        System.out.println("Shortest distances from source " + S + ":");
        System.out.println(Arrays.toString(ans));
    }
}
