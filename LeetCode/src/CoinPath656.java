
import java.util.*;

public class CoinPath656 {
    /**
     * O(n*b)
     */
    public List<Integer> cheapestJump(int[] A, int B) {
        int n = A.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1000000);
        if (A[n - 1] != -1) dp[n - 1] = 0;
        int[] next = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            if (A[i] == -1) continue;
            for (int j = Math.min(n - 1, i + B); j > i; j--) {
                if (A[i] + dp[j] <= dp[i]) {
                    dp[i] = A[i] + dp[j];
                    next[i] = j;
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        if (dp[0] == 1000000) return ans;

        for (int i = 0; i != n - 1; i = next[i])
            ans.add(i + 1);
        ans.add(n);
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
