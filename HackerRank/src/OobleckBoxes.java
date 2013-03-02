/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 2/25/13
 * Time: 12:51 AM
 * To change this template use File | Settings | File Templates.
 */
import java.util.*;

public class OobleckBoxes {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        if(m == 1) {
            System.out.println(1);
            return;
        }

        int[] dp = new int[n+1];
        Arrays.fill(dp, 1);
        for(long base = m; base <= n; base *= m) {
            for(int col = (int)base; col <= n; col++) {
                dp[col] = (dp[col] + dp[col-(int)base]) % 1000000007;
            }
        }

        System.out.println(dp[n]);
    }
}
