package arrays.logic_building;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

    // Method to move all zeros in the array to the end while maintaining the order of non-zero elements
    public void moveZeroes(int[] nums) {
        int i = 0; // Initialize i to track the position where the next non-zero element should be placed.
        int j = 0; // Initialize j to traverse through the array to find non-zero elements.

        // Loop through the array with pointer j to process each element
        while (j < nums.length) {
            // Check if the current element at index j is non-zero
            if (nums[j] != 0) {
                // Only perform a swap if i and j are pointing to different elements
                // This prevents unnecessary swaps if i and j are the same, which would be redundant
                if (i != j) {
                    // Swap the non-zero element at j with the element at i
                    // This moves the non-zero element to the correct position, which is at i
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
                // Move the i pointer forward to the next position
                // This is necessary to keep track of the next available position for another non-zero element
                i++;
            }
            // Always move the j pointer forward to continue examining the next element
            // This ensures we process every element in the array
            j++;
        }
    }

    // Method to remove duplicates from a sorted array and return the new length of the array
    public int removeDuplicates(int[] nums) {
        // Special case: If the array has only one element, there are no duplicates to remove.
        // A single-element array is already considered to have all unique elements.
        if (nums.length == 1) {
            return 1;
        }

        int j = 0; // Initialize j to track the position where the next unique element should be placed.
        // j starts at 0 and will move forward as we find unique elements.

        int i = 0; // Initialize i to traverse through the entire array.
        // i will scan through each element of the array to check for uniqueness.

        // Traverse through the array with pointer i
        while (i < nums.length) {
            // Check if the current element at index i is different from the element at index j.
            // nums[j] is the last unique element found so far.
            // nums[i] is being checked to see if it's a new unique element.
            if (nums[i] != nums[j]) {
                // If nums[i] is different from nums[j], it means nums[i] is a new unique element.
                // Place this new unique element in the position immediately after the last unique element.
                nums[j + 1] = nums[i]; // Move nums[i] to position j + 1.
                // Increment j to point to the next position for any future unique elements.
                j++; // j now points to the new last unique element.
            }
            // Always move the i pointer to continue checking the next element.
            // We need to examine all elements to ensure all duplicates are removed.
            i++;
        }

        // Return the length of the array containing only unique elements.
        // j is the index of the last unique element found, so j + 1 gives the total count of unique elements.
        return j + 1;
    }

    // Method to find the missing number in an array containing numbers from 0 to n
    public int missingNumber(int[] nums) {
        int n = nums.length; // The length of the array represents the highest number (n) in the range 0 to n.

        // Calculate the expected sum of all numbers from 0 to n using the formula for the sum of the first n natural numbers.
        // Formula: sum = n * (n + 1) / 2
        // This sum includes all numbers from 0 to n without any missing.
        int sum = n * (n + 1) / 2;

        // Calculate the actual sum of the numbers present in the array.
        // Arrays.stream(nums).sum() computes the sum of all elements in the array.
        int arraySum = Arrays.stream(nums).sum();

        // The difference between the expected sum and the actual sum is the missing number.
        // Since we have one number missing, subtracting the actual sum from the expected sum gives the missing number.
        return sum - arraySum;
    }

    // Method to find the single number in an array where every element appears twice except one
    public int singleNumber(int[] nums) {
        int result = 0; // Initialize result to 0. This variable will hold the cumulative XOR result.

        // XOR all elements in the array.
        // The XOR operation has a unique property: a number XORed with itself results in 0,
        // and a number XORed with 0 results in the number itself.
        // Since every number except one appears twice, all pairs will cancel each other out (resulting in 0),
        // and the final result will be the number that appears only once.
        for (int num : nums) {
            result ^= num;
        }

        // The result after processing all elements is the number that appears only once.
        return result;
    }

    // Method to find the union of two sorted arrays
    public int[] unionArray(int[] nums1, int[] nums2) {
        int n1 = nums1.length; // Length of the first array
        int n2 = nums2.length; // Length of the second array
        ArrayList<Integer> arrayList = new ArrayList<>(); // List to hold the union result
        int i = 0, j = 0; // Pointers to traverse through the two arrays

        // Traverse through both arrays simultaneously
        while (i < n1 && j < n2) {
            if (nums1[i] == nums2[j]) {
                // If elements are equal, add the element to the result list if it’s not already added
                // This ensures that the common element is added only once
                addToArrayList(arrayList, nums1[i]); // Add the common element
                i++; // Move the pointer in nums1
                j++; // Move the pointer in nums2
            } else if (nums1[i] < nums2[j]) {
                // If the element in nums1 is smaller, add it to the result list
                // This is because nums1[i] is less than nums2[j], so it should be included
                addToArrayList(arrayList, nums1[i]); // Add the element from nums1
                i++; // Move the pointer in nums1
            } else {
                // If the element in nums2 is smaller, add it to the result list
                // This is because nums2[j] is less than nums1[i], so it should be included
                addToArrayList(arrayList, nums2[j]); // Add the element from nums2
                j++; // Move the pointer in nums2
            }
        }

        // Add remaining elements from nums1 if there are any left
        // This handles the case where nums1 has elements left after nums2 is exhausted
        while (i < n1) {
            addToArrayList(arrayList, nums1[i]); // Add the remaining elements from nums1
            i++; // Move the pointer in nums1
        }

        // Add remaining elements from nums2 if there are any left
        // This handles the case where nums2 has elements left after nums1 is exhausted
        while (j < n2) {
            addToArrayList(arrayList, nums2[j]); // Add the remaining elements from nums2
            j++; // Move the pointer in nums2
        }

        // Convert the ArrayList to an array and return it
        // Convert the list of integers to a primitive int array for the final result
        return arrayList.stream().mapToInt(Integer::intValue).toArray();
    }

    // Helper method to add an element to the ArrayList only if it's not already present
    private void addToArrayList(ArrayList<Integer> list, int value) {
        // Add the value only if it's not already in the list
        // This ensures each element is included only once in the final union
        if (list.isEmpty() || list.get(list.size() - 1) != value) {
            list.add(value); // Add the unique value to the list
        }
    }

    // Method to find the intersection of two sorted arrays
    public int[] intersection(int[] nums1, int[] nums2) {
        int n1 = nums1.length; // Length of the first array. This helps to know the bounds for traversing nums1.
        int n2 = nums2.length; // Length of the second array. This helps to know the bounds for traversing nums2.
        ArrayList<Integer> arrayList = new ArrayList<>(); // Create a list to store the intersection of nums1 and nums2.
        int i = 0, j = 0; // Initialize pointers for traversing nums1 and nums2.

        // Traverse both arrays while there are elements in both arrays to compare
        while (i < n1 && j < n2) {
            if (nums1[i] < nums2[j]) {
                // If the current element in nums1 is smaller than the current element in nums2,
                // move the pointer i to the next element in nums1
                // This is because nums1[i] cannot be part of the intersection if it's smaller than nums2[j].
                i++;
            } else if (nums2[j] < nums1[i]) {
                // If the current element in nums2 is smaller than the current element in nums1,
                // move the pointer j to the next element in nums2
                // This is because nums2[j] cannot be part of the intersection if it's smaller than nums1[i].
                j++;
            } else {
                // If nums1[i] and nums2[j] are equal,
                // this means we have found a common element in both arrays.
                arrayList.add(nums1[i]); // Add the common element to the result list.
                i++; // Move to the next element in nums1 to find any other potential common elements.
                j++; // Move to the next element in nums2 to find any other potential common elements.
            }
        }

        // Convert the ArrayList to an array and return it
        // ArrayList stores the result, but the return type is an array.
        // The stream operation converts the list of integers to a primitive int array.
        return arrayList.stream().mapToInt(Integer::intValue).toArray();
    }
}