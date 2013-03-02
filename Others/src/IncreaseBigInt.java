class IncreaseBigInt {
	public static void main(String[] args) {
		String test = "67";
		
		IncreaseBigInt t = new IncreaseBigInt();
		
		System.out.println(t.inc(test));
	}
	
	String inc(String input) {
		if(input == null || input.length() == 0)
			return "";
		
		int n = input.length();
		int carry = 1;
		char[] out = input.toCharArray();
		for(int i = n - 1; i >= 0; i--) {
			int x = out[i] - '0' + carry;
			out[i] = (char)(x % 10 + '0');
			if(x >= 10) {
				carry = 1;
			} else {
				carry = 0;
				break;
			}
		}
		
		if(carry > 0)
			return "1" + new String(out);
		else
			return new String(out);
		/*
		StringBuffer sb = new StringBuffer();
		if(carry > 0)
			sb.append('1');
		sb.append(out);
		
		return sb.toString();
		*/
	}
}