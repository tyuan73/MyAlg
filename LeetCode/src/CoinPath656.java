
import java.util.*;

public class CoinPath656 {
    public List<Integer> cheapestJump(int[] A, int B) {
        int n = A.length;
        int[] dp = new int[n];
        int[] next = new int[n];
        Arrays.fill(next, -1);
        Arrays.fill(dp, 1000000);
        dp[n - 1] = A[n - 1];
        next[n - 1] = n;
        for (int i = n - 1; i >= 0; i--) {
            if (A[i] == -1) continue;
            for (int j = Math.min(n - 1, i + B); j > i; j--) {
                if (A[j] == -1) continue;
                if (A[i] + dp[j] <= dp[i]) {
                    dp[i] = A[i] + dp[j];
                    next[i] = j;
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        if (next[0] == -1) return ans;
        ans.add(1);
        int i = next[0];
        while (i != n) {
            ans.add(i + 1);
            i = next[i];
        }
        return ans;
    }

    /**
     * Use priorityqueue. https://discuss.leetcode.com/topic/100007/use-priorityqueue-o-n-lg-b
     */
    public List<Integer> cheapestJump1(int[] A, int B) {
        int n = A.length;
        int[] next = new int[n];

        //PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0])
                    return a[1] - b[1];
                return a[0] - b[0];
            }
        });

        List<Integer> ans = new ArrayList<>();
        pq.add(new int[]{0, n - 1 + B});
        for (int i = n - 1; i >= 0; i--) {
            if (A[i] == -1) continue;
            while (pq.size() > 0 && pq.peek()[1] > i + B)
                pq.poll();
            if (pq.size() == 0) return ans;
            int[] min = pq.peek();
            pq.add(new int[]{A[i] + min[0], i});
            next[i] = min[1];
        }

        int i = 0;
        do {
            ans.add(i + 1);
            i = next[i];
        } while (i != n - 1 + B);

        return ans;
    }
}
