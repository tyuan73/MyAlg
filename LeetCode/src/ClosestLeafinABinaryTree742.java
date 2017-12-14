/*

*/

import java.util.*;
import java.io.*;

public class ClosestLeafinABinaryTree742 {
    /**
     * First solution. 2 passes.
     * In the first round, calculate closed leaf for each and every node.
     * Then in the second round, search the path to K and find the closed leaf.
     */
    class Solution1 {
        class Ele {
            int dis, val;

            Ele(int dis, int val) {
                this.dis = dis;
                this.val = val;
            }
        }

        Map<Integer, Ele> closeLeaf = new HashMap<>();
        Ele ans = new Ele(0, 0);

        public int findClosestLeaf(TreeNode root, int k) {
            getCloseLeaf(root);
            ans = closeLeaf.get(k);

            find(root, new Ele(100000, 0), k);

            return ans.val;
        }

        private void find(TreeNode node, Ele parent, int k) {
            if (node == null) return;
            if (node.val == k) {
                if (parent.dis + 1 < ans.dis) {
                    ans = parent;
                    ans.dis++;
                }
                return;
            }
            Ele myLeaf = closeLeaf.get(node.val);
            if (parent.dis + 1 < myLeaf.dis) {
                myLeaf = parent;
                myLeaf.dis++;
            }
            find(node.left, myLeaf, k);
            find(node.right, myLeaf, k);
        }

        private Ele getCloseLeaf(TreeNode node) {
            if (node == null)
                return new Ele(100000, 0);
            if (node.left == null && node.right == null) {
                Ele ret = new Ele(0, node.val);
                closeLeaf.put(node.val, ret);
                return ret;
            }

            Ele left = getCloseLeaf(node.left);
            Ele right = getCloseLeaf(node.right);
            Ele leaf = left.dis > right.dis ? new Ele(right.dis + 1, right.val) : new Ele(left.dis + 1, left.val);

            closeLeaf.put(node.val, leaf);
            return leaf;
        }
    }

    /**
     * Solution 2. Instead of store closest leaf for each and every node in a hashmap, we
     * only keep the closest leaf for nodes that are on the path from root to K. The nodes on
     * the path are stored in a Deque.
     * <p>
     * One pass to get closest leaf and then check the node on the path only.
     */
    class Solution2 {
        class Ele {
            int dis, val;

            Ele(int dis, int val) {
                this.dis = dis;
                this.val = val;
            }
        }

        Deque<Ele> path = new LinkedList<>();
        boolean findK = false;

        public int findClosestLeaf(TreeNode root, int k) {
            getCloseLeaf(root, k);

            Ele ans = path.pollFirst();
            while (path.size() > 0) {
                Ele e = path.pollFirst();
                if (ans.dis + 1 < e.dis)
                    ans.dis++;
                else
                    ans = e;
            }
            return ans.val;
        }

        private Ele getCloseLeaf(TreeNode node, int k) {
            if (node == null)
                return new Ele(100000, 0);

            Ele leaf = new Ele(0, node.val);
            if (!findK) path.offerLast(leaf);

            if (node.val == k) findK = true;

            if (node.left == null && node.right == null)
                return leaf;

            Ele left = getCloseLeaf(node.left, k);
            Ele right = getCloseLeaf(node.right, k);

            if (left.dis > right.dis) {
                leaf.dis = right.dis + 1;
                leaf.val = right.val;
            } else {
                leaf.dis = left.dis + 1;
                leaf.val = left.val;
            }

            if (!findK)
                path.pollLast();

            return leaf;
        }
    }

    /**
     * The best solution. One pass, no extra space other than recursive space. Each node gets visited
     * exactly one time.
     */
    class Solution3 {
        int min = 100000, val = 0, dist = 0;
        boolean findK = false;

        public int findClosestLeaf(TreeNode root, int k) {
            closestLeaf(root, k);
            return val;
        }

        private int[] closestLeaf(TreeNode node, int k) {
            if (node == null) return new int[]{100000, 0};

            boolean onPath = !findK;
            if (node.val == k) findK = true;

            int[] leaf = {0, node.val};
            if (node.left != null || node.right != null) {
                int[] left = closestLeaf(node.left, k);
                int[] right = closestLeaf(node.right, k);
                leaf = left[0] > right[0] ? right : left;
                leaf[0]++;
            }

            if (findK && onPath) {
                if (leaf[0] + dist < min) {
                    min = leaf[0] + dist;
                    val = leaf[1];
                }
                dist++;
            }

            return leaf;
        }
    }
}