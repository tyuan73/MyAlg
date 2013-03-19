import java.util.*;

/**
 * one of the bigger test case
 * 67 54 2987
 * 17 65 65 72 63 19 38 83 44 88 32 15 17 67 24 94 93 25 44 81 55 4 72 40 34 7 81 90 79 36 78 4 67 45 4 77 27 69 85 51 30 15 94 95 84 68 40 16 28 77 71 49 70 60 45 82 60 3 15 78 42 45 76 15 24 54 73
 *
 */
class LargestSumLessThanM {
    static public void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int m = in.nextInt();

        int[] d = new int[n];
        for (int i = 0; i < n; i++)
            d[i] = in.nextInt();

        Arrays.sort(d);
        int sum = 0;
        for (int i = 0; i < k; i++)
            sum += d[i];

        for (int j = k - 1; j >= 0; j--) {
            for (int i = k; i < n; i++) {
                int diff = d[i] - d[j];
                if (sum + diff >= m)
                    break;
                int x = d[i];
                d[i] = d[j];
                d[j] = x;
                sum += diff;
            }
        }

        System.out.println(sum);

        //http://en.wikipedia.org/wiki/Partition_%28number_theory%29
        //pre = 3000;
        long[][] dp = new long[sum + 1][sum + 1];
        dp[1][1] = 1;
        for (int i = 2; i <= sum; i++) {
            for (int j = i; j >= 1; j--) {
                if (j == i)
                    dp[i][j] = 1;
                else
                    dp[i][j] = (dp[i][j + 1] + dp[i - j][j]) % 1000000007;
            }
        }
        System.out.println(dp[sum][1]);
    }
}