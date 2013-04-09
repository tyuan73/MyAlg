/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/18/13
 * Time: 1:39 PM
 * To change this template use File | Settings | File Templates.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class BinomialCoefficient {
    static long[] preprocess = new long[1010];
    static long[] rpreprocess = new long[1010];
    static long MOD = 1009;

    static long mult(long a, long b) {
        long result = 1;
        while (b > 0) {
            if (b % 2 == 1)
                result = result * a % MOD;
            b >>= 1;
            a = a * a % MOD;
        }
        return result;
    }

    static long multinv(long a) {
        return mult(a, MOD - 2);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int testcases = Integer.parseInt(br.readLine());
        long num = 1, den = 1;
        int i, j;
        preprocess[0] = rpreprocess[0] = 1;
        for (i = 1; i < 1010; i++) {
            num *= i;
            num %= MOD;
            preprocess[i] = num;
            den *= multinv(i);
            den %= MOD;
            rpreprocess[i] = den;
        }
        for (i = 0; i < testcases; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long result = 1;
            long n = Long.parseLong(st.nextToken());
            long r = Long.parseLong(st.nextToken());
            while (n > 0) {
                long N = n % MOD;
                long K = r % MOD;
                if (N < K) {
                    result = 0;
                    break;
                }
                result *= preprocess[(int) N];
                result = result * rpreprocess[(int) K];
                result = result * rpreprocess[(int) (N - K)];
                result %= MOD;
                n /= MOD;
                r /= MOD;
            }
            pw.println(result);
        }
        pw.flush();
        pw.close();
    }
}