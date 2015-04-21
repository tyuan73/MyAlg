package p10507;

/**
 * Created by yuantian on 4/21/15.
 */

/*

*/

import java.util.*;
import java.io.*;

class Main {
    static void go() {
        //int t = in.nextInt();

        while(true) {
            int n = in.nextInt();
            int m = in.nextInt();
            List<Integer>[] g = new List[26];
            for (int i = 0; i < 26; i++) {
                g[i] = new ArrayList<>();
            }

            int[] visited = new int[26];
            ArrayList<Integer> wake = new ArrayList<>();
            String line = in.nextString();
            for (int i = 0; i < line.length(); i++) {
                wake.add(line.charAt(i) - 'A');
                visited[line.charAt(i) - 'A'] = 3;
            }
            for (int i = 0; i < m; i++) {
                line = in.nextString();
                int a = line.charAt(0) - 'A';
                int b = line.charAt(1) - 'A';
                g[a].add(b);
                g[b].add(a);
            }

            int count = 3, step = -1;
            while (wake.size() > 0) {
                ArrayList<Integer> next = new ArrayList<>();
                for (int x : wake) {
                    for (int y : g[x]) {
                        if (visited[y] < 3) {
                            visited[y]++;
                            if (visited[y] == 3) {
                                next.add(y);
                                count++;
                            }
                        }
                    }
                }
                wake = next;
                step++;
            }
            if (count == n) {
                out.printf("WAKE UP IN, %d, YEARS\n", step);
            } else {
                out.println("THIS BRAIN NEVER WAKES UP");
            }
            try {
                line = in.nextLine();
            } catch (Exception e) {
                break;
            }
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
            while (!(c == '\n' || c == '\r')) {
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

/**
 *

 input:

 6
 11
 HAB
 AB
 AC
 AH
 BD
 BC
 BF
 CD
 CF
 CH
 DF
 FH

 5
 0
 ABZ

 5
 6
 ABC
 AD
 AE
 BD
 BE
 CD
 CE

 3
 0
 ABZ

 4
 0
 ATJ

 11
 12
 ABCD
 AB
 BC
 CD
 BE
 AE
 AF
 AG
 AH
 AI
 AJ
 AK
 KJ

 8
 0
 TYJ

 10
 20
 ABC
 AB
 BC
 CD
 DE
 EF
 FG
 IJ
 FI
 IC
 DA
 BJ
 FJ
 AC
 DF
 HI
 HB
 GA
 GF
 BG
 CH

 6
 14
 ABC
 AB
 AC
 BC
 AD
 BD
 CD
 AE
 BE
 CE
 DE
 FB
 FC
 FD
 FE

 8
 16
 XYZ
 ZA
 AX
 BY
 BZ
 CX
 CY
 DZ
 YD
 XE
 ZE
 XY
 YZ
 ZX
 EF
 CA
 BD

 7
 12
 JOM
 PO
 PJ
 LM
 PM
 NO
 NP
 QP
 QN
 NM
 QJ
 LJ
 LP

 3
 0
 IUH

 6
 9
 ABC
 AD
 BD
 EC
 ED
 EA
 FE
 FA
 FB
 DC

 6
 9
 ABC
 AD
 BD
 CD
 EC
 ED
 EA
 FD
 FC
 FA

 output:

 WAKE UP IN, 3, YEARS
 THIS BRAIN NEVER WAKES UP
 WAKE UP IN, 1, YEARS
 WAKE UP IN, 0, YEARS
 THIS BRAIN NEVER WAKES UP
 THIS BRAIN NEVER WAKES UP
 THIS BRAIN NEVER WAKES UP
 THIS BRAIN NEVER WAKES UP
 WAKE UP IN, 2, YEARS
 THIS BRAIN NEVER WAKES UP
 WAKE UP IN, 3, YEARS
 WAKE UP IN, 0, YEARS
 WAKE UP IN, 3, YEARS
 WAKE UP IN, 2, YEARS

 */