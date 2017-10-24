/*

*/

import java.util.*;
import java.io.*;

public class RangeSumQueryMutable307 {
    /**
     * BIT/Fenwick Tree solution
     */
    class NumArrayFW {
        int[] nums = null;

        private void add(int i, int val) {
            for (; i < nums.length; i += i & -i)
                nums[i] += val;
        }

        private int sum(int i, int j) {
            int sum = 0;
            while (i != j) {
                if (i > j) {
                    sum -= nums[i];
                    i -= i & -i;
                } else {
                    sum += nums[j];
                    j -= j & -j;
                }
            }
            return sum;
        }

        public NumArrayFW(int[] nums) {
            this.nums = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                add(i + 1, nums[i]);
            }
        }

        public void update(int i, int val) {
            int old = sum(i, i + 1);
            add(i + 1, val - old);
        }

        public int sumRange(int i, int j) {
            return sum(i, j + 1);
        }
    }

    /**
     * Segment Tree solution
     */
    class NumArray {
        class SegmentTree {
            int[] st, val;
            int size = 0;

            SegmentTree(int[] val) {
                this.size = val.length;
                this.st = new int[4 * this.size];
                this.val = Arrays.copyOf(val, val.length);
                //System.out.println("size = " + this.size);
                if (this.size > 0)
                    build(0, 0, this.size - 1);
            }

            private int build(int idx, int from, int to) {
                if (from == to) {
                    st[idx] = val[from];
                    return st[idx];
                }
                int left = build(idx * 2 + 1, from, (from + to) / 2);
                int right = build(idx * 2 + 2, (from + to) / 2 + 1, to);
                st[idx] = left + right;
                return st[idx];
            }

            private int query(int idx, int from, int to, int ql, int qr) {
                if (qr < from || ql > to) return 0;
                if (ql <= from && to <= qr) return st[idx];
                int left = query(idx * 2 + 1, from, (from + to) / 2, ql, qr);
                int right = query(idx * 2 + 2, (from + to) / 2 + 1, to, ql, qr);
                return left + right;
            }

            public int query(int l, int r) {
                return query(0, 0, size - 1, l, r);
            }

            private void update(int idx, int from, int to, int pos, int diff) {
                if (pos < from || to < pos) return;
                st[idx] += diff;
                if (from == to) return;
                update(idx * 2 + 1, from, (from + to) / 2, pos, diff);
                update(idx * 2 + 2, (from + to) / 2 + 1, to, pos, diff);
            }

            public void update(int pos, int v) {
                int diff = v - val[pos];
                val[pos] = v;
                update(0, 0, size - 1, pos, diff);
            }
        }

        SegmentTree tree = null;

        public NumArray(int[] nums) {
            tree = new SegmentTree(nums);
        }

        public void update(int i, int val) {
            tree.update(i, val);
        }

        public int sumRange(int i, int j) {
            return tree.query(i, j);
        }
    }
}