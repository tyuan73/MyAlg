package problem440.c;

/**
 * Created by yuantian on 6/12/14.
 */

import java.util.*;

public class OneBasedArithmetic {
    static long[] t = null;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        long num = in.nextLong();
        t = new long[17];
        t[1] = 1;
        for(int i = 2; i < 17; i++) {
            t[i] = t[i-1] * 10 + 1;
        }

        System.out.println(min(num));
    }

    static int min(long num) {
        System.out.println("num = " + num);
        int idx = find(num);
        if (num == t[idx]) {
            return idx;
        } else if (num % t[idx-1] == 0) {
            return (int) (num/t[idx-1] * (idx-1));
        }

        int ret1 = idx + min(t[idx] - num);
        int c = (int) (num / t[idx-1]);
        int ret2 = c * (idx-1) + min(num - c*t[idx-1]);
        int ret3 = (c+1) * (idx-1) + min((c+1) * t[idx-1] - num);

        return Math.min(ret1, Math.min(ret2, ret3));
    }

    static int find(long num) {
        int l = 0, r = 16;
        while(l < r) {
            int mid = (l + r) / 2;
            if (t[mid] < num)
                l = mid + 1;
            else
                r = mid;
        }
        return r;
    }
}
