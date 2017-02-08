/**
 * Created by yuantian on 2/8/17.
 */

import java.util.*;

public class FlattenBinaryTreeToLinkedList114 {
    /**
     * Solution 1: use stack to track right children
     *
     * @param root
     */

    public void flatten(TreeNode root) {
        flat(root, new Stack<TreeNode>());
    }

    TreeNode flat(TreeNode root, Stack<TreeNode> s) {
        if (root == null) return null;
        if (root.right != null) {
            s.push(root.right);
            root.right = null;
        }
        if (root.left == null) {
            if (!s.isEmpty())
                root.right = flat(s.pop(), s);
        } else {
            root.right = flat(root.left, s);
            root.left = null;
        }
        return root;
    }

    /**
     * Solution 2: more clean
     */
    TreeNode prev = null;

    public void flatten1(TreeNode root) {
        if (root == null)
            return;

        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}
