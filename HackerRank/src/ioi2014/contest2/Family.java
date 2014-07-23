package ioi2014.contest2;

/**
 * Created by yuantian on 7/23/14.
 */

import java.util.*;

public class Family {
    static class Point implements Comparable<Point> {
        int x, y;
        public Point(int x, int y) {
            this.x = x; this.y = y;
        }
        public int compareTo(Point p) {
            if (this.x != p.x)
                return this.x - p.x;
            return this.y - p.y;
        }
    }

    /*
        a better solution
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] a = new int[3][2];
        int sumx = 0, sumy = 0;
        for(int i = 0; i < 3; i++) {
            a[i][0] = in.nextInt();
            a[i][1] = in.nextInt();
            sumx += a[i][0];
            sumy += a[i][1];
        }
        Point[] f = new Point[3];
        for(int i = 0; i < 3; i++) {
            f[i] = new Point(sumx - 2*a[i][0], sumy - 2*a[i][1]);
        }
        Arrays.sort(f);
        for(Point p : f) {
            System.out.println(p.x + " " + p.y);
        }
    }

    /*
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x1 = in.nextInt();
        int y1 = in.nextInt();
        int x2 = in.nextInt();
        int y2 = in.nextInt();
        int x3 = in.nextInt();
        int y3 = in.nextInt();

        Point[] p = new Point[3];
        int x = x3 - (x2-x1);
        int y = y3- (y2-y1);
        p[0] = new Point(x, y);
        x = x3 + (x2-x1);
        y = y3 + (y2-y1);
        p[1] = new Point(x, y);
        x = x1 + (x2-x3);
        y = y1 + (y2-y3);
        p[2] = new Point(x, y);
        Arrays.sort(p);
        for(Point p1 : p) {
            System.out.println(p1.x + " " + p1.y);
        }
    }
    */
}

