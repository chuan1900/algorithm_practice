package math_tag.com;

import java.util.HashMap;
import java.util.Map;

public class Math {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Math mt = new Math();
		
		String s = "abccccddaaaa";
		System.out.println(mt.longestPalindrome(s));
	}
	
	/*
	 * 7. Reverse Integer
	 * 
	 * The largest integer is: 0x7fffffff => 7是因为有符号位，所以不是八个F
	 */
	public int reverse(int x) {
		//how to handle stack overflow is significant
        long result = 0;
        while(x != 0){
            result = result * 10 + x % 10;
            x /= 10;
            if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE){
                return 0; // stack overflow, return 0
            }
        }
        return (int)result;
    }
	
	
	 public int longestPalindrome(String s) {
	        Map<Character, Integer> hm = new HashMap<>();
	        for(int i = 0; i < s.length(); i++){
	            char c = s.charAt(i);
	            if(!hm.containsKey(c)){
	                hm.put(c, 1);
	            }else{
	                int v = hm.get(c);
	                
	                System.out.println("v: " + v);
	                hm.put(c, ++v);
	            }
	        }
	        
	        int even = 0;
	        int maxOdd = 1;
	        for(Integer val : hm.values()){
	            if(val % 2 != 0 ) {
	            	//maxOdd = maxOdd > val? maxOdd : val;
	            	maxOdd = java.lang.Math.max(val, maxOdd);
	            	System.out.println("odd: " + maxOdd);
	            }
	            if(val % 2 == 0) even += val;
	        }
	        return even + maxOdd;
	    }
}
