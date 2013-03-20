/**
 * Definition for binary tree
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class BalancedBinaryTree {
    boolean balanced = true;

    public boolean isBalanced(TreeNode root) {
        balanced = true;
        checkSubtree(root);
        return balanced;
    }

    int checkSubtree(TreeNode root) {
        if (root == null)
            return 0;
        int left = checkSubtree(root.left);
        if (!balanced)
            return 0;
        int right = checkSubtree(root.right);
        if (!balanced)
            return 0;
        balanced = Math.abs(left - right) <= 1;
        return Math.max(left, right) + 1;
    }
}