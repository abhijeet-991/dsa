package graph;

import java.util.*;

public class CheapestFlightsWithinKStops {
    public class FlightMetadata {
        Integer flightDestination;
        Integer flightCost;
        FlightMetadata(Integer flightDestination, Integer flightCost) {
            this.flightDestination = flightDestination;
            this.flightCost = flightCost;
        }
    }

    public int CheapestFlight(int n, int[][] flights, int src, int dst, int K) {

        List<List<FlightMetadata>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] flight : flights) {
            int flightSource = flight[0];
            int flightDestinationFromSource = flight[1];
            int flightCostFromSrcToDest = flight[2];

            adjList.get(flightSource).add(
                    new FlightMetadata(flightDestinationFromSource, flightCostFromSrcToDest)
            );
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src, 0, 0});
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[src] = 0;

        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i =0 ; i < size; i++) {

                int[] nodeMetadata = queue.poll();
                int currentSource = nodeMetadata[0];
                int stopsTillSource = nodeMetadata[1];
                int costFromSource = nodeMetadata[2];

                List<FlightMetadata> neighbors = adjList.get(currentSource);
                for (FlightMetadata metadata : neighbors) {
                    int currentDestinationNode = metadata.flightDestination;
                    int costOfReachingThisDestinationNode = metadata.flightCost;

                    int totalCostToReach = costOfReachingThisDestinationNode + costFromSource;

                    if (totalCostToReach < distance[currentDestinationNode]) {
                        distance[currentDestinationNode] = totalCostToReach;
                        queue.offer(new int[]{currentDestinationNode, stopsTillSource+1 ,distance[currentDestinationNode]});
                    }
                }
            }
            level++;
            if (level == K+1) {
                break;
            }
        }
        return distance[dst] == Integer.MAX_VALUE ? -1: distance[dst];
    }

    public static void main(String[] args) {
        CheapestFlightsWithinKStops cheapestFlightsWithinKStops = new CheapestFlightsWithinKStops();
        int[][] flights = {
                {0, 1, 5},
                {1, 2, 5},
                {0, 3, 2},
                {3, 1, 2},
                {1, 4, 1},
                {4, 2, 1}
        };
        cheapestFlightsWithinKStops.CheapestFlight(5, flights,0, 2, 2);
    }
}
