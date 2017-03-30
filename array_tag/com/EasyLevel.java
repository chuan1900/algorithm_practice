package array_tag.com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class EasyLevel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1,2,1,3,6,6,6,6,6,6,6,6,6,8,7};
		//System.out.println(nums.toString());
		EasyLevel el = new EasyLevel();
		el.moveZeroes(nums);
		//以下num.toString()的输出是错误的，输出结果为[类型@哈希值]
		//System.out.println(nums.toString());
		System.out.println(Arrays.toString(nums));
		//System.out.println(el.containsDuplicate1(nums));
		//System.out.println(el.containsNearbyDuplicate(nums, 3));
		System.out.println(el.majorityElement(nums));
		System.out.println(el.getRow(3).toString());
	}
	
	/*
	 * 283. Move Zeroes
	 * 
	 * Time Complexity: O(n)
	 */
	public void moveZeroes(int[] nums) {
        int size = nums.length;
        int cur = 0;
        for(int i = 0; i < size; i++){
        	if(nums[i] != 0) nums[cur++] = nums[i]; 
        }
        
        for(; cur < size; cur++){
        	nums[cur] = 0;
        }
    }
	
	
	/*
	 * 217. Contains Duplicate
	 */	
	public boolean containsDuplicate(int[] nums) {
		int size = nums.length;
        if(size <= 1) return false;
        Set<Integer> set = new HashSet<Integer>();
        int flag = 0;
        for(int i = 0; i < size; i++){
        	set.add(nums[i]);
        }
        if(set.size() != size) return true;
        else return false;
    }
	
	// another approach. Actually this is my original idea~~~~~~, nice implementation!!!!
	public  boolean containsDuplicate1(int[] nums) {
		 Set<Integer> set = new HashSet<Integer>();
		 for(int i : nums)
			 if(!set.add(i))// if there is same
				 return true; 
		 return false;
	 }
	
	/*
	 * 219. Contains Duplicate II
	 * 
	 * HashMap vs. HashSet
	 */
	//------> HashMap
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
			for(int i = 0; i < nums.length; i++){
				if(hm.containsKey(nums[i]) && (i - hm.get(nums[i])) <= k) return true;
				else hm.put(nums[i], i);
			}
			return false;
    }
	//------------>HashSet
	public boolean containsNearbyDuplicate1(int[] nums, int k) {
		Set<Integer> set = new HashSet<Integer>();
			for(int i = 0; i < nums.length; i++){
				//此举是为了将比较范围保持在 k 的范围内
				if(i > k) set.remove(nums[i-k-1]);
				if(!set.add(nums[i])) return true;
			}
			return false;
    }
	
	public int majorityElement(int[] nums) {
        Map<Integer, List<Integer>> hm = new HashMap<Integer, List<Integer>>();
        int ret = 0;
        for(int i = 0; i < nums.length; i++) {
        	if(hm.containsKey(nums[i])) hm.get(nums[i]).add(i);
        	else{
        		hm.put(nums[i], new ArrayList<Integer>());
        		hm.get(nums[i]).add(i);
        	}
        }
        Set<Entry<Integer, List<Integer>>> sets = hm.entrySet();  
        for(Entry<Integer, List<Integer>> entry:sets){
            if(entry.getValue().size() > nums.length/2) {
            	ret = entry.getKey();
            	break;
            }
            
        }
       return ret; 
    }
	
	/*
	 * 26. Remove Duplicates from Sorted Array
	 *  
	 *  Two pointers: i , cur ==> Just let the cur record non duplicate elements 
	 *  
	 *  愚蠢的错误：for 语句中写成：nums[i+1] = nums[cur++]; 注意：Index的初始值。
	 */
	public int removeDuplicates(int[] nums) {
		int size = nums.length;
        int cur = 1;
        for(int i = 0; i < size-1; i++){
        	if(nums[i+1] != nums[i]) nums[cur++] = nums[i+1];
        }
        return cur;
    }
	/*
	 * 80. Remove Duplicates from Sorted Array II: duplicates are allowed at most twice
	 * 
	 */
	public int removeDuplicates2(int[] nums) {
		   int i = 0;
		   for (int n : nums){
			   //nums[]
			   if (i < 2 || n > nums[i - 2])
			    nums[i++] = n; 
		   }
		      
		   return i;
		}
		
	//another one
	int removeDuplicates(int A[], int n, int k) {
		// always remember to check the basic situation   !!!!!!!!!!!!
        if (n <= k) return n;

        int i = 1, j = 1;
        int cnt = 1;
        while (j < n) {
            if (A[j] != A[j-1]) {
                cnt = 1;
                A[i++] = A[j];
            }
            else {
                if (cnt < k) {
                    A[i++] = A[j];
                    cnt++;
                }
            }
            ++j;
        }
        return i;
	}
	
	
	
	/*
	 * 88. Merge Sorted Array, merge 2 into 1
	 * 
	 * Another "two pointers" problem
	 * 
	 * Really should pay attention to the "index".
	 *   
	 * &&&&&He didn't assume n is bigger than m. Either i or j 
	 * could have a chance to drop below zero first. But if it's i, 
	 * noting needed to be done, cause it's already in array nums1.
	 */
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		
        for(int i = m + n -1; i >= 0; i--){
        	if(m > 0 && n > 0){
        		if(nums1[m-1] > nums2[n-1]) {
        			nums1[i] = nums1[m-1];
        			m--;
        		}else{
        			nums1[i] = nums2[n-1];
        			n--;
        		}        		
        	}
        	else if(n > 0){
        		nums1[i] = nums2[n];
        		n--;
        	}
        	
        }
    }
	
	/*
	 * 119. Pascal's Triangle II
	 */
	
	//这个方法参考pascal triangle 1，空间复杂度不符合“Could you optimize your algorithm
	//to use only O(k) extra space?” 要求。。。。。。
	public List<Integer> getRow(int rowIndex) {
        List<Integer> cur = new ArrayList<Integer>();
        List<Integer> pre = new ArrayList<Integer>();
        
        for(int i = 0; i <= rowIndex; i++){
        	
        	//cur.clear() 不对！！！！！  为什么？？？？？
        	cur = new ArrayList<Integer>();
        	for(int j = 0; j < i + 1; j++){
        		//System.out.println("j: "+ j);
        		if(j == 0 || j == i) cur.add(1);
        		else cur.add((pre.get(j-1)+pre.get(j)));
        	}
        	pre = cur;
        	//System.out.println("pre size: "+ pre.size());
        }
        return cur;
    }
	
	// Another more concise solution. Use math
	
	
	 /*
	  * 238. Product of Array Except Self
	  * 
	  * The idea is simply. The product basically is calculated using the numbers before the current
	  *  number and the numbers after the current number. Thus, we can scan the array twice. 
	  *  First, we calcuate the running product of the part before the current number. 
	  *  Second, we calculate the running product of the part after the current number through scanning 
	  *  from the end of the array.
	  */
	public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        int runPrefix = 1;
        for(int i = 0; i < nums.length; i++){
        	result[i] = runPrefix;
        	runPrefix *= nums[i];
        }
        int runSuffix = 1;
        for(int i = nums.length - 1; i <= 0; i--){
        	result[i] *= runSuffix;
        	runSuffix *= nums[i];
        }
        return result;
    }
	
	
}
