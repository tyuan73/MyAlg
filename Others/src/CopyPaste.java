class CopyPaste {
	public static void main(String[] args) {
		int n = 187;
		long[][] res = new long[n+1][2];
		long[][] dp = new long[n+1][n+1];
		
		/*****
		if(n <= 4)
			.....
		*****/
		
		for(int i = 0; i <= 7; i++) {
			res[i][0] = i;
			res[i][1] = 0;
		}
		
		for(int i = 8; i <= n; i++) {
		/*
			if(res[i-1][1] > 0) {
				res[i][0] = res[i-1][0]+res[i-1][1];
				res[i][1] = res[i-1][1];
			} else {
			*/
				//res[i][0] = res[i-1][0] + 1;
				//res[i][1] = res[i-1][1];
			/*
			}
			*/
			/*
			if(res[i-4][0] * 2 >= res[i][0]) {
				res[i][0] = res[i-4][0]*2;
				res[i][1] = res[i-4][0];
			}
			*/
			if(i <= 8) {
			res[i][0] = res[i-5][0] * 3;
			}
			else {
				res[i][0] = Math.max(res[i-6][0] * 4,res[i-7][0] * 5);
			}
			/*
			for(int j = 1; j <= i - 4; j++) {
				int base = res[j][0];
				dp[i][j] = base*(i-j-2);
				if(base * (i-j-2) >= res[i][0]) {
					res[i][0] = base *(i-j-2);
					dp[i][0] = base*(i-j-2);
					res[i][1] = base;
				}
			}
			*/
		}
		
		for(int i = 0; i <= n; i++) {
			long x = f(i);
			long y = f1(i);
			System.out.printf("%4d: %5d --> %5d --> %5d", i, res[i][0], x, y);
			if(x != res[i][0]) System.out.print("  Wrong! base is " + res[i][1]);
			System.out.println("");
		}
		
		/*
		for(int i = 0; i <=n;i++) {
			for(int j=0; j<=n;j++) {
				System.out.printf("%20d", dp[i][j]);
			}
			System.out.println("");
		}
		*/
	}
	
	// recursive 
	static long f2(int n) {
		if(n <= 7) return n;
		if(n == 8) return 9;
		return Math.max(f1(n-6)*4, f1(n-7)*5);
	}
	
	static long f1(int n) {
		if(n <= 7) return n;
		if(n == 8) return 9;
		long[] dp = new long[8];
		for(int i = 0; i < 8; i++)
			dp[i] = i;
		dp[0] = 9;
		for(int i = 9; i <= n; i++)
			dp[n%8] = Math.max(dp[(i-7)%8] * 5, dp[(i-6)%8] * 4);
		return dp[n%8];
	}
	
	static long f(int n) {
		  if (n <= 7) return n;
		 
		  int k = 2;    // define k as the steps number,  the default is 2 when n > 7
		  int kmax = (n+2)/4;
		  // n-2(k-1) = ax+(a+1)(k-x) >= ak ==> k<=(n+2)/(a+2)  ==> k<=(n+2)/4
		  int sum = 0;   // sum = n-2(k-1)
		  long mul = 1;  // the total times, e.g. to 3A3D, the total times is
		  int a;   //aAxD
		  int x;   //aAxD
		 
		  long mulMax = 1;
		  //int aM = 0;
		  //int xM = 0;
		  //int kM = 0;
		 
		  while (k <= kmax) {
			  sum = n - 2*(k-1);
			  a = sum/k;
			  x = sum%k;
		 
			  mul = (long)Math.pow(a, k-x) * (long)Math.pow(a+1, x);
		 
			  if(mul > mulMax){
				mulMax = mul;
				//aM = a;
				//xM = x;
				//kM = k;
			  }
		 
			  k++;
		  }
		 
		  //System.out.print("\t\t" + mulMax + "\t" + getSteps(aM, xM, kM));
		  return mulMax;
}
	
	
	/*
	static int findMaxK(int n) {
		int power = 2;
		double max = 0.0;
		int maxK = 0;
		while (n > 0) {
			n -= 2;
			double t = (double)n/power;
			double r = Math.pow(t, (double)power);
			if (r > max) {
				maxK = power;
				max = r;
			}
			power++;
		}
		return maxK;
	}
 
	static int f(int n) {
		if (n <= 7) return n;
		int k = findMaxK(n);
	 
		int sum = n - 2*(k-1);
		int mul = 1;
		while (k > 0) {
			int avg = sum/k;
			mul *= avg;
			k--;
			sum -= avg;
		}
	 
		//assert(sum == 0);
	 
		return mul;
	}
	*/
}