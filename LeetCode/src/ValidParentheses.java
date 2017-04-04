/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/25/13
 * Time: 4:21 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        stack.push('0');
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '}':
                    if (stack.pop() != '{')
                        return false;
                    break;
                case ']':
                    if (stack.pop() != '[')
                        return false;
                    break;
                case ')':
                    if (stack.pop() != '(')
                        return false;
                    break;
                default:
                    stack.push(ch);
            }
        }

        return (stack.size() == 1);
    }

    /**
     * A more concise version
     */
    public boolean isValid1(String s) {
        Stack<Character> sk = new Stack<>();
        sk.push('0');
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                sk.push(')');
            } else if (ch == '[') {
                sk.push(']');
            } else if (ch == '{') {
                sk.push('}');
            } else if (ch != sk.pop())
                return false;
        }
        return sk.pop() == '0';
    }
}
