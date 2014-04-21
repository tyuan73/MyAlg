/**
 * Created by yuantian on 4/21/14.
 */

import java.util.*;

public class IsFibo {
    public static void main(String[]  args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        long[] fib = new long[52];
        fib[1] = 1;

        for (int i = 2; i < fib.length; i++)
            fib[i] = fib[i-1] + fib[i-2];

        while( t-- > 0) {
            long n = in.nextLong();

            int l = 0, r = fib.length - 1;
            while(l < r) {
                int mid = (l + r) / 2;
                if (fib[mid] < n)
                    l = mid + 1;
                else
                    r = mid;
            }

            if (fib[r] == n)
                System.out.println("IsFibo");
            else
                System.out.println("IsNotFibo");
        }
    }
}
