class LocalMininum {
	public static void main(String[] args) {
		//			  0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
		//int[] test = {9, 7, 7, 1, 2, 3, 7, 8, 10, 7, 3, 5, 4, 8, 6, 9};
		//int[] test = {10, 9, 8, 7, 6, 5, 4, 3, 2,1,0};
		int[] test = {2, 1,2,3,4,5,6,7,8,9};
		
		if(test == null || test.length < 3) {
			return;
		}
		
		int l = 0, r = test.length - 1;
		while(l + 1 < r) {// this is very important!!!!
			int mid = (l + r) / 2;
			if(test[mid] <= test[mid - 1] && test[mid] <= test[mid + 1]) {
				System.out.println("a local mininum is " + mid);
				return;
			}
			if(test[mid] > test[mid - 1])
				r = mid;
			else 
				l = mid;
		}
		
		System.out.println("some thing wrong");
	}
}