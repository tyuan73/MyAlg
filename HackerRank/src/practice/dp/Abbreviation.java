package practice.dp;

/**
 * Created by yuantian on 5/5/17.
 */

import java.io.*;
import java.util.*;

public class Abbreviation {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        in.nextLine();
        while (q-- > 0) {
            String a = in.nextLine();
            String b = in.nextLine();

            System.out.println(match(a, b) ? "YES" : "NO");
        }
    }

    static private boolean match(String a, String b) {
        int n = a.length(), m = b.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= a.length(); i++) {
            if (a.charAt(i - 1) >= 'a')
                dp[0][i] = true;
            else
                break;
        }
        for (int i = 1; i <= m; i++) {
            char chb = b.charAt(i - 1), chb1 = (char) (chb - 'A' + 'a');
            for (int j = 1; j <= n; j++) {
                char cha = a.charAt(j - 1);
                if ((dp[i][j - 1] && cha >= 'a') || (dp[i - 1][j - 1] && (cha == chb || cha == chb1)))
                    dp[i][j] = true;
            }
        }
        return dp[m][n];
    }
}