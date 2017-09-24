/*

*/

import java.util.*;
import java.io.*;

public class NextClosestTime681 {
    /**
     * O(24 * 60)
     */
    public String nextClosestTime(String time) {
        Set<Character> set = new HashSet<>();
        for (char x : time.toCharArray()) set.add(x);
        String[] str = time.split(":");
        int t = Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
        for (int i = 1; i <= 24 * 60; i++) {
            String ans = format(t + i);
            if (valid(ans, set)) return ans;
        }
        return null;
    }

    private boolean valid(String a, Set<Character> set) {
        for (int i = 0; i < a.length(); i++)
            if (!set.contains(a.charAt(i))) return false;
        return true;
    }

    private String format(int time) {
        if (time >= 24 * 60) time -= 24 * 60;
        int h = time / 60, m = time % 60;
        return (h < 10 ? "0" + h : "" + h) + ":" + (m < 10 ? "0" + m : "" + m);
    }

    /**
     * This is faster: O(4 * 4 * 4 * 4)
     */
    public String nextClosestTime1(String time) {
        int[] digits = new int[]{time.charAt(0) - '0', time.charAt(1) - '0', time.charAt(3) - '0', time.charAt(4) - '0'};
        int t = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));
        int min = 100000, minH = 0, minM = 0;
        for (int i = 0; i < 4; i++) {
            if (digits[i] > 2) continue;
            for (int j = 0; j < 4; j++) {
                int h = digits[i] * 10 + digits[j];
                if (h >= 24) continue;
                for (int q = 0; q < 4; q++) {
                    if (digits[q] >= 6) continue;
                    for (int p = 0; p < 4; p++) {
                        int m = digits[q] * 10 + digits[p];
                        int diff = h * 60 + m - t;
                        if (diff <= 0) diff += 24 * 60;
                        if (diff < min) {
                            min = diff;
                            minH = h;
                            minM = m;
                        }
                    }
                }
            }
        }
        //if (min == 100000) return time;
        return (minH < 10 ? "0" + minH : "" + minH) + ":" + (minM < 10 ? "0" + minM : "" + minM);
    }

}