package tools;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/19/15
 * Time: 11:57 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Random;

/**
 * 0-based segment tree
 * len("st") = 4 * len("val")
 */
public class SegmentTree {
    private int[] val, st;
    private int len;

    SegmentTree(int[] val) {
        this.len = val.length;
        this.val = new int[this.len];
        System.arraycopy(val, 0, this.val, 0, this.len);
        this.st = new int[4 * this.len];
        build(0, 0, this.len - 1);
    }

    private int build(int idx, int l, int r) {
        if (l == r) {
            return st[idx] = l;
        }
        int lv = build((idx << 1) + 1, l, (l + r) / 2);
        int rv = build((idx << 1) + 2, (l + r) / 2 + 1, r);
        st[idx] = val[lv] <= val[rv] ? lv : rv;
        return st[idx];
    }

    private int rmq(int idx, int from, int to, int rl, int rr) {
        if (rl > to || rr < from) return -1;
        if (rl <= from && rr >= to) return st[idx];

        int lv = rmq((idx << 1) + 1, from, (from + to) / 2, rl, rr);
        int rv = rmq((idx << 1) + 2, (from + to) / 2 + 1, to, rl, rr);
        if (lv == -1) return rv;
        if (rv == -1) return lv;
        return val[lv] <= val[rv] ? lv : rv;
    }

    public int rmq(int rl, int rr) {
        return rmq(0, 0, len - 1, rl, rr);
    }

    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        int arr[] = {17, 18, 13, 19, 15, 11, 20, 5, 56};
        SegmentTree stree = new SegmentTree(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(stree.val[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < stree.st.length; i++) {
            System.out.print(stree.st[i] + " ");
        }
        System.out.println();

        System.out.println("rmq(1, 3) = " + stree.rmq(1, 3));
        System.out.println("rmq(4, 6) = " + stree.rmq(4, 6));
        System.out.println("rmq(0, 7) = " + stree.rmq(0, 7));
        System.out.println("rmq(2, 3) = " + stree.rmq(2, 3));
        System.out.println("rmq(0, 8) = " + stree.rmq(0, 8));
        System.out.println("rmq(0, 0) = " + stree.rmq(0, 0));

        int n = 1000;
        int[] a = new int[n];
        Random    rnd = new Random();
        for(int i = 0; i < n; i++) {
            a[i] = rnd.nextInt(100000);
        }

        SegmentTree st = new SegmentTree(a);
        int testcase = 10000;
        boolean ok = true;
        while(testcase-- > 0) {
            int x = rnd.nextInt(n);
            int y = rnd.nextInt(n);
            if (x > y) {
                int z = x;
                x = y;
                y = z;
            }

            int ret = st.rmq(x, y);
            int myret = x;
            for(int i = x; i <= y; i++) {
                if (a[i] < a[myret])
                    myret = i;
            }
            if (ret != myret) {
                ok = false;

                for(int d : a)
                    System.out.print(" " + d);
                System.out.println();
                System.out.println(" x = " + x + " y = " + y);
                System.out.println("SegTree:" + ret);
                System.out.println("My answer:" + myret);

                break;
            }
        }
        System.out.println(ok ? "good!" : "wrong!");

    }
}
