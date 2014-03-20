/**
 * Created by yuantian on 3/12/14.
 */

import java.util.*;

public class InnaDimaAndSong390B {
    public static void main(String[]  args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];

        for(int i = 0; i < n; i++)
            a[i] = in.nextInt();

        for(int i = 0; i < n; i++)
            b[i] = in.nextInt();

        long joy = 0;
        for (int i = 0; i < n; i++) {
            if (b[i] > a[i] * 2)
                joy--;
            else {
                long x = b[i] / 2;
                if ( x == 0)
                    joy--;
                else
                    joy += x * (b[i] - x);
            }
        }

        System.out.println(joy);
    }
}
