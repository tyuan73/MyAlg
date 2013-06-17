/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/2/13
 * Time: 11:52 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MysteriousPresent4D {
    static List<Integer>[] G;
    static int[] order;
    static int orderid;
    static boolean[] visited;
    static int n, w, h;

    static int readInput() {
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        w = in.nextInt();
        h = in.nextInt();
        int[][] ev = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            ev[i][0] = in.nextInt();
            ev[i][1] = in.nextInt();
        }

        G = new List[n + 1];
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (ev[i][0] <= w || ev[i][1] <= h)
                continue;
            count++;
            G[i] = new ArrayList<Integer>();
            for (int j = 1; j <= n; j++) {
                if (ev[j][0] <= w || ev[j][1] <= h)
                    continue;
                if (ev[j][0] < ev[i][0] && ev[j][1] < ev[i][1])
                    G[i].add(j);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int count = readInput();
        if (count == 0) {
            System.out.println(0);
            return;
        }

        order = new int[count];
        visited = new boolean[n + 1];
        orderid = 0;
        for (int i = 1; i <= n; i++) {
            if (!visited[i] && G[i] != null) {
                getOrder(i);
            }
        }

        int[] len = new int[n + 1];
        int[] parent = new int[n + 1];
        for (int i = count - 1; i >= 0; i--) {
            int index = order[i];
            if (len[index] == 0)
                len[index]++;
            for (int x : G[index]) {
                if (len[index] + 1 > len[x]) {
                    len[x] = len[index] + 1;
                    parent[x] = index;
                }
            }
        }

        int max = -1, last = -1;
        for (int l = 1; l <= n; l++) {
            if (max < len[l]) {
                last = l;
                max = len[l];
            }
        }

        System.out.println(max);
        System.out.print(last);
        for (int i = 0; i < max - 1; i++) {
            last = parent[last];
            System.out.print(" " + last);
        }
        System.out.println();
    }

    static void getOrder(int i) {
        visited[i] = true;
        for (int x : G[i]) {
            if (!visited[x])
                getOrder(x);
        }
        order[orderid++] = i;
    }
}
