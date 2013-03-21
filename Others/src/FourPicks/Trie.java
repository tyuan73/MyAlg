package FourPicks;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/20/13
 * Time: 11:33 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

public class Trie<Value> {
    private TrieNode root;

    public TrieNode getRoot() {
        return root;
    }

    public Value get(String key) {
        TrieNode x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.getVal();
    }

    private TrieNode get(TrieNode x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.getNext(c), key, d + 1);
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private TrieNode put(TrieNode x, String key, Value val, int d) {
        if (x == null) x = new TrieNode();
        if (d == key.length()) {
            x.setVal(val);
            return x;
        }
        char c = key.charAt(d);
        x.setNext(c, put(x.getNext(c), key, val, d + 1));
        return x;
    }
}
