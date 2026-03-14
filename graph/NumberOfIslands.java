package graph;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    static class Solution {
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            int ans = 0;
            int rowSize = grid.length;
            int colSize = grid[0].length;

            boolean[][] vis = new boolean[rowSize][colSize];

            for (int row = 0; row < rowSize; row++) {
                for (int col = 0; col < colSize; col++) {
                    if (grid[row][col] == '1' && !vis[row][col]) {
                        ans++;
                        traverseNeighbours(row, col, grid, vis);
                    }
                }
            }
            return ans;
        }

        private void traverseNeighbours(int row, int col, char[][] grid, boolean[][] vis) {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{row, col});
            vis[row][col] = true;
            while (!queue.isEmpty()) {
                int[] coordinates = queue.poll();
                int givenRow = coordinates[0];
                int givenCol = coordinates[1];
                for (int deltaRow = -1; deltaRow <= 1; deltaRow++) {
                    for (int deltaCol = -1; deltaCol <= 1; deltaCol++) {
                        int newRow = givenRow + deltaRow;
                        int newCol = givenCol + deltaCol;
                        if (newRow >= 0 && newCol >= 0
                                && newRow < grid.length && newCol < grid[0].length
                                && grid[newRow][newCol] == '1' && !vis[newRow][newCol]) {
                            queue.offer(new int[]{newRow, newCol});
                            vis[newRow][newCol] = true;
                        }
                    }
                }
            }
        }
    }
}
