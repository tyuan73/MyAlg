/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/28/13
 * Time: 3:36 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class SnapperChain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            int n = in.nextInt();
            int k = in.nextInt();
            int base = (1 << n) - 1;
            if ((k & base) == base)
                System.out.println("Case #" + i + ": ON");
            else
                System.out.println("Case #" + i + ": OFF");
        }
    }
}
