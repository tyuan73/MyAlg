/**
 * Created by yuantian on 3/21/17.
 */

import java.util.*;

public class ShortestWordDistance243 {
    public int shortestDistance(String[] words, String word1, String word2) {
        if (word1.equals(word2)) return 0;
        int min = words.length;
        int pre1 = -min, pre2 = -min;
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            if (w.equals(word1)) {
                min = Math.min(min, i - pre2);
                pre1 = i;
            } else if (w.equals(word2)) {
                min = Math.min(min, i - pre1);
                pre2 = i;
            }
        }
        return min;
    }
}
