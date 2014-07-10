package projecteuler1;

/**
 * Created by yuantian on 7/10/14.
 */

/*
This problem is a programming version of Problem 4 from projecteuler.net

A palindromic number reads the same both ways. The smallest 6 digit palindrome made from the product of two 3-digit numbers is 101101=143×707.

Find the largest palindrome made from the product of two 3-digit numbers which is less than N.

Input Format
First line contains T that denotes the number of test cases. This is followed by T lines, each containing an integer, N.

Output Format
Print the required answer for each test case in a new line.

Constraints
1≤T<100
101101<N<1000000
Sample Input

2
101110
800000
Sample Output

101101
793397
 */

import java.util.*;

public class LargestPalindromeProduct {
    static public void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt()-1;
            int max = 101101;
            for(int i = 102; i < 999; i++) {
                for(int j = Math.min(n/i, 999); j > max/i; j--) {
                    int c = i*j;
                    if (isPalindrome(c)) {
                        max = Math.max(max, c);
                        break;
                    }
                }
            }
            System.out.println(max);
        }
    }

    static boolean isPalindrome(int x) {
        int y = x;
        int z = 0;
        while(y > 0) {
            z = z*10 + (y%10);
            y /= 10;
        }
        return z == x;
    }
}
