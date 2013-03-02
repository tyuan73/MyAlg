
// There are total N stairs. Each time you can go up to s steps. that is 1,2,3...s.
// How many different ways you can go to get the top.
class ClimbingStairs {
	public static void main(String[] args) {
		int s = 3;
		int N = 5;
		
		int[] dp = new int[N+1];
		dp[0] = 1;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= Math.min(i, s); j++) {
				dp[i] += dp[i-j];
			}
		}
		
		System.out.println(dp[N]);
	}
	
	/*
	public static void {
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
	*/
}