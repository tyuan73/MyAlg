/**
 * Created by yuantian on 6/12/17.
 */

import java.util.*;

public class AddBoldTaginString616 {
    /**
     * Use marks to merge intervals.
     */
    public String addBoldTag(String s, String[] dict) {
        int n = s.length();
        int[] mark = new int[n + 1];
        for (String d : dict) {
            int j = -1;
            while ((j = s.indexOf(d, j + 1)) >= 0) {
                mark[j]++;
                mark[j + d.length()]--;
            }
        }

        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            int cur = sum + mark[i];
            if (cur > 0 && sum == 0)
                sb.append("<b>");
            if (cur == 0 && sum > 0)
                sb.append("</b>");
            if (i < n)
                sb.append(s.charAt(i));
            sum = cur;
        }
        return sb.toString();
    }

    /**
     * Use str.startsWith(prefix, offset). Sort all string in dict by length first.
     */
    public String addBoldTag2(String s, String[] dict) {
        int n = s.length();
        int[] mark = new int[n + 1];
        Arrays.sort(dict, new Comparator<String>() {
            public int compare(String a, String b) {
                return b.length() - a.length();
            }
        });

        // somehow in leetcode, lambda is significant slower than Comparator version.
        // Arrays.sort(dict, (a, b) -> (b.length() - a.length()));

        for (int i = 0; i < s.length(); i++) {
            for (String d : dict) {
                if (s.startsWith(d, i)) {
                    mark[i]++;
                    mark[i + d.length()]--;
                    break; // since we sort the dict by length.
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            int cur = sum + mark[i];
            if (cur > 0 && sum == 0)
                sb.append("<b>");
            if (cur == 0 && sum > 0)
                sb.append("</b>");
            if (i < n)
                sb.append(s.charAt(i));
            sum = cur;
        }
        return sb.toString();
    }

    /**
     * Use KMP to search. Faster? not sure. but all top competitors were using it.
     */
    public String addBoldTag1(String s, String[] dict) {
        int n = s.length();
        int[] mark = new int[n + 1];
        for (String d : dict) {
            int[] prefix = kmp(d);
            int j = 0;
            for (int i = 0; i < s.length(); i++) {
                while (j > 0 && d.charAt(j) != s.charAt(i))
                    j = prefix[j - 1];
                if (d.charAt(j) == s.charAt(i))
                    j++;
                if (j == d.length()) {
                    mark[i + 1]--;
                    mark[i + 1 - d.length()]++;
                    j = prefix[j - 1];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            int cur = sum + mark[i];
            if (cur > 0 && sum == 0)
                sb.append("<b>");
            if (cur == 0 && sum > 0)
                sb.append("</b>");
            if (i < n)
                sb.append(s.charAt(i));
            sum = cur;
        }
        return sb.toString();
    }

    private int[] kmp(String p) {
        int[] prefix = new int[p.length()];
        int j = 0;
        for (int i = 1; i < p.length(); i++) {
            while (j > 0 && p.charAt(j) != p.charAt(i))
                j = prefix[j - 1];
            if (p.charAt(j) == p.charAt(i))
                j++;
            prefix[i] = j;
        }
        return prefix;
    }
}
