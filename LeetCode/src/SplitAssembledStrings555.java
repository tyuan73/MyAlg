/**
 * Created by yuantian on 4/17/17.
 */

import java.util.*;

public class SplitAssembledStrings555 {
    public String splitLoopedString(String[] strs) {
        int n = strs.length;
        int[] index = new int[n + 1];
        int end = 0;
        StringBuilder all = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String str = needReverse(strs[i]) ? reverse(strs[i]) : strs[i];
            all.append(str);
            index[i] = end;
            end += str.length();
        }
        index[n] = end;

        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String str = strs[i];
            for (int j = 0; j < str.length(); j++) {
                list.add(str.substring(j) + all.substring(index[i + 1]) + all.substring(0, index[i]) + str.substring(0, j));
            }
            str = reverse(str);
            for (int j = 0; j < str.length(); j++) {
                list.add(str.substring(j) + all.substring(index[i + 1]) + all.substring(0, index[i]) + str.substring(0, j));
            }
        }

        Collections.sort(list);
        return list.get(list.size() - 1);
    }

    private boolean needReverse(String str) {
        for (int l = 0, r = str.length() - 1; l < r; l++, r--) {
            if (str.charAt(r) > str.charAt(l))
                return true;
            else if (str.charAt(r) < str.charAt(l))
                return false;
        }
        return false;
    }

    private String reverse(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }
}
