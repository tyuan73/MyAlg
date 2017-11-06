

public class FourKeysKeyboard651 {
    // O(n ^ 2)
    public int maxA(int N) {
        int[] dp = new int[N + 1];
        for (int i = 0; i <= N; i++) dp[i] = i;
        for (int i = 1; i <= N; i++) {
            for (int j = i + 3, k = 2 * dp[i]; j <= N; j++, k += dp[i]) {
                dp[j] = Math.max(dp[j], k);
            }
        }
        return dp[N];
    }

    // O(n)
    public int maxA1(int N) {
        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (i < 6)
                dp[i] = i;
            else
                dp[i] = Math.max(dp[i - 4] * 3, dp[i - 5] * 4);
        }
        return dp[N];
    }


    // O(0)
    public int maxA2(int N) {
        if (N <= 6) return N;
        if (N == 10) return 20;
        int n = N / 5 + 1, n3 = n * 5 - 1 - N;
        return (int) Math.pow(3, n3) * (int) Math.pow(4, n - n3);
    }
}
