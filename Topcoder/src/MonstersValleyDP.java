import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

//SRM 565 DIV 1 250 points
public class MonstersValleyDP
{
	public int minimumPrice(long[] dread, int[] price)
	{
		int n = dread.length;
		long[][] dp = new long[n+1][2*n+1];
		for(int i = 1; i <= n; i++) {
			for(int j = 0; j <= 2*n; j++) {// j is price to pay
				dp[i][j] = -1;
				if(j >= price[i-1] && dp[i-1][j-price[i-1]] >= 0)
					dp[i][j] = dp[i-1][j-price[i-1]] + dread[i-1];
				if(dp[i-1][j] >= dread[i-1])
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j]);
			}
		}
		
		for(int i = 1; i <= 2*n; i++) {
			if(dp[n][i] > 0)
				return i;
		}
		return 2*n;
	}
	
	public static void main(String[] args)
	{
		long time;
		int answer;
		boolean errors = false;
		int desiredAnswer;
		
		
		time = System.currentTimeMillis();
		answer = new MonstersValley().minimumPrice(new long[]{8L, 5L, 10L}, new int[]{1, 1, 2});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 2;
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
		answer = new MonstersValley().minimumPrice(new long[]{1L, 2L, 4L, 1000000000L}, new int[]{1, 1, 1, 2});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 5;
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
		answer = new MonstersValley().minimumPrice(new long[]{200L, 107L, 105L, 206L, 307L, 400L}, new int[]{1, 2, 1, 1, 1, 2});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 2;
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
		answer = new MonstersValley().minimumPrice(new long[]{5216L, 12512L, 613L, 1256L, 66L, 17202L, 30000L, 23512L, 2125L, 33333L}, new int[]{2, 2, 1, 1, 1, 1, 2, 1, 2, 1});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 5;
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
