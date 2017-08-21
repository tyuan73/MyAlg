
import java.util.*;

public class EqualTreePartition663 {
    Set<Integer> set = null;
    public boolean checkEqualTree(TreeNode root) {
        set = new HashSet<>();
        int sum = sum(root.left) + sum(root.right) + root.val;
        if ((sum & 1) == 1) return false;
        return set.contains(sum/2);
    }

    private int sum(TreeNode node) {
        if (node == null) return 0;
        int sum = sum(node.left) + sum(node.right) + node.val;
        set.add(sum);
        return sum;
    }
}
