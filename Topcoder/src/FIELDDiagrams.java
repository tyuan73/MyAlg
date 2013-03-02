import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class FIELDDiagrams
{
	public long countDiagrams(int fieldOrder)
	{
		long[][] dp = new long[fieldOrder][fieldOrder + 1];
		dp[1][0] = 1;
		dp[1][1] = 2;
		dp[0][0] = 1;
		dp[0][1] = 1;
		
		long count = 0;
		for(int i = 1; i <= fieldOrder; i++)
			count += internal(fieldOrder - 1, i, dp);
		return count;
		
	}
	
	private long internal(int row, int pre, long[][] dp) {
		if(dp[row][pre] > 0) {
			return dp[row][pre];
		}
		if(pre == 0) {
			dp[row][pre] = 1;
			return 1;
		}
		
		long count = 0;
		int max = pre > row ? row: pre;
		for(int i = 0; i <= max; i++)
			count += internal(row - 1, i, dp);
		dp[row][pre] = count;
		return count;
	}
	
	public static void main(String[] args)
	{
		long time;
		long answer;
		boolean errors = false;
		long desiredAnswer;
		
		
		time = System.currentTimeMillis();
		answer = new FIELDDiagrams().countDiagrams(2);
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
		answer = new FIELDDiagrams().countDiagrams(3);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 13L;
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
		answer = new FIELDDiagrams().countDiagrams(5);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 131L;
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
