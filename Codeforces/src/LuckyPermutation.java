/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/24/13
 * Time: 12:26 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

/**
 * for any i, assume the value is p[i], we know
 * p[i] = p[i],
 * p[p[i]] = n-i+1,
 * p[n-i+1] = n - p[i] + 1,
 * p[n-p[i]+1] = n-(n-i+1)+1 = i
 * p[i] = n- (n-p[i]+1) + 1 = p[i]
 * <p/>
 * so indexes i, p[i], p[n-i+1], p[n-p[i]+1] are a group. They exchange values among themselves.
 * the only exception is when i = (n+1)/2 where n is an odd. In this case, i = p[i] = p[n-i+1] = p[n-p[i]+1].
 * For example:
 * 1  2  3  4  5
 * X  X  3  X  X
 * So,  in order to get lucky permutations, n has to be equal to 4k or 4k+1
 * <p/>
 * There are many ways to choose groups, but an easy way is to choose p[i] = i+1.
 * That is:
 * p[i] = i+1,
 * p[i+1] = n-i+1
 * p[n-i+1] = n-(i+1)+1 = n-i
 * p[n-i] = n-(n-i+1)+1 = i
 * <p/>
 * Note: here i is 1-based. But in java, array index is 0-base, so you will see a little transfer in code below.
 */
public class LuckyPermutation {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] p = new int[n];
        int r = n % 4;
        if (r == 2 || r == 3) {
            System.out.println(-1);
            return;
        }
        for (int i = 1, j = n; i < j; i += 2, j -= 2) {
            p[i - 1] = i + 1;
            p[i] = j;
            p[j - 1] = j - 1;
            p[j - 2] = i;
        }
        if (r == 1)
            p[(n - 1) / 2] = (n + 1) / 2;
        System.out.print(p[0]);
        for (int i = 1; i < n; i++)
            System.out.print(" " + p[i]);
        System.out.println();
    }
}
