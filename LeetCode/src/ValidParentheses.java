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
}
