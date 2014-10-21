package problemset369; /**
 * Created by yuantian on 12/5/13.
 */

import java.util.*;

public class ValeraAndPlates369A {
    static public void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n, m, k, a, out = 0;
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();

        for (int i = 0; i < n; i++) {
            a = in.nextInt();
            if (a == 2) {
                if (k > 0)
                    k--;
                else if (m > 0)
                    m--;
                else
                    out++;
            } else {
                if (m > 0)
                    m--;
                else
                    out++;
            }
        }

        in.close();
        System.out.println(out);
    }
}
