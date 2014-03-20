/**
 * Created by yuantian on 3/12/14.
 */

import java.util.*;

public class InnaAndCandyBoxes390C {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int k = in.nextInt();
        int w = in.nextInt();

        int[][] a = new int[k][n/k + 3];
        String s = in.next();
        for (int i = 0; i < n; i++) {
            int row = i % k;
            int col = i / k + 1;

            if (s.charAt(i) == '1')
                a[row][col] = a[row][col-1] + 1;
            else
                a[row][col] = a[row][col-1];
        }

        while (w-- > 0) {
            int left = in.nextInt()-1;
            int right = in.nextInt()-1;
            int move = 0;

            int startRow = left % k;
            int startCol = left / k + 1;
            int endRow = right % k;
            int endCol = right / k + 1;
            for (int i = 0; i < k; i++) {
                int l = startCol, r = endCol;
                if (i >= startRow)
                    l--;
                if (i >= endRow)
                    r--;
                int ones = a[i][r] - a[i][l];
                if (i == (startRow + k - 1) % k)
                    move += r - l - ones;
                else
                    move += ones;
            }

            move += 1 - (a[endRow][endCol] - a[endRow][endCol-1]);

            System.out.println(move);
        }
    }
}
