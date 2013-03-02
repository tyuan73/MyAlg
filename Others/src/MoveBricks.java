// Problem: http://www.mitbbs.com/article_t/JobHunting/32337351.html

class MoveBricks {
	public static void main(String[] args) {
		int[] w = {12, 3, 5, 34, 89, 90};
		
		int x = 100;
		int total = 0;
		int cur = 0;
		for(int weight : w) {
			if(weight > x) {
				System.out.println("can not be done!");
				return;
			}
			if(cur + weight > x) {
				total++;
				cur = weight;
			} else 
				cur += weight;
		}
		total++;
		System.out.println("total = " + total);
	}
}