package tools;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 10/28/14
 * Time: 11:21 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class FenwickTree {

    static private int fenwickSum(int[] ft, int i) {
        int ret = 0;
        for (; i > 0; i -= i & -i)
            ret += ft[i];
        return ret;
    }

    static private void fenwickAdd(int[] ft, int i, int val) {
        for (; i < ft.length; i += i & -i)
            ft[i] += val;
    }

    // assume i < j
    static private int fenwickDiff(int[] ft, int i, int j) {
        assert i <= j;
        int ret = 0;
        while(i != j) {
            if (i < j) {
                ret += ft[j];
                j -= j & -j;
            } else {
                ret -= ft[i];
                i -= i & -i;
            }
        }
        return ret;
    }

    static private int readSingle(int[] ft, int i) {
        int sum = ft[i]; // sum will be decreased
        if (i > 0) { // special case
            int z = i - (i & -i); // make z first
            i--; // idx is no important any more, so instead y, you can use idx
            while (i != z) { // at some iteration idx (y) will become z
                sum -= ft[i];
                // substruct tree frequency which is between y and "the same path"
                i -= (i & -i);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print(i + "->");
            System.out.println(i + (i & -i));
        }
    }
}
