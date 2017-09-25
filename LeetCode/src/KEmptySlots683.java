/*

*/

import java.util.*;
import java.io.*;

public class KEmptySlots683 {
    /**
     * First try. O(n * lg(n))
     */
    public int kEmptySlots(int[] flowers, int k) {
        int n = flowers.length;
        int[] bit = new int[n + 1];
        boolean[] bed = new boolean[n + 1];
        for (int i = 0; i < flowers.length; i++) {
            int pos = flowers[i];
            if (pos > k + 1 && bed[pos - k - 1]) {
                if (sum(bit, pos) == sum(bit, pos - k - 1))
                    return i + 1;
            }
            add(bit, pos);
            bed[pos] = true;
            if (pos + k < n && bed[pos + k + 1]) {
                if (sum(bit, pos) == sum(bit, pos + k))
                    return i + 1;
            }
        }
        return -1;
    }

    private void add(int[] bit, int idx) {
        for (; idx < bit.length; idx += idx & -idx)
            bit[idx]++;
    }

    private int sum(int[] bit, int idx) {
        int ret = 0;
        for (; idx > 0; idx -= idx & -idx) {
            ret += bit[idx];
        }
        return ret;
    }

    /**
     * another way to do BIT O(n*lg(n))
     */
    public int kEmptySlots1(int[] flowers, int k) {
        int n = flowers.length;
        int[] bit = new int[n + 1];
        boolean[] bed = new boolean[n + 1];
        for (int i = 0; i < flowers.length; i++) {
            int pos = flowers[i];
            add1(bit, pos);
            bed[pos] = true;
            if (pos > k + 1 && bed[pos - k - 1] && same(bit, pos - k - 1, pos - 1))
                return i + 1;
            if (pos + k < n && bed[pos + k + 1] && same(bit, pos, pos + k))
                return i + 1;
        }
        return -1;
    }

    private void add1(int[] bit, int idx) {
        for (; idx < bit.length; idx += idx & -idx)
            bit[idx]++;
    }

    private boolean same(int[] bit, int l, int r) {
        int diff = 0;
        while (l != r) {
            if (r > l) {
                diff += bit[r];
                r -= r & -r;
            } else {
                diff -= bit[l];
                l -= l & -l;
            }
        }
        return diff == 0;
    }

    /**
     * O(n) solution
     */
    public int kEmptySlots2(int[] flowers, int k) {
        int n = flowers.length;
        int[] pos = new int[n + 1];
        for (int i = 0; i < n; i++) pos[flowers[i]] = i + 1;
        int start = 1, min = 1000000;
        out:
        while (start < n - k) {
            for (int i = start + 1; i <= start + k; i++) {
                if (pos[i] < pos[start] || pos[i] < pos[start + k + 1]) {
                    start = i;
                    continue out;
                }
            }
            min = Math.min(min, Math.max(pos[start], pos[start + k + 1]));
            start += k + 1;
        }
        return min == 1000000 ? -1 : min;
    }

    /**
     * O(n) solution using buckets
     */
    public int kEmptySlots4(int[] flowers, int k) {
        int n = flowers.length;
        int nb = n / (k + 1) + (n % (k + 1) == 0 ? 0 : 1);
        int[] maxs = new int[nb];
        int[] mins = new int[nb];
        Arrays.fill(maxs, Integer.MIN_VALUE);
        Arrays.fill(mins, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            int cur = flowers[i] - 1;
            int b = cur / (k + 1);
            maxs[b] = Math.max(maxs[b], cur);
            mins[b] = Math.min(mins[b], cur);

            if (mins[b] == cur && b > 0) {
                // check the previous bucket
                if (maxs[b - 1] == cur - k - 1) return i + 1;
            }
            if (maxs[b] == cur && b < nb - 1) {
                // check the next bucket
                if (mins[b + 1] == cur + k + 1) return i + 1;
            }
        }
        return -1;
    }
}