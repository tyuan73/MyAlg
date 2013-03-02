class EditDistance {
	public static void main(String[] args) {
		EditDistance ed = new EditDistance();
		System.out.println("edit distance is " + ed.minDistance("a", "a"));
	}
	
    public int minDistance(String word1, String word2) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        
        for(int i = 0; i <= n1; i++) {
            for(int j = 0; j <= n2; j++) {
                if(i == 0) {
                    dp[i][j] = j;
                    continue;
                }
                if(j == 0) {
                    dp[i][j] = i;
                    continue;
                }
                
                int min = dp[i - 1][j] + 1;
                min = Math.min(min, dp[i][j - 1] + 1);
                if(word1.charAt(i - 1) == word2.charAt(j - 1))
                    min = Math.min(min, dp[i - 1][j - 1]);
                else
                    min = Math.min(min, dp[i - 1][j - 1] + 1);
                
                dp[i][j] = min;
            }
        }
        
        return dp[n1][n2];
    }
}