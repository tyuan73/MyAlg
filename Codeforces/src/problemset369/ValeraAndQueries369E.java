package problemset369; /**
 * Created by yuantian on 12/19/13.
 */

import java.util.*;

public class ValeraAndQueries369E {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        int[] a = new int[1000003];
        while(n-- > 0) {
            int l = in.nextInt();
            int r = in.nextInt();
            a[l]++;
            a[r+1]--;
        }

        for (int i = 1; i < 1000003; i++) {
            a[i] += a[i-1];
        }

        while(m-- > 0) {

        }
    }
}
