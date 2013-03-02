/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 1/21/13
 * Time: 10:56 PM
 *
 * This is to test string matching where '?' matches exactly one letter while '*' matches any length
 * letters including 0 in length.
 *
 */
public class StringMatch {
    public static void main(String[] args ){
        String s = "abjkciwn jkjachfuhufhuhu";
        String p = "?*hu";

        boolean[][] dp = new boolean[p.length()+1][s.length()+1];
        dp[0][0] = true;
        for(int i = 1; i <= p.length(); i++) {
            if(p.charAt(i-1) == '*')
                dp[i][0] = dp[i-1][0];
        }
        for(int i = 1; i <= p.length(); i++) {
            char pch = p.charAt(i-1);
            for(int j = 1; j <= s.length(); j++) {
                if(pch == '*')
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-1] || dp[i][j-1];
                else if(pch == '?' || pch == s.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
            }
        }

        System.out.println(dp[p.length()][s.length()] ? "Yes" : "No");
    }
}
