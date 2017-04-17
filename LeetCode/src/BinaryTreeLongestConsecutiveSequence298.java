/**
 * Created by yuantian on 4/17/17.
 */
public class BinaryTreeLongestConsecutiveSequence298 {
    int max = 0;

    public int longestConsecutive(TreeNode root) {
        check(root, 0, 0);
        return max;
    }

    private void check(TreeNode node, int parent, int len) {
        max = Math.max(max, len);
        if (node == null)
            return;

        if (node.val != parent + 1)
            len = 0;

        check(node.left, node.val, len + 1);
        check(node.right, node.val, len + 1);
    }
}
