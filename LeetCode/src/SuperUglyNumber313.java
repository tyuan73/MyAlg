/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/9/17
 * Time: 10:31 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class SuperUglyNumber313 {
    /**
     * Solution 1: use dp to store ugly number and an array to store next index of ugly number
     * This is the most common solution
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];
        int[] idx = new int[primes.length];
        ugly[0] = 1;

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++)
                min = Math.min(min, ugly[idx[j]] * primes[j]);
            ugly[i] = min;
            for (int j = 0; j < primes.length; j++)
                if (min == ugly[idx[j]] * primes[j]) {
                    idx[j]++;
                }
        }
        return ugly[n - 1];
    }

    /**
     * Solution 2: same as above but use another array to store next prime number result, not just idx
     */
    public int nthSuperUglyNumber1(int n, int[] primes) {
        int[] ugly = new int[n];
        int[] idx = new int[primes.length];
        int[] next = new int[primes.length];
        ugly[0] = 1;
        for (int i = 0; i < primes.length; i++) {
            next[i] = primes[i];
        }

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++)
                min = Math.min(min, next[j]);
            ugly[i] = min;
            for (int j = 0; j < primes.length; j++)
                if (min == next[j]) {
                    idx[j]++;
                    next[j] = ugly[idx[j]] * primes[j];
                }
        }
        return ugly[n - 1];
    }

    /**
     * Solution 3: use PriorityQueue, supposed to be faster, but slower in reality.
     */
    class Ele implements Comparable<Ele> {
        int idx = 0, val = 0, prime;

        public Ele(int idx, int val, int prime) {
            this.idx = idx;
            this.val = val;
            this.prime = prime;
        }

        public int compareTo(Ele e) {
            return this.val - e.val;
        }
    }

    public int nthSuperUglyNumber3(int n, int[] primes) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        PriorityQueue<Ele> pq = new PriorityQueue<>();
        for (int x : primes)
            pq.add(new Ele(0, x, x));

        for (int i = 1; i < n; i++) {
            ugly[i] = pq.peek().val;
            while (ugly[i] == pq.peek().val) {
                Ele e = pq.poll();
                e.idx++;
                e.val = ugly[e.idx] * e.prime;
                pq.add(e);
            }
        }

        return ugly[n - 1];
    }
}

