class ShuffleArray {
	/**
		shuffle an array {a1, a2, a3, .... an, b1, b2, b3, ..... bn}
		to {a1, b1, a2, b2, a3, b3, .... an, bn}
		do it in place and in O(n) complexity.
	**/
	public static void main(String[] args) {
		//int[] test = {3,2,6,4,8,9,101, 88, 46,28,3,2,6,4,8,9,101, 88, 46,28};
		int[] test = {1,2,3,4,5,1,2,3,4,5};
		
		int n = test.length / 2;
		int pre = 1;
		int i = 1; int x = test[i];
		do{
			//int x = test[i];
			//i = 2 * i;
			if(i >= n) {
				i = (i - n) * 2 + 1;
			} else {
				i = 2 * i;
			}
			int y = test[i];
			test[i] = x;
			x = y;
		}while(i != pre);
		
		//test[pre] = x;
		
		for(int j : test) 
			System.out.printf("%5d", j);
		System.out.println("");
	}
}