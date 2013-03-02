import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class PastingPaintingDivOne
{// Topcoder: 849 - SRM 562 div1.
	public long[] countColors(String[] clipboard, int T)
	{
		long[] ret = {0,0,0};
		int pre = -1;
		int n = clipboard.length;
		int m = clipboard[0].length();
		for(int i = n - 1; i >= 0; i--) {
			pre = -1;
			for(int k = 0; k <= n - 1 - i && k < m; k++) {
				char c = clipboard[i + k].charAt(k);
				if(c == '.')
					continue;
					
				ret[getIndex(c)] += pre == -1? T : Math.min(T, k - pre);
				pre = k;
			}
		}
		
		for(int i = 1; i < m; i++) {
			pre = -1;
			for(int k = 0; k <= m - 1 - i && k < n; k++) {
				char c = clipboard[k].charAt(i + k);
				if(c == '.')
					continue;
				ret[getIndex(c)] += pre == -1? T: Math.min(T, k - pre);
				pre = k;
			}
		}
		
		return ret;
	}
	
	int getIndex(char ch) {
		if(ch == 'R') return 0;
		if(ch == 'G') return 1;
		if(ch == 'B') return 2;
		return 3;
	}
	
	public static void main(String[] args)
	{
		long time;
		long[] answer;
		boolean errors = false;
		long[] desiredAnswer;
		
		boolean same;
		
		time = System.currentTimeMillis();
		answer = new PastingPaintingDivOne().countColors(new String[]{"..G","R..","BG."}, 3);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = new long[]{3L, 4L, 3L };
		System.out.println("Your answer:");
		if (answer.length > 0)
		{
			System.out.print("\t{ " + answer[0]);
			for (int i=1; i<answer.length; i++)
				System.out.print(", " + answer[i]);
			System.out.println(" }");
		}
		else
			System.out.println("\t{ }");
		System.out.println("Desired answer:");
		if (desiredAnswer.length > 0)
		{
			System.out.print("\t{ " + desiredAnswer[0]);
			for (int i=1; i<desiredAnswer.length; i++)
				System.out.print(", " + desiredAnswer[i]);
			System.out.println(" }");
		}
		else
			System.out.println("\t{ }");
		same = desiredAnswer.length == answer.length;
		for (int i=0; i<answer.length && same; i++)
			if (answer[i] != desiredAnswer[i])
				same = false;
		if (!same)
		{
			errors = true;
			System.out.println("DOESN'T MATCH!!!!");
		}
		else
			System.out.println("Match :-)");
		System.out.println();
		time = System.currentTimeMillis();
		answer = new PastingPaintingDivOne().countColors(new String[]{"R...","....","....","...R"}, 2);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = new long[]{4L, 0L, 0L };
		System.out.println("Your answer:");
		if (answer.length > 0)
		{
			System.out.print("\t{ " + answer[0]);
			for (int i=1; i<answer.length; i++)
				System.out.print(", " + answer[i]);
			System.out.println(" }");
		}
		else
			System.out.println("\t{ }");
		System.out.println("Desired answer:");
		if (desiredAnswer.length > 0)
		{
			System.out.print("\t{ " + desiredAnswer[0]);
			for (int i=1; i<desiredAnswer.length; i++)
				System.out.print(", " + desiredAnswer[i]);
			System.out.println(" }");
		}
		else
			System.out.println("\t{ }");
		same = desiredAnswer.length == answer.length;
		for (int i=0; i<answer.length && same; i++)
			if (answer[i] != desiredAnswer[i])
				same = false;
		if (!same)
		{
			errors = true;
			System.out.println("DOESN'T MATCH!!!!");
		}
		else
			System.out.println("Match :-)");
		System.out.println();
		time = System.currentTimeMillis();
		answer = new PastingPaintingDivOne().countColors(new String[]{"RGB"}, 100000);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = new long[]{100000L, 100000L, 100000L };
		System.out.println("Your answer:");
		if (answer.length > 0)
		{
			System.out.print("\t{ " + answer[0]);
			for (int i=1; i<answer.length; i++)
				System.out.print(", " + answer[i]);
			System.out.println(" }");
		}
		else
			System.out.println("\t{ }");
		System.out.println("Desired answer:");
		if (desiredAnswer.length > 0)
		{
			System.out.print("\t{ " + desiredAnswer[0]);
			for (int i=1; i<desiredAnswer.length; i++)
				System.out.print(", " + desiredAnswer[i]);
			System.out.println(" }");
		}
		else
			System.out.println("\t{ }");
		same = desiredAnswer.length == answer.length;
		for (int i=0; i<answer.length && same; i++)
			if (answer[i] != desiredAnswer[i])
				same = false;
		if (!same)
		{
			errors = true;
			System.out.println("DOESN'T MATCH!!!!");
		}
		else
			System.out.println("Match :-)");
		System.out.println();
		time = System.currentTimeMillis();
		answer = new PastingPaintingDivOne().countColors(new String[]{"."}, 1000000000);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = new long[]{0L, 0L, 0L };
		System.out.println("Your answer:");
		if (answer.length > 0)
		{
			System.out.print("\t{ " + answer[0]);
			for (int i=1; i<answer.length; i++)
				System.out.print(", " + answer[i]);
			System.out.println(" }");
		}
		else
			System.out.println("\t{ }");
		System.out.println("Desired answer:");
		if (desiredAnswer.length > 0)
		{
			System.out.print("\t{ " + desiredAnswer[0]);
			for (int i=1; i<desiredAnswer.length; i++)
				System.out.print(", " + desiredAnswer[i]);
			System.out.println(" }");
		}
		else
			System.out.println("\t{ }");
		same = desiredAnswer.length == answer.length;
		for (int i=0; i<answer.length && same; i++)
			if (answer[i] != desiredAnswer[i])
				same = false;
		if (!same)
		{
			errors = true;
			System.out.println("DOESN'T MATCH!!!!");
		}
		else
			System.out.println("Match :-)");
		System.out.println();
		time = System.currentTimeMillis();
		answer = new PastingPaintingDivOne().countColors(new String[]{"RB.",".G."}, 100);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = new long[]{100L, 1L, 100L };
		System.out.println("Your answer:");
		if (answer.length > 0)
		{
			System.out.print("\t{ " + answer[0]);
			for (int i=1; i<answer.length; i++)
				System.out.print(", " + answer[i]);
			System.out.println(" }");
		}
		else
			System.out.println("\t{ }");
		System.out.println("Desired answer:");
		if (desiredAnswer.length > 0)
		{
			System.out.print("\t{ " + desiredAnswer[0]);
			for (int i=1; i<desiredAnswer.length; i++)
				System.out.print(", " + desiredAnswer[i]);
			System.out.println(" }");
		}
		else
			System.out.println("\t{ }");
		same = desiredAnswer.length == answer.length;
		for (int i=0; i<answer.length && same; i++)
			if (answer[i] != desiredAnswer[i])
				same = false;
		if (!same)
		{
			errors = true;
			System.out.println("DOESN'T MATCH!!!!");
		}
		else
			System.out.println("Match :-)");
		System.out.println();
		time = System.currentTimeMillis();
		answer = new PastingPaintingDivOne().countColors(new String[]{"..........G..........",".........G.G.........","........G...G........",".......G.....G.......","......G..B.B..G......",".....G...B.B...G.....","....G...........G....","...G...R.....R...G...","..G.....RRRRRR....G..",".G..........RR.....G.","GGGGGGGGGGGGGGGGGGGGG"}, 1000000000);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = new long[]{2000000018L, 17000000048L, 2000000005L };
		System.out.println("Your answer:");
		if (answer.length > 0)
		{
			System.out.print("\t{ " + answer[0]);
			for (int i=1; i<answer.length; i++)
				System.out.print(", " + answer[i]);
			System.out.println(" }");
		}
		else
			System.out.println("\t{ }");
		System.out.println("Desired answer:");
		if (desiredAnswer.length > 0)
		{
			System.out.print("\t{ " + desiredAnswer[0]);
			for (int i=1; i<desiredAnswer.length; i++)
				System.out.print(", " + desiredAnswer[i]);
			System.out.println(" }");
		}
		else
			System.out.println("\t{ }");
		same = desiredAnswer.length == answer.length;
		for (int i=0; i<answer.length && same; i++)
			if (answer[i] != desiredAnswer[i])
				same = false;
		if (!same)
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
