/**
 * Created by yuantian on 8/22/14.
 */

import java.util.*;

public class MaxPointsOnLine149 {
    public static void main(String[] args) {
        MaxPointsOnLine149 mp = new MaxPointsOnLine149();

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
        //if (points.length == 0) return 0;
        HashMap<ArrayList<Integer>, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < points.length; i++) {
            map.clear();
            int samepoints = 1;
            int val = 0;
            for (int j = i + 1; j < points.length; j++) {
                int x = points[j].x - points[i].x;
                int y = points[j].y - points[i].y;
                if (x == 0 && y == 0) {
                    ++samepoints;
                    continue;
                }
                int gcd = gcd(x, y);
                ArrayList<Integer> key = new ArrayList<>();
                //if (x == 0) {
                //    key.add(0);
                //    key.add(1);
                //} else if (y == 0) {
                //    key.add(1);
                //    key.add(0);
                //} else {
                key.add(x / gcd);
                key.add(y / gcd);
                //}
                map.put(key, map.getOrDefault(key, 0) + 1);
                val = Math.max(val, map.get(key));
            }
            max = Math.max(max, val + samepoints);
        }
        return max;
    }

    int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
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
