import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
	static class Interval {
		int start; int end;
		Interval(int s, int e) {this.start = s; this.end = e;}
	}
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
		String num = in.nextLine();
		int n = Integer.parseInt(num);
		ArrayList<Interval> list = new ArrayList<Interval>();
		for(int i = 0; i < n; i++) {
			String input = in.nextLine();
			String[] split = input.split(" ");
			int s = Integer.parseInt(split[0]);
			int e = Integer.parseInt(split[1]);
			Interval intv = new Interval(s, e);
			if(list.size() == 0 || intv.start > list.get(list.size() - 1).end)
				list.add(intv);
			else
				addInterval(list, intv);
		}
		
		for(Interval v : list) {
			System.out.println(v.start + " " + v.end);
		}
	}
	
	private static void addInterval(ArrayList<Interval> list, Interval intv) {
		int i = 0;
		while(list.size() >0 && i < list.size()) {
			Interval v = list.get(i);
			if(v.end >= intv.start && intv.end >= v.start) {
				intv.start = Math.min(intv.start, v.start);
				intv.end = Math.max(intv.end, v.end);
				list.remove(i);
			} else if(intv.end < v.start) {
				list.add(i, intv);
				break;
			} else {
				i++;
			}
		}
		
		if(intv.start > list.get(list.size() - 1).end)
			list.add(intv);
	}
}