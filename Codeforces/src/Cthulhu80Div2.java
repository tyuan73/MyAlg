/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/2/13
 * Time: 11:26 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cthulhu80Div2 {
    static List<Integer>[] g = null;
    static boolean[] visited = null;
    static int c = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        g = new List[n];
        for (int i = 0; i < n; i++)
            g[i] = new ArrayList<Integer>();

        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            g[from].add(to);
            g[to].add(from);
        }

        visited = new boolean[n];
        c = 0;
        dfs(0, -1);

        if (c != 2) {
            System.out.println("NO");
            return;
        }

        for (boolean v : visited)
            if (!v) {
                System.out.println("NO");
                return;
            }
        System.out.println("FHTAGN!");
    }

    static void dfs(int index, int parent) {
        visited[index] = true;
        for (int next : g[index]) {
            if (visited[next] && next != parent)
                c++;
            if (!visited[next])
                dfs(next, index);
        }
    }
}
