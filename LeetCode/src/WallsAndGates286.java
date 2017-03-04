/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/4/17
 * Time: 9:51 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class WallsAndGates286 {
    final static int[][] diff = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void wallsAndGates(int[][] rooms) {
        int n = rooms.length;
        if (n == 0) return;
        int m = rooms[0].length;
        List<int[]> cur = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rooms[i][j] == 0) {
                    cur.add(new int[]{i, j});
                }
            }
        }

        int step = 1;
        while (cur.size() > 0) {
            List<int[]> next = new ArrayList<>();
            for (int[] x : cur) {
                for (int[] d : diff) {
                    int x1 = x[0] + d[0], y1 = x[1] + d[1];
                    if (x1 >= 0 && x1 < n && y1 >= 0 && y1 < m && rooms[x1][y1] > step) {
                        rooms[x1][y1] = step;
                        next.add(new int[]{x1, y1});
                    }
                }
            }
            cur = next;
            step++;
        }
    }
}
