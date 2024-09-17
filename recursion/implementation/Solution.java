package recursion.implementation;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public double myPow(double x, int n) {
        // Base case: anything raised to the power of 0 is 1
        if (n == 0) return 1;

        // Edge case: if the base 'x' is 0, any power of 0 is 0 (except 0^0 which we are not considering)
        if (x == 0) return 0;

        // Since n can be negative, we handle it by converting n to a long type to avoid overflow
        // For example: if n = Integer.MIN_VALUE, -n would overflow in the int range.
        long N = n;

        // If the exponent is negative, we invert the base (x -> 1/x) and make the exponent positive.
        // Example: x = 2, n = -3 -> x becomes 1/2 and n becomes 3. We will now calculate (1/2)^3.
        if (N < 0) {
            x = 1 / x;  // Invert the base
            N = -N;     // Make exponent positive
        }

        // Initialize 'result' to 1 since any number raised to the power 0 is 1.
        double result = 1.0;

        // 'base' is the current value of x (initially the base, but it will be squared during calculation).
        double base = x;

        // We will now use a loop to calculate the power using binary exponentiation (exponentiation by squaring).
        // The idea is to break down the exponent into powers of 2.
        // For example, x^13 = x * x^4 * x^8. This reduces the time complexity from O(n) to O(log n).

        while (N > 0) {
            // If the current exponent (N) is odd, we multiply 'result' by 'base'.
            // This is because any odd exponent can be split into x^1 * x^(even).
            if ((N % 2) == 1) {
                result *= base;  // Multiply 'result' by the current 'base'
            }

            // Square the base for the next iteration. This effectively divides the exponent by 2 in each step.
            base *= base;  // Square the base

            // Divide the exponent by 2 (integer division). This is how we break the problem into smaller parts.
            N /= 2;
        }

        // Once the loop finishes, 'result' will have the final answer.
        return result;

        // Example 1:
        // x = 2, n = 10 -> result will be 2^10 = 1024
        // Steps:
        // N = 10 (even), base = 2, square base to get 4, N -> 5
        // N = 5 (odd), result *= 4 -> result = 4, square base to get 16, N -> 2
        // N = 2 (even), base = 16, square base to get 256, N -> 1
        // N = 1 (odd), result *= 256 -> result = 1024, N -> 0 (end)

        // Example 2:
        // x = 2, n = -2 -> result will be 1 / 2^2 = 0.25
        // Steps:
        // N = 2 (even), base = 2, square base to get 4, N -> 1
        // N = 1 (odd), result *= 4 -> result = 4, N -> 0 (end)
        // Since n was negative, return 1 / result = 1 / 4 = 0.25

        // Time Complexity: O(log n)
        // Explanation: Since we're dividing the exponent by 2 in each iteration, the number of operations
        // is proportional to log2(n). Therefore, the time complexity is O(log n).
    }

    public List<String> generateParenthesis(int n) {
        // The result list that will store all valid combinations of parentheses
        List<String> ans = new ArrayList<>();

        // Call the recursive helper function with:
        // n open and n closed parentheses remaining, an empty string as output, and the result list
        parenthesisGenerator(n, n, "", ans);

        // Return the final list of valid parentheses combinations
        return ans;
    }

    private void parenthesisGenerator(int open, int closed, String output, List<String> ans) {
        // Base case: if there are no open or closed parentheses left to add,
        // this means the current output is a valid combination, so add it to the list
        if (open == 0 && closed == 0) {
            ans.add(output);  // Example: when open = 0 and closed = 0, a valid combination like "(())" is added
            return; // Backtrack to explore other combinations
        }

        // If there are still open parentheses left, we can add an open parenthesis to the output
        if (open != 0) {
            // Create a new string by adding an open parenthesis '(' to the current output
            String newOutput = output + "(";  // Example: output = "((", newOutput becomes "(("

            // Recur with one less open parenthesis
            parenthesisGenerator(open - 1, closed, newOutput, ans);
        }

        // If there are more closed parentheses than open ones, we can add a closed parenthesis ')'
        if (closed > open) {
            // Create a new string by adding a closed parenthesis ')' to the current output
            String newOutput = output + ")";  // Example: output = "((", newOutput becomes "(())"

            // Recur with one less closed parenthesis
            parenthesisGenerator(open, closed - 1, newOutput, ans);
        }

        /*
         * Time complexity: O(4^n / sqrt(n))
         * - There are Catalan number C(n) valid parentheses combinations for n pairs, and calculating each takes linear time.
         * - This gives an overall time complexity related to Catalan numbers, which is approximately O(4^n / sqrt(n)).
         *
         * Example walkthrough:
         * n = 3, initially:
         * - open = 3, closed = 3
         * - First, we add '(' recursively until we can't add anymore: "((("
         * - Then, we add ')', backtracking to explore combinations: "(()", "(())", and so on.
         */
    }

    public List<List<Integer>> subsets(int[] nums) {
        // List to store all the subsets (the power set).
        List<List<Integer>> ans = new ArrayList<>();

        // Temporary list to store the current subset during recursion.
        List<Integer> set = new ArrayList<>();

        // Start the recursive helper function from index 0.
        powerSetHelper(ans, set, 0, nums.length, nums);

        // Return the list of subsets.
        return ans;
    }

    private void powerSetHelper(List<List<Integer>> ans, List<Integer> set, int i, int length, int[] nums) {
        // Base case: If we've processed all elements (i == length), add the current subset to the answer list.
        if (i >= length) {
            // Add a copy of the current subset 'set' to 'ans' because 'set' is mutable.
            ans.add(new ArrayList<>(set));
            return;
        }

        // ******************* Intuition and Detailed Breakdown *******************

        // We're using recursion to generate all subsets. At each index `i`, we have two choices:
        // 1. Exclude the current element `nums[i]` from the subset.
        // 2. Include the current element `nums[i]` in the subset.

        // ************************ Example Walkthrough ***************************
        // Suppose nums = [1, 2, 3], we start at i = 0:
        //
        // Step 1 (i = 0): We decide to exclude 1.
        // Step 2 (i = 1): Now, we decide to exclude 2.
        // Step 3 (i = 2): Now, we decide to exclude 3.
        // Step 4: Since we reached i == nums.length, we add the empty set [] to 'ans'.
        // Then we backtrack and include 3, so now the subset is [3].
        // We add [3] to 'ans'.
        // We continue this process recursively to explore all combinations.

        // ******************* Recursive Case 1: Exclude nums[i] *******************
        // We do not include the element nums[i], and move to the next index.
        powerSetHelper(ans, set, i + 1, length, nums);

        // ******************* Recursive Case 2: Include nums[i] *******************
        // We include the element nums[i] in the current subset.
        set.add(nums[i]);  // Example: Add nums[i] = 1, 2, etc., depending on the recursion level.

        // Recursively process the next index after including the element.
        powerSetHelper(ans, set, i + 1, length, nums);

        // ******************* Backtracking Step *******************
        // After the recursive call, remove the last element added (nums[i]) to restore the state.
        // This is called backtracking and helps explore other subsets that do not include nums[i].
        set.remove(set.size() - 1);

        // ******************* Time Complexity Analysis *******************
        // Time complexity: O(2^n * n), where n is the length of nums.
        // - There are 2^n subsets for an array of length n (each element can either be included or excluded).
        // - For each subset, copying the subset to the answer list takes O(n) time in the worst case.
        // Hence, the total time complexity is O(2^n * n).

        // ******************* Space Complexity Analysis *******************
        // Space complexity: O(n), where n is the length of nums.
        // - The recursion depth is O(n), and the temporary subset (set) has at most n elements.
    }

}