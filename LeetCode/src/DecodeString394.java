/**
 * Created by yuantian on 3/2/17.
 */

import java.util.*;

public class DecodeString394 {
    public String decodeString(String s) {
        int[] pair = new int[s.length()];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[')
                stack.push(i);
            else if (s.charAt(i) == ']') {
                int pre = stack.pop();
                pair[pre] = i;
            }
        }

        return decode(s, 0, s.length() - 1, pair);
    }

    String decode(String s, int from, int to, int[] pair) {
        int repeat = 0;
        StringBuilder sb = new StringBuilder();
        int i = from;
        while (i <= to) {
            char ch = s.charAt(i++);
            if (ch >= '0' && ch <= '9')
                repeat = repeat * 10 + ch - '0';
            else if (ch == '[') {
                String childStr = decode(s, i, pair[i - 1] - 1, pair);
                for (int j = 0; j < repeat; j++)   // these 3 lines can be just: while(repeat-- > 0) ....
                    sb.append(childStr);
                repeat = 0;
                i = pair[i - 1] + 1;
            } else {
                sb.append(ch);
            }

        }
        return sb.toString();
    }
}
