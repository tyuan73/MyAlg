/**
 * Definition for binary tree
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class BTreeMaxPathSum {
    int max;

    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE; // if empty path is allowed, init max with 0, that is: max = 0;
        maxSum(root);
        return max;
    }

    int maxSum(TreeNode root) {
        if (root == null)
            return 0;

        int left = maxSum(root.left);
        int right = maxSum(root.right);
        if (left + right + root.val > max)
            max = left + right + root.val;

        int ret = root.val + Math.max(left, right);
        return Math.max(0, ret);
    }
}