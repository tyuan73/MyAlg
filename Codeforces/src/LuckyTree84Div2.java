/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/29/13
 * Time: 11:08 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class LuckyTree84Div2 {
    static long total = 0;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        List<Integer>[] tree = new List[n];
        for (int i = 0; i < n; i++)
            tree[i] = new ArrayList<Integer>();

        for (int i = 0; i < n - 1; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int w = in.nextInt();
            if (!isLucky(w)) {
                tree[from].add(to);
                tree[to].add(from);
            }
        }

        visited = new boolean[n];
        long res = (long) n * (long) (n - 1) * (long) (n - 2);
        for (int i = 0; i < n; i++) {
            if (tree[i].size() == 0)
                visited[i] = true;
            if (!visited[i]) {
                total = 0;
                dfs(tree, i);
                //res -= total*(total-1)*(n-total)*2;
                //res -= total*(total-1)*(total-2);
                res -= total * (total - 1) * (2 * n - total - 2);
            }
        }
        System.out.println(res);
    }

    static void dfs(List<Integer>[] tree, int i) {
        visited[i] = true;
        total++;
        for (int x : tree[i]) {
            if (!visited[x]) {
                dfs(tree, x);
            }
        }
    }

    static boolean isLucky(int w) {
        while (w > 0) {
            int x = w % 10;
            if (x != 4 && x != 7)
                return false;
            w /= 10;
        }

        return true;
    }
}
