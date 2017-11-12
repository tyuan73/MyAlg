/*

*/

import java.util.*;
import java.io.*;

public class NumberofAtoms726 {
    public String countOfAtoms(String formula) {
        HashMap<String, Integer> cur = new HashMap<>();
        check(formula, 0, cur);
        TreeMap<String, Integer> map = new TreeMap<>(cur);
        StringBuilder sb = new StringBuilder();
        for (String k : map.keySet()) {
            sb.append(k);
            if (map.get(k) > 1)
                sb.append(map.get(k));
        }
        return sb.toString();
    }

    private int check(String f, int i, HashMap<String, Integer> cur) {
        while (i < f.length()) {
            if (f.charAt(i) >= 'A' && f.charAt(i) <= 'Z') {
                String e = eleName(f, i);
                int[] num = readNum(f, i + e.length());
                cur.put(e, cur.getOrDefault(e, 0) + num[0]);
                i += e.length() + num[1];
            } else if (f.charAt(i) == ')') {
                return i;
            } else if (f.charAt(i) == '(') {
                HashMap<String, Integer> map = new HashMap<>();
                int len = check(f, i + 1, map);
                int[] num = readNum(f, len + 1);
                for (String s : map.keySet()) {
                    cur.put(s, cur.getOrDefault(s, 0) + map.get(s) * num[0]);
                }
                i = len + num[1] + 1;
            }
        }
        return 0;
    }

    private String eleName(String f, int j) {
        int i = j + 1;
        while (i < f.length() && f.charAt(i) >= 'a' && f.charAt(i) <= 'z')
            i++;
        return f.substring(j, i);
    }

    private int[] readNum(String f, int j) {
        int[] ret = {0, 0};
        for (int i = j; i < f.length(); i++) {
            if (f.charAt(i) < '0' || f.charAt(i) > '9')
                break;
            ret[0] = ret[0] * 10 + f.charAt(i) - '0';
            ret[1]++;
        }
        if (ret[1] == 0)
            ret[0] = 1;
        return ret;
    }
}