
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

    /**
     * O(n) - fastest
     */
    public List<Integer> cheapestJump2(int[] A, int B) {
        int n = A.length;
        List<Integer> ans = new ArrayList<>();
        // this algo can not handle if the last element is -1.
        if (A[n - 1] == -1)
            return ans;
        int[] dp = new int[n];
        //Arrays.fill(dp, 1000000);     // no need to assign init value
        //dp[n-1] = 0;
        int[] next = new int[n];    // for back track
        int[] idx = new int[n];     // keep indexes, like a deque
        int l = n - 1, r = n - 1;   // two pointers, l -> the index of the largest, r -> the index of the smallest
        idx[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) { // start from n-2
            if (A[i] == -1) continue;

            while (r >= l && idx[r] > i + B) // remove any element that is B+ away from i
                r--;
            if (r < l)  // if there is no element in deque, the end is not reachable
                return ans;

            dp[i] = dp[idx[r]] + A[i]; // update dp
            next[i] = idx[r];          // keep index for back tracking

            while (l <= r && dp[i] <= dp[idx[l]]) // add current idx to deque, remove any dp that is larger
                l++;
            idx[--l] = i;  // add current idx to deque
        }

        // no need to check again. if we get here, it is guaranteed the end is reachable
        // if (dp[0] == 1000000) return ans;

        // construct the return list
        for (int i = 0; i != n - 1; i = next[i])
            ans.add(i + 1);
        ans.add(n);

        return ans;
    }

    // for test
    public static void main(String[] args) {
        Random rand = new Random();
        int n = 10000000, B = 1000;
        int[] data = new int[n];
        for(int i = 0; i < n; i++) {
            data[i] = rand.nextInt(10) - 1;
        }

        Set<List<Integer>> set = new HashSet<>();
        List<Integer> ans= null;
        CoinPath656 test = new CoinPath656();
        long start = 0;

        /*
        start = System.currentTimeMillis();
        ans = test.cheapestJump(data, B);
        System.out.println(System.currentTimeMillis() - start);
        set.add(ans);
        */

        start = System.currentTimeMillis();
        ans = test.cheapestJump1(data, B);
        System.out.println(System.currentTimeMillis() - start);
        set.add(ans);

        start = System.currentTimeMillis();
        ans = test.cheapestJump2(data, B);
        System.out.println(System.currentTimeMillis() - start);
        set.add(ans);

        System.out.println("the size is " + set.size());
        //for(int i = 0; i <100; i++)
         //   System.out.println(data[i]);
    }
}
