/**
 * Created by yuantian on 10/17/14.
 */
public class RecoverBinarySearchTree {
    TreeNode first = null;
    TreeNode second = null;

    public void recoverTree(TreeNode root) {
        first = null;
        second = null;
        check(root, new TreeNode(Integer.MIN_VALUE));
        int x = first.val;
        first.val = second.val;
        second.val = x;
    }

    private TreeNode check(TreeNode r, TreeNode lastChecked) {
        if (r == null)
            return lastChecked;
        lastChecked = check(r.left, lastChecked);
        if (r.val < lastChecked.val) {
            if (first == null)
                first = lastChecked;
            second = r;
        }

        return check(r.right, r);
    }
}
