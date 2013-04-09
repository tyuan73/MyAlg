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
}
