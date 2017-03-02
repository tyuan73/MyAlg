/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/1/17
 * Time: 10:48 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class SparseMatrixMultiplication311 {
    class Node {
        int val, index;

        Node(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    public int[][] multiply(int[][] A, int[][] B) {
        int n = A.length, m = A[0].length, k = B[0].length;
        List<Node>[] a = new List[n];
        for (int i = 0; i < n; i++) {
            a[i] = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                if (A[i][j] != 0)
                    a[i].add(new Node(A[i][j], j));
            }
        }
        int[][] ans = new int[n][k];
        for (int i = 0; i < n; i++) {
            List<Node> x = a[i];
            for (int j = 0; j < k; j++) {
                for (Node node : x) {
                    ans[i][j] += node.val * B[node.index][j];
                }
            }
        }
        return ans;
    }
}
