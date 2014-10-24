package problemset369;

/**
 * Created by yuantian on 10/22/14.
 */

/*

*/

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class ValeraAndFools369D {

    static void go() {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] p = in.nextIntArray(n);

        if (n == 1) {
            out.println(1);
            return;
        }
        boolean[][] visited = new boolean[n + 10][n + 10];
        boolean[][] s = new boolean[n + 1][2];
        for (int i = n - 1; i >= 0; i--) {
            s[i][0] = s[i + 1][0];
            s[i][1] = s[i + 1][1];
            if (p[i] == 100)
                s[i][0] = true;
            else if (p[i] > 0)
                s[i][1] = true;
        }

        // BFS, not DFS
        int ans = 0;
        LinkedList<int[]> dp = new LinkedList<>();
        int[] aa = {0, 1, 0};
        dp.add(aa);
        while (dp.size() > 0) {
            int[] bb = dp.removeFirst();
            int first = bb[0];
            int second = bb[1];
            int step = bb[2];

            ans++;
            if (first >= n || second >= n || step >= k) {
                continue;
            }

            if (p[first] > 0 && (s[second][0] || s[second][1]) && !visited[second + 1][second + 2]) {
                int[] cc = {second + 1, second + 2, step + 1};
                dp.addLast(cc);
                visited[second + 1][second + 2] = true;
            }
            if (p[first] > 0 && !s[second][0] && !visited[first][second + 1]) {
                int[] cc = {first, second + 1, step + 1};
                dp.addLast(cc);
                visited[first][second + 1] = true;
            }
            if (p[first] != 100 && (s[second][0] || s[second][1]) && !visited[second][second + 1]) {
                int[] cc = {second, second + 1, step + 1};
                dp.addLast(cc);
                visited[second][second + 1] = true;
            }
        }

        out.println(ans);
    }

    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in = new InputReader(System.in);
        out = new PrintWriter(System.out);

        go();

        out.close();
    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int[] nextIntArray(int len) {
            int[] ret = new int[len];
            for (int i = 0; i < len; i++)
                ret[i] = nextInt();
            return ret;
        }

        public long[] nextLongArray(int len) {
            long[] ret = new long[len];
            for (int i = 0; i < len; i++)
                ret[i] = nextLong();
            return ret;
        }


        public int nextInt() {
            return (int) nextLong();
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String nextString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder sb = new StringBuilder(1024);
            do {
                sb.append((char) c);
                c = read();
            } while (!isSpaceChar(c));
            return sb.toString();
        }

        public static boolean isSpaceChar(int c) {
            switch (c) {
                case -1:
                case ' ':
                case '\n':
                case '\r':
                case '\t':
                    return true;
                default:
                    return false;
            }
        }
    }
}