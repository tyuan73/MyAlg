/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/20/13
 * Time: 4:49 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class SubstringWithConcatenationOfAllWords30 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        int n = words[0].length();
        int k = words.length;
        if (s.length() < n * k) return ans;

        Map<String, Integer> dict = new HashMap<>();
        int[] target = new int[k];
        for (int i = 0; i < k; i++) {
            if (!dict.containsKey(words[i])) {
                dict.put(words[i], i);
                target[i] = 1;
            } else {
                target[dict.get(words[i])]++;
            }
        }

        for (int i = 0; i <= s.length() - n * k; i++) {
            int[] found = new int[k];
            int count = 0;
            for (int j = i; j < i + n * k; j += n) {
                String sub = s.substring(j, j + n);
                if (!dict.containsKey(sub)) break;
                int idx = dict.get(sub);
                if (++found[idx] <= target[idx])
                    count++;
                else
                    break;
            }
            if (count == k) ans.add(i);

        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }
}
