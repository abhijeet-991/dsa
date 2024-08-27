package arrays.fundamentals;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Solution {

    public int linearSearch(int[] nums, int target) {
        if (nums == null) {
            throw new IllegalArgumentException("Array is null operation not possible");
        }
        OptionalInt result
                = IntStream.range(0, nums.length-1).filter(i -> nums[i] == target).findFirst();
        return result.orElse(-1);
    }

    public int largestElement(int[] nums) {
        return Arrays.stream(nums).max().orElseThrow(IllegalArgumentException::new);
    }

    public int secondLargestElement(int[] nums) {
        int maxElement = Arrays.stream(nums).max().orElseThrow(IllegalArgumentException::new);
        int ans = Integer.MIN_VALUE;
        int temp = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num > temp) {
                temp = num;
                if (temp < maxElement) {
                    if (temp > ans) {
                        ans = temp;
                    }
                }
                temp = Integer.MIN_VALUE;
            }
        }

        return ans == Integer.MIN_VALUE ? -1 : ans;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int cnt = 0;
        int ans = 0;
        for (int num : nums) {
            if (num == 1) {
                cnt++;
            } else {
                ans = Math.max(ans, cnt);
                cnt = 0;
            }
        }
        ans = Math.max(cnt, ans);
        return ans;
    }

    public void rotateArrayByOne(int[] nums) {
        int temp = nums[0];
        for (int i = 0; i< nums.length-1; i++) {
            nums[i] = nums[i+1];
        }
        nums[nums.length-1] = temp;
    }

    public void rotateArray(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        reverse(nums, 0, k - 1);

        reverse(nums, k, n - 1);

        reverse(nums, 0, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

}