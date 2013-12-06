/**
 * Created by yuantian on 12/5/13.
 */

/**
 * test case:
 * 5
 * 1 2 2
 * 2 3 1
 * 2 4 2
 * 4 5 1
 *
 * output:
 * 1
 * 4
 *
 *
10
 7 5 1
 2 1 2
 8 7 2
 2 4 1
 4 5 2
 9 5 1
 3 2 2
 2 10 1
 6 5 2

 3
 8 6 3
 */

import java.util.*;

public class ValeraAndElections369C {
    static class Disc {
        int id;
        boolean problem;

        public Disc(int id, int pro) {
            this.id = id;
            this.problem = pro == 1 ? false : true;
        }
    }

    static ArrayList<Disc>[] tree = null;
    static boolean[] visited = null;
    static int[] count = null;
    static ArrayList<Integer> out = null;

    static public void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++)
            tree[i] = new ArrayList<Disc>();

        for (int i = 0; i < n-1; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int pro = in.nextInt();

            tree[from].add(new Disc(to, pro));
            tree[to].add(new Disc(from, pro));
        }

        visited = new boolean[n];
        out = new ArrayList<Integer>();
        count = new int[n];
        visited[0] = true;

        for (Disc d : tree[0])
            dfs(0, d.id, d.problem);

        System.out.println(out.size());
        for (int i = 0; i < out.size(); i++)
            System.out.print((out.get(i)+1) + " ");
    }

    static void dfs(int p, int id, boolean hasproblem) {
        if (visited[id])
            return;
        visited[id] = true;
        for (Disc d : tree[id]) {
            dfs(id, d.id, d.problem);
        }
        count[p] += count[id];
        if (hasproblem && count[id] == 0) {
            out.add(id);
            count[p]++;
        }
    }
}
