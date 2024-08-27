package sorting;

public class SortUtil {

    public static int[] selectionSort(int[] nums) {
        // Check if the array is empty or has only one element
        // If so, it's already sorted, so we return it as is.
        if (nums.length == 0 || nums.length == 1) {
            return nums;
        }

        // Iterate over each element in the array
        for (int i = 0; i < nums.length; i++) {
            // Find the index of the minimum value in the unsorted portion of the array
            int minIndexToSwapWith = getMinIndex(i + 1, nums);

            // Compare the current element with the minimum element found
            // If the current element is greater than the found minimum element,
            // swap the two elements to move the minimum element to the correct position
            if (nums[i] > nums[minIndexToSwapWith]) {
                // Swap the elements using XOR bitwise operation
                nums[i] = nums[i] ^ nums[minIndexToSwapWith];
                nums[minIndexToSwapWith] = nums[i] ^ nums[minIndexToSwapWith];
                nums[i] = nums[i] ^ nums[minIndexToSwapWith];
            }
        }

        // Return the sorted array
        return nums;
    }

    // Helper method to find the index of the minimum element in the sub-array
// starting from the index 'start' to the end of the array
    private static int getMinIndex(int start, int[] nums) {
        // Initialize the minimum index to the starting index
        int minIndex = start;

        // Iterate over the sub-array to find the index of the minimum element
        for (int i = start; i < nums.length; i++) {
            // Update minIndex if a smaller element is found
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
        }

        // Return the index of the minimum element
        return minIndex;
    }

    public static int[] bubbleSort(int[] nums) {
        // Iterate over the entire array
        for (int i = 0; i < nums.length; i++) {
            // Compare each element with the elements that follow it
            for (int j = i + 1; j < nums.length; j++) {
                // If the current element is greater than the next element, swap them
                if (nums[j] < nums[i]) {
                    // Swap elements using XOR bitwise operation
                    nums[i] = nums[i] ^ nums[j];
                    nums[j] = nums[i] ^ nums[j];
                    nums[i] = nums[i] ^ nums[j];
                }
            }
        }
        // Return the sorted array
        return nums;
    }

    public static int[] insertionSort(int[] nums) {
        // If the array is empty or has only one element, it's already sorted
        if (nums.length == 0 || nums.length == 1) {
            return nums;
        }

        // Iterate over each element in the array starting from the second element
        for (int i = 0; i < nums.length; i++) {
            // Sort the array from the start up to the current index i
            sortTillI(i, nums);
        }

        // Return the sorted array
        return nums;
    }

    // Helper method to insert the element at index i into its correct position
    private static void sortTillI(int i, int[] nums) {
        // If i is 0, no need to sort as there is only one element
        if (i == 0) {
            return;
        }

        // Store the element at index i in a temporary variable
        int temp = nums[i];

        // Move elements of the sorted portion that are greater than temp to one position ahead
        while (i > 0 && temp <= nums[i - 1]) {
            nums[i] = nums[i - 1];
            i--;
        }

        // Place the temporary element in its correct position
        nums[i] = temp;
    }

    public static int[] quickSort(int[] nums) {
        // If the array is empty or has only one element, it's already sorted
        if (nums.length == 0 || nums.length == 1) {
            return nums;
        }

        // Call the helper method to sort the array
        quickSortHelper(nums, 0, nums.length - 1);

        // Return the sorted array
        return nums;
    }

    // Helper method to perform quick sort on a subarray
    private static void quickSortHelper(int[] nums, int l, int h) {
        // If the lower index is less than the higher index, the subarray has more than one element
        if (l < h) {
            // Partition the array and get the pivot index
            int j = partition(nums, l, h);

            // Recursively sort the subarrays on the left and right of the pivot
            quickSortHelper(nums, l, j - 1);  // Sort the left subarray
            quickSortHelper(nums, j + 1, h);  // Sort the right subarray
        }
    }

    // Method to partition the array around a pivot
    private static int partition(int[] nums, int low, int high) {
        int l = low;   // Start of the subarray
        int h = high;  // End of the subarray

        // Use the first element as the pivot
        int pivot = nums[l];
        int i = l;  // This will be used to place elements less than the pivot

        // While the pointers haven't crossed
        while (l < h) {
            // Move the left pointer to the right until an element greater than the pivot is found
            while (l < nums.length && nums[l] <= pivot) {
                l++;
            }

            // Move the right pointer to the left until an element less than or equal to the pivot is found
            while (h > -1 && nums[h] > pivot) {
                h--;
            }

            // If the pointers haven't crossed, swap the elements at the pointers
            if (l < h) {
                // Swap elements using XOR bitwise operation
                nums[l] = nums[l] ^ nums[h];
                nums[h] = nums[l] ^ nums[h];
                nums[l] = nums[l] ^ nums[h];
            }
        }

        // Place the pivot element at its correct position
        // Swap the pivot with the element at the high index
        if (h != i) {
            nums[i] = nums[i] ^ nums[h];
            nums[h] = nums[i] ^ nums[h];
            nums[i] = nums[i] ^ nums[h];
        }

        // Return the index of the pivot
        return h;
    }

    public static int[] mergeSort(int[] nums) {
        // Call the helper method to start sorting the entire array
        mergeHelperMethod(nums, 0, nums.length - 1);

        // Return the sorted array
        return nums;
    }

    // Helper method to recursively divide and sort the array
    private static void mergeHelperMethod(int[] nums, int start, int end) {
        // Base case: if the subarray has one or no elements, it's already sorted
        if (start >= end) {
            return;
        }

        // Find the midpoint of the current subarray
        int mid = start + (end - start) / 2;

        // Recursively sort the left half of the array
        mergeHelperMethod(nums, start, mid);

        // Recursively sort the right half of the array
        mergeHelperMethod(nums, mid + 1, end);

        // Merge the two sorted halves
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
        for (int i = 0; i < n1; i++) {
            leftArray[i] = nums[left + i];
        }

        // Copy data to the temporary right array
        for (int j = 0; j < n2; j++) {
            rightArray[j] = nums[mid + 1 + j];
        }

        // Merge the two subarrays back into the original array
        int i = 0; // Pointer for leftArray
        int j = 0; // Pointer for rightArray
        int k = left; // Pointer for the original array

        // Merge the subarrays while both have elements left
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
        while (i < n1) {
            nums[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy any remaining elements from rightArray (if any)
        while (j < n2) {
            nums[k] = rightArray[j];
            j++;
            k++;
        }
    }
}