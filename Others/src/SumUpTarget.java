class SumUpTarget {
	public static void main(String[] args) {
		//int[] S = {1,2,4,5,6,7};
		//int target = 9;
		//int[] S = {1,3, 5};
		//int target = 6;
		int[] S = {1,2,3};
		int target = 5;
		
		int[] dp = new int[target+1];
		dp[0] = 1;
		for(int i = 0; i < S.length; i++) {
			for(int j = S[i]; j <= target; j++) {
				dp[j] += dp[j-S[i]];
			}
		}
		System.out.println(dp[target]);
	}
}