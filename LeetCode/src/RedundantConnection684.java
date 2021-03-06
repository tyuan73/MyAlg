/*

*/

import java.util.*;
import java.io.*;

public class RedundantConnection684 {
    public int[] findRedundantConnection(int[][] edges) {
        int[] ds = new int[2001];
        for (int[] e : edges) {
            int p1 = find(ds, e[0]), p2 = find(ds, e[1]);
            if (p1 == p2) return e;
            ds[p1] = p2;
        }
        return null;
    }

    private int find(int[] ds, int idx) {
        return ds[idx] == 0 ? idx : (ds[idx] = find(ds, ds[idx]));
    }
}