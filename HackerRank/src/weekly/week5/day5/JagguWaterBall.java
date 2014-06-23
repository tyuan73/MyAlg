package weekly.week5.day5;

/**
 * Created by yuantian on 6/20/14.
 */

/*
Jaggu is a little kid and he likes playing with water balloons. He took 1 million ( 106 ) empty buckets and he filled the bucket with water balloons under the instruction of his sister Ishika.
His sister gives him two types of commands:

R pos1 pos2 which implies that jaggu needs to tell her what is the total number of water balloons in the bucket from pos1 to pos2 (both included).

U pos M plus which implies that he has to work like the function

Update(pos,M,plus)

void Update(int pos,int M,int plus)
{
    int N=1000000;  //1 million
    for (int i=1;i<=50;i++)
    {
        int back = pos
        for(int j=1;j<=1000;j++)
        {
            add M water ballons at bucket pos
            int s,in=__builtin_popcount(pos);
            for(int k=0;;k++)
            {
                s=pos+pow(2,k)
                if( __builtin_popcount(s) <= in )
                {
                    in = __builtin_popcount(s)
                    pos = s;
                    if(pos>N)       break;
                    add M water ballons at bucket pos
                }
            }
            pos = pos - N
        }
        pos = back+plus;
        if(pos>N) pos-=N;
    }
}
Jaggu is too lazy to put the water ballons in the bucket. Afraid that he might be caught for not doing what his sister told him to do so, he asks your help to provide correct answers for each of his sister's query. .

Input Format

First line contains Q, number of queries to follow.

Next Q line follows , which can be either an Update Query or Report Query.Each Update Query is followed by atleast 1 report query.

Output Format

For each report query , output the answer in a separate line.

Constraints

1 ≤ Q ≤ 2 * 105

1 ≤ pos1,pos2,pos ≤ 106

pos1 ≤ pos2

1 ≤ M ≤ 10

1 ≤ plus ≤ 999999

Sample Input

2
U 692778 7 291188
R 636916 747794
Sample Output

378
Explanation

Follow the code above to get the answer.

Note

Input is randomly generated.

__builtin_popcount(x) gives the number of set bits in binary representation of x.

pow(2,k) denotes 2 raised to k , i.e. exponentiation of 2.

Timelimit is 3 times the timelimit mentioned here
*/

import java.util.*;
import java.io.*;

public class JagguWaterBall {
    static long[] buckets = null;

    static void go() {
        int q = in.nextInt();

        buckets = new long[1000002];
        while (q-- > 0) {
            String command = in.nextString();
            if (command.charAt(0) == 'U') {
                int p = in.nextInt();
                int m = in.nextInt();
                int plus = in.nextInt();
                update(p, m, plus);
            } else {
                int p1 = in.nextInt();
                int p2 = in.nextInt();
                //out.println(read(p2) - read(p1-1));
                out.println(getBetween(p1-1, p2));
            }
        }
    }

    static void update(int pos, int m, int plus) {
        int N = 1000000;  //1 million
        for (int i = 1; i <= 50; i++) {
            //System.out.println("i= " + i);
            int back = pos;
            //System.out.println("start pos = " + pos);
            for (int j = 1; j <= 1000; j++) {
                //System.out.println("pos1 = " + pos);
                //buckets[pos] += m;
                add(pos, m);
                int s, in = getBITs(pos);
                for (int k = 0; ; k++) {
                    //System.out.println("k = " + k);
                    s = pos + (1<<k);
                    if (getBITs(s) <= in) {
                        in = getBITs(s);
                        pos = s;
                        if (pos > N) {
                            //System.out.println("pos = " + pos);
                            break;
                        }
                        //System.out.println("pos2 = " + pos);
                        //buckets[pos] += m;
                        add(pos, m);
                    }
                }
                //System.out.println(" j = " + j + ": " + pos);
                pos = pos - N;
                if (pos == 48576 && j < 1000) {
                    long val = (1000 - j) * m;
                    add(48576, val);
                    add(48640, val);
                    add(49152, val);
                    add(65536, val);
                    add(131072, val);
                    add(262144, val);
                    add(524288, val);
                    break;
                }
            }
            pos = back + plus;
            //System.out.println("pos3 = " + pos);
            if (pos > N)
                pos -= N;
        }
    }

    static long getBetween(int p1, int p2) {
        long ret = 0;
        while(p1 != p2) {
            if (p2 > p1) {
                ret += buckets[p2];
                p2 -= (p2 & -p2);
            } else {
                ret -= buckets[p1];
                p1 -= (p1 & -p1);
            }
        }
        return ret;
    }

    static long read(int idx) {
        long sum = 0;
        while(idx > 0) {
            sum += buckets[idx];
            idx -= (idx & -idx);
        }
        return sum;
    }

    static void add(int idx, long x) {
        while(idx <= 1000000) {
            buckets[idx] += x;
            idx += (idx & -idx);
        }
    }

    static int getBITs(long x) {
        int ret = 0;
        while (x > 0) {
            ret++;
            x &= x - 1;
        }
        return ret;
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