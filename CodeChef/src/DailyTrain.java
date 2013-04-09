/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/25/13
 * Time: 11:16 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class DailyTrain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int x = in.nextInt();
        int n = in.nextInt();

        int max = 6;
        int[][] pascal = new int[max + 1][max + 1];
        //pascal[0][0] = 1;
        for (int i = 0; i <= max; i++) {
            pascal[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
            }
        }

        int total = 0;
        for (int i = 0; i < n; i++) {
            String train = in.next();
            for (int l = 0, r = 53; l < 36; l += 4, r -= 2) {
                int a = 0;
                if (train.charAt(l) == '0')
                    a++;
                if (train.charAt(l + 1) == '0')
                    a++;
                if (train.charAt(l + 2) == '0')
                    a++;
                if (train.charAt(l + 3) == '0')
                    a++;
                if (train.charAt(r) == '0')
                    a++;
                if (train.charAt(r - 1) == '0')
                    a++;
                total += pascal[a][x];
            }
        }

        System.out.println(total);
    }
}
