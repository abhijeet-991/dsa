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

    public int reversePairs(int[] nums) {
        // Start the merge sort process and count reverse pairs over the entire range of the array
        // Example: For array [1, 3, 2, 3, 1], this call will handle the range from index 0 to 4
        return mergeSortHelper(nums, 0, nums.length - 1);
    }

    // Recursive method to sort and count reverse pairs in the range [low, hi]
    private int mergeSortHelper(int[] nums, int low, int hi) {
        int cnt = 0; // Initialize count of reverse pairs in this range

        // Base case: If the range is invalid or has only one element, there are no reverse pairs
        // Example: For range [0, 0], no pairs can be counted
        if (low >= hi) {
            return 0;
        }

        // Calculate the midpoint to divide the array into two halves
        // Example: For range [0, 4], mid is calculated as (0 + 4) / 2 = 2
        int mid = low + (hi - low) / 2;

        // Recursively sort the left half of the array and count reverse pairs within that half
        // Example: For range [0, 2] in array [1, 3, 2, 3, 1], this will sort [1, 3, 2] and count pairs in that subarray
        cnt += mergeSortHelper(nums, low, mid);

        // Recursively sort the right half of the array and count reverse pairs within that half
        // Example: For range [3, 4] in array [1, 3, 2, 3, 1], this will sort [3, 1] and count pairs in that subarray
        cnt += mergeSortHelper(nums, mid + 1, hi);

        // Count reverse pairs where one element is in the left half and the other is in the right half
        // Example: For left half [1, 3] and right half [2, 3, 1], this will count pairs such as (3, 1)
        cnt += countPairHelper(nums, low, mid, hi);

        // Merge the two sorted halves into a single sorted array
        // Example: Merge sorted [1, 2, 3] and [1, 3] into [1, 1, 2, 3, 3]
        mergeArrayHelper(nums, low, mid, hi);

        // Return the total count of reverse pairs found in the range [low, hi]
        return cnt;
    }

    // Helper method to count reverse pairs between two sorted halves
    private int countPairHelper(int[] nums, int low, int mid, int hi) {
        int j = mid + 1; // Pointer to start scanning the right half of the array
        int cnt = 0;    // Initialize count of reverse pairs

        // Traverse each element in the left half of the array
        // Example: For left half [1, 2, 3] and right half [1, 2, 3], i will be 0, 1, 2
        for (int i = low; i <= mid; i++) {
            // Move pointer `j` in the right half until nums[i] <= 2 * nums[j]
            // Example: For nums[i] = 3 and nums[j] = 1, move `j` to find nums[j] such that 3 <= 2 * nums[j]
            while (j <= hi && nums[i] > 2 * nums[j]) {
                j++; // Increment `j` to find valid pairs
            }
            // The number of valid reverse pairs for nums[i] is the number of elements from mid+1 to j-1
            // because all these elements in the right half are less than nums[i] / 2
            // Example: For nums[i] = 3 and `j` points to 2, count all elements from nums[mid+1] to nums[j-1]
            cnt += (j - (mid + 1));
        }

        // Return the count of reverse pairs found in this range
        return cnt;
    }

    // Helper method to merge two sorted halves of the array into a single sorted array
    private static void mergeArrayHelper(int[] nums, int left, int mid, int right) {
        int n1 = mid - left + 1; // Length of the left half
        int n2 = right - mid;    // Length of the right half

        // Create temporary arrays to hold elements of the left and right halves
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copy elements from the original array into the left temporary array
        System.arraycopy(nums, left, leftArray, 0, n1);
        // Copy elements from the original array into the right temporary array
        System.arraycopy(nums, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left; // Pointers for leftArray, rightArray, and nums respectively

        // Merge the two halves into the original array
        // Compare elements from leftArray and rightArray and copy the smaller one to nums
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                nums[k++] = leftArray[i++]; // Copy smaller element from leftArray to nums
            } else {
                nums[k++] = rightArray[j++]; // Copy smaller element from rightArray to nums
            }
        }

        // Copy any remaining elements from leftArray into nums
        // Example: If leftArray is [2, 3] and rightArray is exhausted, add [2, 3] to nums
        while (i < n1) {
            nums[k++] = leftArray[i++];
        }

        // Copy any remaining elements from rightArray into nums
        // Example: If rightArray is [4, 5] and leftArray is exhausted, add [4, 5] to nums
        while (j < n2) {
            nums[k++] = rightArray[j++];
        }
    }

    public int maxProduct(int[] nums) {
        // If the array has only one element, the maximum product is the element itself.
        // Example: nums = [-2], then max product = -2
        if (nums.length == 1) {
            return nums[0];
        }

        // Initialize prefix and suffix to 1. These will track the product of elements
        // from the start and the end of the array, respectively.
        int prefix = 1;
        int suffix = 1;

        // Initialize the answer (maximum product found so far) to 0.
        // It will store the maximum product found during the iteration.
        int ans = 0;

        // Iterate through the array to calculate prefix and suffix products.
        // We'll check both the forward and backward products simultaneously.
        for (int i = 0 ; i < nums.length; i++) {
            // If the prefix product becomes zero, reset it to 1.
            // This is because multiplying by zero will always give zero,
            // so we need to reset to avoid continuing with an invalid product.
            if (prefix == 0) prefix = 1;

            // Similarly, reset the suffix if it becomes zero for the same reason.
            if (suffix == 0) suffix = 1;

            // Multiply the prefix with the current element from the front.
            // Example: nums = [2, -3, 4], i = 0, prefix = 1 * 2 = 2
            // i = 1, prefix = 2 * -3 = -6, i = 2, prefix = -6 * 4 = -24
            prefix = prefix * nums[i];

            // Multiply the suffix with the current element from the end.
            // Example: nums = [2, -3, 4], i = 0, suffix = 1 * 4 = 4
            // i = 1, suffix = 4 * -3 = -12, i = 2, suffix = -12 * 2 = -24
            suffix = suffix * nums[nums.length-i-1];

            // The maximum product at any point could be either the current prefix,
            // the current suffix, or the previously recorded maximum product.
            // Example: nums = [2, -3, 4], ans = max(0, max(2, 4)) = 4
            // ans = max(4, max(-6, -12)) = 4
            ans = Math.max(ans, Math.max(prefix, suffix));
        }

        // Return the maximum product found during the iteration.
        return ans;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Pointer to the end of the valid portion of nums1
        int i = m - 1;
        // Pointer to the end of nums2
        int j = n - 1;
        // Pointer to the end of the entire nums1 array (including extra space)
        int k = m + n - 1;

        // We will merge nums2 into nums1 from the end towards the beginning.
        // This avoids overwriting any elements in nums1 before we have a chance to move them.
        while (i >= 0 && j >= 0) {
            // Compare elements from nums1 and nums2
            if (nums1[i] > nums2[j]) {
                // If the current element in nums1 is greater, place it at the end of nums1
                nums1[k--] = nums1[i--];
            } else {
                // If the current element in nums2 is greater or equal, place it at the end of nums1
                nums1[k--] = nums2[j--];
            }
        }

        // If there are remaining elements in nums2 that haven't been merged yet
        // We only need to copy remaining elements from nums2 because if nums1 still has elements,
        // they are already in their correct place.
        while (j >= 0) {
            // Copy the remaining elements from nums2 into nums1
            nums1[k--] = nums2[j--];
        }

        // Remaining elements in nums1 (if any) are already in place and sorted.
        // No additional action is needed for them.

        // Example walkthrough:
        // Given nums1 = [1, 3, 5, 0, 0, 0], m = 3, nums2 = [2, 4, 6], n = 3
        // Initial pointers: i = 2, j = 2, k = 5
        // After merging:
        // 1. Compare nums1[2] (5) with nums2[2] (6). Since 6 > 5, place 6 in nums1[5].
        // 2. Compare nums1[2] (5) with nums2[1] (4). Since 5 > 4, place 5 in nums1[4].
        // 3. Compare nums1[1] (3) with nums2[1] (4). Since 4 > 3, place 4 in nums1[3].
        // 4. Compare nums1[1] (3) with nums2[0] (2). Since 3 > 2, place 3 in nums1[2].
        // 5. Copy remaining element 2 from nums2 to nums1[1].
        // Final nums1: [1, 2, 3, 4, 5, 6]

        // Why this method works efficiently:
        // 1. **Avoid Overwriting**: By merging from the end of nums1, we ensure that we do not overwrite elements
        //    in nums1 that we still need to consider.
        // 2. **Optimal Performance**: This approach runs in O(m + n) time and uses O(1) extra space,
        //    making it efficient for merging sorted arrays in-place.
        // 3. **Handling Remaining Elements**: After the initial merge loop, if there are any remaining
        //    elements in nums2, they are copied directly because nums1’s remaining elements are already in place.
    }
}