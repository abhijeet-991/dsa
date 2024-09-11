package searching.on_answers;

public class Solution {
    public long floorSqrt(long n) {
        long s = 1; // Start of the search range
        long e = n; // End of the search range (maximum possible sqrt value is n itself)

        // Binary search to find the largest integer `m` such that `m * m <= n`
        while (s <= e) {
            long m = s + (e - s) / 2; // Calculate the mid-point of the current range to avoid overflow

            // Check if `m * m` equals `n`
            if (m * m == n) {
                return m; // If exact square root, return `m`
            }
            // If `m * m` is greater than `n`, reduce the search space to the left half
            else if (m * m > n) {
                e = m - 1;
            }
            // If `m * m` is less than `n`, search in the right half
            else {
                s = m + 1;
            }

            // Example:
            // n = 10
            // Initially, s = 1, e = 10, m = 5
            // m * m = 25, which is greater than 10, so we move to the left half (e = m-1).
            // Now, s = 1, e = 4, m = 2
            // m * m = 4, which is less than 10, so we move to the right half (s = m+1).
            // Continue this process until the exact or closest integer is found.
        }

        // When the loop ends, `e` will hold the largest integer whose square is less than or equal to `n`.
        return e;
    }

    public int NthRoot(int N, int M) {
        int s = 1;      // Start of the search range (smallest possible root)
        long e = M;     // End of the search range (largest possible root)
        int ans = -1;   // Default value in case no exact root is found

        // Binary search to find the integer `m` such that `m^N = M`
        while (s <= e) {
            long m = s + (e - s) / 2;  // Mid-point of the search range to avoid overflow

            // Calculate m^N using Math.pow and compare with M
            if (Math.pow(m, N) == M) {  // If m^N equals M, we have found the Nth root
                ans = (int) m;
                break;  // Exit the loop as we've found the exact root
            }
            // If m^N is greater than M, search in the left half
            else if (Math.pow(m, N) > M) {
                e = m - 1;
            }
            // If m^N is less than M, search in the right half
            else {
                s = (int) m + 1;
            }

            // Example:
            // N = 3, M = 27 (we're looking for the cube root of 27)
            // Initially, s = 1, e = 27, m = 14
            // m^3 = 14^3 = 2744, which is greater than 27, so we move to the left half (e = 13).
            // Now, s = 1, e = 13, m = 7
            // m^3 = 7^3 = 343, still greater than 27, so again move to the left (e = 6).
            // Now, s = 1, e = 6, m = 3
            // m^3 = 3^3 = 27, which is equal to M, so we return m.
        }

        // Return the found root or -1 if no exact root was found
        return ans;
    }

    public int smallestDivisor(int[] nums, int limit) {
        int low = 1;                     // Smallest possible divisor
        int high = findMax(nums);         // Largest possible divisor (maximum value in nums array)

        // Binary search to find the smallest divisor that keeps the sum <= limit
        while (low <= high) {
            int mid = low + (high - low) / 2;  // Mid-point divisor

            // Calculate the sum of nums divided by mid
            if (calculateSum(nums, mid) <= limit) {
                high = mid - 1;  // Try to find a smaller divisor
            } else {
                low = mid + 1;   // Increase the divisor as the current one results in too large a sum
            }

            // Example:
            // nums = [1, 2, 5, 9], limit = 6
            // Initially, low = 1, high = 9 (maximum element is 9)
            // mid = (1 + 9) / 2 = 5
            // calculateSum(nums, 5) = (1/5 + 2/5 + 5/5 + 9/5) = 1 + 1 + 1 + 2 = 5
            // 5 <= limit, so try smaller divisors (high = 4)
        }

        return low;  // The smallest divisor that keeps the sum <= limit
    }

    // Helper function to find the maximum value in the array
    private int findMax(int[] nums) {
        int max = nums[0];  // Initialize max with the first element
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        return max;  // Return the maximum element in nums
    }

    // Helper function to calculate the sum of divisions with the given divisor
    private int calculateSum(int[] nums, int divisor) {
        int sum = 0;
        for (int num : nums) {
            // Each element is divided by divisor and the ceiling of that division is taken
            sum += (num + divisor - 1) / divisor;  // Equivalent to Math.ceil(num / divisor)
        }
        return sum;  // Return the sum of the array elements divided by divisor
    }


}
