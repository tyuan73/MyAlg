/*
ID: tyuan731
LANG: JAVA
TASK: clocks
*/
import java.io.*;
import java.util.*;
/**
------- test 1 ----
9 9 12
6 6 6
6 3 6
------- test 2 ----
12 9 12
9 9 9
12 9 12
------- test 3 ----
6 9 3
3 3 9
12 12 12
------- test 4 ----
9 3 9
9 9 9
9 9 9
------- test 5 ----
6 12 12
12 12 12
12 12 12
------- test 6 ----
3 12 9
6 6 6
12 12 12
------- test 7 ----
12 3 3
3 6 6
12 3 6
------- test 8 ----
12 3 9
9 12 12
3 6 9
------- test 9 ----
9 12 9
12 3 12
9 12 9
**/
class clocks {
  static final int[][] map = //new int[9][9];
  {//A B C  D  E  F  G  H  I
	{1,1,0, 1, 1, 0, 0, 0, 0},	//ABDE
	{1,1,1, 0, 0, 0, 0, 0, 0},	//ABC
	{0,1,1, 0, 1, 1, 0, 0, 0},	//BCEF
	{1,0,0, 1, 0, 0, 1, 0, 0},	//ADG
	{0,1,0, 1, 1, 1, 0, 1, 0},	//BDEFH
	{0,0,1, 0, 0, 1, 0, 0, 1},	//CFI
	{0,0,0, 1, 1, 0, 1, 1, 0},	//DEGH
	{0,0,0, 0, 0, 0, 1, 1, 1},	//GHI
	{0,0,0, 0, 1, 1, 0, 1, 1}	//EFHI
  };
  
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("clocks.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("clocks.out")));
	
	String in = f.readLine() + " " + f.readLine() + " " + f.readLine();
	StringTokenizer st = new StringTokenizer(in);
	
	int[] clocks = new int[9];
	for(int i = 0; i < 9; i++){
		int c = Integer.parseInt(st.nextToken());
		clocks[i] = c/3;
	}
	
	ArrayList<Integer> path = new ArrayList<Integer>();
	ArrayList<Integer> ret = new ArrayList<Integer>();
	dfs(clocks, 0, path, ret);
	
	StringBuilder sb = new StringBuilder();
	for(int x : ret)
		sb.append(x+1).append(' ');
	sb.setLength(sb.length()-1);
	out.println(sb.toString());
	
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
  
  static void dfs(int[] clocks, int index, ArrayList<Integer> path, ArrayList<Integer> ret) {	
	if(path.size() == 27 || index == 9 || (ret.size() > 0 && path.size() >= ret.size()))
	//if(path.size() == 27 || index == 9)
		return;
	
	for(int i = 3; i >= 0; i--) {
		add(clocks, map[index], i);
		for(int j = 0; j < i; j++)
			path.add(index);
		if(valid(clocks)) {				  
			if(ret.size() == 0 || path.size() < ret.size()) {
				ret.clear();
				for(int x : path)
					ret.add(x);
			}
			sub(clocks, map[index], i);
			for(int k = 0; k < i; k++)
				path.remove(path.size()-1);
			return;
		}
		dfs(clocks, index+1, path, ret);
		sub(clocks, map[index], i);
		for(int k = 0; k < i; k++)
			path.remove(path.size()-1);
	}
	
  }
  
  static void add(int[] clocks, int[] map, int count) {
	for(int i = 0; i < 9; i++)
		clocks[i] += map[i]*count;
  }
  
  static void sub(int[] clocks, int[] map, int count) {
	for(int i = 0; i < 9; i++) 
		clocks[i] -= map[i]*count;
  }
  
  static boolean valid(int[] clocks) {
	for(int c : clocks) {
		if((c % 4) != 0)
			return false;
	}
	return true;
  }
}