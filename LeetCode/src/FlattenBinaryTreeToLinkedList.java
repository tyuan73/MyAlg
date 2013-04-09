/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/5/13
 * Time: 2:22 PM
 * To change this template use File | Settings | File Templates.
 */

public class FlattenBinaryTreeToLinkedList {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);

    }

    public void flatten(TreeNode root) {
        while (root != null) {
            TreeNode p = root.left;
            if (p != null) {
                while (p.right != null) {
                    p = p.right;
                }
                p.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }
}
