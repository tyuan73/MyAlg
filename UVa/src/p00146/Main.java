package p00146;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/4/15
 * Time: 11:33 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = null;
        while (!(str = in.nextLine()).equals("#")) {
            char[] ans = str.toCharArray();
            int p1 = -1;
            for (int i = ans.length - 2; i >= 0; i--) {
                if (ans[i] < ans[i + 1]) {
                    p1 = i;
                    break;
                }
            }

            if (p1 == -1) {
                System.out.println("No Successor");
                continue;
            }

            int p2 = ans.length - 1;
            while (ans[p2] <= ans[p1]) p2--;

            swap(ans, p1, p2);

            for (int l = p1 + 1, r = ans.length - 1; l < r; l++, r--)
                swap(ans, l, r);
            System.out.println(ans);
        }
    }

    static void swap(char[] str, int l, int r) {
        char ch = str[l];
        str[l] = str[r];
        str[r] = ch;
    }
}
