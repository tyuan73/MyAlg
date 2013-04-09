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
    /**
     * the first solution uses a class-wide variable "balanced".
     */
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


    /**
     * The second solution eliminate the class-wide variable "balanced".
     * It used the return value of height() method to tell if the tree is
     * balanced or not. If the value < 0, for example -1, the tree is NOT
     * balanced, otherwise, the tree is balanced.
     */
    public boolean isBalanced2(TreeNode root) {
        return height(root) >= 0;
    }

    int height(TreeNode root) {
        if(root == null)
            return 0;
        int left = height(root.left);
        if(left < 0)
            return left;
        int right = height(root.right);
        if(right < 0)
            return right;
        if(Math.abs(left-right) > 1)
            return -1;

        return Math.max(left, right)+1;
    }
}