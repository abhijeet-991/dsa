package sorting;

import java.util.Arrays;

public class SortUtil {

    public static int[] selectionSort(int[] nums) {
        // Check if the array is empty or has only one element.
        // If so, it's already sorted, so we return it as is.
        // Example: For an array [3], no sorting is needed.
        if (nums.length == 0 || nums.length == 1) {
            return nums;
        }

        // Iterate over each element in the array
        for (int i = 0; i < nums.length; i++) {
            // Find the index of the minimum value in the unsorted portion of the array
            // The unsorted portion starts from index i+1 to the end of the array.
            // Example: If nums = [64, 25, 12, 22, 11], on the first iteration, i = 0,
            // the unsorted portion is [25, 12, 22, 11].
            int minIndexToSwapWith = getMinIndex(i + 1, nums);

            // Compare the current element with the minimum element found
            // If the current element is greater than the found minimum element,
            // swap the two elements to move the minimum element to the correct position.
            // Example: If nums[i] = 64 and nums[minIndexToSwapWith] = 11,
            // we swap them to place 11 in its correct position.
            if (nums[i] > nums[minIndexToSwapWith]) {
                // Swap the elements using XOR bitwise operation
                // This is a bitwise way to swap two values without a temporary variable.
                // Example: To swap nums[i] = 64 and nums[minIndexToSwapWith] = 11:
                // nums[i] = nums[i] ^ nums[minIndexToSwapWith];
                // nums[minIndexToSwapWith] = nums[i] ^ nums[minIndexToSwapWith];
                // nums[i] = nums[i] ^ nums[minIndexToSwapWith];
                nums[i] = nums[i] ^ nums[minIndexToSwapWith];
                nums[minIndexToSwapWith] = nums[i] ^ nums[minIndexToSwapWith];
                nums[i] = nums[i] ^ nums[minIndexToSwapWith];
            }
        }

        // Return the sorted array
        // Example: After sorting, nums will be [11, 12, 22, 25, 64].
        return nums;
    }

    // Method to find the index of the minimum value in a portion of the array
// Starts from the given index (start) and goes to the end of the array.
    private static int getMinIndex(int start, int[] nums) {
        // Assume the first element in the unsorted portion is the minimum
        int minIndex = start;
        for (int i = start; i < nums.length; i++) {
            // Update minIndex if a smaller element is found
            // Example: If nums = [11, 25, 12, 22, 64] and start = 1,
            // it checks elements [25, 12, 22, 64] to find the smallest (12).
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static int[] bubbleSort(int[] nums) {
        // Iterate over the entire array
        // This outer loop runs from the start to the end of the array,
        // ensuring each element gets compared and placed in its correct position.
        // Example: For an array [64, 25, 12, 22, 11], it will run 5 times.
        for (int i = 0; i < nums.length; i++) {
            // Compare each element with the elements that follow it
            // This inner loop performs comparisons and possible swaps between elements.
            // It starts from index i+1 and goes to the end of the array.
            // Example: When i = 0, j will go from 1 to the end of the array.
            for (int j = i + 1; j < nums.length; j++) {
                // If the current element is greater than the next element, swap them
                // This condition ensures that larger elements "bubble up" to the end of the array.
                // Example: If nums = [64, 25, 12, 22, 11] and i = 0, j = 1,
                // it checks if nums[0] > nums[1] (64 > 25) and swaps if true.
                if (nums[j] < nums[i]) {
                    // Swap elements using XOR bitwise operation
                    // This method swaps two numbers without using a temporary variable.
                    // Example: To swap nums[i] = 64 and nums[j] = 25:
                    // nums[i] = nums[i] ^ nums[j]; // nums[i] becomes 41
                    // nums[j] = nums[i] ^ nums[j]; // nums[j] becomes 64
                    // nums[i] = nums[i] ^ nums[j]; // nums[i] becomes 25
                    nums[i] = nums[i] ^ nums[j];
                    nums[j] = nums[i] ^ nums[j];
                    nums[i] = nums[i] ^ nums[j];
                }
            }
        }
        // Return the sorted array
        // After the loops complete, the array is sorted in ascending order.
        // Example: For an array [64, 25, 12, 22, 11], after sorting, it becomes [11, 12, 22, 25, 64].
        return nums;
    }

    // Helper method to insert the element at index i into its correct position
    public static int[] insertionSort(int[] nums) {
        // If the array is empty or has only one element, it's already sorted
        // No need to perform any sorting operations.
        // Example: For an array [5] or [], no sorting is needed.
        if (nums.length == 0 || nums.length == 1) {
            return nums;
        }

        // Iterate over each element in the array starting from the second element
        // The first element is considered sorted by itself.
        // Example: For an array [64, 25, 12, 22, 11], the loop starts from index 1.
        for (int i = 1; i < nums.length; i++) {
            // Sort the array from the start up to the current index i
            // This function ensures that the subarray from index 0 to i is sorted.
            // Example: If nums = [64, 25, 12, 22, 11], for i = 1, it sorts the subarray [64, 25].
            sortTillI(i, nums);
        }

        // Return the sorted array
        // After all iterations, the entire array is sorted in ascending order.
        // Example: For an array [64, 25, 12, 22, 11], after sorting, it becomes [11, 12, 22, 25, 64].
        return nums;
    }

    private static void sortTillI(int i, int[] nums) {
        // If i is 0, no need to sort as there is only one element in the sorted portion
        // The element at index 0 is trivially sorted by itself.
        // Example: For i = 0, the function does nothing.
        if (i == 0) {
            return;
        }

        // Store the element at index i in a temporary variable
        // This element is the one being inserted into the sorted portion.
        // Example: For nums = [64, 25, 12, 22, 11] and i = 2,
        // temp = nums[2] = 12.
        int temp = nums[i];

        // Move elements of the sorted portion that are greater than temp to one position ahead
        // This loop creates space for the temp element to be inserted into its correct position.
        // Example: For nums = [25, 64, 12], and temp = 12,
        // nums[2] (12) needs to be compared with nums[1] (64).
        // Since 12 <= 64, 64 is shifted to nums[2], and i is decremented.
        // The loop continues as long as i > 0 and temp <= nums[i - 1].
        while (i > 0 && temp <= nums[i - 1]) {
            nums[i] = nums[i - 1]; // Shift the larger element one position to the right
            i--; // Move to the previous position
        }

        // Place the temporary element in its correct position
        // After shifting, insert temp at the position where it belongs.
        // Example: After shifting [64, 64, 12], nums becomes [12, 25, 64].
        nums[i] = temp;
    }

    public static int[] quickSort(int[] nums) {
        // If the array is empty or has only one element, it's already sorted
        // An array with zero or one element does not require sorting.
        // Example: For an array [5] or [], no sorting is needed.
        if (nums.length == 0 || nums.length == 1) {
            return nums;
        }

        // Call the helper method to sort the entire array
        // This initiates the recursive sorting process on the full array.
        // Example: For nums = [64, 25, 12, 22, 11], it starts sorting from index 0 to 4.
        quickSortHelper(nums, 0, nums.length - 1);

        // Return the sorted array
        // After the quickSortHelper completes, the array is sorted in ascending order.
        // Example: For nums = [64, 25, 12, 22, 11], after sorting, it becomes [11, 12, 22, 25, 64].
        return nums;
    }

    // Helper method to perform quick sort on a subarray
    private static void quickSortHelper(int[] nums, int l, int h) {
        // If the lower index is less than the higher index, the subarray has more than one element
        // The base case for recursion is when l >= h, meaning the subarray is already sorted.
        // Example: For nums = [11, 22, 12] and l = 0, h = 2, the condition l < h is true.
        if (l < h) {
            // Partition the array and get the pivot index
            // The partition method rearranges elements and returns the index where the pivot is placed.
            int j = partition(nums, l, h);

            // Recursively sort the subarrays on the left and right of the pivot
            // After partitioning, sort elements to the left and right of the pivot index.
            quickSortHelper(nums, l, j - 1);  // Sort the left subarray
            quickSortHelper(nums, j + 1, h);  // Sort the right subarray
        }
    }

    // Method to partition the array around a pivot
    private static int partition(int[] nums, int low, int high) {
        // Initialize pointers
        int l = low;   // Start of the subarray
        int h = high;  // End of the subarray

        // Use the first element as the pivot
        int pivot = nums[l]; // Choose the first element as the pivot
        int i = l;  // This will be used to place elements less than or equal to the pivot

        // Example: For nums = [64, 25, 12, 22, 11] and pivot = 64
        // Initial pointers: l = 0, h = 4

        // While the pointers haven't crossed
        while (l < h) {
            // Move the left pointer to the right until an element greater than the pivot is found
            // Example: For nums = [64, 25, 12, 22, 11] and pivot = 64
            // The pointer `l` will move past elements until it finds an element greater than 64
            while (l < nums.length && nums[l] <= pivot) {
                l++;
            }

            // Move the right pointer to the left until an element less than or equal to the pivot is found
            // Example: For nums = [64, 25, 12, 22, 11] and pivot = 64
            // The pointer `h` will move leftward to find an element less than or equal to 64
            while (h > -1 && nums[h] > pivot) {
                h--;
            }

            // If the pointers haven't crossed, swap the elements at the pointers
            // Example: If l = 2 and h = 1, and nums = [64, 25, 12, 22, 11]
            // We swap elements at indices 2 and 1, resulting in [64, 12, 25, 22, 11]
            if (l < h) {
                // Swap elements using XOR bitwise operation
                nums[l] = nums[l] ^ nums[h];
                nums[h] = nums[l] ^ nums[h];
                nums[l] = nums[l] ^ nums[h];
            }
        }

        // Place the pivot element at its correct position
        // Swap the pivot with the element at the high index
        // Example: After finishing the loop, pivot (64) will be swapped with the element at `h`
        if (h != i) {
            nums[i] = nums[i] ^ nums[h];
            nums[h] = nums[i] ^ nums[h];
            nums[i] = nums[i] ^ nums[h];
        }

        // Return the index of the pivot
        // The pivot is now at the correct position, and all elements to the left are less than or equal to the pivot,
        // and all elements to the right are greater than the pivot.
        // Example: After the pivot 64 is placed correctly, nums might be [11, 25, 12, 22, 64].
        return h;
    }

    public static int[] mergeSort(int[] nums) {
        // Call the helper method to start sorting the entire array
        // This kicks off the merge sort process which will divide the array and then merge the sorted halves.
        // Example: For nums = [38, 27, 43, 3, 9, 82, 10], sorting will start from index 0 to 6.
        mergeHelperMethod(nums, 0, nums.length - 1);

        // Return the sorted array
        // After the merge sort process is complete, the array is sorted in ascending order.
        // Example: For nums = [38, 27, 43, 3, 9, 82, 10], after sorting, it becomes [3, 9, 10, 27, 38, 43, 82].
        return nums;
    }

    // Helper method to recursively divide and sort the array
    private static void mergeHelperMethod(int[] nums, int start, int end) {
        // Base case: if the subarray has one or no elements, it's already sorted
        // A single element or an empty subarray is trivially sorted.
        // Example: For subarray [27, 38] with start = 1 and end = 2, we divide it further.
        if (start >= end) {
            return;
        }

        // Find the midpoint of the current subarray
        // The midpoint helps to divide the array into two halves for sorting.
        // Example: For start = 0 and end = 6, mid = (0 + (6 - 0) / 2) = 3.
        int mid = start + (end - start) / 2;

        // Recursively sort the left half of the array
        // Example: For the array [38, 27, 43, 3, 9, 82, 10], recursively sort the subarray [38, 27, 43, 3].
        mergeHelperMethod(nums, start, mid);

        // Recursively sort the right half of the array
        // Example: For the array [38, 27, 43, 3, 9, 82, 10], recursively sort the subarray [9, 82, 10].
        mergeHelperMethod(nums, mid + 1, end);

        // Merge the two sorted halves
        // After sorting the two halves, merge them into a single sorted subarray.
        // Example: Merge [38, 27, 3, 43] with [9, 10, 82] into a single sorted array.
        merge(nums, start, mid, end);
    }

    // Method to merge two sorted subarrays into a single sorted subarray
    private static void merge(int[] nums, int left, int mid, int right) {
        // Calculate the sizes of the two subarrays to be merged
        int n1 = mid - left + 1; // Size of the left subarray
        int n2 = right - mid;    // Size of the right subarray

        // Create temporary arrays to hold the elements of the two subarrays
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copy data to the temporary left array
        // Example: For nums = [3, 9, 10, 27, 38, 43, 82], leftArray would be [3, 9, 10, 27].
        for (int i = 0; i < n1; i++) {
            leftArray[i] = nums[left + i];
        }

        // Copy data to the temporary right array
        // Example: For nums = [3, 9, 10, 27, 38, 43, 82], rightArray would be [38, 43, 82].
        for (int j = 0; j < n2; j++) {
            rightArray[j] = nums[mid + 1 + j];
        }

        // Merge the two subarrays back into the original array
        int i = 0; // Pointer for leftArray
        int j = 0; // Pointer for rightArray
        int k = left; // Pointer for the original array

        // Merge the subarrays while both have elements left
        // Example: For leftArray = [3, 9, 10, 27] and rightArray = [38, 43, 82],
        // merge them to get [3, 9, 10, 27, 38, 43, 82].
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                nums[k] = leftArray[i];
                i++;
            } else {
                nums[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy any remaining elements from leftArray (if any)
        // If leftArray has remaining elements, they are already in sorted order.
        // Example: If leftArray = [27] and rightArray = [], copy 27 to nums.
        while (i < n1) {
            nums[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy any remaining elements from rightArray (if any)
        // If rightArray has remaining elements, they are already in sorted order.
        // Example: If rightArray = [38, 43, 82] and leftArray is empty, copy them to nums.
        while (j < n2) {
            nums[k] = rightArray[j];
            j++;
            k++;
        }
    }
}