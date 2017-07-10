/**
 * Created by yuantian on 7/3/17.
 */

import java.util.*;

public class SmallestRange632 {
    public int[] smallestRange(List<List<Integer>> nums) {
        int k = nums.size();
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            for (int x : nums.get(i)) {
                list.add(new int[]{x, i});
            }
        }
        Collections.sort(list, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int min = -1, max = 2000000, count = 0, pre = 0;
        int[] flag = new int[k];
        for (int i = 0; i < list.size(); i++) {
            int[] e = list.get(i);
            if (flag[e[1]]++ == 0) {
                count++;
                while (count == k) {
                    if (e[0] - list.get(pre)[0] < max - min) {
                        min = list.get(pre)[0];
                        max = e[0];
                    }
                    if (--flag[list.get(pre++)[1]] == 0) count--;
                }
            }
        }

        return new int[]{min, max};
    }

    /**
     * the same solution using a defined class instead.
     */
    class Ele implements Comparable<Ele> {
        int val = 0, idx = 0;

        Ele(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        public int compareTo(Ele e) {
            return this.val - e.val;
        }
    }

    public int[] smallestRange1(List<List<Integer>> nums) {
        int k = nums.size();
        List<Ele> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            for (int x : nums.get(i)) {
                list.add(new Ele(x, i));
            }
        }
        Collections.sort(list);
        int min = -1, max = 2000000, count = 0, pre = 0;
        int[] flag = new int[k];
        for (int i = 0; i < list.size(); i++) {
            Ele e = list.get(i);
            if (flag[e.idx]++ == 0) {
                count++;
                while (count == k) {
                    if (e.val - list.get(pre).val < max - min) {
                        min = list.get(pre).val;
                        max = e.val;
                    }
                    if (--flag[list.get(pre++).idx] == 0) count--;
                }
            }
        }

        return new int[]{min, max};
    }
}
