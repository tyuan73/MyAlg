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
        int[] bit = new int[n+1];
        boolean[] bed = new boolean[n+1];
        for(int i = 0; i < flowers.length; i++) {
            int pos = flowers[i];
            if (pos > k+1 && bed[pos-k-1]) {
                if (sum(bit, pos) == sum(bit, pos-k-1))
                    return i+1;
            }
            add(bit, pos);
            bed[pos] = true;
            if (pos + k < n && bed[pos +k +1]) {
                if (sum(bit, pos) == sum(bit, pos+k))
                    return i+1;
            }
        }
        return -1;
    }

    private void add(int[] bit, int idx) {
        for(; idx < bit.length; idx += idx & -idx)
            bit[idx]++;
    }
    private int sum(int[] bit, int idx) {
        int ret = 0;
        for(; idx > 0; idx -= idx & -idx) {
            ret += bit[idx];
        }
        return ret;
    }

}