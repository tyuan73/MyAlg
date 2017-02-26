/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 2/24/17
 * Time: 10:19 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class AddandSearchWord211 {
    class TrieNode {
        TrieNode[] children;
        boolean isWord;

        TrieNode() {
            this.children = new TrieNode[26];
            this.isWord = false;
        }
    }

    private TrieNode root = null;

    /**
     * Initialize your data structure here.
     */
    public AddandSearchWord211() {
        this.root = new TrieNode();
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        TrieNode node = this.root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (node.children[idx] == null)
                node.children[idx] = new TrieNode();
            node = node.children[idx];
        }
        node.isWord = true;
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return search(word, root);
    }

    private boolean search(String word, TrieNode node) {
        if (word.length() == 0)
            return node.isWord;

        char ch = word.charAt(0);
        if (ch != '.') {
            int idx = ch - 'a';
            return node.children[idx] != null && search(word.substring(1), node.children[idx]);
        }
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null && search(word.substring(1), node.children[i]))
                return true;
        }
        return false;
    }
}
