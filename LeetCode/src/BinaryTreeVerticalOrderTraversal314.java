/**
 * Created by yuantian on 3/2/17.
 */

import java.util.*;

public class BinaryTreeVerticalOrderTraversal314 {
    class NodeInfo {
        int col;
        TreeNode node;

        NodeInfo(int col, TreeNode node) {
            this.col = col;
            this.node = node;
        }
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;

        Map<Integer, List<Integer>> map = new TreeMap<>();

        List<NodeInfo> level = new ArrayList<>();
        level.add(new NodeInfo(0, root));
        while (!level.isEmpty()) {
            List<NodeInfo> next = new ArrayList<>();
            for (NodeInfo nodeinfo : level) {
                if (!map.containsKey(nodeinfo.col))
                    map.put(nodeinfo.col, new ArrayList<>());
                map.get(nodeinfo.col).add(nodeinfo.node.val);
                if (nodeinfo.node.left != null)
                    next.add(new NodeInfo(nodeinfo.col - 1, nodeinfo.node.left));
                if (nodeinfo.node.right != null)
                    next.add(new NodeInfo(nodeinfo.col + 1, nodeinfo.node.right));
            }
            level = next;
        }

        for (int col : map.keySet())
            ans.add(map.get(col));
        return ans;
    }
}
