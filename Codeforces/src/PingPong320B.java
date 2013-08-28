/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 8/28/13
 * Time: 3:16 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class PingPong320B {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[][] intv = new int[n][2];
        List[] list = new ArrayList[n];
        int index = 0;
        for(int i = 0; i < n; i++) {
            int op = in.nextInt();
            if (op == 1) {
                intv[index][0] = in.nextInt();
                intv[index][1] = in.nextInt();
                list[index] = new ArrayList<Integer>();
                for (int k = 0; k < index; k++) {
                    if((intv[k][0] > intv[index][0] && intv[k][0] < intv[index][1])
                            || (intv[k][1] > intv[index][0] && intv[k][1] < intv[index][1])) {
                        list[k].add(index);
                    }
                    if((intv[index][0] > intv[k][0] && intv[index][0] < intv[k][1])
                            || (intv[index][1] > intv[k][0] && intv[index][1] < intv[k][1])) {
                        list[index].add(k);
                    }
                }
                index++;
            } else {
                // op == 2
                int from = in.nextInt() - 1;
                int to = in.nextInt() - 1;

                boolean[] vd = new boolean[n];
                if(dfs(list, vd, from, to))
                    System.out.println("YES");
                else
                    System.out.println("NO");
            }
        }
    }

    private static boolean dfs(List[] list, boolean[] visited, int from, int to) {
        visited[from] = true;
        ArrayList<Integer> target = (ArrayList<Integer>) list[from];
        for(int i : target) {
            if(visited[i])
                continue;
            if(i == to || dfs(list, visited, i, to))
                return true;
        }
        return false;
    }
}
