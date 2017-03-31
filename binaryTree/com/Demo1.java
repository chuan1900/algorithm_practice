package binaryTree.com;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;



public class Demo1 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo1 bt = new Demo1();
		bt.createBinTree();
		System.out.println(bt.binaryTreePaths().toString());
		//System.out.println(bt.lowestCommonAncestor().data);
		//System.out.println(bt.levelOrder().toString());
		//bt.invertTree();
		//System.out.println(bt.binaryTreePaths().toString());
		
		//????????? Why  java.lang.NullPointerException ???????????
		//System.out.println(bt.LCA().data);
		
		//System.out.println(bt.kthSmallest1(1));
		
		System.out.println(bt.inorderTraversal().toString());
		
	}
	
	private TreeNode root;
	private int[] array = {8,4,10,2,6,9,11,1,3,5,7};  
    private static List<TreeNode> nodeList = null;  
	
	public static class TreeNode{
		TreeNode right;
		TreeNode left;
		int data;
		//构造函数，总是与new操作一起调用，若不写则调用默认无参数构造函数
		TreeNode(int newData){
			right = null;
			left = null;
			data = newData;
		}
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
	
	    // lowestCommonAncestor of BST
		
		public TreeNode lowestCommonAncestor(){
			TreeNode node1 = new TreeNode(2);
			TreeNode node2 = new TreeNode(1);
			return lowestCommonAncestor(root,node1,node2);
		}
		
		
		public TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
				System.out.println("i am here 0");
	        	if (node == null || p == null || q == null) return node;
	        	
	        	//if (node == p || node == q) return node;
	    //if((p.data > node.data && q.data <= node.data) || (p.data <= node.data && q.data > node.data))
	           	        	
	        	if(node.data >= Math.min(p.data, q.data) && node.data <= Math.max(p.data, q.data)){
	        		System.out.println("i am here 1");
	        		System.out.println("current.data1: "+node.data);	        		
	        		return node;
	        	}
	        	else if(p.data > node.data && q.data > node.data){
	        		System.out.println("i am here 2");
	        		System.out.println("current.data2: "+node.data);
	        		return lowestCommonAncestor(node.right,p,q);
	        		
	        	}
	        	else{
	        		System.out.println("i am here 3");
	        		System.out.println("current.data3: "+node.data);
	        		return lowestCommonAncestor(node.left,p,q);
	        		
	        	}
	        	
	    	}
		
		
		//Level order traversal +++++++++++  关于对DFS以及 recursion 的理解  ++++++++++
		
		public List<List<Integer>> levelOrder(){
			List<List<Integer>> list = new ArrayList<List<Integer>>();
			levelOrder(root,list,0);
			return list;
		}
		
		public void levelOrder(TreeNode node, List<List<Integer>> list, int depth) {
	        if(node == null) return;
	         //keep track of the current depth
	        if(list.size() <= depth) list.add(new ArrayList<Integer>());
	        
	        //add data to the proper nth row
	        list.get(depth).add(node.data);
	        levelOrder(node.left, list, depth+1);
	        levelOrder(node.right, list, depth+1);	        	        	
	    }
		
		/*
		 	Invert a binary tree
		 */
		public TreeNode invertTree (){
			return invertTree(root);
		}
		
		public TreeNode invertTree (TreeNode node){
			if(node == null) return node;
			TreeNode temp;
			temp = node.left;
			node.left = node.right;
			node.right = temp;
			
			invertTree(node.left);
			invertTree(node.right);
			
			return node;
		}
		
		//if a binary tree is symmetric. ***Iterative method :BFS ***
		public boolean isSymmetric(TreeNode node) {
			if(node == null) return true;
			
			Queue<TreeNode> left = new LinkedList();
			Queue<TreeNode> right = new LinkedList();
			
			left.add(node.left);
			right.add(node.right);
			
			while(!left.isEmpty() && !right.isEmpty()){
				TreeNode l = left.poll();
				TreeNode r = right.poll();
				
				if(l == null && r == null){
					continue;
				}				
				else if(l == null || r==null) return false;
				else{
					if(l.data != r.data) return false;
					left.add(l.left);
					right.add(r.right);
					
					left.add(l.right);
					right.add(r.left);					
				}							
			}
			
			if(!(left.isEmpty() && right.isEmpty()))  return false;
			
			else return true;	        
	    }
		
		//if a binary tree is symmetric. ***Recursive method :DFS ***
		//This is a mirror symmetry problem, if we use recursion, the root of the tree cannot be included
		//in the function. The trick is to introduce a helper function.
		public boolean isSymmetricRecursion(TreeNode node){
			if(node == null) return true;
			
			/*TreeNode l = node.left;
			TreeNode r = node.right;
			if(l == null && r == null){
				return true;
			}
			if(l == null || r == null ) return false;
			
			return (l.data == r.data) && helper(l.left, r.right) && helper(l.right, r.left);*/
			
			if(node.left != null && node.right != null){
				if(node.left.data != node.right.data) return false;
			}
			
			return helper(node.left, node.right);
		}
		
		public boolean helper(TreeNode left, TreeNode right){
			//base condition			
			if(left == null && right == null){
				return true;
			}
			if(left == null || right == null ) return false;
			
			if(left.data != right.data) return false;
			
			return helper(left.left,right.right) && helper(left.right, right.left);
			
		}
		
		// House Robber III
		public int rob(){
			return rob(root);
		}
		public int rob(TreeNode node) {
			if(node == null) return 0;
			if(node.right == null && node.left == null) return node.data;
			
			//return (node.data+rob(node.left)+rob(node.right));
			//!!!!!!!!!  不一定是偶数层 奇数层... 不要在意命名，纠结太久懒得改了  !!!!!!!!!
	        int sum1 = 0;
	        int sum2 = node.data;
	        if(node.left != null){
	        	sum1 = sum1 + rob(node.left);
	        	sum2 = sum2 + rob(node.left.left) + rob(node.left.right);
	        }
	        if(node.right != null){
	        	sum1 = sum1 + rob(node.right);
	        	sum2 = sum2 + rob(node.right.left) + rob(node.right.right);
	        }
	        //sum = node.data+rob(node.left)+rob(node.right);
	        //sumOdd = node.data+rob(node.left.left)+rob(node.left.right)+rob(node.right.left)+rob(node.right.right);
	         System.out.println("sum1: "+sum1+" sum2: "+sum2);
	         //System.out.println("sum2: "+sum2);
	        return (Math.max(sum1, sum2));			
	    }
		
		/*
		 * LCA of binary tree (not BST)
		 * 
		 * Recursion, iterative is complicated
		 */
		public TreeNode LCA(){
			TreeNode node1 = new TreeNode(4);
			TreeNode node2 = new TreeNode(10);
			return LCA(root , node1, node2 );
		}
		
		public TreeNode LCA(TreeNode node, TreeNode p, TreeNode q) {
	        if(node == null || node ==p || node == q) return node;
	        
	        TreeNode left = LCA(node.left, p, q);
	        TreeNode right = LCA (node.right, p, q);
	        
	        if(left != null && right != null) return node;
	        else{
	        	return left == null? right: left;
	        }
	    }
		
		/*
		 * Kth Smallest Element in a BST
		 */
		public int kthSmallest1(int k){
			List<Integer> list = new ArrayList<Integer>();
			kthSmallest1(root, list);
			return list.get(k-1);
		}
		
		public void kthSmallest1(TreeNode node, List<Integer> list) {
	        if(node == null) return;
	       
	        kthSmallest1(node.left, list);
	        System.out.println(node.data);
	        list.add(node.data);
	        kthSmallest1(node.right, list);
	    }
		/*
		 * *******************
		 */
		int curK = 0;
	    int kSmallest = 0;
	    public int kthSmallest(int k) {
	        curK = k;
	        helper(root);
	        return kSmallest;
	    }
	    
	    public void helper(TreeNode root) {
	        if(root == null) return;
	        helper(root.left);
	        curK--;
	        if(curK == 0) {
	            kSmallest = root.data;
	            return;
	        }
	        helper(root.right);
	    }
	    
	    /*
	     * In-order traversal, iterative
	     */
	    public List<Integer> inorderTraversal(){
	    	List<Integer> result = new ArrayList<Integer>();
	    	return inorderTraversal(root, result);
	    }
	    
	    public List<Integer> inorderTraversal(TreeNode root, List<Integer> result) {
	        if(root == null) return result;
	        TreeNode curr = root;
	        Stack<TreeNode> stack = new Stack<TreeNode>();
	        
	        while(curr != null || !stack.isEmpty()){
	        	while(curr != null){
	        		stack.push(curr);
	        		curr = curr.left;
	        	}
	        	
	        	curr = stack.pop();
	        	result.add(curr.data);
	        	curr = curr.right;
	        }
	        return result;	        
	    }
	    
}
