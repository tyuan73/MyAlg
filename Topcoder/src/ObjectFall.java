import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class ObjectFall
{
	public int howLong(int x, int y, String[] ob)
	{
		int[][] seg = new int[ob.length][3];
		for(int i = 0; i < ob.length; i++) {
			String[] s = ob[i].split(" ");
			seg[i][0] = Integer.parseInt(s[0]);
			seg[i][1] = Integer.parseInt(s[1]);
			seg[i][2] = Integer.parseInt(s[2]);
		}
		
		sort(seg);
		
		int count = 0;
		for(int i = ob.length-1; i >= 0; i--) {
			if(seg[i][0] > y || (seg[i][1]) > x || seg[i][2] < x) {
				continue;
			}
			count++;
			x = seg[i][2];
		}
		
		return y + count*5;
	}
	
	void sort(int[][] seg) {
		for(int i = 1; i < seg.length; i++) {
			int y = seg[i][0];
			int x1 = seg[i][1];
			int x2 = seg[i][2];
			int j = i-1;
			for(; j >= 0; j--) {
				if(y < seg[j][0]) {
					seg[j+1][0] = seg[j][0];
					seg[j+1][1] = seg[j][1];
					seg[j+1][2] = seg[j][2];
				} else 
					break;
			}
			seg[j+1][0] = y;
			seg[j+1][1] = x1;
			seg[j+1][2] = x2;
		}
	}
	
	public static void main(String[] args)
	{
		long time;
		int answer;
		boolean errors = false;
		int desiredAnswer;
		
		
		time = System.currentTimeMillis();
		answer = new ObjectFall().howLong(15, 10, new String[]{"005 010 020"});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 15;
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
		answer = new ObjectFall().howLong(15, 12, new String[]{"010 010 020","015 010 020","005 020 050"});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 22;
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
		answer = new ObjectFall().howLong(50, 85, new String[]{"020 001 100","010 100 100","005 100 200"});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 100;
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
		answer = new ObjectFall().howLong(10, 1000, new String[]{});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 1000;
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
		answer = new ObjectFall().howLong(500, 800, new String[]{"800 001 500","400 001 499","401 501 999"});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 805;
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
		answer = new ObjectFall().howLong(645, 802, new String[]{"739 038 799","916 169 791","822 372 911","162 125 992","261 307 545", "510 031 765","592 723 742","477 315 676","566 143 617","337 114 664", "986 648 883","116 230 680","254 746 943","742 948 988","060 117 401", "634 035 433","288 741 864","819 626 730","906 071 222","554 100 121", "573 051 551","949 528 915","562 151 223","857 135 243","621 115 474", "588 405 615","895 812 919","052 378 836","858 116 505","285 428 469", "792 244 250","109 265 637","714 804 885","625 150 410","518 593 921", "282 235 339","081 212 659","663 047 982","556 194 345","798 150 938", "391 026 813","886 348 796","975 007 743","053 854 895","243 561 875"});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = 817;
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
