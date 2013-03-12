/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/12/13
 * Time: 3:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null)
            return false;

        if(root.left == null && root.right == null) {
            return sum == root.val;
        }

        if(hasPathSum(root.left, sum - root.val)
           || hasPathSum(root.right, sum - root.val))
            return true;

        return false;
    }
}
