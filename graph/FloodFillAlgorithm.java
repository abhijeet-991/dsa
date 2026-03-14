package graph;

import java.util.LinkedList;
import java.util.Queue;

public class FloodFillAlgorithm {
    static class Solution {
        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            Queue<Pair> queue = new LinkedList<>();
            boolean[][] vis = new boolean[image.length][image[0].length];
            int[] directionRow = {-1, 0, +1, 0};
            int[] directionCol = {0, +1, 0, -1};
            int startingPixelColor = image[sr][sc];
            queue.offer(new Pair(sr, sc));
            while (!queue.isEmpty()) {
                Pair coordinates = queue.poll();
                int givenRow = coordinates.x;
                int givenCol = coordinates.y;
                vis[givenRow][givenCol] = true;
                image[givenRow][givenCol] = newColor;
                for (int i = 0; i < 4; i++) {
                    int nextRow = givenRow + directionRow[i];
                    int nextCol = givenCol + directionCol[i];
                    if (nextRow >= 0 && nextCol >= 0
                            && nextRow < image.length && nextCol < image[0].length
                            && image[nextRow][nextCol] == startingPixelColor && !vis[nextRow][nextCol]) {
                        queue.offer(new Pair(nextRow, nextCol));
                        image[nextRow][nextCol] = newColor;
                        vis[nextRow][nextCol] = true;
                    }
                }

            }

            return image;
        }

        static class Pair {
            int x;
            int y;
            public Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }
}
