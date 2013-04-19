/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 4/17/13
 * Time: 9:33 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class IceCreamParlor {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        while (t-- > 0) {
            int c = in.nextInt();
            int l = in.nextInt();
            int[] index = new int[10001];
            for (int i = 0; i < l; i++) {
                int a = in.nextInt();
                if (c-a >= 0 && index[c - a] != 0) {
                    System.out.println(index[c - a] + " " + (i + 1));
                }
                index[a] = i+1;
            }
        }
    }
}
