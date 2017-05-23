/**
 * Created by yuantian on 5/17/17.
 */

import java.util.*;

public class ErectTheFence587 {
    class Point {
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

    /**
     * This is a typical Convex Hull problem. The following code uses Graham Scan.
     * http://www.geeksforgeeks.org/convex-hull-set-2-graham-scan/
     *
     * Most of it is pretty easy to understand. The tricky part is that if there are
     * two or more points are colline with the leftmost or bottommost points - origin point.
     *
     * To solve it, we sort all collinear points by distance to the origin point in descending order.
     * This solves most corner cases automatically, except one case:
     *
     * The bottommost line has more that one points other than the origin point. For example:
     *      .     . (<- other points)
     *
     *   . (<- origin point)
     *    .
     *     .          . (<- this point will cancel all points on the collinear line below except the last one)
     *      .
     *       . (<- colline that is the bottommost)
     */
    public class Solution {

        public List<Point> outerTrees(Point[] points) {
            int n = points.length;
            if (n <= 3) return Arrays.asList(points);

            // find the left most point - origin point
            // if there more than one points with the same x value, pick the bottommost one - this is very important.
            // Otherwise, it will fail this test case: [[0,4],[0,9],[0,1],[0,7],[0,6],[2,2]]
            int idx = 0;
            for (int i = 1; i < n; i++) {
                if (points[i].x < points[idx].x ||(points[i].x == points[idx].x && points[i].y < points[idx].y)) {
                    idx = i;
                }
            }
            // move origin point to index 0
            swap(points, 0, idx);

            /**
             * sort all the points anti-clockwise
             * Note: sort [1, n) only. The first one is the origin. Don't sort it.
             */
            Arrays.sort(points, 1, n, new Comparator<Point>() {
                public int compare(Point a, Point b) {
                    int t = turns(points[0], a, b);
                    if (t == 0) {
                        /**
                         * very important: if collinear happens, sort by distance to
                         * origin in DESCENDING order.
                         */
                        return dist(b, points[0]) - dist(a, points[0]);
                    }
                    return -t;
                }
            });

            /**
             * after sorting, the scanning algorithm works in most cases except one mentioned above.
             * To fix it, we need to check if the bottommost line has multiple points other than origin.
             * If yes, we have to make the furthest points the last (by the sorting above, the furthest
             * point is ranked before others).
             *
             * We don't have reverse all points on collinear. Only the last one matters. So we just swap
             * the furthest point from the head to the end.
             */
            for (int i = 1, j = 2; j < n; i++, j++) {
                if (turns(points[0], points[i], points[j]) != 0) {
                    swap(points, 1, i); // move the furthest point to the end
                    break;
                }
            }

            List<Point> ans = new ArrayList<>();
            Point pre = points[0], cur = points[1];
            ans.add(pre);
            ans.add(cur);
            for (int i = 2; i < n; i++) {
                Point next = points[i];
                /**
                 * pre -> cur -> next is a right turn, we cancel "cur" and continue to
                 * check previous point in ans until we reach a right turn or no turn.
                 */
                while (turns(pre, cur, next) < 0) {
                    ans.remove(ans.size() - 1);
                    cur = pre;
                    pre = ans.get(ans.size() - 2);
                }
                ans.add(next);
                pre = cur;
                cur = next;
            }

            return ans;
        }

        private void swap(Point[] points, int i, int j) {
            Point p = points[i];
            points[i] = points[j];
            points[j] = p;
        }

        private int dist(Point a, Point b) {
            return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
        }

        /**
         * return negative if p1 -> p2 -> p3 is right turn.
         * return positive if p1 -> p2 -> p3 is left turn.
         * return 0 if p1, p2, p3 collinear (no turn)
         * http://www.dcs.gla.ac.uk/~pat/52233/slides/Geometry1x1.pdf
         */
        private int turns(Point p1, Point p2, Point p3) {
            return (p3.y - p2.y) * (p2.x - p1.x) - (p2.y - p1.y) * (p3.x - p2.x);
        }

        /**
         * Monotone Chain
         * https://leetcode.com/articles/erect-the-fence/
         *
         * This is like Graham Scan. But it scan lower hull and then upper hull.
         * Remember to remove duplicated points at the end.
         *
         * This is much simpler in coding in this case.
         */
        public List<Point> outerTrees1(Point[] points) {
            int n = points.length;
            if (n <= 3) return Arrays.asList(points);

            // sort all points by x first, if x is the same
            // then compare y.
            Arrays.sort(points, new Comparator<Point> () {
                public int compare(Point p1, Point p2) {
                    if (p1.x == p2.x)
                        return p1.y - p2.y;
                    return p1.x - p2.x;
                }
            });

            List<Point> ans = new ArrayList<>();
            // scan lower hull
            for(Point p : points) {
                while(ans.size() >= 2 && turns(ans.get(ans.size() -2), ans.get(ans.size() -1), p) > 0)
                    ans.remove(ans.size() - 1);
                ans.add(p);
            }
            // scan upper hull
            for(int i = n-2; i >= 0; i--) {
                while(turns(ans.get(ans.size() -2), ans.get(ans.size() -1), points[i]) > 0)
                    ans.remove(ans.size() - 1);
                ans.add(points[i]);
            }

            // remove duplicates. Duplicates only happens when all points are collinear?
            for(int l = 0, r = ans.size() -1; l < r; l++, r--) {
                Point p1 = ans.get(l), p2 = ans.get(r);
                if (p1.x == p2.x && p1.y == p2.y) ans.remove(r);
                else break;
            }
            return ans;
        }
    }
}
