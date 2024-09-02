package hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int longestConsecutiveBrute(int[] nums) {
        if (nums.length == 0) return 0;

        Arrays.sort(nums);

        int maxLength = 1;
        int currentLength = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            } else if (nums[i] == nums[i - 1] + 1) {
                currentLength++;
            } else {
                maxLength = Math.max(maxLength, currentLength);
                currentLength = 1;
            }
        }

        maxLength = Math.max(maxLength, currentLength);

        return maxLength;
    }

    public int longestConsecutive(int[] nums) {
        // Edge case: If the array is empty, return 0 since there are no numbers to form a sequence.
        if (nums.length == 0) return 0;

        int ans = 1; // Initialize the answer (longest consecutive sequence length) to 1.

        // HashMap to store each number in the array.
        // Key: the number itself, Value: a boolean indicating if this number is the start of a sequence.
        HashMap<Integer, Boolean> hashMap = new HashMap<>();

        // First pass: Populate the HashMap with all numbers from the array.
        // We initially assume all numbers can be the start of a sequence.
        for (int num : nums) {
            hashMap.put(num, true);
        }

        // Second pass: Determine which numbers are actually the start of a sequence.
        for (int num : nums) {
            // If num-1 exists in the map, then num cannot be the start of a sequence.
            if (hashMap.containsKey(num - 1)) {
                hashMap.put(num, false); // Mark num as not being the start of a sequence.
            }
        }

        // Third pass: Find the longest consecutive sequence.
        for (Map.Entry<Integer, Boolean> entry : hashMap.entrySet()) {
            // We only care about numbers that are marked as the start of a sequence.
            if (entry.getValue()) {
                int currentNum = entry.getKey(); // Start of a potential sequence
                int cnt = 1; // Initialize the count of the current sequence length.

                // Example Intuition:
                // If the array is [100, 4, 200, 1, 3, 2]
                // After the second pass, the map would look like:
                // {100=true, 4=false, 200=true, 1=true, 3=false, 2=false}
                // This indicates that 100, 200, and 1 are possible sequence starts.

                // While loop to count the length of the sequence starting from currentNum.
                while (hashMap.containsKey(currentNum + 1)) {
                    currentNum++; // Move to the next number in the sequence
                    cnt++; // Increase the length of the sequence
                }

                // Update the maximum sequence length found so far.
                ans = Math.max(cnt, ans); // Compare current sequence length with the best found so far.
            }
        }

        // Return the length of the longest consecutive sequence found.
        return ans;
    }

    public int longestSubarray(int[] nums, int target) {
        // Edge case: If the array is empty, return 0 since there are no subarrays.
        if (nums.length == 0) {
            return 0;
        }

        // HashMap to store the cumulative sum (running sum) at each index.
        // Key: cumulative sum, Value: the first index at which this sum was encountered.
        HashMap<Integer, Integer> preSumHashMap = new HashMap<>();

        int runningSum = 0; // This will hold the cumulative sum as we iterate through the array.
        int ans = 0; // Initialize the answer (maximum subarray length with sum equal to target) to 0.

        // Iterate through the array to calculate the running sum and check for subarrays with sum equal to target.
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i]; // Update the running sum by adding the current element.

            // Example Intuition:
            // For an array [1, 2, 3, 7, 5] and target = 12, the subarray [2, 3, 7] sums to 12.
            // The running sum at index 2 is 6, and at index 4 is 12. The difference (12 - 12 = 0) shows
            // that the subarray from index 2 to index 4 has the target sum.

            // Check if the running sum itself equals the target.
            // If it does, then the subarray from the start to the current index has the target sum.
            if (runningSum == target) {
                ans = Math.max(ans, i + 1); // Update the maximum subarray length.
            }

            // Calculate the difference needed to find a valid subarray.
            // This difference would be the running sum minus the target.
            int intToFindInMap = runningSum - target;

            // Check if this difference has been seen before in the hashmap.
            // If it has, then the subarray between the previous index and the current index sums to the target.
            if (preSumHashMap.containsKey(intToFindInMap)) {
                int len = i - preSumHashMap.get(intToFindInMap); // Calculate the length of this subarray.
                ans = Math.max(len, ans); // Update the maximum subarray length if this one is longer.
            }

            // Only add the current running sum to the hashmap if it hasn't been added before.
            // This ensures we store the earliest index where this sum occurred.
            if (!preSumHashMap.containsKey(runningSum)) {
                preSumHashMap.put(runningSum, i);
            }
        }

        // Return the length of the longest subarray that sums to the target.
        return ans;
    }

    public int subarraySum(int[] nums, int target) {
        // HashMap to store the cumulative sum (running sum) and its frequency.
        // Key: cumulative sum, Value: number of times this sum has been seen.
        HashMap<Integer, Integer> preSumHashMap = new HashMap<>();

        // Initialize the map with 0 sum seen once, representing the sum of an empty subarray.
        preSumHashMap.put(0, 1);

        int runningSum = 0; // This will hold the cumulative sum as we iterate through the array.
        int cnt = 0; // This will count the number of subarrays that sum to the target.

        // Iterate through each number in the array.
        for (int num : nums) {
            runningSum += num; // Update the running sum by adding the current element.

            // Example Intuition:
            // For an array [1, 1, 1] and target = 2:
            // After processing the first element (runningSum = 1), the map is {0=1, 1=1}.
            // After processing the second element (runningSum = 2), the map is {0=1, 1=1, 2=1}.
            // At this point, we can see that runningSum - target (2 - 2 = 0) exists in the map,
            // meaning there is a subarray that sums to 2.

            // Check if runningSum - target has been seen before in the map.
            // If it has, it means there is a subarray (ending at the current index) that sums to the target.
            if (preSumHashMap.containsKey(runningSum - target)) {
                cnt += preSumHashMap.get(runningSum - target); // Add the frequency of (runningSum - target) to the count.
            }

            // Update the map with the current running sum.
            // If the running sum already exists, increment its count by 1.
            preSumHashMap.put(runningSum, preSumHashMap.getOrDefault(runningSum, 0) + 1);
        }

        // Return the total count of subarrays that sum to the target.
        return cnt;
    }

    public int subarraysWithXorK(int[] nums, int k) {
        int xr = 0; // This will hold the cumulative XOR as we iterate through the array.
        int cnt = 0; // This will count the number of subarrays that have XOR equal to k.

        // HashMap to store the cumulative XOR and its frequency.
        // Key: cumulative XOR, Value: number of times this XOR has been seen.
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        // Initialize the map with 0 XOR seen once, representing the XOR of an empty subarray.
        hashMap.put(0, 1);

        // Iterate through each number in the array.
        for (int num : nums) {
            xr = xr ^ num; // Update the cumulative XOR by XORing the current element.

            // Example Intuition:
            // For an array [4, 2, 2, 6, 4] and k = 6:
            // - After processing the first element (xr = 4), the map is {0=1, 4=1}.
            // - After processing the second element (xr = 6), the map is {0=1, 4=1, 6=1}.
            // - At this point, xr ^ k (6 ^ 6 = 0) exists in the map, meaning there is a subarray that XORs to k.

            // Calculate the value `x` which is the result of XOR between the current cumulative XOR (`xr`) and `k`.
            int x = xr ^ k;

            // If `x` exists in the map, it means there is a subarray that XORs to `k`.
            // The frequency of `x` in the map gives the count of such subarrays.
            cnt += hashMap.getOrDefault(x, 0);

            // Update the map with the current cumulative XOR.
            // If the cumulative XOR already exists, increment its count by 1.
            hashMap.put(xr, hashMap.getOrDefault(xr, 0) + 1);
        }

        // Return the total count of subarrays that have XOR equal to k.
        return cnt;
    }

}