package arrays.logic_building;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{0,1,1};
//        solution.moveZeroes(nums);
//        printArray(solution.unionArray(new int[]{1,2}, new int[]{-1,2,2}));
//        printArray(nums);
        printArray(solution.intersection(new int[]{1}, new int[]{3}));
    }

    private static void printArray(int[] nums) {
        Arrays.stream(nums).forEach(x -> System.out.print(x + " "));
    }
}
