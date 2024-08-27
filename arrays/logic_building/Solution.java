package arrays.logic_building;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public void moveZeroes(int[] nums) {
        int i = 0;
        int j = 0;
        while (i < nums.length && j < nums.length) {
            if (nums[i] != 0 && nums[j] != 0) {
                i++;
                j++;
            }
            if (nums[i] != 0) {
                i++;
            }
            if (nums[j] == 0) {
                j++;
            }
            if (j < nums.length && nums[j] != 0) {
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

    public int removeDuplicates(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int j = 0, i = 0;
        while (i < nums.length) {
            if (nums[i] != nums[j]) {
                nums[j + 1] = nums[i];
                j++;
            }
            i++;
        }
        return j+1;
    }

    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2;
        return sum - Arrays.stream(nums).sum();
    }

    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    public int[] unionArray(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        ArrayList<Integer> arrayList = new ArrayList<>();
        int i = 0, j = 0;

        while (i < n1 && j < n2) {
            if (nums1[i] == nums2[j]) {
                addToArrayList(arrayList, nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                addToArrayList(arrayList, nums1[i]);
                i++;
            } else {
                addToArrayList(arrayList, nums2[j]);
                j++;
            }
        }

        while (i < n1) {
            addToArrayList(arrayList, nums1[i]);
            i++;
        }

        while (j < n2) {
            addToArrayList(arrayList, nums2[j]);
            j++;
        }

        return arrayList.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        ArrayList<Integer> arrayList = new ArrayList<>();
        int i = 0, j = 0;
        while(i < n1 && j < n2) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums2[j] < nums1[i]) {
                j++;
            } else {
                arrayList.add(nums1[i]);
                i++;
                j++;
            }
        }
        return arrayList.stream().mapToInt(Integer::intValue).toArray();
    }

    private void addToArrayList(ArrayList<Integer> arrayList, int value) {
        if (arrayList.isEmpty() || arrayList.get(arrayList.size() - 1) != value) {
            arrayList.add(value);
        }
    }
}
