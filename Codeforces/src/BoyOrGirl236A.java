/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/6/13
 * Time: 10:56 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class BoyOrGirl236A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String name = in.next();
        int[] count = new int[26];
        int total = 0;
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (count[ch - 'a'] == 0) {
                total++;
                count[ch - 'a']++;
            }
        }

        if (total % 2 == 0) {
            System.out.println("CHAT WITH HER!");
        } else {
            System.out.println("IGNORE HIM!");
        }
    }
}
