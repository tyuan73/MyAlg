/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/7/13
 * Time: 5:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;

        if(root.left == null && root.right == null)
            return 1;

        int left = minDepth(root.left);
        int right = minDepth(root.right);

        if(left == 0) return right+1;
        if(right == 0) return left+1;
        return Math.min(left, right)+1;
    }
}
