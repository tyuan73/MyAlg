/*

*/

import java.util.*;
import java.io.*;

public class RedundantConnectionII685 {
    class Node {
        int lastParent = -1, status = 0, parentEdgePriority = -1;
        List<Integer> children = new ArrayList<>();
    }

    int[] ans = null;

    /**
     * DFS
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        Node[] tree = new Node[n + 1];
        for (int i = 0; i <= n; i++) {
            tree[i] = new Node();
        }
        boolean hasRoot = false;
        for (int i = 0; i < n; i++) {
            int parent = edges[i][0], child = edges[i][1];
            tree[parent].children.add(child);
            if (tree[child].lastParent != -1) hasRoot = true;
            tree[child].lastParent = parent;
            tree[child].parentEdgePriority = i;
        }
        if (hasRoot) {
            int root = 0;
            for (int i = 1; i <= n; i++) {
                if (tree[i].lastParent == -1) {
                    root = i;
                    break;
                }
            }
            dfs(tree, root);
            int lastNode = ans[1];
            if (tree[lastNode].status == 2) {
                return new int[]{tree[lastNode].lastParent, lastNode};
            }
            return ans;
        }

        int start = 1;
        while (tree[start].status != 3) {
            tree[start].status = 3;
            start = tree[start].lastParent;
        }
        int priority = -1;
        while (tree[start].status != 4) {
            tree[start].status = 4;
            int p = tree[start].parentEdgePriority;
            if (p > priority) {
                priority = p;
                ans = new int[]{tree[start].lastParent, start};
            }
            start = tree[start].lastParent;
        }

        return ans;
    }

    private boolean dfs(Node[] tree, int i) {
        tree[i].status = 1;
        for (int child : tree[i].children) {
            if (tree[child].status > 0) {
                ans = new int[]{i, child};
                return true;
            }
            if (dfs(tree, child))
                return true;
        }
        tree[i].status = 2;
        return false;
    }

    /**
     * Disjoint set/Union Find.
     */
    public int[] findRedundantDirectedConnectionDS(int[][] edges) {
        int n = edges.length;
        int[] children = new int[n + 1], parent = new int[n + 1];
        int first = -1, second = -1, node = -1;
        Arrays.fill(parent, -1);
        for (int i = 0; i < n; i++) {
            int[] e = edges[i];
            int p = e[0], c = e[1];
            children[p]++;
            if (parent[c] != -1) {
                first = parent[c];
                second = i;
                node = c;
            } else
                parent[c] = i;
        }

        if (node != -1 && children[node] == 0)
            return edges[second];

        int[] ds = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (i == second) continue;
            int[] e = edges[i];
            int p1 = find(ds, e[0]);
            if (p1 == e[1]) {
                if (node == -1)
                    return e;
                return edges[first];
            }
            // find(ds, e[1]) must be e[1], because no node has 2 parents now
            ds[e[1]] = p1;
        }
        return edges[second];
    }

    private int find(int[] ds, int i) {
        return ds[i] == 0 ? i : (ds[i] = find(ds, ds[i]));
    }

    /**
     * Improved DS solution
     */
    public int[] findRedundantDirectedConnection_DS_improved(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1], ds = new int[n + 1];
        Arrays.fill(parent, -1);
        int first = -1, second = -1, last = -1;
        for (int i = 0; i < n; i++) {
            int p = edges[i][0], c = edges[i][1];
            if (parent[c] != -1) {
                first = parent[c];
                second = i;
                continue;
            }
            parent[c] = i;

            int p1 = find(ds, p);
            if (p1 == c)
                last = i;
            else
                ds[c] = p1;
        }

        if (second != -1 && last == -1)
            return edges[second];
        if (second == -1 && last != -1)
            return edges[last];
        return edges[first];
    }
}

/*
Test case:
[[1,2],[1,3],[2,3]]
[[1,2], [2,3], [3,4], [4,1], [1,5]]
[[4,2],[1,5],[5,2],[5,3],[2,4]]
[[2,1],[3,1],[4,2],[1,4]]
[[4,1],[1,2],[1,3],[4,5],[5,6],[6,5]]
[[2,3], [3,4], [4,1], [1,5], [1,2]]
[[3,1],[1,4],[3,5],[1,2],[1,5]]
[[1,2],[2,3],[3,1]]

expected output:
[2,3]
[4,1]
[4,2]
[2,1]
[6,5]
[1,2]
[1,5]
[3,1]


 */