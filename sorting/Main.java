package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<int[]> testCases = new ArrayList<>();

//        testCases.add(new int[]{});
//        testCases.add(new int[]{5});
//        testCases.add(new int[]{7, 7, 7});
//        testCases.add(new int[]{1, 2, 3, 4});
//        testCases.add(new int[]{4, 3, 2, 1});
//        testCases.add(new int[]{5, 4, 3, 2, 1});
//        testCases.add(new int[]{1000000, 5000000, 1000000000});
//        testCases.add(new int[]{-5, -1, -15, 0, 5});
//        testCases.add(new int[]{1, 1, 1, 1, 1, 2, 2, 2, 2});
        testCases.add(new int[]{3, 7, 2, 8, 1});

        for (int i = 0; i < testCases.size(); i++) {
            int[] testCase = testCases.get(i);
//            int[] sortedArray = SortUtil.selectionSort(testCase.clone());
//            int[] sortedArray = SortUtil.bubbleSort(testCase.clone());
//            int[] sortedArray = SortUtil.insertionSort(testCase.clone());
//            int[] sortedArray = SortUtil.quickSort(testCase.clone());
            int[] sortedArray = SortUtil.mergeSort(testCase.clone());
            System.out.println("Test Case " + (i + 1) + " Original: " + Arrays.toString(testCase));
            System.out.println("Test Case " + (i + 1) + " Sorted: " + Arrays.toString(sortedArray));
        }
    }
}