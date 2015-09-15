package problemset568;


/**
 * Created by yuantian on 9/4/15.
 */

import java.util.Arrays;
import java.util.Scanner;

public class PrimesOrPalindromes568A {
    final static int N = 1200000;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long start = System.currentTimeMillis();
        int[] prime = new int[N];
        Arrays.fill(prime, 1);
        prime[0] = 0;
        prime[1] = 0;
        for (int i = 2; i < N; i++) {
            if (prime[i] == 1) {
                for (int j = i + i; j < N; j += i) {
                    prime[j] = 0;
                }
            }
        }
        for (int i = 3; i < N; i++) {
            prime[i] += prime[i - 1];
        }

        int[] palindrome = new int[N];
        for (int i = 1; i < N; i++) {
            if (isPalindrome(i)) {
                palindrome[i] = palindrome[i - 1] + 1;
            } else {
                palindrome[i] = palindrome[i - 1];
            }
        }

        int p = in.nextInt(), q = in.nextInt();
        for (int i = N - 1; i >= 1; i--) {
            if (q * prime[i] <= p * palindrome[i]) {
                System.out.println(i);
                return;
            }
        }
        System.out.println("Palindromic tree is better than splay tree");
        //System.out.println(" run time : " + (System.currentTimeMillis() - start));

    }

    static boolean isPalindrome(int x) {
        if ((x % 10) == 0) return false;
        int rev = 0;
        while (rev < x) {
            rev = rev * 10 + (x % 10);
            if (rev == x)
                return true;
            x /= 10;
        }
        return rev == x;
    }
}
