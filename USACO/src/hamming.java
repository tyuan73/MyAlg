/*
ID: tyuan731
LANG: JAVA
TASK: hamming
*/
import java.io.*;
import java.util.*;

class hamming {  
	public static void main (String [] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("hamming.in"));
													  // input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));

		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int[] res = new int[n];
		int count = 1;
		for(int i = 0; i < (1<<b); i++) {
			boolean flag = true;
			for(int j = 0; j < count; j++) {
				if(!valid(res[j], i, d)) {
					flag = false;
					break;
				}
			}
			if(flag)
				res[count++] = i;
			if(count >= n)
				break;
		}
		for(int i = 0; i < n; i++) {
			if(i % 10 == 0)
				out.print(res[i]);
			else 
				out.print(" " + res[i]);
			if((i+1) %10 == 0)
				out.println();
		}
		if((n % 10) != 0)
			out.println();

		out.close();                                  // close the output file
		System.exit(0);                               // don't omit this!
	}
  
	static boolean valid(int x, int y, int d) {
		int count = 0;
		x = x^y;
		while(x > 0) {
			count++;
			x = (x-1) & x;
		}
		return count >= d;
	}
}