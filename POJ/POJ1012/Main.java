import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
		//int num = in.nextInt();
		ArrayList<Integer> input = new ArrayList<Integer>();
		while(true) {
			int num = in.nextInt();
			if(num != 0)
				input.add(num);
			else
				break;
		}
		
		for(int i : input)
			System.out.println(findMin(i));
	}
	
	static int findMin(int i) {
		//i = i * 2;
		//LinkedList<Integer> l = new LinkedList<Integer>();
		int n = i;
		while(!valid(i, n)) n++;
		return n;
	}
	
	static boolean valid(int i, int n) {
		LinkedList<Integer> l = new LinkedList<Integer>();
		for(int x = 1; x <= 2 * i; x++)
			l.addLast(x);
		for(int j = 0; j < i; j++) {
		System.out.println("j = " + j);
			int z = n;
			while(z-- > 1)
				l.addLast(l.removeFirst());
			if(l.removeFirst() <= i) return false;
		}
		return true;
	}
}