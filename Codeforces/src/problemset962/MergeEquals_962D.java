package problemset962;


/*

 */

import java.util.*;
import java.io.*;

public class MergeEquals_962D {

    static void go() {
        int n = in.nextInt();
        Map<Long, Integer> last = new HashMap<>();
        int count = n;
        long[] a = in.nextLongArray(n);
        for (int i = 0; i < n; ) {
            if (last.containsKey(a[i])) {
                int l = last.remove(a[i]);
                a[l] = 0;
                count--;
                a[i] *= 2;
            } else {
                last.put(a[i], i);
                i++;
            }
        }
        out.println(count);
        for (long x : a)
            if (x > 0)
                out.print(x + " ");
        out.println();
    }

    /**
     * First ACed solution. Use TreeMap.
     */
    static void go1() {
        int n = in.nextInt();
        TreeMap<Long, List<Integer>> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            long x = in.nextLong();
            if (!map.containsKey(x))
                map.put(x, new ArrayList<>());
            map.get(x).add(i);
        }
        int count = 0;
        long[] ans = new long[n];
        while (!map.isEmpty()) {
            long key = map.firstKey();
            List<Integer> l1 = map.remove(key);
            if (l1.size() >= 2) {
                if (!map.containsKey(2 * key))
                    map.put(2 * key, new ArrayList<>());
                List<Integer> l2 = map.get(key * 2);
                for (int i = 1; i < l1.size(); i += 2)
                    l2.add(l1.get(i));
                Collections.sort(l2);
            }

            if ((l1.size() % 2) == 1) {
                count++;
                ans[l1.get(l1.size() - 1)] = key;
            }
        }

        out.println(count);
        for (long x : ans)
            if (x != 0)
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