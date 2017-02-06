/**
 * Created by yuantian on 2/6/17.
 */

import java.util.*;

public class FrogJump403 {

    /**
     * BFS - time limit exceeded!!!!!!!!!!!
     * DFS does not work either!!
     */
    class Pair {
        int from, step;

        Pair(int from, int step) {
            this.from = from;
            this.step = step;
        }
    }

    public boolean canCross(int[] stones) {
        int n = stones.length;
        int[][] G = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++)
                G[i][j] = stones[j] - stones[i];
        }

        LinkedList<Pair> list = new LinkedList<>();
        list.addLast(new Pair(0, 0));
        while (!list.isEmpty()) {
            Pair p = list.removeFirst();
            for (int i = p.from + 1; i < n; i++) {
                int[] row = G[p.from];
                if (row[i] == p.step - 1 || row[i] == p.step || row[i] == p.step + 1) {
                    if (i == n - 1)
                        return true;
                    list.addLast(new Pair(i, row[i]));
                } else if (row[i] > p.step + 1)
                    break;
            }
        }
        return false;
    }

    /**
     * Working - use hashmap as dp
     */
    public boolean canCross1(int[] stones) {
        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        for (int i : stones)
            map.put(i, new HashSet<Integer>());

        map.get(0).add(0);
        int n = stones.length - 1;
        for (int i : stones) {
            Set<Integer> set = map.get(i);
            for (int step : set) {
                for (int k = step - 1; k <= step + 1; k++) {
                    if (k > 0 && map.containsKey(k + i)) {
                        map.get(k + i).add(k);
                    }
                }
            }
        }
        return map.get(stones[n]).size() > 0;
    }
}
