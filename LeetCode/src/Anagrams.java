/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/26/13
 * Time: 6:01 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Anagrams {
    /**
     * First try, O(n^2). it can not pass large judge.
     *
     * @param strs
     * @return
     */
    public ArrayList<String> anagrams(String[] strs) {
        int n = strs.length;
        int[][] map = new int[n][26];
        for (int i = 0; i < n; i++) {
            String s = strs[i];
            for (int j = 0; j < s.length(); j++)
                map[i][s.charAt(j) - 'a']++;
        }

        boolean[] used = new boolean[n];
        ArrayList<String> ret = new ArrayList<String>();
        for (int i = 0; i < n - 1; i++) {
            if (used[i])
                continue;
            for (int j = i + 1; j < n; j++) {
                if (used[j])
                    continue;
                if (areTheSame(map, i, j)) {
                    if (!used[i]) {
                        ret.add(strs[i]);
                        used[i] = true;
                    }
                    ret.add(strs[j]);
                    used[j] = true;
                }
            }
        }

        return ret;
    }

    boolean areTheSame(int[][] map, int i, int j) {
        for (int k = 0; k < 26; k++)
            if (map[i][k] != map[j][k])
                return false;
        return true;
    }

    /**
     * a better solution which can pass large judge.
     *
     * @param strs
     * @return
     */
    public ArrayList<String> anagrams2(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

        for (String s : strs) {
            char[] chs = s.toCharArray();
            Arrays.sort(chs);
            String newStr = new String(chs);
            if (map.containsKey(newStr)) {
                ArrayList<String> list = map.get(newStr);
                list.add(s);
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(s);
                map.put(newStr, list);
            }
        }

        ArrayList<String> ret = new ArrayList<String>();
        for (String s : map.keySet()) {
            ArrayList<String> list = map.get(s);
            if (list.size() > 1) {
                ret.addAll(list);
            }
        }

        return ret;
    }
}
