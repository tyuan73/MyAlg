class LongestCommonSubstr {
	public static void main(String[] args) {
	
		String s = "aababa";
		
		int n = s.length();
		int[][] dp = new int[n + 1][n + 1];
		int max = 0, index = -1;
		for(int i = 0; i < n; i++) {
			for(int j = i+1; j < n; j++) {
				if(s.charAt(i) == s.charAt(j)) {
					dp[i+1][j+1] = dp[i][j] + 1;
					if(dp[i+1][j+1] > max) {
						max = dp[i+1][j+1];
						index = i;
					}
				}
			}
		}
		
		for(int i = 0; i <= n; i++) {
			for(int j = 0; j <= n; j++)
				System.out.printf("%6d", dp[i][j]);
			System.out.println("");
		}
		
		if(max > 0)
			System.out.println(s.substring(index - max + 1, index+1));
		System.out.println("");
	}
}