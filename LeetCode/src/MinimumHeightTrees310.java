/**
 * Created by yuantian on 2/10/17.
 */

import java.util.*;

/**
 * for test:

 Input:
 4
 [[1,0],[1,2],[1,3]]
 Expect output:
 [1]

 Input:
 1
 []
 Expect output:
 [0]

 */

public class MinimumHeightTrees310 {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Set<Integer>[] tree = new Set[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new HashSet<>();
        }
        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }

        List<Integer> ans = new ArrayList<>(); // to keep all leaves
        for (int i = 0; i < n; i++) {
            if (tree[i].size() <= 1) // for case of input "1, []", the size is 0
                ans.add(i);
        }

        List<Integer> temp = new ArrayList<>();
        while ((n -= ans.size()) > 0) { // if there is no non-leaves left - meaning all remaining nodes are leaves
            temp.clear();
            for (int i : ans) {
                for (int next : tree[i]) { // only one element, but just for convinent.
                    tree[next].remove(i);
                    if (tree[next].size() == 1)
                        temp.add(next);
                }
            }
            List<Integer> l = ans;
            ans = temp;
            temp = l;
        }
        return ans;
    }
}
