class FindLongestSubSeq {
	public static void main(String[] args) {
		//int a[] = { 1, 9, 3, 8, 11, 4, 5, 6, 4, 19, 7, 1, 7 };
		int a[] = {2, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
		//int[] a = {0,8,4,9};
		FindLongestSubSeq fl = new FindLongestSubSeq();
		fl.find(a);
	}
	
	public void find(int[] a) {
		int n = a.length;
		int[] seq = new int[n];
		int count = 0;
		int[] bac = new int[n];
		bac[0] = -1;
		
		for(int i = 1; i < n; i++) {
			int x = a[i];
			if(x >= a[seq[count]]) {
				bac[i] = seq[count];
				seq[++count] = i;
				continue;
			}
			
			// x is less than the current largest number in seq
			int l = 0, r = count;
			while(l < r) {
				int mid = (l + r + 1) / 2;
				if(a[seq[mid]] > x)
					r = mid - 1;
				else
					l = mid;
			}
			// when previous while loop terminates, it maintains:
			// 1. l = r
			// 2. a[seq[l]] either equals to x, or less than x
			// 3. the only exception to 2. is that l = r = 0, 
			//     it is possible that x < a[l] = a[0]
			if(x < a[seq[l]]) {
			// this is only possible when l = r =0
				bac[i] = -1;
				seq[l] = i;
			} else {
				bac[i] = seq[l];
				seq[l + 1] = i;
			}
		}
		
		for(int i : seq)
			System.out.printf("%4d", a[i]);
		System.out.println("");
		for(int i : bac)
			System.out.printf("%4d", i);
					System.out.println("");

		for(int i = seq[count]; i >= 0; i = bac[i]) {
			System.out.printf("%4d", a[i]);
		}
	}
}