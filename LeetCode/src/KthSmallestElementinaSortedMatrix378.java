/**
 * Created by yuantian on 3/8/17.
 */

import java.util.*;

public class KthSmallestElementinaSortedMatrix378 {
    public int kthSmallest(int[][] matrix, int k) {
        //if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
        //    return 0;
        int n = matrix.length, m = matrix[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (matrix[a[0]][a[1]] - matrix[b[0]][b[1]]));
        for (int i = 0; i < m; i++)
            pq.add(new int[]{0, i});

        int ans = 0;
        while (k-- > 0) {
            int[] val = pq.poll();
            ans = matrix[val[0]][val[1]];
            if (val[0] < n - 1)
                pq.add(new int[]{val[0] + 1, val[1]});
        }
        return ans;
    }

    /**
     * A faster solution
     * The reason is that "PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (matrix[a[0]][a[1]] - matrix[b[0]][b[1]]));"
     * in above solution is much slower than the Comparator version !!!!!!!!!
     */
    public int kthSmallest1(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Long> q = new PriorityQueue<Long>(new Comparator<Long>() {
            public int compare(Long l1, Long l2) {
                int[] xy1 = getXY(l1);
                int[] xy2 = getXY(l2);
                return matrix[xy1[0]][xy1[1]] - matrix[xy2[0]][xy2[1]];
            }
        });

        q.add(0L);
        int[] xy = null;
        while (k-- > 0) {
            long l = q.poll();
            xy = getXY(l);
            if (xy[0] == 0 && xy[1] < n - 1) {
                q.add(xy[1] + 1L);
            }
            if (xy[0] < n - 1)
                q.add(((xy[0] + 1l) << 32) + xy[1]);
        }
        return matrix[xy[0]][xy[1]];

    }

    int[] getXY(long l) {
        return new int[]{(int) (l >> 32), (int) (l & 0xFFFFFFFF)};
    }
}
