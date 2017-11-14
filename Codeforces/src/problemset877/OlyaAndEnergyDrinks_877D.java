package problemset877;


/*

*/

import java.util.*;
import java.io.*;

public class OlyaAndEnergyDrinks_877D {
    final static int[][] steps = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    static void go() {
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                map[i][j] = in.nextChar();
        int x1 = in.nextInt() - 1, y1 = in.nextInt() - 1, x2 = in.nextInt() - 1, y2 = in.nextInt() - 1;

        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++)
            Arrays.fill(dist[i], 10000000);

        dist[x1][y1] = 0;
        int[] list = new int[n * m];
        int size = 1;
        list[0] = x1 << 10 | y1;
        for (int i = 0; i < size; i++) {
            int x = list[i] >> 10, y = list[i] & 1023;
            if (x == x2 && y == y2) {
                out.println(dist[x][y]);
                return;
            }
            for (int[] s : steps) {
                for (int j = 1; j <= k; j++) {
                    int nX = x + j * s[0], nY = y + j * s[1];
                    if (nX < 0 || nX >= n || nY < 0 || nY >= m || map[nX][nY] == '#' || dist[nX][nY] < dist[x][y] + 1)
                        break;
                    if (dist[nX][nY] > dist[x][y] + 1) {
                        list[size++] = nX << 10 | nY;
                        dist[nX][nY] = dist[x][y] + 1;
                    }
                }
            }
        }

        out.println("-1");
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

        public String nextLine() {
            StringBuilder sb = new StringBuilder(1024);
            int c = read();
            while (!(c == '\n' || c == '\r' || c == -1)) {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
        }

        public char nextChar() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            return (char) c;
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

        public char[] nextCharArray(int n) {
            char[] ca = new char[n];
            for (int i = 0; i < n; i++) {
                int c = read();
                while (isSpaceChar(c))
                    c = read();
                ca[i] = (char) c;
            }
            return ca;
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