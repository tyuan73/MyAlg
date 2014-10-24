package poj1002;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

public class Main {
    static int[] map = {2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();
        ArrayList<String> input = new ArrayList<String>();
        while (total-- >= 0) {
            input.add(in.nextLine());
        }

        HashMap<String, Integer> hash = new HashMap<String, Integer>();
        for (String s : input) {
            process(s, hash);
        }

        //int count = 0;
        ArrayList<String> ret = new ArrayList<String>();
        for (String key : hash.keySet()) {
            int x = hash.get(key);
            if (x > 1) {
                ret.add(key.substring(0, 3) + "-" + key.substring(3, key.length()) + " " + x);
            }
        }
        if (ret.size() > 0) {
            String[] o = new String[ret.size()];
            o = ret.toArray(o);
            Arrays.sort(o);
            for (String s : o)
                System.out.println(s);
        } else
            System.out.println("No duplicates.");
    }

    static void process(String s, HashMap<String, Integer> hash) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= 'A' && ch < 'Z' && ch != 'Q') {
                sb.append(map[ch - 'A']);
            } else if (ch >= '0' && ch <= '9') {
                sb.append(ch);
            }
        }
        String key = sb.toString();
        if (hash.containsKey(key)) {
            int x = hash.get(key);
            hash.put(key, x + 1);
        } else
            hash.put(key, 1);
    }
}