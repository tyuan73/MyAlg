/**
 * Created by yuantian on 6/19/17.
 */

import java.util.*;

public class TaskScheduler621 {
    /**
     * Naive Solution. Better solution below.
     */
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for (char ch : tasks)
            count[ch - 'A']++;
        Arrays.sort(count);
        int ans = (count[25] - 1) * (n + 1) + 1;
        int col = 0, row = 0;
        for (int i = 24; i >= 0; i--) {
            if (count[i] == 0) break;
            if (count[i] == count[25]) {
                if (col >= n) {
                    ans += count[i];
                } else {
                    col++;
                    ans++;
                }
                continue;
            }
            if (col < n) {
                if (row + count[i] >= (count[25] - 1))
                    col++;
                row = (row + count[i]) % (count[25] - 1);
                if (col >= n)
                    ans += row;
            } else {
                ans += count[i];
            }
        }
        return ans;
    }

    /**
     * A much shorter solution.
     */
    public int leastInterval1(char[] tasks, int n) {
        int[] count = new int[26];
        for (char ch : tasks)
            count[ch - 'A']++;
        Arrays.sort(count);
        int maxCount = 0, i = 25;
        // count how many tasks have the same freq as the the most freq task.
        while (i >= 0 && count[i--] == count[25])
            maxCount++;

        return Math.max(tasks.length, (count[25] - 1) * (n + 1) + maxCount);
    }
}
