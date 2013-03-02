import java.util.Arrays;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 2/2/13
 * Time: 11:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class CardGame {
    final static int MOD = 1000000007;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = Integer.parseInt(in.nextLine());
        for(int t1 = 1; t1 <= t; t1++) {
            String l1 = in.nextLine();
            String[] l11 = l1.split(" ");
            String l2 = in.nextLine();
            String[] l22 = l2.split(" ");
            int n = Integer.parseInt(l11[0]);
            int k =  Integer.parseInt(l11[1]);
            long[] a = new long[n];
            for(int i = 0; i < n; i++) {
                a[i] = Long.parseLong(l22[i]);
            }

            long res = process(n,k,a);
            System.out.printf("Case #%d: %d\n", t1, res);
        }
    }

    static long process(int n, int k, long[] a) {
        Arrays.sort(a);
        long ret = a[k-1];
        long f = 1;
        for(int i = 0, j = k; i < n-k; i++, j++) {
            f = (f*(k+i)/(i+1)) % MOD;
            ret += f*(a[j] % MOD) %MOD;
        }
        return (ret % MOD);
    }
}
