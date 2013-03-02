class DeleteTargetInLinkedList {
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int v) {this.val = v; this.next = null;}
	}
	
	public static void main(String[] args) {
		ListNode root = new ListNode(0);
		
		ListNode cur = root;
		for(int i = 0; i < 4; i++) {
			ListNode temp = new ListNode(i);
			cur.next = temp;
			cur = temp;
		}

		printListNode(root);
		root = reverse1(root);
		printListNode(root);
		
		root = null;
		printListNode(root);
		root = reverse1(root);
		printListNode(root);
		/*
		printListNode(root);
		root = delete(root, 3);
		printListNode(root);
		*/
	}
	
	public static ListNode reverse1(ListNode root) {
		if(root == null)
			return null;
		ListNode n = reverse1(root.next);
		if(n == null)
			return root;
		root.next.next = root;
		root.next = null;
		return n;
	}
	
	public static ListNode reverse(ListNode root) {		
		ListNode n1 = null;
		while(root != null) {
			ListNode n3 = root.next;
			root.next = n1;
			n1 = root;
			root = n3;
		}
		return n1;
	}
	
	public static ListNode delete(ListNode root, int target) {
		if(root == null)
			return null;
		if(root.val == target)
			return delete(root.next, target);
		root.next = delete(root.next, target);
		return root;
	}
	
	public static void printListNode(ListNode root) {
		if(root == null)
			System.out.println("the list is empty");
		while(root!= null) {
			System.out.print(root.val + "->");
			root = root.next;
		}
		System.out.println();
	}
}