class RadixSort {
	public static void main(String[] args) {
		String[] test ={"4PGC938","2IYE230","3CI0720",
						"1ICK750","1OHV845","4JXY524","1ICK750",
						"3CI0720","1OHV845","1OHV845","2RLA629",
						"2RLA629","3ATW723"};
		
		RadixSort rs = new RadixSort();
		rs.sort(test, 7);
		
		for(String s: test) 
			System.out.println(s);
	}
	
	void sort(String[] s, int w) {
		int R = 256;
		int N = s.length;
		String[] orig = s;
		String[] aux = new String[N];
		for(int i = w - 1; i >= 0; i--) {
			int[] count = new int[R + 1];
			
			for(int j = 0; j< N; j++)
				count[s[j].charAt(i) + 1]++;
			for(int j = 1; j <= R; j++)
				count[j] += count[j - 1];
			for(int j = 0; j< N; j++)
				aux[count[s[j].charAt(i)]++] = s[j];
			//for(int j = 0; j < N; j++)
			//	s[j] = aux[j];
			String[] temp = s;
			s = aux;
			aux = temp;
		}
		
		if(w % 2 != 0)
			for(int i = 0; i < N; i++)
				orig[i] = s[i];
	}
}