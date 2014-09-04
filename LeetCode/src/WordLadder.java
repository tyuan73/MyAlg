/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/26/13
 * Time: 11:52 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class WordLadder {
    /* BFS */
    public int ladderLength(String start, String end, Set<String> dict) {
        if (start.equals(end))
            return 1;

        Set<String> visited = new HashSet<String>();
        visited.add(start);
        List<String> pre = new ArrayList<String>();
        List<String> next = new ArrayList<String>();
        pre.add(start);
        int steps = 1;
        while (pre.size() > 0) {
            next.clear();
            for (String s : pre) {
                char[] ch = s.toCharArray();
                for (int j = 0; j < ch.length; j++) {
                    char orig = ch[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        ch[j] = c;
                        String s1 = new String(ch);
                        if (s1.equals(end))
                            return steps + 1;
                        else if (dict.contains(s1) && !visited.contains(s1)) {
                            visited.add(s1);
                            next.add(s1);
                        }
                    }
                    ch[j] = orig;
                }
            }
            steps++;
            List<String> temp = pre;
            pre = next;
            next = temp;
        }

        return 0;
    }

    /*
    public int ladderLength(String start, String end, HashSet<String> dict) {
        HashMap<String, Integer> visited = new HashMap<String, Integer>();
        for (String s : dict) {
            visited.put(s, -1);
        }
        visited.put(start, 1);

        ArrayList<String> nextLevel = new ArrayList<String>();
        nextLevel.add(start);
        while (nextLevel.size() > 0) {
            ArrayList<String> temp = new ArrayList<String>();
            for (String s : nextLevel) {
                int hops = visited.get(s) + 1;
                ArrayList<String> temp1 = getNext(s, visited);
                for (String str : temp1) {
                    if (str.equals(end))
                        return hops;
                    visited.put(str, hops);
                    temp.add(str);
                }
            }

            nextLevel = temp;
        }
        return 0;
    }

    ArrayList<String> getNext(String from, HashMap<String, Integer> visited) {
        ArrayList<String> ret = new ArrayList<String>();
        char[] str = from.toCharArray();
        for (int i = 0; i < str.length; i++) {
            char ch = str[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != ch) {
                    str[i] = c;
                    String newword = new String(str);
                    if (visited.containsKey(newword) && visited.get(newword) == -1)
                        ret.add(newword);
                }

            }
            str[i] = ch;
        }
        return ret;
    }
    */
}
