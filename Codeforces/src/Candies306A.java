/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/28/13
 * Time: 10:35 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class Candies306A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int base = n / m;
        int rem = m - (n % m);
        for (int i = 0; i < m; i++) {
            if (i < rem)
                System.out.print(base + " ");
            else
                System.out.print((base + 1) + " ");
        }
    }
}
