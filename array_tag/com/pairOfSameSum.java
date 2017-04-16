How to find all pairs in array of integers whose sum is equal to given number?

Don’t forget to ask questions: 
1. Does array contains only positive numbers or negative?
2. What is some pairs repeated like twice, should I print it every time?
3. Do we need to print only distinct pair? → (3, 3) for sum is 6 ok?
4. How large is the array?



public class Solution{

/*
*  Brute-force 
*  loop through the array
*  [1,4,2,3,6,5] sum = 3  
*  output should be: → (1, 2)
* time complexity: O(n^2)
*/

  public void printPairs(int sum, int[] array){
    for(int i = 0; i < array.length; i++){
	  int first = array[i];
	  for(int j = i+1; j < array; j++){
	       int second = array[j];
	       if(first + second == sum){
		         System.out.println(“(%d, %d)”, first, second);
           }
        }
      }
  }

/*
* Use Hashtable(set)
* time complexity: O(n)
* space complexity: O(n)
*/

  public void printPairsSet(int sum, int[] array){
      if(array.length < 2) return;
      Set<Integer> set = new HashSet<>();
      for(int val : array){
	      int sub = sum - val;
        if(!set.contains(sub)){
           set.add(sub);
       }else{
          System.out.println(“%d, %d”, val, sub);
      }
    }
}




}


