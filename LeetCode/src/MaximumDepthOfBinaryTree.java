/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/7/13
 * Time: 10:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;

        return Math.max(maxDepth(root.left),
                maxDepth(root.right))
                + 1;
    }
}
