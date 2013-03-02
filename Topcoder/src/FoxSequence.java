import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class FoxSequence
{
	public String isValid(int[] seq)
	{
		int lMaxCount = 0;
		int[] lMax = new int[50];
		int lMinCount = 0;
		int[] lMin = new int[50];
		for(int i = 1; i <= seq.length - 2; i++) {
			int pre = seq[i-1];
			int cur = seq[i];
			int next = seq[i+1];
			if(cur > pre && cur > next) {
				lMax[lMaxCount++] = i;
				continue;
			}
			if(cur < pre && cur <= next) {
				if(lMinCount > 0)
					return "NO";
				lMin[lMinCount++] = i;
				continue;
			}
			if(cur == pre && cur < next) {
				if(lMinCount != 1)
					return "NO";
				lMin[lMinCount++] = i;
				continue;
			}
		}
		if(lMinCount == 1)
			lMin[lMinCount++] = lMin[0];
		
		if(lMaxCount != 2 || lMinCount != 2)
			return "NO";
		
		if((!valid(seq, 0, lMax[0]))
			|| (!valid(seq, lMax[1], seq.length-1))
			|| (!valid(seq, lMax[0], lMin[0]))
			|| (!valid(seq, lMin[1], lMax[1])))
			return "NO";
		return "YES"; 
	}
	
	boolean valid(int[] seq, int l, int r) {
		if(l >= r)
			return false;
		int d = seq[l+1] - seq[l];
		for(int i = l+2; i<=r; i++) {
			if(d != seq[i] - seq[i-1])
				return false;
		}
		return true;
	}
	
	public static void main(String[] args)
	{
		long time;
		String answer;
		boolean errors = false;
		String desiredAnswer;
		
		
		time = System.currentTimeMillis();
		answer = new FoxSequence().isValid(new int[]{1,3,5,7,5,3,1,1,1,3,5,7,5,3,1});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = "YES";
		System.out.println("Your answer:");
		System.out.println("\t\"" + answer + "\"");
		System.out.println("Desired answer:");
		System.out.println("\t\"" + desiredAnswer + "\"");
		if (!answer.equals(desiredAnswer))
		{
			errors = true;
			System.out.println("DOESN'T MATCH!!!!");
		}
		else
			System.out.println("Match :-)");
		System.out.println();
		time = System.currentTimeMillis();
		answer = new FoxSequence().isValid(new int[]{1,2,3,4,5,4,3,2,2,2,3,4,5,6,4});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = "YES";
		System.out.println("Your answer:");
		System.out.println("\t\"" + answer + "\"");
		System.out.println("Desired answer:");
		System.out.println("\t\"" + desiredAnswer + "\"");
		if (!answer.equals(desiredAnswer))
		{
			errors = true;
			System.out.println("DOESN'T MATCH!!!!");
		}
		else
			System.out.println("Match :-)");
		System.out.println();
		time = System.currentTimeMillis();
		answer = new FoxSequence().isValid(new int[]{3,6,9,1,9,5,1});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = "YES";
		System.out.println("Your answer:");
		System.out.println("\t\"" + answer + "\"");
		System.out.println("Desired answer:");
		System.out.println("\t\"" + desiredAnswer + "\"");
		if (!answer.equals(desiredAnswer))
		{
			errors = true;
			System.out.println("DOESN'T MATCH!!!!");
		}
		else
			System.out.println("Match :-)");
		System.out.println();
		time = System.currentTimeMillis();
		answer = new FoxSequence().isValid(new int[]{1,2,3,2,1,2,3,2,1,2,3,2,1});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = "NO";
		System.out.println("Your answer:");
		System.out.println("\t\"" + answer + "\"");
		System.out.println("Desired answer:");
		System.out.println("\t\"" + desiredAnswer + "\"");
		if (!answer.equals(desiredAnswer))
		{
			errors = true;
			System.out.println("DOESN'T MATCH!!!!");
		}
		else
			System.out.println("Match :-)");
		System.out.println();
		time = System.currentTimeMillis();
		answer = new FoxSequence().isValid(new int[]{1,3,4,3,1,1,1,1,3,4,3,1});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = "NO";
		System.out.println("Your answer:");
		System.out.println("\t\"" + answer + "\"");
		System.out.println("Desired answer:");
		System.out.println("\t\"" + desiredAnswer + "\"");
		if (!answer.equals(desiredAnswer))
		{
			errors = true;
			System.out.println("DOESN'T MATCH!!!!");
		}
		else
			System.out.println("Match :-)");
		System.out.println();
		time = System.currentTimeMillis();
		answer = new FoxSequence().isValid(new int[]{6,1,6});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = "NO";
		System.out.println("Your answer:");
		System.out.println("\t\"" + answer + "\"");
		System.out.println("Desired answer:");
		System.out.println("\t\"" + desiredAnswer + "\"");
		if (!answer.equals(desiredAnswer))
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
