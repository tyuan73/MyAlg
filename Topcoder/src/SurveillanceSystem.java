import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class SurveillanceSystem
{
	public String getContainerInfo(String containers, int[] reports, int L)
	{
		int n = containers.length();
		char[] out = new char[n];
		Arrays.fill(out, '?');
		int[] count1 = new int[n+1];
		for(int i : reports)
			count1[i]++;
		
		int[] count2 = new int[n+1];
		int c = 0;
		for(int i = 0; i < L; i++)
			if(containers.charAt(i) == 'X')
				c++;
			
		int[][] map = new int[n+1][n];
		count2[c]++;
		for(int i = 0; i < L; i++) {
			map[c][i]++;
		}
		for(int i = L; i < n; i++) {
			if(containers.charAt(i) == 'X')
				c++;
			if(containers.charAt(i-L) == 'X')
				c--;
			count2[c]++;
			for(int j = i; j > i - L; j--)
				map[c][j]++;
		}
				
		for(int i = 0; i <= n; i++) {
			if (count1[i] != 0) {
				int diff = count2[i] - count1[i];
				for(int j = 0; j < n; j++) {
					if(map[i][j] > diff)
						out[j] = '+';
				}
			}
		}

        for(int i = 0; i < n; i++) {
            if(out[i] == '+')
                continue;
            int t = 0;
            for(int j = 0; j <= n; j++) {
                if(map[j][i] > 0)
                    t += count1[j];
            }
            if(t == 0)
                out[i] = '-';
        }
		
		return new String(out);
	}
	
	public static void main(String[] args)
	{
		long time;
		String answer;
		boolean errors = false;
		String desiredAnswer;
		
		
		time = System.currentTimeMillis();
		answer = new SurveillanceSystem().getContainerInfo("-X--XX", new int[]{1, 2}, 3);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = "??++++";
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
		answer = new SurveillanceSystem().getContainerInfo("-XXXXX-", new int[]{2}, 3);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = "???-???";
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
		answer = new SurveillanceSystem().getContainerInfo("------X-XX-", new int[]{3, 0, 2, 0}, 5);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = "++++++++++?";
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
		answer = new SurveillanceSystem().getContainerInfo("-XXXXX---X--", new int[]{2, 1, 0, 1}, 3);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = "???-??++++??";
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
		answer = new SurveillanceSystem().getContainerInfo("-XX--X-XX-X-X--X---XX-X---XXXX-----X", new int[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, 7);
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = "???++++?++++++++++++++++++++??????--";
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
