/*
*contains duplicates
*
*/

public boolean containsNearByDuplicates(int[] nums, int k){
	HashMap<Integer, Integer> hm = new HashMap<>();
	for(int i = 0; i < nums.length; i++){
		if(hm.contansKey(nums[i]) && (i - hm.get(nums[i]) <= k)) return true;
		hm.put(nums[i], i);
	}
	return false;
}


/*
* remove duplicates from sorted array
* @return length of the array after removal
*
* [0,1,1,1,2,3,3,5] --> 
*/

public int removeDuplicates(int[] nums){
	int curr = 1;
	for(int i = 0; i < nums.length; i++){
		if(nums[i+1] != nums[i]) nums[curr++] = nums[i+1];
	}
	return curr;
}


/*
* Search in rotated array
* Using binary search to settle out the problem
* input: [6,7,1,2,3,4,5]
* @param 
* @param
* @return index of the target
*/

public int search(int[] nums, int target){
	int low = 0, high = nums.length - 1;
	while(low < high){
		int mid = low + (high - low)/2;
		if(nums[mid] == target) return mid;
		if(nums[mid] >= nums[low]){// left half of the array is well sorted
			if(target >= nums[low] && target < nums[mid]) high = mid - 1;
			else low = mid + 1;
		}
		if(nums[mid] <= nums[low]){//right half of the array is well sorted
			if(target > nums[mid] && target <= nums[high]) low = mid + 1;
			else high = mid - 1;
		}
	}
	return arr[low] == target ? low : -1;
}
















