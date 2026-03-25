package graph;

public class FloydWarshall {
    public void floyd(int[][] matrix) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int[][] distance = new int[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                } else if (matrix[i][j] == -1) {
                    distance[i][j] = Integer.MAX_VALUE;
                } else {
                    distance[i][j] = matrix[i][j];
                }
            }
        }

        for (int intermediate = 0; intermediate < rowLen; intermediate++) {
            for (int row = 0; row < rowLen; row++) {
                for (int col = 0; col < colLen; col++) {
                    if (row == col) continue;
                    int distanceToGoToIntermediateCol = distance[row][intermediate];
                    int distanceToGoToIntermediateRow = distance[intermediate][col];

                    if (distanceToGoToIntermediateRow != Integer.MAX_VALUE
                            && distanceToGoToIntermediateCol != Integer.MAX_VALUE) {
                        distance[row][col] = Math.min(distance[row][col],
                                distanceToGoToIntermediateCol + distanceToGoToIntermediateRow);
                    }
                }
            }
        }

        for (int i =0 ; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (distance[i][j] == Integer.MAX_VALUE) {
                    matrix[i][j] = -1;
                } else {
                    matrix[i][j] = distance[i][j];
                }
            }
        }
    }
}
