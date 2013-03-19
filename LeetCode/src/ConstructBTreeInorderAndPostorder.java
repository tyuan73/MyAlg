/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/4/13
 * Time: 4:32 PM
 * To change this template use File | Settings | File Templates.
 */

public class ConstructBTreeInorderAndPostorder {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);

    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0)
            return null;
        int n = inorder.length;
        return construct(inorder, 0, n - 1, postorder, 0, n - 1);
    }

    TreeNode construct(int[] inorder, int l, int r, int[] postorder, int pl, int root) {
        if (l > r)
            return null;
        TreeNode n = new TreeNode(postorder[root]);
        int ll = l;
        while (inorder[ll] != postorder[root])
            ll++;
        n.left = construct(inorder, l, ll - 1, postorder, pl, pl + (ll - l) - 1);
        n.right = construct(inorder, ll + 1, r, postorder, pl + (ll - l), root - 1);
        return n;
    }
}
