/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/20/13
 * Time: 11:51 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class SqrtX {
    public int sqrt(int x) {
        long r1 = 0;
        long r2 = x;
        while (r1 < r2) {
            long m = (r1 + r2 + 1) / 2;
            if (m * m == x)
                return (int) m;
            if (m * m > x) {
                r2 = m - 1;
            } else
                r1 = m;
        }

        return (int) r2;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }
}
