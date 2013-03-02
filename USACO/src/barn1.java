/*
ID: tyuan731
LANG: JAVA
TASK: barn1
*/
import java.io.*;
import java.util.*;

class barn1 {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
	
	StringTokenizer st = new StringTokenizer(f.readLine());
	int M = Integer.parseInt(st.nextToken());
	int S = Integer.parseInt(st.nextToken());
	int C = Integer.parseInt(st.nextToken());
	int[] stalls = new int[C];
	for(int i = 0; i < C; i++) {
		stalls[i] = Integer.parseInt(f.readLine());
	}
	
	Arrays.sort(stalls);
	
	int[] map = new int[C];
	Arrays.fill(map, S+1);
	int index = 0;
	for(int i = 1; i < C; i++) {
		int x = stalls[i] - stalls[i-1] - 1;
		if(x > 0)
			map[index++] = x;
	}
	Arrays.sort(map);
	int i = 0;
	int ret = C;
	while(index-- >= M) {
		ret += map[i++];
	}
	
	out.println(ret);

    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
}