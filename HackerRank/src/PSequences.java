/**
 * Created by yuantian on 4/24/14.
 */

/**
 *
 *  We call a sequence of N natural numbers (a1, a2, …, aN) a P-sequence, if the product of any two adjacent numbers in it is not greater than P. In other words, if a sequence (a1, a2, …, aN) is a P-sequence, then ai * ai+1 ≤ P ∀ 1 ≤ i < N

 You are given N and P. Your task is to find the number of such P-sequences of N integers modulo 109+7.

 Input Format
 The first line of input consists of N
 The second line of the input consists of P.

 Constraints
 2 ≤ N ≤ 103
 1 ≤ P ≤ 109
 1 ≤ ai

 Output Format
 Output the number of P-sequences of N integers modulo 109+7.

 Sample Input #00

 2
 2
 Sample Output #00

 3
 Explanation #00

 3 such sequences are {1,1},{1,2} and {2,1}
 */

import java.util.*;

public class PSequences {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        long P = 1000000007;

        int n = in.nextInt();
        long p = in.nextLong();
        int r = (int) Math.sqrt((double)p);

        long[] f = new long[2 * r + 3];
        long[] x = new long[2 * r + 3];
        long[] y = new long[2 * r + 3];

        Arrays.fill(f, 1);
        Arrays.fill(x, 1);
        x[0] = 0;

        int max = r, sum = r;
        int next = r;
        while (sum < p) {
            f[++max] = p/(next) - p/(next+1);
            sum += f[max];
            next--;
        }

        int diff = 0;
        if (sum > p) {
            max--;
            diff = 1;
        }

        while(n-- > 0) {
            for (int i = max, j = 1; i > 0; i--, j++) {
                y[i] = (x[j] * f[j+diff] + y[i+1]) % P;
            }

            long[] z = x;
            x = y;
            y = z;
            y[max+1] = 0;
        }

        System.out.println(x[1]);
    }
}

/**
 * Test case:
 * input:
 *  3
 *  10
 * output:
 *  147
 *
 *
 * input:
 *  2
 *  6
 * output:
 *  14
 *
 * input:
 *  2
 *  13
 * output:
 *  37
 *
 * input:
 *  100
 *  48
 * output:
 *  920856782
 **/