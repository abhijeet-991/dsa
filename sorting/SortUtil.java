package sorting;

public class SortUtil {

    public static int[] selectionSort(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums;
        }

        for (int i = 0; i < nums.length; i++) {
            int minIndexToSwapWith = getMinIndex(i+1, nums);
            if (nums[i] > nums[minIndexToSwapWith]) {
                nums[i] = nums[i] ^ nums[minIndexToSwapWith];
                nums[minIndexToSwapWith] = nums[i] ^ nums[minIndexToSwapWith];
                nums[i] = nums[i] ^ nums[minIndexToSwapWith];
            }
        }

        return nums;
    }

    private static int getMinIndex(int j, int[] nums) {
        int minIdx = j-1;
        int minEle = Integer.MAX_VALUE;
        for (int i = j; i < nums.length; i++) {
            if (nums[i] <= minEle) {
                minEle = nums[i];
                minIdx = i;
            }
        }
        return minIdx;
    }

    public static int[] bubbleSort(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    nums[i] = nums[i] ^ nums[j];
                    nums[j] = nums[i] ^ nums[j];
                    nums[i] = nums[i] ^ nums[j];
                }
            }
        }

        return nums;
    }

    public static int[] insertionSort(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums;
        }

        for (int i = 0; i < nums.length; i++) {
            sortTillI(i, nums);
        }

        return nums;
    }

    private static void sortTillI(int i, int[] nums) {
        if (i == 0) {
            return;
        }
        int temp = nums[i];
        while(i != 0 && temp <= nums[i-1]) {
            nums[i] = nums[i-1];
            i--;
        }
        nums[i] = temp;
    }

    public static int[] quickSort(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums;
        }
        quickSortHelper(nums, 0, nums.length-1);
        return nums;
    }

    private static void quickSortHelper(int[] nums, int l, int h) {
        if (l < h) {
            int j = partition(nums, l, h);
            quickSortHelper(nums, l, j-1);
            quickSortHelper(nums, j+1, h);
        }
    }

    private static int partition(int[] nums, int low, int high) {
        int l = low;
        int h = high;

        int pivot = nums[l];
        int i = l;

        while (l < h) {
            while (l < nums.length && nums[l] <= pivot) {
                l++;
            }

            while (h > -1 && nums[h] > pivot) {
                h--;
            }

            if (l < h) {
                nums[l] = nums[l] ^ nums[h];
                nums[h] = nums[l] ^ nums[h];
                nums[l] = nums[l] ^ nums[h];
            }
        }

        if (h != i) {
            nums[i] = nums[i] ^ nums[h];
            nums[h] = nums[i] ^ nums[h];
            nums[i] = nums[i] ^ nums[h];
        }

        return h;
    }

    public static int[] mergeSort(int[] nums) {
        mergeHelperMethod(nums, 0, nums.length-1);
        return nums;
    }

    private static void mergeHelperMethod(int[] nums, int start, int end) {
        if (start >= end){
            return;
        }
        int mid = start + (end - start)/2;
        mergeHelperMethod(nums, start, mid);
        mergeHelperMethod(nums, mid+1, end);
        merge(nums, start, mid, end);
    }


    private static void merge(int[] nums, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++)
            leftArray[i] = nums[left + i];
        for (int j = 0; j < n2; j++)
            rightArray[j] = nums[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                nums[k] = leftArray[i];
                i++;
            } else {
                nums[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            nums[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            nums[k] = rightArray[j];
            j++;
            k++;
        }
    }
}