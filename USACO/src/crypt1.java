/*
ID: tyuan731
LANG: JAVA
TASK: crypt1
*/
import java.io.*;
import java.util.*;

class crypt1 {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
	
	int n = Integer.parseInt(f.readLine());
	StringTokenizer st = new StringTokenizer(f.readLine());
	
	boolean[] map = new boolean[10];
	int[] numbers = new int[n];
	for(int i = 0; i < n; i++) {
		numbers[i] = Integer.parseInt(st.nextToken());
		map[numbers[i]] = true;
	}
	int ret = 0;
	for(int i = 0; i < n; i++) 
		for(int j = 0; j < n; j++) 
			for(int k = 0; k < n; k++) {
				int value = numbers[i] * 100 + numbers[j] * 10 + numbers[k];
				int max = 999/ value;
				for(int p = 0; p < n; p++) {
					if(numbers[p] > max) continue;
					int prd1 = numbers[p] * value;
					if(!valid(prd1, map)) continue;
					for(int q = 0; q < n; q++) {
						if(numbers[q] > max) continue;
						int prd2 = numbers[q] * value;
						if(!valid(prd2, map)) continue;
						int result = prd2*10 + prd1;
						if(valid(result, map))
							ret++;
					}
				}
			}

	out.println(ret);

    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
  
  static boolean valid(int x, boolean[] map) {
	while(x > 0) {
		if(!map[x%10])
			return false;
		x /= 10;
	}
	return true;
  }
}