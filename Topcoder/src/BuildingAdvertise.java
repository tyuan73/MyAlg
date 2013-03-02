import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class BuildingAdvertise
{
	public long getMaxArea(int[] h, int n)
	{
		int j = 0;
		int m = h.length;
		long[] R = new long[n];
		for(int i = 0; i < n; i++) {
			R[i] = h[j];
			int s = (j + 1) % m;
			h[j] = ((h[j] ^ h[s]) + 13) % 835454957;
			j = s;
		}
		
		Stack<Integer> s = new Stack<Integer>();
		s.push(0);
		long max = 0;
		for(int i = 1; i < n; i++) {
			long x = R[i];
			if(x > R[s.peek()])
			 	s.push(i);
			else {
				int pre = 0;
				do {
					pre = s.pop();
					long area = (i - pre) * (long) R[pre];
					max = Math.max(max, area);
					//max = Math.max(area, max);
				} while(!s.empty() && x <= R[s.peek()]);
				s.push(pre);
				R[pre] = x;
			}
		}
		
		while(!s.empty()) {
			int i = s.pop();
			//long area = (n - i) * (long)
			max = Math.max(max, (n - i) * R[i]);
		}
		
		return max;
	}
	
	public static void main(String[] args)
	{
		long time;
		long answer;
		boolean errors = false;
		long desiredAnswer;
		
		
		time = System.currentTimeMillis();
		answer = new BuildingAdvertise().getMaxArea(new int[]{3,6,5,6,2,4}, 6);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 15L;
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
		answer = new BuildingAdvertise().getMaxArea(new int[]{5,0,7,0,2,6,2}, 7);
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
		answer = new BuildingAdvertise().getMaxArea(new int[]{1048589,2097165}, 100000);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 104858900000L;
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
		answer = new BuildingAdvertise().getMaxArea(new int[]{1,7,2,5,3,1}, 6);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 8L;
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
