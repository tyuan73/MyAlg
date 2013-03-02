import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class MapGuessing
{
	public long countPatterns(String goal, String[] code)
	{
		char[] c = new char[72];
		int start = 36, end = 36, cur = 36;
		long total = 1;
		for(String s : code)
			for(int i = 0; i < s.length(); i++) {
				char x = s.charAt(i);
				boolean changed = false;
				switch (x) {
					case '0':
					case '1':
						c[cur] = x;
						changed = true;
						break;
					case '>':
						cur++;
						if(cur >= 72)
							return 0;
						if(cur > end)
							end++;
						break;
					case '<':
						cur--;
						if(cur < 0)
							return 0;
						if(cur < start)
							start--;
				}
				
				if(end - start + 1 > goal.length())
					return 0;
				if(changed)
					total += match(goal, c, start, end);
			}
			
		return total;
	}
	
	long match(String goal, char[] c, int l, int r) {
		long ret = 0;
		for(int i = 0; i < goal.length() - r + l; i++) {
			int x = 0;
			//boolean matched = true;
			for(int j = l; j <= r; j++) {
				char ch = c[j];
				if(ch != '0' && ch != '1')
					continue;
				if(ch != goal.charAt(i+j-l)) {
					x = 0;
					break;
				} else {
					x++;
				}
			}
			ret += (1 << x) - 1;
		}
		return ret;
	}
	
	public static void main(String[] args)
	{
		long time;
		long answer;
		boolean errors = false;
		long desiredAnswer;
		
		
		time = System.currentTimeMillis();
		answer = new MapGuessing().countPatterns("000", new String[]{"0"});
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
		answer = new MapGuessing().countPatterns("001", new String[]{"0>1"});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 5L;
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
		answer = new MapGuessing().countPatterns("000", new String[]{"1>1>1"});
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
		answer = new MapGuessing().countPatterns("11001", new String[]{">><<<<><<"});
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
		answer = new MapGuessing().countPatterns("1000101011", new String[]{"1<<0>>0>1"});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 22L;
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
		answer = new MapGuessing().countPatterns("00000010000000000000000000000000", new String[]{"><>>0<0<>>1>0><>", "<<0>>0<>><0>0>>><><", ">>>0<>", ">0><>>>>0<<><>>0>>>0<0>>0>"});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 13601L;
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
		answer = new MapGuessing().countPatterns("11100011010111111010100100110001101", new String[]{"11111111111111111111","1<><><><><><><><><>1","1<>000>000><0<><0<>1","1<0<><>0><0<00>00<>1","1<>00<>000><0<0<0<>1","1<><>0>0><0<0<><0<>1","1<000<>0><0<0<><0<>1","1<><><><><><><><><>1","1<>000><000<>000><>1","1<>0><><0<><>0><><>1","1<>000><000<>000><>1","1<><>0><><0<><>0><>1","1<>000><000<>000><>1","1<><><><><><><><><>1","11111111111111111111"});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 90L;
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
