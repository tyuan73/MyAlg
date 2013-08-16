/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 8/15/13
 * Time: 11:07 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class MalekDanceClub319A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String str = in.next();
        long ans = 0;
        long pow = 1;
        for (int i = str.length() - 1; i >= 0; i--) {
            if ('0' == str.charAt(i)) {
                ans = (ans * 2) % 1000000007;
            } else {
                ans = (ans * 2 + pow) % 1000000007;
            }
            pow = (pow << 2) % 1000000007;
        }
        System.out.println(ans);
    }
}
