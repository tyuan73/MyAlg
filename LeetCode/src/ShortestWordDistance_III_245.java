/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/22/17
 * Time: 10:58 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class ShortestWordDistance_III_245 {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        boolean same = word1.equals(word2);
        int pre1 = -words.length, pre2 = -words.length;
        int ans = words.length;
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            if (w.equals(word1)) {
                if (same) {
                    ans = Math.min(ans, i - pre1);
                } else {
                    ans = Math.min(ans, i - pre2);
                }
                pre1 = i;
            } else if (w.equals(word2)) {
                ans = Math.min(ans, i - pre1);
                pre2 = i;
            }
        }
        return ans;
    }
}
