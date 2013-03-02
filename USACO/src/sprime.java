/*
ID: tyuan731
LANG: JAVA
TASK: sprime
*/
import java.io.*;
import java.util.*;

class sprime {  
	public static void main (String [] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
													  // input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));

		int n = Integer.parseInt(f.readLine());
		//boolean[] pri = {false, false, true, true, false, true, false, true, false, false};
		int[] smallPrime = {2,3,5,7};
		if(n == 1) {
			for(int x : smallPrime)
				out.println(x);
			return;
		}
		
		int[] prime = {23,29,31,37,53,59,71,73,79};
		int start  = 1;
		while(n-- > 1) start *= 10;
		int end = start * 10 - 1;
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for(int x : prime)
			getSPrimes(x, start, end, ret);
		for(int x : ret)
			out.println(x);

		out.close();                                  // close the output file
		System.exit(0);                               // don't omit this!
	}
	
	static void getSPrimes(int seed, int start, int end, ArrayList<Integer> ret) {
		if(seed >= start && seed <= end) {
			ret.add(seed);
			return;
		}
		
		for(int i = 1; i <= 9; i++) {
			int x = seed * 10 + i;
			if(isPrime(x)) {
				getSPrimes(x, start, end, ret);
			}
		}
	}
	
	static boolean isPrime(int a) {
		int max = (int)Math.sqrt(a);
		for(int i = 2; i <= max; i++)
			if((a % i) == 0)
				return false;
		return true;
	}
}