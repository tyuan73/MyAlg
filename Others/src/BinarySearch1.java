class BinarySearch1 {
/*****
Given a sorted integer array and a specific integer, find the
first position of that integer. Array contains lots of
duplicate.
******/
	public static void main(String[] args) {
		//         0 1 2 3 4 5 6 7  8 9 10 11
		int[] A = {1,2,2,2,2,3,3,4, 5,5, 8, 9};
		int x = 2;
		
		// l and r are initialized to be 0, and A.length -1
		// to make sure value of l and r are between 0 and A.length -1
		// so that "if(A[l] != x)" won't fail.
		// after while loop, l must have the same value as r.
		int l = -1, r = A.length - 1;
		while(l < r) {
			int mid = (l + r + 1) / 2;
			if(A[mid] > x)
				r = mid - 1;
			else
				l = mid;
		}
		System.out.println("l = " + l);
		if(A[l] != x)
			System.out.println("can not find!");
		else 
			System.out.println("first position is " + l);
	}
}