/*

*/

import java.util.*;
import java.io.*;

public class MaximumXOROfTwoNumbersInAnArray421 {
    /**
     * HashSet solution https://discuss.leetcode.com/topic/63213/java-o-n-solution-using-bit-manipulation-and-hashmap/2
     */
    public int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        for (int i = 31; i >= 0; i--) {
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num & mask);
            }
            int tmp = max | (1 << i);
            for (int prefix : set) {
                if (set.contains(tmp ^ prefix)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }

    /**
     * Trie based solution - much faster than above hashset solution.
     */
    class Node {
        Node[] next = null;

        Node() {
            next = new Node[2];
        }
    }

    Node root = null;

    public int findMaximumXOR1(int[] nums) {
        root = new Node();
        int max = 0;
        for (int n : nums) {
            max = Math.max(max, maxXOR(n));
        }
        return max;
    }

    private int maxXOR(int n) {
        Node node = root, node1 = root;
        int ans = 0;
        for (int bit = 1 << 30; bit > 0; bit >>= 1) {
            int id = (bit & n) > 0 ? 1 : 0;
            if (node1.next[id] == null)
                node1.next[id] = new Node();
            node1 = node1.next[id];

            if (node.next[id ^ 1] != null) {
                ans |= bit;
                node = node.next[id ^ 1];
            } else
                node = node.next[id];
        }
        return ans;
    }

    // test
    public static void main(String[] args) {
        MaximumXOROfTwoNumbersInAnArray421 test = new MaximumXOROfTwoNumbersInAnArray421();
        int[] data = new int[1000000];
        Random rand = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = rand.nextInt(Integer.MAX_VALUE - 1);
        }

        long start = System.currentTimeMillis();
        test.findMaximumXOR(data);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        test.findMaximumXOR1(data);
        System.out.println(System.currentTimeMillis() - start);
    }
}