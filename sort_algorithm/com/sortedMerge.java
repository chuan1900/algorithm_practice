/*
* Sorted Merge
* Merge B into A
*/

void sortedMerge(int[] A, int[] B){
	int indexA = A.length - 1; //index of last element of A
	int indexB = B.length - 1; // index of last element of B
	int curr = lanA + lenB + 1; //the current last index of merged array

	while(indexB >= 0){
		if(indexA >= 0 && A[indexA] < B[indexB]){
			A[curr] = B[indexB];
			indexB--;
		}else{
			A[curr] = A[indexA];
			indexA--;
		}
		curr--;
	}
}