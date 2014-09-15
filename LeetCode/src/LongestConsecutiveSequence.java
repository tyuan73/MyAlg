/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/6/13
 * Time: 12:19 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.HashSet;
import java.util.Scanner;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }

    public int longestConsecutive(int[] num) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i : num)
            set.add(i);

        int max = 0;
        for (int i : num) {
            if (set.contains(i)) {
                int r = i;
                while (set.contains(r)) {
                    set.remove(r);
                    r++;
                }
                int l = i - 1;
                while (set.contains(l)) {
                    set.remove(l);
                    l--;
                }
                max = Math.max(max, r - l - 1);
            }
        }

        return max;
    }

    /* radixsort (O(n)) and then scan (O(n)) */
    public int longestConsecutive1(int[] num) {
        num = this.radixSort(num);
        int length = 1, clength = 1;
        for (int i = 1; i < num.length; i++) {
            if (num[i] == num[i - 1])
                continue;
            if (num[i] == num[i - 1] + 1) {
                clength++;
            } else {
                if (clength >= length) {
                    length = clength;
                }
                clength = 1;
            }
        }
        return clength > length ? clength : length;
    }

    int[] radixSort(int[] num) {
        int l = Integer.MIN_VALUE;
        for (int n : num) {
            if (Math.abs(n) > l)
                l = Math.abs(n);
        }
        int base = 1;

        while (l / base != 0) {
            num = this.countSort(num, base);
            base *= 10;
        }
        return num;
    }

    private int[] countSort(int[] num, int base) {
        int[] psn = new int[num.length];

        int[] counts = new int[19];
        int[] indexes = new int[19];
        for (int n : num) {
            int tail = this.getTail(n, base);
            counts[tail] = counts[tail] + 1;
        }

        indexes[1] = counts[0];
        for (int i = 2; i < counts.length; i++) {
            indexes[i] = indexes[i - 1] + counts[i - 1];
        }

        for (int i = 0; i < num.length; i++) {
            int tail = this.getTail(num[i], base);
            int index = indexes[tail];
            psn[index] = num[i];
            indexes[tail] = index + 1;
        }
        return psn;
    }

    int getTail(int n, int base) {
        return ((n % (base * 10)) / base) + 9;
    }

}
