package arrays.faq_medium;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        printArray(solution.leaders(new int[]{3,2,1}));
//        printArray(solution.replaceElements(new int[]{17,18,5,4,6,1}));
//        int value = 1;
//        int[][] matrix = new int[5][6];
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[i].length; j++) {
//                matrix[i][j] = value++;
//            }
//        }
        int[][] matrix = new int[4][4];
        matrix[0] = new int[]{1, 2, 3, 4};     // First row
        matrix[1] = new int[]{5, 6, 7, 8};     // Second row
        matrix[2] = new int[]{9, 10, 11, 12};     // Third row
        matrix[3] = new int[]{13, 14, 15, 16};  //fourth row

//        List<Integer> result = solution.spiralOrder(matrix);

//        List<List<Integer>> ans = solution.generate(5);
//        solution.rotate(matrix);


//        System.out.println(solution.threeSum(new int[]{2,-2,0,3,-3,5}));
//        System.out.println(solution.fourSum(new int[]{1000000000,1000000000,1000000000,1000000000}, 0));
//        solution.sortZeroOneTwo(new int[]{2,0,2,1,1,0});
        System.out.println(solution.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
//        System.out.println(Arrays.deepToString(matrix));
    }

    private static void printArray(int[] nums) {
        Arrays.stream(nums).forEach(x -> System.out.print(x + " "));
    }
}
