/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/25/13
 * Time: 10:50 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class Coach181div2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int[] g = new int[n];
        int id = 1;
        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            if (g[x] == 0 && g[y] == 0) {
                g[x] = g[y] = id++;
            } else if (g[x] == 0 || g[y] == 0) {
                int gid = g[x] == 0 ? g[y] : g[x];
                g[x] = g[y] = gid;
            } else if (g[x] != g[y]) {
                int gid = g[x];
                for (int j = 0; j < n; j++)
                    if (g[j] == gid)
                        g[j] = g[y];
            }
        }

        int[] ids = new int[id];
        for (int i = 0; i < n; i++) {
            ids[g[i]]++;
            if (g[i] > 0 && ids[g[i]] > 3) {
                System.out.println(-1);
                return;
            }
        }
        int r = 0;
        for (int i = 1; i < id; i++) {
            if (ids[i] != 0)
                r += 3 - ids[i];
        }
        if (r > ids[0]) {
            System.out.println(-1);
            return;
        }

        int index = findNextZero(g, -1);
        for (int i = id - 1; i > 0; i--) {
            int count = 0;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (g[j] == i) {
                    sb.append(" ").append(j + 1);
                    count++;
                }
            }
            if (count != 0) {
                for (int j = count; j < 3; j++) {
                    if (index == -1) {
                        System.out.println(-1);
                        return;
                    }

                    sb.append(" ").append(index + 1);
                    index = findNextZero(g, index);
                }
                System.out.println(sb.substring(1));
            }
        }
        while (index != -1) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                //System.out.print((index + 1) + " ");
                sb.append(" ").append(index + 1);
                index = findNextZero(g, index);
            }
            System.out.println(sb.substring(1));
        }

    }

    static int findNextZero(int[] a, int index) {
        for (int i = index + 1; i < a.length; i++) {
            if (a[i] == 0)
                return i;
        }
        return -1;
    }
}
