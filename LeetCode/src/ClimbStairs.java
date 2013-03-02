public class ClimbStairs {
    public int climbStairs(int n) {
        if(n < 2)
            return 1;
        int a = 1, b = 1;
        while(n-- >= 2) {
            int x = a + b;
            a = b;
            b = x;
        }
        
        return b;
    }
	
	// why above solution is right?
	// look at the DP solution below
	// and you will find that it's just the sum of dp[i-1] + dp[i-2]
	// which is fibonacci sequence.
	public int climbStairsDP(int n) {
        if(n < 2)
            return 1;
        int[] steps = new int[n+1];
        steps[0] = 1; steps[1] = 1;
        
        for(int i = 2; i <= n; i++)
            steps[i] = steps[i-1]+steps[i-2];
        
        return steps[n];
    }
}