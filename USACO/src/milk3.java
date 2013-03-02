/*
ID: tyuan731
LANG: JAVA
TASK: milk3
*/
import java.io.*;
import java.util.*;

class milk3 {  
	static int total = 0;
	static int count = 0;
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("milk3.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
	
	String in = f.readLine();
	StringTokenizer st = new StringTokenizer(in);
	
	int[] cap = new int[3];
	int[] vol = new int[3];
	for(int i = 0; i < 3; i++) {
		cap[i] = Integer.parseInt(st.nextToken());
	}
	vol[2] = cap[2];
	total = cap[2];
	boolean[][] dp = new boolean[21][21];
	dp[0][total] = true;
		
	process(dp, cap, vol);
	
	StringBuilder sb = new StringBuilder();
	for(int i = total; i >= 0; i--) {
		if(dp[i][total-i])
			sb.append(total-i).append(' ');
	}
	sb.setLength(sb.length()-1);
	System.out.println(sb.toString());
	System.out.println(count);
	
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
  
  static void process(boolean[][] dp, int[] cap, int[] vol) {
	count++;
	for(int i = 0; i < 3; i++) {
		for(int j = 0; j < 3; j++) {
			if(i == j) continue;
			int delta = 0;
			if(vol[i] <= cap[j] - vol[j])
				delta = vol[i];
			else
				delta = cap[j] - vol[j];
			vol[i] -= delta;
			vol[j] += delta;
			if(!dp[vol[1]][vol[2]]) {
				dp[vol[1]][vol[2]] = true;
				process(dp, cap, vol);
			}
			vol[i] += delta;
			vol[j] -= delta;
		}
	}
  }
}