
import java.util.*;

public class PrintBinaryTree655 {
    public List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root);
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            List<String> line = new ArrayList<>();
            for (int j = 1; j < 1 << height; j++)
                line.add("");
            ans.add(line);
        }
        setValue(root, 1, 0, (1 << height) - 1, ans);
        return ans;
    }

    private void setValue(TreeNode node, int h, int from, int to, List<List<String>> ans) {
        if (node == null) return;
        int mid = (from + to) / 2;
        ans.get(h - 1).set(mid, "" + node.val);
        setValue(node.left, h + 1, from, mid - 1, ans);
        setValue(node.right, h + 1, mid + 1, to, ans);
    }

    private int getHeight(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }
}
