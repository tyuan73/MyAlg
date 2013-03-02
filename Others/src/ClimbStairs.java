class ClimbStairs {
	public static void main(String[] args) {
		int N = 4;
		
		int total = 1;
		int p = 1;
		int c = N;
		for(int i = 0; i <= N/2; i++) {
			p = p * c * (c - 1) /(N - i) /(i + 1);
			c -= 2;
			//System.out.println("p = "  + p);
			total += p;
		}
		
		System.out.println("total = " + total);
		
		int total1 = 0;
		for(int i =0; i<= N/2; i++)
			total1 += f(N - i, i);
		
		System.out.println("correct answer is " + total1);
	}
	
	static int f(int n, int k) {
		int ret = 1;
		for(int i = n; i > n - k; i--)
			ret *= i;
		for(int i = 1; i <= k; i++)
			ret /= i;
		return ret;
	}
}