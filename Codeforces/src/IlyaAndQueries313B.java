/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/30/13
 * Time: 10:51 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class IlyaAndQueries313B {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String s = in.next();
        int t = in.nextInt();

        int[] count = new int[s.length() + 1];
        int total = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == s.charAt(i)) {
                total++;
            }
            count[i] = total;
        }

        while (t-- > 0) {
            int l = in.nextInt();
            int r = in.nextInt();
            System.out.println(count[r - 1] - count[l - 1]);
        }
    }
}
