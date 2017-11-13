package problemset886;


/*

*/

import java.util.*;
import java.io.*;

public class RestorationofString_886D {
    static void go() {
        int n = in.nextInt();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String str = in.nextString();
            if (dup(str)) {
                out.println("NO");
                return;
            }
            List<String> next = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                String a = combine(list.get(j), str);
                if (a == null) {
                    out.println("NO");
                    return;
                }
                if (a.length() == 0) {
                    next.add(list.get(j));
                } else {
                    str = a;
                }
            }
            next.add(str);
            list = next;
        }

        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for (String s : list)
            sb.append(s);
        out.println(sb.toString());
    }


    // null: invalid, "": canont combine, non-empty string: combined
    static String combine(String s1, String s2) {
        String a = check(s1, s2);
        if (a != null) return a;

        a = check(s2, s1);
        if (a != null) return a;

        if (dup(s1 + s2)) return null;
        return "";
    }

    static String check(String s1, String s2) {
        String ret = null;
        for (int i = 1, j = s2.length() - 1; i <= s1.length() && j >= 0; i++, j--) {
            if (s1.substring(0, i).equals(s2.substring(j))) {
                ret = s2 + s1.substring(i);
                break;
            }
        }
        if (ret == null) {
            if (s1.contains(s2))
                ret = s1;
        } else {
            if (dup(ret))
                return null;
        }
        return ret;
    }

    static boolean dup(String s) {
        if (s.length() > 26) return true;
        int[] count = new int[128];
        for (int i = 0; i < s.length(); i++) {
            if (++count[s.charAt(i)] > 1) return true;
        }
        return false;
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