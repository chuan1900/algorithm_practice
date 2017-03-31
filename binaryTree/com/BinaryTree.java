package binaryTree.com;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTree bt = new BinaryTree();
		bt.CreateBinaryTree();
		bt.printTree();
		System.out.println(bt.binaryTreePaths().toString());
		//System.out.println(bt.lowestCommonAncestor().data);
		//System.out.println(bt.countNodes());
		System.out.println(bt.preorderTraversal().toString());
		
		System.out.println(bt.isBalanced());
		
		
	}
	
	private TreeNode root;
	
	private static class TreeNode{
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
	
	//create an empty binary tree
	public void BinaryTree(){
		root = null;
	}
	
	public void CreateBinaryTree(){
		root = new TreeNode(2);
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(4);
		TreeNode node3 = new TreeNode(5);
		TreeNode node4 = new TreeNode(3);
		root.left = node1;
		root.right = node2;
		node1.left = node3;
		//node3.left = node4;
		//node2.right = node3;
		//node2.left = node4;
	}
	
	
	
	/** 
	 Prints the node values in the "inorder" order. 
	 Uses a recursive helper to do the traversal. 
	*/ 
	public void printTree() { 
	 printTree(root); 
	 System.out.println(); 
	}
	private void printTree(TreeNode node) { 
	 if (node == null) return;

	 // left, node itself, right 
	 printTree(node.left); 
	 System.out.print(node.data + "  "); 
	 printTree(node.right); 
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
	
	//lowestCommonAncestor
	public TreeNode lowestCommonAncestor(){
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(4);
		return lowestCommonAncestor(root,node1,node2);
	}
	
	
	public TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
			//System.out.println("i am here 0");
        	if (node == null) return null;
        	
        	//if (node == p || node == q) return node;
        	
        	if((p.data > node.data && q.data <= node.data) || (p.data <= node.data && q.data > node.data)){
        		//System.out.println("i am here 1");
        		//System.out.println("current.data: "+node.data);
        		return node;
        		
        	}
        	if(p.data > node.data && q.data > node.data){
        		//System.out.println("i am here 2");
        		lowestCommonAncestor(node.right,p,q);
        		node = node.right;
        	}
        	if(p.data < node.data && q.data < node.data){
        		//System.out.println("i am here 3");
        		lowestCommonAncestor(node.left,p,q);
        		node = node.left;
        	}
        	
        	
        	return node;
    	}
	
	//*********************
	
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
         //System.out.println("test: "+(2^(height-1)));
         return size+1+(int)(Math.pow(2, height-1)-1);
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
    		if(temp.right !=null){
    			stack.push(temp.right);
    		}
    		if(temp.left !=null){
    			stack.push(temp.left);
    		}
    		list.add(temp.data);
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
    
    /*
     * Balanced Binary Tree
     */
    public boolean isBalanced(){
    	return isBalanced(root);
    }
    
    public boolean isBalanced(TreeNode node) {
        if(node == null) return true;
        int subLeft = treeHeight(node.left);
        int subRight = treeHeight(node.right);
        
        if(Math.abs(subLeft - subRight) > 1) return false;
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //这真的是正确解题的关键！！！！
        //当初，写的是：return true; 显然是不正确的，因为要保证左右子树分别是balance tree
        // Remember !!!!!!!!!!!
        else return isBalanced(node.left) && isBalanced(node.right);
        
        // Better sulotion~~~~~~~~~~~~~~~
        //*********if(l < 0 || r < 0 || abs(l-r) > 1) return false;  
        //*********else return true;  
    }
    
    //height of a tree
    public int treeHeight(TreeNode root){
    	if(root == null) return 0;
    	
    	int left = treeHeight(root.left);
    	int right = treeHeight(root.right);
    	return (left>=right ? left : right) + 1;
    }
	
}
