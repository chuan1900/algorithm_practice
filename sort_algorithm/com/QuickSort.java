/*
*input[]: 2,3,7,4,1,9
*after quick sort: input[] → 1,2,3,4,7,9
*/

class QuickSort {
  private int input[];
  private int length;

  public void sort(input[] numbers){
    if(numbers.length == 0 || numbers == null){
      return;
    }
      this.input = numbers;
      this.length = numbers.length;
      quickSort(0, length);
  }

  private void quickSort(int low, int high){
    int i = low;
    int j = high;
    int pivot = low + (high - low)/2;
/*
*In each iteration, we identify a number in left side of input[] that is greater than pivot
*and a number in right side that is less than pivot
*then swap the above two
*/
    while(i <= j){
      while(input[i] < pivot) i++;
      while(input[j] > pivot) j++;
      if(i <= j){
        swap(i, j);
        i++;
        j++; 
           }
    }
  //call quickSort() recursively
   if(low < j){
    quickSort(low, j);
  }
  if(i < high){
    quickSort(i, high);
  }

 }

private void swap(int i, int j){
  int temp;
  temp = input[i];
  input[i] = input[j];
  input[j] = temp;
}

}




/*
*Insertion Sort
*/
public void insertionSort(int[] nums){
  for(int i = 0; i < nums.length; i++){
    //int curr = nums[i];
    int j = i;
    while(j > 0 && nums[j - 1] > nums[j]){
      swap(nums, nums[j-1], nums[j]);
      j--;
    }

  }
}




