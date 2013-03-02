//Betsy's Tour [USACO 1995 Qualifying Round]

/**
A square township has been partitioned into n 2 square plots. 
The Farm is located in the upper left plot and the Market is 
located in the lower left plot. Betsy takes a tour of the 
township going from Farm to Market by walking through every 
plot exactly once. Write a program that will count how many 
unique tours Betsy can take in going from Farm to Market for 
any value of n <= 6.

Since the number of solutions is required, the entire tree 
must be searched, even if one solution is found quickly. So 
it doesn't matter from a time perspective whether DFS or BFS
 is used. Since DFS takes less space, it is the search of 
 choice for this problem. 
*/

class BetsyTour {
	public static void main(String[] args) {
		for(int n = 1; n <= 6; n++) {
			int[] ways = {0};
			boolean[][] map = new boolean[n][n];
			//map[n-1][0] = true;
			travel(map, n-1, 0, ways, n, 0);
			System.out.println(n + " : " + ways[0]);
		}
	}
	
	static void travel(boolean[][] map, int i, int j, int[] ways, int n, int visited) {
		if(i < 0 || i >= n || j < 0 || j >= n || map[i][j])
			return;
		if(i == 0 && j == 0 && visited == n*n-1) {
			ways[0]++;
			return;
		}
		
		map[i][j] = true;
		travel(map, i+1, j, ways, n, visited+1);
		travel(map, i-1, j, ways, n, visited+1);
		travel(map, i, j+1, ways, n, visited+1);
		travel(map, i, j-1, ways, n, visited+1);
		map[i][j] = false;
	}
}

