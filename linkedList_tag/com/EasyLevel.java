package linkedList_tag.com;

public class EasyLevel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) { val = x; }
		  }
	
	/*
	 * 2. Add Two Numbers
	 * 
	 * Use of dump head, and don't forget to go to the next loop after each loop
	 * 
	 */
	 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	        ListNode dump = new ListNode(-1);
	        ListNode d = dump;
	        ListNode t1 = l1;
	        ListNode t2 = l2;
	        int sum = 0;
	        
	        while(t1 != null || t2 != null){
	        	//like caculation of the carry
	            sum /= 10;
	            
	            if(t1 != null) {
	                sum += t1.val;
	                t1 = t1.next;
	            }
	            if(t2 != null) {
	                sum += t2.val;
	                t2 = t2.next;
	            }
	            //get the digit regardless of the carry
	            d.next = new ListNode(sum % 10);
	            d = d.next;
	        }
	        //last digit, if we have a carry, put it in the last position
	        if(sum/10 == 1) dump.next = new ListNode(1);
	        return dump.next;
	    }

}
