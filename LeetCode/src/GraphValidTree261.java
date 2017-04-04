/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 4/1/17
 * Time: 11:36 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class GraphValidTree261 {
    /**
     * The first solution - dfs
     */
    int count = 0;
    boolean[] visited = null;

    public boolean validTree(int n, int[][] edges) {
        if (n - 1 != edges.length) return false;
        List<Integer>[] g = new List[n];
        for (int i = 0; i < g.length; i++)
            g[i] = new ArrayList<>();
        for (int[] pair : edges) {
            g[pair[0]].add(pair[1]);
            g[pair[1]].add(pair[0]);
        }

        count = 0;
        visited = new boolean[n];
        dfs(g, 0);

        return count == n;
    }

    private void dfs(List<Integer>[] g, int idx) {
        visited[idx] = true;
        count++;
        for (int next : g[idx]) {
            if (!visited[next])
                dfs(g, next);
        }
    }

    /**
     * A much better solutin - union find/disjoint set
     */
    public boolean validTree1(int n, int[][] edges) {
        if (n - 1 != edges.length) return false;

        int[] ds = new int[n];
        Arrays.fill(ds, -1);

        for (int[] pair : edges) {
            int id1 = find(ds, pair[0]);
            int id2 = find(ds, pair[1]);
            if (id1 == id2)
                return false;
            ds[id1] = id2;
        }
        return true;
    }

    private int find(int[] ds, int idx) {
        return ds[idx] == -1 ? idx : (ds[idx] = find(ds, ds[idx]));
    }
}
