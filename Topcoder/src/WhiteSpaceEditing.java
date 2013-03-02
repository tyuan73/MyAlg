import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class WhiteSpaceEditing
{
	public int getMinimum(int[] lines)
	{
		int ret = getSteps(lines, 0, lines.length-1, 0);
		return ret + lines.length - 1;
	/*
		int min = lines[0];
		for(int i = 1; i < lines.length; i++) {
			int diff = lines[i]-lines[i-1];
			min += diff <= 0? 1 : 1+diff;
		}
		
		return min;
	*/
	}
	
	int getSteps(int[] lines, int l, int r, int min) {
		if(l > r)
			return 0;
		int ret = 0;
		int iMin = Integer.MAX_VALUE;
		int index = 0;
		for(int i = l; i <= r; i++) {
			if(lines[i] < iMin)
			{
				iMin = lines[i];
				index = i;
			}
		}
		ret = iMin - min + getSteps(lines, l, index - 1, iMin) + getSteps(lines, index+1, r, iMin);
		return ret;
	}
	
	public static void main(String[] args)
	{
		long time;
		int answer;
		boolean errors = false;
		int desiredAnswer;
		
		
		time = System.currentTimeMillis();
		answer = new WhiteSpaceEditing().getMinimum(new int[]{ 3, 2, 3 });
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 6;
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
		answer = new WhiteSpaceEditing().getMinimum(new int[]{ 0 });
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 0;
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
		answer = new WhiteSpaceEditing().getMinimum(new int[]{ 1, 2, 4 });
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 6;
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
		answer = new WhiteSpaceEditing().getMinimum(new int[]{ 250, 105, 155, 205, 350 });
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 499;
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
