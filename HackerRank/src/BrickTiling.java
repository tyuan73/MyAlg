/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/11/13
 * Time: 11:48 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class BrickTiling {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[][] b = new int[n+4][m+4];
            for(int[] row : b)
                Arrays.fill(row, 1);
            int empty = 0;
            for(int i = 0; i < n; i++) {
                String line = in.next();
                for(int j = 0; j < m; j++) {
                    if(line.charAt(j) == '.') {
                        b[i+2][j+2] = 0;
                        empty++;
                    }
                }
            }

            int[] ret = {0};
            ex(b, 2, 2, empty, ret);
            System.out.println(ret[0]);
        }
    }

    static void ex(int[][] b, int i, int j, int empty, int[] total) {
        //System.out.println("empty = " + empty);
        int n = b.length-4;
        int m = b[0].length-4;
        if(empty == 0) {
            total[0]++;
            return;
        }
        if((empty%4) != 0)
            return;

        //System.out.println("i= " + i + "j = " + j);
        for(; i <= n+1; i++) {
            boolean ok = false;
            for(; j <= m+1; j++) {
                if(b[i][j] == 0) {
                    ok = true;
                    break;
                }
            }
            if(ok)
                break;
            j = 2;
        }
        /*
        System.out.println("i= " + i + "j = " + j);
        for(int[] x : b) {
            for(int y : x)
                System.out.printf("%5d", y);
            System.out.println();
        }
        */

        if(b[i+1][j] == 0 && b[i+2][j] == 0 && b[i+2][j+1] == 0) {
            b[i][j] = 1;
            b[i+1][j] = 1;
            b[i+2][j] = 1;
            b[i+2][j+1] = 1;
            ex(b, i, j+1, empty-4, total);
            b[i][j] = 0;
            b[i+1][j] = 0;
            b[i+2][j] = 0;
            b[i+2][j+1] = 0;
        }
        if(b[i+1][j] == 0 && b[i+2][j] == 0 && b[i+2][j-1] == 0) {
            b[i][j] = 1;
            b[i+1][j] = 1;
            b[i+2][j] = 1;
            b[i+2][j-1] = 1;
            ex(b, i, j+1, empty-4, total);
            b[i][j] = 0;
            b[i+1][j] = 0;
            b[i+2][j] = 0;
            b[i+2][j-1] = 0;
        }
        if(b[i+1][j] == 0 && b[i+2][j] == 0 && b[i][j+1] == 0) {
            b[i][j] = 1;
            b[i+1][j] = 1;
            b[i+2][j] = 1;
            b[i][j+1] = 1;
            ex(b, i, j+2, empty-4, total);
            b[i][j] = 0;
            b[i+1][j] = 0;
            b[i+2][j] = 0;
            b[i][j+1] = 0;
        }
        if(b[i+1][j+1] == 0 && b[i+2][j+1] == 0 && b[i][j+1] == 0) {
            b[i][j] = 1;
            b[i+1][j+1] = 1;
            b[i+2][j+1] = 1;
            b[i][j+1] = 1;
            ex(b, i, j+2, empty-4, total);
            b[i][j] = 0;
            b[i+1][j+1] = 0;
            b[i+2][j+1] = 0;
            b[i][j+1] = 0;
        }

        if(b[i+1][j] == 0 && b[i+1][j+1] == 0 && b[i+1][j+2] == 0) {
            b[i][j] = 1;
            b[i+1][j] = 1;
            b[i+1][j+1] = 1;
            b[i+1][j+2] = 1;
            ex(b, i, j+1, empty-4, total);
            b[i][j] = 0;
            b[i+1][j] = 0;
            b[i+1][j+1] = 0;
            b[i+1][j+2] = 0;
        }


        if(b[i+1][j-2] == 0 && b[i+1][j-1] == 0 && b[i+1][j] == 0) {
            b[i][j] = 1;
            b[i+1][j-2] = 1;
            b[i+1][j-1] = 1;
            b[i+1][j] = 1;
            ex(b, i, j+1, empty-4, total);
            b[i][j] = 0;
            b[i+1][j-2] = 0;
            b[i+1][j-1] = 0;
            b[i+1][j] = 0;
        }

        if(b[i][j+1] == 0 && b[i][j+2] == 0 && b[i+1][j] == 0) {
            b[i][j] = 1;
            b[i][j+1] = 1;
            b[i][j+2] = 1;
            b[i+1][j] = 1;
            ex(b, i, j+1, empty-4, total);
            b[i][j] = 0;
            b[i][j+1] = 0;
            b[i][j+2] = 0;
            b[i+1][j] = 0;
        }

        if(b[i][j+1] == 0 && b[i][j+2] == 0 && b[i+1][j+2] == 0) {
            b[i][j] = 1;
            b[i][j+1] = 1;
            b[i][j+2] = 1;
            b[i+1][j+2] = 1;
            ex(b, i, j+1, empty-4, total);
            b[i][j] = 0;
            b[i][j+1] = 0;
            b[i][j+2] = 0;
            b[i+1][j+2] = 0;
        }
    }
}
