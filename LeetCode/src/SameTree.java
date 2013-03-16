/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/16/13
 * Time: 12:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;
        if(p == null || q == null || p.val != q.val)
            return false;

        return isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);
    }
}
