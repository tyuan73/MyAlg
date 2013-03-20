/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/5/13
 * Time: 2:29 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.ArrayList;

public class GenerateParentheses {
    public static void main(String[] args) {
        GenerateParentheses g = new GenerateParentheses();
        ArrayList<String> ret = g.generateParenthesis(4);

        for (String s : ret)
            System.out.println(s);
    }

    public ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> ret = new ArrayList<String>();
        helper(n, 0, new StringBuilder(), ret);
        return ret;
    }

    void helper(int open, int close, StringBuilder sb, ArrayList<String> ret) {
        if (open == 0 && close == 0) {
            ret.add(sb.toString());
            return;
        }

        if (open > 0) {
            sb.append('(');
            helper(open - 1, close + 1, sb, ret);
            sb.setLength(sb.length() - 1);
        }

        if (close > 0) {
            sb.append(')');
            helper(open, close - 1, sb, ret);
            sb.setLength(sb.length() - 1);
        }
    }
}
