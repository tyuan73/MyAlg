/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/25/13
 * Time: 11:04 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class Factorial {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            int zeros = 0;
            int fives = 5;
            while(n >= fives) {
                zeros += n/fives;
                fives *= 5;
            }
            System.out.println(zeros);
        }
    }
}
