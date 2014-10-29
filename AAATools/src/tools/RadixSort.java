package tools;/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 10/28/14
 * Time: 11:34 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class RadixSort {


    public static long[] radixSort(long[] f) {
        long[] to = new long[f.length];
        {
            int[] b = new int[65537];
            for (int i = 0; i < f.length; i++) b[1 + (int) (f[i] & 0xffff)]++;
            for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
            for (int i = 0; i < f.length; i++) to[b[(int) (f[i] & 0xffff)]++] = f[i];
            long[] d = f;
            f = to;
            to = d;
        }
        {
            int[] b = new int[65537];
            for (int i = 0; i < f.length; i++) b[1 + (int) (f[i] >>> 16 & 0xffff)]++;
            for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
            for (int i = 0; i < f.length; i++) to[b[(int) (f[i] >>> 16 & 0xffff)]++] = f[i];
            long[] d = f;
            f = to;
            to = d;
        }
        {
            int[] b = new int[65537];
            for (int i = 0; i < f.length; i++) b[1 + (int) (f[i] >>> 32 & 0xffff)]++;
            for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
            for (int i = 0; i < f.length; i++) to[b[(int) (f[i] >>> 32 & 0xffff)]++] = f[i];
            long[] d = f;
            f = to;
            to = d;
        }
        {
            int[] b = new int[65537];
            for (int i = 0; i < f.length; i++) b[1 + (int) (f[i] >>> 48 & 0xffff)]++;
            for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
            for (int i = 0; i < f.length; i++) to[b[(int) (f[i] >>> 48 & 0xffff)]++] = f[i];
            long[] d = f;
            f = to;
            to = d;
        }
        return f;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }
}
