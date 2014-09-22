package projecteuler1;

/**
 * Created by yuantian on 9/18/14.
 */

import java.util.*;

public class ConsecutivePrimeSum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int[] prime = new int[1000001];
        prime[0] = 1; prime[1] = 1;
        for(int i = 2; i <= 1000000; i++ ) {
            if (prime[i] == 0) {
                int p = 2*prime[i];
                while(p <= 1000000) {
                    prime[p] = 1;
                    p += prime[i];
                }
            }
        }


        int t = in.nextInt();

        while(t-- > 0) {
            int n = in.nextInt();
        }
    }
}
