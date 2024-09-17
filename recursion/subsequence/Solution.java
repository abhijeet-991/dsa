package recursion.subsequence;

public class Solution {
    public boolean checkSubsequenceSum(int[] nums, int k) {
        return checkSubsequenceSumHelper(nums, 0, nums.length, k, 0);
    }

    private boolean checkSubsequenceSumHelper(int[] nums, int idx, int length, int k, int currentSum) {
        // Base case: if we've considered all elements in the array
        if (idx < length) {
            // Check if the current subset sum equals k
            if (currentSum == k) {
                return true;  // Found a subset with the sum equal to k
            }

            // Recursive case 1: Exclude the current element (nums[idx]) from the subset
            boolean exclude = checkSubsequenceSumHelper(nums, idx + 1, length, k, currentSum);

            // Recursive case 2: Include the current element (nums[idx]) in the subset
            currentSum += nums[idx];  // Update the current sum to include nums[idx]
            boolean include = checkSubsequenceSumHelper(nums, idx + 1, length, k, currentSum);

            // Return true if either including or excluding the current element yields a subset sum of k
            return include || exclude;
        }
        // Base case: We've finished considering all elements
        // Return true if the current subset sum equals k
        return currentSum == k;

        // ******************* Time Complexity Analysis *******************
        // Time complexity: O(2^n), where n is the length of the array.
        // - Each element can either be included or excluded from the subsequence, leading to 2^n possible subsequences.
        // - The recursion tree has a maximum depth of n, and there are 2^n nodes in the tree.

        // ******************* Space Complexity Analysis *******************
        // Space complexity: O(n), where n is the length of the array.
        // - The recursion depth is O(n) in the worst case (when each element is processed).
        // - The additional space used for storing the current sum and other variables is O(1).
    }

    public int countSubsequenceWithTargetSum(int[] nums, int k) {
        // ******************* Recursive Call *******************
        // Start the recursive helper function with initial parameters:
        // - idx: starting index in the array (0)
        // - k: target sum we want to achieve
        // - currentSum: the current sum of elements considered so far (0)
        return countSubsequenceWithTargetSumHelper(nums, 0, k, 0);
    }

    private int countSubsequenceWithTargetSumHelper(int[] nums, int idx, int k, int currentSum) {
        // ******************* Base Case *******************
        // If idx reaches the length of the array, it means we've considered all elements.
        // Check if the current sum equals the target k.
        // If yes, return 1 (indicating one valid subsequence found); otherwise, return 0.
        if (idx == nums.length) {
            return currentSum == k ? 1 : 0;
        }

        // ******************* Recursive Case *******************
        // Option 1: Exclude the current element nums[idx] from the subsequence.
        // Recur to the next index without changing the current sum.
        int exclude = countSubsequenceWithTargetSumHelper(nums, idx + 1, k, currentSum);

        // Option 2: Include the current element nums[idx] in the subsequence.
        // Add nums[idx] to the current sum and recur to the next index.
        int include = countSubsequenceWithTargetSumHelper(nums, idx + 1, k, currentSum + nums[idx]);

        // ******************* Combine Results *******************
        // Return the total count of valid subsequences by summing the results of both options:
        // - include: subsequences including the current element
        // - exclude: subsequences excluding the current element
        return include + exclude;

        // ******************* Example Walkthrough *******************
        // Example: nums = [1, 2, 3], k = 3
        // - The function explores all subsequences of [1, 2, 3].
        // - Subsequences considered: [ ], [1], [2], [3], [1, 2], [1, 3], [2, 3], [1, 2, 3]
        // - Only [1, 2] and [3] sum to 3, so the result is 2.

        // ******************* Time Complexity Analysis *******************
        // Time complexity: O(2^n), where n is the length of the array.
        // - Each element can either be included or excluded from the subsequence, leading to 2^n possible subsequences.
        // - The recursion tree has a maximum depth of n, and there are 2^n nodes in the tree.

        // ******************* Space Complexity Analysis *******************
        // Space complexity: O(n), where n is the length of the array.
        // - The recursion depth is O(n) in the worst case (when each element is processed).
        // - The additional space used for storing the current sum and other variables is O(1).
    }
}