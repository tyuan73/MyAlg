package projecteuler1;

/**
 * Created by yuantian on 7/10/14.
 */

/*
This problem is a programming version of Problem 5 from projecteuler.net

2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
What is the smallest positive number that is evenly divisible(divisble with no remainder) by all of the numbers from 1 to N?

Input Format
First line contains T that denotes the number of test cases. This is followed by T lines, each containing an integer, N.

Output Format
Print the required answer for each test case.

Constraints
1≤T≤10
1≤N≤40

Sample Input

2
3
10
Sample Output

6
2520
 */

import java.util.*;
public class SmallestMultiple {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] primes = {2,3,5,7,11,13,17,19,23,29,31,37};
        int[][] dp = new int[41][12];
        for(int i = 2; i <= 40; i++) {
            for(int j = 0; j < 12; j++) {
                if (i < primes[j])
                    break;
                int x = i;
                int c = 0;
                while(x % primes[j] == 0) {
                    c++;
                    x /= primes[j];
                }
                dp[i][j] = Math.max(dp[i-1][j], c);
            }
        }

        /*
        for(int[] r : dp) {
            for(int x : r)
                System.out.print(" " + x);
            System.out.println();
        }
        */

        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            long x = 1;
            for(int i = 0; i < 12; i++) {
                int p = dp[n][i];
                while(p > 0) {
                    x *= primes[i];
                    p--;
                }
            }
            System.out.println(x);
        }
    }
}
