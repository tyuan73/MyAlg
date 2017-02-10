/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 2/9/17
 * Time: 9:33 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class IPO502 {
    class Prj implements Comparable<Prj> {
        int p, c;

        Prj(int p, int c) {
            this.p = p;
            this.c = c;
        }

        public int compareTo(Prj p1) {
            return this.c - p1.c;
        }
    }

    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        int n = Profits.length;
        Prj[] projects = new Prj[n];
        for (int i = 0; i < n; i++)
            projects[i] = new Prj(Profits[i], Capital[i]);
        Arrays.sort(projects);

        PriorityQueue<Prj> q = new PriorityQueue<>(new Comparator<Prj>() {
            public int compare(Prj p1, Prj p2) {
                return p2.p - p1.p;
            }
        });
        // or: PriorityQueue<Prj> q = new PriorityQueue<>((p1, p2) -> (p2.p - p1.p));

        int idx = 0;
        while (k > 0) {
            while (idx < n && projects[idx].c <= W)
                q.add(projects[idx++]);
            if (q.isEmpty())
                break;
            W += q.poll().p;
            k--;
        }

        return W;
    }
}
