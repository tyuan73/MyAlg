/**
 * Created by yuantian on 6/7/17.
 */

import java.util.regex.*;
import java.util.*;

public class TagValidator591 {
    /**
     * Short and easy, but not best in performance.
     * <p>
     * The idea is to replace any direct closed tags in each round until there is none left.
     */
    public boolean isValid(String code) {
        code = code.replaceAll("<!\\[CDATA\\[.*?\\]\\]>", "c");

        String prev = "";
        while (!code.equals(prev)) {
            prev = code;
            code = code.replaceAll("<([A-Z]{1,9})>[^<]*</\\1>", ".");
        }

        return code.equals(".");
    }

    /**
     * Regex solution.
     * <p>
     * The idea is to replace all CDATA first and then split the string by "<".
     * The start of each split string is a tag. If first char is "/", it is an ending tag; otherwise, it
     * is an starting tag. Then you can use stack to verify the correctness.
     * <p>
     * A few notes:
     * 1. make sure the first spit string is empty - which means the code must start with "<";
     * 2. in the loop, make sure any time the stack is not empty except the 1st tag;
     * 3. check the last close tag, make sure there is nothing after the close tag;
     */
    public boolean isValid1(String code) {
        Pattern pattern = Pattern.compile("^/?([A-Z]{1,9})>(.*)");
        code = code.replaceAll("<!\\[CDATA\\[(.*?)]]>", ".");
        Stack<String> stack = new Stack<>();
        String[] str = code.split("<");
        if (str.length <= 2 || str[0].length() != 0) return false;
        for (int j = 1; j < str.length; j++) {
            if (j > 1 && stack.isEmpty()) return false;
            String s = str[j];
            Matcher matcher = pattern.matcher(s);

            if (!matcher.find()) return false;

            if (s.charAt(0) == '/') {
                if (stack.isEmpty() || !stack.pop().equals(matcher.group(1))) return false;
                if (j == str.length - 1 && matcher.group(2).length() != 0) return false;
            } else {
                stack.push(matcher.group(1));
            }
        }

        return stack.isEmpty();
    }

    /**
     * Maybe the best solution.
     * <p>
     * Scan the chars in string. If we find CDATA, skip to the end of ending "]]>";
     * if "</" is found, pop stack and check;
     * if "<" is found, push the tag to stack;
     * <p>
     * Note: any time in loop, make sure the stack is not empty, except the first char.
     * This is to make sure no string is not wrapped in a tag.
     */
    public boolean isValid2(String code) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < code.length(); ) {
            if (i > 0 && stack.isEmpty()) return false;
            if (code.startsWith("<![CDATA[", i)) {
                int j = i + 9;
                i = code.indexOf("]]>", j);
                if (i < 0 || stack.isEmpty()) return false;
                i += 3;
            } else if (code.startsWith("</", i)) {
                int j = i + 2;
                i = code.indexOf('>', j);
                if (i < 0 || i == j || i - j > 9) return false;
                String s = code.substring(j, i++);
                if (stack.isEmpty() || !stack.pop().equals(s)) return false;
            } else if (code.startsWith("<", i)) {
                int j = i + 1;
                i = code.indexOf('>', j);
                if (i < 0 || i == j || i - j > 9) return false;
                for (int k = j; k < i; k++) {
                    if (!Character.isUpperCase(code.charAt(k))) return false;
                }
                String s = code.substring(j, i++);
                stack.push(s);
            } else {
                i++;
            }
        }
        return stack.isEmpty();
    }
}
