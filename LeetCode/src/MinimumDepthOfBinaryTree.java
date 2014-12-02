import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/7/13
 * Time: 5:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class MinimumDepthOfBinaryTree {
    /**
     * Solution 1: DFS, recursive
     */
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;

        // the next 2 lines are not required.
        //if (root.left == null && root.right == null)
        //    return 1;

        int left = minDepth(root.left);
        int right = minDepth(root.right);

        if (left == 0) return right + 1;
        if (right == 0) return left + 1;
        return Math.min(left, right) + 1;
    }

    /**
     * Solution 2: BFS - travel by level, iterative
     */
    public int minDepth1(TreeNode root) {
        if (root == null)
            return 0;
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
        list.add(root);
        int depth = 0;
        while (list.size() > 0) {
            depth++;
            ArrayList<TreeNode> next = new ArrayList<TreeNode>();
            for (TreeNode node : list) {
                if (node.left == null && node.right == null)
                    return depth;
                if (node.left != null)
                    next.add(node.left);
                if (node.right != null)
                    next.add(node.right);
            }
            list = next;
        }
        return depth;
    }

    /**
     * Solution 3: improved DFS, recursive.
     */
    int min = 0;

    public int minDepth2(TreeNode root) {
        if (root == null)
            return 0;
        min = Integer.MAX_VALUE;
        travel(root, 1);
        return min;
    }

    private void travel(TreeNode root, int depth) {
        if (root == null || depth >= min) {
            return;
        }

        if (root.left == null && root.right == null) {
            min = Math.min(min, depth);
            return;
        }

        travel(root.left, depth + 1);
        travel(root.right, depth + 1);
    }
}
