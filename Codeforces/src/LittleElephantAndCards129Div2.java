/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/1/13
 * Time: 11:16 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Arrays;
import java.util.Scanner;

public class LittleElephantAndCards129Div2 {
    static class Ele implements Comparable<Ele> {
        int val;
        int w;

        public int compareTo(Ele e) {
            if (e == null)
                return -1;
            if (this.val == e.val)
                return this.w - e.w;
            return this.val - e.val;
        }

        Ele(int v, int weight) {
            this.val = v;
            this.w = weight;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        Ele[] ele = new Ele[2 * n];
        int c = 0;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            ele[c++] = new Ele(x, 0);
            if (y != x)
                ele[c++] = new Ele(y, 1);
            else
                ele[c++] = new Ele(Integer.MAX_VALUE, 1);
        }
        Arrays.sort(ele);

        int min = Integer.MAX_VALUE;
        int m = (n + 1) / 2;
        int pre = ele[0].val;
        int x = 1, y = 0;
        for (int i = 1; i < c; i++) {
            if (ele[i].val > pre) {

            }
        }
        if (min > m)
            System.out.println(-1);
        else
            System.out.println(Math.max(0, min));
    }
}
