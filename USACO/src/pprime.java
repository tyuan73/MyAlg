/*
ID: tyuan731
LANG: JAVA
TASK: pprime
*/
import java.io.*;
import java.util.*;

class pprime {
	static int[] smallNum = {5,7,11};
	static boolean[] validNum = {false, true, false, true, false, false, false, true, false, true};
	public static void main (String [] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("pprime.in"));
													  // input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));

		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		for(int x : smallNum)
			if(x >= a && x <= b)
				out.println(x);
		
		if(b > 100) {
			if(a <= 100) a = 101;
			int base = 100, count = 3;
			while(base*10 <= a) { base *= 10; count++;}
			if((count % 2) == 0) base *= 10;
			while((a%10) != a/base) {
				a++;
				if(a >= base * 10)
					base *= 10;
			}
			while(a <= b) {
				int first = a / base;
				if(!validNum[first]) {
					a = (first+1)*base + first + 1;
				} else {
					if(isPalindrome(a) && isPrime(a))
						out.println(a);
					//a++;
					a += 10;
					if(a >= base*10){
						base *= 100;
						a = base + 1;
					} else if(a >= (first + 1) * base)
						a = (first+1) * base + first + 1;
				}
			}
			
			
		}

		out.close();                                  // close the output file
		System.exit(0);                               // don't omit this!
	}
	
	static boolean isPrime(int a) {
		int max = (int)Math.sqrt(a);
		for(int i = 2; i <= max; i++)
			if((a % i) == 0)
				return false;
		return true;
	}
	
	static boolean isPalindrome(int a) {
		if(!validNum[a%10])
			return false;
		int b = 0;
		int orig = a;
		while(a > 0) {
			b = b*10 + (a%10);
			a /= 10;
		}
		return orig == b;
	}
}