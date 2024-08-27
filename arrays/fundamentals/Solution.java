package arrays.fundamentals;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Solution {

    // Method to perform a linear search for a target value in an array
    public int linearSearch(int[] nums, int target) {
        // Check if the array is null
        if (nums == null) {
            throw new IllegalArgumentException("Array is null, operation not possible");
        }

        // Use IntStream to create a stream of indices from 0 to nums.length - 1
        // Filter indices where the value in nums at that index equals the target
        // Find the first index where this condition is true
        OptionalInt result = IntStream.range(0, nums.length).filter(i -> nums[i] == target).findFirst();

        // Return the found index or -1 if the target is not found
        return result.orElse(-1);
    }

    // Method to find the largest element in an array
    public int largestElement(int[] nums) {
        // Use Arrays.stream to create a stream from the array
        // Find the maximum value in the stream
        // Throw an exception if the array is empty
        return Arrays.stream(nums).max().orElseThrow(IllegalArgumentException::new);
    }

    // Method to find the second largest element in an array
    public int secondLargestElement(int[] nums) {
        // Find the maximum element in the array
        int maxElement = Arrays.stream(nums).max().orElseThrow(IllegalArgumentException::new);

        // Initialize variables for the second largest element and temporary comparisons
        int ans = Integer.MIN_VALUE; // Will hold the second largest value
        int temp = Integer.MIN_VALUE; // Temporary variable to track the current maximum during iteration

        // Iterate through the array
        for (int num : nums) {
            if (num > temp) {
                temp = num; // Update temp to the new maximum
                // Check if temp is less than the overall maximum
                if (temp < maxElement) {
                    // Update ans if temp is greater than the current second largest
                    if (temp > ans) {
                        ans = temp;
                    }
                }
                // Reset temp to minimum value to find the next potential maximum
                temp = Integer.MIN_VALUE;
            }
        }

        // If no valid second largest was found, return -1
        return ans == Integer.MIN_VALUE ? -1 : ans;
    }

    // Method to find the maximum number of consecutive 1s in an array
    public int findMaxConsecutiveOnes(int[] nums) {
        int cnt = 0; // Counter for current streak of 1s
        int ans = 0; // Maximum length of consecutive 1s found

        // Iterate through the array
        for (int num : nums) {
            if (num == 1) {
                cnt++; // Increment counter if the current number is 1
            } else {
                // Update the maximum length if the current streak ends
                ans = Math.max(ans, cnt);
                cnt = 0; // Reset counter for the next streak
            }
        }
        // Check for the maximum length one last time at the end of the array
        ans = Math.max(cnt, ans);

        return ans;
    }

    // Method to rotate the array by one position to the right
    public void rotateArrayByOne(int[] nums) {
        if (nums.length == 0) {
            return; // No need to rotate an empty array
        }
        // Store the first element to place it at the end
        int temp = nums[0];

        // Shift all elements one position to the left
        for (int i = 0; i < nums.length - 1; i++) {
            nums[i] = nums[i + 1];
        }

        // Place the stored first element at the end of the array
        nums[nums.length - 1] = temp;
    }

    // Method to rotate the array by k positions to the right
    public void rotateArray(int[] nums, int k) {
        if (nums.length == 0) {
            return; // No need to rotate an empty array
        }
        int n = nums.length;
        k = k % n; // Handle cases where k is greater than the array length

        // Reverse the first k elements
        reverse(nums, 0, k - 1);

        // Reverse the remaining elements from k to the end of the array
        reverse(nums, k, n - 1);

        // Reverse the entire array to get the final rotated array
        reverse(nums, 0, n - 1);
    }

    // Helper method to reverse a portion of the array
    private void reverse(int[] nums, int start, int end) {
        // Swap elements from start to end until the pointers meet
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}