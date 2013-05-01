/*
ID: tyuan731
LANG: JAVA
TASK: subset
*/
import java.io.*;
import java.util.*;

class subset {
	public static void main (String [] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("subset.in"));
													  // input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));

		int n = Integer.parseInt(f.readLine());
		int sum = n*(n+1)/2;
		if(sum % 2 != 0) {
			out.println(0);
			out.close();
			
			return;
		}
		
		sum /= 2;
		long[] dp = new long[sum+1];
		dp[0] = 1;
		for(int i = 1; i <= n; i++) {
			for(int j = sum; j >= i; j--) {
				dp[j] += dp[j-i];
			}
		}

		out.println(dp[sum]/2);

		out.close();                                  // close the output file
		System.exit(0);                               // don't omit this!
	}
}