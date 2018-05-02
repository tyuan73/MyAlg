package problemset963;


/*

 */

import java.util.*;
import java.io.*;

public class DestructionofaTree_963B {
    static void go() {
        int n = in.nextInt();
        if ((n % 2) == 0) {
            out.println("NO");
            return;
        }
        int[] deg = new int[n];
        List<Integer>[] tree = new List[n];
        for (int i = 0; i < n; i++)
            tree[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int a = in.nextInt() - 1;
            if (a == -1) continue;
            tree[i].add(a);
            tree[a].add(i);
            deg[a]++;
            deg[i]++;
        }
        int[] status = new int[n];
        List<Integer> ans = new ArrayList<>();
        dfs(tree, status, 0, deg, ans);
        out.println("YES");
        for (int x : ans)
            out.println(x + " ");
    }

    static private void dfs(List<Integer>[] tree, int[] status, int idx, int[] deg, List<Integer> ans) {
        if (status[idx] != 0) return;
        if ((deg[idx] % 2) == 0) {
            status[idx] = 2;
            ans.add(idx + 1);
            for (int next : tree[idx])
                deg[next]--;
            for (int next : tree[idx])
                if (status[next] == 0)
                    dfs(tree, status, next, deg, ans);
        } else {
            status[idx] = 1;
            for (int next : tree[idx])
                if (status[next] == 0)
                    dfs(tree, status, next, deg, ans);
            status[idx] = 0;
            dfs(tree, status, idx, deg, ans);
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