import java.util.*;

class LargestSumLessThanM {
	static public void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int m = in.nextInt();
		
		int[] d = new int[n];
		for(int i = 0; i < n; i++)
			d[i] = in.nextInt();
		
		Arrays.sort(d);
		int sum = 0;
		for(int i = 0; i < k; i++)
			sum += d[i];

		for(int j = k -1; j >= 0; j--) {
			boolean ok = false;
			for(int i = k; i < n; i++) {
				int diff = d[i] - d[j];
				if(sum + diff >= m) {
					ok = true;
					break;
				}
				int x = d[i];
				d[i] = d[j];
				d[j] = x;
				sum += diff;
			}
			if(ok)
				break;
		}
		System.out.println(sum);
		
		
		//http://en.wikipedia.org/wiki/Partition_%28number_theory%29
		//pre = 3000;
		long[][] dp = new long[sum+1][sum+1];
		dp[1][1] = 1;
		for(int i = 2; i <= sum; i++) {
			for(int j = i; j >= 1; j--) {
				if(j == i)
					dp[i][j] = 1;
				else
					dp[i][j] = (dp[i][j+1] + dp[i-j][j]) % 1000000007;
			}
		}
		System.out.println(dp[sum][1]);
	}
}