/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/17/13
 * Time: 11:23 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class BowlingScores {
    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        int t = S.nextInt();
        S.nextLine();
        while (t-- > 0) {
            String[] s = S.nextLine().split(" ");
            int[] a = new int[25];
            for (int i = 0; i < s.length; i++)
                a[i] = Integer.parseInt(s[i]);

            int ret = 0;
            // note: in the next line, the exit condition is "next < 20" not "i < n".
            for (int i = 0, next = 0; next < 20; i++, next++) {
                ret += a[i];
                if ((next % 2) == 0) {
                    if (a[i] == 10) {
                        ret += a[i + 1] + a[i + 2];
                        next++; // here, next could be increased.
                    }
                } else if (a[i] + a[i - 1] == 10)
                    ret += a[i + 1];
            }
            System.out.println(ret);
        }
    }
}
