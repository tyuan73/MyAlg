/*
ID: tyuan731
LANG: JAVA
TASK: holstein
*/
import java.io.*;
import java.util.*;

class holstein {
	static int[] vit;
	static int[][] scoop;
	static int min = 0;
	static ArrayList<Integer> ret;
	
	public static void main (String [] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("holstein.in"));
													  // input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));

		int v = Integer.parseInt(f.readLine());
		StringTokenizer st = new StringTokenizer(f.readLine());
		vit = new int[v];
		for(int i = 0; i < v; i++) {
			vit[i] = Integer.parseInt(st.nextToken());
		}
		
		int g = Integer.parseInt(f.readLine());
		scoop = new int[g][v];
		for(int i = 0; i < g; i++) {
			st = new StringTokenizer(f.readLine());
			for(int j = 0; j < v; j++) {
				scoop[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		min = g+1;
		
		for(int i = 0; i < g; i++) {
			search(new int[v], i, new ArrayList<Integer>());
		}
		out.print(min);
		for(int i = 0; i < ret.size(); i++) {
			out.print(" " + (ret.get(i)+1));
		}
		out.println();

		out.close();                                  // close the output file
		System.exit(0);                               // don't omit this!
	}
  
	static void search(int[] total, int index, ArrayList<Integer> path) {
		if(valid(total, vit)) {
			if(path.size() < min) {
				min = path.size();
				ret = new ArrayList<Integer>();
				for(int x : path)
					ret.add(x);
			}
			return;
		}
		
		for(int i = index; i < scoop.length; i++) {
			for(int j = 0; j < vit.length; j++) {
				total[j] += scoop[i][j];
			}
			path.add(i);
			search(total, i+1, path);
			path.remove(path.size()-1);
			for(int j = 0; j < vit.length; j++)
				total[j] -= scoop[i][j];
		}
	}
	
	static boolean valid(int[] total, int[] vit) {
		for(int i = 0; i < vit.length; i++)
			if(total[i] < vit[i])
				return false;
		return true;
	}
}