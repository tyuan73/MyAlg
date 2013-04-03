/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/6/13
 * Time: 2:21 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class MaximandDiscounts {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int m = in.nextInt();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            int next = in.nextInt();
            min = Math.min(min, next);
        }

        int n = in.nextInt();
        int[] prices = new int[n];
        for (int i = 0; i < n; i++)
            prices[i] = in.nextInt();

        shuffle(prices);
        Arrays.sort(prices);

        int total = 0;
        for (int i = n - 1; i >= 0; i -= min + 2) {
            for (int j = i; j >= 0 && j > i - min; j--)
                total += prices[j];
        }
        System.out.println(total);
    }

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
}
