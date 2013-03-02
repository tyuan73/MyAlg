import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class CentaurCompanyDiv2
{
	int[] order, parent;
	boolean[] visited;
	int cur = 0;
	ArrayList<Integer>[] servers;
	public long count(int[] a, int[] b)
	{
		int n = a.length+1;
		order = new int[n];
		parent = new int[n];
		visited = new boolean[n];
		servers = new ArrayList[n];
		cur = 0;
		for(int i = 0; i < n; i++)
			servers[i] = new ArrayList<Integer>();

		for(int i = 0; i < n-1; i++) {
			int from = a[i] - 1;
			int to = b[i] - 1;
			servers[from].add(to);
			servers[to].add(from);
		}
		
		long[] dp = new long[n];
		Arrays.fill(dp, 1l);
		dfs(0);

		for(int i = 0; i < n-1; i++)
			dp[parent[order[i]]] *= dp[order[i]] + 1;

        long ret = 1;
		for(long x : dp)
			ret += x;
		return ret;
	}
	
	void dfs(int index) {
		visited[index] = true;
		for(int s : servers[index]) {
			if(!visited[s]) {
				parent[s] = index;
				dfs(s);
			}
		}
		order[cur++] = index;
	}
	
	public static void main(String[] args)
	{
		long time;
		long answer;
		boolean errors = false;
		long desiredAnswer;
		
		
		time = System.currentTimeMillis();
		answer = new CentaurCompanyDiv2().count(new int[]{1}, new int[]{2});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 4L;
		System.out.println("Your answer:");
		System.out.println("\t" + answer);
		System.out.println("Desired answer:");
		System.out.println("\t" + desiredAnswer);
		if (answer != desiredAnswer)
		{
			errors = true;
			System.out.println("DOESN'T MATCH!!!!");
		}
		else
			System.out.println("Match :-)");
		System.out.println();
		time = System.currentTimeMillis();
		answer = new CentaurCompanyDiv2().count(new int[]{2,2}, new int[]{1,3});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 7L;
		System.out.println("Your answer:");
		System.out.println("\t" + answer);
		System.out.println("Desired answer:");
		System.out.println("\t" + desiredAnswer);
		if (answer != desiredAnswer)
		{
			errors = true;
			System.out.println("DOESN'T MATCH!!!!");
		}
		else
			System.out.println("Match :-)");
		System.out.println();
		time = System.currentTimeMillis();
		answer = new CentaurCompanyDiv2().count(new int[]{1,2,3,4,5,6,7,8,9}, new int[]{2,3,4,5,6,7,8,9,10});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 56L;
		System.out.println("Your answer:");
		System.out.println("\t" + answer);
		System.out.println("Desired answer:");
		System.out.println("\t" + desiredAnswer);
		if (answer != desiredAnswer)
		{
			errors = true;
			System.out.println("DOESN'T MATCH!!!!");
		}
		else
			System.out.println("Match :-)");
		System.out.println();
		time = System.currentTimeMillis();
		answer = new CentaurCompanyDiv2().count(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, new int[]{2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 1125899906842675L;
		System.out.println("Your answer:");
		System.out.println("\t" + answer);
		System.out.println("Desired answer:");
		System.out.println("\t" + desiredAnswer);
		if (answer != desiredAnswer)
		{
			errors = true;
			System.out.println("DOESN'T MATCH!!!!");
		}
		else
			System.out.println("Match :-)");
		System.out.println();
		time = System.currentTimeMillis();
		answer = new CentaurCompanyDiv2().count(new int[]{10, 7, 2, 5, 6, 2, 4, 9, 7}, new int[]{8, 10, 10, 4, 1, 6, 2, 2, 3});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 144L;
		System.out.println("Your answer:");
		System.out.println("\t" + answer);
		System.out.println("Desired answer:");
		System.out.println("\t" + desiredAnswer);
		if (answer != desiredAnswer)
		{
			errors = true;
			System.out.println("DOESN'T MATCH!!!!");
		}
		else
			System.out.println("Match :-)");
		System.out.println();
		
		
		if (errors)
			System.out.println("Some of the test cases had errors :-(");
		else
			System.out.println("You're a stud (at least on the test data)! :-D ");
	}

}
//Powered by [KawigiEdit] 2.0!
