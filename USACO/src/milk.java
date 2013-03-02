/*
ID: tyuan731
LANG: JAVA
TASK: milk
*/
import java.io.*;
import java.util.*;

class milk {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("milk.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
	
	StringTokenizer st = new StringTokenizer(f.readLine());
	int N = Integer.parseInt(st.nextToken());
	int M = Integer.parseInt(st.nextToken());
	int[][] farmer = new int[M][2];
	for(int i = 0; i < M; i++) {
		StringTokenizer s = new StringTokenizer(f.readLine());
		farmer[i][0] = Integer.parseInt(s.nextToken());
		farmer[i][1] = Integer.parseInt(s.nextToken());
	}
	
	sort(farmer);
	
	int index = 0;
	int total = 0;
	while(N > 0) {
		int amt = Math.min(N, farmer[index][1]);
		total += amt * farmer[index][0];
		N -= amt;
		index++;
	}
	out.println(total);

    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
  
  static void sort(int[][] farmer) {
	for(int i = 1; i < farmer.length; i++) {
		int price = farmer[i][0];
		int amt = farmer[i][1];
		int j = i-1;
		while(j >= 0) {
			if(price < farmer[j][0]) {
				farmer[j+1][0] = farmer[j][0];
				farmer[j+1][1] = farmer[j][1];
			} else
				break;
			j--;
		}
		farmer[j+1][0] = price;
		farmer[j+1][1] = amt;
	}
  }
}