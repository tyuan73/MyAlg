import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class CardStraights
{
	public int longestStraight(int[] cards)
	{
		boolean[] u = new boolean[1000000];
		
		int j = 0;
		for(int c : cards) {
			if(c == 0) j++;
			else u[c - 1] = true;
		}
		
		int index = 0;
		ArrayList<Integer> poscount = new ArrayList<Integer>();
		ArrayList<Integer> negcount = new ArrayList<Integer>();
		int i = 0;
		while( i < 1000000) {
			int pos = 0;
			while(i < 1000000 && u[i])
				{pos++; i++;}
			poscount.add(pos);
			pos = 0;
			while(i < 1000000 && !u[i])
				{pos++; i++;}
			negcount.add(pos);
		}
		if(negcount.size() < poscount.size())
			negcount.add(0);
		
		int max = 0 ;
		//r = 0;
		for(int l = 0; l < poscount.size(); l++) {
			int r = l;
			int cur = 0, remainj = j;
			while(r <negcount.size() && remainj >= negcount.get(r))
				remainj -= negcount.get(r++);
			for(int k = l; k <= r; k++)
				cur += poscount.get(k);
			cur += j;
			cur = Math.min(cur, cards.length);
			max = Math.max(max, cur);
		}
		
		return max;
	}
	
	public static void main(String[] args)
	{
		long time;
		int answer;
		boolean errors = false;
		int desiredAnswer;
		
		
		time = System.currentTimeMillis();
		answer = new CardStraights().longestStraight(new int[]{0,6,5,10,3,0,11});
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
		answer = new CardStraights().longestStraight(new int[]{100,100,100,101,100,99,97,103});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 3;
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
		answer = new CardStraights().longestStraight(new int[]{0,0,0,1,2,6,8,1000});
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
		answer = new CardStraights().longestStraight(new int[]{1,9,5,7,3,4,0,0,0,10});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 10;
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
