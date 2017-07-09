/**
 * Created by miaomiao on 7/9/17.
 */

import java.util.*;

public class DecodeWaysII639 {
    static final int[][] map = new int[58][58];

    static {
        Arrays.fill(map['*'], 1);
        map['*']['*'] = 15;
        map['*']['0'] = 2;
        map['*']['1'] = 2;
        map['*']['2'] = 2;
        map['*']['3'] = 2;
        map['*']['4'] = 2;
        map['*']['5'] = 2;
        map['*']['6'] = 2;
        Arrays.fill(map['1'], 1);
        map['1']['*'] = 9;
        Arrays.fill(map['2'], 1);
        map['2']['*'] = 6;
        map['2']['7'] = 0;
        map['2']['8'] = 0;
        map['2']['9'] = 0;
        Arrays.fill(map[0], 1);
        map[0]['*'] = 9;
        map[0]['0'] = 0;
    }

    public int numDecodings(String s) {
        long cur = 1, pre = 0;
        char ch = 0, ch1 = '0';
        for (int i = s.length() - 1; i >= 0; i--) {
            ch = s.charAt(i);
            cur = (map[ch][ch1] * pre + map[0][(ch1 = ch)] * (pre = cur)) % 1000000007;
        }

        return (int) cur;
    }
}
