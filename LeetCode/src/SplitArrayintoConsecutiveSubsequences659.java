/*

*/

import java.util.*;
import java.io.*;

public class SplitArrayintoConsecutiveSubsequences659 {
    /**
     * First AC solution
     */
    public boolean isPossible(int[] nums) {
        int n = nums.length;
        int[] count = new int[n];
        count[0] = 1;
        int size = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1]) count[size]++;
            else {
                nums[++size] = nums[i];
                count[size] = 1;
            }
        }

        int[] len = new int[n];
        int low = 0, high = count[0] - 1;
        for (int i = 0; i <= high; i++) len[i] = 1;
        for (int i = 1; i <= size; i++) {
            int bottom = Math.max(low, high - count[i] + 1);
            int top = bottom + count[i] - 1;
            if (nums[i] > nums[i - 1] + 1) {
                for (int j = low; j <= high; j++) {
                    if (len[j] < 3) return false;
                    len[j] = 0;
                }
                for (int j = bottom; j <= top; j++)
                    len[j] = 1;
                low = bottom;
                high = top;
            } else {
                for (int j = low; j < bottom; j++) {
                    if (len[j] < 3) return false;
                }
                for (int j = bottom; j <= top; j++)
                    len[j]++;
                low = bottom;
                high = top;
            }
        }
        for (int i = low; i <= high; i++)
            if (len[i] < 3) return false;
        return true;
    }

    /**
     * Improved version
     */
    public boolean isPossible1(int[] nums) {
        int n = nums.length;
        int[] count = new int[n + 1];
        int size = -1;
        for (int i = 0; i < n; i++) {
            if (i == 0 || nums[i] != nums[i - 1])
                nums[++size] = nums[i];
            count[size]++;
        }

        int[] len = new int[n];
        int low = 0, high = 0;
        for (int i = 0; i <= size + 1; i++) {
            int bottom = Math.max(low, high - count[i]);
            int top = bottom + count[i];
            if (i == 0 || i == size + 1 || nums[i] == nums[i - 1] + 1) {
                high = bottom;
            }
            for (int j = low; j < high; j++) {
                if (len[j] < 3) return false;
                len[j] = 0;
            }
            for (int j = bottom; j < top; j++)
                len[j]++;
            low = bottom;
            high = top;
        }
        return true;
    }

}