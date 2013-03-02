import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

// SRM 564 DIV 1 point 500
public class AlternateColors2
{
	public long countWays(int n, int k)
	{
		long ret = 0;
		int max3 = (k-1) % 3 == 0? n:(k-1);
		for(int cur3 = 0; cur3 <= max3; cur3 += 3) {
			if(k > cur3) {
				int lower = k - cur3;
				if((lower & 1) == 1) { 		// lower is odd
					ret += lower - 1; 		// its actually lower/2*2;
					// now lower == 1
					ret += (n - k) /2 * 4;
					if(((n-k-1) & 1) == 0) 	// if n-k-1 is even, add 2 since 3*i + 2*j == n
						ret += 2;
				} else { //lower is even
					ret += (lower - 2);		// its actually (lower - 2) / 2 * 2
				}
				// no 2, all 1
				ret++;
			} else { // now cur3 is over k, note: cur3 never == k
				int upper = n - cur3;
				if(upper > 0) {
					ret += 3; 				// no 2, all 1
					ret += (upper - 1) / 2 * 6;
					if((upper & 1) == 0)
						ret += 3;
				} else {
					ret++; 			// only happens when cur3 == n 
				}
			}
		}
		
		return ret;
	}
	/*
	public static void main(String[] args) {
	
	long answer = new AlternateColors2().countWays(6, 1);
	}
	*/
	public static void main(String[] args)
	{
		long time;
		long answer;
		boolean errors = false;
		long desiredAnswer;
		
		
		time = System.currentTimeMillis();
		answer = new AlternateColors2().countWays(1, 1);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 1L;
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
		answer = new AlternateColors2().countWays(3, 3);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 3L;
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
		answer = new AlternateColors2().countWays(6, 4);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 9L;
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
		answer = new AlternateColors2().countWays(6, 1);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 21L;
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
		answer = new AlternateColors2().countWays(1000, 2);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 1L;
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
		answer = new AlternateColors2().countWays(100000, 100000);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 1666700000L;
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
