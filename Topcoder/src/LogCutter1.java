import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class LogCutter1
{
	public String bestCut(int L, int A, int K, int C)
	{
		//K = 10;C = 8;L=52;
		//int[] cut = {3,5,9,12,17,25,31,38,39,46,52};
		
		int[] cut = new int[K];
		
		for(int i = 1; i <= K; i++) {
			long x = ((long) A) * i;
			cut[i - 1] = (int)(x % (L - 1)) + 1;
			//System.out.println(" " + cut[0][i - 1]);
		}
		Arrays.sort(cut);
		int count = 1;
		for(int i = 1; i < K; i++) {
			if(cut[i] != cut[i - 1])
				cut[count++] = cut[i];
		}
		count += 2;
		int row = Math.min(count, C);
		int[][] cut1 = new int[row][count];
		for(int i = 1; i < count - 1; i++)
			cut1[0][i]= cut[i-1];
		cut1[0][count - 1] = L;
		
		/*
		for(int i : cut1[0])
			System.out.printf("%8d", i);
		System.out.println();
		*/
		
		//int[] index = new int[count];
		for(int i = 1; i < row; i++) {
			int[] index = new int[count];
			for(int j = i; j < count-1; j++) {
				//int min = L;
				//for(int k = i - 1; k < j; k++) {
					//System.out.printf("i = %8d, j = %8d, k = %8d\n", i, j, k);
					//if(cut1[i - 1][k] > min)
					//	break;
					//int x = Math.max(cut1[0][j] - cut1[0][k], cut1[i - 1][k]);
					//if(x < min)
					//	min = x;
				//}
				int one = Math.max(cut1[0][j+1] - cut1[0][j], cut1[i - 1][j]);
				int two = Math.max(cut1[0][j+1] - cut1[0][index[j - 1]], cut1[i][j - 1]);
				
				if(one > two) index[j]= index[j-1];
				else 	index[j]=j;
				cut1[i][j] = Math.min(one, two);
			}
			/*
			for(int z : index) {
				System.out.printf("%8d", z);
			}
			System.out.println("");
			*/
		}
		
		for(int[] i : cut1) {
		//for(int z = 0; z < 2; z++) {
			//int[] i = cut1[z];
			for(int j : i)
				System.out.printf("%8d", j);
			System.out.println("");
		}
		
		int min = L;
		for(int i = row - 1; i < count; i++) {
			int x = Math.max(cut1[0][count - 1] - cut1[0][i], cut1[row -1][i]);
			min = Math.min(min, x);
		}
		
		System.out.println("Min is " + min);
		
		return "" + min;
	}
	
	public static void main(String[] args) {
		LogCutter lc = new LogCutter();
		//lc.bestCut(10000, 999983, 5000, 1000);
		lc.bestCut(6, 3, 5, 3);
	}
	
	/*
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
	*/
}
//Powered by [KawigiEdit] 2.0!
