/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/2/13
 * Time: 11:00 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class PoloThePenguinAndStrings {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int k = in.nextInt();
        char[] ans = new char[n];
        if (k > n) {
            System.out.println(-1);
            return;
        }

        Arrays.fill(ans, 'a');
        char ch = (char) ('a' + k - 1);
        for (int i = n - 1; i >= n - k + 2; i--) {
            ans[i] = ch--;
        }
        if (k > 1) {
            for (int i = 1; i < Math.min(n, n - k + 2); i += 2) {
                ans[i] = 'b';
            }
        }
        System.out.println(ans);
    }
}
