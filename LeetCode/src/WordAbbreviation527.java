/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 5/9/17
 * Time: 9:26 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class WordAbbreviation527 {

    class Word implements Comparable<Word> {
        String wd;
        int idx;

        Word(String word, int idx) {
            this.wd = word;
            this.idx = idx;
        }

        public int compareTo(Word w) {
            if (this.wd.length() == w.wd.length())
                return this.wd.compareTo(w.wd);
            return w.wd.length() - this.wd.length();
        }
    }

    public List<String> wordsAbbreviation(List<String> dict) {
        int n = dict.size();
        Word[] words = new Word[n + 1];
        for (int i = 0; i < n; i++) {
            String word = dict.get(i);
            words[i] = new Word(word.charAt(word.length() - 1) + word, i);
        }
        words[n] = new Word("", n);

        Arrays.sort(words);

        int pre = 0, cur = 0;
        for (int i = 0; i < n; i++) {
            String w1 = words[i].wd, w2 = words[i + 1].wd;
            cur = 0;
            if (w1.length() == w2.length()) {
                cur = commonPrefix(w1, w2);
            }
            String abbr = getAbbr(w1, Math.max(cur, pre));
            dict.set(words[i].idx, abbr.length() < w1.length() - 1 ? abbr : w1.substring(1));
            pre = cur;
        }

        return dict;
    }

    private String getAbbr(String str, int prefLen) {
        if (prefLen == 0) prefLen = 1;

        int shortcut = str.length() - prefLen - 2;
        return str.substring(1, prefLen + 1) + shortcut + str.charAt(0);
    }

    private int commonPrefix(String s1, String s2) {
        int pref = 0;
        while (pref < s1.length()) {
            if (s1.charAt(pref) != s2.charAt(pref))
                return pref;
            pref++;
        }
        return pref;
    }
}