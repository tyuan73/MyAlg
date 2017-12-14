/*

*/

import java.util.*;
import java.io.*;

public class NetworkDelayTime743 {
    /**
     * The first solution is based on DFS. The slowest.
     * <p>
     * Runtime: 309ms in Leetcode.com
     */
    public int networkDelayTime(int[][] times, int N, int K) {
        List<int[]>[] g = new List[N];
        for (int i = 0; i < N; i++)
            g[i] = new ArrayList<>();
        for (int[] t : times)
            g[t[0] - 1].add(new int[]{t[1] - 1, t[2]});

        int[] signal = new int[N];
        Arrays.fill(signal, 1000000);
        signal[K - 1] = 0;
        dfs(g, K - 1, signal);

        int max = 0;
        for (int s : signal)
            max = Math.max(max, s);
        return max == 1000000 ? -1 : max;
    }

    private void dfs(List<int[]>[] g, int k, int[] signal) {
        for (int[] next : g[k]) {
            if (signal[k] + next[1] < signal[next[0]]) {
                signal[next[0]] = signal[k] + next[1];
                dfs(g, next[0], signal);
            }
        }
    }

    /**
     * The second solution is based on Heap (PriorityQueue), much faster.
     * This is basically the same as dijkstra's shortest path algorithm.
     * <p>
     * Runtime: 108ms in Leetcode.com
     */
    public int networkDelayTime1(int[][] times, int N, int K) {
        List<int[]>[] g = new List[N];
        for (int i = 0; i < N; i++)
            g[i] = new ArrayList<>();
        for (int[] t : times)
            g[t[0] - 1].add(new int[]{t[1] - 1, t[2]});

        boolean[] reached = new boolean[N];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        pq.add(new int[]{K - 1, 0});
        //reached[K-1] = true;
        int max = 0, count = 0;
        while (pq.size() > 0) {
            int[] next = pq.poll();
            if (reached[next[0]]) continue;
            count++;
            reached[next[0]] = true;
            max = Math.max(max, next[1]);
            for (int[] l : g[next[0]]) {
                pq.add(new int[]{l[0], next[1] + l[1]});
            }
        }
        return count < N ? -1 : max;
    }

    /**
     * The third solution is basically the same as above. But, instead of using Lambda
     * in priorityqueue, Comparator is used which is somehow faster.
     * <p>
     * Runtime: 69ms in Leetcode.com
     */
    public int networkDelayTime3(int[][] times, int N, int K) {
        List<int[]>[] g = new List[N];
        for (int i = 0; i < N; i++)
            g[i] = new ArrayList<>();
        for (int[] t : times)
            g[t[0] - 1].add(new int[]{t[1] - 1, t[2]});

        boolean[] reached = new boolean[N];
        //PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        pq.add(new int[]{K - 1, 0});
        int max = 0, count = 0;
        while (pq.size() > 0) {
            int[] next = pq.poll();
            if (reached[next[0]]) continue;
            count++;
            reached[next[0]] = true;
            max = Math.max(max, next[1]);
            for (int[] l : g[next[0]]) {
                pq.add(new int[]{l[0], next[1] + l[1]});
            }
        }
        return count < N ? -1 : max;
    }

    /**
     * The fourth solution is the same as above. But a plan java object (Ele) is used instead of int[].
     * <p>
     * Runtime: 53ms in Leetcode.com
     */
    class Ele {
        int i, t;

        Ele(int i, int t) {
            this.i = i;
            this.t = t;
        }
    }

    public int networkDelayTime4(int[][] times, int N, int K) {
        List<Ele>[] g = new List[N];
        for (int i = 0; i < N; i++)
            g[i] = new ArrayList<>();
        for (int[] t : times)
            g[t[0] - 1].add(new Ele(t[1] - 1, t[2]));

        boolean[] reached = new boolean[N];
        //PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        PriorityQueue<Ele> pq = new PriorityQueue<>(new Comparator<Ele>() {
            public int compare(Ele a, Ele b) {
                return a.t - b.t;
            }
        });
        pq.add(new Ele(K - 1, 0));
        int max = 0, count = 0;
        while (pq.size() > 0) {
            Ele next = pq.poll();
            if (reached[next.i]) continue;
            count++;
            reached[next.i] = true;
            max = Math.max(max, next.t);
            for (Ele l : g[next.i]) {
                pq.add(new Ele(l.i, next.t + l.t));
            }
        }
        return count < N ? -1 : max;
    }
}