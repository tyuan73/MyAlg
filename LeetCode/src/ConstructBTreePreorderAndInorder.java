/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/4/13
 * Time: 4:42 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class ConstructBTreePreorderAndInorder {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        return construct(preorder, 0, inorder, 0, n-1);
    }

    TreeNode construct(int[] preorder, int root, int[] inorder, int l, int r) {
        if(l > r)
            return null;

        TreeNode n = new TreeNode(preorder[root]);
        int ll = l;
        while(inorder[ll] != preorder[root])
            ll++;
        n.left = construct(preorder, root+1, inorder, l, ll-1);
        n.right = construct(preorder, root+ll-l+1, inorder, ll+1, r);
        return n;
    }
}
