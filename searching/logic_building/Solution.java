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
            // mid-distance (m) could be tested like 4 or 5 to see if 3 cows can be placed 4 or 5 units apart.
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

    public int findPages(int[] bookPages, int totalStudents) {
        // Initialize result as -1, meaning if we can't allocate, we'll return this.
        int result = -1;

        // If the number of students is greater than the number of books, return -1.
        // This is because it's impossible to allocate books to more students than we have.
        if (totalStudents > bookPages.length) {
            return result;
        }

        // The minimum number of pages to consider is the book with the maximum pages,
        // because each student must read at least one book, so this is the lowest possible allocation.
        int minPages = Arrays.stream(bookPages).max().getAsInt();

        // The maximum number of pages would be if one student had to read all the books,
        // which is the total sum of all book pages.
        int maxPages = Arrays.stream(bookPages).sum();

        // Perform binary search between the minimum and maximum number of pages.
        while (minPages <= maxPages) {
            // Find the middle number of pages that could be allocated.
            int midPages = minPages + (maxPages - minPages) / 2;

            // Check how many students are needed if each student is allocated at most 'midPages' pages.
            // If the number of students needed is less than or equal to the given number of students,
            // then 'midPages' is a possible solution, so we store it and try to find a smaller answer.
            if (allocateBooksToStudents(bookPages, midPages) <= totalStudents) {
                result = midPages; // Store the valid solution.
                maxPages = midPages - 1; // Try to minimize further by checking lower pages.
            } else {
                minPages = midPages + 1; // If more students are required, increase the pages limit.
            }

        /*
        Example:
        - bookPages = [12, 34, 67, 90], totalStudents = 2
        - minPages = 90 (max of array), maxPages = 203 (sum of array)
        - midPages = (90 + 203) / 2 = 146
        - Check if it's possible to allocate books so no student reads more than 146 pages.
        */
        }

        // Return the minimum number of pages found.
        return result;
    }

    // Helper function to determine how many students are needed if each student can read at most 'maxPages' pages.
    private int allocateBooksToStudents(int[] bookPages, int maxPagesPerStudent) {
        int studentsRequired = 1; // Start with 1 student.
        int currentPageAllocation = bookPages[0]; // Assign the first book to the first student.

        // Iterate through each book.
        for (int i = 1; i < bookPages.length; i++) {
            // If adding this book to the current student's total pages doesn't exceed 'maxPagesPerStudent',
            // assign this book to the current student.
            if (bookPages[i] + currentPageAllocation <= maxPagesPerStudent) {
                currentPageAllocation += bookPages[i];
            } else {
                // Otherwise, allocate this book to a new student and reset the currentPageAllocation.
                studentsRequired++; // Increase the student count.
                currentPageAllocation = bookPages[i]; // Start a new allocation with this book.
            }

        /*
        Example:
        - maxPagesPerStudent = 146
        - bookPages = [12, 34, 67, 90]
        - Allocate 12 + 34 = 46, next 46 + 67 = 113, but adding 90 exceeds 146,
          so allocate 90 to a new student. Total students required = 2.
        */
        }

        // Return the total number of students required.
        return studentsRequired;
    }

    public int findPeakElement(int[] nums) {
        // If the array has only one element, that element is a peak.
        // Example: nums = [3] -> peak is 3 (index 0).
        if (nums.length == 1) {
            return 0; // Return index 0 because it's the only element.
        }

        // If the first element is greater than the second element, it must be a peak.
        // Example: nums = [5, 3, 2] -> peak is 5 (index 0).
        if (nums[0] > nums[1]) {
            return 0; // Return index 0 because nums[0] is larger than nums[1].
        }

        // If the last element is greater than the second last element, it must be a peak.
        // Example: nums = [1, 2, 3, 5] -> peak is 5 (index 3).
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1; // Return the last index because it's larger than its neighbor.
        }

        // Binary search initialization:
        // Search for a peak in the range from the second element to the second last element.
        int start = 1; // We already checked the first element, so start from index 1.
        int end = nums.length - 2; // We already checked the last element, so end at index nums.length-2.

        // Perform binary search in the middle of the array to find a peak element.
        while (start <= end) {
            int mid = start + (end - start) / 2; // Calculate the mid-point of the current range.

            // Check if the middle element is greater than its neighbors (a peak).
            // If true, mid is a peak element.
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid; // Return the index of the peak element.
            }

            // If nums[mid] is greater than its left neighbor but less than its right neighbor,
            // it means the peak lies on the right side. Example: nums = [1, 2, 3, 1], mid = 1.
            // Since nums[mid] < nums[mid + 1], move right to find the peak.
            else if (nums[mid] > nums[mid + 1]) {
                end = mid - 1; // Move to the left half, as a peak exists there.
            }

            // If nums[mid] is greater than its right neighbor but less than its left neighbor,
            // the peak lies on the left side. Example: nums = [1, 3, 2], mid = 1.
            // Since nums[mid] > nums[mid + 1], move left to find the peak.
            else if (nums[mid] > nums[mid - 1]) {
                start = mid + 1; // Move to the right half, as a peak exists there.
            }

            // In other cases, continue narrowing the search space based on comparison.
            else {
                end = mid - 1; // Continue reducing the search space.
            }

        /*
        Example:
        - nums = [1, 2, 3, 1]
        - Initial start = 1, end = 2, mid = (1 + 2) / 2 = 1
        - nums[mid] = 2, nums[mid-1] = 1, nums[mid+1] = 3
        - Since 2 < 3, move to the right half: start = mid + 1 = 2
        - Now mid = 2, and nums[mid] = 3 is a peak because it's greater than both neighbors.
        */
        }

        // Return -1 in case no peak is found (though it won't happen in this problem).
        return -1;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure that we always binary search on the smaller array (nums1) to reduce complexity.
        int n = nums1.length;
        int m = nums2.length;

        // If nums1 is larger than nums2, swap them so that we binary search on the smaller array.
        // This makes the algorithm more efficient.
        if (n > m) {
            return findMedianSortedArrays(nums2, nums1);  // Swap and recur to keep binary search on the smaller array.
        }

        int low = 0;      // Start of the binary search on nums1.
        int high = n;     // End of the binary search on nums1 (not nums1.length-1, because we are considering partitions).

        // We are partitioning the two arrays such that all elements on the left of the partition
        // are less than or equal to all elements on the right. We binary search on nums1 to find this partition.
        while (low <= high) {
            // Partition in nums1: mid-point for the first array
            int partition1 = (low + high) / 2;

            //The formula (n + m + 1) / 2 ensures that the left half of both arrays combined
            //has one more element if the total length is odd. We add +1 to handle odd-length cases.
            int partition2 = (n + m + 1) / 2 - partition1;

            // Edge case: If partition1 is at the start, there are no elements on the left side of nums1
            int maxLeft1 = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1];

            // Edge case: If partition1 is at the end, there are no elements on the right side of nums1
            int minRight1 = (partition1 == n) ? Integer.MAX_VALUE : nums1[partition1];

            // Similarly, handle the left and right elements for nums2
            int maxLeft2 = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1];
            int minRight2 = (partition2 == m) ? Integer.MAX_VALUE : nums2[partition2];

            // Intuition:
            // 1. All elements on the left of partition1 and partition2 must be less than or equal to all elements on the right.
            // 2. If maxLeft1 <= minRight2 and maxLeft2 <= minRight1, then the correct partition is found.

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // If the total number of elements (n + m) is even, the median is the average of the two middle elements.
                if ((n + m) % 2 == 0) {
                    return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                }
                // If the total number of elements is odd, the median is the larger of the two left elements.
                else {
                    return Math.max(maxLeft1, maxLeft2);
                }
            }
            // If maxLeft1 > minRight2, it means we have too many large elements on the left side of nums1.
            // So, we need to move partition1 to the left by reducing the 'high' boundary.
            else if (maxLeft1 > minRight2) {
                high = partition1 - 1;
            }
            // Otherwise, move partition1 to the right by increasing the 'low' boundary.
            else {
                low = partition1 + 1;
            }

            // Example walkthrough:
            // nums1 = [1, 3, 8], nums2 = [7, 9, 10, 11]
            // n = 3, m = 4
            // Partition positions initially: partition1 = (0+3)/2 = 1, partition2 = (3+4+1)/2 - partition1 = 2
            // maxLeft1 = nums1[0] = 1, minRight1 = nums1[1] = 3
            // maxLeft2 = nums2[1] = 9, minRight2 = nums2[2] = 10
            // maxLeft1 <= minRight2 and maxLeft2 <= minRight1 => valid partition, so compute the median.
        }

        // If no valid partition is found (though this should never happen with valid input), return an error value.
        return -1;
    }

    public int kthElement(int[] a, int[] b, int k) {
        // Get the lengths of the arrays a and b
        int n = a.length;
        int m = b.length;

        // Ensure that we always binary search on the smaller array for efficiency.
        // If 'a' is longer than 'b', we swap the arrays to maintain the condition n <= m.
        // This reduces the search space and ensures better performance.
        if (n > m) {
            return kthElement(b, a, k); // Recursively swap and call again
        }

        // Define the binary search range for 'a'.
        // We are finding the partition for 'a' within this range.
        // low starts at max(0, k-m) because there must be at least 'k-m' elements from 'a' to ensure
        // that partitionB is valid.
        // high ends at min(n, k) because there can be at most 'k' elements taken from 'a'.
        int low = Math.max(0, k - m);
        int high = Math.min(n, k);

        // Perform binary search
        while (low <= high) {
            // Partition the array 'a'. We use binary search to choose the partition point.
            // partitionA is the number of elements we are taking from array 'a'.
            int partitionA = (low + high) / 2;

            // partitionB is automatically determined based on the total 'k'.
            // We want to select a total of 'k' elements from both arrays, so if partitionA elements
            // are taken from 'a', then 'partitionB = k - partitionA' must be taken from 'b'.
            int partitionB = k - partitionA;

            // Edge case: if partitionA is 0, there are no elements on the left side of the partition in 'a',
            // so we treat maxLeftA as negative infinity. Otherwise, it is the element just before the partition.
            int maxLeftA = (partitionA == 0) ? Integer.MIN_VALUE : a[partitionA - 1];

            // Edge case: if partitionA equals n, there are no elements on the right side of the partition in 'a',
            // so we treat minRightA as positive infinity. Otherwise, it is the element just after the partition.
            int minRightA = (partitionA == n) ? Integer.MAX_VALUE : a[partitionA];

            // Similar logic for array 'b':
            // If partitionB is 0, it means there are no elements on the left side of the partition in 'b',
            // so maxLeftB is negative infinity. Otherwise, it's the element just before the partition.
            int maxLeftB = (partitionB == 0) ? Integer.MIN_VALUE : b[partitionB - 1];

            // If partitionB equals m, no elements remain on the right side of the partition in 'b',
            // so minRightB is positive infinity. Otherwise, it's the element just after the partition.
            int minRightB = (partitionB == m) ? Integer.MAX_VALUE : b[partitionB];

            // Now we check if the current partition is valid:
            // We need maxLeftA <= minRightB and maxLeftB <= minRightA.
            // This ensures that all elements on the left are smaller than those on the right.
            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                // If the partition is correct, return the largest element from the left half.
                // We want the 'k'th element, so it will be the maximum of the two left side elements.
                return Math.max(maxLeftA, maxLeftB);
            }
            // If maxLeftA > minRightB, it means we have too many elements from 'a' in the left partition.
            // We need to reduce the number of elements from 'a', so we move the partition to the left.
            else if (maxLeftA > minRightB) {
                high = partitionA - 1;
            }
            // If maxLeftB > minRightA, it means we need more elements from 'a' in the left partition.
            // Move the partition to the right by increasing low.
            else {
                low = partitionA + 1;
            }

            // Example:
            // a = [2, 3, 6, 7, 9], b = [1, 4, 8, 10], k = 5
            // We want to find the 5th smallest element from both arrays.
            // Partition the arrays such that we have 5 elements on the left (total).
            // We keep adjusting the partitions using binary search until we find the correct one.
        }

        // If we exit the loop without finding the kth element, return -1 (this should never happen in a valid input).
        return -1;
    }

    // if the maximum allowed distance between any two consecutive stations is `dist`.
    private int numberOfGasStationsRequired(double dist, int[] arr) {
        int n = arr.length;  // Number of gas stations in the original array
        int cnt = 0;  // Counter for the number of new gas stations required

        // Iterate over each pair of consecutive gas stations
        for (int i = 1; i < n; i++) {

            // Calculate the number of gas stations required between arr[i-1] and arr[i]
            // We find how many stations are needed so that no gap exceeds 'dist'.
            int numberInBetween = (int) ((arr[i] - arr[i - 1]) / dist);

            // Check if the gap between arr[i-1] and arr[i] is exactly divisible by dist.
            // If the gap is exactly divisible, we subtract one because we don't need an extra station
            // for the last segment (it would land exactly on the existing station).
            if ((arr[i] - arr[i - 1]) == (dist * numberInBetween)) {
                numberInBetween--;  // Reduce count if the distance is perfectly divisible
            }

            cnt += numberInBetween;  // Add the required number of stations for this segment
        }

        return cnt;  // Return total number of new gas stations needed
    }

    public double minimiseMaxDistance(int[] arr, int k) {
        int n = arr.length;  // Number of existing gas stations

        // Variables for binary search: low starts at 0, high is the maximum distance between consecutive stations
        double low = 0;
        double high = 0;

        // Calculate the maximum distance between any two consecutive gas stations
        // This will be the upper limit (high) for our binary search.
        for (int i = 0; i < n - 1; i++) {
            high = Math.max(high, arr[i + 1] - arr[i]);
        }

        // diff is the precision we want to achieve in our answer (1e-6 here for accuracy).
        double diff = 1e-6;

        // Binary search to minimize the maximum distance
        // We keep searching while the difference between high and low is greater than `diff`.
        while (high - low > diff) {
            // Midpoint between low and high (this is the maximum allowed distance we are testing).
            double mid = (low + high) / 2.0;

            // Calculate how many new gas stations are required if the maximum allowed distance is `mid`.
            int cnt = numberOfGasStationsRequired(mid, arr);

            // If we need more gas stations than `k`, it means the allowed distance `mid` is too small.
            // So, we increase the minimum distance (low).
            if (cnt > k) {
                low = mid;  // We need fewer stations, so we increase the allowed distance
            } else {
                // If we can fit the gas stations with `k` or fewer, we try to minimize the distance further.
                high = mid;  // Decrease the maximum allowed distance
            }

            // Example Intuition:
            // arr = [0, 10], k = 1
            // Initially, high = 10 (max distance between any two consecutive stations).
            // Let's say mid = 5. How many new stations do we need if the maximum allowed distance is 5?
            // Between 0 and 10, we can place 1 new station to ensure no gap is greater than 5.
            // If k = 1, this is valid, so we will try to minimize the distance further.
            // If we needed more than k new stations, we would increase `low` to try allowing a greater distance.
        }

        // At the end, `high` holds the minimized maximum distance, which is our answer.
        return high;
    }

    // when dividing the array into exactly 'numPartitions' subarrays.
    public int largestSubArraySumMinimized(int[] array, int numPartitions) {
        int result = -1;  // Store the minimized largest subarray sum

        // If the number of partitions is greater than the length of the array,
        // it's not possible to partition the array properly.
        if (numPartitions > array.length) {
            return result;  // Return -1 to indicate invalid partitioning
        }

        // The minimum possible value for the largest sum is the maximum element of the array.
        // This is because no partition can have a sum less than the largest element in the array.
        int maxElement = Arrays.stream(array).max().getAsInt();

        // The maximum possible value for the largest sum is the total sum of the array.
        // This happens when we put all elements into one partition.
        int totalSum = Arrays.stream(array).sum();

        // Binary search to find the minimized largest sum.
        // We search between the maxElement (smallest possible largest sum)
        // and totalSum (largest possible largest sum).
        while (maxElement <= totalSum) {
            // Midpoint of current search range
            // This represents a candidate for the maximum sum of any partition.
            int mid = maxElement + (totalSum - maxElement) / 2;

            // Check if we can partition the array with this 'mid' as the largest sum
            // If the number of partitions required is <= numPartitions, it means this is a valid division.
            if (canDivideArray(array, mid) <= numPartitions) {
                // If the array can be divided into the required partitions with 'mid' as the largest sum,
                // we store this as a potential result and continue to search for a smaller largest sum.
                result = mid;

                // Try to find an even smaller maximum sum by reducing the upper bound (totalSum).
                totalSum = mid - 1;
            } else {
                // If more partitions are needed, it means 'mid' is too small, and we need to increase it.
                maxElement = mid + 1;
            }

            // Example intuition:
            // Suppose the array is [7, 2, 5, 10, 8], and we want to partition it into 2 subarrays.
            // We start with a range from maxElement (10, the largest element) to totalSum (32).
            // We try mid = (10 + 32) / 2 = 21. Can we divide the array into 2 subarrays such that
            // the largest sum is <= 21? Yes, we can do [7, 2, 5] and [10, 8].
            // We store 21 as the result and then try to minimize it further by checking if we can do better.
        }

        // Return the minimized largest sum after partitioning the array
        return result;
    }

    // Helper function to determine how many partitions are needed if the largest sum per partition is 'maxSumPerPartition'.
    private int canDivideArray(int[] array, int maxSumPerPartition) {
        int partitionsRequired = 1;  // Start with 1 partition (at least one partition is required)
        int currentPartitionSum = array[0];  // Start by adding the first element to the first partition

        // Traverse the array and try to form partitions
        for (int i = 1; i < array.length; i++) {
            // If adding the current element to the current partition doesn't exceed the maximum allowed sum,
            // we add it to the current partition.
            if (array[i] + currentPartitionSum <= maxSumPerPartition) {
                currentPartitionSum += array[i];  // Add current element to the current partition
            } else {
                // Otherwise, we start a new partition and assign the current element to it.
                partitionsRequired++;  // Increase the partition count
                currentPartitionSum = array[i];  // Reset the partition sum with the current element
            }

            // Example intuition:
            // Suppose array = [7, 2, 5, 10, 8] and maxSumPerPartition = 21.
            // We start with the first element 7. The sum of the current partition is 7.
            // Adding 2 keeps the sum at 9, so we continue.
            // Adding 5 keeps the sum at 14, so we continue.
            // Adding 10 would make the sum 24, which exceeds 21, so we create a new partition starting at 10.
            // Then we add 8 to the new partition. We end up with two partitions: [7, 2, 5] and [10, 8].
        }

        // Return the total number of partitions required with the current maxSumPerPartition
        return partitionsRequired;
    }
}
