package hack101.Sept2014;

/**
 * Created by yuantian on 10/15/14.
 */

/*

*/

import java.util.*;
import java.io.*;


public class JimAndHisLanParty {

    static void go() {
        int n = in.nextInt();
        int m = in.nextInt();
        int q = in.nextInt();
        int[] game = new int[n];
        int[] count = new int[m];
        for (int i = 0; i < n; i++) {
            game[i] = in.nextInt() - 1;
            count[game[i]]++;
        }

        int[] ans = new int[m];
        Arrays.fill(ans, -1);

        // disjoint set
        DisjointSet djSet = new DisjointSet(n);

        @SuppressWarnings("unchecked")
        Map<Integer, Integer>[] map = new Map[n];
        for (int i = 0; i < n; i++) {
            map[i] = new HashMap<>();
            if (count[game[i]] == 1) {
                ans[i] = 0;
            } else {
                map[i].put(game[i], 1);
            }
        }

        for (int step = 1; step <= q; step++) {
            int p1 = djSet.root(in.nextInt() - 1), p2 = djSet.root(in.nextInt() - 1);
            if (p1 == p2)
                continue;
            if (djSet.merge(p1, p2)) {
                int p = p1;
                p1 = p2;
                p2 = p;
            }
            HashMap<Integer, Integer> m1 = (HashMap<Integer, Integer>) map[p1];
            HashMap<Integer, Integer> m2 = (HashMap<Integer, Integer>) map[p2];
            for (int key : m2.keySet()) {
                if (m1.containsKey(key)) {
                    int total = m1.get(key) + m2.get(key);
                    if (count[key] <= total) {
                        ans[key] = step;
                        m1.remove(key);
                    } else {
                        m1.put(key, total);
                    }
                } else {
                    m1.put(key, m2.get(key));
                }
            }
        }

        for (int x : ans)
            out.println(x);
    }

    static class DisjointSet {
        int[] dset = null;

        public DisjointSet(int n) {
            assert n > 0;
            dset = new int[n];
            Arrays.fill(dset, -1);
        }

        public boolean merge(int a, int b) {
            assert dset[a] < 0 && dset[b] < 0;
            if (dset[a] > dset[b]) {
                dset[a] = b;
                return true;
            } else if (dset[a] == dset[b]) {
                dset[b] = a;
                dset[a]--;
            } else {
                dset[b] = a;
            }
            return false;
        }

        public int root(int a) {
            return dset[a] < 0 ? a : (dset[a] = root(dset[a]));
        }
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