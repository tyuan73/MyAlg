/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/7/17
 * Time: 9:37 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class ShortestDistancefromAllBuildings317 {
    final static int[][] steps = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int shortestDistance(int[][] grid) {
        int n = grid.length;
        if (n == 0) return -1;
        int m = grid[0].length;
        if (m == 0) return -1;
        int[][] dist = new int[n * m][m * n];
        Set<Integer> building = new HashSet<>();
        Set<Integer> empty = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    bfs(grid, i, j, dist);
                    building.add(i * m + j);
                } else if (grid[i][j] == 0)
                    empty.add(i * m + j);
            }
        }

        int min = Integer.MAX_VALUE;
        l1:
        for (int e : empty) {
            int total = 0;
            //boolean valid = true;
            for (int b : building) {
                if (dist[b][e] == 0) {
                    //valid = false;
                    continue l1;  /**** USE continue label to continue outside loop ****/
                }
                total += dist[b][e];
            }
            min = Math.min(min, total);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    void bfs(int[][] grid, int x, int y, int[][] dist) {
        int n = grid.length, m = grid[0].length;
        LinkedList<Integer> list = new LinkedList<>();
        boolean[] visited = new boolean[n * m];
        list.add(x * m + y);
        while (!list.isEmpty()) {
            int point = list.removeFirst();
            int x1 = point / m, y1 = point % m;
            for (int[] step : steps) {
                int x2 = x1 + step[0], y2 = y1 + step[1];
                if (x2 < 0 || x2 >= n || y2 < 0 || y2 >= m || visited[x2 * m + y2] || grid[x2][y2] != 0)
                    continue;
                dist[x * m + y][x2 * m + y2] = dist[x * m + y][x1 * m + y1] + 1;
                visited[x2 * m + y2] = true;
                list.addLast(x2 * m + y2);
            }
        }
    }
}
