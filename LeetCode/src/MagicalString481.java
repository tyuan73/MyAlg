/*

*/

import java.util.*;
import java.io.*;

public class MagicalString481 {
    public int magicalString(int n) {
        if (n <= 1) return n;
        int[] nums = new int[n];
        nums[0] = 1;
        nums[1] = 2;
        int ans = 0;
        for (int i = 0, j = 0, ele = 1; j < n; i++, ele ^= 3) {
            for (int k = 0; k < nums[i] && j < n; k++) {
                nums[j++] = ele;
                if (ele == 1) ans++;
            }
        }
        return ans;
    }
}