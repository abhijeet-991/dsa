package graph;

public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int maxRowLen = grid.length;
        int maxColLen = grid[0].length;
        boolean[][] vis = new boolean[maxRowLen][maxColLen];
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int ans = 0;
        for (int i = 0; i < maxRowLen; i++) {
            for (int j = 0; j < maxColLen; j++) {
                if (grid[i][j] == 1 && !vis[i][j]) {
                    ans = Math.max(ans, dfs(grid, i, j, maxRowLen, maxColLen, vis, directions));
                }
            }
        }
        return ans;
    }

    private int dfs(int[][] grid, int i, int j, int maxRowLen, int maxColLen, boolean[][] vis, int[][] directions) {
        if (i < 0 || j < 0 || i >= maxRowLen || j >= maxColLen || vis[i][j] || grid[i][j] == 0) {
            return 0;
        }
        vis[i][j] = true;
        int result = 0;
        for (int[] direction: directions) {
            int newRow = i + direction[0];
            int newCol = j + direction[1];
            result = result + dfs(grid, newRow, newCol, maxRowLen, maxColLen, vis, directions);
        }
        return result+1;
    }

}
