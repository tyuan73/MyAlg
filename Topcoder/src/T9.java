import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class T9 {
    public String message(String[] part, String[] dict, String[] keystr) {
        int[] map = new int[26];
        for (int i = 0; i < part.length; i++) {
            String str = part[i];
            for (int j = 0; j < str.length(); j++) {
                map[str.charAt(j) - 'a'] = i + 1;
            }
        }

        String[] keys = new String[dict.length];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dict.length; i++) {
            sb.setLength(0);
            String str = dict[i];
            for (int j = 0; j < str.length(); j++) {
                sb.append(map[str.charAt(j) - 'a']);
            }
            keys[i] = sb.toString();
        }

        sort(keys, dict);

        sb.setLength(0);
        for (String str : keystr)
            sb.append(str);
        String keytext = sb.toString();
        sb.setLength(0);
        int start = 0;
        int n = keytext.length();
        for (int i = 0; i < n; i++) {
            char ch = keytext.charAt(i);
            if (ch == '0') {
                if (start < i) {
                    int index = find(keytext.substring(start, i), keys);
                    sb.append(dict[index]);
                }
                sb.append(' ');
                start = i + 1;
                continue;
            }
            if (ch == '*' || ch == '#') {
                int index = find(keytext.substring(start, i), keys);
                int count = 0;
                for (; i < n; i++) {
                    if (keytext.charAt(i) != '#' && keytext.charAt(i) != '*') {
                        break;
                    }
                    count += keytext.charAt(i) == '#' ? 1 : 5;
                }
                sb.append(dict[index + count]);
                start = i;
                i--;
            }
        }
        if (start < n) {
            int index = find(keytext.substring(start, keytext.length()), keys);
            sb.append(dict[index]);
        }

        return sb.toString();
    }

    int find(String s1, String[] keys) {
        int l = 0, r = keys.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (compare(s1, keys[mid]) > 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    void sort(String[] keys, String[] dict) {
        int n = keys.length;
        for (int i = 1; i < n; i++) {
            String key = keys[i];
            String d = dict[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                int x = compare(key, keys[j]);
                if (x < 0) {
                    keys[j + 1] = keys[j];
                    dict[j + 1] = dict[j];
                } else if (x == 0) {
                    x = compare(d, dict[j]);
                    if (x < 0) {
                        keys[j + 1] = keys[j];
                        dict[j + 1] = dict[j];
                    } else
                        break;
                } else
                    break;
            }
            keys[j + 1] = key;
            dict[j + 1] = d;
        }
    }

    int compare(String s1, String s2) {
        int i = 0;
        while (i < s1.length() && i < s2.length()) {
            if (s1.charAt(i) < s2.charAt(i))
                return -1;
            else if (s1.charAt(i) > s2.charAt(i))
                return 1;
            i++;
        }
        if (s1.length() == s2.length())
            return 0;

        return s1.length() < s2.length() ? -1 : 1;
    }

    public static void main(String[] args) {
        long time;
        String answer;
        boolean errors = false;
        String desiredAnswer;


        time = System.currentTimeMillis();
        answer = new T9().message(new String[]{"", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"}, new String[]{"bad"}, new String[]{"2230223"});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = "bad bad";
        System.out.println("Your answer:");
        System.out.println("\t\"" + answer + "\"");
        System.out.println("Desired answer:");
        System.out.println("\t\"" + desiredAnswer + "\"");
        if (!answer.equals(desiredAnswer)) {
            errors = true;
            System.out.println("DOESN'T MATCH!!!!");
        } else
            System.out.println("Match :-)");
        System.out.println();
        time = System.currentTimeMillis();
        answer = new T9().message(new String[]{"", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"}, new String[]{"the", "tie"}, new String[]{"0843#000843#000"});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = " tie   tie   ";
        System.out.println("Your answer:");
        System.out.println("\t\"" + answer + "\"");
        System.out.println("Desired answer:");
        System.out.println("\t\"" + desiredAnswer + "\"");
        if (!answer.equals(desiredAnswer)) {
            errors = true;
            System.out.println("DOESN'T MATCH!!!!");
        } else
            System.out.println("Match :-)");
        System.out.println();
        time = System.currentTimeMillis();
        answer = new T9().message(new String[]{"", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"}, new String[]{"bad", "ace", "aad", "aae", "aaf", "acf", "acd", "the", "tie"}, new String[]{"223#02", "23*#00843#0"});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = "aae bad  tie ";
        System.out.println("Your answer:");
        System.out.println("\t\"" + answer + "\"");
        System.out.println("Desired answer:");
        System.out.println("\t\"" + desiredAnswer + "\"");
        if (!answer.equals(desiredAnswer)) {
            errors = true;
            System.out.println("DOESN'T MATCH!!!!");
        } else
            System.out.println("Match :-)");
        System.out.println();
        time = System.currentTimeMillis();
        answer = new T9().message(new String[]{"", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"}, new String[]{"the", "tie", "bad", "ace", "aad", "aae", "aaf", "acf", "acd"}, new String[]{"84300223#02", "23#*"});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = "the  aae bad";
        System.out.println("Your answer:");
        System.out.println("\t\"" + answer + "\"");
        System.out.println("Desired answer:");
        System.out.println("\t\"" + desiredAnswer + "\"");
        if (!answer.equals(desiredAnswer)) {
            errors = true;
            System.out.println("DOESN'T MATCH!!!!");
        } else
            System.out.println("Match :-)");
        System.out.println();
        time = System.currentTimeMillis();
        answer = new T9().message(new String[]{"", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"}, new String[]{"bad", "ace", "aad", "aae", "tie", "aaf", "acf", "acd", "the"}, new String[]{"223#02", "23######"});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = "aae bad";
        System.out.println("Your answer:");
        System.out.println("\t\"" + answer + "\"");
        System.out.println("Desired answer:");
        System.out.println("\t\"" + desiredAnswer + "\"");
        if (!answer.equals(desiredAnswer)) {
            errors = true;
            System.out.println("DOESN'T MATCH!!!!");
        } else
            System.out.println("Match :-)");
        System.out.println();
        time = System.currentTimeMillis();
        answer = new T9().message(new String[]{"", "rq", "lde", "yoauz", "cbfgn", "tjkpx", "wvs", "ih", "m"}, new String[]{"xktgmfmoqlmivm", "hmthr", "tpjgmnmaremiwm", "tpjcmnmyrlmhvm", "xkpnmgmzqdmhsm", "wqopvvmiig", "melbcbqeeg", "jkxnmbmardmhwm", "kpxnmcmyqlmism", "wrztvsmhhf", "srztssmiic", "pxtgmfmyrdmhwm", "vqoxswmiin", "wryksvmihb", "ptjfmbmoremhvm"}, new String[]{"00", "7246779885##00000089682000007246779885##0000724677", "9885#000089682000093355523350066659594239879###000"});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = "  wqopvvmiig      hmthr     wqopvvmiig    vqoxswmiin    hmthr    melbcbqeeg  pxtgmfmyrdmhwm   ";
        System.out.println("Your answer:");
        System.out.println("\t\"" + answer + "\"");
        System.out.println("Desired answer:");
        System.out.println("\t\"" + desiredAnswer + "\"");
        if (!answer.equals(desiredAnswer)) {
            errors = true;
            System.out.println("DOESN'T MATCH!!!!");
        } else
            System.out.println("Match :-)");
        System.out.println();


        if (errors)
            System.out.println("Some of the test cases had errors :-(");
        else
            System.out.println("You're a stud (at least on the test data)! :-D ");
    }

}
//Powered by [KawigiEdit] 2.0!
