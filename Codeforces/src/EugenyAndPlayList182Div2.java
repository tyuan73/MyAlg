/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/8/13
 * Time: 2:23 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class EugenyAndPlayList182Div2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int[] time = new int[n];
        int total = 0;
        for(int i = 0; i < n; i++) {
            int t = in.nextInt();
            int c = in.nextInt();
            total += c*t;
            time[i] = total;
        }

        int index = 0;
        while(m-- > 0) {
            int x = in.nextInt();
            while(index < n && time[index] < x)
                index++;
            System.out.println(index+1);
        }
    }
}
