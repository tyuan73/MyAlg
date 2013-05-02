/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/29/13
 * Time: 10:22 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class LuckyString84Div2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        char[] res = new char[n];
        char[] chs = {'a', 'b', 'c', 'd'};
        for (char ch : chs) {
            for (int i = ch - 'a'; i < n; i += 4) {
                res[i] = ch;
            }
        }
        System.out.println(res);
    }
}
