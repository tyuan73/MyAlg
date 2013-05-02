/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/2/13
 * Time: 11:04 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class TestingPantsForSadness80Div2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        long total = 0;
        for(int i = 0; i < n; i++) {
            long x = in.nextLong();
            total += (x-1) * i + x;
        }
        System.out.println(total);
    }
}
