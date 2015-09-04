package TenDigits5Grp; /**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 9/3/15
 * Time: 10:03 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class TenDigits5Group {
    static int[] list;
    static int[] del;
    static int count = 0;
    final static int N = 4;

    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        list = new int[N];
        for (int i = 0; i < N; i++)
            list[i] = i;

        permutation(0);
        System.out.println(count);
    }

    static void permutation(int idx) {
        if (idx >= N) {
            for (int i : list)
                System.out.print(i);
            System.out.println();
            count++;
            //del = new int[6];
            //del[5] = 10;
            //check(1, 0);
            return;
        }

        for (int i = idx; i < N; i++) {
            swap(i, idx);
            permutation(idx + 1);
            swap(i, idx);
        }
    }

    static void check(int idx, int total) {
        if (idx == 5) {
            total += cal(5);
            if (total == 1089)
                printAns();
            return;
        }
        for (int i = del[idx - 1] + 1; i <= 5 + idx; i++) {
            del[idx] = i;
            int t = total + cal(idx);
            if (t > 1089)
                return;
            check(idx + 1, t);
        }
    }

    static void printAns() {
        //count++;
        /*
        for (int i = 1; i < 6; i++) {
            int num = cal(i);
            System.out.print(num + " + ");
        }
        System.out.println();
        */
    }

    static int cal(int idx) {
        int total = 0;
        for (int i = del[idx] - 1; i >= del[idx - 1]; i--) {
            total = total * 10 + list[i];
        }
        return total;
    }

    static void swap(int a, int b) {
        int x = list[a];
        list[a] = list[b];
        list[b] = x;
    }
}
