import java.util.*;

class LuckyNumbers {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		boolean[] primes = new boolean[2000];
		for(int i = 0; i < 2000; i++)
			if(isPrime(i))
				primes[i] = true;
			
		for(int i = 0; i < 2000; i++) {
			if(!primes[i]) continue;
			process(i, primes, i, 9, new StringBuilder());
		}
		/*
		int total = 0;
		for(int i = 11; i < n; i++) {
			int sum = calSum(i);
			if(primes[sum]) {
				sum = calSumSqr(i);
				if(primes[sum]) {
					total++;
					//System.out.println(i);
				}
			}
		}
		System.out.println(total);
		*/
	}
	
	static void process(int x, boolean[] primes, int rem, int base, StringBuilder sb) {
		if(base == 1) {
			StringBuilder sb1 = new StringBuilder();
			while(rem-- > 0)
				sb1.append(1);
			//System.out.println(x + " = " + sb.toString() + sb1.toString());
			return;
		}
		
		int max = rem /(base*base);
		int sum = 0;
		for(int i = 1; i <= max; i++) {
			sb.append(base);
			sum += base*base;
		}
		for(int i = 1; i <= max; i++) {
			process(x, primes, rem - sum, base-1, sb);
			sb.setLength(sb.length()-1);
			sum -= base* base;
		}
		process(x, primes, rem, base-1, sb);
	}
	
	static int calSum(int x) {
		int ret = 0;
		while(x > 0) {
			ret += (x%10);
			x /= 10;
		}
		return ret;
	}
	
	static int calSumSqr(int x) {
		int ret = 0;
		while(x > 0)  {
			int y = x % 10;
			ret += y*y;
			x /= 10;
		}
		return ret;
	}
	
	static boolean isPrime(int x) {
		if(x < 2) return false;
		int max = (int) Math.sqrt(x);
		for(int i = 2; i <= max; i++) {
			if((x % i) == 0)
				return false;
		}
		//System.out.println(x);
		return true;
	}
}