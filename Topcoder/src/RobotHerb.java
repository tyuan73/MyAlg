import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

// ** SRM 570 DIV1 250
public class RobotHerb
{
	public long getdist(int T, int[] a)
	{
		int d = 0;
		long x = 0, y = 0;
		int i = 0;
		for(; i < Math.min(4, T); i++) {
			for(int k : a) {
				if(d == 0) 
					y += k;
				else if (d == 1)
					x += k;
				else if (d == 2)
					y -= k;
				else 
					x -= k;
				d = (d+k) % 4;
			}
			
			if(d == 0)
				break;
		}
		if(i == T-1)
			return Math.abs(x) + Math.abs(y);
			
		i++;
		int round = T/i;
		int more = T%i;
		x *= round; y *= round;
		while(more-- > 0) {
			for(int k: a) {
				if(d == 0)
					y += k;
				else if(d==1)
					x += k;
				else if(d ==2)
					y -= k;
				else
					x -= k;
				d = (d+k) % 4;
			}
		}
		
		return Math.abs(x) + Math.abs(y);
	}
	
	public static void main(String[] args)
	{
		long time;
		long answer;
		boolean errors = false;
		long desiredAnswer;
		
		
		time = System.currentTimeMillis();
		answer = new RobotHerb().getdist(1, new int[]{1,2,3});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 2L;
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
		answer = new RobotHerb().getdist(100, new int[]{1});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 0L;
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
		answer = new RobotHerb().getdist(5, new int[]{1,1,2});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 10L;
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
		answer = new RobotHerb().getdist(1000000000, new int[]{100});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 100000000000L;
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
		answer = new RobotHerb().getdist(570, new int[]{2013,2,13,314,271,1414,1732});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 4112L;
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
