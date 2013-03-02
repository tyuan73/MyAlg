/*
ID: tyuan731
LANG: JAVA
TASK: beads
*/
import java.io.*;
import java.util.*;

class beads {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("beads.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
	
	String b  = f.readLine();
	b = f.readLine();
	int n = b.length();
	int[] nk = new int[2*n];
	for(int i = 0; i < n; i++) {
		switch(b.charAt(i)) {
			case 'r' : nk[i] = 1;nk[n+i] = 1; break;
			case 'b' : nk[i] = -1;nk[n+i] = -1; break;
		}
	}
	
	int max = 1;
	for(int i = 1; i < 2*n; i++) {
		int count1 = 0;
		int j = i-1;
		while(j >= 0 && nk[j]>= 0) {count1++;j--;}
		j = i;
		while(j < 2*n && nk[j] <= 0) {count1++; j++;}
		max = Math.max(max, count1);
		count1 = 0;
		j = i-1;
		while(j >= 0 && nk[j] <= 0) {count1++;j--;}
		j= i;
		while(j < 2*n && nk[j]>=0) {count1++; j++;}
		max = Math.max(max, count1);
	}
	max = Math.min(n, max);
	out.println(max+"");
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
}