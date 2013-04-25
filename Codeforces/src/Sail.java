/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/19/13
 * Time: 10:51 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class Sail {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int sx = in.nextInt();
        int sy = in.nextInt();
        int ex = in.nextInt();
        int ey = in.nextInt();
        String wind = in.next();

        int i = 0;
        for (; i < wind.length(); i++) {
            char ch = wind.charAt(i);
            switch (ch) {
                case 'E':
                    if (ex > sx)
                        sx++;
                    break;
                case 'S':
                    if (ey < sy)
                        sy--;
                    break;
                case 'W':
                    if (ex < sx)
                        sx--;
                    break;
                case 'N':
                    if (ey > sy)
                        sy++;
                    break;
            }
            if (sx == ex && sy == ey)
                break;
        }

        if (sx == ex && sy == ey)
            System.out.println(i + 1);
        else
            System.out.println(-1);
    }
}
