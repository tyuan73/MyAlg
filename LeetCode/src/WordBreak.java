/**
 * Created by yuantian on 9/4/14.
 */

import java.util.*;

public class WordBreak {
    public boolean wordBreak(String s, Set<String> dict) {
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for(int i = 1; i <= s.length(); i++) {
            for(int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    continue;
                }

            }
        }
        return dp[s.length()];
    }
}
