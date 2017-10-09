/*

*/

import java.util.*;
import java.io.*;

public class CutOffTreesforGolfEvent675 {
    final static int[][] steps = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * Floyd-Warshall solution - O(n^3) - TLE
     * <p>
     * https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm
     */
    public int cutOffTree(List<List<Integer>> forest) {
        int n = forest.size(), m = forest.get(0).size();
        List<int[]> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (forest.get(i).get(j) <= 1) continue;
                map.add(new int[]{forest.get(i).get(j), i * m + j});
            }
        }
        Collections.sort(map, (a, b) -> (a[0] - b[0]));

        int[][] path = new int[n * m][n * m];
        for (int i = 0; i < path.length; i++)
            Arrays.fill(path[i], 1000000);

        for (int i = 0; i < path.length; i++) {
            int x = i / m, y = i % m;
            if (forest.get(x).get(y) == 0) continue;
            path[i][i] = 0;
            for (int[] s : steps) {
                int x1 = x + s[0], y1 = y + s[1];
                if (x1 < 0 || x1 >= n || y1 < 0 || y1 >= m || forest.get(x1).get(y1) == 0)
                    continue;
                path[i][x1 * m + y1] = 1;
            }
        }
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path.length; j++) {
                if (i == j) continue;
                for (int k = 0; k < path.length; k++) {
                    if (k == i || k == j) continue;
                    if (path[j][i] + path[i][k] < path[j][k]) {
                        path[j][k] = path[j][i] + path[i][k];
                    }
                }
            }
        }

        int from = 0, ans = 0;
        for (int[] next : map) {
            if (path[from][next[1]] == 1000000) return -1;
            ans += path[from][next[1]];
            from = next[1];
        }
        return ans;
    }

    /**
     * Sorting + BFS. O(n^2). ACed.
     */
    public int cutOffTree1(List<List<Integer>> forest) {
        //if (forest.get(0).get(0) == 0) return -1;
        int n = forest.size(), m = forest.get(0).size();
        List<int[]> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (forest.get(i).get(j) <= 1) continue;
                map.add(new int[]{forest.get(i).get(j), i * m + j});
            }
        }
        Collections.sort(map, (a, b) -> (a[0] - b[0]));

        int[][] path = new int[n * m][n * m];
        for (int i = 0; i < path.length; i++) {
            Arrays.fill(path[i], 1000000);
            path[i][i] = 0;
        }

        int from = 0, ans = 0;
        for (int[] next : map) {
            // BFS
            if (path[from][next[1]] == 1000000 && path[next[1]][from] == 1000000) {
                List<Integer> line = new ArrayList<>();
                line.add(next[1]);
                while (line.size() > 0) {
                    List<Integer> temp = new ArrayList<>();
                    for (int tree : line) {

                        int x = tree / m, y = tree % m;

                        for (int[] s : steps) {
                            int x1 = x + s[0], y1 = y + s[1];
                            if (x1 < 0 || x1 >= n || y1 < 0 || y1 >= m || forest.get(x1).get(y1) == 0)
                                continue;
                            if (path[next[1]][tree] + 1 < path[next[1]][x1 * m + y1]) {
                                temp.add(x1 * m + y1);
                                path[next[1]][x1 * m + y1] = path[next[1]][tree] + 1;
                                //path[x1*m+y1][next[1]] = path[next[1]][tree] + 1;
                            }

                        }
                    }
                    line = temp;
                }
            }
            if (path[from][next[1]] == 1000000 && path[next[1]][from] == 1000000) return -1;
            ans += Math.min(path[from][next[1]], path[next[1]][from]);
            from = next[1];
        }
        return ans;
    }

}