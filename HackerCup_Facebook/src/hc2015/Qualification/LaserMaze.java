package hc2015.Qualification;/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 1/30/15
 * Time: 11:00 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class LaserMaze {
    FastScanner in;
    PrintWriter out;

    static final int[][] D = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    int row = 0;
    int col = 0;
    int[][][] visited = null;
    int[][] maze = null;
    Point start = null;
    Point end = null;
    int min = Integer.MAX_VALUE;

    class Point {
        int x, y;

        public Point(int x1, int y1) {
            this.x = x1;
            this.y = y1;
        }
    }

    public void solve() throws IOException {
        long st = System.currentTimeMillis();
        int t = in.nextInt();

        for (int x = 1; x <= t; x++) {
            row = in.nextInt();
            col = in.nextInt();
            char[][] m = new char[row][];
            for (int i = 0; i < row; i++) {
                m[i] = in.next().toCharArray();
            }

            maze = new int[row][col];
            start = new Point(0, 0);
            end = new Point(0, 0);
            List<Point> lasers = new ArrayList<Point>();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    switch (m[i][j]) {
                        case 'S':
                            start.x = i;
                            start.y = j;
                            break;
                        case 'G':
                            end.x = i;
                            end.y = j;
                            break;
                        case '#':
                            maze[i][j] = 15;
                            break;
                        case '<':
                        case '>':
                        case '^':
                        case 'v':
                            lasers.add(new Point(i, j));
                            maze[i][j] = 15;
                            break;
                        default:
                            break;
                    }
                }
            }

            for (Point p : lasers) {
                for (int mod = 0; mod <= 3; mod++) {
                    int index = 0;
                    switch (m[p.x][p.y]) {
                        case 'v':
                            index = mod;
                            break;
                        case '<':
                            index = (1 + mod) % 4;
                            break;
                        case '^':
                            index = (2 + mod) % 4;
                            break;
                        case '>':
                            index = (3 + mod) % 4;
                            break;
                    }
                    setLaserPath(p, 1 << mod, D[index][0], D[index][1]);
                }
            }

            /*
            for(int[] k : maze) {
                for(int z : k) {
                    System.out.print(z + " ");
                }
                System.out.println();
            }
            */

            min = Integer.MAX_VALUE;
            visited = new int[row][col][4];
            for(int[][] z : visited) {
                for(int[] y : z)
                    Arrays.fill(y, Integer.MAX_VALUE);
            }
            visited[start.x][start.y][0] = 0;
            for (int[] s : D) {
                Point next = new Point(start.x + s[0], start.y + s[1]);
                dfs(next, 1);
            }

            if (min == Integer.MAX_VALUE) {
                out.println("Case #" + x + ": impossible");
                System.out.println("Case #" + x + ": impossible");
            } else {
                out.println("Case #" + x + ": " + min);
                System.out.println("Case #" + x + ": " + min);
            }

        }
        System.out.println("Total time is " + (System.currentTimeMillis() - st));
        // dfs: Total time is 3577
    }

    private void dfs(Point p, int step) {
        if (p.x < 0 || p.x >= row
                || p.y < 0 || p.y >= col
                || (maze[p.x][p.y] & (1 << (step % 4))) > 0
                || visited[p.x][p.y][step % 4] <= step
                || step >= min)
            return;

        visited[p.x][p.y][step % 4] = step;

        if (p.x == end.x && p.y == end.y) {
            min = Math.min(step, min);
            return;
        }
        for (int[] s : D) {
            Point next = new Point(p.x + s[0], p.y + s[1]);
            dfs(next, step + 1);
        }
    }

    private void setLaserPath(Point p, int mask, int dX, int dY) {
        int x = p.x + dX;
        int y = p.y + dY;
        while (x >= 0 && x < row && y >= 0 && y < col && maze[x][y] != 15) {
            maze[x][y] |= mask;
            x += dX;
            y += dY;
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("/Users/yuantian/MyAlg/HackerCup_Facebook/src/hc2015/Qualification/LaserMaze.in"));
            out = new PrintWriter(new File("/tmp/test/LaserMaze.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new LaserMaze().run();
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(File file) throws IOException {
            br = new BufferedReader(new FileReader(file));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                    return "";
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
