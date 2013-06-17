/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 2/27/13
 * Time: 1:40 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CycleInGraph {
    static List<Integer>[] v;
    static int[] dist;
    static int[] path;
    static int len;
    static int n;
    static int m;
    static int k;

    static public void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        v = new List[n];
        for (int i = 0; i < n; i++)
            v[i] = new ArrayList<Integer>();

        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            v[from].add(to);
            v[to].add(from);
        }

        dist = new int[n];
        path = new int[n];
        for (int i = 0; i < n; i++) {
            if (dist[i] == 0) {
                len = 0;
                if (hasCycle(i, 1))
                    break;
            }
        }
    }

    static boolean hasCycle(int index, int d) {
        dist[index] = d;
        path[len++] = index;
        for (int i : v[index]) {
            // if()
        }

        return false;
    }
}
