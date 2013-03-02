/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 1/24/13
 * Time: 1:14 PM
 * To change this template use File | Settings | File Templates.
 *
 *
 *
 * <p/>
 * 碰到的一题online coding
 * <p/>
 * Given a string s, return the shortest substring that is only occurring once.
 * Examples:
 * s="aabbabbaab" retunrs either "bab" or "baa"
 * s="aaaa" returns "aaaa"
 *
 *
 */
public class UniqueShortestSubstring {
    public static void main(String[] args) {
        String s = "aabbabbaab";

        // optimized in space use
        int n = s.length();
        int min = n, last = n;
        int[] dp = new int[n+1];
        for(int i = 1; i <= n; i++) {
            int max = 1;
            for(int j = n; j >= 1; j--) {
                if (s.charAt(i-1) == s.charAt(j-1))
                    dp[j] = dp[j-1] + 1;
                else
                    dp[j] = 0;
                if(i != j && dp[j] >= max)
                    max = dp[j]+1;
            }
            if(max <= i && max < min) {
                min = max;
                last = i;
            }
        }

        System.out.println(min);
        System.out.println(s.substring(last-min, last));

        /****** origianl ***************
        int n = s.length();
        int min = n, last = n;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            int max = 1;
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                if (i != j && dp[i][j] >= max)
                    max = dp[i][j] + 1;
            }
            if (max <= i && max < min) {
                min = max;
                last = i;
            }
        }

        for (int[] x : dp) {
            for (int y : x)
                System.out.printf("%5d", y);
            System.out.println();
        }

        System.out.println(min);
        System.out.println(s.substring(last - min, last));
        **********************/
    }
}
