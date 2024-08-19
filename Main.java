import beginner_problems.BasicArrays;
import beginner_problems.BasicConcepts;
import beginner_problems.Patterns;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        patters();
//        basicConcepts();
        basicArray();
    }

    private static void basicArray() {
        BasicArrays basicArrays = new BasicArrays();
        System.out.println(basicArrays.sum(new int[]{1, 2, 3, 4}, 4));
        System.out.println(basicArrays.countOdd(new int[]{1,2,3,4,5,6}, 6));
        int[] arr = new int[]{1,2,3,4,5};
        basicArrays.reverse(arr, 5);
        Arrays.stream(arr).forEach(System.out::print);
        System.out.println(basicArrays.arraySortedOrNot(new int[]{1,2,3,4}, 4));
    }

    private static void basicConcepts() {
        BasicConcepts basicConcepts = new BasicConcepts();
        System.out.println(basicConcepts.GCD(12, 6));
        System.out.println(basicConcepts.countDigit(123));
        System.out.println(basicConcepts.countOddDigit(12125297));
        System.out.println(basicConcepts.isPalindrome(121));
        System.out.println(basicConcepts.largestDigit(15215219));
        System.out.println(basicConcepts.factorial(5));
        System.out.println(basicConcepts.isArmstrong(123));
        System.out.println(basicConcepts.isPerfect(52));
        System.out.println(basicConcepts.isPrime(5));
        System.out.println(basicConcepts.LCM(12,6));
        System.out.println(basicConcepts.primeUptoN(1000));
        Arrays.stream(basicConcepts.divisors(12)).forEach(System.out::println);
    }

    private static void patters() {
        Patterns patterns = new Patterns();
        patterns.pattern1(5);
        patterns.pattern2(5);
        patterns.pattern3(5);
        patterns.pattern4(5);
        patterns.pattern5(5);
        patterns.pattern6(5);
        patterns.pattern7(5);
        patterns.pattern8(5);
        patterns.pattern9(5);
        patterns.pattern10(5);
        patterns.pattern11(5);
        patterns.pattern12(5);
        patterns.pattern13(5);
        patterns.pattern14(5);
        patterns.pattern15(5);
        patterns.pattern16(5);
        patterns.pattern17(5);
        patterns.pattern18(5);
        patterns.pattern19(5);
        patterns.pattern20(5);
        patterns.pattern21(5);
        patterns.pattern22(5);
    }
}
