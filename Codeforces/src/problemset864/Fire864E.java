package problemset864;


/*

*/

import java.util.*;
import java.io.*;

public class Fire864E {
    static void go() {
        int n = in.nextInt(), m = 0;
        int[][] items = new int[n][4];
        for (int i = 0; i < n; i++) {
            items[i][0] = in.nextInt();
            items[i][1] = in.nextInt();
            items[i][2] = in.nextInt();
            items[i][3] = i + 1;
            m = Math.max(m, items[i][1]);
        }

        int[][] dp = new int[n + 1][m + 1];
        List<Integer>[][] list = new List[n + 1][m + 1];
        for (int i = 0; i <= m; i++)
            list[0][i] = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            list[i][0] = new ArrayList<>();
        Arrays.sort(items, (a, b) -> (a[1] - b[1]));
        for (int i = 1; i <= n; i++) {
            int[] item = items[i - 1];
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                list[i][j] = new ArrayList<>();
                if (dp[i][j - 1] >= dp[i - 1][j]) {
                    list[i][j].addAll(list[i][j - 1]);
                } else
                    list[i][j].addAll((list[i - 1])[j]);
                if (j >= item[1] || j < item[0]) continue;
                if (dp[i - 1][j - item[0]] + item[2] > dp[i][j]) {
                    dp[i][j] = dp[i - 1][j - item[0]] + item[2];
                    list[i][j] = new ArrayList<>();
                    list[i][j].addAll(list[i - 1][j - item[0]]);
                    list[i][j].add(item[3]);
                }
            }
        }

        out.println(dp[n][m]);
        out.println(list[n][m].size());
        for (int x : list[n][m])
            out.print(x + " ");
        out.println();
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

/*

input:
9
13 18 14
8 59 20
9 51 2
18 32 15
1 70 18
14 81 14
10 88 16
18 52 3
1 50 6

expect:
106
8
1 4 9 8 2 5 6 7


input:
5
12 44 17
10 12 11
16 46 5
17 55 5
6 60 2

expect:
35
4
2 1 3 5

 */