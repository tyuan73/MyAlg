/**
 * Created by yuantian on 6/2/14.
 */
/*
Array A contains the elements, A1,A2…AN. And array B contains the elements, B1,B2…BN. There is a relationship between Ai and Bi, ∀ 1 ≤ i ≤ N, i.e.,
        any element Ai lies between 1 and Bi.

        Let cost S of an array A is defined as:

        image

        You have to print the largest possible value of S.

        Input Format
        The first line contains, T, the number of test cases. Each test case contains an integer, N, in first line. The second line of each test case contains N integers that denote the array B.

        Output Format
        For each test case, print the required answer in one line.

        Constraints
        1 ≤ T ≤ 20
        1 ≤ N ≤ 105
        1 ≤ Bi ≤ 100

        Sample Input

        1
        5
        10 1 10 1 10
        Sample Output

        36
        Explanation
        The maximum value occurs when A1=A3=A5=10 and A2=A4=1.
*/
import java.util.*;

public class SherlockAndCost {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            int[] b = new int[n];
            for(int i = 0; i < n; i++) {
                b[i] = in.nextInt();
            }

            int[][] dp = new int[n][2];
            for(int i = 1; i < n; i++) {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + b[i-1]-1);
                dp[i][1] = Math.max(dp[i-1][0] + b[i]-1, dp[i-1][1] + Math.abs(b[i]-b[i-1]));
            }

            System.out.println(Math.max(dp[n-1][0], dp[n-1][1]));
        }
    }
}
