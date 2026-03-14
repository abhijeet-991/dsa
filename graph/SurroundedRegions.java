package graph;

public class SurroundedRegions {
    public char[][] fill(char[][] mat) {
        int maxRowLen = mat.length;
        int maxColLen = mat[0].length;
        int[][] directions = new int[][]{{-1,0}, {0,1}, {1,0}, {0, -1}};
        boolean[][] vis = new boolean[maxRowLen][maxColLen];
        for (int row = 0; row < maxRowLen; row++) {
            for (int col = 0; col < maxColLen; col++) {
                if (row == 0 || col == 0 || row == maxRowLen-1 || col == maxColLen-1) {
                    if (mat[row][col] == 'O' && !vis[row][col]) {
                        dfs(mat, row, col, maxRowLen, maxColLen, vis, directions);
                    }
                }
            }
        }

        for (int i = 0; i < maxRowLen; i++) {
            for (int j = 0; j < maxColLen; j++) {
                if (mat[i][j] == 'O' && !vis[i][j]) {
                    mat[i][j] = 'X';
                }
            }
        }

        return mat;
    }

    private void dfs(char[][] mat, int row, int col, int maxRowLen, int maxColLen, boolean[][] vis, int[][] directions) {

        if (row < 0 || col < 0 || row >= maxRowLen || col >= maxColLen || vis[row][col] || mat[row][col] == 'X' ) {
            return;
        }

        vis[row][col] = true;
        for (int[] direction : directions) {
            dfs(mat, row + direction[0], col + direction[1], maxRowLen, maxColLen, vis, directions);
        }
    }
}
