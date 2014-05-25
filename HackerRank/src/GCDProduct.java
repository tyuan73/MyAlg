/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 5/23/14
 * Time: 11:37 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class GCDProduct {
    static long P = 1000000007;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        long n = in.nextLong();
        long m = in.nextLong();
        if (n > m) {
            long temp = n; n = m; m = temp;
        }

        int[] primes = new int[(int)n+1];
        int total = 0;

        //Arrays.fill(primes, true);
        primes[0] = primes[1] = 1;
        for(int i = 2; i <= n; i++) {
            if (primes[i] == 0) {
                primes[total++] = i;
                for(int j = 2*i; j <= n; j += i)
                    primes[j] = 1;
            }
        }

        long out = 1;
        long p = 0, power = 0;
        for(int i = 0; i < total; i++) {
            long count = 0;
            p = primes[i]; power = p;
            while(power <= n) {
                count += (n/power) * (m/power);
                power *= power;
            }

            power = p;
            while (count > 0) {
                if ((count & 1) == 1) {
                    out = (out * power) % P;
                }
                count >>= 1;
                power = (power*power)% P;
            }
        }

        System.out.println(out);
    }
}
