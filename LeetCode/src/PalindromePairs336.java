/**
 * Created by yuantian on 4/17/17.
 */

import java.util.*;

public class PalindromePairs336 {
    public List<List<Integer>> palindromePairs(String[] words) {
        int n = words.length;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++)
            map.put(words[i], i);

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String str = words[i];
            for (int j = 0; j < str.length(); j++) {
                if (isPalindrome(str, j)) {
                    StringBuilder rev = new StringBuilder(str.substring(j + 1));
                    String revStr = rev.reverse().toString();
                    if (map.containsKey(revStr))
                        ans.add(getPair(map.get(revStr), i));
                }
            }
            StringBuilder sb = new StringBuilder(str);
            str = sb.reverse().toString();
            if (map.containsKey(str) && map.get(str) != i) {
                ans.add(getPair(i, map.get(str)));
            }
            for (int j = 0; j < str.length(); j++) {
                if (isPalindrome(str, j) && map.containsKey(str.substring(j + 1))) {
                    ans.add(getPair(i, map.get(str.substring(j + 1))));
                }
            }
        }
        return ans;
    }

    private List<Integer> getPair(int i, int j) {
        List<Integer> list = new ArrayList<>();
        list.add(i);
        list.add(j);
        return list;
    }

    private boolean isPalindrome(String str, int j) {
        for (int l = 0, r = j; l < r; l++, r--) {
            if (str.charAt(l) != str.charAt(r))
                return false;
        }
        return true;
    }
}
