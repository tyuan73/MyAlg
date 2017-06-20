/**
 * Created by yuantian on 6/19/17.
 */

import java.util.*;

public class RearrangeStringkDistanceApart358 {
    /**
     * First try. ACed. Pretty messy. I think it can be improved.
     */
    public String rearrangeString(String s, int k) {
        //if (k == 0) return s;
        int[][] count = new int[26][2];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a'][0]++;
            count[s.charAt(i) - 'a'][1] = s.charAt(i);
        }
        Arrays.sort(count, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int max = count[25][0], maxCount = 0, n = 25;
        while (n >= 0 && count[n--][0] == max)
            maxCount++;
        if (s.length() < (max - 1) * k + maxCount)
            return "";

        if (max == 1) return s;

        int col = (s.length() - maxCount) / (max - 1) + 1;
        char[][] ans = new char[max][col];

        for (int j = 0; j < maxCount; j++) {
            for (int i = 0; i < max; i++) {
                ans[i][j] = (char) count[25 - j][1];
            }
        }
        int idx = 25 - maxCount;
        for (int j = maxCount; j < col; j++) {
            for (int i = 0; i < max - 1; i++) {
                if (idx < 0 || count[idx][0] == 0) break;
                ans[i][j] = (char) count[idx][1];
                if (--count[idx][0] == 0) idx--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < col; j++) {
                if (ans[i][j] != 0)
                    sb.append(ans[i][j]);
            }
        }
        return sb.toString();
    }

    /**
     * I think this is much faster.
     */
    public String rearrangeString1(String s, int k) {
        int len = s.length();
        int[][] count = new int[26][2];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            count[ch - 'a'][0]++;
            count[ch - 'a'][1] = ch;
        }
        Arrays.sort(count, new Comparator<int[]>() {
            // sort in reverse order
            public int compare(int[] a, int[] b) {
                return b[0] - a[0];
            }
        });

        int max = count[0][0], maxCount = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i][0] == max) maxCount++;
            else break;
        }

        if (len - maxCount < (max - 1) * k) return "";
        if (max == 1) return s;

        int ext = (len - maxCount) % (max - 1);
        int col = (len - maxCount) / (max - 1);
        char[] ans = new char[len];
        int start = 0, r = 0, next = 0;
        for (int idx = 0; idx < 26; idx++) {
            while (count[idx][0]-- > 0) {
                ans[next] = (char) count[idx][1];
                if (r++ < ext) next += col + 1;
                else next += col;
                if (next >= len) {
                    r = 0;
                    next = ++start;
                }
            }
        }
        return new String(ans);
    }
}
