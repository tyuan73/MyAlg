package p10107;

/**
 * Created by yuantian on 4/6/15.
 */

import java.util.*;

class Main {
    static class RevInt implements Comparable<RevInt> {
        int val;

        public RevInt(int v) {
            this.val = v;
        }

        public int compareTo(RevInt i) {
            return i.val - this.val;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        PriorityQueue<Integer> upper = new PriorityQueue<>();
        PriorityQueue<RevInt> lower = new PriorityQueue<>();
        upper.add(Integer.MAX_VALUE);
        lower.add(new RevInt(-1));
        while (in.hasNextLine()) {
            int x = Integer.parseInt(in.nextLine().trim());
            if (x < upper.peek()) {
                lower.add(new RevInt(x));
            } else {
                upper.add(x);
            }
            int c1 = upper.size();
            int c2 = lower.size();
            if (c1 == c2) {
                System.out.println(lower.peek().val + (upper.peek() - lower.peek().val) / 2);
            } else if (c1 == c2 + 1) {
                System.out.println(upper.peek());
            } else if (c1 == c2 - 1) {
                System.out.println(lower.peek().val);
            } else {
                if (c1 > c2) {
                    lower.add(new RevInt(upper.poll()));
                } else {
                    upper.add(lower.poll().val);
                }
                System.out.println(lower.peek().val + (upper.peek() - lower.peek().val) / 2);
            }
        }
    }
}
