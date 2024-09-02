package searching;

public class Solution {
    public int search(int[] nums, int target) {
        int start = 0; // Start index of the search range
        int end = nums.length - 1; // End index of the search range

        // Continue searching while the search range is valid (start <= end)
        while (start <= end) {
            // Calculate the middle index to avoid overflow in large arrays
            int mid = start + (end - start) / 2;

            // Example Intuition:
            // For an array [1, 2, 3, 4, 5, 6, 7] and target = 5:
            // - Initially, start = 0, end = 6, and mid = 3 (value = 4).
            // - Since 4 is less than 5, the search range is adjusted to start = 4, end = 6.
            // - Next, mid = 5 (value = 6). Now, end is adjusted to mid-1 = 4.
            // - Finally, start = 4, end = 4, and mid = 4 (value = 5), target found at index 4.

            // Check if the middle element is the target
            if (nums[mid] == target) {
                return mid; // Target found, return its index
            }
            // If the middle element is greater than the target
            else if (nums[mid] > target) {
                end = mid - 1; // Adjust the end to search the left half
            }
            // If the middle element is less than the target
            else {
                start = mid + 1; // Adjust the start to search the right half
            }
        }

        // If the target is not found in the array, return -1
        return -1;
    }

    public int lowerBound(int[] nums, int x) {
        int start = 0; // Start index of the search range
        int end = nums.length - 1; // End index of the search range
        int ansIndex = nums.length; // Default to array length if `x` is greater than all elements

        // Continue searching while the search range is valid (start <= end)
        while (start <= end) {
            // Calculate the middle index to avoid overflow in large arrays
            int mid = start + (end - start) / 2;

            // Example Intuition:
            // For an array [1, 2, 4, 6, 8] and x = 5:
            // - Initially, start = 0, end = 4, mid = 2 (value = 4).
            // - Since 4 is less than 5, we move start to mid + 1 = 3.
            // - Now, start = 3, end = 4, mid = 3 (value = 6).
            // - Since 6 is greater than 5, we set ansIndex = mid (3) and move end to mid - 1 = 2.
            // - The loop ends, and we return ansIndex = 3, which is the index of the first element >= 5.

            // Check if the middle element is greater than or equal to `x`
            if (nums[mid] >= x) {
                ansIndex = mid; // Update the answer index to the current middle index
                end = mid - 1;  // Narrow the search to the left half
            } else {
                start = mid + 1; // Narrow the search to the right half
            }
        }

        // Return the index of the first element >= `x`. If `x` is greater than all elements,
        // ansIndex will be equal to nums.length, indicating no valid index was found.
        return ansIndex;
    }

    public int upperBound(int[] nums, int x) {
        int start = 0; // Start index of the search range
        int end = nums.length; // End index is set to the length of the array
        int ansIndex = end; // Default to array length if `x` is less than all elements

        // Continue searching while the search range is valid (start < end)
        while (start < end) {
            // Calculate the middle index to avoid overflow in large arrays
            int mid = start + (end - start) / 2;

            // Example Intuition:
            // For an array [1, 2, 4, 6, 8] and x = 5:
            // - Initially, start = 0, end = 5, mid = 2 (value = 4).
            // - Since 4 is less than or equal to 5, we move start to mid + 1 = 3.
            // - Now, start = 3, end = 5, mid = 4 (value = 8).
            // - Since 8 is greater than 5, we set ansIndex = mid (4) and move end to mid = 4.
            // - The loop ends, and we return ansIndex = 4, which is the index of the first element > 5.

            // Check if the middle element is less than or equal to `x`
            if (nums[mid] <= x) {
                start = mid + 1; // Narrow the search to the right half
            } else {
                ansIndex = mid; // Update the answer index to the current middle index
                end = mid;      // Narrow the search to the left half
            }
        }

        // Return the index of the first element > `x`. If `x` is greater than or equal to all elements,
        // ansIndex will be equal to nums.length, indicating no element > `x` was found.

        // Note: `end = mid` is used instead of `end = mid - 1` to ensure the search includes the current `mid`
        // when it's greater than `x`. This is because the upper bound is the smallest index where elements
        // are strictly greater than `x`. Using `end = mid - 1` might skip over the exact position where elements
        // start being greater than `x`.
        return ansIndex;
    }
}
