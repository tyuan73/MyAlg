/**
 * Created by yuantian on 6/14/17.
 */
public class BinaryTreeLongestConsecutiveSequenceII549 {
    int max = 0;

    public int longestConsecutive(TreeNode root) {
        path(root, Integer.MAX_VALUE - 1);
        return max;
    }

    private int[] path(TreeNode node, int parent) {
        if (node == null)
            return new int[]{0, 0};
        int[] left = path(node.left, node.val);
        int[] right = path(node.right, node.val);
        max = Math.max(max, Math.max(left[0] + right[1] + 1, left[1] + right[0] + 1));
        if (node.val == parent + 1) {
            return new int[]{0, Math.max(left[1], right[1]) + 1};
        } else if (node.val == parent - 1) {
            return new int[]{Math.max(left[0], right[0]) + 1, 0};
        }
        return new int[]{0, 0};
    }
}
