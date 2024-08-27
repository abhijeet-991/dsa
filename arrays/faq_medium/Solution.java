package arrays.faq_medium;

import java.util.*;

public class Solution {

    public int[] leaders(int[] nums) {
        // Create a stack to store leaders. We use a stack to keep track of leaders
        // because it allows us to efficiently retrieve them in reverse order.
        Stack<Integer> stack = new Stack<>();

        // Push the last element of the array onto the stack. The last element is always a leader
        // because there are no elements to its right.
        stack.push(nums[nums.length - 1]);

        // Start from the second last element and move backwards. We do this because we need to
        // compare each element with the elements that come after it (which we've already processed).
        int j = nums.length - 2;

        while (j >= 0) {
            // Check if the current element is greater than the top of the stack.
            // The top of the stack represents the greatest element we've seen so far from the right.
            // If the current element is greater, it means it's a new leader.
            if (nums[j] > stack.peek()) {
                // Push the new leader onto the stack. We do this because we need to keep track
                // of all leaders, and the stack helps us to do so efficiently.
                stack.push(nums[j]);
            }
            // Move to the previous element
            j--;
        }

        // Prepare a list to store the leaders in the order they appeared in the array. The stack
        // gives us leaders in reverse order, so we need a list to reverse them back.
        List<Integer> arrayList = new ArrayList<>();

        // Pop elements from the stack to get them in the correct order (from left to right as they appeared).
        // The stack is LIFO (Last In, First Out), so popping elements will give us the leaders in reverse.
        while (!stack.isEmpty()) {
            arrayList.add(stack.pop()); // Collect leaders into the list.
        }

        // Convert the list of leaders to an array of primitive ints.
        // This is required to match the return type of the method, which expects an int array.
        return arrayList.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] replaceElements(int[] arr) {
        // Handle the edge case where the array contains only one element.
        // For a single element, there are no elements to its right, so replace it with -1.
        if (arr.length == 1) {
            return new int[]{-1};
        }

        // Initialize temp with the last element of the array. This will help us track the maximum
        // element we've seen so far from the right side of the array.
        int temp = arr[arr.length - 1];

        // Traverse the array from the second-to-last element to the first element.
        // We move backwards because we need to compute the maximum of elements to the right of each element.
        for (int i = arr.length - 2; i >= 0; i--) {
            // Save the current element in backup. This is needed because we'll overwrite arr[i]
            // but need to keep the original value for future comparisons.
            int backup = arr[i];

            // Replace the current element with the maximum value of the elements to its right.
            // The maximum value to the right is tracked by `temp` (updated during traversal).
            arr[i] = Math.max(temp, arr[i + 1]);

            // Update `temp` to the maximum value seen so far. `backup` is the original value of arr[i],
            // so we compare it with `temp` to keep track of the highest value encountered from the right.
            temp = Math.max(temp, backup);
        }

        // After the loop, the last element of the array is always replaced with -1 because there are
        // no elements to its right.
        arr[arr.length - 1] = -1;

        // Return the modified array with elements replaced as required.
        return arr;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        // Initialize the boundary indices for the traversal
        int minRow = 0; // Starting row index for the topmost row.
        int minCol = 0; // Starting column index for the leftmost column.
        int maxRow = matrix.length - 1; // Ending row index for the bottommost row.
        int maxCol = matrix[0].length - 1; // Ending column index for the rightmost column.

        // List to store the elements in spiral order.
        List<Integer> arrayList = new ArrayList<>();

        // Total number of elements in the matrix, used to determine when to stop.
        int totalElements = matrix.length * matrix[0].length;
        int cnt = 0; // Counter to keep track of the number of elements processed.

        // Traverse the matrix in a spiral order until all elements are processed.
        while (cnt < totalElements) {
            // Traverse from left to right across the topmost row.
            for (int i = minCol; i <= maxCol && cnt < totalElements; i++) {
                arrayList.add(matrix[minRow][i]); // Add the element to the result list.
                cnt++; // Increment the counter.
            }
            minRow++; // Move down to the next row as the current top row is processed.

            // Traverse from top to bottom down the rightmost column.
            for (int i = minRow; i <= maxRow && cnt < totalElements; i++) {
                arrayList.add(matrix[i][maxCol]); // Add the element to the result list.
                cnt++; // Increment the counter.
            }
            maxCol--; // Move left to the next column as the current right column is processed.

            // Traverse from right to left across the bottommost row.
            for (int i = maxCol; i >= minCol && cnt < totalElements; i--) {
                arrayList.add(matrix[maxRow][i]); // Add the element to the result list.
                cnt++; // Increment the counter.
            }
            maxRow--; // Move up to the previous row as the current bottom row is processed.

            // Traverse from bottom to top up the leftmost column.
            for (int i = maxRow; i >= minRow && cnt < totalElements; i--) {
                arrayList.add(matrix[i][minCol]); // Add the element to the result list.
                cnt++; // Increment the counter.
            }
            minCol++; // Move right to the next column as the current left column is processed.
        }

        // Return the list of elements arranged in spiral order.
        return arrayList;
    }

    public int[] rearrangeArray(int[] nums) {
        int n = nums.length; // Length of the input array.

        // Initialize indices to keep track of where to place positive and negative numbers.
        int posIndex = 0; // Index for placing positive numbers in the result array.
        int negIndex = 1; // Index for placing negative numbers in the result array.

        // Create a result array to store the rearranged numbers.
        int[] result = new int[n];

        // Iterate through the input array to rearrange elements.
        for (int num : nums) {
            if (num > 0) {
                // If the number is positive, place it at the current position for positive numbers.
                result[posIndex] = num;
                // Move to the next position for positive numbers (every second position).
                posIndex += 2;
            } else {
                // If the number is negative, place it at the current position for negative numbers.
                result[negIndex] = num;
                // Move to the next position for negative numbers (every second position).
                negIndex += 2;
            }
        }

        // Return the rearranged array with positive and negative numbers alternating.
        return result;
    }

    public List<List<Integer>> generate(int numRows) {
        // List to store all rows of Pascal's Triangle.
        List<List<Integer>> triangle = new ArrayList<>();

        // Construct each row of Pascal's Triangle.
        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            // Create a new list to represent the current row.
            List<Integer> currentRow = new ArrayList<>();

            // The first element of every row is always 1.
            currentRow.add(1);

            // For rows greater than the first row, compute the inner elements.
            if (rowIndex > 0) {
                // Get the previous row to help compute the current row's values.
                List<Integer> previousRow = triangle.get(rowIndex - 1);

                // Compute values for the current row based on the previous row.
                for (int colIndex = 1; colIndex < rowIndex; colIndex++) {
                    // Get the elements from the previous row that are needed to compute the current value.
                    int leftParent = previousRow.get(colIndex - 1); // Element to the left in the previous row.
                    int rightParent = previousRow.get(colIndex); // Element directly above the current position.

                    // The value at the current position is the sum of the two elements directly above it.
                    int currentValue = leftParent + rightParent;

                    // Add the computed value to the current row.
                    currentRow.add(currentValue);
                }

                // The last element of every row is always 1.
                currentRow.add(1);
            }

            // Add the current row to the triangle.
            triangle.add(currentRow);
        }

        // Return the complete Pascal's Triangle.
        return triangle;
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length; // Size of the matrix (n x n). This gives us the number of rows and columns.

        // Transpose the matrix by swapping elements across the diagonal.
        for (int i = 0; i < n; i++) { // Loop over each row.
            for (int j = i + 1; j < n; j++) { // Loop over each column in the row, starting from i+1 to avoid redundant swaps.
                // Example: For a 3x3 matrix, this loop will swap elements across the diagonal (i.e., (0,1) with (1,0), (0,2) with (2,0), etc.).
                int temp = matrix[i][j]; // Save the current element (upper triangle of the matrix).
                matrix[i][j] = matrix[j][i]; // Swap element from [i][j] with [j][i] (lower triangle of the matrix).
                matrix[j][i] = temp; // Complete the swap to achieve transposition.
            }
        }

        // Reverse each row to achieve the final rotated matrix.
        for (int i = 0; i < n; i++) { // Loop over each row.
            for (int j = 0; j < n / 2; j++) { // Loop over half of the row (swap elements symmetrically around the center).
                // Example: For the row [1, 2, 3], this loop will swap (1 with 3) to get [3, 2, 1].
                int temp = matrix[i][j]; // Save the current element (left side of the row).
                matrix[i][j] = matrix[i][n - 1 - j]; // Swap element from the left half with the right half of the row.
                matrix[i][n - 1 - j] = temp; // Complete the swap to reverse the row.
            }
        }
    }

    public int[] twoSum(int[] nums, int target) {
        // Create a HashMap to store numbers and their corresponding indices.
        // This allows us to quickly check if a required complement exists.
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        // Initialize the result array with default values.
        // This will hold the indices of the two numbers that sum to the target.
        int[] ans = new int[]{-1, -1};

        // Traverse the array to find two numbers that add up to the target.
        for (int i = 0; i < nums.length; i++) { // Iterate over each number in the array.
            int complement = target - nums[i]; // Calculate the complement needed to reach the target.

            // Check if the complement (target - current number) is already in the map.
            // If complement is found, it means we have already seen the number that pairs with the current number to sum to the target.
            if (hashMap.get(complement) != null) {
                ans[0] = hashMap.get(complement); // Get the index of the previously seen number (the complement).
                ans[1] = i; // Set the current index as the second element of the result.
                break; // Exit the loop as the solution is found.
            } else {
                // If the complement is not found, store the current number and its index in the map.
                // This allows us to find the complement if it appears in future iterations.
                hashMap.put(nums[i], i);
            }
        }

        // Return the indices of the two numbers that add up to the target.
        return ans;
    }

    public List<List<Integer>> threeSumUsingHashSet(int[] nums) {
        // Create a HashSet to store unique triplets.
        // Using a set helps to avoid duplicate triplets.
        HashSet<List<Integer>> set = new HashSet<>();

        // Iterate through the array to find triplets.
        for (int i = 0; i < nums.length; i++) { // Iterate over each element as the first number of the triplet.
            // Create a HashSet to store numbers seen in the current iteration.
            // This helps in finding the required third number efficiently.
            HashSet<Integer> hashSet = new HashSet<>();

            // Iterate through the remaining elements in the array to find the second number of the triplet.
            for (int j = i + 1; j < nums.length; j++) {
                int potentialThirdNum = -(nums[i] + nums[j]); // Compute the required third number to form a triplet with nums[i] and nums[j].

                // Check if the computed third number is present in the hashSet.
                // If the third number exists, a valid triplet has been found.
                if (hashSet.contains(potentialThirdNum)) {
                    // Create a list to store the current triplet.
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]); // Add the first number of the triplet.
                    list.add(nums[j]); // Add the second number of the triplet.
                    list.add(potentialThirdNum); // Add the third number of the triplet.
                    set.add(list); // Add the triplet to the set.
                }
                // Add the current number to the hashSet for future complement checks.
                hashSet.add(nums[j]);
            }
        }

        // Convert the set to a list and return.
        // The set ensures that all triplets are unique.
        return new ArrayList<>(set);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        // Sort the array to simplify finding triplets.
        // Sorting helps in easily skipping duplicates and using the two-pointer technique.
        Arrays.sort(nums);

        // Initialize a list to store unique triplets.
        // This will hold all the triplets that sum to zero.
        List<List<Integer>> ans = new ArrayList<>();

        // Iterate through the sorted array to find triplets.
        for (int firstNumberIndex = 0; firstNumberIndex < nums.length; firstNumberIndex++) {
            // Skip duplicate values for the first number.
            // This ensures that we don't consider the same number multiple times as the starting number.
            if (firstNumberIndex > 0 && nums[firstNumberIndex] == nums[firstNumberIndex - 1]) {
                continue;
            }

            int secondNumberIndex = firstNumberIndex + 1; // Starting index for the second number.
            int thirdNumberIndex = nums.length - 1; // Starting index for the third number.

            // Use two-pointer approach to find the second and third numbers.
            while (secondNumberIndex < thirdNumberIndex) {
                int sum = nums[firstNumberIndex] + nums[secondNumberIndex] + nums[thirdNumberIndex]; // Calculate the sum of the triplet.

                if (sum == 0) {
                    // If the sum is zero, we found a valid triplet.
                    ans.add(Arrays.asList(nums[firstNumberIndex], nums[secondNumberIndex], nums[thirdNumberIndex])); // Add the triplet to the result list.

                    // Skip duplicate values for the second number.
                    // Move to the next distinct number to avoid duplicate triplets.
                    while (secondNumberIndex < thirdNumberIndex && nums[secondNumberIndex] == nums[secondNumberIndex + 1]) {
                        secondNumberIndex++;
                    }
                    // Skip duplicate values for the third number.
                    // Move to the next distinct number to avoid duplicate triplets.
                    while (secondNumberIndex < thirdNumberIndex && nums[thirdNumberIndex] == nums[thirdNumberIndex - 1]) {
                        thirdNumberIndex--;
                    }

                    // Move to the next distinct second and third numbers for further potential triplets.
                    secondNumberIndex++;
                    thirdNumberIndex--;
                } else if (sum > 0) {
                    // If the sum is greater than zero, we need a smaller sum.
                    // Move the third number left to decrease the sum.
                    thirdNumberIndex--;
                } else {
                    // If the sum is less than zero, we need a larger sum.
                    // Move the second number right to increase the sum.
                    secondNumberIndex++;
                }
            }
        }

        // Return the list of unique triplets that sum to zero.
        return ans;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        // Initialize a list to store unique quadruplets.
        // This will hold all quadruplets that sum up to the target.
        List<List<Integer>> ansList = new ArrayList<>();

        // If there are fewer than 4 numbers, it's impossible to find a quadruplet.
        if (nums.length < 4) {
            return new ArrayList<>();
        }

        // Sort the array to simplify finding quadruplets and handle duplicates.
        Arrays.sort(nums);

        // Iterate through the array to find the first number of the quadruplet.
        for (int i = 0; i < nums.length - 3; i++) {
            // Skip duplicate values for the first number to avoid considering the same quadruplet multiple times.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Iterate through the array to find the second number of the quadruplet.
            for (int j = i + 1; j < nums.length - 2; j++) {
                // Skip duplicate values for the second number to avoid redundant quadruplets.
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // Initialize two pointers to find the third and fourth numbers.
                int k = j + 1; // Pointer for the third number starts right after the second number.
                int l = nums.length - 1; // Pointer for the fourth number starts at the end of the array.

                // Use two-pointer approach to find the third and fourth numbers.
                while (k < l) {
                    // Calculate the sum of the current quadruplet.
                    long sum = (long) nums[i] + (long) nums[j] + (long) nums[k] + (long) nums[l];

                    if (sum == target) {
                        // If the sum equals the target, add the quadruplet to the result list.
                        ansList.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));

                        // Skip duplicate values for the third number.
                        while (k < l && nums[k] == nums[k + 1]) k++;
                        // Skip duplicate values for the fourth number.
                        while (k < l && nums[l] == nums[l - 1]) l--;

                        // Move to the next distinct third and fourth numbers.
                        k++;
                        l--;
                    } else if (sum > target) {
                        // If the sum is greater than the target, move the fourth number left to reduce the sum.
                        l--;
                    } else {
                        // If the sum is less than the target, move the third number right to increase the sum.
                        k++;
                    }
                }
            }
        }

        // Return the list of unique quadruplets that sum up to the target.
        return ansList;
    }

    public void sortColors(int[] nums) {
        int i = 0, j = 0, k = nums.length - 1; // Initialize pointers for 0, 1, and 2.

        // Dutch National Flag Algorithm to sort array containing 0s, 1s, and 2s.
        while (i <= k) { // Iterate while the current index is less than or equal to the last index for 2s.
            if (nums[i] == 1) {
                // If the current element is 1, it's already in the correct section.
                i++; // Move to the next element.
            } else if (nums[i] == 2) {
                // If the current element is 2, it should be at the end of the array.
                swap(nums, i, k); // Swap 2 with the element at position k.
                k--; // Decrease the pointer for 2s.
                // Note: We do not increment `i` here because we need to check the element swapped from the end.
            } else {
                // If the current element is 0, it should be at the beginning of the array.
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
        int ans = Integer.MIN_VALUE; // Initialize to the smallest possible integer to handle all cases, including when all numbers are negative.
        int currentBestSum = 0; // Track the sum of the current subarray being evaluated.

        // Iterate through the array to find the maximum sum of any subarray.
        for (int num : nums) {
            // Decide whether to start a new subarray with the current element or extend the existing subarray.
            // currentBestSum + num represents extending the current subarray with the new element.
            // num represents starting a new subarray with the current element.
            currentBestSum = Math.max(currentBestSum + num, num);

            // Update the answer with the maximum subarray sum found so far.
            // Compare the current best subarray sum with the overall maximum found so far.
            ans = Math.max(currentBestSum, ans);
        }

        return ans; // Return the maximum subarray sum found.
    }
}