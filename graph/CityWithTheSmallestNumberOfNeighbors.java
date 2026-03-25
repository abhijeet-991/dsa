package graph;

import java.util.Arrays;

public class CityWithTheSmallestNumberOfNeighbors {
    public int findCity(int n, int m, int edges[][], int distanceThreshold) {
        int neighbors = Integer.MAX_VALUE;
        int ansIndex = 0;

        int[][] distance = new int[n][n];
        for (int i =0 ; i < n; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        for (int i =0 ; i < m; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            int weight = edges[i][2];
            distance[from][to] = weight;
            distance[to][from] = weight;
        }

        int directionLen = distance.length;

        for (int i = 0; i < directionLen; i++) {
            for (int j = 0; j < directionLen; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                }
            }
        }

        for (int intermediate = 0; intermediate < directionLen; intermediate++) {
            for (int r = 0; r < directionLen; r++) {
                for (int c = 0; c < directionLen; c++) {
                    int distanceFromCurrentNodeViaIntermediateRow = distance[intermediate][c];
                    int distanceFromCurrentNodeViaIntermediateCol = distance[r][intermediate];
                    if (distanceFromCurrentNodeViaIntermediateRow != Integer.MAX_VALUE
                            && distanceFromCurrentNodeViaIntermediateCol != Integer.MAX_VALUE) {
                        distance[r][c]
                                = Math.min(distanceFromCurrentNodeViaIntermediateRow
                                + distanceFromCurrentNodeViaIntermediateCol, distance[r][c]);
                    }
                }
            }
        }

        int neighborsHavingLessThenDistance = 0;
        for (int i = 0; i < directionLen; i++) {
            for (int j = 0; j < directionLen; j++) {
                if (i != j && distance[i][j] <= distanceThreshold) {
                    neighborsHavingLessThenDistance++;
                }
            }
            if (neighborsHavingLessThenDistance <= neighbors) {
                neighbors = neighborsHavingLessThenDistance;
                ansIndex = i;
            }
            neighborsHavingLessThenDistance = 0;
        }

        return ansIndex;
    }
}
