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

expected output:
[2,3]
[4,1]
[4,2]
[2,1]
[6,5]
[1,2]
[1,5]


 */