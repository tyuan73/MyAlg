import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class StringGame
{
	public int[] getWinningStrings(String[] S)
	{
        int n = S.length;
		ArrayList<Integer> ret = new ArrayList<Integer>();
        int[][] map = new int[n][26];
        for(int i = 0; i < n; i++) {
            String s = S[i];
            for(int j = 0; j < s.length(); j++)
                map[i][s.charAt(j)-'a']++;
        }

		for(int i = 0; i < n; i++) {
			int[][] count = getCount(S[i]);
			boolean ok = true;
			for(int j = 0; j < n; j++) {
				if(j == i) continue;
				int[] d = map[j];

				boolean bigger = false;
				for(int z = 0; z < 26; z++) {
                    int idx = count[1][z];
                    if(count[0][z] > d[idx])
                        {bigger = true; break;}
                    else if(count[0][z] < d[idx])
                        {bigger = false; break;}
                }
				if(!bigger) {ok = false;break;}
			}
			if(ok)
				ret.add(i);
		}
		int[] ans = new int[ret.size()];
		for(int i = 0; i < ans.length; i++) {
			ans[i] = ret.get(i);
		}
		return ans;
	}
	
	int[][] getCount(String s) {
		int[][] ret  = new int[2][26];
		for(int i = 0; i < s.length(); i++) {
			ret[0][s.charAt(i) - 'a']++;
		}
		for(int i = 0; i < 26; i++)
			ret[1][i] = i;

		for(int i = 1; i < 26; i++) {
			int j = i-1;
            int c = ret[0][i];
            int idx = ret[1][i];
			while(j >= 0 && c > ret[0][j]) {
				ret[0][j+1] = ret[0][j];
				ret[1][j+1] = ret[1][j];
				j--;
			}
			ret[0][j+1] = c;
			ret[1][j+1] = idx;
		}

		return ret;
	}
	
	public static void main(String[] args)
	{
		long time;
		int[] answer;
		boolean errors = false;
		int[] desiredAnswer;
		
		boolean same;
		
		time = System.currentTimeMillis();
		answer = new StringGame().getWinningStrings(new String[]{"a", "b", "c", "d"});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = new int[]{0, 1, 2, 3 };
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
		answer = new StringGame().getWinningStrings(new String[]{"aabbcc", "aaabbb", "aaaccc"});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = new int[]{1, 2 };
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
		answer = new StringGame().getWinningStrings(new String[]{"ab", "ba"});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = new int[]{ };
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
		answer = new StringGame().getWinningStrings(new String[]{"xaocxsss", "oooxsoas", "xaooosss", "xaocssss", "coxaosxx"});
		System.out.println("Time: " + (System.currentTimeMillis()-time)/1000.0 + " seconds");
		desiredAnswer = new int[]{1, 3, 4 };
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
