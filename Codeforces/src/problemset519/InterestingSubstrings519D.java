package problemset519;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/20/15
 * Time: 10:27 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;


import java.util.*;
import java.io.*;

public class InterestingSubstrings519D {
    static void go() {
        int[] val = in.nextIntArray(26);
        String line = in.nextString();
        int n = line.length();
        ArrayList<Integer>[] pos = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            pos[i] = new ArrayList<Integer>();
        }
        long[] dp = new long[n + 1];
        for (int i = 0; i < n; i++) {
            pos[line.charAt(i) - 'a'].add(i);
            dp[i + 1] = dp[i] + val[line.charAt(i) - 'a'];
        }

        long total = 0;
        for (ArrayList<Integer> list : pos) {
            if (list.size() <= 1) continue;
            LongHashMap hash = new LongHashMap();
            for (int i : list) {
                if (hash.containsKey(dp[i]))
                    total += (int) hash.get(dp[i]);
                int x = hash.containsKey(dp[i + 1]) ? ((int) hash.get(dp[i + 1])) + 1 : 1;
                hash.put(dp[i + 1], x);
            }
        }
        out.println(total);
    }

    // ******** copied from UWI, for testing purpose
    public static class LongHashMap {
        public long[] keys;
        public Object[] allocated;
        private int scale = 1 << 2;
        private int rscale = 1 << 1;
        private int mask = scale - 1;
        public int size = 0;

        public LongHashMap() {
            allocated = new Object[scale];
            keys = new long[scale];
        }

        public boolean containsKey(long x) {
            int pos = h(x) & mask;
            while (allocated[pos] != null) {
                if (x == keys[pos]) return true;
                pos = pos + 1 & mask;
            }
            return false;
        }

        public Object get(long x) {
            int pos = h(x) & mask;
            while (allocated[pos] != null) {
                if (x == keys[pos]) return allocated[pos];
                pos = pos + 1 & mask;
            }
            return null;
        }

        public Object put(long x, Object v) {
            int pos = h(x) & mask;
            while (allocated[pos] != null) {
                if (x == keys[pos]) {
                    Object oldval = allocated[pos];
                    allocated[pos] = v;
                    return oldval;
                }
                pos = pos + 1 & mask;
            }
            if (size == rscale) {
                resizeAndPut(x, v);
            } else {
                keys[pos] = x;
                allocated[pos] = v;
            }
            size++;
            return null;
        }

        public boolean remove(long x) {
            int pos = h(x) & mask;
            while (allocated[pos] != null) {
                if (x == keys[pos]) {
                    size--;
                    // take last and fill rmpos
                    int last = pos;
                    pos = pos + 1 & mask;
                    while (allocated[pos] != null) {
                        int lh = h(keys[pos]) & mask;
                        // lh <= last < pos
                        if (
                                lh <= last && last < pos ||
                                        pos < lh && lh <= last ||
                                        last < pos && pos < lh
                                ) {
                            keys[last] = keys[pos];
                            allocated[last] = allocated[pos];
                            last = pos;
                        }
                        pos = pos + 1 & mask;
                    }
                    keys[last] = 0;
                    allocated[last] = null;

                    return true;
                }
                pos = pos + 1 & mask;
            }
            return false;
        }

        private void resizeAndPut(long x, Object v) {
            int nscale = scale << 1;
            int nrscale = rscale << 1;
            int nmask = nscale - 1;
            Object[] nallocated = new Object[nscale];
            long[] nkeys = new long[nscale];
            itrreset();
            while (true) {
                long y = next();
                if (end()) break;
                int pos = h(y) & nmask;
                while (nallocated[pos] != null) pos = pos + 1 & nmask;
                nkeys[pos] = y;
                nallocated[pos] = allocated[itr];
            }
            {
                int pos = h(x) & nmask;
                while (nallocated[pos] != null) pos = pos + 1 & nmask;
                nkeys[pos] = x;
                nallocated[pos] = v;
            }
            allocated = nallocated;
            keys = nkeys;
            scale = nscale;
            rscale = nrscale;
            mask = nmask;
        }

        public int itr = -1;

        public void itrreset() {
            itr = -1;
        }

        public boolean end() {
            return itr == -1;
        }

        private static int NG = Integer.MIN_VALUE;

        public long next() {
            while (++itr < scale && allocated[itr] == null) ;
            if (itr == scale) {
                itr = -1;
                return NG;
            }
            return keys[itr];
        }

        private int h(long x) {
            x ^= x >>> 33;
            x *= 0xff51afd7ed558ccdL;
            x ^= x >>> 33;
            x *= 0xc4ceb9fe1a85ec53L;
            x ^= x >>> 33;
            return (int) x;
        }

        @Override
        public String toString() {
            itrreset();
            long[] vals = new long[size];
            int p = 0;
            while (true) {
                long y = next();
                if (end()) break;
                vals[p++] = y;
            }
            return Arrays.toString(vals);
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

        public String nextLine() {
            StringBuilder sb = new StringBuilder(1024);
            int c = read();
            while (!(c == '\n' || c == '\r' || c == -1)) {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
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