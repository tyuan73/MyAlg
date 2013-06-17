/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 2/28/13
 * Time: 10:48 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class LittleGirlMaxSum {
    static void shuffle(int[] a) {
        int n = a.length;
        Random rnd = new Random(System.nanoTime());
        for (int i = 0; i < n; ++i) {
            int v = rnd.nextInt(n - i);
            int tmp = a[i + v];
            a[i + v] = a[i];
            a[i] = tmp;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++)
            data[i] = in.nextInt();
        int[] multi = new int[n];
        for (int i = 0; i < q; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            multi[from]++;
            if (to < n - 1)
                multi[to + 1]--;
        }

        int v = 0;
        for (int i = 0; i < n; i++) {
            v += multi[i];
            multi[i] = v;
        }

        shuffle(data);
        Arrays.sort(data);
        shuffle(multi);
        Arrays.sort(multi);

        long ret = 0;
        for (int i = 0; i < n; i++) {
            ret += (long) data[i] * (long) multi[i];
        }
        System.out.println(ret);
    }
}
