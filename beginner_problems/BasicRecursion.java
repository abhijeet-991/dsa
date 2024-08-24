package beginner_problems;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class BasicRecursion {

    public static boolean isSorted(ArrayList<Integer> nums) {
        return helper3(nums, nums.size()-1);
    }

    public static boolean helper3(ArrayList<Integer> nums, int idx) {
        if (idx == 0){
            return true;
        }
        if (nums.get(idx) < nums.get(idx - 1)) {
            return false;
        }
        return helper3(nums, idx - 1);
    }

    public static void reverseArray(ArrayList<Integer> arrayList, int n) {
        int start = 0;
        int end = n-1;
        helper(arrayList, start, end);
    }

    private static void helper(ArrayList<Integer> arrayList, int start, int end) {
        if (start > end) {
            return;
        }
        int temp = arrayList.get(start);
        arrayList.set(start, arrayList.get(end));
        arrayList.set(end, temp);
        helper(arrayList, start + 1, end -1);
    }

    public int addDigits(int num) {
        if (num < 10) {
            return num;
        }

        return addDigits(this.solve(num, 0));
    }

    private int solve(int num, int ans) {
        if (num <= 0) {
            return ans;
        }
        return solve(num/10, ans + num%10);
    }

    public static void countZeroes(int n, AtomicInteger ans) {
        if (n < 10) {
            if (n%10 == 0) {
                ans.incrementAndGet();
            }
            return;
        }
        if (n % 10 == 0) ans.incrementAndGet();
        countZeroes(n/10, ans);
    }

    public int factorial(int n) {
        if (n == 1) return 1;
        return n * factorial(n-1);
    }

    public static int numberOfSteps(int n, int steps) {
        if (n <= 0) {
            return steps;
        }
        if (n % 2 == 0) {
            return numberOfSteps(n/2, ++steps);
        }
        return numberOfSteps(n-1, ++steps);
    }

    public static void reverseString(Vector<Character> s) {
        helper2(s, 0, s.size()-1);
    }

    private static void helper2(Vector<Character> ans, int s, int e) {
        if (s > e) {
            return;
        }
        char temp = ans.get(s);
        ans.set(s, ans.get(e));
        ans.set(e, temp);
        helper2(ans, s+1, e-1);
    }

    public static boolean palindromeCheck(String s) {
        return solvePallindrome(s, 0, s.length()-1);
    }

    private static boolean solvePallindrome(String s, int start, int end) {
        if (start >= end) {
            return true;
        }
        char c1 = s.charAt(start);
        char c2 = s.charAt(end);
        if (c1 != c2) {
            return false;
        }
        return solvePallindrome(s, start + 1, end-1);
    }

    public static int arraySum(int[] nums) {
        return help(nums, 0);
    }

    private static int help(int[] nums, int index) {
        if (index >= nums.length) {
            return 0;
        }
        return nums[index] + help(nums, index+1);
    }

    public static boolean checkPrime(int num) {
        if (num < 2) {
            return false;
        }
        return checkPrimeHelper(num, 2);
    }

    private static boolean checkPrimeHelper(int num, int i) {

        if (num % i == 0) {
            return false;
        }
        if (i > num) {
            return true;
        }
        return checkPrimeHelper(num, i + 1);
    }


    public static void main(String[] args) {
//        AtomicInteger ans = new AtomicInteger(0);
//        countZeroes(320502, ans);
//        int temp = numberOfSteps(14, 0);
        ArrayList<Integer> list = new ArrayList<>();
//        Vector<Character> vector = new Vector<>();
//        vector.add('a');
//        vector.add('e');
//        vector.add('i');
//        vector.add('o');
//        reverseString(vector);
//
//        System.out.println(vector);
//
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(1);
//        reverseArray(list, 5);
//        System.out.println(list);

//        String s = "racecar";
//        System.out.println(palindromeCheck(s));

//        int[] arr = new int[] {1,2,3,4,5};
//        System.out.println(arraySum(arr));
//        System.out.println(checkPrime(49));
        System.out.println(isSorted(list));
    }
}