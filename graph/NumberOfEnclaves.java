package graph;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfEnclaves {
    public int numberOfEnclaves(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int gridRow = grid.length;
        int gridCol = grid[0].length;
        int ans = 0;
        boolean[][] vis = new boolean[gridRow][gridCol];
        for (int i = 0; i < gridRow; i++) {
            for (int j = 0; j < gridCol; j++) {
                if (grid[i][j] == 1 && !vis[i][j]) {
                    ans += bfs(i, j, gridRow, gridCol, grid, vis);
                }
            }
        }

        return ans;
    }

    private int bfs(int i, int j, int gridRow, int gridCol, int[][] grid, boolean[][] vis) {
        int[][] directions = new int[][]{{-1,0}, {0, 1}, {1, 0}, {0, -1}};
        boolean touchesBoundry = false;
        int cnt = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] coordinates = queue.poll();
            int givenRow = coordinates[0];
            int givenCol = coordinates[1];
            vis[i][j] = true;
            cnt++;
            for (int[] direction: directions) {
                int newRow = givenRow + direction[0];
                int newCol = givenCol + direction[1];

                if (newRow < 0 || newCol < 0 || newRow == gridRow || newCol == gridCol) {
                    touchesBoundry = true;
                }

                if (newRow >= 0 && newCol >= 0
                        && newRow < gridRow && newCol < gridCol
                        && grid[newRow][newCol] == 1 && !vis[newRow][newCol]) {
                    queue.offer(new int[]{newRow, newCol});
                     vis[newRow][newCol] = true;
                }
            }
        }

        return touchesBoundry ? 0 : cnt;
    }
}
