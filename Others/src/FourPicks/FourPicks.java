package FourPicks;
/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/20/13
 * Time: 11:33 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FourPicks {
    static Trie<String> trie = new Trie<String>();
    static int count = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            buildTrie();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FourPicks fp = new FourPicks();
        while (true) {
            int n = in.nextInt();
            if (n == 0)
                break;
            String s = in.next();
            s = s.trim().toUpperCase();
            char[] chs = s.toCharArray();
            Arrays.sort(chs);

            count = 0;
            fp.search(n, chs, new boolean[chs.length], new StringBuilder(), trie.getRoot());
            if (count == 0)
                System.out.println("Unable to find any words");
        }
    }

    void search(int len, char[] charSet, boolean[] used, StringBuilder sb, TrieNode n) {
        if (sb.length() == len) {
            if (n.getVal() != null) {
                System.out.println(sb.toString() + " : " + n.getVal());
                count++;
            }
            return;
        }

        char pre = 'a';
        for (int i = 0; i < charSet.length; i++) {
            char ch = charSet[i];
            if (used[i] || ch == pre || n.getNext(ch) == null)
                continue;

            pre = ch;
            used[i] = true;
            sb.append(ch);
            search(len, charSet, used, sb, n.getNext(ch));
            sb.setLength(sb.length() - 1);
            used[i] = false;
        }
    }

    static void buildTrie() throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("/Users/yuantian/OWL.txt"));
        String s = null;
        while ((s = f.readLine()) != null) {
            int sep = s.indexOf(" ");
            if (sep > 0) {
                trie.put(s.substring(0, sep), s.substring(sep + 1));
            } else {
                trie.put(s, "[undefined]");
            }
        }
        f.close();
    }
}
