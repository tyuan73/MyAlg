class PermutateString {
	public static void main(String[] args) {
		int n = 3;
		
		int[] arr = new int[n];
		for(int i = 0; i < n; i++)
			arr[i] = i+1;
			
		PermutateString.perm(arr, 0);
	}
	
	static void perm(int[] arr, int start) {
		if(start == arr.length-1) {
			printArray(arr);
			return ;
		}
		
		for(int i = start; i < arr.length; i++) {
			//perm(arr, start+1);
		//	for(int j = i+1; j < arr.length; j++) {
				swap(arr, start, i);
				perm(arr, start+1);
				swap(arr, start, i);
			//}
		}
	}
	
	static void swap(int[] arr, int i, int j) {
		int x = arr[i];
		arr[i] = arr[j];
		arr[j] = x;
	}
	
	static void printArray(int[] arr) {
		StringBuilder sb = new StringBuilder();
		for(int i: arr)
			sb.append(i);
		System.out.println(sb.toString());
	}
}