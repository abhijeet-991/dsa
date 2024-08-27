package arrays.fundamentals;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {1,2,3,4,5};
        System.out.println(solution.linearSearch(nums, -1));
        System.out.println(solution.largestElement(nums));
        System.out.println(solution.secondLargestElement(nums));
        System.out.println(solution.findMaxConsecutiveOnes(new int[]{1,1,0,0,1}));
        solution.rotateArrayByOne(nums);
        Arrays.stream(nums).forEach(System.out::print);
    }
}
