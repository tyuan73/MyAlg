/**
 * Created by yuantian on 1/13/16.
 */

import java.util.*;

public class OJ327CountOfRangeSum {
    class Ele {
        long val;
        int idx;

        Ele(long v, int i) {
            this.val = v;
            this.idx = i;
        }
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        int len = nums.length;
        //long[][] sum = new long[len + 1][3];
        Ele[] sorted = new Ele[3 * len + 3];
        sorted[0] = new Ele(0, 0);
        sorted[1] = new Ele(lower, 1);
        sorted[2] = new Ele(upper, 2);
        long sum = 0;
        for (int i = 0, j = 3; i < len; i++) {
            sum += nums[i];
            sorted[j] = new Ele(sum, j++);
            sorted[j] = new Ele(sum + lower, j++);
            sorted[j] = new Ele(sum + upper, j++);
        }

        Arrays.sort(sorted, new Comparator<Ele>() {
            public int compare(Ele e1, Ele e2) {
                if (e1.val == e2.val)
                    return 0;
                else if (e1.val > e2.val)
                    return 1;
                else
                    return -1;
            }
        });

        int max = 1;
        int[] after = new int[3 * len + 3];
        for (int i = 0; i < 3 * len + 3; i++) {
            if (i > 0 && sorted[i].val != sorted[i - 1].val)
                max++;
            after[sorted[i].idx] = max;
        }

        int[] fw = new int[max + 1];
        int total = 0;
        for (int i = 3 * len; i >= 0; i -= 3) {
            int u = after[i + 2], l = after[i + 1] - 1;
            while (u != l) {
                if (u > l) {
                    total += fw[u];
                    u -= u & -u;
                } else {
                    total -= fw[l];
                    l -= l & -l;
                }
            }
            for (int j = after[i]; j <= max; j += j & -j)
                fw[j]++;
        }
        return total;
    }

    public static void main(String[] args) {
        OJ327CountOfRangeSum run = new OJ327CountOfRangeSum();
        int[] input = {2147483647, -2147483648, -1, 0};
        System.out.println(run.countRangeSum(input, -1, 0));
    }

    public void printArray(long[][] a) {
        for (long[] b : a) {
            for (long c : b)
                System.out.print(c + " ");
            System.out.println();
        }
    }
}
