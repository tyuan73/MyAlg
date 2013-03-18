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
            int r = 0;
            for (int i = 0, n = 0; n < 20; r += a[i++], n++)
                if ((n % 2) == 0) {
                    if (a[i] == 10) {
                        r += a[i + 1] + a[i + 2];
                        n++;
                    }
                } else if (a[i] + a[i - 1] == 10)
                    r += a[i + 1];
            System.out.println(r);
        }
    }
}
