package problem440.b;

/**
 * Created by yuantian on 6/12/14.
 */

import java.util.*;

public class Balancer {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        long[] m = new long[n];
        long total = 0;
        for(int i = 0; i < n; i++) {
            m[i] = in.nextLong();
            total += m[i];
        }

        long avg = total / n;
        long steps = 0;
        for(int i = 0; i < n-1; i++) {
            long diff = m[i] - avg;
            steps += Math.abs(diff);
            m[i+1] += diff;
        }
        System.out.println(steps);
    }
}
