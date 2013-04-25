/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/19/13
 * Time: 10:32 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class SnowFootprints {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        String path = in.next();
        int s = 0, t = 0;
        int index = 0;
        while (index < n && path.charAt(index) == '.')
            index++;
        if (path.charAt(index) == 'L') {
            t = index;
            while (index < n && path.charAt(index) == 'L')
                index++;
            s = index;
        } else {
            s = index + 1;
            while (index < n && path.charAt(index) == 'R')
                index++;
            t = path.charAt(index) == '.' ? index + 1 : index;
        }
        System.out.println(s + " " + t);
    }
}
