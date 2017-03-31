package binaryTree.com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import binaryTree.com.Demo1.TreeNode;

public class MediumLevel {
	public static void main(String[] args) {
		
		MediumLevel ml = new MediumLevel();	
		Demo1 dm = new Demo1();
		//ml.createBinTree();
		ml.createBinTree();
		System.out.println(ml.binaryTreePaths().toString());
		//ml.inorder();
		//System.out.println(ml.countNodes());
		System.out.println(ml.preorderTraversal().toString());
		//ml.flatten();
		System.out.println(ml.rightSideView().toString());
		
		//BSTIterator i = new BSTIterator(root);
		//while (i.hasNext()) System.out.println(i.next());
		System.out.println(ml.pathSum(27).toString());
		//int[] nums = {1,2,3,4,5,6,7,8,9};
		//ml.sortedArrayToBST(nums);
		System.out.println(ml.zigzagLevelOrder().toString());
		System.out.println(ml.postorderTraversal().toString());
	}
	
	private static TreeNode root;
	private int[] array = {8,4,10,2,6,9,11,1,3,5,7};  
    private static List<TreeNode> nodeList = null;  
	public void inorder(){
		inorder(root);
	}
	
	public void createBinTree() {  
        nodeList = new LinkedList<TreeNode>();  
        // 将一个数组的值依次转换为Node节点  
        for (int nodeIndex = 0; nodeIndex < array.length; nodeIndex++) {  
            nodeList.add(new TreeNode(array[nodeIndex]));  
        }  
        // 对前lastParentIndex-1个父节点按照父节点与孩子节点的数字关系建立二叉树  
        for (int parentIndex = 0; parentIndex < array.length / 2 - 1; parentIndex++) {  
        	root = nodeList.get(0);
            // 左孩子  
            nodeList.get(parentIndex).left = nodeList  
                    .get(parentIndex * 2 + 1);  
            // 右孩子  
            nodeList.get(parentIndex).right = nodeList  
                    .get(parentIndex * 2 + 2);  
        }  
        // 最后一个父节点:因为最后一个父节点可能没有右孩子，所以单独拿出来处理  
        int lastParentIndex = array.length / 2 - 1;  
        // 左孩子  
        nodeList.get(lastParentIndex).left = nodeList  
                .get(lastParentIndex * 2 + 1);  
        // 右孩子,如果数组的长度为奇数才建立右孩子  
        if (array.length % 2 == 1) {  
            nodeList.get(lastParentIndex).right = nodeList  
                    .get(lastParentIndex * 2 + 2);  
        }  
    } 
	
	//返回Binary Tree的各个路径
		public List<String> binaryTreePaths(){
			List<String> list = new ArrayList<String>();
			String path="";
			return binaryTreePaths( root, list, path);
		}
		
		private List<String> binaryTreePaths(TreeNode node, List<String> list, String path) {
			if (node == null) return list;
			
			if(node.left == null && node.right == null){
				path=path+node.data;
			}
			else{
				path=path+node.data+"->";
			}
			
			//path = path+node.data;
			
	        
	        if(node.left == null && node.right == null){
	        	list.add(path);
	        }
	        else{
	        	binaryTreePaths(node.left,list,path);
	        	binaryTreePaths(node.right,list,path);
	        }
	        
	        return list;
	    }
	
	public void inorder(TreeNode node){
		if(node == null) return;
		if(node.left != null) inorder(node.left);
		System.out.println(node.data);
		if(node.right != null) inorder(node.right);
	}
	
	//Count Complete Tree Nodes
	public int countNodes(){
		return countNodes(root);
	}
	
	public int countNodes(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null) return 1;
        if(root.right == null) return 2;
        int size = size(root.left);
        System.out.println("size: "+size);
        
         int height = 0;
         while(root != null){
             root = root.right;
             height ++;
         }
         System.out.println("height: "+height);
         return size+1+(2^(height-1)-1);
    }
    
    public int size(TreeNode root){
        if(root == null) return 0;
       // System.out.println("size_left: "+height);
        return size(root.left) +1 +size(root.right);
    }
    
 // Iterative preorder Traversal
    public List<Integer> preorderTraversal(){
    	List<Integer> list = new ArrayList<Integer>();
    	return preorderTraversal(root,list);
    }
    
    public List<Integer> preorderTraversal(TreeNode root, List<Integer> list){
    	if(root == null) return list;
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	stack.push(root);
    	while(!stack.isEmpty()){
    		TreeNode temp = stack.pop();
    		/*if(temp.right !=null){
    			stack.push(temp.right);
    		}
    		if(temp.left !=null){
    			stack.push(temp.left);
    		}*/
    		if(temp != null){
    			stack.push(temp.right);
    			stack.push(temp.left);
    			list.add(temp.data);
    		}
    		
    	}
    	
    	return list;
    }
    
    /*
     * Flatten Binary Tree to Linked List
     */
    
    public void flatten(){
    	flatten(root);
    	while(root != null){
    		System.out.println("right: "+ root.data+" left: "+root.left);
    		root = root.right;
    	}
    	
    }
    
    public void flatten(TreeNode root) {
    	if(root == null) return;
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	stack.push(root);
  
    	while(!stack.isEmpty()){
    		TreeNode temp = stack.pop();
    		if(temp.right !=null){
    			stack.push(temp.right);
    		}
    		if(temp.left !=null){
    			stack.push(temp.left);
    		}
    		if(!stack.isEmpty()){
    			temp.right = stack.peek();
    		}
    		
			temp.left = null;   		
    	}
    	
    }
    
    /*
     * Binary Tree Right Side View
     */
    public List<Integer> rightSideView(){
    	List<Integer> list = new ArrayList<Integer>();
    	return rightSideView(root,list);
    }
    
    public List<Integer> rightSideView(TreeNode root, List<Integer> list) {
    	if(root == null) return list;
    	Queue<TreeNode> que1 = new LinkedList<TreeNode>();
    	Queue<TreeNode> que2 = new LinkedList<TreeNode>();
    	que1.add(root);
    	que2.add(root);
    	//if(root.right != null) que2.add(root.right);
    	//if(root.left != null) que2.add(root.left);
    	while(!que1.isEmpty()){
    		int size = que2.size();
    		que2.clear();
    		for(int i = 0; i < size; i++){
        		TreeNode temp = que1.poll();
        		if(i == 0) list.add(temp.data);
        		
        		if(temp.right != null) {
        			que1.add(temp.right) ;
        			que2.add(temp.right) ;        			
        		}
            	if(temp.left != null) {
            		que1.add(temp.left);
            		que2.add(temp.left);
            	}        		
        	}
    		
    	}
    	return list;    	
    }
    
    /*
     * Sum Root to Leaf Numbers
     */
    public int sumNumbers(){
    	String path = "";
    	return sumNumbers(root, path);
    }
    
    public int sum = 0;
    public int sumNumbers(TreeNode node, String path) {
        if(node == null) return (-1);
        
        path = path + node.data;
        
        if(node.right == null && node.left == null){
        	int temp = Integer.parseInt(path);
        	sum = sum + temp;
        }else{
        	sumNumbers(node.left, path);
        	sumNumbers(node.right, path);
        }
        return sum;
    }
    
    //a much better solution~~
    public int helper(TreeNode root, int curSum) {
        if(root == null) return 0;
        // this is the key point
        curSum = curSum*10 + root.data;
        if(root.left == null && root.right == null) return curSum;
        return helper(root.left, curSum) + helper(root.right, curSum);
    }
    
    /*
     * Populating Next Right Pointers in Each Node
     * BFS, 	*****really do not know how to deal with this using DFS
     */
    //public void connect(TreeLinkNode root) {
        
    //}
    
    /*
     * Path Sum II
     */
   // List<List<Integer>> result = new ArrayList<List<Integer>>();
    
    public List<List<Integer>> pathSum(int sum){ 
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	List<Integer> temp = new ArrayList<Integer>();
    	pathSum(root, sum, result, temp);
    	return result;
    }
    
    public void pathSum(TreeNode node, int sum,List<List<Integer>> result, List<Integer> temp) {
    	if(node == null) return;
        
        int subSum = sum - node.data;
        temp.add(node.data);
        
        //为什么result.add()中要new一个ArrayList
        //when you use add function of List, it just add a copy of reference of the object into the List 
        //instead of a new copy of the object. So if you don't create a new copy, 
        //all the reference you add to your result refer to the same object.
        
        if(node.left == null && node.right == null && subSum == 0) {
        	result.add(new ArrayList<Integer>(temp));
        	//temp.clear();
        }
        else{
        	pathSum(node.left, subSum, result, temp);
        	pathSum(node.right, subSum, result,temp);
        }
        
        //为什么要删掉最后一个element??? 以下
        //This is the backtracking point. If we execute two sub recursive code in else branch and still can't 
        // get a match pathSum, this means the current "root" which already added into the List currentResult
        //can not lead us to the correct answer. So we need to remove it from List currentResult
        //and try other possible branches, this is what backtracking means.
        
        temp.remove(temp.size()-1);
    }
    
    /*
     * Convert Sorted Array to Binary Search Tree
     * 
     * Use -------->Something like Binary Search
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, nums.length-1, 0);
    }
    
    public TreeNode sortedArrayToBST(int[] nums, int high, int low) {
        if(low > high) return null;
        // java 中乘1/2 结果为0 ！！！！！！！！！！！！！！
        //int medium = low + 1/2*(high-low);
        int medium = low + (high-low)/2;
        TreeNode root = new TreeNode(nums[medium]);
        root.left = sortedArrayToBST(nums, medium-1, low);
        root.right = sortedArrayToBST(nums, high, medium+1);
        
        return root;
    }
    
    /*
     * Binary Tree Zigzag Level Order Traversal
     * Use ------>BST
     */
    public List<List<Integer>> zigzagLevelOrder(){
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	//List<Integer> temp = new ArrayList<Integer>();
    	return zigzagLevelOrder(root, result);
    }
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root, 
    		List<List<Integer>> result) {
        
    	if(root == null) return result;
    	Queue<TreeNode> que = new LinkedList<TreeNode>();
    	que.add(root);
    	int swap = 0;
    	while(!que.isEmpty()){
    		LinkedList<Integer> temp = new LinkedList<Integer>();
    		
    		//！！！！！！大哥，这个size要固定啊，如果que.size()写在for循环中，会随着循环一直变化的！！！！！！！
    		int size = que.size();
    		if(swap % 2 == 0){
    			for(int i = 0; i < size; i++){
    				TreeNode cur = que.poll();
    				temp.add(cur.data);
    				if(cur.left != null){
    					que.add(cur.left);
    				}
    				if(cur.right != null){
    					que.add(cur.right);
    				}    				
    			}
    		
    		}else{
    			for(int i = 0; i < size; i++){
    				TreeNode cur = que.poll();   				
    				temp.addFirst(cur.data);
    				if(cur.left != null){
    					que.add(cur.left);
    				}
    				if(cur.right != null){
    					que.add(cur.right);
    				} 
    			}    			
    		}
    		result.add(temp);
			swap++;
    	}
    	return result;
    }
    
    /**
     * Solution:
     * DP
     * a BST can be destruct to root, left subtree and right subtree.
     * if the root is fixed, every combination of unique left/right subtrees forms
     * a unique BST.
     * Let a[n] = number of unique BST's given values 1..n, then
     * a[n] = a[0] * a[n-1]     // put 1 at root, 2...n right
     *      + a[1] * a[n-2]     // put 2 at root, 1 left, 3...n right
     *      + ...
     *      + a[n-1] * a[0]     // put n at root, 1...n-1 left
     */
    public int numTrees(int n) {
        int [] G = new int[n+1];
        G[0] = G[1] = 1;
        
        for(int i=2; i<=n; ++i) {
        	for(int j=1; j<=i; ++j) {
        		G[i] += G[j-1] * G[i-j];
        	}
        }

        return G[n];
    }
    
    /*
     * 145. Binary Tree Postorder Traversal
     * Just do a trick, reverse the preorder using add(0, data) or addFirst() of the LinkedList
     */
    public List<Integer> postorderTraversal(){
    	List<Integer> result = new LinkedList<Integer>();
    	return postorderTraversal(root, result);
    }
    
    public List<Integer> postorderTraversal(TreeNode root, List<Integer> result) {
        if(root == null) return result;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        //TreeNode cur = root;
        stack.push(root);
        
        while(!stack.isEmpty()){
        	TreeNode cur = stack.pop();
        	result.add(0, cur.data);
        	if(cur.left != null) stack.push(cur.left);
        	if(cur.right != null) stack.push(cur.right);
        }
        return result;
    }

}







	/*
	 * Just the operation of the in-order traversal
	 */
 class BSTIterator extends MediumLevel{

	 private Stack<TreeNode> stack = new Stack<TreeNode>();
	 private TreeNode cur;

	public BSTIterator(TreeNode root) {
        cur = root;
    }

    /** @return whether we have a next smallest number */
   
    public boolean hasNext() {
         return (!stack.isEmpty() || cur != null);
    }
    

    /** @return the next smallest number */
    //in-order traversal
    public int next() {
        	while(cur != null){
        		stack.push(cur);
        		cur = cur.left;
        	}
        	TreeNode temp = stack.pop();
        	//if(cur.right != null){
        		//cur = cur.right;
        	//}
        	cur = temp.right;
           return temp.data; 
    }
    
    
}
