package addeparhackathon;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 6/28/14
 * Time: 11:59 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*


For each positive integer n, denote by d(n) the number of positive divisors of n. A positive integer n is said to be special if there is no k<n with d(k)=d(n). Compute the sum of all special numbers no greater than N.

Input: A single integer N.

Output: A single integer: the sum of all special numbers no greater than N.

Constraints: 1≤N≤100000.

Scoring:

    Test cases worth a total of 60 points will have N≤6000.

Sample Input:
4

Sample Output:
7

Explanation: 3 is not special since d(2)=d(3)=2. 1, 2, and 4 are special, so the answer is 1+2+4=7.

 */
import java .util.*;

public class SpecialNumbers {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (n == 1) {
            System.out.println(1);
            return;
        }
        if (n == 2 || n == 3) {
            System.out.println(3);
            return;
        }

        int[] a = new int[n+1];
        boolean[] b = new boolean[n];
        int output = 1;
        for(int i = 2; i <= n; i++) {
            if (!b[a[i]]) {
                b[a[i]] = true;
                output += i;
            }
            for(int j = i+i; j <= n; j += i) {
                a[j]++;
            }
        }

        System.out.println(output);
    }
}

/*
test case:
input:
20

output:
41
 */