/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/20/13
 * Time: 4:49 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class SubstringWithConcatenationOfAllWords {
    class Ele {
        int index;
        int count;

        Ele(int i, int c) {
            this.index = i;
            this.count = c;
        }
    }

    public ArrayList<Integer> findSubstring(String S, String[] L) {
        ArrayList<Integer> ret = new ArrayList<Integer>();

        HashMap<String, Ele> map = new HashMap<String, Ele>();
        for (int i = 0; i < L.length; i++) {
            if (map.containsKey(L[i])) {
                Ele e = map.get(L[i]);
                e.count++;
            } else {
                map.put(L[i], new Ele(i, 1));
            }
        }

        int[] used = new int[L.length];

        final int len = L[0].length();
        final int total = L.length;

        int count = 0;
        for (int i = 0; i <= S.length() - len * total; i++) {
            count = 0;
            for (int j = 0; j < total; j++) {
                String substr = S.substring(i + j * len, i + (j + 1) * len);
                if (map.containsKey(substr)) {
                    Ele e = map.get(substr);
                    if (used[e.index] < e.count) {
                        used[e.index]++;
                        count++;
                    } else
                        break;
                } else
                    break;
            }
            if (count == total)
                ret.add(i);
            Arrays.fill(used, 0);
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }
}
