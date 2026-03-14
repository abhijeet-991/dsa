package graph;

import java.util.ArrayDeque;
import java.util.Queue;

public class DistanceOfNearestCellHavingOne {
    public int[][] nearest(int[][] grid) {
        int maxLenRow = grid.length;
        int maxLenCol = grid[0].length;
        boolean[][] vis = new boolean[maxLenRow][maxLenCol];
        Queue<int[]> queue = new ArrayDeque<>();
        int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        for (int i = 0; i < maxLenRow; i++) {
            for (int j = 0; j < maxLenCol; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j, 0});
                    vis[i][j] = true;
                    grid[i][j] = 0;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] coordinates = queue.poll();
            int givenRow = coordinates[0];
            int givenCol = coordinates[1];
            int currentDistance = coordinates[2];

            for(int[] direction: directions) {
                int newRow = direction[0] + givenRow;
                int newCol = direction[1] + givenCol;
                int newDistance = 1 + currentDistance;

                if (newRow >= 0
                        && newCol >= 0
                        && newRow < maxLenRow
                        && newCol < maxLenCol
                        && grid[newRow][newCol] == 0
                        && !vis[newRow][newCol]) {
                    grid[newRow][newCol] = newDistance;
                    queue.offer(new int[]{newRow, newCol, newDistance});
                    vis[newRow][newCol] = true;
                }
             }
        }
        return grid;
    }
//    public int[][] nearest(int[][] grid) {
//        int maxLenRow = grid.length;
//        int maxLenCol = grid[0].length;
//        boolean[][] vis = new boolean[maxLenRow][maxLenCol];
//        int[][] tempGrid = new int[maxLenRow][maxLenCol];
//        for (int i = 0; i < maxLenRow; i++) {
//            for (int j = 0; j < maxLenCol; j++) {
//                tempGrid[i][j] = grid[i][j];
//            }
//        }
//        for (int i = 0; i < maxLenRow; i++) {
//            for (int j = 0; j < maxLenCol; j++) {
//                if (tempGrid[i][j] == 0) {
//                    bfs(grid, i, j ,maxLenRow, maxLenCol, vis, tempGrid);
//                } else {
//                    grid[i][j] = 0;
//                }
//            }
//        }
//        return grid;
//    }
//
//    private void bfs(int[][] grid, int i, int j, int maxLenRow, int maxLenCol, boolean[][] vis, int[][] tempGrid) {
//        int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
//        Queue<int[]> queue = new ArrayDeque<>();
//        queue.offer(new int[]{i, j, 0});
//        vis[i][j] = true;
//        while (!queue.isEmpty()) {
//            int[] coordinates = queue.poll();
//            int givenRow = coordinates[0];
//            int givenCol = coordinates[1];
//            int currentDistance = coordinates[2];
//
//            for (int[] direction: directions) {
//                int newRow = givenRow + direction[0];
//                int newCol = givenCol + direction[1];
//                int newDistance = currentDistance + 1;
//                if (newRow >= 0
//                        && newCol >= 0
//                        && newRow < maxLenRow
//                        && newCol < maxLenCol && !vis[newRow][newCol]) {
//                    if (tempGrid[newRow][newCol] == 1) {
//                        grid[i][j] = newDistance;
//                        return;
//                    }
//                    queue.offer(new int[]{newRow, newCol, newDistance});
//                    vis[newRow][newCol] = true;
//                }
//            }
//        }
//    }
}
