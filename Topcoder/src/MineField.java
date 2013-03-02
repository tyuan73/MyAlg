import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class MineField
{
	public String[] getMineField(String mineLocations)
	{
		int[][] bd = new int[11][11];
		String[] in = mineLocations.split("\\)");
		//int total = in.length - 1;
        for(String s: in)
        System.out.println(s+"K");
		for(int i = 0; i < in.length; i++) {
            if(in[i].length() == 0)continue;
			String mine = in[i].substring(1);
			String[] pos = mine.split(",");
			int row = Integer.parseInt(pos[0]) + 1;
			int col = Integer.parseInt(pos[1]) + 1;
			bd[row][col] = -1;
		}

        for(int[] i : bd) {
            for(int j : i)
                System.out.printf("%5d", j);
            System.out.println();
        }
		
		for(int i = 1; i <= 9; i++) {
			for(int j = 1; j <= 9; j++) {
				if(bd[i][j] == -1)
					continue;
				if(bd[i-1][j-1] < 0)
					bd[i][j]++;
				if(bd[i-1][j] < 0)
					bd[i][j]++;
				if(bd[i-1][j+1] < 0)
					bd[i][j]++;
				if(bd[i][j-1] < 0)
					bd[i][j]++;
				if(bd[i][j+1] < 0)
					bd[i][j]++;
				if(bd[i+1][j-1] < 0)
					bd[i][j]++;
				if(bd[i+1][j] < 0)
					bd[i][j]++;
				if(bd[i+1][j+1] < 0)
					bd[i][j]++;
			}
		}
		
		String[] ret = new String[9];
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= 9; i++) {
			sb.setLength(0);
			for(int j = 1; j<=9; j++) {
				if(bd[i][j] == -1)
					sb.append('M');
				else
					sb.append(bd[i][j]);
			}
			ret[i-1] = sb.toString();
		}
		
		return ret;
		
	}
	
	public static void main(String[] args)
	{
		long time;
		String[] answer;
		boolean errors = false;
		String[] desiredAnswer;
		
		boolean same;
		
		time = System.currentTimeMillis();
		answer = new MineField().getMineField("(0,0)(1,0)(2,0)(3,0)(4,0)");
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = new String[]{ "M20000000",  "M30000000",  "M30000000",  "M30000000",  "M20000000",  "110000000",  "000000000",  "000000000",  "000000000" };
		System.out.println("Your answer:");
		if (answer.length > 0)
		{
			System.out.print("\t{ \"" + answer[0] + "\"");
			for (int i=1; i<answer.length; i++)
				System.out.print(",\n\t  \"" + answer[i] + "\"");
			System.out.println(" }");
		}
		else
			System.out.println("\t{ }");
		System.out.println("Desired answer:");
		if (desiredAnswer.length > 0)
		{
			System.out.print("\t{ \"" + desiredAnswer[0] + "\"");
			for (int i=1; i<desiredAnswer.length; i++)
				System.out.print(",\n\t  \"" + desiredAnswer[i] + "\"");
			System.out.println(" }");
		}
		else
			System.out.println("\t{ }");
		same = desiredAnswer.length == answer.length;
		for (int i=0; i<answer.length && same; i++)
			if (!answer[i].equals(desiredAnswer[i]))
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
		answer = new MineField().getMineField("(0,0)(0,8)(8,0)(8,8)");
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = new String[]{ "M1000001M",  "110000011",  "000000000",  "000000000",  "000000000",  "000000000",  "000000000",  "110000011",  "M1000001M" };
		System.out.println("Your answer:");
		if (answer.length > 0)
		{
			System.out.print("\t{ \"" + answer[0] + "\"");
			for (int i=1; i<answer.length; i++)
				System.out.print(",\n\t  \"" + answer[i] + "\"");
			System.out.println(" }");
		}
		else
			System.out.println("\t{ }");
		System.out.println("Desired answer:");
		if (desiredAnswer.length > 0)
		{
			System.out.print("\t{ \"" + desiredAnswer[0] + "\"");
			for (int i=1; i<desiredAnswer.length; i++)
				System.out.print(",\n\t  \"" + desiredAnswer[i] + "\"");
			System.out.println(" }");
		}
		else
			System.out.println("\t{ }");
		same = desiredAnswer.length == answer.length;
		for (int i=0; i<answer.length && same; i++)
			if (!answer[i].equals(desiredAnswer[i]))
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
		answer = new MineField().getMineField("(3,2)(3,3)(3,4)(4,2)(4,4)(5,2)(5,3)(5,4)(7,4)(6,7)");
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = new String[]{ "000000000",  "000000000",  "012321000",  "02MMM2000",  "03M8M3000",  "02MMM2111",  "0124321M1",  "0001M1111",  "000111000" };
		System.out.println("Your answer:");
		if (answer.length > 0)
		{
			System.out.print("\t{ \"" + answer[0] + "\"");
			for (int i=1; i<answer.length; i++)
				System.out.print(",\n\t  \"" + answer[i] + "\"");
			System.out.println(" }");
		}
		else
			System.out.println("\t{ }");
		System.out.println("Desired answer:");
		if (desiredAnswer.length > 0)
		{
			System.out.print("\t{ \"" + desiredAnswer[0] + "\"");
			for (int i=1; i<desiredAnswer.length; i++)
				System.out.print(",\n\t  \"" + desiredAnswer[i] + "\"");
			System.out.println(" }");
		}
		else
			System.out.println("\t{ }");
		same = desiredAnswer.length == answer.length;
		for (int i=0; i<answer.length && same; i++)
			if (!answer[i].equals(desiredAnswer[i]))
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
		answer = new MineField().getMineField("");
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = new String[]{ "000000000",  "000000000",  "000000000",  "000000000",  "000000000",  "000000000",  "000000000",  "000000000",  "000000000" };
		System.out.println("Your answer:");
		if (answer.length > 0)
		{
			System.out.print("\t{ \"" + answer[0] + "\"");
			for (int i=1; i<answer.length; i++)
				System.out.print(",\n\t  \"" + answer[i] + "\"");
			System.out.println(" }");
		}
		else
			System.out.println("\t{ }");
		System.out.println("Desired answer:");
		if (desiredAnswer.length > 0)
		{
			System.out.print("\t{ \"" + desiredAnswer[0] + "\"");
			for (int i=1; i<desiredAnswer.length; i++)
				System.out.print(",\n\t  \"" + desiredAnswer[i] + "\"");
			System.out.println(" }");
		}
		else
			System.out.println("\t{ }");
		same = desiredAnswer.length == answer.length;
		for (int i=0; i<answer.length && same; i++)
			if (!answer[i].equals(desiredAnswer[i]))
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
