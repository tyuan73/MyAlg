/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/22/13
 * Time: 10:43 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class KsushaTheSquirrel {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int k = in.nextInt();
        String road = in.next();
        int max = 0;
        int cur = 0;
        for (int i = 1; i < n; i++) {
            if (road.charAt(i) == '#') {
                cur++;
            } else {
                max = Math.max(max, cur);
                cur = 0;
            }
        }
        if (max >= k)
            System.out.println("NO");
        else
            System.out.println("YES");
    }
}
