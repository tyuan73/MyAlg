/**
 * Created by yuantian on 5/6/14.
 */
/*
Atul is into graph theory, and he is learning about trees nowadays. He observed that the removal of an edge from a given tree T will result in formation of two separate trees T1 and T2.

Each vertex of the tree T is assigned a positive integer. Your task is to remove an edge, such that, the Tree_diff of the resultant trees is minimized. Tree_diff is defined as

 F(T) = Sum of numbers written on each vertex of a Tree T
 Tree_diff(T) = abs(F(T1) - F(T2))
Input Format
The first line will contain an integer N, i.e., the number of vertices in the tree.
The next line will contain N integers separated by a single space, i.e., the values assigned to each of the vertices.
The next (N-1) lines contain pair of integers separated by a single space and denote the edges of the tree.
In the above input, the vertices are numbered from 1 to N.

Output Format
A single line containing the minimum value of Tree_diff.

Constraints
3 ≤ N ≤ 105
1 ≤ number written on each vertex ≤ 1001

Sample Input

6
100 200 100 500 100 600
1 2
2 3
2 5
4 5
5 6
Sample Output

400
Explanation

Cutting the edge at 1 2 would result in Tree_diff = 1400
Cutting the edge at 2 3 would result in Tree_diff = 1400
Cutting the edge at 2 5 would result in Tree_diff = 800
Cutting the edge at 4 5 would result in Tree_diff = 600
Cutting the edge at 5 6 would result in Tree_diff = 400

Hence, the answer is 400.
 */

import java.util.*;

public class CutTheTree {
    static List[] tree = null;
    static boolean[] visited = null;
    static int[] parent = null;
    static int[] order = null;
    static int orderI = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] weight = new int[n];
        int[] tw = new int[n];
        int total = 0;
        for (int i = 0; i < n; i++) {
            weight[i] = in.nextInt();
            tw[i] = weight[i];
            total += weight[i];
        }


        tree = new ArrayList[n];
        for(int i = 0; i < n; i++)
            tree[i] = new ArrayList<Integer>();

        for(int i = 0; i < n-1; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            tree[from].add(to);
            tree[to].add(from);
        }

        visited = new boolean[n];
        parent = new int[n];
        order = new int[n];
        orderI = 0;
        dfs(0);

        int min = Integer.MAX_VALUE;
        for (int i = n-1; i > 0; i--) { // skip the root which is vertex 0
            int next = order[i];
            int p = parent[next];
            tw[p] += tw[next];
            min = Math.min(min, Math.abs(total - 2*tw[next]));
        }

        System.out.println(min);
    }

    private static void dfs(int index) {
        visited[index] = true;
        order[orderI++] = index;
        ArrayList<Integer> list = (ArrayList<Integer>) tree[index];
        for(Integer child : list) {
            if (!visited[child]) {
                parent[child] = index;
                dfs(child);
            }
        }
    }
}
