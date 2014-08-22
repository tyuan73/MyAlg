/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

import java.util.*;

public class BTreeInorderTraversal {
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        Stack<TreeNode> s = new Stack<TreeNode>();
        addLeft(root, s);
        while (!s.empty()) {
            TreeNode n = s.pop();
            ret.add(n.val);
            addLeft(n.right, s);
        }
        return ret;
    }

    void addLeft(TreeNode n, Stack<TreeNode> s) {
        while (n != null) {
            s.push(n);
            n = n.left;
        }
    }

    /**
     * recursive solution
     */
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> ans = new ArrayList<Integer>();
        internal(root, ans);
        return ans;
    }

    private void internal(TreeNode root, List<Integer> list) {
        if (root == null)
            return;

        internal(root.left, list);
        list.add(root.val);
        internal(root.right, list);
    }
}