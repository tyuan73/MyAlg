package problemset734;

/**
 * Created by yuantian on 11/16/16.
 */

/*

*/

import java.util.*;
import java.io.*;

public class AntonAndChess734D {
    static class Ele {
        int x, y, type;

        public Ele(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    static void go() {
        int n = in.nextInt();
        int x0 = in.nextInt(), y0 = in.nextInt();
        int dx1 = x0 + y0, dx2 = x0 - y0;

        List<Ele> check1 = new ArrayList<>(); // x == x0
        List<Ele> check2 = new ArrayList<>(); // y == y0
        List<Ele> check3 = new ArrayList<>(); // x0+y0 == x + y
        List<Ele> check4 = new ArrayList<>(); // x0-y0 == x - y

        for (int i = 0; i < n; i++) {
            String s = in.nextString();
            int type = 0;
            if (s.charAt(0) == 'R')
                type = 1;
            else if (s.charAt(0) == 'B')
                type = 2;
            else
                type = 3;
            int x = in.nextInt(), y = in.nextInt();
            if (x == x0)
                check1.add(new Ele(x, y, type));
            else if (y == y0)
                check2.add(new Ele(x, y, type));
            else if (x + y == dx1)
                check3.add(new Ele(x, y, type));
            else if (x - y == dx2)
                check4.add(new Ele(x, y, type));
        }

        Collections.sort(check1, new Comparator<Ele>() {
            @Override
            public int compare(Ele o1, Ele o2) {
                return o1.y - o2.y;
            }
        });
        Collections.sort(check2, new Comparator<Ele>() {
            @Override
            public int compare(Ele o1, Ele o2) {
                return o1.x - o2.x;
            }
        });
        Collections.sort(check3, new Comparator<Ele>() {
            @Override
            public int compare(Ele o1, Ele o2) {
                return o1.x - o2.x;
            }
        });
        Collections.sort(check4, new Comparator<Ele>() {
            @Override
            public int compare(Ele o1, Ele o2) {
                return o1.x - o2.x;
            }
        });

        int idx = 0;
        if (check1.size() > 0) {
            idx = searchY(check1, y0);
            if (idx >= 0 && (check1.get(idx).type & 1) > 0) {
                out.println("YES");
                return;
            }
            if (idx < check1.size() - 1 && (check1.get(idx + 1).type & 1) > 0) {
                out.println("YES");
                return;
            }
        }

        if (check2.size() > 0) {
            idx = searchx(check2, x0);

            if (idx >= 0 && (check2.get(idx).type & 1) > 0) {
                out.println("YES");
                return;
            }
            if (idx < check2.size() - 1 && (check2.get(idx + 1).type & 1) > 0) {
                out.println("YES");
                return;
            }
        }

        if (check3.size() > 0) {
            idx = searchx(check3, x0);
            if (idx >= 0 && (check3.get(idx).type & 2) > 0) {
                out.println("YES");
                return;
            }
            if (idx < check3.size() - 1 && (check3.get(idx + 1).type & 2) > 0) {
                out.println("YES");
                return;
            }
        }

        if (check4.size() > 0) {
            idx = searchx(check4, x0);
            if (idx >= 0 && (check4.get(idx).type & 2) > 0) {
                out.println("YES");
                return;
            }
            if (idx < check4.size() - 1 && (check4.get(idx + 1).type & 2) > 0) {
                out.println("YES");
                return;
            }
        }

        out.println("NO");
    }

    static int searchY(List<Ele> list, int y) {
        int l = -1, r = list.size() - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (list.get(mid).y > y)
                r = mid - 1;
            else
                l = mid;
        }
        return l;
    }

    static int searchx(List<Ele> list, int x) {
        int l = -1, r = list.size() - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (list.get(mid).x > x)
                r = mid - 1;
            else
                l = mid;
        }
        return l;
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