/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/5/13
 * Time: 11:51 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class LetterCombinationsPhoneNumber {
    final static String[] map = {
            " ",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };

    public ArrayList<String> letterCombinations(String digits) {
        ArrayList<String> ret = new ArrayList<String>();
        helper(digits, 0, new StringBuilder(), ret);
        return ret;
    }

    void helper(String digits, int index, StringBuilder sb, ArrayList<String> ret) {
        if(index == digits.length()) {
            ret.add(sb.toString());
            return;
        }

        String s = map[digits.charAt(index) - '0'];
        if(s.length() == 0)
            helper(digits, index+1, sb, ret);
        else {
            for(int i = 0; i < s.length(); i++) {
                sb.append(s.charAt(i));
                helper(digits, index+1, sb, ret);
                sb.setLength(sb.length()-1);
            }
        }
    }

    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);

    }
}
