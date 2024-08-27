package arrays.faq_medium;

import sorting.SortUtil;

import java.util.*;

public class Solution {

    public int[] leaders(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        stack.push(nums[nums.length-1]);
        int j = nums.length-1;
        while (j > -1) {
            if (nums[j] > stack.peek()) {
                stack.push(nums[j]);
            }
            j--;
        }
        List<Integer> arrayList = new ArrayList<>();
        while(!stack.isEmpty()) {
            arrayList.add(stack.pop());
        }

        return arrayList.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] replaceElements(int[] arr) {
        if (arr.length == 1) {
            return new int[]{-1};
        }
        int temp = arr[arr.length-1];
        for (int i = arr.length-2; i >= 0; i--) {
            int backup = arr[i];
            arr[i] = Math.max(temp, arr[i+1]);
            temp = Math.max(temp, backup);
        }
        arr[arr.length-1] = -1;
        return arr;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int minRow = 0;
        int minCol = 0;
        int maxRow = matrix.length-1;
        int maxCol = matrix[0].length-1;

        List<Integer> arrayList = new ArrayList<>();

        int totalElements = matrix.length * matrix[0].length;

        int cnt = 0;
        while (cnt < totalElements) {
            //top most row
            for (int i = minCol ; i <= maxCol && cnt < totalElements; i++) {
                arrayList.add(matrix[minRow][i]);
                cnt++;
            }

            minRow = minRow + 1;

            //right most column
            for (int i = minRow; i <= maxRow && cnt < totalElements; i++) {
                arrayList.add(matrix[i][maxCol]);
                cnt++;
            }

            maxCol = maxCol - 1;

            //bottom most row
            for (int i = maxCol; i >= minCol && cnt < totalElements ; i--) {
                arrayList.add(matrix[maxRow][i]);
                cnt++;
            }

            maxRow = maxRow - 1;

            //left most row
            for (int i = maxRow; i >= minRow && cnt < totalElements; i--) {
                arrayList.add(matrix[i][minCol]);
                cnt++;
            }

            minCol = minCol + 1 ;
        }
        return arrayList;
    }

    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int posIndex = 0;
        int negIndex = 1;

        int[] result = new int[n];

        for (int num : nums) {
            if (num > 0) {
                result[posIndex] = num;
                posIndex += 2;
            } else {
                result[negIndex] = num;
                negIndex += 2;
            }
        }
        return result;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            List<Integer> currentRow = new ArrayList<>();

            currentRow.add(1);

            if (rowIndex > 0) {
                List<Integer> previousRow = triangle.get(rowIndex - 1);
                for (int colIndex = 1; colIndex < rowIndex; colIndex++) {
                    int leftParent = previousRow.get(colIndex - 1);
                    int rightParent = previousRow.get(colIndex);
                    int currentValue = leftParent + rightParent;
                    currentRow.add(currentValue);
                }

                currentRow.add(1);
            }

            triangle.add(currentRow);
        }

        return triangle;
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>(); // will be num and index
        int[] ans = new int[]{-1,-1};
        for(int i = 0; i < nums.length; i++) {
            if (hashMap.get(target - nums[i]) != null) {
                ans[0] = hashMap.get(target - nums[i]);
                ans[1] = i;
                break;
            } else {
                hashMap.put(nums[i], i);
            }
        }
        return ans;
    }

//    public List<List<Integer>> threeSum(int[] nums) {
//        HashSet<List<Integer>> set = new HashSet<>();
//        for (int i = 0; i < nums.length; i++) {
//            HashSet<Integer> hashSet = new HashSet<>();
//            for (int j = i+1; j < nums.length; j++) {
//                int potentialThirdNum = -(nums[i] + nums[j]);
//                if (hashSet.contains(potentialThirdNum)) {
//                    List<Integer> list = new ArrayList<>();
//                    list.add(nums[i]);
//                    list.add(nums[j]);
//                    list.add(potentialThirdNum);
//                    set.add(list);
//                }
//                hashSet.add(nums[j]);
//            }
//        }
//        return new ArrayList<>(set);
//    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int firstNumberIndex = 0; firstNumberIndex < nums.length; firstNumberIndex++) {
            if (firstNumberIndex > 0 && nums[firstNumberIndex] == nums[firstNumberIndex - 1]) {
                continue;  // Skip duplicates for the first number
            }
            int secondNumberIndex = firstNumberIndex + 1;
            int thirdNumberIndex = nums.length - 1;
            while (secondNumberIndex < thirdNumberIndex) {
                if (nums[firstNumberIndex] + nums[secondNumberIndex] + nums[thirdNumberIndex] == 0) {
                    ans.add(Arrays.asList(nums[firstNumberIndex], nums[secondNumberIndex], nums[thirdNumberIndex]));

                    //skip duplicates from the left
                    while (secondNumberIndex < thirdNumberIndex && nums[secondNumberIndex] == nums[secondNumberIndex + 1]) {
                        secondNumberIndex++;
                    }
                    //skip duplicates from the right
                    while (secondNumberIndex < thirdNumberIndex && nums[thirdNumberIndex] == nums[thirdNumberIndex - 1]) {
                        thirdNumberIndex--;
                    }

                    secondNumberIndex++;
                    thirdNumberIndex--;
                } else if (nums[firstNumberIndex] + nums[secondNumberIndex] + nums[thirdNumberIndex] > 0) {
                    thirdNumberIndex--;
                } else {
                    secondNumberIndex++;
                }
            }
        }
        return ans;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ansList = new ArrayList<>();
        if (nums.length < 4) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // Skip duplicate elements for i
            }

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue; // Skip duplicate elements for j
                }

                int k = j + 1;
                int l = nums.length - 1;
                while (k < l) {

                    long sum = (long) nums[i] + (long) nums[j] + (long) nums[k] + (long) nums[l];

                    if (sum == target) {
                        ansList.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));

                        while (k < l && nums[k] == nums[k + 1]) k++;
                        while (k < l && nums[l] == nums[l - 1]) l--;

                        k++;
                        l--;
                    } else if (sum > target) {
                        l--;
                    } else {
                        k++;
                    }
                }
            }
        }
        return ansList;
    }

    public void sortZeroOneTwo(int[] nums) {
        int i = 0, j = 0, k = nums.length-1;
        while(i < k) {
            if (nums[i] == 1) {
                i++;
            }
            else if (nums[i] == 2) {
                swap(nums, i, k);
                k--;
            } else {
                swap(nums, i, j);
                i++;
                j++;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int currentBestSum = 0;

        for (int num : nums) {
            currentBestSum = Math.max(currentBestSum + num, num);
            ans = Math.max(currentBestSum, ans);
        }

        return ans;
    }
}