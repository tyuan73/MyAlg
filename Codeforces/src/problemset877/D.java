package problemset877;


/*

*/

import java.util.*;
import java.io.*;

public class D {
    static void go() {
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
        char[][] map = new char[n][m];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                map[i][j] = in.nextChar();
        int x1 = in.nextInt()-1, y1 = in.nextInt()-1, x2 = in.nextInt()-1, y2 = in.nextInt()-1;
        int[][] dir = new int[n*m][4];
        for(int i = 0; i < n; i++) {
            int next = 0;
            for(int j = 0; j < m; j++) {
                if (map[i][j] == '#')
                    next = j + 1;
                else
                    dir[i*m + j][0] = Math.max(next, j-k);
            }
        }
        for(int i = 0; i < n; i++) {
            int next = m-1;
            for(int j = m-1; j >= 0; j--) {
                if (map[i][j] == '#')
                    next = j - 1;
                else
                    dir[i*m + j][1] = Math.min(next, j+k);
            }
        }

        for(int j = 0; j < m; j++) {
            int next = 0;
            for(int i = 0; i < n; i++) {
                if (map[i][j] == '#')
                    next = i + 1;
                else
                    dir[i*m + j][2] = Math.max(next, i-k);
            }
        }
        for(int j = 0; j < m; j++) {
            int next = n-1;
            for(int i = n-1; i >= 0; i--) {
                if (map[i][j] == '#')
                    next = i - 1;
                else
                    dir[i*m + j][3] = Math.min(next, i+k);
            }
        }



        int step = 0;
        boolean[] visited = new boolean[n*m];
        List<Integer> list = new ArrayList<>();
        list.add(x1 * m + y1);
        while(list.size() > 0) {
            List<Integer> next = new ArrayList<>();
            for(int pos : list) {
                int x3 = pos / m, y3 = pos % m;
                if (x3 == x2 && y3 == y2) {
                    out.println(step);
                    return;
                }
                for(int i = dir[pos][0]; i < y3; i++) {
                    if (!visited[x3 * m + i]) {
                        visited[x3 * m + i] = true;
                        next.add(x3 * m + i);
                    }
                }
                for(int i = y3+1; i <= dir[pos][1]; i++) {
                    if (!visited[x3 * m + i]) {
                        visited[x3 * m + i] = true;
                        next.add(x3 * m + i);
                    }
                }
                for(int i = dir[pos][2]; i < x3; i++) {
                    if (!visited[i * m + y3]) {
                        visited[i * m + y3] = true;
                        next.add(i * m + y3);
                    }
                }
                for(int i = x3 + 1; i <= dir[pos][3]; i++) {
                    if (!visited[i * m + y3]) {
                        visited[i * m + y3] = true;
                        next.add(i * m + y3);
                    }
                }
            }
            list = next;
            step++;
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