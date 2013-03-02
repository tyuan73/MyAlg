import java.util.*;

class Median {
	static ArrayList<Integer> data;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		data = new ArrayList<Integer>();
		while(n-- > 0) {
			String op = in.next();
			int x = in.nextInt();
			if(op.startsWith("r")) {
				if(data.size() == 0)
				{System.out.println("Wrong!");continue;}
				int index = find(x);
				if(index < 0 || index >= data.size() || data.get(index) != x)
					{System.out.println("Wrong!");continue;}
				else
					data.remove(index);
			} else {
				int index = find(x);
				if(index < 0)
					data.add(0, x);
				if(index >= data.size())
					data.add(x);
				else
					data.add(index, x);
			}
			printMedia();
		}
	}
	
	static int find(int target) {
		int l = 0, r = data.size()-1;
		while(l <= r) {
			int mid = (l+r)/2;
			if(data.get(mid) == target)
				return mid;
			if(data.get(mid) < target)
				l = mid+1;
			else
				r = mid-1;
		}
		return l;
	}
	
	static void printMedia() {
		int size = data.size();
		if(size == 0) {
			System.out.println("Wrong!");
			return;
		}
		if((size % 2) == 1) {
			System.out.println(data.get(size / 2));
		} else {
			int x1 = data.get(size/2);
			int x2 = data.get(size/2-1);
			if((x1%2) != (x2%2))
				System.out.println((x1+x2)/2.0);
			else
				System.out.println((x1+x2)/2);
		}
	}
}