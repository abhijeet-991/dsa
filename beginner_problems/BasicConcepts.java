package beginner_problems;

import java.util.ArrayList;
import java.util.List;

public class BasicConcepts {
    public int countDigit(int n) {
        if (n == 0) {
            return 1;
        }
        int count = 0;
        while (n != 0) {
            n /= 10;
            count++;
        }
        return count;
    }

    public int countOddDigit(int n) {
        if (n == 0) {
            return 0;
        }
        int ans = 0;
        while (n != 0) {
            int ones = n%10;
            if ((ones & 1) == 1) {
                ans++;
            }
            n = n/10;
        }
        return ans;
    }

    public int reverseNumber(int n) {
        int ans = 0;
        while(n != 0) {
            int ones = n%10;
            ans = ans * 10 + ones;
            n = n/10;
        }
        return ans;
    }

    public boolean isPalindrome(int n) {
        return n == reverseNumber(n);
    }

    public int largestDigit(int n) {
        int ans = 0;
        while (n != 0) {
            ans = Math.max(ans, n%10);
            n = n/10;
        }
        return ans;
    }

    public int factorial(int n) {
        int ans = 1;
        for (int i = 1; i <= n; i++) {
            ans = ans * i;
        }
        return ans;
    }

    public boolean isArmstrong(int n) {
        int originalN = n;
        int numberOfDigits = String.valueOf(n).length();
        int sum = 0;

        while (n > 0) {
            int digit = n % 10;
            sum += (int) Math.pow(digit, numberOfDigits);
            n /= 10;
        }

        return sum == originalN;
    }

    public boolean isPerfect(int n) {
        int sum = 0;
        for (int i = 1; i < n; i++){
            if ((n % i) == 0) {
                sum = sum + i;
            }
        }
        return sum == n;
    }

    public boolean isPrime(int n) {
        for (int i=2;i<n;i++){
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int primeUptoN(int n) {
        if (n < 2) return 0;

        boolean[] sieve = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            sieve[i] = true;
        }

        for (int i = 2; i * i <= n; i++) {
            if (sieve[i]) {
                for (int j = i * i; j <= n; j += i) {
                    sieve[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (sieve[i]) {
                count++;
            }
        }
        return count;
    }

    public int GCD(int dividend, int divisor) {
        while (divisor != 0) {
            int remainder = dividend % divisor;
            dividend = divisor;
            divisor = remainder;
        }
        return dividend;
    }

    public int LCM(int a, int b) {
        return (a*b)/GCD(a,b);
    }

    public int[] divisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
            }
        }

        int[] result = new int[divisors.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = divisors.get(i);
        }

        return result;
    }
}
