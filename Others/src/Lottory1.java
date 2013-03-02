class Lottory1 {
	public static void main(String[] args) {
		int[] com = new int[28];
		for(int i = 0; i <= 27; i++)
			com[i] = cal(i);
		
		int total = 0;
		for(int i = 0; i <= 9; i++)
			for(int j = 0; j <= 9; j++)
				for(int k = 0; k <= 9; k++)
				{
					total += com[i+j+k];
				}
		
		for(int i: com)
			System.out.println(i);
		System.out.println("total is " + total);
		
		total = 0;
		int t2 = 0;
		for(int i = 0; i <= 9; i++) {
			total += com[i]*com[i];
			t2 += com[i];
		}
		System.out.println("total is " + total);
		System.out.println("t2 is " + t2);
		
		int num = 999999;
		total = 0;
		while(num-- > 0) {
			if(valid(num))
				total++;
		}
		System.out.println("total is " + total);
	}
	
	static boolean valid(int num) {
		int res = 0;
		res += (num % 10) + ((num /10)%10) + ((num/100)%10);
		if(res == (((num/1000) % 10) + ((num /10000)%10) + ((num/100000)%10)))
			return true;
		else
			return false;
	}
	
	public static int cal(int sum) {
		int ret = 0;
		for(int i = 0; i <= 9; i++) {
			int x = sum - i;
			if(x < 0) break;
			for(int j = 0; j <= 9; j++) {
				int y = x - j;
				
				if(y >= 0 && y <= 9)
					ret++;
			}
			
		}
		return ret;
	}
}