/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/19/13
 * Time: 11:29 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class FishWeight {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[m];
        for(int i = 0; i < n; i++)
            a[i] = in.nextInt();
        for(int i = 0; i < m; i++)
            b[i] = in.nextInt();
        shuffle(a);
        shuffle(b);
        Arrays.sort(a);
        Arrays.sort(b);
        int[] a1 = new int[n];
        int[] b1 = new int[m];
        int i1 = 0, i2 = 0, c1 = 0, c2 = 0;
        while(i1 < n || i2 < m) {
            if(i1 == n) {
                b1[c2++] = b[i2++];
                continue;
            }
            if(i2 == m) {
                a1[c1++] = a[i1++];
                continue;
            }
            if(a[i1] == b[i2]) {
                i1++; i2++;
            } else if(a[i1] < b[i2]) {
                a1[c1++] = a[i1++];
            } else if(a[i1] > b[i2])
                b1[c2++] = b[i2++];
        }

        i1 = i2 = 0;
        //int d1 = 0, d2 = 0;
        while(i1 < c1 && i2 < c2) {
            if(a1[i1] < b1[i2]) {
                i1++; i2++;
            } else {
                i2++;
            }
        }

        if(i1 < c1)
            System.out.println("YES");
        else
            System.out.println("NO");
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
