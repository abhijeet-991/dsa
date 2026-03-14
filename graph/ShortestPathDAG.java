package graph;

import java.util.*;

class ShortestPathDAG {

    private class EdgeMetadata {
        Integer target;
        Integer distance;
        EdgeMetadata(Integer target, Integer distance) {
            this.target = target;
            this.distance = distance;
        }
    }

    public int[] shortestPath(int N, int M, int[][] edges) {
        List<List<EdgeMetadata>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < N; i++) adjacencyList.add(new ArrayList<>());

        for (int[] edge : edges) {
            int src = edge[0];
            int tar = edge[1];
            int cost = edge[2];
            adjacencyList.get(src).add(new EdgeMetadata(tar, cost));
        }

        int[] indegree = new int[N];
        int[] distanceArray = new int[N];
        Arrays.fill(distanceArray, Integer.MAX_VALUE);
        distanceArray[0] = 0; // hardcoding since the source is always 0 in this problem

        for (List<EdgeMetadata> edgeMetadataList : adjacencyList) {
            for (EdgeMetadata edgeMetadata : edgeMetadataList) {
                indegree[edgeMetadata.target]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer currentSource = queue.poll();

            List<EdgeMetadata> metadataList = adjacencyList.get(currentSource);
            for (EdgeMetadata edgeMetadata : metadataList) {
                indegree[edgeMetadata.target]--;
                if (distanceArray[currentSource] != Integer.MAX_VALUE) { //for unreachable nodes, unreachable nodes wil have source as INT_MAX
                    distanceArray[edgeMetadata.target] =
                            Math.min(distanceArray[edgeMetadata.target], distanceArray[currentSource] + edgeMetadata.distance);
                }
                if (indegree[edgeMetadata.target] == 0) {
                    queue.offer(edgeMetadata.target);
                }
            }
        }


        for (int i = 0; i< distanceArray.length; i++) {
            if (distanceArray[i] == Integer.MAX_VALUE) distanceArray[i] = -1;
        }

        return distanceArray;
    }
}