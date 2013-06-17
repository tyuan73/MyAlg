/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/1/13
 * Time: 10:30 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class LittleElephantAndRozdil129Div2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int min = Integer.MAX_VALUE, count = 0;
        int position = -1;
        for (int i = 0; i < n; i++) {
            int d = in.nextInt();
            if (d < min) {
                min = d;
                count = 1;
                position = i + 1;
            } else if (d == min) {
                count++;
            }
        }

        if (count == 1) {
            System.out.println(position);
        } else {
            System.out.println("Still Rozdil");
        }
    }
}
