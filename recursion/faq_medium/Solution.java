package recursion.faq_medium;

import java.util.*;

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();

        // ******************* Initial Call to Helper *******************
        // We start the recursive helper function with the full target and the entire candidates array.
        // The ans list will store all the valid combinations.
        // The current combination is tracked using the integers list.
        combinationSumHelper(candidates, target, ans, new ArrayList<Integer>(), 0);

        return ans;
    }

    private void combinationSumHelper(int[] candidates, int target, List<List<Integer>> ans, ArrayList<Integer> integers, int idx) {

        // ******************* Base Condition *******************
        // If we have processed all candidates or if the target becomes 0 or less, we stop.
        // 1. If the target is exactly 0, we have found a valid combination, so we add it to the answer list.
        // 2. If the target is less than 0, we stop further exploration.
        if (idx == candidates.length || target <= 0) {
            if (target == 0) {
                // Add the current valid combination to the answer list.
                ans.add(new ArrayList<>(integers));
            }
            return;
        }

        // ******************* Include the Current Candidate *******************
        // We choose to include candidates[idx] in the current combination.
        // We subtract the candidate's value from the target and continue to explore further.
        // Since we can use the same candidate multiple times, we don't increment idx after this recursive call.
        integers.add(candidates[idx]);
        combinationSumHelper(candidates, target - candidates[idx], ans, integers, idx);

        // ******************* Backtracking Step *******************
        // After returning from the recursive call, we remove the last added element to explore other paths.
        integers.remove(integers.size() - 1);

        // ******************* Exclude the Current Candidate *******************
        // Now, we explore the path where we exclude the current candidate.
        // We move to the next index by incrementing idx and try with the next candidate.
        combinationSumHelper(candidates, target, ans, integers, idx + 1);

        // Example Walkthrough:
        // Consider candidates = [2, 3, 6, 7], target = 7
        // 1. Starting with index 0 (candidate = 2):
        //    - We include 2 in the combination -> Target becomes 5.
        //    - Include 2 again -> Target becomes 3.
        //    - Include 2 again -> Target becomes 1.
        //    - Exclude 2, move to candidate 3.
        //    - Exclude all, finally find [7].
        // The valid combinations are: [2, 2, 3], [7].

        // Another Example:
        // candidates = [2, 3, 5], target = 8
        // Combinations would be: [2, 2, 2, 2], [2, 3, 3], [3, 5].

        // ******************* Time Complexity Analysis *******************
        // Time complexity: O(2^t), where t is the target value.
        // - In the worst case, every candidate can be chosen multiple times, leading to a large number of possible combinations.
        // - The depth of the recursion tree can be proportional to t, and at each level, two choices are made (include or exclude).
        // Hence, the time complexity is approximately O(2^t).

        // ******************* Space Complexity Analysis *******************
        // Space complexity: O(t), where t is the target value.
        // - The depth of the recursion tree can go up to the target value in the worst case (each recursive call reduces the target).
        // - Each combination stored in the ans list takes up additional space, but the recursion stack itself consumes O(t) space.
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();

        // ******************* Step 1: Sort Candidates *******************
        // We first sort the candidates array to handle duplicates easily.
        // Sorting helps us avoid adding duplicate combinations to the final answer list by skipping over duplicates in the loop.
        Arrays.sort(candidates);

        // ******************* Initial Call to Helper *******************
        // Start the recursion with an empty combination (arrayList) and idx = 0 (first element).
        combinationSum2Helper(candidates, target, ans, 0, new ArrayList<Integer>());

        return ans;
    }

    private void combinationSum2Helper(int[] candidates, int target, List<List<Integer>> ans, int idx, ArrayList<Integer> arrayList) {

        // ******************* Base Case 1: Target Reached *******************
        // If the target becomes 0, it means we have found a valid combination that sums to the target.
        // Add the current combination (arrayList) to the answer list (ans).
        if (target == 0 && idx >= 0) { // Sneaky condition: Ensure idx is valid to avoid array errors.
            ans.add(new ArrayList<>(arrayList)); // Copy the arrayList to prevent modifications to the answer list.
            return;
        }

        // ******************* Base Case 2: Invalid Case *******************
        // If we have gone beyond the bounds of the array (idx >= candidates.length) or if the target becomes negative, stop recursion.
        // A negative target means the current combination exceeds the target, and no further exploration is necessary.
        if (idx >= candidates.length || target < 0 || (idx < 0 && target > 0)) {  // Sneaky condition: idx < 0 prevents out-of-bounds errors.
            return;
        }

        // ******************* Iterate Over Candidates *******************
        // We iterate over the candidates starting from the current index (idx) to explore all possible combinations.
        // This loop generates the subsets, ensuring that each number is considered only once in each position of the combination.
        for (int i = idx; i < candidates.length; i++) {

            // ******************* Skip Duplicates *******************
            // If the current candidate is the same as the previous one (candidates[i] == candidates[i - 1]) and `i > idx`, we skip it to avoid duplicate combinations.
            // This ensures that we do not repeat the same combination with the same set of elements.
            if (i > idx && candidates[i] == candidates[i - 1]) {  // Sneaky condition: i > idx ensures duplicate candidates are skipped only in future iterations.
                continue;  // Skip this candidate.
            }

            // ******************* Include the Current Candidate *******************
            // Add the current candidate (candidates[i]) to the combination (arrayList).
            arrayList.add(candidates[i]);

            // ******************* Recursive Call to Explore Further *******************
            // Make a recursive call to explore further with the new reduced target (target - candidates[i]).
            // We pass `i + 1` because each candidate can only be used once in this combination.
            combinationSum2Helper(candidates, target - candidates[i], ans, i + 1, arrayList);

            // ******************* Backtracking Step *******************
            // After returning from the recursive call, remove the last added element to explore other possible combinations.
            arrayList.remove(arrayList.size() - 1);
        }

        // Example Walkthrough:
        // Consider candidates = [10, 1, 2, 7, 6, 1, 5], target = 8
        // 1. Sort candidates: [1, 1, 2, 5, 6, 7, 10]
        // 2. Start from index 0:
        //    - Include 1 -> target becomes 7.
        //    - Include 1 again -> target becomes 6.
        //    - Include 6 -> target becomes 0 (valid combination [1, 1, 6]).
        //    - Backtrack, explore other paths: [1, 2, 5], [1, 7], [2, 6].
        // 3. Skip the second 1 (to avoid duplicate combination [1, 1, 6] again).
        // The valid combinations are: [1, 1, 6], [1, 2, 5], [1, 7], [2, 6].

        // ******************* Time Complexity Analysis *******************
        // Time complexity: O(2^n), where n is the number of candidates.
        // - In the worst case, each candidate can either be included or excluded, leading to 2^n possible combinations.
        // - Additionally, sorting the candidates array takes O(n log n) time.
        // Overall, the time complexity is O(2^n) for generating combinations, plus O(n log n) for sorting.

        // ******************* Space Complexity Analysis *******************
        // Space complexity: O(n), where n is the number of candidates.
        // - The recursion depth is at most O(n), as we explore each candidate recursively.
        // - The additional space is used by the arrayList to store the current combination and the answer list (ans) storing all valid combinations.
    }

    public List<Integer> subsetSums(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        // ******************* Initial Call to Helper *******************
        // We begin the recursion with an empty subset (arrayList) and idx = 0 (first element).
        subsetSumsHelper(nums, ans, new ArrayList<>(), 0);

        return ans;
    }

    private void subsetSumsHelper(int[] nums, List<Integer> ans, ArrayList<Integer> arrayList, int idx) {

        // ******************* Base Case: Reached the End of Array *******************
        // When we reach the end of the array (idx == nums.length), it means we have formed a subset.
        // We calculate the sum of the current subset (arrayList) and add it to the answer list (ans).
        // Stream API is used to calculate the sum of the integers in the subset.
        if (idx == nums.length) {
            ans.add(arrayList.stream().mapToInt(Integer::intValue).sum());
            return;
        }

        // ******************* Include the Current Element in the Subset *******************
        // Add the current element (nums[idx]) to the subset (arrayList).
        // This is one option: to include nums[idx] in the subset we're building.
        arrayList.add(nums[idx]);

        // ******************* Recursive Call After Including the Element *******************
        // Recursively call the helper function to continue building subsets with the next index (idx + 1).
        subsetSumsHelper(nums, ans, arrayList, idx + 1);

        // ******************* Backtracking Step *******************
        // After returning from the recursive call, remove the last added element to restore the subset
        // to its previous state. This allows us to explore other subsets without nums[idx].
        arrayList.remove(arrayList.size() - 1);

        // ******************* Exclude the Current Element from the Subset *******************
        // This is the other option: to exclude nums[idx] from the subset. We move to the next element
        // without adding nums[idx] to the subset.
        subsetSumsHelper(nums, ans, arrayList, idx + 1);

        // Example Walkthrough:
        // Consider nums = [1, 2], the function will generate subsets as follows:
        // 1. Start with an empty subset: []
        // 2. Include 1 -> Subset becomes [1], recursively explore further.
        // 3. Include 2 -> Subset becomes [1, 2] (end reached, sum is 3).
        // 4. Backtrack, exclude 2 -> Subset becomes [1] (end reached, sum is 1).
        // 5. Backtrack, exclude 1 -> Subset becomes [], now explore without 1.
        // 6. Include 2 -> Subset becomes [2] (end reached, sum is 2).
        // 7. Backtrack, exclude 2 -> Subset becomes [] (end reached, sum is 0).
        // The final answer list will contain sums: [3, 1, 2, 0].

        // ******************* Time Complexity Analysis *******************
        // Time complexity: O(2^n), where n is the number of elements in nums.
        // - Each element can either be included or excluded from the subset, leading to 2^n possible subsets.
        // - For each subset, we calculate the sum using the Stream API, which takes O(n) in the worst case.
        // Hence, the total time complexity is approximately O(2^n * n).

        // ******************* Space Complexity Analysis *******************
        // Space complexity: O(n), where n is the number of elements in nums.
        // - The recursion depth is at most O(n) as we explore subsets.
        // - The temporary subset (arrayList) can have at most n elements.
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        // ******************* Step 1: Sort Input Array *******************
        // Sorting the array helps handle duplicate elements easily by ensuring that duplicates are adjacent.
        // This will allow us to skip over duplicate elements in the subset generation process to avoid redundant subsets.
        Arrays.sort(nums);

        // ******************* Initial Call to Helper *******************
        // We begin the recursive process with an empty subset (integers) and start from index 0 (i = 0).
        subsetsWithDupHelper(nums, ans, 0, new ArrayList<>());

        return ans;
    }

    private void subsetsWithDupHelper(int[] nums, List<List<Integer>> ans, int i, List<Integer> integers) {

        // ******************* Base Case: Reached the End of Array *******************
        // If we reach the end of the array (i == nums.length), it means we have formed a complete subset.
        // We add a copy of the current subset (integers) to the answer list (ans).
        if (i == nums.length) {
            ans.add(new ArrayList<>(integers)); // Create a new list to avoid reference issues.
            return;
        }

        // ******************* Include the Current Element in the Subset *******************
        // We first explore the option of including the current element (nums[i]) in the subset.
        // This is one branch of the recursion where we include nums[i] and then continue building subsets with the next element.
        integers.add(nums[i]);
        subsetsWithDupHelper(nums, ans, i + 1, integers);

        // ******************* Backtracking Step *******************
        // After exploring the inclusion of nums[i], we backtrack by removing the last added element.
        // This allows us to explore the option of *not* including nums[i] in the subset.
        integers.remove(integers.size() - 1);

        // ******************* Skip Duplicate Elements *******************
        // After backtracking, we want to avoid including duplicate subsets.
        // If the next element is the same as the current one (nums[i] == nums[i+1]), we skip it to prevent adding the same subset multiple times.
        // We move the index `i` forward while the next element is the same as the current one.
        while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
            i++;
        }

        // ******************* Recursive Call to Exclude the Element *******************
        // This branch explores the option where we skip the current element (nums[i]) entirely.
        // We continue the recursive process with the next index (i + 1).
        subsetsWithDupHelper(nums, ans, i + 1, integers);

        // Example Walkthrough:
        // Consider nums = [1, 2, 2]
        // 1. Sort nums: [1, 2, 2]
        // 2. Start at index 0:
        //    - Include 1 -> Subset becomes [1], recursively explore further.
        //    - Include 2 -> Subset becomes [1, 2], recursively explore further.
        //    - Include 2 -> Subset becomes [1, 2, 2] (end reached, add to ans).
        //    - Backtrack, exclude the last 2 -> Subset becomes [1, 2].
        //    - Skip the second 2 (to avoid duplicate subsets) -> Continue exploring.
        //    - Backtrack, exclude 2 -> Subset becomes [1].
        //    - Exclude 1 -> Subset becomes [].
        //    - Include 2 -> Subset becomes [2], recursively explore further.
        //    - Include 2 again -> Subset becomes [2, 2] (end reached, add to ans).
        //    - Skip the second 2.
        // The final answer list will contain the unique subsets: [[], [1], [1, 2], [1, 2, 2], [2], [2, 2]].

        // ******************* Time Complexity Analysis *******************
        // Time complexity: O(2^n * n), where n is the number of elements in the input array nums.
        // - The factor 2^n comes from the fact that we generate all possible subsets, including handling duplicates.
        //   For each element, we have the option to either include it or exclude it, leading to 2^n subsets in total.
        //   This is why generating all subsets takes O(2^n) time.
        // - For each subset, adding a copy of the current subset to the answer list takes O(n) time in the worst case,
        //   because each subset can have up to n elements.
        // - Additionally, sorting the input array initially takes O(n log n) time.
        // Therefore, the overall time complexity is O(2^n * n) for generating the subsets, plus O(n log n) for sorting.

        // ******************* Space Complexity Analysis *******************
        // Space complexity: O(n * 2^n), where n is the number of elements in nums.
        // - The recursion depth is at most O(n), as we process each element recursively.
        // - The answer list (ans) will store all subsets, which can be as many as 2^n subsets.
        // - Each subset can have at most n elements, leading to a space complexity of O(n * 2^n) for storing all subsets.
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();

        // ******************* Initial Call to Helper *******************
        // Start the recursion from the number 1.
        // `k` is the target size of the combination, `n` is the target sum.
        combinationSum3Helper(ans, k, n, 1, new ArrayList<>(), 0);

        return ans;
    }

    private void combinationSum3Helper(List<List<Integer>> ans, int k, int n, int number, List<Integer> current, int sum) {

        // ******************* Base Case 1: Combination of Size k Reached *******************
        // If we have selected `k` numbers, we check if their sum equals `n`.
        // If so, we add the current combination to the answer list (ans).
        if (current.size() == k) {
            if (sum == n) {
                ans.add(new ArrayList<>(current)); // Add a copy of the current valid combination.
            }
            return; // Stop further recursion as we've reached the required size.
        }

        // ******************* Base Case 2: Prune Invalid Branches *******************
        // If the sum exceeds `n`, or if we have more than `k` numbers, we stop recursion.
        // This ensures that we don't explore invalid paths where the sum or size exceeds the given constraints.
        if (sum > n || current.size() > k) {
            return;
        }

        // ******************* Iterate Over Numbers *******************
        // We iterate over numbers from `number` (starting at 1) to 9.
        // Each number can only be used once in the combination, and numbers are always considered in increasing order.
        // We avoid using any number more than once, as we increment `i` after including it.
        for (int i = number; i <= 9; i++) {

            // ******************* Include the Current Number *******************
            // Add the current number `i` to the combination (current).
            current.add(i);

            // ******************* Recursive Call to Explore Further *******************
            // Make a recursive call to explore combinations that include this number, with `i + 1` as the next number.
            // The sum is incremented by `i`.
            combinationSum3Helper(ans, k, n, i + 1, current, sum + i);

            // ******************* Backtracking Step *******************
            // After returning from recursion, remove the last added number to explore other possibilities.
            current.remove(current.size() - 1);
        }

        // Example Walkthrough:
        // Consider k = 3, n = 7.
        // Start with number 1:
        // - Include 1 -> Subset becomes [1], sum = 1.
        // - Include 2 -> Subset becomes [1, 2], sum = 3.
        // - Include 4 -> Subset becomes [1, 2, 4], sum = 7 (valid combination).
        // - Backtrack, explore [1, 3, 3], [1, 6], etc.
        // - Continue with numbers 2, 3, and so on.
        // Valid combinations for (k = 3, n = 7) would be [1, 2, 4].

        // ******************* Time Complexity Analysis *******************
        // Time complexity: O(2^9), as we explore all combinations of numbers from 1 to 9.
        // - For each number, we either include or exclude it, leading to 2^9 possible combinations.
        // - The maximum depth of recursion is k, as we only select up to k numbers.
        // Therefore, the overall time complexity is O(2^9), or O(512), which is constant in practice.
        // Since the problem constrains `k <= 9`, this is efficient for the given constraints.

        // ******************* Space Complexity Analysis *******************
        // Space complexity: O(k), where k is the number of elements in the current combination.
        // - The recursion depth is at most k, as we recursively select up to k elements.
        // - The additional space is used by the `current` list to store the current combination.
        // The space complexity is O(k), plus the space required to store the result list `ans`, which holds all valid combinations.
    }

    public List<String> letterCombinations(String digits) {
        // ******************* Edge Case Handling *******************
        // If the input `digits` string is empty, return an empty list.
        if (digits.isEmpty()) {
            return Collections.emptyList(); // No combinations for an empty input.
        }

        // ******************* Keypad Mapping *******************
        // Create a mapping of digits to their corresponding letters (like a phone keypad).
        String[] keypad = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        // ******************* Initial Call to Helper *******************
        // Call the helper method to generate all possible letter combinations.
        return letterCombinationsHelper(digits, keypad);
    }

    private List<String> letterCombinationsHelper(String digits, String[] keypad) {

        // ******************* Determine Keypad Letters for Current Digit *******************
        // Calculate the index corresponding to the first digit in the string.
        // The index is calculated as `digits.charAt(0) - '2'` because '2' corresponds to the first index in the keypad array.
        int idx = digits.charAt(0) - '2'; // Maps '2' to index 0, '3' to index 1, and so on.
        String letters = keypad[idx];     // Fetch the corresponding letters for the current digit.

        // ******************* Base Case: Single Digit Left *******************
        // If the `digits` string contains only one character, return a list of individual letters mapped to that digit.
        if (digits.length() == 1) {
            List<String> temp = new ArrayList<>();
            for (char letter : letters.toCharArray()) {
                temp.add(String.valueOf(letter)); // Add each letter as a single-character string to the list.
            }
            return temp; // Return the list of possible letters for the single digit.
        }

        // ******************* Recursive Case: Multiple Digits *******************
        // Recursively call the helper for the remaining digits, excluding the first digit.
        List<String> res = letterCombinationsHelper(digits.substring(1), keypad);

        // ******************* Build New Combinations *******************
        // Now, combine the letters of the current digit with the results from the recursive call (res).
        List<String> ans = new ArrayList<>();
        for (char letter : letters.toCharArray()) {
            for (String re : res) {
                ans.add(letter + re); // For each letter of the current digit, append every result from the recursive call.
            }
        }

        // ******************* Return Final Combinations *******************
        // Return the final list of all combinations for the current level of recursion.
        return ans;

        // Example Walkthrough:
        // Consider digits = "23":
        // 1. First recursive call processes '2' -> letters = "abc".
        // 2. Recursive call processes '3' -> letters = "def".
        // 3. Recursive combinations:
        //    - Combine 'a' with ["d", "e", "f"] -> ["ad", "ae", "af"]
        //    - Combine 'b' with ["d", "e", "f"] -> ["bd", "be", "bf"]
        //    - Combine 'c' with ["d", "e", "f"] -> ["cd", "ce", "cf"]
        // Final result: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]

        // ******************* Time Complexity Analysis *******************
        // Time complexity: O(4^n), where `n` is the length of the digits string.
        // - For each digit, we generate up to 4 combinations (as in the case of digits 7 and 9, which have 4 letters).
        // - The recursion tree has depth `n` (equal to the number of digits), and at each level, we combine up to 4 letters with the results from the next level.
        // Therefore, the total number of combinations is bounded by O(4^n).

        // ******************* Space Complexity Analysis *******************
        // Space complexity: O(n) for the recursion stack depth.
        // - The maximum depth of recursion is `n`, as we process each digit recursively.
        // - Additionally, the result list grows to contain all possible combinations, which contributes O(4^n) space for storing the final answer.
    }

}