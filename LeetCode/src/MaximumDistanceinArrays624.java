/**
 * Created by yuantian on 6/19/17.
 */

import java.util.*;

public class MaximumDistanceinArrays624 {
    public int maxDistance(List<List<Integer>> arrays) {
        int[][] min = new int[2][2];
        int[][] max = new int[2][2];
        int minFirst = 0, maxFirst = 0;
        min[0][0] = min[1][0] = Integer.MAX_VALUE;
        max[0][0] = max[1][0] = Integer.MIN_VALUE;
        for (int i = 0; i < arrays.size(); i++) {
            List<Integer> list = arrays.get(i);
            if (list.get(0) <= min[minFirst][0]) {
                minFirst ^= 1;
                min[minFirst][0] = list.get(0);
                min[minFirst][1] = i;
            } else if (list.get(0) < min[minFirst ^ 1][0]) {
                min[minFirst ^ 1][0] = list.get(0);
                min[minFirst ^ 1][1] = i;
            }

            if (list.get(list.size() - 1) >= max[maxFirst][0]) {
                maxFirst ^= 1;
                max[maxFirst][0] = list.get(list.size() - 1);
                max[maxFirst][1] = i;
            } else if (list.get(list.size() - 1) > max[maxFirst ^ 1][0]) {
                max[maxFirst ^ 1][0] = list.get(list.size() - 1);
                max[maxFirst ^ 1][1] = i;
            }
        }
        if (max[maxFirst][1] != min[minFirst][1])
            return max[maxFirst][0] - min[minFirst][0];
        return Math.max(max[maxFirst ^ 1][0] - min[minFirst][0], max[maxFirst][0] - min[minFirst ^ 1][0]);
    }

    /**
     * a much shorter solution
     */
    public int maxDistance1(List<List<Integer>> arrays) {
        int ans = 0;
        int max = -100000, min = 100000;
        for (List<Integer> list : arrays) {
            ans = Math.max(ans, Math.max(max - list.get(0), list.get(list.size() - 1) - min));
            max = Math.max(max, list.get(list.size() - 1));
            min = Math.min(min, list.get(0));
        }
        return ans;
    }
}
