/**
 * Created by yuantian on 2/27/17.
 */

import java.util.*;

/**
 * Trie + DFS
 */
public class WordSearchII_212 {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
        boolean added = false;    // remove duplicates
    }

    private TrieNode root;
    private List<String> ans;
    final static private int[][] steps = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private void initTrie(String[] words) {
        root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                int ch = word.charAt(i) - 'a';
                if (node.children[ch] == null) {
                    node.children[ch] = new TrieNode();
                }
                node = node.children[ch];
            }
            node.word = word;
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        initTrie(words);
        ans = new ArrayList<>();
        int n = board.length, m = board[0].length;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(board, i, j, visited, root);
            }
        }
        return ans;
    }

    private void dfs(char[][] board, int x, int y, boolean[][] visited, TrieNode node) {
        if (node.word != null && !node.added) {
            ans.add(node.word);
            node.added = true;     // to avoid duplicates
        }
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y])
            return;

        int ch = board[x][y] - 'a';
        if (node.children[ch] != null) {
            visited[x][y] = true;
            for (int[] step : steps) {
                dfs(board, x + step[0], y + step[1], visited, node.children[ch]);
            }
            visited[x][y] = false;
        }

    }
}
