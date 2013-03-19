/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/19/13
 * Time: 12:12 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class EvenTree {
    static int[] order;
    static int[] parent;
    static boolean[] visited;
    static int oi;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        List<Integer>[] nodes = new List[n];
        for (int i = 0; i < n; i++)
            nodes[i] = new ArrayList<Integer>();
        while (m-- > 0) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            nodes[from].add(to);
            nodes[to].add(from);
        }

        visited = new boolean[n];
        parent = new int[n];
        order = new int[n];
        oi = 0;

        dfs(nodes, 0);

        int ret = 0;
        int[] d = new int[n];
        Arrays.fill(d, 1);
        for (int i = 0; i < n - 1; i++) {
            int node = order[i];
            if ((d[node] % 2) == 0) {
                ret++;
            } else {
                d[parent[node]]++;
            }
        }
        System.out.println(ret);
    }

    static void dfs(List<Integer>[] nodes, int index) {
        visited[index] = true;
        for (int i : nodes[index]) {
            if (!visited[i]) {
                dfs(nodes, i);
                parent[i] = index;
            }
        }
        order[oi++] = index;
    }
}
