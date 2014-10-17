package tools;

import java.util.Arrays;

/**
 * Created by yuantian on 10/15/14.
 */
public class DisjointSetSample {

    static public void main(String[] args) {
        int n = 100;

        // Disjoint Set data structure:
        // http://community.topcoder.com/tc?module=Static&d1=tutorials&d2=disjointDataStructure

        // example of how to use disjoint set. Also see JimAndHisLanParty.java
        DisjointSet djSet = new DisjointSet(n);

        // to join/merge 2 sets
        djSet.merge(1, 3); // 1 and 3 must be root
        // a better way to join 2 sets
        int a = djSet.root(1);
        int b = djSet.root(3);
        if (a != b)
            djSet.merge(a, b);
    }

    static class DisjointSet {
        int[] dset = null;

        public DisjointSet(int n) {
            assert n > 0;
            dset = new int[n];
            Arrays.fill(dset, -1);
        }

        public boolean merge(int a, int b) {
            assert dset[a] < 0 && dset[b] < 0;
            if (dset[a] > dset[b]) {
                dset[a] = b;
                return true;
            } else if (dset[a] == dset[b]) {
                dset[b] = a;
                dset[a]--;
            } else {
                dset[b] = a;
            }
            return false;
        }

        public int root(int a) {
            return dset[a] < 0 ? a : (dset[a] = root(dset[a]));
        }
    }
}
