import java.util.*;

class SliceString {
	//http://www.mitbbs.com/mitbbs_article_t.php?board=JobHunting&gid=32308035
	public static void main(String[] args) {
		String s = "abcdef";
		HashSet<String> dict = new HashSet<String>();
		dict.add("abcde");
		dict.add("f");
		dict.add("ab");
		dict.add("cd");
		dict.add("ef");
		
		int n = s.length();
		int[] dp = new int[n+1];
		int[] backtrack = new int[n+1];
		Arrays.fill(dp, 1000000);
		dp[n] = 0;
		for(int i = n-1; i >= 0; i--) {
			for(int j = i+1; j <= n; j++) {
				if(dict.contains(s.substring(i,j))) {
					if(dp[j]+1 < dp[i]) {
						dp[i] = dp[j] + 1;
						backtrack[i]=j;
					}
				}
			}
		}

		if(dp[0] > n) {
			System.out.println("Invalid");
			return;
		}
		int next = 0;
		StringBuffer sb = new StringBuffer();
		do {
			sb.append(s.substring(next, backtrack[next])).append(' ');
			next = backtrack[next];
		} while(backtrack[next] != 0);
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		/*
		int n = s.length();
		int[] dp = new int[n+1];
		int[] backtrack = new int[n+1];
		Arrays.fill(dp, 10000000);
		dp[0] = 0;
		for(int i = 1; i <= n; i++)
			for(int j = i-1; j>= 0; j--) {
				if(dict.contains(s.substring(j, i))) {
					if(dp[j]+1 < dp[i]) {
						dp[i] = dp[j]+1;
						backtrack[i] = j;
					}
				}
			}
		
		if(dp[n] > n) {
			System.out.println("invalid");
				return;
		}
		
		System.out.println("" + dp[n]);
		String out = new String();
		int i = backtrack[n];
		int j = n;
		do {
			out = s.substring(i, j) + " " + out;
			j = i;
			i = backtrack[i];
		} while(j != 0);
		System.out.println(out.substring(0, out.length()-1));
		*/
	}
}