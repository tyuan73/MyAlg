import java.util.*;

class TestCompare {
	public final Comparator<Employee>  LASTNAMECOMPARE = new Comparator<Employee>() {
		public int compare(Employee e1, Employee e2) {
			return e1.lastName.compareTo(e2.lastName);
		}
	};
	
	static class Employee implements Comparable<Employee> {
		String firstName;
		String lastName;
		Employee(String fn, String ln) {this.firstName = fn; this.lastName = ln;}
		public int compareTo(Employee e) {return this.firstName.compareTo(e.firstName);}
	}
	public static void main(String[] args) {
		TestCompare tc = new TestCompare();
		String[][] names = {{"abc", "abc"},
							{"abc", "aaa"},
							{"abc", "acc"},
							{"aaa", "ttt"},
							{"aba", "ttt"},
							{"cdg", "cdg"},
							{"vvv", "vvv"},
							{"vvv", "abc"}};
		List<Employee> emps = new ArrayList<Employee>();
		for(int i = 0; i < names.length; i++) {
			emps.add(new Employee(names[i][0], names[i][1]));
			//System.out.println(emps[i].firstName + " " + emps[i].lastName);
		}

		tc.printInOrder2(emps);
	}
	
	void printInOrder(List<Employee> emps) {
		Collections.sort(emps);
		for(Employee e : emps)
			System.out.println(e.firstName + " " + e.lastName);
	}
	
	void printInOrder2(List<Employee> emps) {
		//List<Employee> employees = new ArrayList<Employee>(emps);
		Collections.sort(emps, LASTNAMECOMPARE);
		Collections.sort(emps);
		for(Employee e : emps)
			System.out.println(e.firstName + " " + e.lastName);
	}
}