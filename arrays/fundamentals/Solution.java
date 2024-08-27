package arrays.fundamentals;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Solution {

    // Method to perform a linear search for a target value in an array
    public int linearSearch(int[] nums, int target) {
        // Check if the array is null to avoid NullPointerException.
        // It's a good practice to handle null inputs gracefully.
        if (nums == null) {
            throw new IllegalArgumentException("Array is null, operation not possible");
        }

        // Create a stream of indices from 0 to nums.length - 1.
        // This allows us to check each index for the target value.
        // The filter operation ensures we only keep indices where nums[i] == target.
        // findFirst() retrieves the first matching index if available.
        OptionalInt result = IntStream.range(0, nums.length)
                .filter(i -> nums[i] == target)
                .findFirst();

        // Return the index if present; otherwise, return -1 indicating the target is not found.
        // The orElse method provides a default value (-1) if no matching index is found.
        return result.orElse(-1);
    }

    // Method to find the largest element in an array
    public int largestElement(int[] nums) {
        // Convert the array to an IntStream to utilize stream operations.
        // The max() function efficiently finds the maximum value in the stream.
        // orElseThrow ensures that we handle the case where the array is empty,
        // which would otherwise result in an error.
        return Arrays.stream(nums)
                .max()
                .orElseThrow(() -> new IllegalArgumentException("Array is empty"));
    }

    // Method to find the second largest element in an array
    public int secondLargestElement(int[] nums) {
        // First, find the maximum element in the array.
        // This is necessary because the second largest element has to be less than this maximum.
        int maxElement = Arrays.stream(nums)
                .max()
                .orElseThrow(() -> new IllegalArgumentException("Array is empty"));

        // Initialize 'ans' to the smallest integer value. 'ans' will store the second largest element.
        // 'temp' is used to keep track of the largest element found so far during iteration.
        int ans = Integer.MIN_VALUE;
        int temp = Integer.MIN_VALUE;

        // Iterate through the array to find the second largest element.
        // For each element, update 'temp' if it is greater than the current 'temp'.
        for (int num : nums) {
            if (num > temp) {
                temp = num;

                // Check if 'temp' is less than the overall maximum element.
                // This ensures we do not consider the largest element itself as the second largest.
                if (temp < maxElement) {
                    // Update 'ans' if 'temp' is greater than the current 'ans'.
                    // This step ensures we find the largest value that is less than the maximum element.
                    if (temp > ans) {
                        ans = temp;
                    }
                }
                // Reset 'temp' to find the next potential maximum.
                temp = Integer.MIN_VALUE;
            }
        }

        // Return -1 if no valid second largest was found (e.g., if the array had all identical elements),
        // otherwise return the second largest element found.
        return ans == Integer.MIN_VALUE ? -1 : ans;
    }

    // Method to find the maximum number of consecutive 1s in an array
    public int findMaxConsecutiveOnes(int[] nums) {
        // Initialize counters: 'cnt' for the current streak of 1s and 'ans' for the maximum length found.
        int cnt = 0;
        int ans = 0;

        // Iterate through the array to count consecutive 1s.
        // Update 'ans' whenever a streak of 1s ends, and reset 'cnt' for the next streak.
        for (int num : nums) {
            if (num == 1) {
                cnt++; // Increment count for consecutive 1s
            } else {
                // Update maximum length 'ans' if current streak 'cnt' is greater
                ans = Math.max(ans, cnt);
                cnt = 0; // Reset count for the next possible streak
            }
        }
        // Final check to update 'ans' for a streak that might end at the end of the array.
        ans = Math.max(cnt, ans);

        return ans;
    }

    // Method to rotate the array by one position to the right
    public void rotateArrayByOne(int[] nums) {
        // Check if the array is empty to avoid unnecessary operations.
        if (nums.length == 0) {
            return;
        }

        // Store the first element to place it at the end after the shift.
        int temp = nums[0];

        // Shift all elements one position to the left.
        // This overwrites the first element and shifts all subsequent elements to the left by one position.
        for (int i = 0; i < nums.length - 1; i++) {
            nums[i] = nums[i + 1];
        }

        // Place the initially stored first element at the end of the array.
        nums[nums.length - 1] = temp;
    }

    // Method to rotate the array by k positions to the right
    public void rotateArray(int[] nums, int k) {
        // Check if the array is empty to avoid unnecessary operations.
        if (nums.length == 0) {
            return;
        }

        int n = nums.length;
        // Adjust k to handle cases where k is greater than the array length.
        // This reduces unnecessary full rotations by taking the modulus.
        k = k % n;

        // Reverse the first k elements.
        // This places elements that will eventually move to the end of the array into their reversed positions.
        reverse(nums, 0, k - 1);

        // Reverse the remaining elements from index k to the end of the array.
        // This prepares the part of the array that will be shifted to the beginning.
        reverse(nums, k, n - 1);

        // Reverse the entire array.
        // This final reverse puts all elements into their final rotated positions.
        reverse(nums, 0, n - 1);
    }

    // Helper method to reverse a portion of the array from start to end indices
    private void reverse(int[] nums, int start, int end) {
        // Swap elements from the start index to the end index until the pointers meet in the middle.
        // This effectively reverses the segment of the array.
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}