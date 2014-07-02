package weekly.week6.day3;

/**
 * Created by yuantian on 7/2/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class MinimumAverageWaitingTime {

    static void go() {
        int n = in.nextInt();
        Customer[] c = new Customer[n];
        for(int i = 0; i < n; i++) {
            c[i] = new Customer(in.nextInt(), in.nextInt());
        }
        Arrays.sort(c, Customer.Order.ByT.ascending());

        PriorityQueue<Customer> q =
                new PriorityQueue<Customer>(n, Customer.Order.ByL.ascending());
        long time = c[0].t;
        int idx = 0;
        while(idx < n && c[idx].t <= time) {
            q.add(c[idx]);
            idx++;
        }

        long wait = 0;
        while(q.size() > 0) {
            Customer next = q.poll();
            time += next.l;
            wait += time - next.t;

            if (idx < n && q.size() == 0 && time < c[idx].t) {
                time = c[idx].t;
            }
            while(idx < n && c[idx].t <= time) {
                q.add(c[idx]);
                idx++;
            }
        }
        out.println(wait / n);
    }

    public static class Customer {
        public Long t, l;
        public Customer(long t1, long l1) {this.t = t1; this.l = l1;}

        public static enum Order implements Comparator<Customer> {
            ByT() {
                public int compare(Customer c1, Customer c2) {
                    return c1.t.compareTo(c2.t);
                }
            },
            ByL() {
                public int compare(Customer c1, Customer c2) {
                    return c1.l.compareTo(c2.l);
                }
            };

            public abstract int compare(Customer c1, Customer c2);

            public Comparator ascending() {
                return this;
            }

            public Comparator descending() {
                return Collections.reverseOrder(this);
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