
import java.util.*;

public class MaximumWidthofBinaryTree662 {
    /**
     * BFS, iterative solution
     *
     * Use ArrayDeque, offerLast, pollLast, offerFirst, pollFirst instead of LinkedList
     */
    public int widthOfBinaryTree(TreeNode root) {
        ArrayDeque<TreeNode> list = new ArrayDeque<>();
        ArrayDeque<Integer> list1 = new ArrayDeque<>();
        int size = 0, ans = 0;
        list.offerLast(root);
        list1.offerLast(1);
        while ((size = list.size()) > 0) {
            ans = Math.max(ans, list1.getLast() - list1.getFirst() + 1);
            while (size-- > 0) {
                TreeNode node = list.pollFirst();
                int i = list1.pollFirst();
                if (node.left != null) {
                    list.offerLast(node.left);
                    list1.offerLast(i * 2);
                }
                if (node.right != null) {
                    list.offerLast(node.right);
                    list1.offerLast(i * 2 + 1);
                }
            }
        }
        return ans;
    }
}
