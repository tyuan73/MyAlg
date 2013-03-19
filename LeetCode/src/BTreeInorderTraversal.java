/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

import java.util.ArrayList;
import java.util.Stack;

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
}