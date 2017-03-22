/**
 * Created by yuantian on 3/21/17.
 */

import java.util.*;

public class ShortestWordDistance_II_244 {
    Map<String, List<Integer>> map = null;

    public ShortestWordDistance_II_244(String[] words) {
        map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            if (!map.containsKey(w))
                map.put(w, new ArrayList<>());
            map.get(w).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        if (word1.equals(word2)) return 0;

        List<Integer> l1 = map.get(word1);
        List<Integer> l2 = map.get(word2);
        int min = Integer.MAX_VALUE;
        int i1 = 0, i2 = 0;
        while (i1 < l1.size() && i2 < l2.size()) {
            if (l1.get(i1) < l2.get(i2)) {
                min = Math.min(min, l2.get(i2) - l1.get(i1));
                i1++;
            } else {
                min = Math.min(min, l1.get(i1) - l2.get(i2));
                i2++;
            }
        }
        return min;
    }
}
