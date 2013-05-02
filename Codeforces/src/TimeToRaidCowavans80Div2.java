/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/2/13
 * Time: 2:03 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class TimeToRaidCowavans80Div2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] cow = new int[n];
        for(int i = 0; i< n; i++)
            cow[i] = in.nextInt();

        int p = in.nextInt();
        while(p-- > 0){
            int a = in.nextInt()-1;
            int b = in.nextInt();
            long total = 0;
            for(int i = a; i < n; i += b)
                total += cow[i];

            System.out.println(total);
        }
    }
}
