/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 2/27/13
 * Time: 1:10 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class Squares {
    static int[] data;

    static public void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; i++)
            data[i] = in.nextInt();
        if (k > n) {
            System.out.println(-1);
            return;
        }

        if (k == n) {
            System.out.println("0 0");
            return;
        }

        k = n - k;
        int l = 0, r = n - 1;
        int i = partition(l, r);
        while (i != k) {
            if (i > k) {
                r = i - 1;
            } else {
                l = i + 1;
            }
            i = partition(l, r);
        }
        System.out.println(data[k] + " 0");
    }

    static int partition(int l, int r) {
        int pivot = data[l];
        int i = l, j = l + 1;
        while (j <= r) {
            if (data[j] < pivot) {
                i++;
                int x = data[j];
                data[j] = data[i];
                data[i] = x;
            }
            j++;
        }
        data[l] = data[i];
        data[i] = pivot;
        return i;
    }
}
