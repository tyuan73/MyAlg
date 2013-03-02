class PrimeProductOrder {
	public static void main(String[] args) {
		// p1, p2 can be any prime number
		int p1 = 2, p2 = 5; 
		twoPrimes(2,5, 20);
		
		// multiple primes
		int[] primes = {2,5,7, 11};
		multiplePrimes(primes, 40);
	}
	
	static void twoPrimes(int p1, int p2, int N) {
		int[] output = new int[N];
		output[0] = 1;
		int i1 = 0;
		int i2 = 0;
		int a1 = output[0] * p1;
		int a2 = output[0] * p2;
		
		for(int i = 1; i < N; i++) {
			int min = Math.min(a1, a2);
			output[i] = min;
			if(min == a1)
				a1 = p1 * output[++i1];
			if(min == a2) 
				a2 = p2 * output[++i2];
		}
		for(int i : output) 
			System.out.printf("%5d", i);
		
		System.out.println();
	}
	
	static void multiplePrimes(int[] p, int N) {
		int[] output = new int[N];
		output[0] = 1;
		int K = p.length;
		int[] index = new int[K];
		int[] value = new int[K];
		
		for(int i = 0; i < K; i++)
			value[i] = p[i] * output[index[i]];
		
		for(int i = 1; i < N; i++) {
			int min = Integer.MAX_VALUE;
			for(int v : value)
				min = Math.min(min, v);
			output[i] = min;
			for(int j = 0; j < K; j++) {
				if(value[j] == min)
					value[j] = p[j] * output[++index[j]];
			}
		}
		
		for(int i : output)
			System.out.printf("%6d", i);
		System.out.println();
	}
}