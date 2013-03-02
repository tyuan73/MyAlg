class FindSubarraySum {
	public static void main(String[] args) {
		int[] data = {1,2,3,4,5};
		
		int n = data.length;
		for(int i = 0; i < (1 << n); i++) {
			int index = 0;
			int cc = i;
			while(cc > 0) {
				if((cc & 1) > 0) {
					System.out.print(" " + data[index]);
				}
				cc >>= 1;
				index++;
			}
			System.out.println("");
		}
		
		/*
		int sum = 29;
		
		int start = 0;
		int curSum = 0;
		if(sum <= 0) {
			System.out.println("can not find");
			return;
		}
		for(int i = 0; i < data.length; i++) {
			curSum += data[i];
			if(curSum == sum) {
				System.out.println(" start = " + start + ", end = " + i);
				return ;
			}
			if(curSum > sum) {
				while(curSum > sum) curSum -= data[start++];
				if(curSum == sum) {
					System.out.println(" start = " + start + ", end = " + i);
					return ;
				}
			}
		}
		System.out.println("can not find");
		*/
	}
}