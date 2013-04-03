/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/2/13
 * Time: 11:41 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class PoloThePenguinAndSegments {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int k = in.nextInt();

        int total = 0;
        while (n-- > 0) {
            int x = in.nextInt();
            int y = in.nextInt();
            total += y - x + 1;
        }
        if ((total % k) == 0)
            System.out.println(0);
        else
            System.out.println((total / k + 1) * k - total);
    }
}
