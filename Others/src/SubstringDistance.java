
class SubstringDistance {
	public static void main(String[] args) {
		//String s1 = "awer e eg weg35 y jtjkghl;89p778i hr svs3gsdvdsaweyw32tgmmv";
		//String s2 = "favgawf4fzfgsdfg5afafafsvfh 5kul;/p[oioigwergerfagfarap78kjyujtr5y56baqwetva";
		String s1 = "xyzef";
		String s2 = "abc";
		SubstringDistance sd = new SubstringDistance();
		System.out.println("the distance between " + s1 + " and " + s2 + " is " + sd.maxDisBruteForce(s1, s2));
		System.out.println("the distance between " + s1 + " and " + s2 + " is " + sd.maxDis(s1, s2));
	}
	int maxDisBruteForce(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();
		int len = n < m ? n : m;
		int max = 0;
		for(int l = 1; l <= len; l++) {
			for(int i = 0; i <= n - l; i++) {
				int total1 = 0;
				for(int k = 0; k < l; k++) {
					total1 += s1.charAt(i + k);
				}
				for(int j = 0; j <= m - l; j++) {
					int total2 = 0;
					for(int k = 0; k < l; k++) {
						total2 += s2.charAt(j + k);
					}
					
					if(total2 - total1 > max)
						max = total2 - total1;
					else if(total1 - total2 > max)
						max = total1 - total2;
				}
			}
		}
		return max;
	}
	
	int maxDis(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();
		if(m > n)
			return maxDis(s2, s1);		

		int max = 0;

		for(int i = -m + 1; i < n; i++) {
			int al = i > 0? i : 0;
			int bl = i > 0? 0 : -i;
			int len = i > 0? m: i + m;
			if(al + m > n)
				len = n - al;

			int curMax = 0, curMin = 0;
			System.out.println("al = " + al + " bl = " + bl);
			for(int k = 0; k < len; k++) {
			//System.out.println("al + k = " + (al + k) + "bl + k = " +(bl + k));
				int diff = s1.charAt(al + k) - s2.charAt(bl + k);
				curMax += diff;
				if(curMax < 0)
					curMax = 0;
				else if(curMax > max)
					max = curMax;
				
				curMin += diff;
				if(curMin > 0)
					curMin  = 0;
				else if(curMin < -max)
					max = -curMin;
			}
		}
		
		return max;
		/*
		int max = 0, min = 0;
		for(int i = n - 1; i >= 0; i--) {
			int curMax = 0;
			int curMin = 0;
			for(int k = 0; k < n - i && k < m; k++){
				int diff = s1.charAt(i + k) - s2.charAt(k);
				curMax += diff; //dp[i + k][k];
				if(curMax < 0)
					curMax = 0;
				else if(curMax > max)
					max = curMax;
				
				curMin += diff;
				if(curMin > 0)
					curMin = 0;
				else if(curMin < min)
					min = curMin;
			}
		}
		
		for(int i = 1; i < m; i++) {
			int curMax = 0;
			int curMin = 0;
			for(int k = 0; k < m - i && k < n; k++) {
				int diff = s1.charAt(k) - s2.charAt(i + k);
				curMax += diff; //dp[k][i + k];
				if(curMax < 0)
					curMax = 0;
				else if(curMax > max)
					max = curMax;
					
				curMin += diff;
				if(curMin > 0)
					curMin = 0;
				else if (curMin < min)
					min = curMin;
			}
		}
		
		return max > -min ? max : -min;
		*/
	}
}