import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;


// SRM 329 DIV 1 - 500 points
public class LogCutter
{
	public String bestCut(int L, int A, int K, int C)
	{
		int[] cuts = new int[K + 2];
		for(int i = 1; i <= K; i++) 
			cuts[i] = (int)((((long) A) * i) % (L - 1)) + 1;
			
		cuts[K + 1] = L;
		Arrays.sort(cuts);
		
		int l = 0, r = L;
		int cut = 0, first = 0;
		while(r > l) {
			int mid = (l + r) / 2;
			int f = getFirstCut(cuts, mid, C);
			if(f == -1)
				l = mid + 1;
			else {
				cut = mid;
				first = f;
				r = mid;
			}
		}
		
		return cut + " " + first;
	}
	
	int getFirstCut(int[] cuts, int maxLen, int C) {
		int cur = cuts.length - 1;
		while(cur > 0 && C-- > 0) {
			int next = cur;
			while(next > 0 && cuts[cur] - cuts[next - 1] <= maxLen)
				next--;
			cur = next;
		}
		
		if(cur == 0)
			return cuts[1];
		return cuts[cur] > maxLen ? -1 : cuts[cur];
	}
	
	public static void main(String[] args)
	{
		long time;
		String answer;
		boolean errors = false;
		String desiredAnswer;
		
		
		time = System.currentTimeMillis();
		answer = new LogCutter().bestCut(9, 3, 2, 1);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = "5 4";
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
		answer = new LogCutter().bestCut(5, 2, 1, 2);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = "3 3";
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
		answer = new LogCutter().bestCut(6, 3, 5, 3);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = "2 1";
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
		answer = new LogCutter().bestCut(10000, 999983, 5000, 1000);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = "13 2";
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
		answer = new LogCutter().bestCut(5, 7, 100, 100);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = "1 1";
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
