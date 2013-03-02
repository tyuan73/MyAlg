import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class SpeedRadar
{
	public double averageSpeed(int minLimit, int maxLimit, int[] readings)
	{
		int total = 0; int count = 0;
		int inf = readings.length / 10;
		int t = 0;
		for(int r : readings) {
			if(r > maxLimit || r < minLimit) {
				t++;
				if(t > inf) return 0.0;
			}
			else {
				total += r;
				count++;
			}
		}
		
		return (double)total/count;
	}
	
	public static void main(String[] args)
	{
		long time;
		double answer;
		boolean errors = false;
		double desiredAnswer;
		
		
		time = System.currentTimeMillis();
		answer = new SpeedRadar().averageSpeed(1, 50, new int[]{45, 40, 50});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 45.0D;
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
		answer = new SpeedRadar().averageSpeed(1, 50, new int[]{42,43,44,45,46,47,48,49,50,51});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 46.0D;
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
		answer = new SpeedRadar().averageSpeed(1, 50, new int[]{42,46,48,50,52});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 0.0D;
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
		answer = new SpeedRadar().averageSpeed(20, 60, new int[]{25,45,45,43,24,9,51,55,60,34,61,23,40,40,47,49,33,23,47,54,54});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 41.68421052631579D;
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
