/*
ID: tyuan731
LANG: JAVA
TASK: milk2
*/
import java.io.*;
import java.util.*;

class milk2 {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
 
 	int n = Integer.parseInt(f.readLine());
 	int[][] interval = new int[n][2];
 	for(int i = 0; i < n; i++) {
 		String s = f.readLine();
 		String[] s1 = s.split(" ");
 		interval[i][0] = Integer.parseInt(s1[0]);
 		interval[i][1] = Integer.parseInt(s1[1]);
 	}
 	
 	sort(interval);
 	
 	int max1 = 0;
 	int max2 = 0;
 	int start = interval[0][0], end = interval[0][1];
 	for(int i = 1; i < n; i++) {
 		if(end < interval[i][0]) {
 			max1 = Math.max(max1, end - start);
 			max2 = Math.max(max2, interval[i][0] - end);
 			start = interval[i][0];
 			end = interval[i][1];
 		} else {
 			end = Math.max(end, interval[i][1]);
 		}
 	}

 	max1 = Math.max(max1, end-start);
 	out.println(max1 + " " + max2);
 	
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
  
  static void sort(int[][] in) {
  	for(int i = 1; i < in.length; i++) {
  		int x = in[i][0], y = in[i][1];
  		int j = i-1;
  		while(j>= 0 && in[j][0] > x) {
  			in[j+1][0] = in[j][0];
  			in[j+1][1] = in[j][1];
  			j--;
  		}
  		in[j+1][0]= x; in[j+1][1] = y;
  	}
  }
}