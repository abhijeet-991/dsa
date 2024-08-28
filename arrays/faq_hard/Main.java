package arrays.faq_hard;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.majorityElementTwo(new int[]{1,2,1,1,3,2,2}));
        System.out.println(Arrays.toString(solution.findMissingRepeatingNumbers(new int[]{3,5,4,1,1})));
    }
}
