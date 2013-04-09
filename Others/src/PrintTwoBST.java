class PrintTwoBST {
	public static void main(String[] args) {
		
	}

    /*
	TreeNode merge(TreeNode n1, TreeNode n2) {
		if(n1 == null && n2 == null) {
			return null;
		}
		if(n1 == null || n2 == null) {
			return n1 == null? n2 : n1;
		}
		
		if(n1.val > n2.val) {
			TreeNode right = n2.right;
			n2.right = null;
			n1.left = merge(n1.left, n2);
			return merge(n1, right);
		} else {
			TreeNode left = n2.left;
			n2.left = null;
			n2.right = merge(n1.right, n2);
			return merge(n1, left);
		}
	}
	
	void printBST(TreeNode n1, TreeNode n2) {
		if(n1 == null && n2 == null) 
			return;
		if(n1 == null || n2 == null) {
			n1 = n1 == null ? n2 : n1;
			printBST(n1.left, null);
			print(n1.val);
			printBST(n1.right, null);
			return;
		}
		
		if (n1.val > n2.val) {
			
		}
	}
	*/
}