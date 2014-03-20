/**
 * Created by yuantian on 3/12/14.
 */

import java.util.*;

public class InnaandAlarmClock390A {
    static public void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int[] row = new int[103];
        int[] col = new int[103];
        int totalR = 0, totalC = 0;
        int n = in.nextInt();
        while(n-- > 0) {
            int x = in.nextInt();
            int y = in.nextInt();

            if (row[x] != 1) {
                row[x] = 1;
                totalR++;
            }
            if (col[y] != 1) {
                col[y] = 1;
                totalC++;
            }
        }

        System.out.println(Math.min(totalR, totalC));
    }
}
