/*
ID: tyuan731
LANG: JAVA
TASK: castle
*/
import java.io.*;
import java.util.*;

class castle {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("castle.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
	
	//String line = f.readLine();
	StringTokenizer st = new StringTokenizer(f.readLine());
	int M = Integer.parseInt(st.nextToken());
	int N = Integer.parseInt(st.nextToken());
	int[][] C = new int[N][M];
	String line = f.readLine();
	int row = 0;
	while(line != null) {
		st = new StringTokenizer(line);
		for(int i = 0; i < M; i++)
			C[row][i] = Integer.parseInt(st.nextToken());
		row++;
		line = f.readLine();
	}

	int[][] mark = new int[N][M];
	int room = 0;
	for(int i = 0; i < N; i++)
		for(int j = 0; j < M; j++) {
			if(mark[i][j] == 0) {
				mark(C, mark, i,j,++room);
			}
		}
	
	int max = 0;
	int[] R = new int[room+1];
	for(int i = 0; i < N; i++)
		for(int j = 0; j < M; j++) {
			R[mark[i][j]]++;
			max = Math.max(max, R[mark[i][j]]);
		}
		
	int maxCR = 0, maxRow = 0, maxCol = 0;
	char wall = 'N';

	for(int j = 0; j < M; j++) 	
		for(int i = N-1; i >= 0; i--){
			if(i > 0 && mark[i][j] != mark[i-1][j] && R[mark[i][j]] + R[mark[i-1][j]] > maxCR) {
				maxCR = R[mark[i][j]] + R[mark[i-1][j]];
				maxRow = i+1; maxCol = j+1;
				wall = 'N';
			}
			if(j < M-1 && mark[i][j] != mark[i][j+1] && R[mark[i][j]] + R[mark[i][j+1]] > maxCR) {
				maxCR = R[mark[i][j]] + R[mark[i][j+1]];
				maxRow = i+1; maxCol = j+1;
				wall = 'E';
			}
		}

	out.println(room);
	out.println(max);
	out.println(maxCR);
	out.println(maxRow + " " + maxCol + " " + wall);
	
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }

  static void mark(int[][] C, int[][] mark, int i, int j, int roomnum) {
	mark[i][j] = roomnum;
	if((C[i][j] & 1) == 0 && mark[i][j-1] == 0)
		mark(C, mark, i, j-1, roomnum);
	if((C[i][j] & 2) == 0 && mark[i-1][j] == 0)
		mark(C, mark, i-1, j, roomnum);
	if((C[i][j] & 4) == 0 && mark[i][j+1] == 0)
		mark(C, mark, i, j+1, roomnum);
	if((C[i][j] & 8) == 0 && mark[i+1][j] == 0)
		mark(C, mark, i+1, j, roomnum);
  }
}