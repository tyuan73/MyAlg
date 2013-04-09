/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/27/13
 * Time: 11:15 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class LeastCostBracketSequence3D {
    static class Element implements Comparable<Element> {
        int index;
        int cost;

        Element(int i, int c) {
            this.index = i;
            this.cost = c;
        }

        public int compareTo(Element e) {
            return this.cost - e.cost;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String bracket = in.next();
        char[] b = bracket.toCharArray();
        int m = 0;
        for (int i = 0; i < b.length; i++)
            if (b[i] == '?')
                m++;

        int[][] cost = new int[m][2];
        for (int i = 0; i < m; i++) {
            cost[i][0] = in.nextInt();
            cost[i][1] = in.nextInt();
        }

        int next = 0, balance = 0;
        long total = 0;
        PriorityQueue<Element> p = new PriorityQueue<Element>();
        for (int i = 0; i < b.length; i++) {
            switch (b[i]) {
                case '(':
                    balance++;
                    break;
                case ')':
                    balance--;
                    break;
                case '?':
                    b[i] = ')';
                    total += cost[next][1];
                    balance--;
                    p.add(new Element(i, cost[next][0] - cost[next][1]));
                    next++;
            }
            if (balance < 0) {
                if (p.size() == 0) {
                    System.out.print(-1);
                    return;
                }
                Element e = p.poll();
                b[e.index] = '(';
                total += e.cost;
                balance += 2;
            }
        }
        if (balance != 0) {
            System.out.println(-1);
            return;
        }

        System.out.println(total);
        System.out.println(b);
    }
}
