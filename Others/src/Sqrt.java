class Sqrt {
	public static void main(String[] args) {
		double C = 100089.67;
		double d = 1e-15;
		//double d = 1e-5;
		double r = C;
		double r1 = C/r;
		while(Math.abs(r - r1) > d*r) {
			r = (r1 + r) / 2.0;
			//r = Math.abs(r1-r) *0.5+ Math.min(r1,r);
			r1 = C/r;
			System.out.printf(" r = %5.9f, r1 = %5.9f\n", r, r1);
		}
		
		System.out.printf("%5.9f sqrt is %5.9f\n", C, r);
		
		double c1 = 10087;
		double cuberoot = c1;
		double cuberoot1 = 1.0;
		while(Math.abs(cuberoot - cuberoot1) > d*cuberoot) {
			cuberoot = Math.abs(cuberoot-cuberoot1)/3.0 + Math.min(cuberoot,cuberoot1);
			cuberoot1 = c1/cuberoot/cuberoot;
			System.out.printf(" cuberoot = %5.15f, cuberoot1 = %5.15f\n", cuberoot, cuberoot1);
		}
		
		System.out.printf("%5.15f cube sqrt is %5.15f\n", c1, cuberoot);

	}
}