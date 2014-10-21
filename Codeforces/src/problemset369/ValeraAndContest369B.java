package problemset369; /**
 * Created by yuantian on 12/5/13.
 */

import java.util.*;

public class ValeraAndContest369B {
    static public void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n, k, l, r, sa, sk;

        n = in.nextInt();
        k = in.nextInt();
        l = in.nextInt();
        r = in.nextInt();
        sa = in.nextInt();
        sk = in.nextInt();

        int avg = sk / k;
        int rem = sk % k;
        for (int i = 0; i < k; i++) {
            if (i < rem)
                System.out.println((avg + 1));
            else
                System.out.println(avg);
        }

        if (k < n) {
            avg = (sa - sk) / (n - k);
            rem = (sa - sk) % (n - k);
            for (int i = k; i < n; i++) {
                if (i < k+rem)
                    System.out.println((avg + 1));
                else
                    System.out.println(avg);
            }
        }
    }
}
