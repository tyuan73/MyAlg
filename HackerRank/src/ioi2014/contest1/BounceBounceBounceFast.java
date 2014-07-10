package ioi2014.contest1;

/**
 * Created by yuantian on 7/10/14.
 */


/*
use : Euler's totient function

https://www.hackerrank.com/contests/ioi-2014-practice-contest-1/challenges/bounce-bounce-bounce-ioi14/editorial
 */

import java.util.*;

public class BounceBounceBounceFast {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        long n = in.nextLong()+1;
        long start = System.currentTimeMillis();
        long x = n;
        for(long p = 2; p <= n; p++) {
            if (p > n/p)
                p = n;
            if (n % p == 0) {
                x -= x/p;
                while(n % p == 0)
                    n /= p;
            }
        }
        System.out.println(x);
        System.out.println(System.currentTimeMillis() - start);
    }
}
