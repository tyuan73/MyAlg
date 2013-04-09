package FourPicks;/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/20/13
 * Time: 11:56 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

public class TrieNode {
    private int R = 26;
    private Object val;
    private TrieNode[] next = new TrieNode[R];

    public Object getVal() {
        return val;
    }

    public void setVal(Object o) {
        val = o;
    }

    public TrieNode getNext(char ch) {
        return next[ch - 'A'];
    }

    public void setNext(char ch, TrieNode tn) {
        next[ch - 'A'] = tn;
    }
}
