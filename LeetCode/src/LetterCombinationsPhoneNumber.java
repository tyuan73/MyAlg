/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/5/13
 * Time: 11:51 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class LetterCombinationsPhoneNumber {
    List<String> ret = null;
    StringBuilder sb = null;
    static char[][] map = {
            {' ', ' ', ' ', ' '}, // 0
            {' ', ' ', ' ', ' '}, // 1
            {'a', 'b', 'c', ' '}, // 2
            {'d', 'e', 'f', ' '}, // 3
            {'g', 'h', 'i', ' '}, // 4
            {'j', 'k', 'l', ' '}, // 5
            {'m', 'n', 'o', ' '}, // 6
            {'p', 'q', 'r', 's'}, // 7
            {'t', 'u', 'v', ' '}, // 8
            {'w', 'x', 'y', 'z'}  // 9
    };

    /*
    Three solutions are listed below:
        1. iterative solution with a stack
        2. iterative solution with an array
        3. recursive solution
     */

    // iterative solution with stack
    public List<String> letterCombinations1(String digits) {
        List<String> ret = new ArrayList<String>();
        char[] seq = new char[digits.length()];
        Stack<Integer> s = new Stack<Integer>();
        s.push(0);
        s.push(0);
        while (!s.empty()) {
            int index = s.pop();
            int c = s.pop();
            if (c == digits.length()) {
                ret.add(new String(seq));
                continue;
            }
            if (index == map[0].length || map[digits.charAt(c) - '0'][index] == ' ')
                continue;
            seq[c] = map[digits.charAt(c) - '0'][index];
            s.push(c);
            s.push(index + 1);
            s.push(c + 1);
            s.push(0);
        }
        return ret;
    }

    // iterative solution with an array. this
    // is basically the same as above, but the stack
    // is implemented with an array ("list") plus an "index"
    public List<String> letterCombinations2(String digits) {
        List<String> ret = new ArrayList<String>();

        int n = digits.length();
        char[] seq = new char[n];
        int[] list = new int[n + 1];
        int index = 0;
        while (index >= 0) {
            if (index == n) {
                ret.add(new String(seq));
                index--;
                continue;
            }
            if (list[index] == 4 || map[digits.charAt(index) - '0'][list[index]] == ' ') {
                index--;
                continue;
            }
            seq[index] = map[digits.charAt(index) - '0'][list[index]];
            list[index]++;
            index++;
            list[index] = 0;
        }
        return ret;
    }

    // recursive solution
    public List<String> letterCombinations(String digits) {
        ret = new ArrayList<String>();
        sb = new StringBuilder();

        listName(digits);

        return ret;
    }

    private void listName(String digits) {
        if (digits.length() == 0) {
            ret.add(sb.toString());
            return;
        }

        char[] chars = map[digits.charAt(0) - '0'];
        for (char ch : chars) {
            if (ch == ' ')
                break;
            sb.append(ch);
            listName(digits.substring(1));
            sb.setLength(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);

    }
}
