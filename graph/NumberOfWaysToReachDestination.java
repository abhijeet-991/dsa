package graph;

import java.util.*;

public class NumberOfWaysToReachDestination {
    public int countPaths(int n, int[][] roads) {

        List<List<List<Integer>>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int time = road[2];
            adj.get(u).add(List.of(v, time));
            adj.get(v).add(List.of(u, time));
        }

        long[] distance = new long[n];
        long[] ways = new long[n];

        Arrays.fill(distance, Long.MAX_VALUE);
        Arrays.fill(ways, 0);
        distance[0] = 0;
        ways[0] = 1;

        Queue<long[]> minHeap = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        minHeap.offer(new long[]{0, 0});

        while (!minHeap.isEmpty()) {
            long[] currentNodeMetadata = minHeap.poll();
            long currentNode = currentNodeMetadata[0];
            long costTillCurrentNode = currentNodeMetadata[1];

            List<List<Integer>> neighborMetadataList = adj.get((int) currentNode);
            for (List<Integer> neighborMetadata : neighborMetadataList) {

                int neighborNode = neighborMetadata.get(0);
                int costOfReachingNodeFromSource = neighborMetadata.get(1);

                long totalCostOfReachingThisNeighborNode =
                        costTillCurrentNode + costOfReachingNodeFromSource;

                if (totalCostOfReachingThisNeighborNode < distance[neighborNode]) {
                    distance[neighborNode] = totalCostOfReachingThisNeighborNode;
                    ways[neighborNode] = ways[Math.toIntExact(currentNode)];
                    minHeap.offer(new long[]{neighborNode, totalCostOfReachingThisNeighborNode});
                } else if (totalCostOfReachingThisNeighborNode == distance[neighborNode]) {
                    ways[neighborNode] = (ways[neighborNode] + ways[Math.toIntExact(currentNode)]) % 1000000007;
                }
            }
        }
        return Math.toIntExact(ways[n - 1]);
    }

    public static void main(String[] args) {
        List<List<Integer>> roadsList = new ArrayList<>();
        NumberOfWaysToReachDestination destination = new NumberOfWaysToReachDestination();

        int[][] input = {
                {0,6,7}, {0,1,2}, {1,2,3}, {1,3,3}, {6,3,3},
                {3,5,1}, {6,5,1}, {2,5,1}, {0,4,5}, {4,6,2}
        };

        for (int[] edge : input) {
            List<Integer> edgeList = new ArrayList<>();
            edgeList.add(edge[0]);
            edgeList.add(edge[1]);
            edgeList.add(edge[2]);
            roadsList.add(edgeList);
        }
    }
}