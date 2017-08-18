public class MaximumBinaryTree654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode root = null;
        for (int x : nums) {
            root = insert(root, x);
        }
        return root;
    }

    private TreeNode insert(TreeNode node, int x) {
        if (node == null || node.val < x) {
            TreeNode n = new TreeNode(x);
            n.left = node;
            return n;
        }
        node.right = insert(node.right, x);
        return node;
    }
}
