/**
 * Created by yuantian on 7/25/17.
 */

import java.util.*;

public class ReplaceWords648 {
    class TriNode {
        TriNode[] list;
        boolean isWord;

        TriNode() {
            this.list = new TriNode[26];
            this.isWord = false;
        }
    }

    TriNode root = null;

    public String replaceWords(List<String> dict, String sentence) {
        root = new TriNode();

        for (String str : dict)
            insert(str);

        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            int idx = search(w);
            if (idx < 0)
                sb.append(" ").append(w);
            else
                sb.append(" ").append(w.substring(0, idx + 1));
        }
        return sb.substring(1).toString();
    }

    private int search(String w) {
        TriNode node = root;
        for (int i = 0; i < w.length(); i++) {
            int ch = w.charAt(i) - 'a';
            if (node.list[ch] == null) return -1;
            node = node.list[ch];
            if (node.isWord) return i;
        }
        return -1;
    }

    private void insert(String str) {
        TriNode node = root;
        for (int i = 0; i < str.length(); i++) {
            int ch = str.charAt(i) - 'a';
            if (node.list[ch] == null)
                node.list[ch] = new TriNode();
            node = node.list[ch];
        }
        node.isWord = true;
    }
}
