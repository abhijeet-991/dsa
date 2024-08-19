package beginner_problems;

import java.util.Arrays;
import java.util.stream.IntStream;

public class BasicArrays {
    public int sum(int[] arr, int n) {
        return Arrays.stream(arr).sum();
    }

    public int countOdd(int[] arr, int n) {
        return (int) Arrays.stream(arr).filter(x -> x%2 != 0).count();
    }

    public void reverse(int[] arr, int n) {
        int start = 0, end = n-1;
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public boolean arraySortedOrNot(int[] arr, int n) {
        return IntStream.range(0, n-1).allMatch(i -> arr[i] <= arr[i+1]);
    }
}
