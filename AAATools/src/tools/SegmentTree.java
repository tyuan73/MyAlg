package tools;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/19/15
 * Time: 11:57 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class SegmentTree {
    private int[] val, st;
    private int len;

    SegmentTree(int[] val) {
        this.len = val.length;
        this.val = new int[this.len];
        System.arraycopy(val, 0, this.val, 0, this.len);
        this.st = new int[this.len];
        build(0, 0, this.len - 1);
    }

    private int build(int idx, int l, int r) {
        if (l == r) {
            return l;
        }
        int lv = build((idx << 1) + 1, l, (l + r) / 2);
        int rv = build((idx << 1) + 2, (l + r) / 2 + 1, r);
        st[idx] = val[lv] <= val[rv] ? lv : rv;
        return st[idx];
    }

    private int rmq(int idx, int from, int to, int rl, int rr) {
        if (from == to) return from;
        if (rl > to || rr < from) return -1;
        if (rl == rr) return st[rl];

        if (rl >= from && rr <= to) return st[idx];

        int lv = rmq((idx << 1) + 1, from, to, rl, (rl + rr) / 2);
        int rv = rmq((idx << 1) + 2, from, to, (rl + rr) / 2 + 1, rr);
        if (lv == -1) return rv;
        if (rv == -1) return lv;
        return val[lv] <= val[rv] ? lv : rv;
    }

    public int rmq(int from, int to) {
        return rmq(0, from, to, 0, len - 1);
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
    }
}
