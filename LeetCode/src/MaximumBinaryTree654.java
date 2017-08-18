
import java.util.*;

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

    /**
     * Use stack to keep the right most path from root to the right end.
     */
    public TreeNode constructMaximumBinaryTre1e(int[] nums) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode fakeRoot = new TreeNode(Integer.MAX_VALUE);
        stack.push(fakeRoot);
        for (int x : nums) {
            while (x > stack.peek().val)
                stack.pop();
            TreeNode node = new TreeNode(x);
            node.left = stack.peek().right;
            stack.peek().right = node;
            stack.push(node);
        }
        return fakeRoot.right;
    }
}
