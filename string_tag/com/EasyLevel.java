package string_tag.com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class EasyLevel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EasyLevel easyLevel = new EasyLevel();
		System.out.println(easyLevel.strStr("abbbcc", "bcc"));
	}
	
	public boolean canConstruct(String ransomNote, String magazine) {
		Map<Character, Integer> magM = new HashMap<>();
        for (char c:magazine.toCharArray()){
            //int newCount = magM.getOrDefault(c, 0)+1;
        	int newCount;
        	if(magM.containsKey(c)){
        		newCount = magM.get(c) + 1;
        		magM.put(c, newCount);
        	}else{
        		 magM.put(c, 1);
        	}          
        }
        for (char c:ransomNote.toCharArray()){
            //int newCount = magM.getOrDefault(c,0)-1;
        	int newCount;
        	if(magM.containsKey(c)){
        		newCount = magM.get(c) - 1;
        		//magM.put(c, newCount);
        		 if (newCount<0)  return false;
        		 magM.put(c, newCount);
        	}else return false;        
        }
        return true;
    }
	
	/*
	 * 67. Add Binary
	 * 
	 * Math is so important in algorithm
	 * 
	 * Carry(进位) is necessary
	 * 
	 * Learn to use ****StringBuilder****: 在需要对字符串执行重复修改的情况下，
	 * 与创建新的 String对象相关的系统开销可能会非常昂贵。如果要修改字符串而不创建新的对象，
	 * 则可以使用System.Text.StringBuilder类。
	 * 
	 * =>如果程序对附加字符串的需求很频繁，不建议使用+来进行字符串的串联。
	 */
	 public String addBinary(String a, String b) {
	       StringBuilder sb = new StringBuilder();
	       int lenA = a.length() - 1, lenB = b.length(), carry = 0, sum = 0;
	       while(lenA >= 0 || lenB >= 0){
	    	   sum = carry;
	    	   //substract '0' to guarantee the correct int result
	    	   if(lenA >= 0) sum += a.charAt(lenA--) - '0';
	    	   if(lenB >= 0) sum += b.charAt(lenB--) - '0';
	    	   sb.append(sum % 2);
	    	   carry = sum / 2;
	       }
	       if(carry != 0) sb.append(carry);
	       return sb.reverse().toString();
	 }
	 
	 /*
	  * 28. Implement strStr()
	  * 
	  * Two pointers
	  */
	 public int strStr(String haystack, String needle) {
		 if(needle == null || haystack == null) return -1;
	        //if(needle.length() == 0 && haystack.lenght() == 0) return 0;
	        int lenN = needle.length(), lenH = haystack.length();
	        int i = 0, j = 0, k = 0;
	        //char firstChar = needle.charAt(0);
	        for(i = 0; i <= lenH - lenN; i++){	        	
	        	k = 0;
	        	//int flag = 0;
	        	for(j = i; j < i + lenN; j++){
	        		if(haystack.charAt(j) == needle.charAt(k)){
	        		//这不是while循环啊！！！！为什么会加一行j++！！！！
                    // j++;
                     k++;
                     //break的用法是对的~~~
                 }else break;
	        	}
	        	//if(flag == 1) continue;
	        	System.out.println("i: "+i);
	        	System.out.println("j: "+j);
	        	if(j - i == lenN) return i;
	        }
	        return -1;
	    }
	 
	 // ~~~~~~~~~~A far more elegant solution!!!!!!!!~~~~~~~~~
	 public int strStr1(String haystack, String needle) {
		  for (int i = 0; ; i++) {
		    for (int j = 0; ; j++) {
		      if (j == needle.length()) return i;
		      if (i + j == haystack.length()) return -1;
		      if (needle.charAt(j) != haystack.charAt(i + j)) break;
		    }
		  }
		}
	
	 /*
	  * 125. Valid Palindrome
	  * 
	  * Two pointers
	  * 
	  * Character的自带用法太好用了！！！！！！
	  *  自己写的全是错啊！！！！！
	  */
	 public boolean isPalindrome(String s) {
	        if (s.isEmpty()) {
	        	return true;
	        }
	        int head = 0, tail = s.length() - 1;
	        char cHead, cTail;
	        while(head <= tail) {
	        	cHead = s.charAt(head);
	        	cTail = s.charAt(tail);
	        	if (!Character.isLetterOrDigit(cHead)) {
	        		head++;
	        	} else if(!Character.isLetterOrDigit(cTail)) {
	        		tail--;
	        	} else {
	        		if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
	        			return false;
	        		}
	        		head++;
	        		tail--;
	        	}
	        }
	        
	        return true;
	    }
	 
	 //自己写的 有错！！！！
	 //参考上面解法！！！！~~~~~
	 public boolean isPalindrome1(String s) {
	        if(s.length() == 0) return true;
	        int left = 0, right = s.length() - 1;
	        while(left < right){
	            while((s.charAt(left) < 'A' || s.charAt(left) > 'z' || (s.charAt(left) > 'Z' && s.charAt(left) < 'a')) && left < right) left++;
	            while((s.charAt(right) < 'A' || s.charAt(right) > 'z' || (s.charAt(right) > 'Z' && s.charAt(right) < 'a')) && left < right) right--;
	            //此判断： "0P"之间相差32， 返回true, 显然是错的！！！
	            if(s.charAt(left) == s.charAt(right) || s.charAt(left) == s.charAt(right) - 32 || s.charAt(left) == s.charAt(right) + 32){
	                left++;
	                right--;
	            }else return false;
	        }
	        return true;
	    }
	 
	 //正则解法~~~
	 public boolean isPalindrome2(String s) {
	        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
	        String rev = new StringBuffer(actual).reverse().toString();
	        return actual.equals(rev);
	    }
	 
	 /*
	  * 8. String to Integer (atoi)
	  * 
	  * Pay attention to the &&&&&&&&&&"handle of stack overflow"&&&&&&&&&&&&
	  */
	 public int myAtoi(String str) {
	        int index = 0, sign = 1, total = 0;
	    //1. Empty string
	    if(str.length() == 0) return 0;

	    //2. Remove Spaces
	    while(str.charAt(index) == ' ' && index < str.length())
	        index ++;

	    //3. Handle signs
	    if(str.charAt(index) == '+' || str.charAt(index) == '-'){
	        sign = str.charAt(index) == '+' ? 1 : -1;
	        index ++;
	    }
	    
	    //4. Convert number and avoid overflow
	    while(index < str.length()){
	        int digit = str.charAt(index) - '0';
	        if(digit < 0 || digit > 9) break;

	        //check if total will be overflow after 10 times and add digit
	        //really important
	        if(Integer.MAX_VALUE/10 < total || Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE %10 < digit)
	            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

	        total = 10 * total + digit;
	        index ++;
	    }
	    return total * sign;
	  }
	 
	 /*
	  * 49. Group Anagrams
	  * 
	  * Importance of using sort~~~~~~~
	  * complexity: O(n log n)
	  * 
	  * Very clever!!!!!!!
	  */
	 public List<List<String>> groupAnagrams(String[] strs) {
		 if(strs.length == 0 || strs == null) return new ArrayList<List<String>>();
	     Map<String,List<String>> hm = new HashMap<>();
	     
	     for(int i = 0; i < strs.length; i++){
	    	 char[] ca = strs[i].toCharArray();
	    	 Arrays.sort(ca);
	    	 String keyStr = ca.toString();
	    	 if(!hm.containsKey(keyStr)) hm.put(keyStr, new ArrayList<String>());
	    	 hm.get(keyStr).add(strs[i]);
	     }
	     return new ArrayList<List<String>>(hm.values());
	 }
	 
	 /*
	  * 22. Generate Parentheses
	  * 
	  * Backtracking~~~~~~ 不懂！！！！！
	  * 
	  * The idea here is to only add '(' and ')' that we know will guarantee
	  *  us a solution (instead of adding 1 too many close). 
	  *  Once we add a '(' we will then discard it and try a ')' which can only close a valid '('. 
	  *  Each of these steps are recursively called.
	  * 
	  * A StringBuilder also can be used to append the str instead of + "str"
	  */
	 
	 public List<String> generateParenthesis(int n) {
	        List<String> list = new ArrayList<String>();
	        backtrack(list, "", 0, 0, n);
	        return list;
	    }
	    
	    public void backtrack(List<String> list, String str, int open, int close, int max){
	        
	        if(str.length() == max*2){
	            list.add(str);
	            return;
	        }
	        
	        if(open < max)
	            backtrack(list, str+"(", open+1, close, max);
	        if(close < open)
	            backtrack(list, str+")", open, close+1, max);
	    }
	    
	    //answer using StringBuilder
	    public List<String> generateParenthesis1(int n) {
	        List<String> res = new ArrayList<>();
	        helper(res, new StringBuilder(), 0, 0, n);
	        return res;
	   }

	   private void helper(List<String> res, StringBuilder sb, int open, int close, int n) {
	       if(open == n && close == n) {
	           res.add(sb.toString());
	           return;
	       }
	       
	       if(open < n) {
	           sb.append("(");
	           helper(res, sb, open+1, close, n);
	           sb.setLength(sb.length()-1);
	       } 
	       if(close < open) {
	           sb.append(")");
	           helper(res, sb, open, close+1, n);
	           sb.setLength(sb.length()-1);
	       }
	   }
	   
	   /*
	    * 12. Integer to Roman
	    * 
	    * Brillant idea!!!
	    */
	   public static String intToRoman(int num) {
		    String M[] = {"", "M", "MM", "MMM"};
		    String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
		    String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
		    String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
		    return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
		}
	   
	   //another
	   public String intToRoman1(int num) {
	        int values[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
	        String strs[] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
	        StringBuilder sb = new StringBuilder();
	        
	        for(int i = 0; i < values.length; i++){
	            while(num >= values[i]){
	                num -= values[i];
	                sb.append(strs[i]);
	            }
	        }
	        return sb.toString();
	    }
	   
	   /*
	    * 17. Letter Combinations of a Phone Number
	    * 
	    * Back tracking
	    * For example: "23" => "abc","def"
	    * while round 1: ""
	    * while round 2: a, b, c
	    * while round3:  ad, ae, af, bd, be, bf, cd, ce, cf
	    * 
	    */
	   public List<String> letterCombinations(String digits) {
	        List<String> result = new LinkedList<>();
	        String[] map = {"0", "1","abc", "def", "ghi", "jkl", "mno", "qprs", "tuv", "wxyz"};
	        result.add("");
	        
	        for(int i = 0; i < digits.length(); i++){
	        	// a smart way to convert char to number
	            int d = digits.charAt(i) - '0';
	            while(result.get(0).length() == i){
	                String temp = result.remove(0);
	                for(char c : map[d].toCharArray()){
	                    result.add(temp+c);
	                }
	            }
	        }
	        return result;
	    }
	   
	   /*
	    * 205. Isomorphic Strings
	    * 
	    * Hash Table
	    */
	   public boolean isIsomorphic(String s, String t) {
	        if(s.length() == 0) return true;
	        Map<Character, Character> hm = new HashMap<>();
	        
	        for(int i = 0; i < s.length(); i++){
	            char sc = s.charAt(i);
	            char st = t.charAt(i);
	            if(!hm.containsKey(sc) && !hm.containsValue(st))
	                hm.put(sc, st);
	            else{
	                if(hm.get(sc) == null) return false;
	                if(hm.get(sc) != st) return false;
	            }
	        }
	        return true;
	    }
	   
	   
}
