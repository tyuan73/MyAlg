import java.util.ArrayList;

class JosephusProblem {
	public static void main(String[] args) {
		int N = 41;
		int C = 3;
		
		ArrayList<Integer> list = new ArrayList<Integer> ();
		for(int i = 1; i <= 41; i++)
			list.add(i);
		
		int index = 0; int size = N;
		for(int i = 1; i < 41; i++) {
			index = (index + C - 1) % size--;
			System.out.printf(" %4d", list.get(index));
			list.remove(index);
		}
		
		System.out.printf("\nthe winner is %4d\n", list.get(0));
	}
}