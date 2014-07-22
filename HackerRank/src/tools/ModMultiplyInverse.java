package tools;

/**
 * Created by yuantian on 7/22/14.
 */
public class ModMultiplyInverse {
    public static void main(String[] args) {
        final long P = 1000000007;
        // calculate modular multiplicative inverse of
        // a single number
        for(int i = 1; i <= 9; i++)
            System.out.print(modMultInverse(i, P) + " ");
        System.out.println();

        // calculate mmi for number from 1 to n;
        int n = 9;
        long[] b = new long[n+1];
        invserseArray(n, b, P);
        for(int i = 1; i <= n; i++) {
            System.out.print(b[i] + " ");
        }
        System.out.println();
    }

    /**
     * inverse array - O(n)
     *
     * @param n -> the length of the array [0..n+1]
     * @param a -> the array which hold the result
     * @param p -> the prime
     */
    static void invserseArray(int n, long[] a, long p) {
        a[1] = 1;
        for(int i = 2; i <= n; i++) {
            a[i] = (-(p/i) * a[(int)p%i]) % p + p;
        }
    }

    /**
     * Fermat’s little theorem.
     * a ^ (-1) mod p  =  a ^ (p-2) mod p
     * Assume a and p are prime to each other (or co-prime)
     * @param x
     * @param p
     * @return
     */
    static long modMultInverse(long x, long p) {
        long base = x;
        long a = 1;
        long pow = p-2;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                a = (a * base) % p;
            }
            pow >>= 1;
            base = (base * base) % p;
        }
        return a;
    }

}
