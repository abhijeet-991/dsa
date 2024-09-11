package searching.logic_building;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Solution {
    public int searchInsert(int[] nums, int target) {
        int potentialAnsIndex = nums.length; // Default to array length if target is greater than all elements
        int start = 0; // Start index of the search range
        int end = nums.length - 1; // End index of the search range

        // Continue searching while the search range is valid (start <= end)
        while (start <= end) {
            // Calculate the middle index to avoid overflow in large arrays
            int mid = start + (end - start) / 2;

            // If the middle element is greater than or equal to the target,
            // update potentialAnsIndex to mid and move the end to mid - 1.
            // This is because we are looking for the first position where the target could be inserted.
            if (nums[mid] >= target) {
                potentialAnsIndex = mid;
                end = mid - 1; // Narrow the search to the left half

                // Note: We use `end = mid - 1` here because we are looking for the lowest index where
                // the target can be inserted. By reducing `end` to `mid - 1`, we continue checking
                // if there's a lower index where the target can still fit.

            } else {
                start = mid + 1; // Narrow the search to the right half
            }
        }

        // Example: For an array [1, 3, 5, 6] and target = 5:
        // - Initial values: start = 0, end = 3, mid = 1 (value = 3).
        // - Since 3 < 5, move start to mid + 1 = 2.
        // - New values: start = 2, end = 3, mid = 2 (value = 5).
        // - Since 5 >= 5, update potentialAnsIndex = mid = 2 and move end to mid - 1 = 1.
        // - The search ends with potentialAnsIndex = 2, which is the index of the target or where it should be inserted.
        //
        // If target = 2, the function would determine that the target should be inserted at index 1, where 3 is currently located.

        // Return the index where the target should be inserted to maintain sorted order.
        return potentialAnsIndex;
    }

    public int[] getFloorAndCeil(int[] nums, int x) {
        int floor = getFloor(nums, x); // Get the floor value (largest element <= x)
        int ceil = getCeil(nums, x);   // Get the ceil value (smallest element >= x)
        return new int[]{floor, ceil}; // Return both floor and ceil in an array
    }

    private int getFloor(int[] nums, int x) {
        int start = 0; // Start index of the search range
        int end = nums.length - 1; // End index of the search range
        int ans = -1; // Initialize answer to -1, meaning no floor found

        // Continue searching while the search range is valid (start <= end)
        while (start <= end) {
            // Calculate the middle index to avoid overflow in large arrays
            int mid = start + (end - start) / 2;

            // If the middle element is less than or equal to `x`,
            // update `ans` to the current middle value and move the start to `mid + 1`.
            if (nums[mid] <= x) {
                ans = nums[mid];
                start = mid + 1; // Narrow the search to the right half to find a closer floor

                // Example: For an array [1, 2, 4, 6] and x = 5:
                // - Initial values: start = 0, end = 3, mid = 1 (value = 2).
                // - Since 2 <= 5, update ans = 2 and move start to mid + 1 = 2.
                // - New values: start = 2, end = 3, mid = 2 (value = 4).
                // - Since 4 <= 5, update ans = 4 and move start to mid + 1 = 3.
                // - New values: start = 3, end = 3, mid = 3 (value = 6).
                // - Since 6 > 5, move end to mid - 1 = 2.
                // - The loop ends, and the floor value is 4.

            } else {
                end = mid - 1; // Narrow the search to the left half
            }
        }

        return ans; // Return the floor value (largest element <= x), or -1 if no such element
    }

    private int getCeil(int[] nums, int x) {
        int start = 0; // Start index of the search range
        int end = nums.length - 1; // End index of the search range
        int ans = -1; // Initialize answer to -1, meaning no ceil found

        // Continue searching while the search range is valid (start <= end)
        while (start <= end) {
            // Calculate the middle index to avoid overflow in large arrays
            int mid = start + (end - start) / 2;

            // If the middle element is less than `x`,
            // move the start to `mid + 1` to search in the right half.
            if (nums[mid] < x) {
                start = mid + 1; // Narrow the search to the right half

                // Example: For an array [1, 3, 5, 7] and x = 4:
                // - Initial values: start = 0, end = 3, mid = 1 (value = 3).
                // - Since 3 < 4, move start to mid + 1 = 2.
                // - New values: start = 2, end = 3, mid = 2 (value = 5).
                // - Since 5 >= 4, update ans = 5 and move end to mid - 1 = 1.
                // - The loop ends, and the ceil value is 5.

            } else {
                ans = nums[mid]; // Update `ans` to the current middle value
                end = mid - 1;   // Narrow the search to the left half to find a closer ceil
            }
        }

        return ans; // Return the ceil value (smallest element >= x), or -1 if no such element
    }

    public int[] searchRange(int[] nums, int target) {
        int firstPosition = getFirstPosition(nums, target); // Find the first occurrence of the target
        int lastPosition = getLastPosition(nums, target);   // Find the last occurrence of the target
        return new int[]{firstPosition, lastPosition};      // Return both positions as an array
    }

    private int getFirstPosition(int[] nums, int target) {
        int start = 0;            // Start index of the search range
        int end = nums.length - 1; // End index of the search range
        int ans = -1;             // Initialize answer to -1, meaning the target is not found

        // Continue searching while the search range is valid (start <= end)
        while (start <= end) {
            int mid = start + (end - start) / 2; // Calculate the middle index

            // If the middle element is equal to the target, update `ans` and move `end` to `mid - 1`
            // to find the first occurrence of the target.
            if (nums[mid] == target) {
                ans = mid;
                end = mid - 1; // Narrow the search to the left half

                // Example: For an array [5, 7, 7, 8, 8, 10] and target = 8:
                // - Initial values: start = 0, end = 5, mid = 2 (value = 7).
                // - Since 7 < 8, move start to mid + 1 = 3.
                // - New values: start = 3, end = 5, mid = 4 (value = 8).
                // - Since 8 == 8, update ans = 4 and move end to mid - 1 = 3.
                // - New values: start = 3, end = 3, mid = 3 (value = 8).
                // - Since 8 == 8, update ans = 3 and move end to mid - 1 = 2.
                // - The loop ends, and the first position is 3.

            } else if (nums[mid] > target) {
                end = mid - 1;   // Narrow the search to the left half
            } else {
                start = mid + 1; // Narrow the search to the right half
            }
        }

        return ans; // Return the first occurrence of the target, or -1 if not found
    }

    private int getLastPosition(int[] nums, int target) {
        int start = 0;            // Start index of the search range
        int end = nums.length - 1; // End index of the search range
        int ans = -1;             // Initialize answer to -1, meaning the target is not found

        // Continue searching while the search range is valid (start <= end)
        while (start <= end) {
            int mid = start + (end - start) / 2; // Calculate the middle index

            // If the middle element is equal to the target, update `ans` and move `start` to `mid + 1`
            // to find the last occurrence of the target.
            if (nums[mid] == target) {
                ans = mid;
                start = mid + 1; // Narrow the search to the right half

                // Example: For an array [5, 7, 7, 8, 8, 10] and target = 8:
                // - Initial values: start = 0, end = 5, mid = 2 (value = 7).
                // - Since 7 < 8, move start to mid + 1 = 3.
                // - New values: start = 3, end = 5, mid = 4 (value = 8).
                // - Since 8 == 8, update ans = 4 and move start to mid + 1 = 5.
                // - New values: start = 5, end = 5, mid = 5 (value = 10).
                // - Since 10 > 8, move end to mid - 1 = 4.
                // - The loop ends, and the last position is 4.

            } else if (nums[mid] > target) {
                end = mid - 1;   // Narrow the search to the left half
            } else {
                start = mid + 1; // Narrow the search to the right half
            }
        }

        return ans; // Return the last occurrence of the target, or -1 if not found
    }

    public int search(int[] nums, int k) {
        int start = 0;
        int end = nums.length - 1;
        int ansIndex = -1; // Initialize answer index to -1, meaning `k` is not found

        while (start <= end) {
            int mid = start + (end - start) / 2; // Calculate the middle index

            if (nums[mid] == k) {
                ansIndex = mid; // If `k` is found at `mid`, store the index
                break; // Exit the loop since we found the target

                // Intuition: If the element at `mid` is the target, we have found `k`
                // and can immediately return its index.

                // Example:
                // nums = [4, 5, 6, 7, 0, 1, 2], k = 6
                // start = 0, end = 6
                // mid = 3, nums[mid] = 7
                // Since 6 < 7, the target must be on the left side of `mid`,
                // so we search the left half by setting end = mid - 1.
            }
            else if (nums[start] <= nums[mid]) { // Check if the left half is sorted
                if (k >= nums[start] && k <= nums[mid]) {
                    end = mid - 1; // Search the left half if `k` is in this range
                } else {
                    start = mid + 1; // Otherwise, search the right half
                }

                // Intuition: If the left half is sorted, and `k` lies within the range
                // of the sorted half, we can safely narrow our search to this half.

                // Example:
                // nums = [4, 5, 6, 7, 0, 1, 2], k = 6
                // start = 0, end = 6
                // mid = 3, nums[mid] = 7
                // Since 4 <= 7 (left half sorted) and 6 lies between 4 and 7,
                // search left side by setting end = mid - 1.
            }
            else { // Otherwise, the right half must be sorted
                if (k >= nums[mid] && k <= nums[end]) {
                    start = mid + 1; // Search the right half if `k` is in this range
                } else {
                    end = mid - 1; // Otherwise, search the left half
                }

                // Intuition: If the right half is sorted, and `k` lies within the range
                // of the sorted half, we can safely narrow our search to this half.

                // Example:
                // nums = [4, 5, 6, 7, 0, 1, 2], k = 1
                // start = 0, end = 6
                // mid = 3, nums[mid] = 7
                // Since the right half [0, 1, 2] is sorted and 1 lies between 0 and 2,
                // we search the right side by setting start = mid + 1.
            }
        }

        return ansIndex; // Return the index of `k` or -1 if not found
    }

    public boolean searchInARotatedSortedArrayII(ArrayList<Integer> nums, int k) {
        int start = 0;
        int end = nums.size() - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2; // Calculate the middle index

            if (nums.get(mid) == k) {
                return true; // If the target `k` is found at `mid`, return true

                // Intuition: If the element at `mid` is the target, we have found `k`
                // and can immediately return true.

                // Example:
                // nums = [4, 5, 6, 7, 0, 1, 2], k = 6
                // start = 0, end = 6
                // mid = 3, nums[mid] = 6
                // Since 6 == 6, return true.
            }

            if (Objects.equals(nums.get(start), nums.get(mid)) && Objects.equals(nums.get(mid), nums.get(end))) {
                start++;
                end--; // Skip duplicates by narrowing the search range

                // Intuition: When duplicates are present, `start`, `mid`, and `end` could all
                // be equal, making it unclear which half is sorted. By narrowing the range,
                // we can avoid being stuck in an infinite loop.

                // Example:
                // nums = [2, 2, 2, 2, 2, 2, 2], k = 3
                // start = 0, end = 6
                // mid = 3, nums[mid] = 2
                // Since nums[start] == nums[mid] == nums[end], increment start and decrement end.
            } else if (nums.get(start) <= nums.get(mid)) {
                // Check if the left half is sorted
                if (k >= nums.get(start) && k < nums.get(mid)) {
                    end = mid - 1; // Search the left half if `k` is in this range
                } else {
                    start = mid + 1; // Otherwise, search the right half
                }

                // Intuition: If the left half is sorted, and `k` lies within the range
                // of the sorted half, narrow the search to this half.

                // Example:
                // nums = [4, 5, 6, 7, 0, 1, 2], k = 6
                // start = 0, end = 6
                // mid = 3, nums[mid] = 7
                // Since 4 <= 7 (left half sorted) and 6 lies between 4 and 7,
                // search left side by setting end = mid - 1.
            } else {
                // Otherwise, the right half must be sorted
                if (k > nums.get(mid) && k <= nums.get(end)) {
                    start = mid + 1; // Search the right half if `k` is in this range
                } else {
                    end = mid - 1; // Otherwise, search the left half
                }

                // Intuition: If the right half is sorted, and `k` lies within the range
                // of the sorted half, narrow the search to this half.

                // Example:
                // nums = [4, 5, 6, 7, 0, 1, 2], k = 1
                // start = 0, end = 6
                // mid = 3, nums[mid] = 7
                // Since the right half [0, 1, 2] is sorted and 1 lies between 0 and 2,
                // we search the right side by setting start = mid + 1.
            }
        }

        return false; // Return false if the target `k` is not found
    }

    public int findMin(ArrayList<Integer> arr) {
        int s = 0; // Start of the search range
        int min = Integer.MAX_VALUE; // Initialize `min` to the largest possible value
        int e = arr.size() - 1; // End of the search range

        while (s <= e) {
            int m = s + (e - s) / 2; // Calculate the middle index to divide the array

            // Check if the left half from `s` to `m` is sorted
            if (arr.get(s) <= arr.get(m)) {
                min = Math.min(arr.get(s), min);
                // If the left half is sorted, the smallest element must be at `s`
                // (since it's a rotated sorted array), compare it to the current `min`

                // Example:
                // arr = [3, 4, 5, 1, 2]
                // s = 0, e = 4, m = 2
                // arr[s] = 3, arr[m] = 5
                // Left half [3, 4, 5] is sorted, so minimum must be `3` or less,
                // Update `min = 3` and continue searching in the unsorted half.

                s = m + 1; // Move to the right half for further search
            } else { // If the right half is sorted
                min = Math.min(arr.get(m), min);
                // If the right half is sorted, then `arr[m]` is a candidate for the minimum.
                // Compare `arr[m]` with `min` to find the smallest value.

                // Example:
                // arr = [4, 5, 6, 7, 0, 1, 2]
                // s = 0, e = 6, m = 3
                // arr[s] = 4, arr[m] = 7
                // Right half [7, 0, 1, 2] is unsorted, so we search the unsorted portion.
                // Set min to `arr[m]` and adjust `e = m - 1` to narrow down the range.

                e = m - 1; // Move to the left half for further search
            }
        }

        return min; // Return the minimum value found
    }

    public int findKRotation(ArrayList<Integer> nums) {
        int s = 0; // Start of the search range
        int e = nums.size() - 1; // End of the search range
        int maxElement = Integer.MIN_VALUE; // Variable to track the maximum element found
        int ansIndex = -1; // To store the index of the maximum element

        // If the array is not rotated (first element is smaller than last element),
        // the rotation count is zero, so return 0 immediately.
        if (nums.get(0) <= nums.get(nums.size() - 1)) {
            return 0;
        }

        // Binary search loop to find the maximum element in the rotated sorted array
        while (s <= e) {
            int m = s + (e - s) / 2; // Calculate the middle index

            // Check if the left half from `s` to `m` is sorted
            if (nums.get(s) <= nums.get(m)) {
                // If left half is sorted, check if `nums[m]` is greater than `maxElement`
                if (nums.get(m) > maxElement) {
                    ansIndex = m; // Update the answer index with the maximum element's index
                    maxElement = nums.get(m); // Update the maximum element found
                }
                s = m + 1; // Move to the right half for further search
            } else {
                // Otherwise, check if `nums[e]` (last element) is greater than `maxElement`
                if (nums.get(e) > maxElement) {
                    ansIndex = e; // Update the answer index with the maximum element's index
                    maxElement = nums.get(e); // Update the maximum element found
                }
                e = m - 1; // Move to the left half for further search
            }

            // Example:
            // nums = [4, 5, 6, 7, 0, 1, 2]
            // s = 0, e = 6, m = 3
            // Left half [4, 5, 6, 7] is sorted, so update maxElement = 7 and ansIndex = 3.
            // Continue searching in the right half (unsorted).
        }

        // The number of rotations in the array is the index after the maximum element
        return ansIndex + 1;
    }

    public int singleNonDuplicate(int[] nums) {
        int s = 1; // Starting index for the search (ignoring first element)
        int e = nums.length - 2; // Ending index for the search (ignoring last element)

        // Edge case: if the array has only one element, return it as the answer.
        if (nums.length == 1) {
            return nums[0];
        }

        // Check if the single element is the first element
        if (nums[0] != nums[1]) {
            return nums[0];
        }

        // Check if the single element is the last element
        if (nums[nums.length - 1] != nums[e]) {
            return nums[nums.length - 1];
        }

        // Perform binary search to find the single element
        while (s <= e) {
            int m = s + (e - s) / 2; // Calculate the mid-point of the current range

            // Check if the middle element is the single element
            if (nums[m - 1] != nums[m] && nums[m] != nums[m + 1]) {
                return nums[m]; // If `nums[m]` is not equal to its neighbors, it's the single element
            }

            // The array is grouped in pairs except for the single element.
            // If `m` is odd and `nums[m]` is paired with `nums[m-1]`, the single element lies to the right.
            // If `m` is even and `nums[m]` is paired with `nums[m+1]`, the single element lies to the right.
            if (m % 2 != 0 && nums[m - 1] == nums[m] || (m % 2 == 0 && nums[m] == nums[m + 1])) {
                s = m + 1; // Move search to the right half
            } else {
                e = m - 1; // Move search to the left half
            }

            // Example:
            // nums = [1, 1, 2, 3, 3, 4, 4, 5, 5]
            // s = 1, e = 7, m = 4
            // nums[3] == nums[4], so the single element lies to the right.
            // Now s = 5, e = 7, and repeat the process.
        }

        // When the loop ends, `s` points to the single non-duplicate element.
        return nums[s];
    }

    public int minEatingSpeed(int[] nums, int h) {
        // s is the minimum possible eating speed (1 banana/hour)
        // e is the maximum possible eating speed (the largest pile in nums)
        int s = 1;
        int e = Arrays.stream(nums).max().getAsInt();  // Find the maximum element in nums

        // Binary search to find the minimum eating speed
        while (s <= e) {
            int m = s + (e - s) / 2;  // Mid-point speed

            // Check if Koko can finish all the bananas with this speed
            if (calcSum(nums, h, m)) {
                e = m - 1;  // Try to find a smaller eating speed
            } else {
                s = m + 1;  // Increase the eating speed as current is too slow
            }

            // Example:
            // nums = [3, 6, 7, 11], h = 8
            // Initially, s = 1, e = 11 (maximum element is 11)
            // m = (1 + 11) / 2 = 6
            // calcSum(nums, 8, 6) checks if Koko can finish at speed 6
            // If yes, reduce e to try smaller speeds (e = 5)
        }

        return s;  // This is the minimum speed that allows Koko to finish in h hours
    }

    // Helper method to calculate if Koko can eat all bananas at speed 'm' within 'h' hours
    private boolean calcSum(int[] nums, int h, int m) {
        int sum = 0;  // Sum represents total hours needed
        for (int num : nums) {
            // Calculate how many hours it takes to eat each pile with speed 'm'
            sum += (num + m - 1) / m;  // Equivalent to Math.ceil(num / m)

            // If the hours exceed the given limit h, return false early
            if (sum > h) {
                return false;
            }
        }
        return sum <= h;  // Return true if total hours are within the allowed limit h
    }

    public int minDays(int[] bloomDay, int m, int k) {
        // s is the minimum bloom day, e is the maximum bloom day
        int s = Arrays.stream(bloomDay).min().getAsInt();
        int e = Arrays.stream(bloomDay).max().getAsInt();

        // If it's impossible to make m bouquets (because there are fewer flowers than needed), return -1
        if (m * k > bloomDay.length) return -1;

        int minDays = Integer.MAX_VALUE;  // To store the minimum number of days needed

        // Perform binary search on the number of days
        while (s <= e) {
            int mid = s + (e - s) / 2;  // Calculate the mid-point (current number of days)

            // Count how many bouquets can be formed by day 'mid'
            int bouquetCount = countBouquets(bloomDay, mid, k);

            // If we can make at least 'm' bouquets, try to minimize the days
            if (bouquetCount >= m) {
                minDays = Math.min(mid, minDays);  // Update the minimum days if current is valid
                e = mid - 1;  // Search for fewer days
            } else {
                s = mid + 1;  // Increase the days, as current days aren't sufficient
            }

            // Example:
            // bloomDay = [1, 10, 3, 10, 2], m = 3, k = 1
            // Initial s = 1 (min bloom day), e = 10 (max bloom day)
            // mid = 5, check if we can make at least 3 bouquets by day 5.
        }

        // If no valid solution was found, return -1, otherwise return the minimum days found
        return minDays == Integer.MAX_VALUE ? -1 : minDays;
    }

    // Helper method to count how many bouquets can be formed by day 'dayLimit' with 'k' flowers per bouquet
    private int countBouquets(int[] bloomDay, int dayLimit, int k) {
        int flowerCount = 0;  // Count of consecutive flowers that have bloomed by 'dayLimit'
        int bouquetCount = 0;  // Total number of bouquets formed

        for (int day : bloomDay) {
            // If the flower blooms on or before 'dayLimit', add it to the current bouquet
            if (day <= dayLimit) {
                flowerCount++;  // Add the flower to the current bouquet

                // If we have enough flowers for a bouquet, increment the bouquet count
                if (flowerCount == k) {
                    bouquetCount++;
                    flowerCount = 0;  // Reset the flower count for the next bouquet
                }
            } else {
                flowerCount = 0;  // Reset flower count if we hit a flower that hasn't bloomed
            }

            // Example:
            // bloomDay = [1, 10, 3, 10, 2], dayLimit = 5, k = 1
            // By day 5, flowers at positions 1, 3, and 5 have bloomed, allowing for 3 bouquets.
        }

        return bouquetCount;  // Return the total number of bouquets formed by day 'dayLimit'
    }

    public int aggressiveCows(int[] positions, int k) {
        // Sort the positions to make sure cows are placed in ascending order
        Arrays.sort(positions);

        // 'ans' will hold the maximum minimum distance between cows found
        int ans = -1;

        // 's' is the minimum possible distance (0)
        int s = 0;
        // 'e' is the maximum possible distance (difference between the farthest cows)
        int e = Arrays.stream(positions).max().getAsInt() - Arrays.stream(positions).min().getAsInt();

        // Perform binary search to find the largest minimum distance where cows can be placed
        while (s <= e) {
            // 'm' is the middle point of the current search range
            int m = s + (e - s) / 2;

            // Check if it's possible to place 'k' cows such that each cow is at least 'm' distance apart
            if (canPlaceCows(positions, m, k)) {
                // If placing cows is possible with at least 'm' distance, record this distance
                ans = m;
                // Try for a larger distance
                s = m + 1;
            } else {
                // If it's not possible, try a smaller distance
                e = m - 1;
            }

            // Example:
            // positions = [1, 2, 8, 4, 9], k = 3
            // After sorting: [1, 2, 4, 8, 9]
            // Initial search range: s = 0, e = 8 (max - min)
            // Mid distance (m) could be tested like 4 or 5 to see if 3 cows can be placed 4 or 5 units apart.
        }

        return ans; // Return the largest minimum distance where cows can be placed
    }

    // Helper method to determine if 'k' cows can be placed such that each cow is at least 'distance' apart
    private boolean canPlaceCows(int[] positions, int distance, int k) {
        int count = 1; // Place the first cow at the first position
        int lastPosition = positions[0]; // Track the position of the last placed cow

        // Try placing remaining cows
        for (int i = 1; i < positions.length; i++) {
            // If the current position is at least 'distance' from the last placed cow
            if (positions[i] - lastPosition >= distance) {
                count++; // Place the next cow
                lastPosition = positions[i]; // Update the position of the last placed cow
            }
        }

        // Check if we placed at least 'k' cows
        return count >= k;

        // Example:
        // positions = [1, 2, 4, 8, 9], distance = 3, k = 3
        // We need to place 3 cows such that each cow is at least 3 units apart.
        // The valid placements are at positions 1, 4, and 9 (with distances of 3 and 5).
    }

}
