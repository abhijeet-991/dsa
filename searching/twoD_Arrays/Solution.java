package searching.twoD_Arrays;

public class Solution {

    // in a binary matrix (each row is sorted in non-decreasing order).
    public int rowWithMax1s(int[][] mat) {
        // Get the number of rows (n) and columns (m) in the matrix.
        int n = mat.length;
        int m = mat[0].length;

        // Initialize the index of the row with the maximum number of 1s as -1.
        // This is returned if no row contains any 1s.
        int rowIndex = -1;

        // Variable to store the maximum count of 1s found in any row so far.
        int maxCount = 0;

        // Start from the last column (rightmost column).
        // This is because the matrix rows are sorted, so the 1s will be grouped to the right.
        int j = m - 1;

        // Iterate through each row of the matrix.
        for (int i = 0; i < n; i++) {
            // Move left as long as the current element is 1 and we haven't gone out of bounds.
            // By moving left, we are counting how many 1s are in the current row.
            while (j >= 0 && mat[i][j] == 1) {
                // Move left by decrementing the column index `j`.
                j--;

                // Calculate how many 1s are in this row.
                // The number of 1s is the distance from the current position of `j`
                // to the end of the row. `m - j - 1` gives us the count of 1s.
                int countOnes = m - j - 1;

                // If the current row has more 1s than the previous maximum, update maxCount
                // and store the index of the current row (`i`).
                if (countOnes > maxCount) {
                    maxCount = countOnes;
                    rowIndex = i;  // Update the rowIndex to the current row.
                }
            }

            // Example intuition:
            // Suppose matrix = [[0, 1, 1], [1, 1, 1], [0, 0, 1]]:
            // Start with i = 0, j = 2. For row 0, we encounter a 1 at position (0, 2), so move left.
            // Count the number of 1s in row 0 (there are 2). Update maxCount to 2 and rowIndex to 0.
            // Move to row 1 (i = 1), where we start from j = 1 (since we already moved j).
            // Here, we encounter more 1s in row 1 and update accordingly.
        }

        // Return the index of the row with the maximum number of 1s.
        // If no row contains 1s, rowIndex remains -1.
        return rowIndex;
    }

    // Function to search for a target value in a matrix where each row is sorted.
    // The approach is to first locate the row that could contain the target
    // and then perform a binary search within that row.
    public boolean searchMatrixBetter(int[][] matrix, int target) {
        // Get the number of rows (n) and columns (m) in the matrix.
        int n = matrix.length;
        int m = matrix[0].length;

        // Iterate over each row of the matrix to find the potential row
        // that could contain the target.
        for (int i = 0; i < n; i++) {
            // Check if the target could be in the current row.
            // If the target is greater than or equal to the first element
            // and less than or equal to the last element of this row,
            // the target may lie in this row because the row is sorted.
            if (matrix[i][0] <= target && matrix[i][m - 1] >= target) {

                // Perform binary search in the current row to find the target.
                int low = 0;
                int hi = m - 1;

                // Binary search loop.
                while (low <= hi) {
                    // Calculate the middle index of the row.
                    int mid = low + (hi - low) / 2;

                    // If the element at mid index matches the target, return true.
                    if (matrix[i][mid] == target) {
                        return true;
                    }
                    // If the element at mid is less than the target,
                    // move the search window to the right half by adjusting `low`.
                    else if (matrix[i][mid] < target) {
                        low = mid + 1;
                    }
                    // If the element at mid is greater than the target,
                    // move the search window to the left half by adjusting `hi`.
                    else {
                        hi = mid - 1;
                    }
                }

                // If the binary search in this row did not find the target,
                // we return false since the row was the only potential candidate.
                return false;
            }
        }

        // If no row is found where the target can exist, return false.
        return false;
    }

    // Function to search for a target in a 2D matrix that is sorted row-wise
    // and where the first element of each row is greater than the last element of the previous row.
    // This structure allows us to treat the matrix as a "flattened" 1D array for binary search.
    public boolean searchMatrix(int[][] matrix, int target) {

        // First, handle edge cases. If the matrix is null, has no rows,
        // or has no columns, return false immediately, as the target cannot exist in an empty matrix.
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        // Get the number of rows (n) and columns (m) in the matrix.
        int n = matrix.length;
        int m = matrix[0].length;

        // Initialize the pointers for the binary search over the conceptual 1D array.
        // The left pointer starts at 0 (the first element in the conceptual array),
        // and the right pointer starts at (n * m - 1) (the last element in the conceptual array).
        int left = 0;
        int right = n * m - 1;

        // Perform binary search in the conceptual 1D array formed by the matrix.
        while (left <= right) {
            // Calculate the middle index in the flattened 1D array.
            // `left + (right - left) / 2` is used instead of `(left + right) / 2`
            // to avoid potential overflow when dealing with large numbers.
            int mid = left + (right - left) / 2;

            // Convert the middle index from the 1D conceptual array back to the 2D matrix.
            // We use `mid / m` to get the row index and `mid % m` to get the column index.
            // For example, if mid is 4 in a 3x3 matrix:
            // mid / 3 gives us row 1, mid % 3 gives us column 1 (i.e., matrix[1][1]).
            int midValue = matrix[mid / m][mid % m];

            // Check if the middle element in the matrix is equal to the target.
            // If it is, we've found the target, so return true.
            if (midValue == target) {
                return true;
            }
            // If the middle value is less than the target,
            // this means the target must be in the right half of the search space.
            // So, we discard the left half by moving the left pointer to `mid + 1`.
            else if (midValue < target) {
                left = mid + 1;
            }
            // If the middle value is greater than the target,
            // the target must be in the left half of the search space.
            // Hence, we discard the right half by moving the right pointer to `mid - 1`.
            else {
                right = mid - 1;
            }
        }

        // If we exit the while loop without having found the target,
        // it means the target is not present in the matrix, so we return false.
        return false;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        int n = matrix.length; // Number of rows in the matrix
        int m = matrix[0].length; // Number of columns in the matrix

        int j = m - 1; // Start searching from the last column in the first row (top-right corner of the matrix)

        // Loop through each row of the matrix
        for (int i = 0; i < n; i++) {
            // While we're within valid column range and the current element is greater than or equal to the target,
            // check if it's the target and adjust the column index
            while (j >= 0 && matrix[i][j] >= target) {
                // If the element at matrix[i][j] is equal to the target, we return true (target found)
                if (matrix[i][j] == target) {
                    return true;
                }
                // If the element is greater than the target, decrement the column index to move left
                j--;
            }
            // Example:
            // matrix = [[1, 3, 5],
            //           [6, 7, 10],
            //           [11, 16, 20]]
            // target = 7
            // Start from matrix[0][2] = 5 (j=2). 5 is less than 7, so we move to the next row.
            // Now at row 1, start from matrix[1][2] = 10. Since 10 > 7, move left.
            // Now at matrix[1][1] = 7, we find the target and return true.
        }

        // If we exit the loop without finding the target, return false
        return false;
    }

    public int[] findPeakGrid(int[][] arr) {
        int n = arr.length;  // Get the number of rows in the grid
        int m = arr[0].length;  // Get the number of columns in the grid

        int low = 0;  // Initialize the binary search to start from the first column
        int high = m - 1;  // The last column will be the upper limit of the binary search

        // We are performing binary search on the columns of the 2D matrix.
        while (low <= high) {
            int mid = (low + high) / 2;  // Find the mid column index for binary search

            // We need to find the row that contains the maximum element in the current mid column.
            int row = maxElement(arr, mid);

            // Check the elements to the left and right of the current mid element.
            // These checks ensure that we're considering the possibility of a peak being next to mid.
            int left = mid - 1 >= 0 ? arr[row][mid - 1] : Integer.MIN_VALUE;  // If mid-1 is within bounds, compare with left element.
            int right = mid + 1 < m ? arr[row][mid + 1] : Integer.MIN_VALUE;  // If mid+1 is within bounds, compare with right element.

            // If the current mid element is greater than both its left and right neighbors, it is a peak.
            if (arr[row][mid] > left && arr[row][mid] > right) {
                return new int[]{row, mid};  // Return the row and column of the peak element.
            }
            // If the left element is greater than the current mid element, move the search space to the left.
            else if (left > arr[row][mid]) {
                high = mid - 1;  // Shift the binary search to the left side of the mid column.
            }
            // Otherwise, move the search space to the right.
            else {
                low = mid + 1;  // Shift the binary search to the right side of the mid column.
            }
        }

        // If no peak is found, return [-1, -1]. This is just a safeguard, though the problem guarantees there will be a peak.
        return new int[]{-1, -1};
    }

    /*
    Helper function to find the row index that contains the maximum element in the given column.
    We iterate through each row for that column and find the largest element.
    This function helps us focus our search for the peak in the most "promising" row.
    */
    private int maxElement(int[][] arr, int col) {
        int n = arr.length;  // Get the number of rows in the grid
        int max = Integer.MIN_VALUE;  // Initialize max to a very small value
        int index = -1;  // Variable to store the row index with the maximum element in the column

        // Example: Let's say we have a grid:
        // arr = [
        //  [10, 20, 15],
        //  [21, 30, 14],
        //  [7,  16, 32]
        // ]
        // If col = 1 (second column), this loop will check arr[0][1], arr[1][1], arr[2][1], and find that arr[1][1] (30) is the max.

        // Iterate through each row to find the maximum element in the given column.
        for (int i = 0; i < n; i++) {
            if (arr[i][col] > max) {  // If the current element is larger than the max found so far,
                max = arr[i][col];  // Update the max value.
                index = i;  // Update the row index of the maximum element.
            }
        }

        // Return the row index that contains the maximum element in the specified column.
        return index;
    }

    // Function to find the first element in the row that is greater than 'x'.
    // This is an implementation of binary search that returns the upper bound of 'x' in the row.
    private int upperBound(int[] arr, int x, int m) {
        int low = 0, high = m - 1; // Set the search space from the first to the last element
        int ans = m;  // If no element is greater than 'x', 'ans' will remain as 'm' (out of bounds)

        // Binary search to find the upper bound of 'x'
        while (low <= high) {
            int mid = (low + high) / 2;  // Midpoint of the current range

            // If arr[mid] > x, we found an upper bound, so narrow the search to the left side
            if (arr[mid] > x) {
                ans = mid; // Update 'ans' to the current 'mid' index
                high = mid - 1; // Move to the left side of the array
            }
            // Otherwise, move to the right half to find a larger value
            else {
                low = mid + 1;
            }
        }
        return ans;  // Return the index of the first element greater than 'x'
        // Example: arr = [1, 3, 5, 7], x = 4 -> upperBound will return index 2 (because arr[2] = 5 is the first element > 4)
        // Time complexity: O(log m) where m is the number of columns in the matrix (binary search)
    }

    // Function to count how many elements in the matrix are <= 'x'.
    // It goes through each row and counts the number of elements <= 'x' using the upperBound function.
    private int countSmallEqual(int[][] matrix, int n, int m, int x) {
        int cnt = 0;  // Initialize the count of elements <= 'x'

        // Iterate over every row in the matrix
        for (int i = 0; i < n; i++) {
            // For each row, add the count of elements <= 'x' by using upperBound
            cnt += upperBound(matrix[i], x, m);
        }
        return cnt;  // Return the total count of elements <= 'x'
        // Example: matrix = [[1, 3, 5], [2, 6, 9]], x = 4 -> countSmallEqual will return 4 (four elements are <= 4: [1, 3, 2])
        // Time complexity: O(n * log m) where n is the number of rows, m is the number of columns (since we apply binary search for each row)
    }

    // Function to find the median of the matrix.
    // The matrix is row-wise sorted, and the goal is to use binary search to find the median value.
    public int findMedian(int[][] matrix) {
        int n = matrix.length;  // Number of rows in the matrix
        int m = matrix[0].length;  // Number of columns in the matrix

        // Initialize the search space for the binary search.
        // 'low' will be the smallest element in the matrix and 'high' will be the largest.
        int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;

        // Step 1: Find the smallest and largest element in the matrix
        for (int i = 0; i < n; i++) {
            low = Math.min(low, matrix[i][0]);  // The smallest element is in the first column of each row
            high = Math.max(high, matrix[i][m - 1]);  // The largest element is in the last column of each row
        }

        // The median position is (n * m) / 2 when the matrix is flattened
        int req = (n * m) / 2;

        // Step 2: Binary search between 'low' and 'high' to find the median
        while (low <= high) {
            int mid = low + (high - low) / 2;  // Find the middle value in the current search range

            // Count how many elements in the matrix are <= 'mid'
            int smallEqual = countSmallEqual(matrix, n, m, mid);

            // If the count of elements <= 'mid' is less than or equal to the required count,
            // it means the median is greater, so move the lower bound up
            if (smallEqual <= req) {
                low = mid + 1;  // Move the lower bound up
            }
            // Otherwise, the median must be smaller, so move the upper bound down
            else {
                high = mid - 1;  // Move the upper bound down
            }
        }

        // Step 3: The binary search narrows down the range, and 'low' will point to the median value
        return low;  // Return the median value
        // Example: matrix = [[1, 3, 5], [2, 6, 9]], the median will be 5 because it's the middle element in the sorted order [1, 2, 3, 5, 6, 9]
        // Time complexity: O(n * log m * log(max-min)) where n is the number of rows, m is the number of columns, and max-min is the range of matrix elements
    }
}