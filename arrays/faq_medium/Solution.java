package arrays.faq_medium;

import java.util.*;

public class Solution {

    public int[] leaders(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        stack.push(nums[nums.length - 1]); // The last element is always a leader, push it to the stack.
        int j = nums.length - 1;

        // Traverse the array from end to start.
        while (j > -1) {
            // Check if the current element is greater than the top of the stack.
            if (nums[j] > stack.peek()) {
                stack.push(nums[j]); // New leader found, push it to the stack.
            }
            j--;
        }

        // Prepare the result list by popping elements from the stack.
        List<Integer> arrayList = new ArrayList<>();
        while (!stack.isEmpty()) {
            arrayList.add(stack.pop()); // Add leaders to list in reverse order (stack is LIFO).
        }

        // Convert the list of integers to an array of primitive ints.
        return arrayList.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] replaceElements(int[] arr) {
        if (arr.length == 1) {
            return new int[]{-1}; // If there's only one element, replace it with -1.
        }

        int temp = arr[arr.length - 1]; // Initialize temp with the last element of the array.
        // Traverse the array from the second-to-last element to the first element.
        for (int i = arr.length - 2; i >= 0; i--) {
            int backup = arr[i]; // Save the current element.
            arr[i] = Math.max(temp, arr[i + 1]); // Replace current element with the maximum value of elements to its right.
            temp = Math.max(temp, backup); // Update temp to the maximum value seen so far.
        }
        arr[arr.length - 1] = -1; // Last element is always replaced with -1.

        return arr; // Return the modified array.
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int minRow = 0; // Starting row index.
        int minCol = 0; // Starting column index.
        int maxRow = matrix.length - 1; // Ending row index.
        int maxCol = matrix[0].length - 1; // Ending column index.

        List<Integer> arrayList = new ArrayList<>(); // List to store elements in spiral order.

        int totalElements = matrix.length * matrix[0].length; // Total number of elements in the matrix.
        int cnt = 0; // Counter for the number of elements processed.

        // Traverse the matrix in spiral order.
        while (cnt < totalElements) {
            // Traverse from left to right across the topmost row.
            for (int i = minCol; i <= maxCol && cnt < totalElements; i++) {
                arrayList.add(matrix[minRow][i]); // Add element to result list.
                cnt++;
            }
            minRow++; // Move down to the next row.

            // Traverse from top to bottom down the rightmost column.
            for (int i = minRow; i <= maxRow && cnt < totalElements; i++) {
                arrayList.add(matrix[i][maxCol]); // Add element to result list.
                cnt++;
            }
            maxCol--; // Move left to the next column.

            // Traverse from right to left across the bottommost row.
            for (int i = maxCol; i >= minCol && cnt < totalElements; i--) {
                arrayList.add(matrix[maxRow][i]); // Add element to result list.
                cnt++;
            }
            maxRow--; // Move up to the previous row.

            // Traverse from bottom to top up the leftmost column.
            for (int i = maxRow; i >= minRow && cnt < totalElements; i--) {
                arrayList.add(matrix[i][minCol]); // Add element to result list.
                cnt++;
            }
            minCol++; // Move right to the next column.
        }

        return arrayList; // Return the list of elements in spiral order.
    }

    public int[] rearrangeArray(int[] nums) {
        int n = nums.length; // Length of the input array.
        int posIndex = 0; // Index for placing positive numbers.
        int negIndex = 1; // Index for placing negative numbers.

        int[] result = new int[n]; // Array to store rearranged result.

        // Iterate through input array to rearrange elements.
        for (int num : nums) {
            if (num > 0) {
                result[posIndex] = num; // Place positive number at the correct position.
                posIndex += 2; // Move to the next position for positive numbers.
            } else {
                result[negIndex] = num; // Place negative number at the correct position.
                negIndex += 2; // Move to the next position for negative numbers.
            }
        }

        return result; // Return the rearranged array.
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>(); // List to store rows of Pascal's Triangle.

        // Construct each row of Pascal's Triangle.
        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            List<Integer> currentRow = new ArrayList<>(); // Create a new row.

            currentRow.add(1); // First element of every row is 1.

            // For rows greater than the first, compute the inner elements.
            if (rowIndex > 0) {
                List<Integer> previousRow = triangle.get(rowIndex - 1); // Get the previous row.
                // Compute values for the current row based on the previous row.
                for (int colIndex = 1; colIndex < rowIndex; colIndex++) {
                    int leftParent = previousRow.get(colIndex - 1); // Element to the left in the previous row.
                    int rightParent = previousRow.get(colIndex); // Element to the right in the previous row.
                    int currentValue = leftParent + rightParent; // Compute the value for the current position.
                    currentRow.add(currentValue); // Add computed value to the current row.
                }

                currentRow.add(1); // Last element of every row is 1.
            }

            triangle.add(currentRow); // Add the current row to the triangle.
        }

        return triangle; // Return the complete Pascal's Triangle.
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length; // Size of the matrix (n x n).

        // Transpose the matrix by swapping elements across the diagonal.
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j]; // Save the current element.
                matrix[i][j] = matrix[j][i]; // Swap element from [i][j] with [j][i].
                matrix[j][i] = temp; // Complete the swap.
            }
        }

        // Reverse each row to achieve the final rotated matrix.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j]; // Save the current element.
                matrix[i][j] = matrix[i][n - 1 - j]; // Swap element from the left half with the right half.
                matrix[i][n - 1 - j] = temp; // Complete the swap.
            }
        }
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>(); // Map to store numbers and their indices.
        int[] ans = new int[]{-1, -1}; // Initialize result array with default values.

        // Traverse the array to find two numbers that add up to the target.
        for (int i = 0; i < nums.length; i++) {
            // Check if the complement (target - current number) is already in the map.
            if (hashMap.get(target - nums[i]) != null) {
                ans[0] = hashMap.get(target - nums[i]); // Get the index of the complement.
                ans[1] = i; // Set the current index as the second element of the result.
                break; // Exit the loop as the solution is found.
            } else {
                hashMap.put(nums[i], i); // Store the current number and its index in the map.
            }
        }

        return ans; // Return the indices of the two numbers.
    }

    public List<List<Integer>> threeSumUsingHashSet(int[] nums) {
        HashSet<List<Integer>> set = new HashSet<>(); // Set to store unique triplets.

        // Iterate through the array to find triplets.
        for (int i = 0; i < nums.length; i++) {
            HashSet<Integer> hashSet = new HashSet<>(); // Set to store numbers seen in the current iteration.
            for (int j = i + 1; j < nums.length; j++) {
                int potentialThirdNum = -(nums[i] + nums[j]); // Compute the required third number to form a triplet.

                // Check if the computed third number is present in the hashSet.
                if (hashSet.contains(potentialThirdNum)) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]); // Add the first number of the triplet.
                    list.add(nums[j]); // Add the second number of the triplet.
                    list.add(potentialThirdNum); // Add the third number of the triplet.
                    set.add(list); // Add the triplet to the set.
                }
                hashSet.add(nums[j]); // Add the current number to the hashSet.
            }
        }

        return new ArrayList<>(set); // Convert the set to a list and return.
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // Sort the array to simplify finding triplets.
        List<List<Integer>> ans = new ArrayList<>(); // List to store unique triplets.

        // Iterate through the sorted array to find triplets.
        for (int firstNumberIndex = 0; firstNumberIndex < nums.length; firstNumberIndex++) {
            // Skip duplicate values for the first number.
            if (firstNumberIndex > 0 && nums[firstNumberIndex] == nums[firstNumberIndex - 1]) {
                continue;
            }

            int secondNumberIndex = firstNumberIndex + 1; // Starting index for the second number.
            int thirdNumberIndex = nums.length - 1; // Starting index for the third number.

            // Use two-pointer approach to find the second and third numbers.
            while (secondNumberIndex < thirdNumberIndex) {
                int sum = nums[firstNumberIndex] + nums[secondNumberIndex] + nums[thirdNumberIndex]; // Calculate the sum of the triplet.

                if (sum == 0) {
                    ans.add(Arrays.asList(nums[firstNumberIndex], nums[secondNumberIndex], nums[thirdNumberIndex])); // Add valid triplet to the result list.

                    // Skip duplicate values for the second number.
                    while (secondNumberIndex < thirdNumberIndex && nums[secondNumberIndex] == nums[secondNumberIndex + 1]) {
                        secondNumberIndex++;
                    }
                    // Skip duplicate values for the third number.
                    while (secondNumberIndex < thirdNumberIndex && nums[thirdNumberIndex] == nums[thirdNumberIndex - 1]) {
                        thirdNumberIndex--;
                    }

                    secondNumberIndex++; // Move to the next distinct second number.
                    thirdNumberIndex--; // Move to the next distinct third number.
                } else if (sum > 0) {
                    thirdNumberIndex--; // Reduce sum by moving the third number left.
                } else {
                    secondNumberIndex++; // Increase sum by moving the second number right.
                }
            }
        }

        return ans; // Return the list of unique triplets.
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ansList = new ArrayList<>(); // List to store unique quadruplets.
        if (nums.length < 4) {
            return new ArrayList<>(); // Return an empty list if there are fewer than 4 numbers.
        }
        Arrays.sort(nums); // Sort the array to simplify finding quadruplets.

        // Iterate through the array to find quadruplets.
        for (int i = 0; i < nums.length - 3; i++) {
            // Skip duplicate values for the first number.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < nums.length - 2; j++) {
                // Skip duplicate values for the second number.
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int k = j + 1; // Starting index for the third number.
                int l = nums.length - 1; // Starting index for the fourth number.

                // Use two-pointer approach to find the third and fourth numbers.
                while (k < l) {
                    long sum = (long) nums[i] + (long) nums[j] + (long) nums[k] + (long) nums[l]; // Calculate the sum of the quadruplet.

                    if (sum == target) {
                        ansList.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l])); // Add valid quadruplet to the result list.

                        // Skip duplicate values for the third number.
                        while (k < l && nums[k] == nums[k + 1]) k++;
                        // Skip duplicate values for the fourth number.
                        while (k < l && nums[l] == nums[l - 1]) l--;

                        k++; // Move to the next distinct third number.
                        l--; // Move to the next distinct fourth number.
                    } else if (sum > target) {
                        l--; // Reduce sum by moving the fourth number left.
                    } else {
                        k++; // Increase sum by moving the third number right.
                    }
                }
            }
        }

        return ansList; // Return the list of unique quadruplets.
    }

    public void sortZeroOneTwo(int[] nums) {
        int i = 0, j = 0, k = nums.length - 1; // Initialize pointers.

        // Dutch National Flag Algorithm to sort array containing 0s, 1s, and 2s.
        while (i < k) {
            if (nums[i] == 1) {
                i++; // Move to the next element if it's 1.
            } else if (nums[i] == 2) {
                swap(nums, i, k); // Swap 2 with the element at position k.
                k--; // Decrease the pointer for 2s.
            } else {
                swap(nums, i, j); // Swap 0 with the element at position j.
                i++; // Move to the next element.
                j++; // Increase the pointer for 0s.
            }
        }

        System.out.println(Arrays.toString(nums)); // Print the sorted array.
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i]; // Save element at index i.
        nums[i] = nums[j]; // Swap element at index i with element at index j.
        nums[j] = temp; // Complete the swap.
    }

    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE; // Initialize to the smallest possible integer.
        int currentBestSum = 0; // Track the current sum of the subarray.

        // Iterate through the array to find the maximum sum of any subarray.
        for (int num : nums) {
            // Update currentBestSum: either start a new subarray or extend the existing one.
            currentBestSum = Math.max(currentBestSum + num, num);
            // Update the answer with the maximum subarray sum found so far.
            ans = Math.max(currentBestSum, ans);
        }

        return ans; // Return the maximum subarray sum.
    }
}