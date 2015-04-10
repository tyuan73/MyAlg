package p11988;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/9/15
 * Time: 10:30 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            String line = "[" + in.nextLine();
            int n = line.length();
            char[] ans = new char[n];
            int l = 0, r = n - 1;
            int idx = n - 1, preE = n, preS = n;
            for (; idx >= 0; idx--) {
                if (line.charAt(idx) == ']') {
                    preE = idx;
                } else if (line.charAt(idx) == '[') {
                    for (int i = idx; i < preE; i++) {
                        ans[l++] = line.charAt(i);
                    }
                    for (int i = preS - 1; i >= preE; i--) {
                        ans[r--] = line.charAt(i);
                    }
                    preS = idx;
                    preE = idx;
                }
            }
            for (char c : ans) {
                if (c != '[' && c != ']')
                    System.out.print(c);
            }
            System.out.println();
        }
    }
}