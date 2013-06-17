/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 6/16/13
 * Time: 11:05 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class StringsOfPower318A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String s = in.next();
        int count = 0;
        int[] start = new int[200000];
        int x = 0;
        while (x >= 0 && x < s.length()) {
            x = s.indexOf("metal", x);
            if (x >= 0) {
                start[count++] = x;
            } else
                break;
            x++;
        }
        start[count] = s.length();

        int[] c = new int[count + 1];
        for (int i = count; i >= 0; i--) {
            c[i] = count - i;
        }

        x = 0;
        long ret = 0;
        int index = 0;
        while (x >= 0 && x < s.length()) {
            x = s.indexOf("heavy", x);
            if (x < 0) break;
            while (x > start[index])
                index++;
            //if (x < start[index]) {
            ret += c[index];
            //}
            x++;
        }
        System.out.println(ret);
    }
}
