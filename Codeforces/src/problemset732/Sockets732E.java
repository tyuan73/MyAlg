package problemset732;

/**
 * Created by yuantian on 11/3/16.
 */

/*
Test: #8, time: 108 ms., memory: 0 KB, exit code: 0, checker exit code: 1, verdict: WRONG_ANSWER
Input
7 14
3001266 412 1 6352768 8464826 377123633 47905
754247265 394532939 770046583 653892313 683423733 954533833 49604822 768323920 915021669 957143548 958842438 215985395 436153649 784873711
Output
5 72
1 0 0 0 0 0 0 8 0 0 30 19 0 14
8 12 11 0 0 1 14
Answer
5 68
1 0 0 0 0 0 26 8 0 0 0 19 0 14
8 12 7 0 0 1 14
Checker Log
wrong answer jury have found the better answer (5, 68) than participant has (5, 72)

*/

import java.util.*;
import java.io.*;

public class Sockets732E {
    static class Socket implements Comparable<Socket> {
        int power, num, idx;

        Socket(int power, int num, int idx) {
            this.power = power;
            this.num = num;
            this.idx = idx;
        }

        public int compareTo(Socket s) {
            return s.power - this.power;
        }
    }

    static class Computer implements Comparable<Computer> {
        int power, idx;

        Computer(int power, int idx) {
            this.power = power;
            this.idx = idx;
        }

        public int compareTo(Computer c) {
            return c.power - this.power;
        }
    }

    static Socket next(Socket s) {
        return new Socket((s.power + 1) / 2, s.num + 1, s.idx);
    }

    static void add(Socket[] s, Socket s1, int r) {
        while (r > 0 && s1.power == s[r - 1].power && s1.num < s[r - 1].num) {
            s[r] = s[r - 1];
            r--;
        }
        s[r] = s1;
    }

    static void go() {
        int n = in.nextInt();
        int m = in.nextInt();
        Computer[] computers = new Computer[n];
        Socket sockets[] = new Socket[m];
        for (int i = 0; i < n; i++) {
            int p = in.nextInt();
            computers[i] = new Computer(p, i);
        }

        for (int i = 0; i < m; i++) {
            int p = in.nextInt();
            sockets[i] = new Socket(p, 0, i);
        }

        Arrays.sort(computers);
        Arrays.sort(sockets);

        Socket temp[] = new Socket[33 * m];
        Socket sorted[] = new Socket[33 * m];
        int l = 0, r = 0;
        int s = 0, k = 0;
        while (k < m) {
            Socket s1 = sockets[k];
            if (l < r) {
                Socket s2 = temp[l];
                if (s1.power >= s2.power) {
                    sorted[s++] = s1;
                    if (s1.power > 1)
                        add(temp, next(s1), r++);
                    k++;
                } else {
                    sorted[s++] = s2;
                    if (s2.power > 1)
                        add(temp, next(s2), r++);
                    l++;
                }
            } else {
                sorted[s++] = s1;
                k++;
                add(temp, next(s1), r++);
            }
        }
        while (l < r) {
            Socket s1 = temp[l];
            sorted[s++] = temp[l++];
            if (s1.power > 1) {
                add(temp, next(s1), r++);
            }
        }

        // check sorted
        /*
        int count = 0;
        if (sorted[0].power == 1) count = 1;
        for (int i = 1; i < s; i++) {
            if (sorted[i].power > sorted[i - 1].power) {
                System.out.println(sorted[i].power + " > " + sorted[i - 1].power);
                //return;
            }
            if (sorted[i].power == sorted[i - 1].power) {
                if (sorted[i].num < sorted[i - 1].num) {
                    System.out.println(sorted[i].num + " < " + sorted[i - 1].num);
                    //return;
                }

            }
            if (sorted[i].power == 1) count++;
        }
        if (count != m) {
            System.out.println("total number is " + count);
            return;
        }
        */


        int[] chosen = new int[m];
        Arrays.fill(chosen, -1);
        int[] c2s = new int[n];
        int nc = 0, ns = 0;
        int is = 0, ic = 0;
        while (is < s && ic < n) {
            Socket s1 = sorted[is];
            Computer c = computers[ic];
            if (s1.power == c.power) {
                if (chosen[s1.idx] == -1) {
                    nc++;
                    ns += s1.num;
                    is++;
                    ic++;
                    chosen[s1.idx] = s1.num;
                    c2s[c.idx] = s1.idx + 1;
                } else {
                    is++;
                }
            } else if (s1.power > c.power) {
                is++;
            } else {
                ic++;
            }
        }

        out.println(nc + " " + ns);

        for (int x : chosen) {
            if (x == -1)
                out.print("0 ");
            else
                out.print(x + " ");
        }
        out.println();

        for (int x : c2s) {
            out.print(x + " ");
        }
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