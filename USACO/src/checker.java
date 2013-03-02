/*
ID: tyuan731
LANG: JAVA
TASK: checker
*/
import java.io.*;
import java.util.*;

class checker {  
	static int total = 0;
	static int count = 0;
	static int[][] ret;
	//static int debug  = 0;
	
	public static void main (String [] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("checker.in"));
													  // input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("checker.out")));

		int n = Integer.parseInt(f.readLine());
		total = 0;
		ret = new int[3][n];
		int[] rows = new int[n];
		boolean[] used = new boolean[n];
		boolean[] c1 = new boolean[n*2];
		boolean[] c2 = new boolean[n*2];
		process(rows, used, 0, n, c1, c2);
		StringBuilder sb = new StringBuilder();
		for(int[] line : ret) {
			for(int x : line) 
				sb.append(" ").append(x);
			out.println(sb.toString().substring(1));
			sb.setLength(0);
		}
		out.println(total);
		//System.out.println("recursion = " + debug);

		out.close();                                  // close the output file
		System.exit(0);                               // don't omit this!
	}
	
	static void process(int[] rows, boolean[] used, int index, int n, boolean[] c1, boolean[] c2) {
	//debug++;
		if(index == n) {
			total++;
			if(count < 3) {
				for(int i = 0; i < n; i++) 
					ret[count][i] = rows[i]+1;
				count++;
			}
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(used[i] || c1[index-i+n] || c2[i+index])
				continue;
			
			rows[index] = i;
			// validate diag using boolean arrays c1 and c2
			//if(valid(rows, index)) {
			used[i] = true;
			c1[index-i+n] = true;
			c2[i+index] = true;
			process(rows, used, index+1, n, c1, c2);
			c1[index-i+n] = false;
			c2[i+index] = false;
			used[i] = false;
			//}
		}
	}
	
	/*
	static boolean valid(int[] rows, int index) {
		for(int i = 0; i < index; i++) {
			if(index - i == Math.abs(rows[index] - rows[i]))
				return false;
		}
		return true;
	}
	*/
}