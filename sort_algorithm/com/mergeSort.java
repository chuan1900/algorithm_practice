void mergeSort(int[] array, int[] helper, int low, int high){
	if(low < high){
		int mid = (low + high)/2;
		mergeSort(array, helper, low, mid); //sort the left half
		mergeSore(array, helper, mid + 1, high); // sort the right half
		merge(array, helper, low, mid, high);
	}
}

void merge(int[] array, int[] helper, int low, int mid, int high){
	// copy all the elements from array into helper
	for(int i; i < array.lengh; i++){
		helper[i] = array[i];
	}

	int helperLeft = low;
	int helperRighe = mid + 1;
	int curr = low;
  
    // Iterate through helper array
	while(helperLeft <= mid && helperRight <= high){
		if(helper[helperLeft] <= helper[helpRight]){
			array[curr] = helper[helperLeft];
			helperLeft++;
		}else{
			array[curr] = helper[helperRighe];
			helperRight;
		}
		curr++;
	}

	//copy the remaining element of the left half into target array
	int remain = mid - helperLeft;
	for(int i = 0; i < remain; i++){
		array[i] = helper[i];
	}
}