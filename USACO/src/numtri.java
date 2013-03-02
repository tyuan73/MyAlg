/*
ID: tyuan731
LANG: JAVA
TASK: numtri
*/
import java.io.*;
import java.util.*;

class numtri {  
	public static void main (String [] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("numtri.in"));
													  // input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));

		int n = Integer.parseInt(f.readLine());
		int[][] input = new int[n][n+1];
		for(int i = 0; i < n; i++) {
			int col = n-1;
			StringTokenizer st = new StringTokenizer(f.readLine());
			while(st.hasMoreTokens())
				input[i][col--] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i < n; i++) {
			for(int j = n-1; j >= n-i-1; j--) {
				input[i][j] = Math.max(input[i-1][j], input[i-1][j+1]) + input[i][j];
			}
		}
		
		int max = 0;
		for(int i = 0; i < n; i++) 
			max = Math.max(max, input[n-1][i]);
		
		out.println(max);

		out.close();                                  // close the output file
		System.exit(0);                               // don't omit this!
	}
  
}