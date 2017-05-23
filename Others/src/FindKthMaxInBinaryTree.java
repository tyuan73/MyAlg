class FindKthMaxInBinaryTree {
    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        TreeNode(int v) {
            this.val = v;
        }
    }

    public static void main(String[] args) {
        int[] count = {0};
        int k = 8;
        FindKthMaxInBinaryTree f = new FindKthMaxInBinaryTree();
        TreeNode ret = f.findKthMax(f.createTree(), k, count);
        if (count[0] >= k)
            //return ret;
            System.out.println("the k-th value is " + ret.val);

        //return null;
        System.out.println("can not find the k-th value!");
    }


    TreeNode findKthMax(TreeNode root, int k, int[] count) {
        if (root == null) {
            count[0] = 0;
            return null;
        }
        TreeNode n = findKthMax(root.left, k, count);
        int leftCount = count[0];
        if (leftCount >= k)
            return n;
        if (leftCount + 1 == k) {
            count[0]++;
            return root;
        }
        n = findKthMax(root.right, k - leftCount - 1, count);
        count[0] += leftCount + 1;
        return n;
    }

    public TreeNode createTree() {
        int[] inorder = {1, 2, 3, 4, 5, 8, 9, 10, 11};
        TreeNode root = createNode(inorder, 0, inorder.length - 1);

        return root;
    }

    TreeNode createNode(int[] in, int l, int r) {
        if (l > r)
            return null;
        int mid = (l + r) / 2;
        TreeNode n = new TreeNode(in[mid]);
        n.left = createNode(in, l, mid - 1);
        n.right = createNode(in, mid + 1, r);

        return n;
    }
}