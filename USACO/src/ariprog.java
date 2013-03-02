/*
ID: tyuan731
LANG: JAVA
TASK: ariprog
*/
import java.io.*;
import java.util.*;

class ariprog {  
	public static void main (String [] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
													  // input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));

		//String in = f.readLine();
		int n = Integer.parseInt(f.readLine());
		int m = Integer.parseInt(f.readLine());

		int max = m*m*2;
		boolean[] valid = new boolean[max + 1];
		for(int i = 0; i <= m; i++) {
			for(int j = 0; j <= m; j++)
				valid[i*i + j*j] = true;
		}

		ArrayList<Integer> seq = new ArrayList<Integer>();
		ArrayList<Integer> diff = new ArrayList<Integer>();

		for(int j = 1; j < max; j++) {
			for(int i = 0; i <= max - n*j+j; i++) {
				if(!valid[i]) continue;
				boolean ok = true;
				for(int k = 1; k < n; k++) {
					if(i + k*j > max || !valid[i+k*j]) {
						ok = false;
						break;
					}
				}
				if(ok) {
					seq.add(i);
					diff.add(j);
				}
			}
		}

		if(seq.size() == 0)
			out.println("NONE");
		else 
			for(int i = 0; i < seq.size(); i++)
				out.println(seq.get(i) + " " + diff.get(i));

		out.close();                                  // close the output file
		System.exit(0);                               // don't omit this!
	}
  
}