/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/30/13
 * Time: 10:39 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class IlyaAndBankAccount313A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        if (n > 0) {
            System.out.println(n);
            return;
        }

        n = -n;
        int l1 = n % 10;
        int l2 = (n % 100) / 10;
        if (l1 > l2) {
            n = -(n / 10);
        } else {
            n = -(n / 100 * 10 + l1);
        }
        System.out.println(n);

    }
}
