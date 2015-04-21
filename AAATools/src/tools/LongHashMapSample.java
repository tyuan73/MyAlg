package tools;/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/20/15
 * Time: 11:27 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class LongHashMapSample {

    // ******** copied from UWI, for testing purpose
    public static class LongHashMap {
        public long[] keys;
        public Object[] allocated;
        private int scale = 1 << 2;
        private int rscale = 1 << 1;
        private int mask = scale - 1;
        public int size = 0;

        public LongHashMap() {
            allocated = new Object[scale];
            keys = new long[scale];
        }

        public boolean containsKey(long x) {
            int pos = h(x) & mask;
            while (allocated[pos] != null) {
                if (x == keys[pos]) return true;
                pos = pos + 1 & mask;
            }
            return false;
        }

        public Object get(long x) {
            int pos = h(x) & mask;
            while (allocated[pos] != null) {
                if (x == keys[pos]) return allocated[pos];
                pos = pos + 1 & mask;
            }
            return null;
        }

        public Object put(long x, Object v) {
            int pos = h(x) & mask;
            while (allocated[pos] != null) {
                if (x == keys[pos]) {
                    Object oldval = allocated[pos];
                    allocated[pos] = v;
                    return oldval;
                }
                pos = pos + 1 & mask;
            }
            if (size == rscale) {
                resizeAndPut(x, v);
            } else {
                keys[pos] = x;
                allocated[pos] = v;
            }
            size++;
            return null;
        }

        public boolean remove(long x) {
            int pos = h(x) & mask;
            while (allocated[pos] != null) {
                if (x == keys[pos]) {
                    size--;
                    // take last and fill rmpos
                    int last = pos;
                    pos = pos + 1 & mask;
                    while (allocated[pos] != null) {
                        int lh = h(keys[pos]) & mask;
                        // lh <= last < pos
                        if (
                                lh <= last && last < pos ||
                                        pos < lh && lh <= last ||
                                        last < pos && pos < lh
                                ) {
                            keys[last] = keys[pos];
                            allocated[last] = allocated[pos];
                            last = pos;
                        }
                        pos = pos + 1 & mask;
                    }
                    keys[last] = 0;
                    allocated[last] = null;

                    return true;
                }
                pos = pos + 1 & mask;
            }
            return false;
        }

        private void resizeAndPut(long x, Object v) {
            int nscale = scale << 1;
            int nrscale = rscale << 1;
            int nmask = nscale - 1;
            Object[] nallocated = new Object[nscale];
            long[] nkeys = new long[nscale];
            itrreset();
            while (true) {
                long y = next();
                if (end()) break;
                int pos = h(y) & nmask;
                while (nallocated[pos] != null) pos = pos + 1 & nmask;
                nkeys[pos] = y;
                nallocated[pos] = allocated[itr];
            }
            {
                int pos = h(x) & nmask;
                while (nallocated[pos] != null) pos = pos + 1 & nmask;
                nkeys[pos] = x;
                nallocated[pos] = v;
            }
            allocated = nallocated;
            keys = nkeys;
            scale = nscale;
            rscale = nrscale;
            mask = nmask;
        }

        public int itr = -1;

        public void itrreset() {
            itr = -1;
        }

        public boolean end() {
            return itr == -1;
        }

        private static int NG = Integer.MIN_VALUE;

        public long next() {
            while (++itr < scale && allocated[itr] == null) ;
            if (itr == scale) {
                itr = -1;
                return NG;
            }
            return keys[itr];
        }

        private int h(long x) {
            x ^= x >>> 33;
            x *= 0xff51afd7ed558ccdL;
            x ^= x >>> 33;
            x *= 0xc4ceb9fe1a85ec53L;
            x ^= x >>> 33;
            return (int) x;
        }

        @Override
        public String toString() {
            itrreset();
            long[] vals = new long[size];
            int p = 0;
            while (true) {
                long y = next();
                if (end()) break;
                vals[p++] = y;
            }
            return Arrays.toString(vals);
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }
}
