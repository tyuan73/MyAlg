/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/18/13
 * Time: 11:19 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Scanner;

public class CowsAndSequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        long total = 0;
        int len = 1;
        int[] s = new int[200005];
        int[] d = new int[200005];
        while (n-- > 0) {
            int op = in.nextInt();
            if (op == 1) {
                int a = in.nextInt();
                int x = in.nextInt();
                d[a - 1] += x;
                total += a * x;
            } else if (op == 2) {
                int k = in.nextInt();
                s[len++] = k;
                total += k;
            } else if (len > 1) {
                len--;
                total -= s[len] + d[len];
                d[len - 1] += d[len];
                d[len] = 0;
            }
            System.out.println((double) total / (double) len);
        }
    }
}
