/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/26/13
 * Time: 12:16 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class WordLadderII {
    public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();

        HashMap<String, String> parent = new HashMap<String, String>();
        for (String s : dict)
            parent.put(s, null);
        parent.put(start, "");

        ArrayList<String> nextLevel = new ArrayList<String>();
        nextLevel.add(start);
        boolean gotIt = false;
        while (nextLevel.size() > 0) {
            ArrayList<String> temp = new ArrayList<String>();
            for (String s : nextLevel) {
                ArrayList<String> temp1 = getChildren(s, parent);
                for (String child : temp1) {
                    if (child.equals(end)) {
                        gotIt = true;
                        parent.put(child, s);
                        addPath(end, parent, ret);
                        break;
                    }
                    temp.add(child);
                }
            }
            if (gotIt)
                break;
            nextLevel = temp;
        }
        return ret;
    }

    void addPath(String end, HashMap<String, String> parent, ArrayList<ArrayList<String>> ret) {
        Stack<String> stack = new Stack<String>();
        stack.push(end);
        while (stack.peek().length() > 0) {
            stack.push(parent.get(stack.peek()));
        }
        ArrayList<String> path = new ArrayList<String>();
        stack.pop();
        while (!stack.empty()) {
            path.add(stack.pop());
        }
        ret.add(path);
    }

    ArrayList<String> getChildren(String s, HashMap<String, String> parent) {
        ArrayList<String> ret = new ArrayList<String>();
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            char ch = str[i];
            for (char a = 'a'; a <= 'z'; a++) {
                if (a != ch) {
                    str[i] = a;
                    String newWord = new String(str);
                    if (parent.containsKey(newWord) && parent.get(newWord) == null)
                        ret.add(newWord);
                }
            }
            str[i] = ch;
        }
        return ret;
    }

}
