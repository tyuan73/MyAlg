/**
 * Created by yuantian on 8/10/15.
 */

import java.util.*;

public class Test557D {
    static public void main(String[] args) {
        int N = 10;

        int count = 0;
        String[] list = new String[400];
        for (int i = 1; i <= N; i++) {
            int max = 1 << i;
            for (int j = 0; j < max; j++) {
                if (checkP(j, i))
                    list[count++] = getStr(j, i);
                    //count++;
            }
        }
        System.out.println(count);
        Arrays.sort(list);
        for (int i = 0; i < count; i++) {
            System.out.println(list[i]);
        }
    }

    static String getStr(int j, int n) {
        StringBuffer sb = new StringBuffer();
        for (int bit = 1 << (n - 1); bit > 0; bit >>= 1) {
            if ((j & bit) > 0) {
                sb.append('b');
            } else {
                sb.append('a');
            }
        }
        return sb.toString();
    }

    static boolean checkP(int j, int n) {
        for (int l = 1, r = 1 << (n - 1); l < r; l <<= 2, r >>= 2) {
            if (((j & l) > 0 && (j & r) == 0) || ((j & l) == 0 && (j & r) > 0))
                return false;
        }
        return true;
    }
}
