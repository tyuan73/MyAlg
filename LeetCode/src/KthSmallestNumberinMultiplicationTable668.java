/*

*/

import java.util.*;
import java.io.*;

public class KthSmallestNumberinMultiplicationTable668 {
    /**
     * First try. LTE
     */
    public int findKthNumber(int m, int n, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> ((a[0] + 1) * (a[1] + 1) - (b[0] + 1) * (b[1] + 1)));
        for (int i = 0; i < n; i++)
            pq.add(new int[]{0, i});
        int[] num = null;
        while (k-- > 0) {
            num = pq.poll();
            if (num[0] < m - 1) pq.add(new int[]{num[0] + 1, num[1]});
        }
        return (num[0] + 1) * (num[1] + 1);
    }

    /**
     * O(n * lg(n))
     */
    public int findKthNumber1(int m, int n, int k) {
        int l = 1, h = m * n;
        while (l < h) {
            int mid = (h + l) / 2;
            if (enough(mid, m, n, k))
                h = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    private boolean enough(int x, int m, int n, int k) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            count += Math.min(n, x / i);
        }
        return count >= k;
    }

    /**
     * Test
     */
    public static void main(String[] args) {
        KthSmallestNumberinMultiplicationTable668 test = new KthSmallestNumberinMultiplicationTable668();
        long start = System.currentTimeMillis();
        test.findKthNumber(9895, 28405, 100787757);
        System.out.println(System.currentTimeMillis() - start);
    }
}