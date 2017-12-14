/*

*/

import java.util.*;
import java.io.*;

public class PrefixAndSuffixSearch745 {

    /**
     * The first solution using HashMaps.
     */
    class Solution1 {
        Map<String, List<Integer>> map1 = null;
        Map<String, List<Integer>> map2 = null;

        public Solution1(String[] words) {
            map1 = new HashMap<>();
            map2 = new HashMap<>();
            for (int i = words.length - 1; i >= 0; i--) {
                for (int j = 0; j <= words[i].length(); j++) {
                    String key = words[i].substring(0, j);
                    if (!map1.containsKey(key))
                        map1.put(key, new ArrayList<>());
                    map1.get(key).add(i);
                    key = words[i].substring(j);
                    if (!map2.containsKey(key))
                        map2.put(key, new ArrayList<>());
                    map2.get(key).add(i);
                }
            }
        }

        public int f(String prefix, String suffix) {
            List<Integer> pre = map1.get(prefix);
            if (pre == null) return -1;
            List<Integer> suf = map2.get(suffix);
            if (suf == null) return -1;
            return max(pre, suf);
        }

        private int max(List<Integer> pre, List<Integer> suf) {
            int i = 0, j = 0;
            while (i < pre.size() && j < suf.size()) {
                int a = pre.get(i), b = suf.get(j);
                if (a == b) return a;
                if (a > b) i++;
                else j++;
            }
            return -1;
        }
    }

    /**
     * A solution based on Trie
     */
    class Solution2 {
        class TrieNode {
            List<Integer> w = new ArrayList<>();
            TrieNode[] list = new TrieNode[26];
        }

        TrieNode prefixRoot = null;
        TrieNode suffixRoot = null;

        public Solution2(String[] words) {
            prefixRoot = new TrieNode();
            suffixRoot = new TrieNode();
            for (int i = words.length - 1; i >= 0; i--) {
                insert(prefixRoot, words[i], i);
                insertReverse(suffixRoot, words[i], i);
            }
        }

        private void insert(TrieNode root, String word, int val) {
            TrieNode node = root;
            node.w.add(val);
            for (int i = 0; i < word.length(); i++) {
                int ch = word.charAt(i) - 'a';
                if (node.list[ch] == null)
                    node.list[ch] = new TrieNode();
                node = node.list[ch];
                node.w.add(val);
            }
        }

        private void insertReverse(TrieNode root, String word, int val) {
            TrieNode node = root;
            node.w.add(val);
            for (int i = word.length() - 1; i >= 0; i--) {
                int ch = word.charAt(i) - 'a';
                if (node.list[ch] == null)
                    node.list[ch] = new TrieNode();
                node = node.list[ch];
                node.w.add(val);
            }
        }

        private List<Integer> search(TrieNode root, String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                int ch = word.charAt(i) - 'a';
                if (node.list[ch] == null)
                    return null;
                node = node.list[ch];
            }
            return node.w;
        }

        private List<Integer> searchReverse(TrieNode root, String word) {
            TrieNode node = root;
            for (int i = word.length() - 1; i >= 0; i--) {
                int ch = word.charAt(i) - 'a';
                if (node.list[ch] == null)
                    return null;
                node = node.list[ch];
            }
            return node.w;
        }

        public int f(String prefix, String suffix) {
            List<Integer> pre = search(prefixRoot, prefix);
            if (pre == null) return -1;
            List<Integer> suf = searchReverse(suffixRoot, suffix);
            if (suf == null) return -1;
            return max(pre, suf);
        }

        private int max(List<Integer> pre, List<Integer> suf) {
            int i = 0, j = 0;
            while (i < pre.size() && j < suf.size()) {
                int a = pre.get(i), b = suf.get(j);
                if (a == b) return a;
                if (a > b) i++;
                else j++;
            }
            return -1;
        }
    }

    /**
     * A smart solution to use prefix + ". + suffix as key.
     */
    class Solution3 {
        Map<String, Integer> map = null;

        public Solution3(String[] words) {
            map = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                String w = words[i];
                for (int j = 0; j <= Math.min(10, w.length()); j++) {
                    String pre = w.substring(0, j);
                    for (int k = 0; k <= Math.min(10, w.length()); k++) {
                        String suf = w.substring(w.length() - k);
                        map.put(pre + "." + suf, i);
                    }
                }
            }
        }

        public int f(String prefix, String suffix) {
            Integer x = map.get(prefix + "." + suffix);
            return x == null ? -1 : x;
        }
    }
}