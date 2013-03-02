
class LongestIncreasingSeqDP {
	public static void main(String[] args) {
		int a[] = {2, 8, 4,12,2,10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
		
		int n = a.length;
		int max = 1, last = 0;
		int[] dp = new int[n];
		int[] backtrack = new int[n];
		dp[0] = 1;
		backtrack[0] = -1;
		for(int i = 1; i < n; i++) {
			for(int j = 0; j < i; j++) {
				if(a[i] > a[j] && dp[j]+1 > dp[i]) {
					dp[i] = dp[j]+1;
					backtrack[i] = j;
				}
				if(dp[i] > max) {
					max = dp[i];
					last = i;
				}
			}
		}
		System.out.println("max length is :" + max);
		while(last >= 0) {
			System.out.printf("%5d", a[last]);
			last = backtrack[last];
		}
		System.out.println();
	}
}