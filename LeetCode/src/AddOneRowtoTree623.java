/**
 * Created by yuantian on 6/19/17.
 */
public class AddOneRowtoTree623 {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newHead = new TreeNode(v);
            newHead.left = root;
            return newHead;
        }
        add(root, 1, v, d);
        return root;
    }

    private void add(TreeNode node, int dep, int v, int d) {
        if (node == null) return;
        if (dep == d - 1) {
            TreeNode left = new TreeNode(v);
            TreeNode right = new TreeNode(v);
            left.left = node.left;
            right.right = node.right;
            node.left = left;
            node.right = right;
        } else {
            add(node.left, dep + 1, v, d);
            add(node.right, dep + 1, v, d);
        }
    }
}
