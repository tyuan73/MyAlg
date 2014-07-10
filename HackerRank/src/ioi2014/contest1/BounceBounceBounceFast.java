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
            // p is now greater than sqrt(n). the next prime candidate can only be n.
            if (p > n/p)
                p = n;

            if (n % p == 0) {
                // p is a prime factor of n so, multiply x by (1 - 1/p) = x-x/p
                x -= x/p;
                // remove all prime factors p in n
                while((n /= p) % p == 0)
                    ;
            }
        }
        System.out.println(x);
        System.out.println(System.currentTimeMillis() - start);
    }
}
