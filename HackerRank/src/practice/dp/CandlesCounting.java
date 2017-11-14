package practice.dp;

import java.util.*;

public class CandlesCounting {
    final static long P = 1000000007l;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), k = in.nextInt();
        int[] h = new int[n], c = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = in.nextInt();
            c[i] = 1 << (in.nextInt() - 1);
        }

        long[] bit = new long[50001];
        long ans = 0;
        boolean[] plus = getPlus(k);

        for (int mask = 1; mask < 1 << k; mask++) {
            Arrays.fill(bit, 0);
            long count = 0;
            for (int j = 0; j < n; j++) {
                if ((c[j] & mask) == 0) continue;
                long x = sum(bit, h[j] - 1);
                add(bit, h[j], x + 1);
                count = (count + x + 1) % P;
            }
            if (plus[mask])
                ans = (ans + count) % P;
            else
                ans = (ans - count) % P;
            if (ans < 0) ans += P;
        }
        System.out.println(ans);
    }

    private static void add(long[] bit, int i, long val) {
        for (; i < bit.length; i += i & -i)
            bit[i] = (bit[i] + val) % P;
    }

    private static long sum(long[] bit, int i) {
        long ret = 0;
        for (; i > 0; i -= i & -i)
            ret = (ret + bit[i]) % P;
        return ret;
    }

    private static boolean[] getPlus(int k) {
        boolean[] res = new boolean[1 << k], flag = new boolean[k + 1];
        for (int i = k; i >= 0; i -= 2) {
            flag[i] = true;
        }
        for (int i = 1; i < 1 << k; i++) {
            int count = 0, j = i;
            while (j > 0) {
                count++;
                j -= j & -j;
            }
            res[i] = flag[count];
        }
        return res;
    }
}
