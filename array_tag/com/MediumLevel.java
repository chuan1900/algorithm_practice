package array_tag.com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediumLevel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {-3,3,4};
		//System.out.println(nums.toString());
		MediumLevel ml = new MediumLevel();
		//int[] ret = ml.twoSum(nums,0);
		//System.out.println(Arrays.toString(ret));
		int[][] matrix = {{1,   3,  5,  7},{10, 11, 16, 20},{23, 30, 34, 50}};
		System.out.println(ml.searchMatrix(matrix, 11));
	}
	
	/*
	 * 39. Combination Sum
	 * 
	 * Backtrack Algorithm
	 * 
	 * 40. Conbination Sum II, slightly difference
	 */
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		//List<Integer> tempList = new ArrayList<>();
		//sort为下面for节约计算
		Arrays.sort(candidates);
		//---------------> 必须是new ArrayList<>()，不然只是原来的副本，结果是错误的
		backtrack(result, new ArrayList<Integer>(), candidates, target, 0);
		return result;
    }
	
	public void backtrack(List<List<Integer>> result, List<Integer> tempList, 
									int[] candidates, int target,int start) {
        if(target < 0) return; 
        // ---------------->构造器：用一个tempList对象来构造，并将该集合的元素添加到ArrayList 
        else if(target == 0) result.add(new ArrayList<Integer>(tempList));
        else{
        	for(int i = start; i < candidates.length; i++){
        		tempList.add(candidates[i]);
        		// not i + 1 because we can reuse same elements
        		backtrack(result, tempList, candidates, target-candidates[i], i);
        		tempList.remove(tempList.size() - 1);
        	}
        }
    }
	
	/*
	 * 40. Combination Sum II, without duplicate of the same number
	 * 
	 * This can explain the "skip duplicates" part : ！！！！！！！！IMPORTANT！！！！！！！！！！
	 *  The revursive component tries the elements after the current one and also tries duplicate elements.
	 *   So we can get correct answer for cases like [1 1] 2. 
	 *   The iterative component checks duplicate combinations and skip it if it is. 
	 *   So we can get correct answer for cases like [1 1 1] 2.
	 * 
	 */
	public List<List<Integer>> combinationSum2(int[] nums, int target) {
	    List<List<Integer>> list = new ArrayList<>();
	    Arrays.sort(nums);
	    backtrack1(list, new ArrayList<Integer>(), nums, target, 0);
	    return list;
	    
	}

	private void backtrack1(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
	    if(remain < 0) return;
	    else if(remain == 0) list.add(new ArrayList<>(tempList));
	    else{
	        for(int i = start; i < nums.length; i++){ // iterative component
	        	//why??? It just said no duplication of each number???
	            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
	            tempList.add(nums[i]);
	            backtrack(list, tempList, nums, remain - nums[i], i + 1); // recursive componenet
	            tempList.remove(tempList.size() - 1); 
	        }
	    }
	} 
	
	/*
	 * 216. Combination Sum III
	 */
	public class Solution {
	    public List<List<Integer>> combinationSum3(int k, int n) {
	        List<List<Integer>> result = new ArrayList<>();
	        List<Integer> tempList = new ArrayList<>();
	        backtrack(result, tempList, k, n, 1);
	        return result;
	    }
	    
	    public void backtrack(List<List<Integer>> result, List<Integer> tempList, int k, int n, int start){
	        //To make this faster, you can quit the for loop early and avoid unnecessary work.
	        if(tempList.size() > k) return;
	        
	        if(tempList.size() == k && n == 0) result.add(new ArrayList<Integer>(tempList));
	        
	        for(int i = start; i <= 9; i++){
	            tempList.add(i); //将i 加入tempList
	            backtrack(result, tempList, k, n - i, i + 1); //遍历i的后代（即：之后的数字），寻找可行的解，类似于深度优先
	            tempList.remove(tempList.size() - 1); // 没有可行的解路径，则删掉i重来
	        }
	    }
	}
	
	/*
	 * 78. Subsets I
	 * 
	 * Backtrack algorithm again. Like DFS and use recursion
	 * 
	 * 找到所有的XXX，这种题目都可以向DP和backtracking的方向去想，这道题和38,
	 * Combination Sum的解法基本是一样的，不同的是这道题目让返回所有的子集，所以在backtrack中不用进行检测，
	 * 直接将当前的subset加入subsets中即可。另外这道题目也反映出了无剪枝的backtracking算法的运行情况，大约会调用2 ^ n次
	 */
	public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> subset = new ArrayList<>();
        backtrack(nums, 0 , result, subset);
        return result;
      }
	
	//------------------------------------------->传参避免了使用全局变量
	//怎样保证subsets不重复？？？
	private void backtrack(int[] nums, int start, List<List<Integer>> result, List<Integer> subset){
		result.add(new ArrayList<>(subset));
		for(int i = start; i < nums.length; i++){
			subset.add(nums[i]);
			backtrack(nums,i + 1, result, subset);
			subset.remove(subset.size() - 1);
		}
	}
	
	/*
	 * 289. Game of Life
	 * 
	 * All about two dimensional array and Logic ~~~
	 * 
	 * 代码全是参考的
	 */
	// 0: dead -> dead
    // 1: live -> live
    // 2: live -> dead
    // 3: dead -> live
    public void gameOfLife(int[][] board) {
        int [] di = {-1, -1, -1, 0, 0, 1, 1, 1};
        int [] dj = {-1, 0, 1, -1, 1, -1, 0, 1};
        int row = board.length, col = row != 0 ? board[0].length : 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                int live = 0;
                //firstly, 判断边界
                for(int k = 0; k < 8; k++){
                    int ii = i + di[k], jj = j + dj[k]; 
                    if(ii < 0 || ii >= row || jj < 0 || jj >= col) continue;
                    if(board[ii][jj] == 2 || board[ii][jj] == 1) live++;
                }
                if(board[i][j] == 1 && (live < 2 || live > 3)) board[i][j] = 2;
                if(board[i][j] == 0 && live == 3) board[i][j] = 3;
            }
        }
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                board[i][j] %= 2;
            }
        }
    }
    
    /*
     * 120. Triangle
     * 
     * DP DP DP DP DP DP DP DP DP DP DP DP
     * 
     * 找到状态转移方程 bottom-up.  对于当前行中的每一个元素，只需要找到上一行中和当前元素相邻的2个元素的最小值，
     * 然后把这儿最小值加上当前元素饼更新sum_vec数组就可以了。不断进行下去，直到最后一行为止。这个过程可以看做一个很简单的动态规划。
     * 
     */
    //calculate in-place
    public int minimumTotal(List<List<Integer>> triangle) {
        for(int i = triangle.size() - 2; i >= 0; i--){
            for(int j = 0; j <=i; j++){
                int sum =triangle.get(i).get(j) + Math.min(triangle.get(i+1).get(j), triangle.get(i+1).get(j+1));
                triangle.get(i).set(j, sum);
            }
        }
        return triangle.get(0).get(0);
    }
   //update an extra one dimensional array using O(N) space
    public int minimumTotal1(List<List<Integer>> triangle){
        List<Integer> minSum = new ArrayList<>(triangle.get(triangle.size() - 1));
        for(int i = triangle.size() - 2; i >= 0; i--){
            for(int j = 0; j <=i; j++){
                int sum = triangle.get(i).get(j) + Math.min(minSum.get(j),minSum.get(j + 1));
                minSum.set(j, sum);
            }
        }
        return minSum.get(0);
    }
    
    /*
     * 11. Container With Most Water
     * 
     * -------------> "Two pointers"
     * 
     * The idea is : to compute area, we need to take min(height[i],height[j]) as our height.
     *  Thus if height[i]<height[j], then the expression min(height[i],height[j]) will always 
     *  lead to at maximum height[i] for all other j(i being fixed), hence no point checking them. 
     *  Similarly when height[i]>height[j] then all the other i's can be ignored for that particular j.
     */   
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int water = (right - left) * Math.min(height[left], height[right]);
        while(left < right){
            if(height[left] < height[right]) left++;
            else right--;
            water = Math.max(water, (right - left) * Math.min(height[left], height[right]));
        }
        return water;
    }
    
    /*
     * 122. Best Time to Buy and Sell Stock II
     * 
     * Greedy: Just like eating shit after seeing the answer
     */
    public int maxProfit(int[] prices) {
        int total = 0;
        for (int i=0; i< prices.length-1; i++) {
        if (prices[i+1]>prices[i]) total += prices[i+1]-prices[i];
        }
    
        return total;
    }
    
    /*
     * 189. Rotate Array
     */
    public void rotate(int[] nums, int k) {
        int size = nums.length;
        reverse(nums, 0, k%size - 1);
        reverse(nums, k%size, size - 1);
        reverse(nums, 0, size - 1);
    }
    public void reverse(int[] nums, int start, int end){
        while(start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    
    /*
     * 35. Search Insert Position
     * 
     * Binary Search
     */
    public int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length - 1, middle = 0;
        while(low <= high){
            middle = low + ((high - low) >> 1);
            if(target == nums[middle]) return middle;
            else{
                if(target > nums[middle]) high = middle - 1;
                else low = middle + 1;
            }
        }
        //if(high <= middle) return high+1;
        //else return low-1;
        return low;
    }
    
    /*
     * 48. Rotate Image
     * 
     *
     * clockwise rotate
     * first reverse up to down, then swap the symmetry 
     * 1 2 3     7 8 9     7 4 1
     * 4 5 6  => 4 5 6  => 8 5 2
     * 7 8 9     1 2 3     9 6 3
     */
 

    /*
     * anticlockwise rotate
     * first reverse left to right, then swap the symmetry
     * 1 2 3     3 2 1     3 6 9
     * 4 5 6  => 6 5 4  => 2 5 8
     * 7 8 9     9 8 7     1 4 7
     */
    
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n; i++){
        	//pay attention to the index j
            for(int j = i; j < n; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // this is to reverse left to right
        for(int i = 0; i < n; i++){
        	//pay attention to the index j
            for(int j = 0; j < n/2; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = temp;
            }
        }
    }
    
    /*
     * 162. Find Peak Element
     * 
     * Binary Search:
     * Consider that each local maximum is one valid peak.
	 * My solution is to find one local maximum with binary search.
	 * Binary search satisfies the O(logn) computational complexity.
     */
    public int findPeakElement(int[] nums) {
        int low = 0, high = nums.length - 1;
        while(low < high){
            int middle1 = low + (high - low)/2;
            int middle2 = middle1 + 1;
            if(nums[middle1] < nums[middle2]){
                low = middle2;
            }else{
                high = middle1;
            }
        }
        return low;
    }
    
    //Sequential search
    public int findPeakElement1(int[] nums){
    	for(int i = 1; i < nums.length; i++){
    		if(nums[i] < nums[i - 1]) return i-1;
    	}
    	return nums.length - 1;
    }
    
    /*
     * 229. Majority Element II
     * 
     * 回头再细研究！！！~~~
     */
    
    public List<Integer> majorityElement(int[] nums) {
    	 //there should be at most 2 different elements in nums more than n/3
    	 //so we can find at most 2 elements based on Boyer-Moore Majority Vote algo
    	 List<Integer> res = new ArrayList<Integer>();
    	 if(nums.length==0) return res;
    	 int count1=0,count2=0,m1=0,m2=0;
    	 for(int n : nums){
    	     if(m1==n) count1++;
    	     else if(m2==n) count2++;
    	     else if(count1==0) {
    	         m1=n;
    	         count1=1;
    	     }
    	     else if(count2==0) {
    	         m2=n;
    	         count2=1;
    	     }
    	     else{
    	         count1--;
    	         count2--;
    	     }
    	 }
    	 count1=0;
    	 count2=0;
    	 //count the number for the 2 elements
    	 for(int n:nums){
    	     if(n==m1) count1++;
    	     else if(n==m2) count2++;
    	 }
    	 //if those appear more than n/3
    	 if(count1>nums.length/3) res.add(m1);
    	 if(count2>nums.length/3) res.add(m2);
    	 return res;

    }
    
    
    /*
     * 167. Two Sum II - Input array is sorted
     * 
     * Without HashMap, just have two pointers or binary search. Much more faster!!!
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        int[] result = new int[2];
        for(int i = 0; i < nums.length; i++){
        	//if(nums[i] > target) break;
            int subsum = target - nums[i];
            if(map.containsKey(subsum)){
            	 System.out.println("i am here");
                result[0] = i + 1;
                result[1] = map.get(subsum) + 1;
                System.out.println(result[0]);
                System.out.println(result[1]);
                break;
            }else{
            	System.out.println("i am here 1");
                map.put(nums[i], i);
            }
        }
        return result;
    }
    
    /*
     * 33. Search in Rotated Sorted Array
     * 
     * Similar problem: 153. Find Minimum in Rotated Sorted Array
     * 
     * Binary Search
     * 
     * The idea is that when rotating the array, there must be one half of the array that is still in sorted order.
	 *For example, 6 7 1 2 3 4 5, the order is disrupted from the point between 7 and 1. So when doing binary search, 
	 * we can make a judgement that which part is ordered and whether the target is in that range, if yes, continue the
	 * search in that half, if not continue in the other half.
     */
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while(low < high){
            int middle = low + (high - low)/2;
            if(target == nums[middle]) return middle;
            if(nums[middle] >= nums[low]){//the left part of the array is well sorted
                //Was the targer in the left part?
                if(target >= nums[low] && target <= nums[middle]) high = middle - 1;
                else low = middle + 1;
            }
            if(nums[middle] <= nums[high]){//the right part of the array is well sorted
                //Was the target in the right part?
                //之所以写<= >= 是为了考虑nums[middle] == nums[high]的情况
                if(target >= nums[middle] && target <= nums[high]) low = middle + 1;
                else high = middle - 1;
            }
        }
        //ATTENTION!!!!!!!!!!!!! while(low < high) ===> 存在 返回low
        return nums[low] == target ? low :-1;
    }
    
    /*
     *153. Find Minimum in Rotated Sorted Array 
     *
     * Like the question above
     *
     * Binary Search
     */
    public int findMin(int[] nums) {
        if(nums.length == 1) return nums[0];
        int low = 0, high = nums.length - 1;
        while(low < high){
            int middle = low + (high - low)/2;
            if(nums[low] < nums[high]) return nums[low];
            //if(nums[middle] < nums[high]) high = middle;
            if(nums[middle] >= nums[low]) low = middle + 1;
            else high = middle;
        }
        return nums[low];
    }
    
    /*
     * 74. Search a 2D Matrix
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int aimRow = 0, left = 0, right = n - 1;
        int flag = 0;
        for(int i = 0; i < m; i++){
            if(matrix[i][0] <= target && matrix[i][n-1] >= target){           	
                aimRow = i;
                System.out.println("aimRow: "+aimRow);
                flag = 1;
                break;
            }
        }
        
        if(flag == 0) return false;
        
        while(left < right){
            int mid = left + (right - left)/2;
            if(matrix[aimRow][mid] == target) return true;
            if(matrix[aimRow][mid] > target) right = mid - 1;
            if(matrix[aimRow][mid] < target) left = mid + 1;
        }
        
        return matrix[aimRow][left] == target ? true : false;
    }
    
    //Another method: just treat it as one-dimensional sorted array
    public boolean searchMatrix2(int[][] matrix, int target) {
    	
    	int row_num = matrix.length;
    	int col_num = matrix[0].length;
    	
    	int begin = 0, end = row_num * col_num - 1;
    	
    	while(begin <= end){
    		int mid = (begin + end) / 2;
    		
    		//the mid value is the key point to solve this problem
    		int mid_value = matrix[mid/col_num][mid%col_num];
    		
    		if( mid_value == target){
    			return true;
    		
    		}else if(mid_value < target){
    			//Should move a bit further, otherwise dead loop.
    			begin = mid+1;
    		}else{
    			end = mid-1;
    		}
    	}
    	
    	return false;
    }
    
    /*
     * 34. Search for a Range
     * 
     * Binary Search
     * 
     * Given a sorted array of integers, find the starting and ending position of a given target value.

		Your algorithm's runtime complexity must be in the order of O(log n).

		If the target is not found in the array, return [-1, -1].
		
		
	 *
	 * Something tricky of the two binary searches, especially the second => int mid = (left + right + 1)/2;
     */
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length -1;
        int[] result = {-1, -1};
        //check the left bound
        while(left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] < target) left = mid + 1;
            if(nums[mid] >= target) right = mid;
        }
        if(nums[left] != target) return result;
        result[0] = left;
        
        //check the right bound
        right = nums.length - 1;
        while(left < right){
            int mid = (left + right + 1)/2;
            if(nums[mid] > target) right = mid - 1;
            if(nums[mid] <= target) left = mid;
        }
        result[1] = right;
        return result;
    }
    
    /*
     * 55. Jump Game
     * 
     * Greedy
     * 
     * I just iterate and update the maximal index that I can reach
     * 
     */
    public boolean canJump(int[] nums) {
        int size = nums.length, reach = 0;
        for(int i = 0; i < size; i++){
        	if(reach < i) return false;
        	reach = Math.max(nums[i] + i, reach);//greedy
        }
        return true;
    }
    
    //think reversely from the above answer
    public boolean canJump1(int[] nums) {
    	int back = nums.length - 1, size = nums.length,i;
    	for(i = size - 2; i >= 0; i--){
    		if(nums[i] + i >= back) back = i;
    	}
    	return i == 0;
    }
    
    /*
     * 45. Jump Game II
     * 
     * Greedy
     */
    public int jump(int[] nums) {
        int curStart = 0, curEnd = 0, reach = 0, jump = 0;
        //pay attention to the index bound
        for(int i = 0; i < nums.length - 1; i++){
            reach = Math.max(nums[i] + i, reach);
            if(i == curEnd){ curEnd = reach;
            jump++;
            }
        }
        return jump;
    }
}













