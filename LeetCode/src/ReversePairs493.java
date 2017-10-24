/**
 * Created by yuantian on 3/28/17.
 */

import java.util.*;

public class ReversePairs493 {
    /**
     * BIT + Binary search
     */
    class SolutionBIT_BSearch {
        public int reversePairs(int[] nums) {
            int n = nums.length;
            int[] map = new int[n + 1];
            map[n] = Integer.MIN_VALUE;
            System.arraycopy(nums, 0, map, 0, n);
            Arrays.sort(map);

            int[] bit = new int[n + 1];
            int ans = 0;
            for (int i = n - 1; i >= 0; i--) {
                int x = nums[i];
                int y = x / 2;
                if (x < 0 || (x & 1) == 0)
                    y--;
                ans += sum(bit, index(map, y));

                add(bit, index(map, x));
            }

            return ans;
        }

        private void add(int[] bit, int i) {
            for (; i < bit.length; i += i & -i)
                bit[i]++;
        }

        private int sum(int[] bit, int i) {
            int sum = 0;
            for (; i > 0; i -= i & -i)
                sum += bit[i];
            return sum;
        }

        // binary search
        private int index(int[] map, int x) {
            int l = 0, r = map.length - 1;
            while (l < r) {
                int mid = (l + r + 1) / 2;
                if (map[mid] > x) {
                    r = mid - 1;
                } else {
                    l = mid;
                }
            }
            return l;
        }
    }

    /**
     * BIT + re-index
     */
    public class SolutionBIT2 {
        private Map<Integer, Integer> getMap(int[] nums) {
            int n = nums.length;
            int[] m = new int[2 * n];
            for (int i = 0; i < n; i++) {
                m[i * 2] = nums[i];
                int y = nums[i] <= 0 ? nums[i] - 2 : nums[i] - 1;
                m[i * 2 + 1] = y / 2;
            }
            Arrays.sort(m);
            Map<Integer, Integer> map = new HashMap<>();
            int i = 1;
            for (int x : m) {
                if (!map.containsKey(x)) map.put(x, i++);
            }
            return map;
        }

        public int reversePairs(int[] nums) {
            Map<Integer, Integer> map = getMap(nums);
            int[] bit = new int[map.size() + 1];
            int ans = 0;
            for (int i = nums.length - 1; i >= 0; i--) {
                int y = nums[i] <= 0 ? nums[i] - 2 : nums[i] - 1;
                int idx = map.get(y / 2);
                ans += sum(bit, idx);
                add(bit, map.get(nums[i]));
            }
            return ans;
        }

        private void add(int[] bit, int i) {
            for (; i < bit.length; i += i & -i)
                bit[i]++;
        }

        private int sum(int[] bit, int i) {
            int sum = 0;
            for (; i > 0; i -= i & -i)
                sum += bit[i];
            return sum;
        }
    }


    /**
     * Mergesort - 89ms
     */
    public class SolutionMergesort {
        int[] helper;

        public int reversePairs(int[] nums) {
            this.helper = new int[nums.length];
            return mergeSort(nums, 0, nums.length - 1);
        }

        private int mergeSort(int[] nums, int s, int e) {
            if (s >= e) return 0;
            int mid = s + (e - s) / 2;
            int cnt = mergeSort(nums, s, mid) + mergeSort(nums, mid + 1, e);
            for (int i = s, j = mid + 1; i <= mid; i++) {
                while (j <= e && nums[i] / 2.0 > nums[j]) j++;
                cnt += j - (mid + 1);
            }
            //Arrays.sort(nums, s, e+1);
            myMerge(nums, s, mid, e);
            return cnt;
        }

        private void myMerge(int[] nums, int s, int mid, int e) {
            for (int i = s; i <= e; i++) helper[i] = nums[i];
            int p1 = s;//pointer for left part
            int p2 = mid + 1;//pointer for rigth part
            int i = s;//pointer for sorted array
            while (p1 <= mid || p2 <= e) {
                if (p1 > mid || (p2 <= e && helper[p1] >= helper[p2])) {
                    nums[i++] = helper[p2++];
                } else {
                    nums[i++] = helper[p1++];
                }
            }
        }
    }

    /**
     * SegmentTree solution
     */
    public class SolutionSegmentTree {
        private Map<Integer, Integer> getMap(int[] nums) {
            int n = nums.length;
            int[] m = new int[2 * n];
            for (int i = 0; i < n; i++) {
                m[i * 2] = nums[i];
                int y = nums[i] <= 0 ? nums[i] - 2 : nums[i] - 1;
                m[i * 2 + 1] = y / 2;
            }
            Arrays.sort(m);
            Map<Integer, Integer> map = new HashMap<>();
            int i = 1;
            for (int x : m) {
                if (!map.containsKey(x)) map.put(x, i++);
            }
            return map;
        }

        class SegmentTree {
            int[] st;
            int size;

            SegmentTree(int size) {
                this.size = size;
                st = new int[4 * size];
            }

            private int query(int idx, int l, int r, int ql, int qr) {
                if (qr < l || r < ql) return 0;
                if (ql <= l && r <= qr) return st[idx];
                int left = query(idx * 2 + 1, l, (l + r) / 2, ql, qr);
                int right = query(idx * 2 + 2, (l + r) / 2 + 1, r, ql, qr);
                return left + right;
            }

            public int query(int pos) {
                return query(0, 0, size - 1, 0, pos);
            }

            private void update(int idx, int l, int r, int pos) {
                if (pos < l || r < pos) return;
                st[idx]++;
                if (l == r) return;
                update(idx * 2 + 1, l, (l + r) / 2, pos);
                update(idx * 2 + 2, (l + r) / 2 + 1, r, pos);
            }

            public void update(int pos) {
                update(0, 0, size - 1, pos);
            }
        }

        public int reversePairs(int[] nums) {
            Map<Integer, Integer> map = getMap(nums);
            SegmentTree tree = new SegmentTree(map.size() + 1);
            int ans = 0;
            for (int i = nums.length - 1; i >= 0; i--) {
                int val = nums[i] <= 0 ? nums[i] - 2 : nums[i] - 1;
                ans += tree.query(map.get(val / 2));
                tree.update(map.get(nums[i]));
            }
            return ans;
        }
    }
}

/*
Test cases:
[1,3,2,3,1]
[-2147483648,0]
[0, -2147483648]
[0, 3,-2147483648]
[7,4]
[8,4]
[9,4]
[-7,-4]
[-8,-4]
[-9, -4]
[-10,-4]

output:
2
0
1
2
0
0
1
1
0
0
0
 */
