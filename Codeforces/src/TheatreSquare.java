/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/10/13
 * Time: 10:26 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class TheatreSquare {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int a = in.nextInt();
        long x = (n + a - 1) / a;
        long y = (m + a - 1) / a;
        System.out.println(x * y);
    }
}
