package weekly.week5.day4;

/**
 * Created by yuantian on 6/19/14.
 */

import java.util.*;

public class SwapPermutation {
    public static void main(String[] args) {
        HashSet<String> hash = new HashSet<String>();
        List<String>[] list = new List[22];
        for(int i = 0; i < 22; i++) {
            list[i] = new ArrayList<String>();
        }

        list[0].add("12345678");
        hash.add("12345678");
        for(int i = 0; i < 21; i++) {
            List<String> temp = list[i];
            for(String str : temp) {
                char[] chars = str.toCharArray();
                for(int k = 0; k < str.length()-1; k++) {
                    for(int j = k+1; j < str.length(); j++) {
                        char c = chars[k];
                        chars[k] = chars[j];
                        chars[j] = c;
                        String s = new String(chars);
                        if (!hash.contains(s)) {
                            hash.add(s);
                            list[i + 1].add(s);
                        }
                        c = chars[k];
                        chars[k] = chars[j];
                        chars[j] = c;
                    }
                }
            }
        }

        int total = 0;
        for(int i = 0; i < 22; i++) {
            total += list[i].size();
            System.out.println(list[i].size());
        }

        int f = 1;
        for(int i = 1; i <= 7; i++)
            f *= i;
        System.out.println("total = " + total);
        System.out.println("f = " + f);
    }
}
