/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 9/25/13
 * Time: 5:41 PM
 * To change this template use File | Settings | File Templates.
 * URL: http://codeforces.com/gym/100231/attachments/download/1868/20132014-ct-s01e03-codeforces-trainings-season-1-episode-3-en.pdf
 */

import java.util.*;

public class TrainingProblemJ {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        char[][] board = getArray(n+2);
        for(int i = 0; i < n; i++) {
            String line = in.next();
            for(int j = 0; j < n; j++) {
                board[i+1][j+1] = line.charAt(j);
            }
        }

        char[][] out = getArray(n);

        boolean touched = false;
        for(int i = 0; i < n; i++) {
            String move = in.next();
            for(int j = 0; j < n; j++) {
                if(move.charAt(j) == 'x') {
                    if(board[i+1][j+1] == '*')
                        touched = true;
                    else {
                        int x = 0;
                        for(int q = i; q <= i+2; q++)
                            for(int p = j; p <= j+2; p++)
                                //if(q != i+1 && p != j+1 && board[q][p] == '*')
                                if(board[q][p] == '*')
                                    x++;
                        out[i][j] = (char) ('0' + x);
                    }
                }
            }
        }

        if(touched) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(board[i][j] == '*')
                        out[i-1][j-1] = '*';
                }
            }
        }

        for(int i = 0; i < n; i++) {
            System.out.println(out[i]);
        }
    }

    static char[][] getArray(int n) {
        char[][] ret = new char[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(ret[i], '.');
        }
        return ret;
    }

}
