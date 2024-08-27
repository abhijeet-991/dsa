package arrays.logic_building;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

    // Method to move all zeros in the array to the end while maintaining the order of non-zero elements
    public void moveZeroes(int[] nums) {
        int i = 0; // Pointer for the position to place the next non-zero element
        int j = 0; // Pointer to traverse through the array

        // Traverse through the array with two pointers
        while (i < nums.length && j < nums.length) {
            // If both pointers are at non-zero elements, move them forward
            if (nums[i] != 0 && nums[j] != 0) {
                i++;
                j++;
            }

            // Move the i pointer if it's at a non-zero element
            if (nums[i] != 0) {
                i++;
            }

            // Move the j pointer if it's at a zero
            if (nums[j] == 0) {
                j++;
            }

            // If j is within bounds and at a non-zero element
            if (j < nums.length && nums[j] != 0) {
                // Swap elements if the i pointer is at a zero
                if (nums[i] == 0) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    i++;
                }
                j++;
            }
        }
    }

    // Method to remove duplicates from a sorted array and return the new length of the array
    public int removeDuplicates(int[] nums) {
        // Special case: If the array has only one element, no duplicates to remove
        if (nums.length == 1) {
            return 1;
        }

        int j = 0; // Pointer for the position to place the next unique element
        int i = 0; // Pointer to traverse through the array

        // Traverse through the array
        while (i < nums.length) {
            // If the current element is different from the element at pointer j
            if (nums[i] != nums[j]) {
                nums[j + 1] = nums[i]; // Move the unique element to the next position
                j++; // Move the j pointer forward
            }
            i++; // Move the i pointer forward
        }

        // Return the length of the array with unique elements
        return j + 1;
    }

    // Method to find the missing number in an array containing numbers from 0 to n
    public int missingNumber(int[] nums) {
        int n = nums.length; // The length of the array
        int sum = n * (n + 1) / 2; // Calculate the expected sum of numbers from 0 to n
        int arraySum = Arrays.stream(nums).sum(); // Calculate the actual sum of numbers in the array
        return sum - arraySum; // The difference is the missing number
    }

    // Method to find the single number in an array where every element appears twice except one
    public int singleNumber(int[] nums) {
        int result = 0; // Variable to hold the result

        // XOR all elements in the array
        for (int num : nums) {
            result ^= num;
        }

        // The result will be the number that appears only once
        return result;
    }

    // Method to find the union of two sorted arrays
    public int[] unionArray(int[] nums1, int[] nums2) {
        int n1 = nums1.length; // Length of the first array
        int n2 = nums2.length; // Length of the second array
        ArrayList<Integer> arrayList = new ArrayList<>(); // List to hold the union result
        int i = 0, j = 0; // Pointers for the two arrays

        // Traverse through both arrays
        while (i < n1 && j < n2) {
            if (nums1[i] == nums2[j]) {
                // Add the common element if it's not already added
                addToArrayList(arrayList, nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                // Add element from nums1 if it's smaller
                addToArrayList(arrayList, nums1[i]);
                i++;
            } else {
                // Add element from nums2 if it's smaller
                addToArrayList(arrayList, nums2[j]);
                j++;
            }
        }

        // Add remaining elements from nums1
        while (i < n1) {
            addToArrayList(arrayList, nums1[i]);
            i++;
        }

        // Add remaining elements from nums2
        while (j < n2) {
            addToArrayList(arrayList, nums2[j]);
            j++;
        }

        // Convert the ArrayList to an array and return
        return arrayList.stream().mapToInt(Integer::intValue).toArray();
    }

    // Method to find the intersection of two sorted arrays
    public int[] intersection(int[] nums1, int[] nums2) {
        int n1 = nums1.length; // Length of the first array
        int n2 = nums2.length; // Length of the second array
        ArrayList<Integer> arrayList = new ArrayList<>(); // List to hold the intersection result
        int i = 0, j = 0; // Pointers for the two arrays

        // Traverse through both arrays
        while (i < n1 && j < n2) {
            if (nums1[i] < nums2[j]) {
                i++; // Move pointer i if nums1[i] is smaller
            } else if (nums2[j] < nums1[i]) {
                j++; // Move pointer j if nums2[j] is smaller
            } else {
                // Add the common element
                arrayList.add(nums1[i]);
                i++;
                j++;
            }
        }

        // Convert the ArrayList to an array and return
        return arrayList.stream().mapToInt(Integer::intValue).toArray();
    }

    // Helper method to add a value to the ArrayList if it's not already the last added value
    private void addToArrayList(ArrayList<Integer> arrayList, int value) {
        // Add the value only if the list is empty or the value is not the same as the last value
        if (arrayList.isEmpty() || arrayList.get(arrayList.size() - 1) != value) {
            arrayList.add(value);
        }
    }
}