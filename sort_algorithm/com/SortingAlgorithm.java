package sort_algorithm.com;

import java.util.Arrays;

public class SortingAlgorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortingAlgorithm sa = new SortingAlgorithm();
		int[] A ={9,2,4,3,7,3,8,10};
		sa.quickSort(A, 0, 7);
		System.out.println(Arrays.toString(A));
	}
	
	/*
	 * Quick sort
	 * 
	 * 1. Partition (Divide): choose a pivot (p). array[l....r] Rearrange the (sub)array 
	 * so that all other elements in the array
	 * that are less or equal to the pivot are to its left and vice versa.
	 *    
	 * 2. Conquer: recursively sorting (also choosing pivot) the sub-arrays array[l...p-1] and array[p+1....r]
	 * 
	 * 3. In-place, without any extra space. On average, time complexity is O(n log(n))
	 */
	void quickSort(int[] A, int left, int right){
		if(A == null || A.length == 0) return;
		//Important!!!!!! Recursion 的终止标志！！！！！
		if(left >= right) return;
		
		int pivot = A[left], i = left, j = right;
		while(i < j){
			
			//find the number that is less than the pivot, move it to the left
			while(i < j && A[j] >= pivot) j--;
			if(i < j) A[i++] = A[j];
			
			//find the number that is larger or equal than the pivot, move it to the right
			while(i < j && A[i] < pivot) i++;
			if(i < j) A[j--] = A[i];			
		}
		A[i] = pivot;
		quickSort(A, left, i - 1);
		quickSort(A, i + 1, right);
	}
	
	/*
	 * Heap Sort: Complete binary tree
	 * 
	 *  If the parent node is stored at index I, the left child can be calculated by 2 * I + 1 
	 *  and right child by 2 * I + 2.
	 *  
	 *  For max heap:
	 *  1. Build a max heap
	 *  2. At this point, the root is the largest element. swap the root with the last element and reduce 
	 *     the size of the heap by one. Heapify the root the heap
	 *  3. Repeat the above two step until there is no more than one element in the heap (heap size steps).
	 *  
	 *  Heap sort is an in-place algorithm. 
	 *  Time complexity of heapify is O(Logn). Time complexity of createAndBuildHeap() is O(n) and
	 *  overall time complexity of Heap Sort is O(nLogn).
	 */
	
	//i is the index of the array where we want to heapify from, in heap sort typically i is the root
	// n = subarray.length is the size of the heap
	//终止条件有点让晕啊啊啊！！！！
	void heapify(int[] A, int n, int i){
		int largest = i;
		int lc = 2*i + 1, rc = 2*i + 2;
		//if left child > parent
		if(lc < n && A[lc] > A[i]) largest = lc;
		
		//if right child > parent
		if(rc < n && A[rc] > A[i]) largest = rc;
		
		//if the above situations happen, swap parent and its larger child
		if(largest != i){
			int temp = A[i];
			A[i] = A[largest];
			A[largest] = temp;
			
			//heapify the subtree top-down recursively
			//~~~~~~~~~~~~~~until largest is i~~~~~~~~~~~~~
			heapify(A, n, largest);
		}		
	}
	
	//Heap sort
	void heapSort(int[] A){
		int len = A.length;
		//build heap
		//starting from the none leaf node, the leaf nodes can be treated as already heapified
		for(int i = len/2 - 1; i >= 0; i--) heapify(A, len, i);
		
		//swap and sort one by one in the array
		for(int i = len-1; i > 0; i--){
			int temp = A[0];
			A[0] = A[i];
			A[i] = temp;
			
			heapify(A, i, 0);
		}
		
	}
}
