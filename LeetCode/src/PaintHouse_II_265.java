/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/22/17
 * Time: 10:40 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class PaintHouse_II_265 {
    public int minCostII(int[][] costs) {
        if (costs.length == 0) return 0;
        int n = costs.length, k = costs[0].length;
        int idx1 = 0, min1 = 0, idx2 = 0, min2 = 0;
        for (int i = 0; i < n; i++) {
            int curIdx1 = 0, curMin1 = Integer.MAX_VALUE, curIdx2 = 0, curMin2 = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                int cost = costs[i][j];
                if (j == idx1) {
                    cost += min2;
                } else {
                    cost += min1;
                }
                if (cost <= curMin1) {
                    curMin2 = curMin1;
                    curIdx2 = curIdx1;
                    curMin1 = cost;
                    curIdx1 = j;
                } else if (cost < curMin2) {
                    curMin2 = cost;
                    curIdx2 = j;
                }
            }
            idx1 = curIdx1;
            min1 = curMin1;
            idx2 = curIdx2;
            min2 = curMin2;
        }

        return min1;
    }
}
