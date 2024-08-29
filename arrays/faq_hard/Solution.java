package arrays.faq_hard;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int majorityElement(int[] nums) {
        // Initialize a variable to store the candidate for the majority element.
        // The majority element is the one that appears more than n/2 times in the array.
        int candidate = 0;
        // Initialize a counter to track the balance between the candidate and other elements.
        // If the counter is positive, it means the candidate has more support than the elements seen so far.
        int cnt = 0;

        // Iterate through each element in the array to determine the majority element.
        for (int num : nums) {
            // If cnt is zero, we need to choose a new candidate for the majority element.
            // This happens when we've balanced out the previous candidate with other numbers.
            if (cnt == 0) {
                // Set the current number as the new candidate.
                // Example: In array [3, 3, 4, 2, 4, 4, 2, 4, 4], when cnt = 0 and num = 3,
                // candidate is updated to 3, and cnt is set to 1.
                candidate = num;
                // Set cnt to 1 as this candidate is now the first occurrence.
                cnt = 1;
            } else if (candidate == num) {
                // If the current number matches the candidate, increment the counter.
                // Example: Continuing with the array [3, 3, 4, 2, 4, 4, 2, 4, 4],
                // after num = 3, cnt is incremented to 2 because 3 matches the candidate.
                cnt++;
            } else {
                // If the current number does not match the candidate, decrement the counter.
                // This means that the candidate is losing support compared to other numbers.
                // Example: In array [3, 3, 4, 2, 4, 4, 2, 4, 4],
                // when num = 4 and candidate = 3, cnt is decremented to 1.
                cnt--;
            }
        }

        // Return the candidate as the result.
        // At this point, candidate is the element that, if it is guaranteed to be the majority, will be returned.
        // This works because the problem guarantees that a majority element always exists.
        // Example: After processing the array [3, 3, 4, 2, 4, 4, 2, 4, 4],
        // the final candidate should be 4, which is the majority element.
        return candidate;
    }

    public List<Integer> majorityElementTwo(int[] nums) {
        // Initialize potential majority element candidates and their counts.
        // candidate1 and candidate2 are the two potential majority elements.
        // cnt1 and cnt2 are their respective counts.
        int candidate1 = 0, candidate2 = 0;
        int cnt1 = 0, cnt2 = 0;

        // Phase 1: Find potential candidates using the Boyer-Moore Voting Algorithm
        for (int num : nums) {
            // Case 1: If cnt1 is zero, set candidate1 to num and cnt1 to 1.
            // Example: For nums = [1, 1, 2, 2, 3], candidate1 starts as 0.
            // When cnt1 is zero, we set candidate1 to the current num.
            if (cnt1 == 0 && num != candidate2) {
                candidate1 = num;
                cnt1 = 1;
            }
            // Case 2: If cnt2 is zero, set candidate2 to num and cnt2 to 1.
            // Example: Continuing from the above, if cnt2 is zero and num is different from candidate1,
            // we set candidate2 to the current num.
            else if (cnt2 == 0 && num != candidate1) {
                candidate2 = num;
                cnt2 = 1;
            }
            // Case 3: If num matches candidate1, increment cnt1.
            // Example: For nums = [1, 1, 2, 2, 2], candidate1 is 1 with cnt1 incrementing as we see more 1s.
            else if (num == candidate1) {
                cnt1++;
            }
            // Case 4: If num matches candidate2, increment cnt2.
            // Example: For nums = [1, 1, 2, 2, 2], candidate2 is 2 with cnt2 incrementing as we see more 2s.
            else if (num == candidate2) {
                cnt2++;
            }
            // Case 5: If num is neither candidate1 nor candidate2, decrement both counts.
            // Example: For nums = [1, 2, 3, 1, 2], when num = 3 and it's neither candidate1 (1) nor candidate2 (2),
            // decrement both cnt1 and cnt2.
            else {
                cnt1--;
                cnt2--;
            }
        }

        // Phase 2: Verify the actual counts of the potential candidates
        cnt1 = 0;
        cnt2 = 0;
        for (int num : nums) {
            // Count occurrences of candidate1.
            // Example: For nums = [1, 1, 2, 2, 2], count the number of 1s and 2s.
            if (candidate1 == num) cnt1++;
            // Count occurrences of candidate2.
            // Example: For nums = [1, 1, 2, 2, 2], candidate2 (if still valid) will have count of 2s.
            if (candidate2 == num) cnt2++;
        }

        // Determine the majority elements based on their counts.
        // A majority element must appear more than ⌊n/3⌋ times.
        // Example: For nums = [1, 1, 2, 2, 2], n/3 = 5/3 ≈ 1.67. Elements appearing more than 1 time are considered.
        List<Integer> result = new ArrayList<>();
        if (cnt1 > nums.length / 3) {
            result.add(candidate1);
        }
        if (cnt2 > nums.length / 3) {
            result.add(candidate2);
        }

        return result;
    }

    public int[] findDuplicateAndMissing(int[] nums) {
        // The length of the array is n, which should be the number of elements if no duplicates or missing values
        int n = nums.length;

        // Calculate the expected sum of numbers from 1 to n
        // Formula: n * (n + 1) / 2
        // This is because the sum of the first n natural numbers is always n * (n + 1) / 2.
        // We use this expected sum to compare against the actual sum.
        long expectedSum = (long) n * (n + 1) / 2;

        // Calculate the expected sum of squares of numbers from 1 to n
        // Formula: n * (n + 1) * (2n + 1) / 6
        // This is because the sum of the squares of the first n natural numbers is n * (n + 1) * (2n + 1) / 6.
        // We use this expected sum of squares to compare against the actual sum of squares.
        long expectedSumOfSquares = (long) n * (n + 1) * (2L * n + 1) / 6;

        // Initialize variables to compute the actual sum and sum of squares from the input array
        long actualSum = 0;
        long actualSumOfSquares = 0;

        // Iterate over the array to compute the actual sum and sum of squares
        // By iterating through the array, we accumulate the actual sum of elements and the sum of their squares.
        // These are then compared against the expected values to identify discrepancies.
        for (int num : nums) {
            actualSum += num;
            actualSumOfSquares += (long) num * num;
        }

        // x - y = actualSum - expectedSum
        // This difference represents (duplicate - missing) because the sum of the array will be higher by the duplicate value and lower by the missing value.
        // For example, if the array is [1, 2, 2, 4, 5], actualSum = 14 and expectedSum = 15
        // x - y = 14 - 15 = -1
        long XminusY = actualSum - expectedSum;

        // x^2 - y^2 = actualSumOfSquares - expectedSumOfSquares
        // This difference represents (duplicate^2 - missing^2). This is derived from the fact that the sum of squares in the array will be higher by the square of the duplicate value and lower by the square of the missing value.
        // For example, if the array is [1, 2, 2, 4, 5], actualSumOfSquares = 54 and expectedSumOfSquares = 55
        // x^2 - y^2 = 54 - 55 = -1
        long XsquareMinusYsquare = actualSumOfSquares - expectedSumOfSquares;

        // Compute the sum of the duplicate and missing number
        // x + y = (x^2 - y^2) / (x - y)
        // This formula comes from solving the equations:
        // x^2 - y^2 = (x - y) * (x + y) => x + y = (x^2 - y^2) / (x - y)
        // For example, if x - y = -1 and x^2 - y^2 = -1, then x + y = -1 / -1 = 1
        long XplusY = XsquareMinusYsquare / XminusY;

        // Compute the duplicate number (x) and the missing number (y)
        // Using the equations:
        // x - y = XminusY
        // x + y = XplusY
        // Solve these equations to find x and y:
        // x = (XminusY + XplusY) / 2
        // y = XplusY - x
        // For example, if XminusY = -1 and XplusY = 1:
        // x = (-1 + 1) / 2 = 0
        // y = 1 - 0 = 1
        long x = (XminusY + XplusY) / 2;
        long y = XplusY - x;

        // Return the result as an array where the first element is the duplicate number and the second element is the missing number
        // The result array contains the values we computed for x and y, which are the duplicate and missing numbers respectively.
        return new int[] { (int) x, (int) y };
    }

    public int numberOfInversions(int[] nums) {
        // We use merge sort because it efficiently sorts an array while also counting inversions.
        // An inversion is a pair of elements (i, j) such that i < j and nums[i] > nums[j].
        // Merge sort works well for this problem because, during the merge step,
        // we can count how many elements from the right half are smaller than an element from the left half.
        // This allows us to count all the inversions in O(n log n) time, which is much faster than the O(n^2)
        // time that a brute-force approach would take.
        // Example: If nums = [2, 3, 8, 6, 1], merge sort will sort the array and count inversions like:
        // (2, 1), (3, 1), (8, 6), (8, 1), (6, 1)
        return mergeSort(nums, 0, nums.length - 1);
    }

    // Merge sort function that returns the number of inversions.
    private int mergeSort(int[] nums, int low, int hi) {
        // Base case: If the subarray has less than or equal to 1 element, it's already sorted.
        // No inversions are possible in this case, so return 0.
        if (low >= hi) {
            return 0;
        }

        // Find the middle point to divide the array into two halves.
        // This is essential for the divide-and-conquer approach of merge sort.
        int mid = low + (hi - low) / 2;

        // Recursively sort and count inversions in the left half.
        int leftInversions = mergeSort(nums, low, mid);

        // Recursively sort and count inversions in the right half.
        int rightInversions = mergeSort(nums, mid + 1, hi);

        // Merge the two halves together and count the inversions that occur between the two halves.
        int mergeInversions = mergeArray(nums, low, mid, hi);

        // The total number of inversions is the sum of inversions in the left half, the right half,
        // and the inversions counted during the merge step.
        return leftInversions + rightInversions + mergeInversions;
    }

    // Merging function that counts inversions between the two halves.
    private int mergeArray(int[] nums, int low, int mid, int hi) {
        // Calculate the sizes of the two subarrays to be merged.
        int n1 = mid - low + 1; // Number of elements in the left half
        int n2 = hi - mid; // Number of elements in the right half

        // Temporary arrays to hold the left and right subarrays.
        int[] arr1 = new int[n1];
        int[] arr2 = new int[n2];

        // Copy the left half into arr1.
        for (int i = 0; i < n1; i++) {
            arr1[i] = nums[low + i];
        }

        // Copy the right half into arr2.
        for (int j = 0; j < n2; j++) {
            arr2[j] = nums[mid + 1 + j];
        }

        // Merging process: we will compare elements from arr1 and arr2 and
        // place the smaller element back into the original array (nums).
        int i = 0, j = 0, k = low;
        int inversions = 0;

        // Compare each element of the left half with the right half.
        // If an element in arr1 is smaller or equal to an element in arr2,
        // it means no inversion for that element.
        // Otherwise, if arr2[j] < arr1[i], then all remaining elements in arr1
        // (from i to n1-1) are greater than arr2[j], so count these as inversions.
        while (i < n1 && j < n2) {
            if (arr1[i] <= arr2[j]) {
                nums[k] = arr1[i];
                i++;
            } else {
                nums[k] = arr2[j];
                // All elements from arr1[i] to arr1[n1-1] are greater than arr2[j],
                // so they form inversions with arr2[j].
                inversions += (n1 - i);
                j++;
            }
            k++;
        }

        // Copy any remaining elements of arr1, if any.
        while (i < n1) {
            nums[k] = arr1[i];
            i++;
            k++;
        }

        // Copy any remaining elements of arr2, if any.
        while (j < n2) {
            nums[k] = arr2[j];
            j++;
            k++;
        }

        // Return the number of inversions counted during the merge step.
        return inversions;
    }


}