/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/19/13
 * Time: 12:01 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class ParityGame {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String a = in.next();
        String b = in.next();

        int c1 = 0, c2 = 0;
        for (int i = 0; i < a.length(); i++)
            if (a.charAt(i) == '1')
                c1++;
        for (int i = 0; i < b.length(); i++)
            if (b.charAt(i) == '1')
                c2++;

        c1 += c1 & 1;
        if (c1 >= c2)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
