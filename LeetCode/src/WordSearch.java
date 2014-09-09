/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/26/13
 * Time: 12:42 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class WordSearch {
    boolean[][] visited = null;
    String w = null;
    char[][] b = null;
    int n = 0;
    int m = 0;
    int[][] next = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean exist(char[][] board, String word) {
        n = board.length;
        m = board[0].length;
        visited = new boolean[n][m];
        w = word;
        b = board;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == word.charAt(0) && dfs(i, j, 0))
                    return true;
            }
        }

        return false;
    }

    boolean dfs(int i, int j, int index) {
        if (index == w.length())
            return true;
        if (i < 0 || i >= n
                || j < 0 || j >= m
                || visited[i][j]
                || b[i][j] != w.charAt(index))
            return false;


        visited[i][j] = true;
        for (int[] step : next)
            if (dfs(i + step[0], j + step[1], index + 1))
                return true;
        visited[i][j] = false;

        return false;
    }
}
