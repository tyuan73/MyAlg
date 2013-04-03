/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/31/13
 * Time: 11:41 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class MainSequence176C {
    static int[] x;
    static int j;
    static int[] d;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        x = new int[n + 2];
        for (int i = 1; i <= n; i++)
            x[i] = in.nextInt();
        x[0] = 1000000001;
        x[n + 1] = -1000000001;
        int t = in.nextInt();
        while (t-- > 0) {
            int k = in.nextInt();
            x[k] = -x[k];
        }

        d = new int[n + 2];
        j = 0;
        for (int i = 1; i <= n + 1; i++) {
            int a = x[i];
            if (a > 0) {
                d[++j] = i;
            } else {
                while (j >= 0 && x[d[j]] != -a) {
                    if (!popStack()) {
                        System.out.println("NO");
                        return;
                    }
                }
                if (j < 0) {
                    System.out.println("NO");
                    return;
                }
                j--;
            }
        }

        System.out.println("YES");
        System.out.print(x[1]);
        for (int i = 2; i <= n; i++) {
            System.out.print(" " + x[i]);
        }
        System.out.println();
    }

    static boolean popStack() {
        if (j >= 0) {
            int e = x[d[j]];
            x[d[j]] = -x[d[j]];
            j--;
            if (j >= 0) {
                while (j >= 0 && x[d[j]] != e) {
                    if (!popStack())
                        return false;
                }
                if (j >= 0 && x[d[j]] == e) {
                    j--;
                    return true;
                }
            }
        }
        return false;
    }
}
