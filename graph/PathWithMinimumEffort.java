package graph;

import java.util.*;

public class PathWithMinimumEffort {
    public int MinimumEffort(List<List<Integer>> heights) {
        Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        int[][] effort = new int[heights.size()][heights.get(0).size()];
        for (int[] row : effort) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        minHeap.offer(new int[] {0, 0, 0});
        effort[0][0] = 0;

        int[] directionRow = {-1, 0, 1, 0};
        int[] directionCol = {0, 1, 0, -1};

        while(!minHeap.isEmpty()) {

            int[] nodeMetadata = minHeap.poll();
            int currentRow = nodeMetadata[0];
            int currentCol = nodeMetadata[1];
            int distanceTillThisPoint = nodeMetadata[2];

            for (int i = 0; i < 4; i++) {
                int newRow = currentRow + directionRow[i];
                int newCol = currentCol + directionCol[i];
                if (boundryCheck(heights, newRow, newCol)) {

                    int stepCostToReachCell = Math.abs(heights.get(currentRow).get(currentCol)
                            - heights.get(newRow).get(newCol));

                    int newEffort = Math.max(distanceTillThisPoint, stepCostToReachCell);
                    if (newEffort < effort[newRow][newCol]) {
                        effort[newRow][newCol] = newEffort;
                        minHeap.offer(new int[] {newRow, newCol, newEffort});
                    }
                }
            }
        }
        return effort[heights.size()-1][heights.get(0).size()-1];
    }

    private boolean boundryCheck(List<List<Integer>> heights, int newRow, int newCol) {
        if (newRow >= 0
                && newCol >= 0
                && newRow < heights.size() && newCol < heights.get(0).size()) {
            return true;
        }
        return false;
    }
}
