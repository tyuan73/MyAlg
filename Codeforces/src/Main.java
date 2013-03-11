public class Main {

    int i;

    public Main() {i = 1;}

    public void print() {System.out.println("i = " + i);}

    static class subclass extends Main {
        //int i;
        public subclass() {i=2;}
        public void print() {super.print(); System.out.println(i);}
    }

    public static void main(String[] args) {
	// write your code here

        subclass sb = new subclass();
        sb.print();
        Main m = new Main();
        m.print();
    }
}
