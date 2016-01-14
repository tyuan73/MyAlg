/**
 * Created by yuantian on 1/14/16.
 */

import java.util.*;

public class OJ316RemoveDuplicateLetters {

    public String removeDuplicateLetters(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        boolean[] used = new boolean[26];

        Stack<Character> stk = new Stack<>();
        stk.push((char) ('a' - 1));

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (used[ch - 'a']) {
                count[ch - 'a']--;
                continue;
            }
            while (ch < stk.peek() && count[stk.peek() - 'a'] > 1) {
                char x = stk.pop();
                used[x - 'a'] = false;
                count[x - 'a']--;
            }
            stk.push(ch);
            used[ch - 'a'] = true;
        }

        char[] res = new char[stk.size() - 1];
        for (int i = res.length - 1; i >= 0; i--)
            res[i] = stk.pop();
        return new String(res);
    }

    public String removeDuplicateLettersWithouStack(String s) {
        int[] count = new int['z' + 1];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }
        boolean[] used = new boolean['z' + 1];
        char[] res = new char[s.length() + 1];

        int idx = 1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (used[ch]) {
                count[ch]--;
                continue;
            }
            while (ch < res[idx - 1] && count[res[idx - 1]] > 1) {
                idx--;
                used[res[idx]] = false;
                count[res[idx]]--;
            }
            res[idx++] = ch;
            used[ch] = true;
        }

        return String.copyValueOf(res, 1, idx - 1);
    }
}
