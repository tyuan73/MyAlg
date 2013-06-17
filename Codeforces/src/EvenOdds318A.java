/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 6/16/13
 * Time: 10:52 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class EvenOdds318A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        long n = in.nextLong();
        long k = in.nextLong();

        long h = (n + 1) / 2;
        if (k <= h) {
            System.out.println(k * 2 - 1);
        } else {
            System.out.println((k - h) * 2);
        }

    }
}
