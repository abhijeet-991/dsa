package graph;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {
    public int orangesRotting(int[][] grid) {
        int maxRowLength = grid.length;
        int maxColLength = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < maxRowLength; i++) {
            for (int j = 0; j < maxColLength; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j, 0});
                }
            }
        }

        boolean[][] vis = new boolean[maxRowLength][maxColLength];

        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        int ans = 0;

        while (!queue.isEmpty()) {
            int[] coordinates = queue.poll();
            int givenRow = coordinates[0];
            int givenCol = coordinates[1];
            int currentLen = coordinates[2];
            vis[givenRow][givenCol] = true;
            ans = currentLen;

            for (int[] direction : directions) {
                int newRow = direction[0] + givenRow;
                int newCol = direction[1] + givenCol;
                int newLen = currentLen + 1;
                if (newRow >= 0
                        && newCol >= 0
                        && newRow < maxRowLength
                        && newCol < maxColLength
                        && grid[newRow][newCol] == 1 && !vis[newRow][newCol]) {
                    vis[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol, newLen});
                }
            }
        }

        for (int i = 0; i < maxRowLength; i++) {
            for (int j = 0; j < maxColLength; j++) {
                if (grid[i][j] == 1 && !vis[i][j]) {
                    return -1;
                }
            }
        }
        return ans;
    }
}
