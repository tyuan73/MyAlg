/*

*/

import java.util.*;
import java.io.*;

public class PathSumIV666 {
    public int pathSum(int[] nums) {
        int[] path = new int[60];
        int total = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            int val = nums[i] % 10, pos = nums[i] / 10;
            int parent = pos / 10 * 10 - 10 + ((pos % 10) + 1) / 2;
            if (path[pos] == 0) path[pos] = 1;
            path[parent] += path[pos];
            total += val * path[pos];
        }

        return total;
    }
}