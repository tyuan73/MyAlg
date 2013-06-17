/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/4/13
 * Time: 1:48 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class K_MultipleFreeSet {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();

        if (k == 1) {
            System.out.println(n);
            return;
        }

        long[] data = new long[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextInt();
        }
        shuffle(data);
        Arrays.sort(data);
        boolean[] removed = new boolean[n];
        int j = 0, res = n;
        for (int i = 0; i < n; i++) {
            while (j < i && data[j] * k < data[i])
                j++;
            if (j < i && !removed[j] && data[j] * k == data[i]) {
                res--;
                removed[i] = true;
            }
        }

        System.out.println(res);
    }

    static void shuffle(long[] a) {
        int n = a.length;
        Random rnd = new Random(System.nanoTime());
        for (int i = 0; i < n; ++i) {
            int v = rnd.nextInt(n - i);
            long tmp = a[i + v];
            a[i + v] = a[i];
            a[i] = tmp;
        }
    }
}
