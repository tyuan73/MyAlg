import java.util.*;

class CoinTable {
	static int n = 0;
	static int m = 0;
	static int k = 0;
	static int min = 10000;
	static String[] board;
	static int[][] mod;
	
	static public void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		k = in.nextInt();
		board = new String[n];
		for(int i = 0; i < n; i++)
			board[i] = in.next();

		min = Integer.MAX_VALUE;
		mod = new int[n][m];
		for(int[] c: mod)
			Arrays.fill(c, Integer.MAX_VALUE);
		//mod[0][0] = 0;
		dfs(0, 0, 0, 0);
		if(min == Integer.MAX_VALUE)
			min = -1;
		System.out.println(min);
	}
	
	static void dfs(int row, int col, int step, int changes) {
		if(row < 0 || row >= n || col < 0 || col >= m || step > k || changes > min || changes >= mod[row][col])
			return;
		
		mod[row][col] = changes;
		char dir = board[row].charAt(col);	
		if(dir == '*') {
			min = Math.min(min, changes);
			return;
		}

		if(dir == 'R')
			dfs(row, col+1, step+1, changes);
		else
			dfs(row, col+1, step+1, changes+1);
		if(dir == 'U')
			dfs(row-1, col, step+1, changes);
		else
			dfs(row-1, col, step+1, changes+1);
		if(dir == 'D')
			dfs(row+1, col, step+1, changes);
		else
			dfs(row+1, col, step+1, changes+1);
		if(dir == 'L')
			dfs(row, col-1, step+1, changes);
		else
			dfs(row, col-1, step+1, changes+1);
	}
	
	static void mark(String[] board, int[][] steps, int row, int col, int step, char dir) {
		if(row < 0 || row >= n || col < 0 || col >= m || board[row].charAt(col) != dir)
			return;
		
		if(steps[row][col] == 0 || steps[row][col] > step) {
			steps[row][col] = step;
			mark(board, steps, row+1, col, step+1, 'U');
			mark(board, steps, row-1, col, step+1, 'D');
			mark(board, steps, row, col+1, step+1, 'L');
			mark(board, steps, row+1, col, step-1, 'R');
		}
	}
}