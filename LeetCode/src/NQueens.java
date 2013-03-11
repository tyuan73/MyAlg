/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/11/13
 * Time: 4:21 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.*;

public class NQueens {
    boolean[] b1;
    boolean[] b2;
    int n;
    int total;

    public ArrayList<String[]> solveNQueens(int n) {

        int[] rows = new int[n];
        boolean[] used = new boolean[n];
        ArrayList<String[]> ret = new ArrayList<String[]>();
        b1 = new boolean[2*n];
        b2 = new boolean[2*n];
        this.n = n;
        this.total = 0;
        queens(rows, used, 0, ret);

        return ret;
    }

    void queens(int[] rows, boolean[] used, int index, ArrayList<String[]> ret) {
        //int n = rows.length;
        if(index == n) {
            String[] board = new String[n];
            for(int i = 0; i < n; i++) {
                char[] line = new char[n];
                Arrays.fill(line, '.');
                line[rows[i]] = 'Q';
                board[i] = new String(line);
            }
            ret.add(board);
            this.total++;
            return;
        }

        for(int i = 0; i < n; i++) {
            if(used[i]) continue;
            if(valid(index, i)) {
                rows[index] = i;
                used[i] = true;
                queens(rows, used, index+1, ret);
                used[i] = false;
                b1[index+i] = false;
                b2[i-index+n] = false;
            }
        }
    }

    boolean valid(int x, int y) {
        if(b1[x+y] || b2[y-x+n])
            return false;
        b1[x+y] = true;
        b2[y-x+n] = true;
        return true;
    }

    public static void main(String[] args) {
        NQueens nq = new NQueens();
        ArrayList<String[]> ret = nq.solveNQueens(12);
        /*
        for(String[] strs : ret) {
            for(String s : strs)
                System.out.println(s);
            System.out.println();
        }
        */
        System.out.println(nq.total);
    }
}
