/**
 * Created by yuantian on 8/22/14.
 */

import java.util.*;

public class MaxPointsOnLine {
    public static void main(String[] args) {
        MaxPointsOnLine mp = new MaxPointsOnLine();

        Point[] p = new Point[3];

        // test 1
        p[0] = new Point(2, 3);
        p[1] = new Point(3, 3);
        p[2] = new Point(-5, 3);
        // output : 3

        /* test 2
        p[0] = new Point(2, 3);
        p[1] = new Point(3, 3);
        p[2] = new Point(-5, 3);
        // output : 3
         */
        System.out.println(mp.maxPoints(p));
    }

    public int maxPoints(Point[] points) {
        int max = 0;
        HashMap<Slop, Integer> map = new HashMap<Slop, Integer>();
        for (int i = 0; i < points.length; i++) {
            int cur = 1, overlap = 0;
            map.clear();
            for (int j = i + 1; j < points.length; j++) {
                int x = points[j].x - points[i].x;
                int y = points[j].y - points[i].y;
                if (x == 0 && y == 0) {
                    overlap++;
                    continue;
                }

                int g = gcd(x, y);
                Slop slop = new Slop(x / g, y / g);
                int count = map.containsKey(slop) ? map.get(slop) + 1 : 2;
                map.put(slop, count);
                cur = Math.max(cur, count);
            }
            max = Math.max(max, cur + overlap);
        }
        return max;
    }

    public int gcd(int x, int y) {
        if (x == 0) return y;
        if (y == 0) return x;
        return gcd(y % x, x);
    }

    class Slop {
        int x;
        int y;

        Slop(int a, int b) {
            x = a;
            y = b;
        }

        public boolean equals(Object other) {
            return (other != null && other instanceof Slop && ((Slop) other).x == x && ((Slop) other).y == y);
        }

        public int hashCode() {
            return x + y;
        }
    }

    static class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }
}
