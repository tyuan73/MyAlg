/**
 * Created by yuantian on 3/20/14.
 */

import java.util.*;

public class InnaAndSweetMatrix390D {
    static class Point {
        int x, y;

        public Point(int a, int b) {
            this.x = a;
            this.y = b;
        }

        public void print() {
            System.out.print("(" + x + ", " + y + ") ");
        }
    }

    static ArrayList<Point> steps = new ArrayList<Point>();
    static int total = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();

        steps.clear();
        steps.add(new Point(1, 1));
        total = 0;

        search(k, n, m);
    }

    static void search(int rem, int row, int col) {
        if (rem == 0) {
            System.out.println(total);
            return;
        }

        int n = (int) Math.sqrt(rem);

        n = Math.min(n, Math.min(row, col));
        total += n * n * n;
        if (row > n && col > n) {
            int p = (rem - n * n + 1) / 2;
            int q = (rem - n * n) / 2;
            total += (steps.size()+n-1) * (p + q) + (1 + p) * p / 2 + (1 + q) * q / 2;

            System.out.println(total);

            if (p > 0) {
                addPoints(n, true);
                addPoints(p - 1, false);
                printPath(p);
                removePoints(n - 2);
            }

            if (q > 0) {
                addPoints(n, false);
                addPoints(q - 1, true);
                printPath(q);
                removePoints(n - 2);
            }

        } else if (row > n) {
            addPoints(n, true);

            search(rem - n * n, row - n, col);

            removePoints(n);
        } else if (col > n) {
            addPoints(n, false);

            search(rem - n * n, row, col - n);
            removePoints(n);
        } else {
            System.out.println(total);
        }

        printSquare(n);
    }

    static void printSquare(int n) {
        if (n == 1) {
            for (int i = 0; i < steps.size(); i++)
                steps.get(i).print();
            System.out.println();
            return;
        }

        addPoints(n - 1, true);
        addPoints(n - 1, false);
        printPath(n);
        removePoints(n - 2);
        addPoints(n - 1, false);
        addPoints(n - 2, true);
        printPath(n - 1);
        removePoints(n - 2);
        printSquare(n - 1);
    }

    static void addPoints(int n, boolean up) {
        int baseX = steps.get(steps.size() - 1).x;
        int baseY = steps.get(steps.size() - 1).y;

        if (up) {
            for (int i = 0; i < n; i++)
                steps.add(new Point(baseX, baseY + i + 1));
        } else {
            for (int i = 0; i < n; i++)
                steps.add(new Point(baseX + i + 1, baseY));
        }
    }

    static void removePoints(int n) {
        for (int i = 0; i < n; i++)
            steps.remove(steps.size() - 1);
    }

    static void printPath(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < steps.size(); j++) {
                steps.get(j).print();
            }
            System.out.println();
            steps.remove(steps.size() - 1);
        }
    }
}
