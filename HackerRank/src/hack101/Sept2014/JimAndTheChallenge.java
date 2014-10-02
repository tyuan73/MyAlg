package hack101.Sept2014;

/**
 * Created by yuantian on 10/2/14.
 */

/*

https://www.hackerrank.com/contests/101sep14/challenges/jim-and-the-challenge

Hey buddy,

How are you? Last night I saw you programming on Hackerrank in my burger restauraunt. So I wonder if you can help me out on a problem I've been struggling with for like 10 hours.

It is called "The Challenge" and for some reasons the online judge always gives me TLE (Time Limit Exceeded). My code and the important part of the problem are attached with this email. I omitted the problem statement because it was three A4 sheets long and included a very boring story.

I want you to send me an AC (Accepted) code so that I can learn from you and figure out why my implementation is so slow.

Your favourite burger cook,

Jim

PS: Tuesday is "Happy Burgers Day" so there is a -π% discount on all burgers!

the_challenge.psc

### author: jim
### problem: the challenge
### status: TLE
### a..b : iterate from a to b ( both inclusive )
define n, d as integers
define H as an integer array
define X as a two-dimensional integer array

function m(i, j)
   for k = 1..d
      dist = dist + abs(X[i][k] - X[j][k])

function main()
  read n and d from input
  for i = 1..n
     read H[i]
    for j = 1..d
      read X[i][j]
  sum = 0
  for i = 1..n
      for j = i+1..n
       sum = sum + H[i] * H[j] * m(i, j)
  print sum mod 1000000009
the_challenge.pdf

Input Format

On the first line, you will be given n and d, then n lines follow, describing the n points. Each point is described as (Hi,Xi1,Xi2,...,Xid). So the first integer of each line is Hi, then d integers follow which will describe the X values of point i.

Output Format

Print the answer to the challenge modulo 1000000009 on a single line.

Constraints

1≤d≤4
1≤n≤3⋅105
1≤Hi≤106
|Xij|≤106
Sample Input

3 3
5 1 2 3
7 2 3 4
8 4 5 6
Sample Output

801
Explanation

Compare point 1 and point 2: Our first term of the sum is 5⋅7⋅(|1−2|+|2−3|+|3−4|)=105.

Compare point 1 and point 3: The second term is 5⋅8⋅(|1−4|+|2−5|+|3−6|)=360
Compare point 2 and point 3: The final term will be 7⋅8⋅(|2−4|+|3−5|+|4−6|)=336
So the answer is 105+360+336=801.
*/

import java.util.*;
import java.io.*;

public class JimAndTheChallenge {
    static long P = 1000000009;

    static void go() {
        int n = in.nextInt();
        int d = in.nextInt();
        int[][] x = new int[n][d + 1];
        long hsum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= d; j++)
                x[i][j] = in.nextInt();
            hsum += x[i][0];
        }

        long ret = 0;
        for (int i = 1; i <= d; i++) {
            sort(x, i);
            long a = -hsum;
            long pre = 0;
            for (int j = 0; j < n; j++) {
                a = (a + x[j][0] + pre) % P;
                ret = (ret + ((a * x[j][0]) % P) * x[j][i]) % P;
                pre = x[j][0];
            }
        }

        if (ret < 0)
            ret += P;
        out.println(ret);
    }

    static void sort(int[][] x, final int index) {
        Arrays.sort(x, new Comparator<int[]>() {
                    @Override
                    public int compare(int[] x1, int[] x2) {
                        return x1[index] - x2[index];
                    }
                }
        );
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